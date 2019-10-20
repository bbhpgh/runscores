var gatheringCodeVM = new Vue({
	el : '#gatheringCode',
	data : {
		gatheringChannelCode : '',
		gatheringChannelDictItems : [],
		state : '',
		gatheringCodeStateDictItems : [],
		accountTime : dayjs().format('YYYY-MM-DD'),
		gatheringCodes : [],
		pageNum : 1,
		totalPage : 1,
		showGatheringCodeFlag : true,

		editGatheringCode : {
			gatheringChannelCode : '',
			state : '',
			gatheringAmount : '',
			payee : ''
		},
		showEditGatheringCodeFlag : false,
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		var that = this;
		headerVM.title = '收款码';
		headerVM.showBackFlag = true;
		that.loadGatheringChannelDictItem();
		that.loadGatheringCodeStateDictItem();
		that.loadGatheringCodeByPage();

		$('.gathering-code-pic').on('fileuploaded', function(event, data, previewId, index) {
			that.editGatheringCode.storageId = data.response.data.join(',');
			that.addOrUpdateGatheringCodeInner();
		});
	},
	methods : {
		/**
		 * 加载收款渠道字典项
		 */
		loadGatheringChannelDictItem : function() {
			var that = this;
			that.$http.get('/dictconfig/findDictItemInCache', {
				params : {
					dictTypeCode : 'gatheringChannel'
				}
			}).then(function(res) {
				this.gatheringChannelDictItems = res.body.data;
			});
		},

		loadGatheringCodeStateDictItem : function() {
			var that = this;
			that.$http.get('/dictconfig/findDictItemInCache', {
				params : {
					dictTypeCode : 'gatheringCodeState'
				}
			}).then(function(res) {
				this.gatheringCodeStateDictItems = res.body.data;
			});
		},

		query : function() {
			this.pageNum = 1;
			this.loadGatheringCodeByPage();
		},

		prePage : function() {
			this.pageNum = this.pageNum - 1;
			this.loadGatheringCodeByPage();
		},

		nextPage : function() {
			this.pageNum = this.pageNum + 1;
			this.loadGatheringCodeByPage();
		},

		loadGatheringCodeByPage : function() {
			var that = this;
			that.$http.get('/gatheringCode/findMyGatheringCodeByPage', {
				params : {
					pageSize : 5,
					pageNum : that.pageNum,
					state : that.state,
					gatheringChannelCode : that.gatheringChannelCode
				}
			}).then(function(res) {
				that.gatheringCodes = res.body.data.content;
				that.pageNum = res.body.data.pageNum;
				that.totalPage = res.body.data.totalPage;
			});
		},

		initFileUploadWidget : function(storageId) {
			var initialPreview = [];
			var initialPreviewConfig = [];
			if (storageId != null) {
				initialPreview.push('/storage/fetch/' + storageId);
				initialPreviewConfig.push({
					downloadUrl : '/storage/fetch/' + storageId
				});
			}
			$('.gathering-code-pic').fileinput('destroy').fileinput({
				browseOnZoneClick : true,
				showBrowse : false,
				showCaption : false,
				showClose : true,
				showRemove : false,
				showUpload : false,
				dropZoneTitle : '点击选择图片',
				dropZoneClickTitle : '',
				layoutTemplates : {
					footer : ''
				},
				maxFileCount : 1,
				uploadUrl : '/storage/uploadPic',
				enctype : 'multipart/form-data',
				allowedFileExtensions : [ 'jpg', 'png', 'bmp', 'jpeg' ],
				initialPreview : initialPreview,
				initialPreviewAsData : true,
				initialPreviewConfig : initialPreviewConfig
			});
		},

		switchGatheringAmountMode : function() {
			if (!this.editGatheringCode.fixedGatheringAmount) {
				this.editGatheringCode.gatheringAmount = '';
			}
		},

		showEditGatheringCodePage : function(gatheringCodeId) {
			var that = this;
			if (gatheringCodeId == null || gatheringCodeId == '') {
				that.editGatheringCode = {
					gatheringChannelCode : '',
					state : '',
					fixedGatheringAmount : true,
					gatheringAmount : '',
					payee : ''
				};
				that.showEditGatheringCodePageInner();
				that.initFileUploadWidget();
			} else {
				that.$http.get('/gatheringCode/findMyGatheringCodeById', {
					params : {
						id : gatheringCodeId,
					}
				}).then(function(res) {
					that.editGatheringCode = res.body.data;
					that.showEditGatheringCodePageInner();
					that.initFileUploadWidget(res.body.data.storageId);
				});
			}
		},

		showEditGatheringCodePageInner : function() {
			headerVM.showBackFlag = false;
			headerVM.title = '编辑收款码';
			this.showGatheringCodeFlag = false;
			this.showEditGatheringCodeFlag = true;
		},

		hideEditGatheringCodePage : function() {
			headerVM.showBackFlag = true;
			headerVM.title = '收款码';
			this.showGatheringCodeFlag = true;
			this.showEditGatheringCodeFlag = false;
		},

		addOrUpdateGatheringCode : function() {
			var that = this;
			var editGatheringCode = that.editGatheringCode;
			if (editGatheringCode.gatheringChannelCode == null || editGatheringCode.gatheringChannelCode == '') {
				layer.alert('请选择收款渠道', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (editGatheringCode.state == null || editGatheringCode.state == '') {
				layer.alert('请选择状态', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (editGatheringCode.fixedGatheringAmount == null) {
				layer.alert('请选择是否固定收款金额', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (editGatheringCode.fixedGatheringAmount) {
				if (editGatheringCode.gatheringAmount == null || editGatheringCode.gatheringAmount == '') {
					layer.alert('请输入收款金额', {
						title : '提示',
						icon : 7,
						time : 3000
					});
					return;
				}
			}
			if (editGatheringCode.payee == null || editGatheringCode.payee == '') {
				layer.alert('请选择收款人', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}

			if ($('.gathering-code-pic').fileinput('getPreview').content.length != 0) {
				that.addOrUpdateGatheringCodeInner();
			} else {
				var filesCount = $('.gathering-code-pic').fileinput('getFilesCount');
				if (filesCount == 0) {
					layer.alert('请选择要上传的图片', {
						title : '提示',
						icon : 7,
						time : 3000
					});
					return;
				}
				$('.gathering-code-pic').fileinput('upload');
			}
		},

		addOrUpdateGatheringCodeInner : function() {
			var that = this;
			that.$http.post('/gatheringCode/addOrUpdateGatheringCode', that.editGatheringCode).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.hideEditGatheringCodePage();
				that.query();
			});
		},

		delGatheringCode : function(gatheringCodeId) {
			var that = this;
			that.$http.get('/gatheringCode/delMyGatheringCodeById', {
				params : {
					id : gatheringCodeId,
				}
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.hideEditGatheringCodePage();
				that.query();
			});
		}
	}
});
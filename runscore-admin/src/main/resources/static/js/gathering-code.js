var gatheringCodeVM = new Vue({
	el : '#gathering-code',
	data : {
		state : '',
		gatheringCodeStateDictItems : [],
		gatheringChannelCode : '',
		gatheringChannelDictItems : [],
		payee : '',
		userName : '',

		addOrUpdateGatheringCodeFlag : false,
		gatheringCodeActionTitle : '',
		editGatheringCode : {},

		viewGatheringCodeFlag : false,
		viewGatheringCodeUrl : '',

	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		var that = this;
		that.loadGatheringCodeStateDictItem();
		that.loadGatheringChannelDictItem();
		that.initTable();

		$('.gathering-code-pic').on('fileuploaded', function(event, data, previewId, index) {
			that.editGatheringCode.storageId = data.response.data.join(',');
			that.addOrUpdateGatheringCodeInner();
		});
	},
	methods : {

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

		initTable : function() {
			var that = this;
			$('.gathering-code-table').bootstrapTable({
				classes : 'table table-hover',
				height : 490,
				url : '/gatheringCode/findGatheringCodeByPage',
				pagination : true,
				sidePagination : 'server',
				pageNumber : 1,
				pageSize : 10,
				pageList : [ 10, 25, 50, 100 ],
				queryParamsType : '',
				queryParams : function(params) {
					var condParam = {
						pageSize : params.pageSize,
						pageNum : params.pageNumber,
						state : that.state,
						gatheringChannelCode : that.gatheringChannelCode,
						payee : that.payee,
						userName : that.userName
					};
					return condParam;
				},
				responseHandler : function(res) {
					return {
						total : res.data.total,
						rows : res.data.content
					};
				},
				columns : [ {
					field : 'gatheringChannelName',
					title : '收款渠道'
				}, {
					field : 'stateName',
					title : '收款码状态'
				}, {
					title : '收款金额',
					formatter : function(value, row, index, field) {
						if (row.fixedGatheringAmount) {
							return row.gatheringAmount;
						} 
						return '不固定';
					}
				}, {
					field : 'payee',
					title : '收款人'
				}, {
					field : 'userName',
					title : '所属账号'
				}, {
					field : 'createTime',
					title : '创建时间'
				}, {
					title : '操作',
					formatter : function(value, row, index) {
						return [ '<button type="button" class="view-gathering-code-btn btn btn-outline-primary btn-sm" style="margin-right: 4px;">查看收款码</button>', '<button type="button" class="edit-gathering-code-btn btn btn-outline-success btn-sm" style="margin-right: 4px;">编辑</button>', '<button type="button" class="del-gathering-code-btn btn btn-outline-danger btn-sm">删除</button>' ].join('');
					},
					events : {
						'click .view-gathering-code-btn' : function(event, value, row, index) {
							that.openViewGatheringCodeModal(row);
						},
						'click .edit-gathering-code-btn' : function(event, value, row, index) {
							that.openEditGatheringCodeModal(row.id);
						},
						'click .del-gathering-code-btn' : function(event, value, row, index) {
							that.delGatheringCode(row.id);
						}
					}
				} ]
			});
		},

		refreshTable : function() {
			$('.gathering-code-table').bootstrapTable('refreshOptions', {
				pageNumber : 1
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

		openViewGatheringCodeModal : function(gatheringCode) {
			this.viewGatheringCodeFlag = true;
			this.viewGatheringCodeUrl = '/storage/fetch/' + gatheringCode.storageId;
		},

		switchGatheringAmountMode : function() {
			if (!this.editGatheringCode.fixedGatheringAmount) {
				this.editGatheringCode.gatheringAmount = '';
			}
		},

		openAddGatheringCodeModal : function() {
			this.addOrUpdateGatheringCodeFlag = true;
			this.gatheringCodeActionTitle = '新增收款码';
			this.editGatheringCode = {
				userName : '',
				gatheringChannelCode : '',
				state : '',
				fixedGatheringAmount : true,
				gatheringAmount : '',
				payee : ''
			};
			this.initFileUploadWidget();
		},

		openEditGatheringCodeModal : function(gatheringCodeId) {
			var that = this;
			that.$http.get('/gatheringCode/findGatheringCodeById', {
				params : {
					id : gatheringCodeId,
				}
			}).then(function(res) {
				that.addOrUpdateGatheringCodeFlag = true;
				that.gatheringCodeActionTitle = '编辑收款码';
				that.editGatheringCode = res.body.data;
				that.initFileUploadWidget(res.body.data.storageId);
			});
		},

		addOrUpdateGatheringCode : function() {
			var that = this;
			var editGatheringCode = that.editGatheringCode;
			if (editGatheringCode.userName == null || editGatheringCode.userName == '') {
				layer.alert('请输入所属账号', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
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
				that.addOrUpdateGatheringCodeFlag = false;
				that.refreshTable();
			});
		},

		delGatheringCode : function(gatheringCodeId) {
			var that = this;
			that.$http.get('/gatheringCode/delGatheringCodeById', {
				params : {
					id : gatheringCodeId,
				}
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.addOrUpdateGatheringCodeFlag = false;
				that.refreshTable();
			});
		}
	}
});
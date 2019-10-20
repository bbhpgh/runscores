var appealRecordVM = new Vue({
	el : '#appeal-record',
	data : {
		orderNo : '',
		gatheringChannelCode : '',
		gatheringChannelDictItems : [],
		receiverUserName : '',
		appealType : '',
		appealTypeDictItems : [],
		appealState : '',
		appealStateDictItems : [],
		initiatorObj : '',
		initiatorObjDictItems : [],
		appealProcessWay : '',
		appealProcessWayDictItems : [],
		initiationStartTime : dayjs().format('YYYY-MM-DD'),
		initiationEndTime : dayjs().format('YYYY-MM-DD'),

		showAppealRecordFlag : true,
		selectAppeal : {},
		showUploadPicModalFlag : false,
		merchantSreenshotIds : ''
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		var that = this;
		that.loadGatheringChannelDictItem();
		that.loadAppealTypeDictItem();
		that.loadAppealStateDictItem();
		that.loadInitiatorObjDictItem();
		that.loadAppealProcessWayDictItem();
		that.initTable();

		$('.upload-sreenshot').on('filebatchuploadsuccess', function(event, data) {
			that.merchantSreenshotIds = data.response.data.join(',');
			that.uploadSreenshotInner();
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

		loadAppealTypeDictItem : function() {
			var that = this;
			that.$http.get('/dictconfig/findDictItemInCache', {
				params : {
					dictTypeCode : 'appealType'
				}
			}).then(function(res) {
				this.appealTypeDictItems = res.body.data;
			});
		},

		loadAppealStateDictItem : function() {
			var that = this;
			that.$http.get('/dictconfig/findDictItemInCache', {
				params : {
					dictTypeCode : 'appealState'
				}
			}).then(function(res) {
				this.appealStateDictItems = res.body.data;
			});
		},

		loadInitiatorObjDictItem : function() {
			var that = this;
			that.$http.get('/dictconfig/findDictItemInCache', {
				params : {
					dictTypeCode : 'appealInitiatorObj'
				}
			}).then(function(res) {
				this.initiatorObjDictItems = res.body.data;
			});
		},

		loadAppealProcessWayDictItem : function() {
			var that = this;
			that.$http.get('/dictconfig/findDictItemInCache', {
				params : {
					dictTypeCode : 'appealProcessWay'
				}
			}).then(function(res) {
				this.appealProcessWayDictItems = res.body.data;
			});
		},

		initTable : function() {
			var that = this;
			$('.appeal-record-table').bootstrapTable({
				classes : 'table table-hover',
				height : 490,
				url : '/appeal/findAppealByPage',
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
						orderNo : that.orderNo,
						gatheringChannelCode : that.gatheringChannelCode,
						receiverUserName : that.receiverUserName,
						appealType : that.appealType,
						appealState : that.appealState,
						appealProcessWay : that.appealProcessWay,
						initiationStartTime : that.initiationStartTime,
						initiationEndTime : that.initiationEndTime
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
					field : 'orderNo',
					title : '订单号'
				}, {
					title : '收款渠道/收款金额',
					formatter : function(value, row, index, field) {
						var text = row.gatheringChannelName + '/' + row.gatheringAmount + '元';
						return text;
					}
				}, {
					title : '接单人/接单时间',
					formatter : function(value, row, index, field) {
						if (row.receiverUserName == null) {
							return;
						}
						var text = row.receiverUserName + '/' + row.receivedTime;
						return text;
					}
				}, {
					field : 'appealTypeName',
					title : '申诉类型'
				}, {
					field : 'initiatorObjName',
					title : '发起方'
				}, {
					field : 'stateName',
					title : '状态'
				}, {
					field : 'processWayName',
					title : '处理方式'
				}, {
					field : 'initiationTime',
					title : '发起时间'
				}, {
					title : '操作',
					formatter : function(value, row, index) {
						if (row.state == '1' && row.initiatorObj == 'user' && (row.merchantSreenshotIds == null || row.merchantSreenshotIds.length == 0)) {
							return [ '<button type="button" class="deal-btn btn btn-outline-danger btn-sm">提供截图</button>' ].join('');
						} else {
							return [ '<button type="button" class="deal-btn btn btn-outline-info btn-sm">查看详情</button>' ].join('');
						}
					},
					events : {
						'click .deal-btn' : function(event, value, row, index) {
							that.showViewDetailsPage(row.id);
						}
					}
				} ]
			});
		},

		refreshTable : function() {
			$('.appeal-record-table').bootstrapTable('refreshOptions', {
				pageNumber : 1
			});
		},

		showAppealRecordPage : function() {
			this.showAppealRecordFlag = true;
			this.refreshTable();
		},

		showViewDetailsPage : function(appealId) {
			var that = this;
			that.$http.get('/appeal/findAppealById', {
				params : {
					appealId : appealId
				}
			}).then(function(res) {
				this.showAppealRecordFlag = false;
				this.selectAppeal = res.body.data;
			});
		},

		showUploadPicModal : function() {
			this.showUploadPicModalFlag = true;
			this.initFileUploadWidget();
		},

		initFileUploadWidget : function() {
			var initialPreview = [];
			var initialPreviewConfig = [];
			$('.upload-sreenshot').fileinput('destroy').fileinput({
				uploadAsync : false,
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
				maxFileCount : 2,
				uploadUrl : '/storage/uploadPic',
				enctype : 'multipart/form-data',
				allowedFileExtensions : [ 'jpg', 'png', 'bmp', 'jpeg' ],
				initialPreview : initialPreview,
				initialPreviewAsData : true,
				initialPreviewConfig : initialPreviewConfig
			});
		},

		uploadSreenshot : function() {
			var filesCount = $('.upload-sreenshot').fileinput('getFilesCount');
			if (filesCount == 0) {
				layer.alert('请上传截图', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			$('.upload-sreenshot').fileinput('upload');
		},

		uploadSreenshotInner : function() {
			var that = this;
			that.$http.get('/appeal/merchantUploadSreenshot', {
				params : {
					appealId : that.selectAppeal.id,
					merchantSreenshotIds : that.merchantSreenshotIds
				}
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.showUploadPicModalFlag = false;
				that.showViewDetailsPage(that.selectAppeal.id);
			});
		},

		merchantCancelAppeal : function(appealId) {
			var that = this;
			that.$http.get('/appeal/merchantCancelAppeal', {
				params : {
					appealId : appealId
				}
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.showAppealRecordPage();
				that.refreshTable();
			});
		},

		viewImage : function(imagePath) {
			var image = new Image();
			image.src = imagePath;
			var viewer = new Viewer(image, {
				hidden : function() {
					viewer.destroy();
				},
			});
			viewer.show();
		}
	}
});
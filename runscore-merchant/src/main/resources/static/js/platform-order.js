var platformOrderVM = new Vue({
	el : '#platform-order',
	data : {
		orderNo : '',
		platformName : '',
		gatheringChannelCode : '',
		gatheringChannelDictItems : [],
		orderState : '',
		platformOrderStateDictItems : [],
		receiverUserName : '',
		submitStartTime : dayjs().format('YYYY-MM-DD'),
		submitEndTime : dayjs().format('YYYY-MM-DD'),
		showMerchantRecordFlag : true,

		showAddOrderFlag : false,
		gatheringChannelCodeWithAddOrder : '',
		gatheringAmountWithAddOrder : '',
		orderNoWithAddOrder : '',
		notifyUrlWithAddOrder : '',
		returnUrlWithAddOrder : '',
		attchWithAddOrder : '',

		showOrderDetailsFlag : false,
		selectedOrderDetails : {},

		showStartAppealFlag : false,
		appealTypeDictItems : [],
		appealType : '',
		actualPayAmount : '',
		merchantSreenshotIds : ''
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		var that = this;
		that.loadGatheringChannelDictItem();
		that.loadPlatformOrderStateDictItem();
		that.loadAppealTypeDictItem();
		that.initTable();

		$('.sreenshot').on('filebatchuploadsuccess', function(event, data) {
			that.merchantSreenshotIds = data.response.data.join(',');
			that.merchantStartAppealInner();
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

		/**
		 * 加载平台订单状态字典项
		 */
		loadPlatformOrderStateDictItem : function() {
			var that = this;
			that.$http.get('/dictconfig/findDictItemInCache', {
				params : {
					dictTypeCode : 'platformOrderState'
				}
			}).then(function(res) {
				this.platformOrderStateDictItems = res.body.data;
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

		initTable : function() {
			var that = this;
			$('.platform-order-table').bootstrapTable({
				classes : 'table table-hover',
				height : 490,
				url : '/merchantOrder/findMerchantOrderByPage',
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
						platformName : that.platformName,
						orderState : that.orderState,
						gatheringChannelCode : that.gatheringChannelCode,
						receiverUserName : that.receiverUserName,
						submitStartTime : that.submitStartTime,
						submitEndTime : that.submitEndTime
					};
					return condParam;
				},
				responseHandler : function(res) {
					return {
						total : res.data.total,
						rows : res.data.content
					};
				},
				detailView : true,
				detailFormatter : function(index, row, element) {
					var html = template('platform-order-detail', {
						platformOrderInfo : row
					});
					return html;
				},
				columns : [ {
					field : 'orderNo',
					title : '订单号'
				}, {
					field : 'orderStateName',
					title : '订单状态'
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
					field : 'submitTime',
					title : '提交时间'
				}, {
					field : 'confirmTime',
					title : '确认时间'
				}, {
					title : '通知状态',
					formatter : function(value, row, index, field) {
						return row.payInfo.noticeStateName;
					}
				}, {
					title : '操作',
					formatter : function(value, row, index) {
						var btns = [];
						btns.push('<button type="button" class="order-details-btn btn btn-outline-info btn-sm">订单详情</button>');
						if (row.orderState == '1') {
							btns.push('<button type="button" class="cancel-order-btn btn btn-outline-danger btn-sm">取消订单</button>');
						}
						if (row.payInfo.noticeState == '3') {
							btns.push('<button type="button" class="resend-notice-btn btn btn-outline-info btn-sm">重发通知</button>');
						}
						if (btns.length > 0) {
							return btns.join('');
						}
					},
					events : {
						'click .cancel-order-btn' : function(event, value, row, index) {
							that.cancelOrder(row.id);
						},
						'click .order-details-btn' : function(event, value, row, index) {
							that.showOrderDetailsPage(row.id);
						},
						'click .resend-notice-btn' : function(event, value, row, index) {
							that.resendNotice(row.id);
						}
					}
				} ]
			});
		},

		refreshTable : function() {
			$('.platform-order-table').bootstrapTable('refreshOptions', {
				pageNumber : 1
			});
		},

		resendNotice : function(id) {
			var that = this;
			layer.load(0, {
				time : 8000,
				shade : [ 0.1, '#fff' ]
			});
			that.$http.get('/merchantOrder/resendNotice', {
				params : {
					id : id
				}
			}).then(function(res) {
				layer.closeAll('loading');
				that.refreshTable();
				var result = res.body.data;
				if (result == null || result == '') {
					result = '无内容';
				}
				layer.open({
					type : 1,
					anim : 2,
					shadeClose : true,
					area : [ '420px', '240px' ],
					content : '<div style="padding: 10px;">' + '<p>返回内容</p>' + '<div style="color: red;">' + result + '</div>' + '</div>'
				});
			});
		},

		showAddOrderModal : function() {
			this.showAddOrderFlag = true;
			this.gatheringChannelCodeWithAddOrder = '';
			this.gatheringAmountWithAddOrder = '';
			this.orderNoWithAddOrder = '';
			this.notifyUrlWithAddOrder = '';
			this.returnUrlWithAddOrder = '';
			this.attchWithAddOrder = '';
		},

		addOrder : function() {
			var that = this;
			if (that.gatheringChannelCodeWithAddOrder == null || that.gatheringChannelCodeWithAddOrder == '') {
				layer.alert('请选择收款渠道', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (that.gatheringAmountWithAddOrder == null || that.gatheringAmountWithAddOrder == '') {
				layer.alert('请输入收款金额', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (that.orderNoWithAddOrder == null || that.orderNoWithAddOrder == '') {
				layer.alert('请输入商户订单号', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (that.notifyUrlWithAddOrder == null || that.notifyUrlWithAddOrder == '') {
				layer.alert('请输入异步通知地址', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (that.returnUrlWithAddOrder == null || that.returnUrlWithAddOrder == '') {
				layer.alert('请输入同步通知地址', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			that.$http.post('/merchantOrder/startOrder', {
				gatheringChannelCode : that.gatheringChannelCodeWithAddOrder,
				gatheringAmount : that.gatheringAmountWithAddOrder,
				orderNo : that.orderNoWithAddOrder,
				notifyUrl : that.notifyUrlWithAddOrder,
				returnUrl : that.returnUrlWithAddOrder,
				attch : that.attchWithAddOrder
			}, {
				emulateJSON : true
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.showAddOrderFlag = false;
				that.refreshTable();
			});
		},

		showOrderDetailsPage : function(orderId) {
			var that = this;
			that.$http.get('/merchantOrder/findMerchantOrderDetailsById', {
				params : {
					orderId : orderId
				}
			}).then(function(res) {
				that.selectedOrderDetails = res.body.data;
				that.showMerchantRecordFlag = false;
				that.showOrderDetailsFlag = true;
				that.showStartAppealFlag = false;
			});
		},

		cancelOrder : function(id) {
			var that = this;
			layer.confirm('确定要取消订单吗?', {
				icon : 7,
				title : '提示'
			}, function(index) {
				layer.close(index);
				that.$http.get('/merchantOrder/merchantCancelOrder', {
					params : {
						id : id
					}
				}).then(function(res) {
					layer.alert('操作成功!', {
						icon : 1,
						time : 3000,
						shade : false
					});
					that.showAddOrderFlag = false;
					that.refreshTable();
				});
			});
		},

		showMerchantRecordPage : function() {
			this.showMerchantRecordFlag = true;
			this.showOrderDetailsFlag = false;
			this.showStartAppealFlag = false;
			this.refreshTable();
		},

		showStartAppealPage : function() {
			this.showMerchantRecordFlag = false;
			this.showOrderDetailsFlag = false;
			this.showStartAppealFlag = true;
			this.appealType = '';
			this.actualPayAmount = '';
			this.initFileUploadWidget();
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
			$('.sreenshot').fileinput('destroy').fileinput({
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

		merchantStartAppeal : function() {
			var that = this;
			if (that.appealType == null || that.appealType == '') {
				layer.alert('请选择申诉类型', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (that.appealType == '2') {
				if (that.actualPayAmount == null || that.actualPayAmount == '') {
					layer.alert('请输入实际支付金额', {
						title : '提示',
						icon : 7,
						time : 3000
					});
					return;
				}
				if (that.selectedOrderDetails.gatheringAmount < that.actualPayAmount) {
					layer.alert('实际支付金额须小于收款金额', {
						title : '提示',
						icon : 7,
						time : 3000
					});
					return;
				}
			}
			if (that.appealType == '2' || that.appealType == '3') {
				var filesCount = $('.sreenshot').fileinput('getFilesCount');
				if (filesCount == 0) {
					layer.alert('请上传截图', {
						title : '提示',
						icon : 7,
						time : 3000
					});
					return;
				}
				$('.sreenshot').fileinput('upload');
			} else {
				that.merchantSreenshotIds = '';
				that.merchantStartAppealInner();
			}
		},

		merchantStartAppealInner : function() {
			var that = this;
			that.$http.post('/appeal/merchantStartAppeal', {
				appealType : that.appealType,
				actualPayAmount : that.actualPayAmount,
				merchantSreenshotIds : that.merchantSreenshotIds,
				merchantOrderId : that.selectedOrderDetails.id
			}, {
				emulateJSON : true
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.showMerchantRecordPage();
			});
		}
	}
});
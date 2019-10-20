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

		auditPlatformOrderFlag : false,
		auditPlatformOrder : '',
		auditNote : '',
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		this.loadGatheringChannelDictItem();
		this.loadPlatformOrderStateDictItem();
		this.initTable();
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

		initTable : function() {
			var that = this;
			$('.platform-order-table').bootstrapTable({
				classes : 'table table-hover',
				height : 490,
				url : '/platformOrder/findPlatformOrderByPage',
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
					field : 'platformName',
					title : '商户'
				}, {
					field : 'orderStateName',
					title : '订单状态'
				}, {
					title : '收款渠道/收款金额/奖励金',
					formatter : function(value, row, index, field) {
						var text = row.gatheringChannelName + '/' + row.gatheringAmount + '元';
						if (row.bounty != null) {
							text += '/' + row.bounty + '元';
						}
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

		cancelOrder : function(id) {
			var that = this;
			layer.confirm('确定要取消订单吗?', {
				icon : 7,
				title : '提示'
			}, function(index) {
				layer.close(index);
				that.$http.get('/platformOrder/cancelOrder', {
					params : {
						id : id
					}
				}).then(function(res) {
					layer.alert('操作成功!', {
						icon : 1,
						time : 3000,
						shade : false
					});
					that.refreshTable();
				});
			});
		},

		resendNotice : function(id) {
			var that = this;
			layer.load(0, {
				time : 8000,
				shade : [ 0.1, '#fff' ]
			});
			that.$http.get('/platformOrder/resendNotice', {
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

		showAuditOrderModal : function(platformOrder) {
			this.auditPlatformOrderFlag = true;
			this.auditPlatformOrder = platformOrder;
			this.auditNote = '';
		},

		audit : function(action) {
			var that = this;
			var url = '/platformOrder/customerServiceConfirmToPaid';
			if (action == 2) {
				url = '/platformOrder/unpaidCancelOrder';
			}
			that.$http.get(url, {
				params : {
					id : that.auditPlatformOrder.id,
					note : that.auditNote
				}
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				this.auditPlatformOrderFlag = false;
				that.refreshTable();
			});
		}

	}
});
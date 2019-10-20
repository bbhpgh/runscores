var rechargeOrderVM = new Vue({
	el : '#recharge-order',
	data : {
		orderNo : '',
		payChannelId : '',
		payChannels : [],
		orderState : '',
		rechargeOrderStateDictItems : [],
		submitStartTime : dayjs().format('YYYY-MM-DD'),
		submitEndTime : dayjs().format('YYYY-MM-DD'),
		approvalFlag : false,
		selectedOrder : {},
		actualPayAmount : '',
		approvalResult : '',
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		this.loadPayChannel();
		this.loadRechargeOrderStateDictItem();
		this.initTable();
	},
	methods : {

		loadPayChannel : function() {
			var that = this;
			that.$http.get('/recharge/findAllPayChannel').then(function(res) {
				this.payChannels = res.body.data;
			});
		},

		/**
		 * 加载充值订单状态字典项
		 */
		loadRechargeOrderStateDictItem : function() {
			var that = this;
			that.$http.get('/dictconfig/findDictItemInCache', {
				params : {
					dictTypeCode : 'rechargeOrderState'
				}
			}).then(function(res) {
				this.rechargeOrderStateDictItems = res.body.data;
			});
		},

		initTable : function() {
			var that = this;
			$('.recharge-order-table').bootstrapTable({
				classes : 'table table-hover',
				height : 490,
				url : '/recharge/findRechargeOrderByPage',
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
						payChannelId : that.payChannelId,
						orderState : that.orderState,
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
					var html = template('recharge-order-detail', {
						rechargeOrderInfo : row
					});
					return html;
				},
				columns : [ {
					field : 'orderNo',
					title : '订单号'
				}, {
					field : 'userName',
					title : '充值用户'
				}, {
					field : 'orderStateName',
					title : '订单状态'
				}, {
					title : '支付通道/充值金额/实际支付',
					formatter : function(value, row, index, field) {
						var text = row.payChannelName + '/' + row.rechargeAmount;
						if (row.actualPayAmount != null) {
							text += '/' + row.actualPayAmount;
						}
						return text;
					}
				}, {
					field : 'submitTime',
					title : '提交时间'
				}, {
					field : 'payTime',
					title : '支付时间'
				}, {
					field : 'settlementTime',
					title : '结算时间'
				}, {
					title : '操作',
					formatter : function(value, row, index) {
						if (row.orderState == '1') {
							if (row.depositTime != null) {
								return [ '<button type="button" class="approval-order-btn btn btn-outline-info btn-sm">审核</button>' ].join('');
							} else {
								return [ '<button type="button" class="cancel-order-btn btn btn-outline-danger btn-sm">取消订单</button>' ].join('');
							}
						}
						if (row.orderState == '2') {
							return [ '<button type="button" class="manual-settlement-btn btn btn-outline-success btn-sm">手动结算</button>' ].join('');
						}
					},
					events : {
						'click .approval-order-btn' : function(event, value, row, index) {
							that.showApprovalOrderModal(row.id);
						},
						'click .cancel-order-btn' : function(event, value, row, index) {
							that.cancelOrder(row.id);
						},
						'click .manual-settlement-btn' : function(event, value, row, index) {
							that.manualSettlement(row.orderNo);
						}
					}
				} ]
			});
		},

		refreshTable : function() {
			$('.recharge-order-table').bootstrapTable('refreshOptions', {
				pageNumber : 1
			});
		},

		showApprovalOrderModal : function(id) {
			var that = this;
			that.$http.get('/recharge/findRechargeOrderById', {
				params : {
					id : id
				}
			}).then(function(res) {
				that.selectedOrder = res.body.data;
				that.actualPayAmount = that.selectedOrder.rechargeAmount;
				that.approvalResult = '2';
				that.approvalFlag = true;
			});
		},

		approval : function() {
			var that = this;
			if (that.approvalResult == null || that.approvalResult == '') {
				layer.alert('请选择审核结果', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (that.approvalResult == '2') {
				if (that.actualPayAmount == null || that.actualPayAmount == '') {
					layer.alert('请输入实际存款金额', {
						title : '提示',
						icon : 7,
						time : 3000
					});
					return;
				}
			}
			that.$http.post('/recharge/approval', {
				id : that.selectedOrder.id,
				actualPayAmount : that.actualPayAmount,
				approvalResult : that.approvalResult
			}, {
				emulateJSON : true
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.approvalFlag = false;
				that.refreshTable();
			});
		},

		cancelOrder : function(id) {
			var that = this;
			layer.confirm('确定要取消订单吗?', {
				icon : 7,
				title : '提示'
			}, function(index) {
				layer.close(index);
				that.$http.get('/recharge/cancelOrder', {
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

		manualSettlement : function(orderNo) {
			var that = this;
			layer.confirm('确定要结算吗?', {
				icon : 7,
				title : '提示'
			}, function(index) {
				layer.close(index);
				that.$http.get('/recharge/manualSettlement', {
					params : {
						orderNo : orderNo
					}
				}).then(function(res) {
					layer.alert('已通知系统进行结算!', {
						icon : 1,
						time : 3000,
						shade : false
					});
					that.refreshTable();
				});
			});
		}
	}
});
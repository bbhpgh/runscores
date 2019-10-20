var appealRecordVM = new Vue({
	el : '#appeal-record',
	data : {
		orderNo : '',
		merchantName : '',
		gatheringChannelCode : '',
		gatheringChannelDictItems : [],
		receiverUserName : '',
		appealType : '',
		appealTypeDictItems : [],
		appealState : '',
		appealStateDictItems : [],
		appealProcessWay : '',
		appealProcessWayDictItems : [],
		initiationStartTime : dayjs().format('YYYY-MM-DD'),
		initiationEndTime : dayjs().format('YYYY-MM-DD')
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		var appealState = getQueryString('appealState');
		if (appealState == '1') {
			this.appealState = appealState;
			this.initiationStartTime = '';
			this.initiationEndTime = '';
		}
		this.loadGatheringChannelDictItem();
		this.loadAppealTypeDictItem();
		this.loadAppealStateDictItem();
		this.loadAppealProcessWayDictItem();
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
						merchantName : that.merchantName,
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
					field : 'merchantName',
					title : '商户'
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
						if (row.state == '1') {
							return [ '<button type="button" class="deal-btn btn btn-outline-danger btn-sm">马上处理</button>' ].join('');
						} else if (row.state == '2') {
							return [ '<button type="button" class="deal-btn btn btn-outline-info btn-sm">查看详情</button>' ].join('');
						}
					},
					events : {
						'click .deal-btn' : function(event, value, row, index) {
							window.location.href = '/appeal-details?id=' + row.id;
						}
					}
				} ]
			});
		},

		refreshTable : function() {
			$('.appeal-record-table').bootstrapTable('refreshOptions', {
				pageNumber : 1
			});
		}
	}
});
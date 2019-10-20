var accountChangeLogVM = new Vue({
	el : '#account-change-log',
	data : {
		accountChangeTypeCode : '',
		accountChangeTypeDictItems : [],
		userName : '',
		accountChangeStartTime : dayjs().format('YYYY-MM-DD'),
		accountChangeEndTime : dayjs().format('YYYY-MM-DD')
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		this.loadAccountChangeTypeDictItem();
		this.initTable();
	},
	methods : {
		/**
		 * 加载账变类型字典项
		 */
		loadAccountChangeTypeDictItem : function() {
			var that = this;
			that.$http.get('/dictconfig/findDictItemInCache', {
				params : {
					dictTypeCode : 'accountChangeType'
				}
			}).then(function(res) {
				this.accountChangeTypeDictItems = res.body.data;
			});
		},

		initTable : function() {
			var that = this;
			$('.account-change-log-table').bootstrapTable({
				classes : 'table table-hover',
				height : 490,
				url : '/userAccount/findAccountChangeLogByPage',
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
						accountChangeTypeCode : that.accountChangeTypeCode,
						userName : that.userName,
						startTime : that.accountChangeStartTime,
						endTime : that.accountChangeEndTime
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
					field : 'userName',
					title : '用户名'
				}, {
					field : 'orderNo',
					title : '订单号'
				}, {
					field : 'accountChangeTypeName',
					title : '帐变类型'
				}, {
					field : 'accountChangeTime',
					title : '账变时间'
				}, {
					field : 'note',
					title : '备注'
				}, {
					field : 'accountChangeAmount',
					title : '账变金额'
				}, {
					field : 'cashDeposit',
					title : '保证金'
				} ]
			});
		},
		refreshTable : function() {
			$('.account-change-log-table').bootstrapTable('refreshOptions', {
				pageNumber : 1
			});
		}
	}
});
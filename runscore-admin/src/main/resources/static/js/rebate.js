var rebateVM = new Vue({
	el : '#rebate',
	data : {
		quickSettingFlag : false,
		lowestRebate : null,
		highestRebate : null,
		rebateStep : null,
		rebates : [],

		editRebate : {},
		addOrUpdateRebateFlag : false,
		rebateActionTitle : '',
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		this.initTable();
	},
	methods : {

		initTable : function() {
			var that = this;
			$('.rebate-table').bootstrapTable({
				classes : 'table table-hover',
				height : 490,
				url : '/agent/findRebateSituationByPage',
				pagination : true,
				sidePagination : 'server',
				pageNumber : 1,
				pageSize : 50,
				pageList : [ 10, 25, 50, 100 ],
				queryParamsType : '',
				queryParams : function(params) {
					var condParam = {
						pageSize : params.pageSize,
						pageNum : params.pageNumber,
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
					title : '返点',
					formatter : function(value, row, index) {
						return row.rebate + '%';
					}
				}, {
					title : '状态',
					formatter : function(value, row, index) {
						if (row.createTime != null) {
							return '正常';
						}
						return '旧的返点';
					},
					cellStyle : function(value, row, index) {
						if (row.createTime == null) {
							return {
								classes : 'rebate-state'
							};
						}
						return {};
					}
				}, {
					field : 'associatedAccountNum',
					title : '分配账号数量',
					formatter : function(value) {
						if (value > 0) {
							return value;
						}
						return '未分配';
					}
				}, {
					field : 'createTime',
					title : '创建时间'
				}, {
					title : '操作',
					formatter : function(value, row, index) {
						if (row.createTime == null) {
							return;
						}
						return [ '<button type="button" class="edit-rebate-btn btn btn-outline-primary btn-sm" style="margin-right: 4px;">编辑</button>', '<button type="button" class="del-rebate-btn btn btn-outline-danger btn-sm">删除</button>' ].join('');
					},
					events : {
						'click .edit-rebate-btn' : function(event, value, row, index) {
							that.showEditRebateModal(row);
						},
						'click .del-rebate-btn' : function(event, value, row, index) {
							that.delRebate(row);
						}
					}
				} ]
			});
		},
		refreshTable : function() {
			$('.rebate-table').bootstrapTable('refreshOptions', {
				pageNumber : 1
			});
		},

		showAddRebateModal : function() {
			this.editRebate = {
				rebate : null
			};
			this.addOrUpdateRebateFlag = true;
			this.rebateActionTitle = '新增返点';
		},

		showEditRebateModal : function(rebate) {
			var that = this;
			that.$http.get('/agent/findRebate', {
				params : {
					rebate : rebate.rebate
				}
			}).then(function(res) {
				that.editRebate = res.body.data;
				that.addOrUpdateRebateFlag = true;
				that.rebateActionTitle = '编辑返点';
			});
		},

		addOrUpdateRebate : function() {
			var that = this;
			var editRebate = that.editRebate;
			if (editRebate.rebate == null || editRebate.rebate === '') {
				layer.alert('请输入返点', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			that.$http.post('/agent/addOrUpdateRebate', editRebate).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.addOrUpdateRebateFlag = false;
				that.refreshTable();
			});
		},

		delRebate : function(rebate) {
			var that = this;
			layer.confirm('确定要删除吗?', {
				icon : 7,
				title : '提示'
			}, function(index) {
				layer.close(index);
				that.$http.get('/agent/delRebate', {
					params : {
						rebate : rebate.rebate
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

		showQuickSettingModal : function() {
			this.quickSettingFlag = true;
			this.lowestRebate = null;
			this.highestRebate = null;
			this.rebateStep = null;
			this.rebates = [];
		},

		addRecord : function() {
			this.rebates.push({
				rebate : null
			});
		},

		generateRecord : function() {
			if (this.lowestRebate == null || this.lowestRebate === '') {
				layer.alert('请输入最低返点', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (this.highestRebate == null || this.highestRebate === '') {
				layer.alert('请输入最高返点', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (this.rebateStep == null || this.rebateStep === '') {
				layer.alert('请输入返点步长', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (this.lowestRebate >= this.highestRebate) {
				layer.alert('最低返点必须要小于最高返点', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			var rebates = [];
			for (var i = this.lowestRebate; i <= this.highestRebate; i = numberFormat(i + this.rebateStep)) {
				rebates.push({
					rebate : numberFormat(i)
				});
			}
			if (rebates.length == 0) {
				layer.alert('返点生成的记录长度为0,请重新调整', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			this.rebates = rebates;
		},

		saveRebate : function() {
			var that = this;
			if (this.rebates.length == 0) {
				layer.alert('请增加返点记录', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			var map = new Map();
			for (var i = 0; i < this.rebates.length; i++) {
				var rebate = this.rebates[i];
				if (rebate.rebate == null || rebate.rebate === '') {
					layer.alert('请输入返点', {
						title : '提示',
						icon : 7,
						time : 3000
					});
					return;
				}
				var key = rebate.rebate;
				if (map.get(key)) {
					layer.alert('不能设置重复的返点', {
						title : '提示',
						icon : 7,
						time : 3000
					});
					return;
				}
				map.set(key, key);
			}
			that.$http.post('/agent/resetRebate', this.rebates).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.quickSettingFlag = false;
				that.refreshTable();
			});
		},
	}
});
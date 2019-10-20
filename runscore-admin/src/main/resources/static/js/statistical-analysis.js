var totalBountyRankChart = null;
var todayBountyRankChart = null;
var statisticalAnalysisVM = new Vue({
	el : '#statistical-analysis',
	data : {
		totalCashDeposit : '',
		totalStatistical : {},
		monthStatistical : {},
		todayStatistical : {}
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		var that = this;
		that.loadTotalCashDeposit();
		/*setInterval(function() {
			that.loadTotalCashDeposit();
		}, 1000 * 60 * 5);*/

		that.loadMonthStatistical();
		/*setInterval(function() {
			that.loadMonthStatistical();
		}, 1000 * 60 * 5);*/

		that.loadTodayStatistical();
		/*setInterval(function() {
			that.loadTodayStatistical();
		}, 1000 * 60 * 5);*/

		that.loadTotalStatistical();
		/*setInterval(function() {
			that.loadTotalStatistical();
		}, 1000 * 60 * 5);*/

		that.initTotalBountyRankChart();
		that.loadTotalTop10BountyRankData();
		/*setInterval(function() {
			that.loadTotalTop10BountyRankData();
		}, 1000 * 60 * 5);*/

		that.initTodayBountyRankChart();
		that.loadTodayTop10BountyRankData();
		/*setInterval(function() {
			that.loadTodayTop10BountyRankData();
		}, 1000 * 60 * 5);*/
	},
	methods : {

		loadTotalCashDeposit : function() {
			var that = this;
			that.$http.get('/statisticalAnalysis/findTotalCashDeposit').then(function(res) {
				that.totalCashDeposit = res.body.data;
			});
		},

		loadTotalStatistical : function() {
			var that = this;
			that.$http.get('/statisticalAnalysis/findTotalStatistical').then(function(res) {
				that.totalStatistical = res.body.data;
			});
		},

		loadMonthStatistical : function() {
			var that = this;
			that.$http.get('/statisticalAnalysis/findMonthStatistical').then(function(res) {
				that.monthStatistical = res.body.data;
			});
		},

		loadTodayStatistical : function() {
			var that = this;
			that.$http.get('/statisticalAnalysis/findTodayStatistical').then(function(res) {
				that.todayStatistical = res.body.data;
			});
		},

		initTotalBountyRankChart : function() {
			var that = this;
			option = {
				title : {
					text : '累计奖励金排行榜前十'
				},
				color : [ '#3398DB' ],
				tooltip : {
					trigger : 'axis',
					axisPointer : {
						type : 'shadow'
					}
				},
				grid : {
					left : '3%',
					right : '4%',
					bottom : '3%',
					containLabel : true
				},
				xAxis : {
					type : 'category',
					data : [],
					axisTick : {
						alignWithLabel : true
					}
				},
				yAxis : {
					type : 'value'
				},
				series : [ {
					name : '奖励金',
					type : 'bar',
					barWidth : '60%',
					data : []
				} ]
			};
			totalBountyRankChart = echarts.init(document.getElementById('total-bounty-rank-chart'));
			totalBountyRankChart.setOption(option);
		},

		initTodayBountyRankChart : function() {
			var that = this;
			option = {
				title : {
					text : '今日奖励金排行榜前十'
				},
				color : [ '#3398DB' ],
				tooltip : {
					trigger : 'axis',
					axisPointer : {
						type : 'shadow'
					}
				},
				grid : {
					left : '3%',
					right : '4%',
					bottom : '3%',
					containLabel : true
				},
				xAxis : {
					type : 'category',
					data : [],
					axisTick : {
						alignWithLabel : true
					}
				},
				yAxis : {
					type : 'value'
				},
				series : [ {
					name : '奖励金',
					type : 'bar',
					barWidth : '60%',
					data : []
				} ]
			};
			todayBountyRankChart = echarts.init(document.getElementById('today-bounty-rank-chart'));
			todayBountyRankChart.setOption(option);
		},

		loadTotalTop10BountyRankData : function() {
			var that = this;
			that.$http.get('/statisticalAnalysis/findTotalTop10BountyRank').then(function(res) {
				var xAxisDatas = [];
				var seriesDatas = [];
				var top10BountyRanks = res.body.data;
				for (var i = 0; i < top10BountyRanks.length; i++) {
					xAxisDatas.push(top10BountyRanks[i].userName);
					seriesDatas.push(top10BountyRanks[i].bounty);
				}
				totalBountyRankChart.setOption({
					xAxis : {
						data : xAxisDatas
					},
					series : [ {
						data : seriesDatas
					} ]
				});
			});
		},

		loadTodayTop10BountyRankData : function() {
			var that = this;
			that.$http.get('/statisticalAnalysis/findTodayTop10BountyRank').then(function(res) {
				var xAxisDatas = [];
				var seriesDatas = [];
				var top10BountyRanks = res.body.data;
				for (var i = 0; i < top10BountyRanks.length; i++) {
					xAxisDatas.push(top10BountyRanks[i].userName);
					seriesDatas.push(top10BountyRanks[i].bounty);
				}
				todayBountyRankChart.setOption({
					xAxis : {
						data : xAxisDatas
					},
					series : [ {
						data : seriesDatas
					} ]
				});
			});
		}
	}
});
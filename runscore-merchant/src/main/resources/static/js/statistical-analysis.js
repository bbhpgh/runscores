var totalBountyRankChart = null;
var todayBountyRankChart = null;
var statisticalAnalysisVM = new Vue({
	el : '#statistical-analysis',
	data : {
		totalStatistical : {},
		monthStatistical : {},
		todayStatistical : {},
		everydayStatisticals : []
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		var that = this;
		that.loadTotalStatistical();
		that.loadMonthStatistical();
		that.loadTodayStatistical();
		that.loadEveryStatisticalDataAndInitChart();
	},
	methods : {

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

		loadEveryStatisticalDataAndInitChart : function() {
			var that = this;
			that.$http.get('/statisticalAnalysis/findEverydayStatistical', {
				params : {
					startTime : dayjs().startOf('month').format('YYYY-MM-DD'),
					endTime : dayjs().endOf('month').format('YYYY-MM-DD')
				}
			}).then(function(res) {
				that.everydayStatisticals = res.body.data;
				that.initTradeAmountTrendChart();
				that.initOrderQuantityContrastChart();
			});
		},

		initTradeAmountTrendChart : function() {
			var that = this;
			option = {
				title : {
					text : '交易金额走势(本月)'
				},
				color : [ '#3398DB' ],
				tooltip : {
					trigger : 'axis',
					axisPointer : {
						type : 'shadow'
					}
				},
				xAxis : {
					type : 'category',
					data : []
				},
				yAxis : {
					type : 'value'
				},
				series : [ {
					name : '交易金额',
					type : 'line',
					data : []
				} ]
			};
			totalBountyRankChart = echarts.init(document.getElementById('total-bounty-rank-chart'));
			totalBountyRankChart.setOption(option);

			var xAxisDatas = [];
			var seriesDatas = [];
			for (var i = 0; i < that.everydayStatisticals.length; i++) {
				xAxisDatas.push(dayjs(that.everydayStatisticals[i].date).format('MM月DD日'));
				seriesDatas.push(that.everydayStatisticals[i].tradeAmount);
			}
			totalBountyRankChart.setOption({
				xAxis : {
					data : xAxisDatas
				},
				series : [ {
					data : seriesDatas
				} ]
			});
		},

		initOrderQuantityContrastChart : function() {
			var that = this;
			option = {
				title : {
					text : '已支付订单量/订单量走势(本月)'
				},
				legend : {
					x : 'right',
					data : [ '已支付', '订单量' ]
				},
				color : [ '#07a2a4', '#3398DB' ],
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
				series : []
			};
			todayBountyRankChart = echarts.init(document.getElementById('today-bounty-rank-chart'));
			todayBountyRankChart.setOption(option);

			var xAxisDatas = [];
			var paidOrderQuantity = [];
			var orderQuantity = [];
			for (var i = 0; i < that.everydayStatisticals.length; i++) {
				xAxisDatas.push(dayjs(that.everydayStatisticals[i].date).format('MM月DD日'));
				paidOrderQuantity.push(that.everydayStatisticals[i].paidOrderNum);
				orderQuantity.push(that.everydayStatisticals[i].orderNum);
			}
			todayBountyRankChart.setOption({
				xAxis : {
					data : xAxisDatas
				},
				series : [ {
					name : '已支付',
					type : 'line',
					stack : '汇总',
					data : paidOrderQuantity
				}, {
					name : '订单量',
					type : 'line',
					stack : '汇总',
					data : orderQuantity
				} ]
			});
		}

	}
});
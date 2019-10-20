var rechargeVM = new Vue({
	el : '#recharge',
	data : {
		quickInputAmount : '',
		rechargeExplain : '',
		payTypes : [],
		selectedPayType : {},
		payChannels : [],
		selectedPayChannel : '',
		depositDate : dayjs().format('YYYY-MM-DD'),
		depositTime : dayjs().format('HH:mm'),
		depositor : '',
		rechargeAmount : ''
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		headerVM.title = '充值';
		headerVM.showBackFlag = true;
		var clipboard = new ClipboardJS('.copy-btn');
		clipboard.on('success', function(e) {
			layer.alert('复制成功!', {
				icon : 1,
				time : 3000,
				shade : false
			});
		});
		this.loadRechargeSetting();
		this.loadPayType();
		this.loadPayChannel();

	},
	methods : {

		loadRechargeSetting : function() {
			var that = this;
			that.$http.get('/masterControl/getRechargeSetting').then(function(res) {
				that.quickInputAmount = res.body.data.quickInputAmount;
				that.rechargeExplain = res.body.data.rechargeExplain;
			});
		},

		loadPayType : function() {
			var that = this;
			that.$http.get('/recharge/findEnabledPayType').then(function(res) {
				that.payTypes = res.body.data;
				if (that.payTypes.length > 0) {
					that.selectedPayType = that.payTypes[0];
				} else {
					layer.alert('暂没有可用的充值通道', {
						title : '提示',
						icon : 7,
						time : 3000
					});
				}
			});
		},

		loadPayChannel : function() {
			var that = this;
			that.$http.get('/recharge/findEnabledPayChannel').then(function(res) {
				that.payChannels = res.body.data;
			});
		},

		switchPayType : function(payType) {
			this.selectedPayType = payType;
			this.selectedPayChannel = '';
		},

		confirmSubmit : function() {
			var that = this;
			if (that.selectedPayChannel == null || that.selectedPayChannel == '') {
				layer.alert(that.selectedPayType.bankCardFlag ? '请选择银行' : '请选择支付通道');
				return;
			}
			if (that.selectedPayType.bankCardFlag) {
				if (that.depositDate == null || that.depositDate == '') {
					layer.alert('请输入存款日期');
					return;
				}
				if (that.depositTime == null || that.depositTime == '') {
					layer.alert('请输入存款时间');
					return;
				}
				if (that.depositor == null || that.depositor == '') {
					layer.alert('请输入存款人姓名');
					return;
				}
			}
			if (that.rechargeAmount == null || that.rechargeAmount == '') {
				layer.alert('请输入充值金额');
				return;
			}
			layer.open({
				title : '提示',
				icon : 7,
				closeBtn : 0,
				btn : [],
				content : '正在创建充值订单...',
				time : 2000
			});
			that.$http.post('/recharge/generateRechargeOrder', {
				payChannelId : that.selectedPayChannel.id,
				depositTime : that.depositDate + ' ' + that.depositTime,
				depositor : that.depositor,
				rechargeAmount : that.rechargeAmount
			}, {
				emulateJSON : true
			}).then(function(res) {
				if (that.selectedPayType.bankCardFlag) {
					layer.open({
						title : '提示',
						icon : '1',
						closeBtn : 0,
						btn : [],
						content : '提交成功,预计30分钟到帐!',
						time : 1500,
						end : function() {
							window.location.href = '/my-home-page';
						}
					});
				} else {
					layer.open({
						title : '提示',
						icon : '1',
						closeBtn : 0,
						btn : [],
						content : '充值订单创建成功,正在跳转到支付页面!',
						time : 2000,
						end : function() {
							window.location.href = res.body.data.payUrl;
						}
					});
				}
			});
		}
	}
});
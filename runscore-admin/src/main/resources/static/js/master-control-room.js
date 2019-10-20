var masterControlRoomVM = new Vue({
	el : '#master-control-room',
	data : {
		/**
		 * 系统设置start
		 */
		websiteTitle : '',

		/**
		 * 注册设置start
		 */
		registerEnabled : '',
		regitserDefaultRebate : '',
		inviteCodeEffectiveDuration : '',
		inviteRegisterEnabled : false,

		/**
		 * 商户订单start
		 */
		receiveOrderEffectiveDuration : '',
		orderPayEffectiveDuration : '',
		receiveOrderUpperLimit : '',
		cashDepositMinimumRequire : '',
		unfixedGatheringCodeReceiveOrder : false,
		/**
		 * 充值start
		 */
		rechargeOrderEffectiveDuration : '',
		rechargeReturnWaterRate : '',
		rechargeReturnWaterRateEnabled : false,
		quickInputAmount : '',
		rechargeExplain : '',

		/**
		 * 客服二维码start
		 */
		qrcodeStorageIds : [],
		tmpQrcodeStorageIds : '',
		uploadQrcodeFlag : false,

		/**
		 * 刷新缓存start
		 */
		refreshConfigItem : true,
		refreshDict : true
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		var that = this;
		that.loadSystemSetting();
		that.loadRegisterSetting();
		that.loadReceiveOrderSetting();
		that.loadRechargeSetting();
		that.loadCustomerQrcodeSetting();

		$('.qrcode-pic').on('filebatchuploadsuccess', function(event, data) {
			that.tmpQrcodeStorageIds = data.response.data.join(',');
			that.updateCustomerQrcodeSetting();
		});
	},
	methods : {

		loadSystemSetting : function() {
			var that = this;
			that.$http.get('/masterControl/getSystemSetting').then(function(res) {
				if (res.body.data != null) {
					that.websiteTitle = res.body.data.websiteTitle;
				}
			});
		},

		updateSystemSetting : function() {
			var that = this;
			var websiteTitle = that.websiteTitle;
			if (websiteTitle == null || websiteTitle == '') {
				layer.alert('请输入网站标题', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			that.$http.post('/masterControl/updateSystemSetting', {
				websiteTitle : websiteTitle
			}, {
				emulateJSON : true
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.loadSystemSetting();
			});
		},

		loadRegisterSetting : function() {
			var that = this;
			that.$http.get('/masterControl/getRegisterSetting').then(function(res) {
				if (res.body.data != null) {
					that.registerEnabled = res.body.data.registerEnabled;
					that.regitserDefaultRebate = res.body.data.regitserDefaultRebate;
					that.inviteCodeEffectiveDuration = res.body.data.inviteCodeEffectiveDuration;
					that.inviteRegisterEnabled = res.body.data.inviteRegisterEnabled;
				}
			});
		},

		updateRegisterSetting : function() {
			var that = this;
			var registerEnabled = that.registerEnabled;
			if (registerEnabled == null) {
				layer.alert('请设置是否开放注册功能', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			var regitserDefaultRebate = that.regitserDefaultRebate;
			if (regitserDefaultRebate == null || regitserDefaultRebate == '') {
				layer.alert('请输入注册默认返点', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			var inviteCodeEffectiveDuration = that.inviteCodeEffectiveDuration;
			if (inviteCodeEffectiveDuration == null || inviteCodeEffectiveDuration == '') {
				layer.alert('请输入邀请码有效时长', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			var inviteRegisterEnabled = that.inviteRegisterEnabled;
			if (inviteRegisterEnabled == null) {
				layer.alert('请选择是否启用邀请码注册模式', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			that.$http.post('/masterControl/updateRegisterSetting', {
				registerEnabled : registerEnabled,
				regitserDefaultRebate : regitserDefaultRebate,
				inviteCodeEffectiveDuration : inviteCodeEffectiveDuration,
				inviteRegisterEnabled : inviteRegisterEnabled,
			}, {
				emulateJSON : true
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.loadRegisterSetting();
			});
		},

		loadReceiveOrderSetting : function() {
			var that = this;
			that.$http.get('/masterControl/getReceiveOrderSetting').then(function(res) {
				if (res.body.data != null) {
					that.receiveOrderEffectiveDuration = res.body.data.receiveOrderEffectiveDuration;
					that.orderPayEffectiveDuration = res.body.data.orderPayEffectiveDuration;
					that.receiveOrderUpperLimit = res.body.data.receiveOrderUpperLimit;
					that.cashDepositMinimumRequire = res.body.data.cashDepositMinimumRequire;
					that.unfixedGatheringCodeReceiveOrder = res.body.data.unfixedGatheringCodeReceiveOrder;
				}
			});
		},

		updateReceiveOrderSetting : function() {
			var that = this;
			var receiveOrderEffectiveDuration = that.receiveOrderEffectiveDuration;
			if (receiveOrderEffectiveDuration == null || receiveOrderEffectiveDuration == '') {
				layer.alert('请输入接单有效时长', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			var orderPayEffectiveDuration = that.orderPayEffectiveDuration;
			if (orderPayEffectiveDuration == null || orderPayEffectiveDuration == '') {
				layer.alert('请输入支付有效时长', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			var receiveOrderUpperLimit = that.receiveOrderUpperLimit;
			if (receiveOrderUpperLimit == null || receiveOrderUpperLimit == '') {
				layer.alert('请输入接单数量上限', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			var cashDepositMinimumRequire = that.cashDepositMinimumRequire;
			if (cashDepositMinimumRequire == null || cashDepositMinimumRequire == '') {
				layer.alert('请输入保证金最低要求金额', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			var unfixedGatheringCodeReceiveOrder = that.unfixedGatheringCodeReceiveOrder;
			if (unfixedGatheringCodeReceiveOrder == null) {
				layer.alert('请选择是否支持不固定金额收款码接单', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}

			that.$http.post('/masterControl/updateReceiveOrderSetting', {
				receiveOrderEffectiveDuration : receiveOrderEffectiveDuration,
				orderPayEffectiveDuration : orderPayEffectiveDuration,
				receiveOrderUpperLimit : receiveOrderUpperLimit,
				cashDepositMinimumRequire : cashDepositMinimumRequire,
				unfixedGatheringCodeReceiveOrder : unfixedGatheringCodeReceiveOrder
			}, {
				emulateJSON : true
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.loadReceiveOrderSetting();
			});
		},

		loadRechargeSetting : function() {
			var that = this;
			that.$http.get('/masterControl/getRechargeSetting').then(function(res) {
				if (res.body.data != null) {
					that.rechargeOrderEffectiveDuration = res.body.data.orderEffectiveDuration;
					that.rechargeReturnWaterRate = res.body.data.returnWaterRate;
					that.rechargeReturnWaterRateEnabled = res.body.data.returnWaterRateEnabled;
					that.quickInputAmount = res.body.data.quickInputAmount;
					that.rechargeExplain = res.body.data.rechargeExplain;
				}
			});
		},

		updateRechargeSetting : function() {
			var that = this;
			var rechargeOrderEffectiveDuration = that.rechargeOrderEffectiveDuration;
			if (rechargeOrderEffectiveDuration == null || rechargeOrderEffectiveDuration == '') {
				layer.alert('请输入充值订单有效时长', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			var rechargeReturnWaterRate = that.rechargeReturnWaterRate;
			if (rechargeReturnWaterRate == null || rechargeReturnWaterRate == '') {
				layer.alert('请输入充值返水率', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			var rechargeReturnWaterRateEnabled = that.rechargeReturnWaterRateEnabled;
			if (rechargeReturnWaterRateEnabled == null) {
				layer.alert('请选择是否启用', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			var quickInputAmount = that.quickInputAmount;
			if (quickInputAmount == null || quickInputAmount == '') {
				layer.alert('请输入快速设置金额', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			var rechargeExplain = that.rechargeExplain;
			if (rechargeExplain == null || rechargeExplain == '') {
				layer.alert('请输入充值说明', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}

			that.$http.post('/masterControl/updateRechargeSetting', {
				orderEffectiveDuration : rechargeOrderEffectiveDuration,
				returnWaterRate : rechargeReturnWaterRate,
				returnWaterRateEnabled : rechargeReturnWaterRateEnabled,
				quickInputAmount : quickInputAmount,
				rechargeExplain : rechargeExplain
			}, {
				emulateJSON : true
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.loadRechargeSetting();
			});
		},

		loadCustomerQrcodeSetting : function() {
			var that = this;
			that.$http.get('/masterControl/getCustomerQrcodeSetting').then(function(res) {
				if (res.body.data != null) {
					that.qrcodeStorageIds = res.body.data.qrcodeStorageIds.split(',');
				} else {
					that.qrcodeStorageIds = [];
				}
			});
		},

		showUploadQrcodeModal : function() {
			this.uploadQrcodeFlag = true;
			$('.qrcode-pic').fileinput('destroy').fileinput({
				uploadAsync : false,
				browseOnZoneClick : true,
				showBrowse : false,
				showCaption : false,
				showClose : true,
				showRemove : false,
				showUpload : false,
				dropZoneTitle : '点击选择二维码',
				dropZoneClickTitle : '',
				layoutTemplates : {
					footer : ''
				},
				maxFileCount : 2,
				uploadUrl : '/storage/uploadPic',
				enctype : 'multipart/form-data',
				allowedFileExtensions : [ 'jpg', 'png', 'bmp', 'jpeg' ],
				initialPreview : [],
				initialPreviewAsData : true,
				initialPreviewConfig : []
			});
		},

		uploadQrcode : function() {
			var filesCount = $('.qrcode-pic').fileinput('getFilesCount');
			if (filesCount == 0) {
				layer.alert('请选择要上传的二维码', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			$('.qrcode-pic').fileinput('upload');
		},

		updateCustomerQrcodeSetting : function() {
			var that = this;
			var tmpQrcodeStorageIds = that.tmpQrcodeStorageIds;
			that.$http.post('/masterControl/updateCustomerQrcodeSetting', {
				qrcodeStorageIds : that.tmpQrcodeStorageIds
			}, {
				emulateJSON : true
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.uploadQrcodeFlag = false;
				that.loadCustomerQrcodeSetting();
			});
		},

		refreshCache : function() {
			var cacheItems = [];
			var that = this;
			if (that.refreshConfigItem) {
				cacheItems.push('config*');
			}
			if (that.refreshConfigItem) {
				cacheItems.push('dict*');
			}
			if (cacheItems.length == 0) {
				layer.alert('请勾选要刷新的缓存项', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			that.$http.post('/masterControl/refreshCache', cacheItems).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
			});
		}
	}
});
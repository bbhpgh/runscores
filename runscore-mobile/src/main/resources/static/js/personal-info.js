var personalInfoVM = new Vue({
	el : '#personal-info',
	data : {
		accountInfo : '',
		bankInfo : '',
		showPersonalInfoFlag : true,

		editBankInfoFlag : false,
		openAccountBank : '',
		accountHolder : '',
		bankCardAccount : '',

		modifyLoginPwdFlag : false,
		oldLoginPwd : '',
		newLoginPwd : '',
		confirmLoginPwd : '',

		modifyMoneyPwdFlag : false,
		oldMoneyPwd : '',
		newMoneyPwd : '',
		confirmMoneyPwd : ''
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		headerVM.title = '个人信息';
		headerVM.showBackFlag = true;
		this.loadBankInfo();
		this.getUserAccountInfo();
	},
	methods : {
		/**
		 * 获取用户账号信息
		 */
		getUserAccountInfo : function() {
			var that = this;
			that.$http.get('/userAccount/getUserAccountInfo').then(function(res) {
				if (res.body.data != null) {
					that.accountInfo = res.body.data;
				}
			});
		},

		loadBankInfo : function() {
			var that = this;
			that.$http.get('/userAccount/getBankInfo').then(function(res) {
				that.bankInfo = res.body.data;
			});
		},

		hideEditBankInfoPage : function() {
			headerVM.showBackFlag = true;
			headerVM.title = '个人信息';
			this.showPersonalInfoFlag = true;
			this.editBankInfoFlag = false;
		},

		showEditBankInfoPage : function() {
			headerVM.showBackFlag = false;
			headerVM.title = '绑定银行卡';
			this.showPersonalInfoFlag = false;
			this.editBankInfoFlag = true;
			this.openAccountBank = this.bankInfo.openAccountBank;
			this.accountHolder = this.bankInfo.accountHolder;
			this.bankCardAccount = this.bankInfo.bankCardAccount;
		},

		bindBankInfo : function() {
			var that = this;
			if (that.openAccountBank == null || that.openAccountBank == '') {
				layer.alert('请输入开户银行', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (that.accountHolder == null || that.accountHolder == '') {
				layer.alert('请输入开户人姓名', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (that.bankCardAccount == null || that.bankCardAccount == '') {
				layer.alert('请输入银行卡账号', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			that.$http.post('/userAccount/bindBankInfo', {
				openAccountBank : that.openAccountBank,
				accountHolder : that.accountHolder,
				bankCardAccount : that.bankCardAccount
			}, {
				emulateJSON : true
			}).then(function(res) {
				layer.alert('银行卡信息绑定成功!', {
					icon : 1,
					time : 2000,
					shade : false
				});
				that.loadBankInfo();
				that.hideEditBankInfoPage();
			});
		},

		hideModifyLoginPwdPage : function() {
			headerVM.showBackFlag = true;
			headerVM.title = '个人信息';
			this.showPersonalInfoFlag = true;
			this.modifyLoginPwdFlag = false;
		},

		showModifyLoginPwdPage : function() {
			headerVM.showBackFlag = false;
			headerVM.title = '修改登录密码';
			this.showPersonalInfoFlag = false;
			this.modifyLoginPwdFlag = true;
			this.oldLoginPwd = '';
			this.newLoginPwd = '';
			this.confirmLoginPwd = '';
		},

		/**
		 * 修改登录密码
		 */
		modifyLoginPwd : function() {
			var that = this;
			if (that.oldLoginPwd == null || that.oldLoginPwd == '') {
				layer.alert('请输入旧的登录密码', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (that.newLoginPwd == null || that.newLoginPwd == '') {
				layer.alert('请输入新的登录密码', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (that.confirmLoginPwd == null || that.confirmLoginPwd == '') {
				layer.alert('请输入确认登录密码', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (that.newLoginPwd != that.confirmLoginPwd) {
				layer.alert('密码不一致', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			that.$http.post('/userAccount/modifyLoginPwd', {
				oldLoginPwd : that.oldLoginPwd,
				newLoginPwd : that.newLoginPwd
			}, {
				emulateJSON : true
			}).then(function(res) {
				layer.alert('登录密码修改成功!', {
					icon : 1,
					time : 2000,
					shade : false
				});
				that.hideModifyLoginPwdPage();
			});
		},

		hideModifyMoneyPwdPage : function() {
			headerVM.showBackFlag = true;
			headerVM.title = '个人信息';
			this.showPersonalInfoFlag = true;
			this.modifyMoneyPwdFlag = false;
		},

		showModifyMoneyPwdPage : function() {
			headerVM.showBackFlag = false;
			headerVM.title = '修改资金密码';
			this.showPersonalInfoFlag = false;
			this.modifyMoneyPwdFlag = true;
			this.oldMoneyPwd = '';
			this.newMoneyPwd = '';
			this.confirmMoneyPwd = '';
		},

		/**
		 * 修改资金密码
		 */
		modifyMoneyPwd : function() {
			var that = this;
			if (that.oldMoneyPwd == null || that.oldMoneyPwd == '') {
				layer.alert('请输入旧的资金密码', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (that.newMoneyPwd == null || that.newMoneyPwd == '') {
				layer.alert('请输入新的资金密码', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (that.confirmMoneyPwd == null || that.confirmMoneyPwd == '') {
				layer.alert('请输入确认资金密码', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (that.newMoneyPwd != that.confirmMoneyPwd) {
				layer.alert('密码不一致', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			that.$http.post('/userAccount/modifyMoneyPwd', {
				oldMoneyPwd : that.oldMoneyPwd,
				newMoneyPwd : that.newMoneyPwd
			}, {
				emulateJSON : true
			}).then(function(res) {
				layer.alert('资金密码修改成功!', {
					icon : 1,
					time : 2000,
					shade : false
				});
				that.hideModifyMoneyPwdPage();
			});
		}

	}
});
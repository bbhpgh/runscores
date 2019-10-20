var appealDetailsVM = new Vue({
	el : '#appeal-details',
	data : {
		showAppealRecordFlag : true,
		selectAppeal : {}
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		var id = getQueryString('id');
		this.loadAppealDetails(id);
	},
	methods : {

		loadAppealDetails : function(id) {
			var that = this;
			that.$http.get('/appeal/findAppealDetailsById', {
				params : {
					id : id
				}
			}).then(function(res) {
				that.selectAppeal = res.body.data;
			});
		},

		dontProcess : function(appealId) {
			var that = this;
			that.$http.get('/appeal/dontProcess', {
				params : {
					appealId : appealId
				}
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				window.history.back(-1);
			});
		},

		cancelOrder : function(appealId) {
			var that = this;
			that.$http.get('/appeal/cancelOrder', {
				params : {
					appealId : appealId
				}
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				window.history.back(-1);
			});
		},

		alterToActualPayAmount : function(appealId) {
			var that = this;
			that.$http.get('/appeal/alterToActualPayAmount', {
				params : {
					appealId : appealId
				}
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				window.history.back(-1);
			});
		},

		confirmToPaid : function(appealId) {
			var that = this;
			that.$http.get('/appeal/confirmToPaid', {
				params : {
					appealId : appealId
				}
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				window.history.back(-1);
			});
		},

		viewImage : function(imagePath) {
			var image = new Image();
			image.src = imagePath;
			var viewer = new Viewer(image, {
				hidden : function() {
					viewer.destroy();
				},
			});
			viewer.show();
		}

	}
});
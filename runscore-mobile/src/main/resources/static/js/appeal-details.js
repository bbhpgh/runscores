var appealDetailsVM = new Vue({
	el : '#appeal-details',
	data : {
		showViewDetailsFlag : false,
		selectedAppealRecord : {},
		showUploadSreenshotFlag : false,
		userSreenshotIds : ''
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		headerVM.title = '申诉详情';
		var that = this;
		that.showViewDetailsPage(getQueryString('id'));
		$('.upload-sreenshot').on('filebatchuploadsuccess', function(event, data) {
			that.userSreenshotIds = data.response.data.join(',');
			that.uploadSreenshotInner();
		});
	},
	methods : {
		showViewDetailsPage : function(appealId) {
			var that = this;
			that.$http.get('/appeal/findAppealById', {
				params : {
					appealId : appealId
				}
			}).then(function(res) {
				this.selectedAppealRecord = res.body.data;
				headerVM.title = '申诉详情';
				this.showViewDetailsFlag = true;
				this.showUploadSreenshotFlag = false;
			});
		},

		userCancelAppeal : function(appealId) {
			var that = this;
			that.$http.get('/appeal/userCancelAppeal', {
				params : {
					appealId : appealId
				}
			}).then(function(res) {
				layer.alert('撤销成功', {
					icon : 1,
					time : 2000,
					shade : false
				});
				that.showViewDetailsPage(appealId);
			});
		},

		showUploadSreenshotPage : function() {
			headerVM.title = '上传截图';
			this.showViewDetailsFlag = false;
			this.showUploadSreenshotFlag = true;
			this.initFileUploadWidget();
		},

		initFileUploadWidget : function() {
			var initialPreview = [];
			var initialPreviewConfig = [];
			$('.upload-sreenshot').fileinput('destroy').fileinput({
				uploadAsync : false,
				browseOnZoneClick : true,
				showBrowse : false,
				showCaption : false,
				showClose : true,
				showRemove : false,
				showUpload : false,
				dropZoneTitle : '点击选择图片',
				dropZoneClickTitle : '',
				layoutTemplates : {
					footer : ''
				},
				maxFileCount : 2,
				uploadUrl : '/storage/uploadPic',
				enctype : 'multipart/form-data',
				allowedFileExtensions : [ 'jpg', 'png', 'bmp', 'jpeg' ],
				initialPreview : initialPreview,
				initialPreviewAsData : true,
				initialPreviewConfig : initialPreviewConfig
			});
		},

		uploadSreenshot : function() {
			var filesCount = $('.upload-sreenshot').fileinput('getFilesCount');
			if (filesCount == 0) {
				layer.alert('请上传截图', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			$('.upload-sreenshot').fileinput('upload');
		},

		uploadSreenshotInner : function() {
			var that = this;
			that.$http.get('/appeal/userUploadSreenshot', {
				params : {
					appealId : that.selectedAppealRecord.id,
					userSreenshotIds : that.userSreenshotIds
				}
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.showViewDetailsPage(that.selectedAppealRecord.id);
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
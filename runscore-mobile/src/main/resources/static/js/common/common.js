Vue.http.interceptors.push(function(request) {
	return function(response) {
		if (response.body.code != 200) {
			response.ok = false;
			layer.alert(response.body.msg, {
				title : '提示',
				icon : 7,
				time : 3000
			});
		}
	};
});

/**
 * 获取url参数
 * 
 * @param name
 * @returns
 */
function getQueryString(name) {
	var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

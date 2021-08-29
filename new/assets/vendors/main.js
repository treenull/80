
$(document).ready(function () {

	// 获取一言数据
	fetch('https://v1.hitokoto.cn').then(function (res) {
		return res.json();
	}).then(function (e) {
		$('#description').html(e.hitokoto)
		$('#dfrom').html("——「" + e.from + "」")
	}).catch(function (err) {
		console.error(err);
	})
});
	
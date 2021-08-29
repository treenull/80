
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



function post(){
    $.ajax({
        url: "../../apppubServer/checkIp",
        type: "POST",
        data: {
            id:$('#edit_ser_id').val(),
            ip: $('#edit_ser_ip').val()
        },
        success: function (data){
            if(!data.success){
                $('#judge_ip').text('服务器地址重复');
            }
        }
    });
}
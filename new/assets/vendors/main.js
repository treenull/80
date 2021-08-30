
$(document).ready(function () {
    var hitokoto = null;
    // 获取一言数据
    fetch('https://v1.hitokoto.cn').then(function (res) {
        return res.json();
    }).then(function (e) {
        hitokoto = e
        $('#description').html(e.hitokoto)
        $('#dfrom').html("——「" + e.from + "」")
    }).catch(function (err) {
        console.error(err);
    });

    fetch('http://127.0.0.1:9282/cloudopen/yiyan/checkHealth', {
        method: 'GET',
        mode: 'cors',
    }).then(function (res) {
        return res.json();
    }).then(function (e) {
        console.log(e);
        // $('#description').html(e.hitokoto)
        // $('#dfrom').html("——「" + e.from + "」")
    }).catch(function (err) {
        console.error(err);
    });

    const url = 'http://127.0.0.1:9282/cloudopen/yiyan/dataRecord';
    var data = {hitokoto};

    fetch(url, {
        method: 'POST', // or 'PUT'
        mode: 'cors',
        body: JSON.stringify(data), // data can be `string` or {object}!
        headers: new Headers({
          'Content-Type': 'application/json'
        })
      }).then(res => res.json())
      .catch(error => console.error('Error:', error))
      .then(response => console.log('Success:', response));

});



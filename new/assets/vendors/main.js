
$(document).ready(function () {
    fetch('https://v1.hitokoto.cn').then(function (res) {
        return res.json();
    }).then(function (e) {
        $('#description').html(e.hitokoto)
        $('#dfrom').html("——「" + e.from + "」")
        dataRecord(e);
    }).catch(function (err) {
        console.error(err);
    });
});

function dataRecord(data){
    const url = 'https://open.treenull.cn/cloudopen/yiyan/dataRecord';
        fetch(url, {
            method: 'POST',
            mode: 'cors',
            body: JSON.stringify(data),
            headers: new Headers({
              'Content-Type': 'application/json'
            })
          }).then(res => res.json())
          .catch(function (err){
              console.error(err)
          });
}

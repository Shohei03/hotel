'use strict';

let dialog = document.querySelector('dialog');
let btn = document.getElementById('btn');
let yes = document.getElementById('yes');
let no = document.getElementById('no');

btn.addEventListener('click', function() {
    // 開くボタンをクリックした場合の処理
    dialog.showModal();
}, false);

yes.addEventListener('click', function() {
    // はいボタンをクリックした場合の処理
    // 通常のJavascriptで実装
    //sendDataXMLHttpRequest();
    // jQueryで実装　
    sendDatajQuery();
    dialog.close();

}, false);

no.addEventListener('click', function() {
    // いいえボタンをクリックした場合の処理
    dialog.close();
}, false);

/**
 * jQueryで実装
 */
function sendDatajQuery(){
    // ファイルを取得
    let file = $('#file')[0].files[0];

    // フォームデータを取得
    let formdata = new FormData();
    formdata.append( "file", file);

    // POSTでアップロード
	$.ajax({
          url  : "/uploadFile",
          type : "POST",
          data : formdata,
          cache       : false,
          contentType : false,
          processData : false,
          dataType    : "html"
      })
      .done(function(){
          alert("成功");
      })
      .fail(function(){
          alert("失敗");
      });
}
'use strict';

var userData = null; // ホテルデータ
var table = null; // DataTablesオブジェクト

/** 画面ロード時の処理. */
jQuery(function($){
  // DataTablesの初期化

  createDataTables();

  /** 検索ボタンを押したときの処理. */
  $('#btn-search').click(function (event) {
    // 検索
    search();
  });
});

/** 検索処理. */
function search() {

  // formの値を取得
  var formData = $('#user-search-form').serialize();
  console.log("確認formData:" + formData);
  // ajax通信
  $.ajax({
    type : "GET",
    url : '/user/restList',
    data: formData,
    dataType : 'json',
    contentType: 'application/json; charset=UTF-8',
    cache : false,
    timeout : 2000,
  }).done(function(data) {
    // ajax成功時の処理
    console.log(data);
    // JSONを変数に入れる
     userData = data;
    // DataTables作成
    createDataTables();
  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax失敗時の処理
    alert('検索処理に失敗しました');

  }).always(function() {
    // 常に実行する処理(特になし)
  });
}

/** DataTables作成. */
function createDataTables() {

  //既にDataTablesが作成されている場合
  if(table !== null) {
    // DataTables破棄
    table.destroy();
  }

  // DataTables作成
  table = $('#user-list-table').DataTable({
    // 日本語化
    language: {
      url: '/Japanese.json'
    },
    "paging": true, // false to disable pagination (or any other option)
    "pagingType": "full_numbers",
    "pageLength": 5,
    //表示データ
    data: userData,
    //データと列のマッピング
    columns: [
      { data: 'id'}, // ユーザーID
      { data: 'name'}, // ユーザー名
      { data: 'nameKana'}, // ユーザー名（フリガナ）
      { data: 'mail'}, // 郵便番号
      {
        data: 'id', // 詳細画面のURL
        render: function ( data, type, row ) {
          var url = '<a href="/user/edit/' + data + '">詳細</a>';
          return url;
        }
      },
    ]
  });
}


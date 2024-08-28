'use strict';

var hotelData = null; // ホテルデータ
var table = null; // DataTablesオブジェクト

/** 画面ロード時の処理. */
jQuery(function($){
    /** 削除ボタンを押したときの処理. */
    $('#item_del').click(function (event) {
      console.log("test__item_del:");
      // 検索
      deletes();
    });


  // DataTablesの初期化
  createDataTables();

  /** 検索ボタンを押したときの処理. */
  $('#btn-search').click(function (event) {
    // 検索
    search();
  });


});


function deletes() {
  // formの値を取得
  var formData = $('#item_del').val();
  console.log("確認item_del:" + formData);
  // ajax通信
  $.ajax({
    type : "DELETE",
    url : '/hotel/restDelete',
    data: {"formData":formData},
    dataType : 'json',
    cache : false,
  }).done(function(data) {
    alert('ユーザを削除しました。')
    window.location.href = '/hotel/infoList';

  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax失敗時の処理
    alert('削除処理に失敗しました');

  }).always(function() {
    // 常に実行する処理(特になし)
  });

}


/** 検索処理. */
function search() {
console.log('test__');
  // formの値を取得
  var formData = $('#hotel_name').val();
  console.log("確認formData:" + formData);
  // ajax通信
  $.ajax({
    type : "GET",
    url : '/hotel/restList',
    data: {"formData":formData},
    dataType : 'json',
    contentType: 'application/json; charset=UTF-8',
    cache : false,
    timeout : 2000,
  }).done(function(data) {
    // ajax成功時の処理
    console.log("data___" + data);
    // JSONを変数に入れる
     hotelData = data;
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
  table = $('#hotel-list-table').DataTable({
    // 日本語化
    language: {
      url: '/Japanese.json'
    },
    "paging": true, // false to disable pagination (or any other option)
    "pagingType": "full_numbers",
    "pageLength": 5,
    //表示データ
    data: hotelData,
    //データと列のマッピング
    columns: [
      { data: 'id'}, // ユーザーID
      { data: 'name'}, // 郵便番号
      { data: 'address'}, // 郵便番号
      {
        data: {'prefecture':'prefecture','city':'city','otherParts':'otherParts'},
        render: function(data, type, row) {
        var jusyo = data.prefecture + data.city + data.otherParts;
        return jusyo}
      }, // 住所
      { data: 'phoneNumber'}, // 電話番号
      {
        data: 'id', // 詳細画面のURL
        render: function ( data, type, row ) {
          var url = '<a href="/hotel/detail/' + data + '">詳細</a>';
          return url;
        }
      },
        {
          data: 'id', // 削除
          render: function ( data, type, row ) {
            var url2 = '<a href="/hotel/delete/' + data +'">削除</a >';
            return url2;
          }
        }

    ]
  });
}


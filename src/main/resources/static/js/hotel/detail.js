'use strict';

$(function() {
    $("#checkin_date").datepicker({
        onSelect: calculateStay
    });
    $("#checkout_date").datepicker({
        onSelect: calculateStay
    });

    // ページがロードされたときにcalculateStayを呼び出す
    calculateStay();

function calculateStay() {
    const checkinDate = document.getElementById('checkin_date').value;
    const checkoutDate = document.getElementById('checkout_date').value;

    if (!checkinDate || !checkoutDate) {
        document.getElementById('result').innerHTML = '';
        document.getElementById('stayLength').value = 0;
        return;
    }

    const checkin = new Date(checkinDate);
    const checkout = new Date(checkoutDate);

    const timeDiff = checkout - checkin;
    const days = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));

    const resultDiv = document.getElementById('result');
    if (days >= 0) {
        resultDiv.innerHTML = `<p>宿泊数: ${days} 日</p>`;
         document.getElementById('stayLength').value = days;
    } else {
        resultDiv.innerHTML = '<p>チェックアウト日はチェックイン日より後の日付にしてください。</p>';
        document.getElementById('stayLength').value = 0;
    }
}
});

// 写真を横スクロール
$(document).ready(function () {
    var $nextButton = $('#next-button');
    var $prevButton = $('#prev-button');
    var $photos = $('#photos');
    var $photosWrapper = $('#photos-wrapper');
    var li_width = $('#photos li').width();
    var totalWidth = li_width * $('#photos li').length;
    var containerWidth = $photosWrapper.width();
    var li_count = $('#photos li').length; // 写真の枚数を取得


    // 写真が1枚のみの場合、ボタンを非表示にする
    if(li_count <= 1) {
        $nextButton.hide();
        $prevButton.hide();
    }

    $('#next-button').on('click', function () {
        var marginLeft = parseInt($photos.css('margin-left'), 10);
        if (Math.abs(marginLeft) >= (totalWidth - containerWidth)) {
            $photos.stop().animate({ marginLeft: '0px' });
        } else {
            $photos.stop().animate({ marginLeft: marginLeft - li_width + 'px' });
        }
    });

    $('#prev-button').on('click', function () {
        var marginLeft = parseInt($photos.css('margin-left'), 10);
        if (marginLeft >= 0) {
            $photos.stop().animate({ marginLeft: -((totalWidth - containerWidth)) + 'px' });
        } else {
            $photos.stop().animate({ marginLeft: marginLeft + li_width + 'px' });
        }
    });
});
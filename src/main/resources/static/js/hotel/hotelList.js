'use strict';

document.addEventListener('DOMContentLoaded', function() {
    const orderSelect = document.querySelector('#orderitem');
    const sortButton = document.querySelector('#select_button');

    sortButton.addEventListener('click', function() {
        const selectedOrder = orderSelect.value;
        const searchForm = document.querySelector('.search-form1 form');
        const orderKey = document.querySelector("#orderKey")
        const actionUrl = new URL(searchForm.action, window.location.href);

        orderKey.value = selectedOrder;

        // 検索フォームを送信
        searchForm.action = actionUrl.toString();

        searchForm.submit();
    });
});
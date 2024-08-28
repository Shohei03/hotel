'use strict';

function confirmCancel(event) {
    var userConfirmed = confirm("本当にキャンセルしますか？");

if(!userConfirmed) {
    event.preventDefault();
}

}

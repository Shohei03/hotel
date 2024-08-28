'use strict';

$(function(){
  $('.hamburger').off('click').on('click', function(){
    $('.hamburger, .slide-menu').toggleClass('active');
  });
});
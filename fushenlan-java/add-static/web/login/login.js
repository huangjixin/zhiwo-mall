// JavaScript Document
$(function(){

    $('#login-form li:not(active)').on('tap', function () {
        $(this).addClass('active').siblings().removeClass('active');
        $(this).data('type') === 'face' ? ($('.face-recognition').removeClass('go-left'), $('.user-password')				.removeClass('go-right')) : ($('.face-recognition').addClass('go-left'), $('.user-password').addClass			('go-right'))
    });

    $('.login-to-platform').on('tap', function(){
        return  window.location.href='../platform/#page=main';
    });

    $('.face-login').on('tap', function(){
        return  window.location.href='../platform/#page=main';
    });
});

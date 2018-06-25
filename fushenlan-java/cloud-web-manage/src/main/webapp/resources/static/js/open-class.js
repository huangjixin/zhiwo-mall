$('.open-content-tab').on('click', '.tab-item', function() {
    $(this).addClass('tab-item-active').siblings('.tab-item').removeClass('tab-item-active');
})
// 下拉框
$('body').on('change', '.ipt-select select', function(){
    console.log($(this).siblings('.ipt-text'));
    $(this).siblings('.ipt-text').val($(this).children(':checked').text());
});
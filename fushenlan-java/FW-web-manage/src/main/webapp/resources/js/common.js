$(function(){
	
	$('.wrap-menu').each(function(index, element) {
		var _obj = $(this).find('li.active');
		if(_obj.length > 0){
			_obj.parents('dd').show();
			_obj.parents('dd').prev().addClass('active');
		}
	});
	$('.wrap-menu').on('click', 'dt', function(){
		$(this).hasClass('active') ? (
			$(this).removeClass('active'),
			$(this).next().stop().slideUp(300)
		) : (
			$(this).addClass('active'),
			$(this).next().stop().slideDown(300)
		);
	});
	
	$('.nav-tabs').each(function(index, element) {
		var _obj = $(this);
		$(this).on('click', 'strong:not(.active)', function(){
			$(this).addClass('active').siblings('strong').removeClass('active');
			_obj.nextAll('.form').children('.tab-pane').removeClass('active').eq($(this).index()).addClass('active')
		})
	});
	
});



/* 单选框功能 */
$('body').on('change', '.ipt-radio input', function(){
	$(this).parent().addClass('checked');
	$(this).parent().siblings('.checked').removeClass('checked');
});

/* 复选框功能 */
$('body').on('change', '.ipt-checkbox input', function(){
	$(this).is(':checked') ? $(this).parent().addClass('checked') : $(this).parent().removeClass('checked');
});

/* 下拉框事件 */
$('body').on('change', '.ipt-select select', function(){
	$(this).siblings('.ipt-text').val($(this).children(':checked').text());
});

/*$(window).load(function(){
	日历控件
	$('.date').each(function(index, element) {
		$(this).hasClass('date_month') ? (
			_sDate = $(this).find('.datepicker[name=startDate]'),
			_eDate = $(this).find('.datepicker[name=endDate]'),
			_sDate.focus(function(){
				WdatePicker()
			}),
			_eDate.focus(function(){
				WdatePicker({
					minDate : '#F{$dp.$D(\'sDate\', {d: +1})}',
					maxDate : '#F{$dp.$D(\'sDate\', {M: +1, d: -1})}'
				})
			})
		):(
			$(this).on('focus', '.datepicker', function(){
				WdatePicker()
			})
		);
	});
});*/
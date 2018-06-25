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
	
	
});

$(window).load(function(){
	/*日历控件*/
	/*$('.date').each(function(index, element) {
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
	});*/
});
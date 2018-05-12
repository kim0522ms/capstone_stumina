$(function() {
  $( ".calendar" ).datepicker({
		dateFormat: 'yy/mm/dd',
		firstDay: 1
	});
	
	$(document).on('click', '.date-picker .input', function(e){
		var $me = $(this),
		$parent = $me.parents('.date-picker');
		
		$parent.toggleClass('open');
	});
	
	
	$(".calendar").on("change",function(){
		var $me = $(this),
				$selected = $me.val(),
				$parent = $me.parents('.date-picker');
		$parent.find('.result').children('span').html($selected);
		
		console.log($parent.find('.result').children('span').attr('id'));
		
		if ($parent.find('.result').children('span').attr('id') == 'selectedDate')
		{
			console.log('find selectedDate');
			document.getElementById('rsch_date').value = $me.val();
		}
		else if ($parent.find('.result').children('span').attr('id') == 'startdate')
		{
			console.log('find startdate');
			document.getElementById('std_startdate').value = $me.val();
		}
		else if ($parent.find('.result').children('span').attr('id') == 'enddate')
		{
			console.log('find enddate');
			document.getElementById('std_enddate').value = $me.val();
		}
	});
});
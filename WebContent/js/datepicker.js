function getContextPath() {
	var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
};


$(function() {
	var availableTime;
	
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
			
			var sr_idx = $("#sr_idx").attr('value');
			var rsch_date = $("#rsch_date").attr('value');
			var room_idx = $("#room_idx").attr('value');
			
			console.log (sr_idx + ' ' + rsch_date + ' ' + room_idx);
			
			$.ajax({
	            type : "GET",
	            url : getContextPath() + "/ajax/getRoomAvailableTime?sr_idx=" + sr_idx,
	            dataType : "text",
	            data : { sr_idx : sr_idx,
	            		rsch_date : rsch_date,
	            		room_idx : room_idx
	            		},
	            error : function(){
	                alert('통신실패!!');
	            },
	            success : function(data){
	                //alert("통신데이터 값 : " + data) ;
	            	
	            	if (data == "error")
            		{
            			alert('예약 정보를 가져오는데 실패했습니다! 새로고침 후 다시 시도해 주세요!');
            			return;
            		}
	            	
	            	console.log(data);
	            	
	            	availableTime = data.split(new RegExp('\r?\n','g'));
	            	
	            	for (var temp in availableTime)
            		{
	            		if (availableTime[temp] == 'true')
            			{
	            			$('#dropdown-menu4').find('li').find('a').each(function(){
	            				if($(this).attr('id') == temp)
            					{
	            					$(this).show();
            					}
	            			});
	            			$('#dropdown-menu5').find('li').find('a').each(function(){
	            				if($(this).attr('id') == temp)
            					{
	            					$(this).show();
            					}
	            			});
            			}
	            		else
            			{
	            			$('#dropdown-menu4').find('li').find('a').each(function(){
	            				if($(this).attr('id') == temp)
            					{
	            					$(this).hide();
            					}
	            			});
	            			$('#dropdown-menu5').find('li').find('a').each(function(){
	            				if($(this).attr('id') == temp)
            					{
	            					$(this).hide();
            					}
	            			});
            			}
            		}	            	
	            }
	        });
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

function getContextPath() {
	var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
};


$(document).ready(function() {
	
	
	var triggerOpen_Study1		= $('#input_Study1');
	var triggerClose_Study1 	= $('#dropdown-menu_Study1').find('li');
	var marka_Study1			= $('#icon_Study1');
	var select_Study1			= '';
	
	var triggerOpen_Study2		= $('#input_Study2');
	var triggerClose_Study2 	= $('#dropdown-menu_Study2').find('li');
	var marka_Study2 			= $('#icon_Study2');

	//////////////////////
	// Dropdown #Study1 //
	//////////////////////
	
	// set initial Marka icon
	var m_Study1 = new Marka('#icon');
	m_Study1.set('triangle').size(10);
	m_Study1.rotate('down');

	// trigger dropdown
    triggerOpen_Study1.add(marka_Study1).on('click', function(e) {
		e.preventDefault();
		$('#dropdown-menu_Study1').add(triggerOpen_Study1).toggleClass('open');

		if($('#icon').hasClass("marka-icon-times")) {
			m_Study1.set('triangle').size(10);
		} else {
			m_Study1.set('times').size(15);
		}
	});

	triggerClose_Study1.on('click', function() {
		// set new placeholder for demo
		var options = $(this).find('a').html();
		
		// 선택한 값 반영
		triggerOpen_Study1.text(options);
		triggerOpen_Study2.text('소분류');
		document.getElementById('detail_idx').value = '';
		

		// hidden value 변경
		select_Study1 = $(this).find('a').attr('value');
		console.log(select_Study1);
		document.getElementById('area_idx').value = select_Study1;
		
		$('#dropdown-menu_Study2').find('li').find('a').each(function() 
		{
			console.log($(this).attr('id'));
			if ($(this).attr('id') != select_Study1)
			{
				$(this).hide();
			}
			else
			{
				$(this).show();
			}
		});
		

		$('#dropdown-menu_Study1').add(triggerOpen_Study1).toggleClass('open');
		m_Study1.set('triangle').size(10);
	});
	
	
	//////////////////////
	// Dropdown #Study2 //
	//////////////////////

	// set initial Marka icon
	var m_Study2 = new Marka('#icon');
	m_Study2.set('triangle').size(10);
	m_Study2.rotate('down');

	// trigger dropdown
    triggerOpen_Study2.add(marka_Study2).on('click', function(e) {
		e.preventDefault();
		$('#dropdown-menu_Study2').add(triggerOpen_Study2).toggleClass('open');

		if($('#icon').hasClass("marka-icon-times")) {
			m_Study2.set('triangle').size(10);
		} else {
			m_Study2.set('times').size(15);
		}
	});

	triggerClose_Study2.on('click', function() {
		// set new placeholder for demo
		var options = $(this).find('a').html();
		
		// 선택한 값 반영
		triggerOpen_Study2.text(options);

		// hidden value 변경
		var select_Study2 = $(this).find('a').attr('value');
		console.log(select_Study2);
		document.getElementById('detail_idx').value = select_Study2;

		$('#dropdown-menu_Study2').add(triggerOpen_Study2).toggleClass('open');
		m_Study2.set('triangle').size(10);
	});
	
	
	/////////////////
	// Dropdown #1 //
	/////////////////
	
	var triggerOpen		= $('#input');
	var triggerClose 	= $('#dropdown-menu').find('li');
	var marka 			= $('#icon');

	// set initial Marka icon
	var m = new Marka('#icon');
	m.set('triangle').size(10);
	m.rotate('down');

	// trigger dropdown
    triggerOpen.add(marka).on('click', function(e) {
		e.preventDefault();
		$('#dropdown-menu').add(triggerOpen).toggleClass('open');


		if($('#icon').hasClass("marka-icon-times")) {
			m.set('triangle').size(10);
		} else {
			m.set('times').size(15);
		}
	});

	triggerClose.on('click', function() {
		// set new placeholder for demo
		var options = $(this).find('a').html();
		
		// 선택한 값 반영
		triggerOpen.text(options);
		triggerOpen2.text('스터디룸을 선택해 주세요!');
		triggerOpen3.text('방을 선택해 주세요!');
		
		// hidden value 변경
		var select1 = $(this).find('a').attr('id');
		console.log(select1);
		document.getElementById('area_idx').value = select1;
		document.getElementById('room_idx').value = '';
		document.getElementById('room_idx').value = '';
		
		$('#dropdown-menu2').find('li').find('a').each(function() 
		{
			console.log($(this).attr('id'));
			if ($(this).attr('id') != select1)
			{
				$(this).hide();
			}
			else
			{
				$(this).show();
			}
		});


		$('#dropdown-menu').add(triggerOpen).toggleClass('open');
		m.set('triangle').size(10);
		$('#ddl_2').show();
	});
	
	
	/////////////////
	// Dropdown #2 //
	/////////////////
	
	var triggerOpen2	= $('#input2');
	var triggerClose2 	= $('#dropdown-menu2').find('li');
	var marka2 			= $('#icon2');

	// set initial Marka icon
	var m2 = new Marka('#icon');
	m2.set('triangle').size(10);
	m2.rotate('down');

	// trigger dropdown
    triggerOpen2.add(marka2).on('click', function(e) {
		e.preventDefault();
		$('#dropdown-menu2').add(triggerOpen2).toggleClass('open');

		if($('#icon').hasClass("marka-icon-times")) {
			m2.set('triangle').size(10);
		} else {
			m2.set('times').size(15);
		}
	});

	triggerClose2.on('click', function() {
		// set new placeholder for demo
		var options = $(this).find('a').html();
		
		// 선택한 값 반영
		triggerOpen2.text(options);
		triggerOpen3.text('방을 선택해 주세요!');
		
		$('#studyroom_name').text(options);
		
		var sr_idx = $(this).find('a').attr('value');
		$("#sr_idx").attr('value', sr_idx);
		
		var sr_info = "";
		
		// 스터디룸 이미지
		$.ajax({
            
            type : "GET",
            url : getContextPath() + "/ajax/getStudyroomImage?sr_idx=" + sr_idx,
            dataType : "text",
            error : function(){
                alert('통신실패!!');
            },
            success : function(data){
                //alert("통신데이터 값 : " + data) ;
            	console.log(data);
            	$('#studyroom_img').attr('src', data);
            }
        });
		
		// 스터디룸 정보
		$.ajax({
            
            type : "GET",
            url : getContextPath() + "/ajax/getStudyroomInfo?sr_idx=" + sr_idx,
            dataType : "text",
            error : function(){
                alert('통신실패!!');
            },
            success : function(data){
                //alert("통신데이터 값 : " + data) ;
            	$('#studyroom_info').text(data);
            }
        });
		
		// 스터디룸 시간
		$.ajax({  
            type : "GET",
            url : getContextPath() + "/ajax/getStudyroomTime?sr_idx=" + sr_idx,
            dataType : "text",
            error : function(){
                alert('통신실패!!');
            },
            success : function(data){
                //alert("통신데이터 값 : " + data) ;
                $('#studyroom_time').text("영업 시간 : " + data);
            }
        });
		
		// 스터디룸 위치
		$.ajax({  
            type : "GET",
            url : getContextPath() + "/ajax/getStudyroomLocation?sr_idx=" + sr_idx,
            dataType : "text",
            error : function(){
                alert('통신실패!!');
            },
            success : function(data){
                //alert("통신데이터 값 : " + data) ;
                $('#studyroom_location').text("주소 : " + data);
            }
        });
		
		$('#srform').css('display', 'inline');
		$('#srform').css('width', '30%');
		$('#srform').css('margin', 'auto');
		$('#stepform').css('width', '70%');
		$('#stepform').css('margin-left', '0%');
		
		
		// hidden value 변경
		var select2 = $(this).find('a').attr('value');
		console.log(select2);
		document.getElementById('room_idx').value = select2;
		document.getElementById('room_idx').value = '';
		
		$('#dropdown-menu3').find('li').find('a').each(function() 
		{
			console.log($(this).attr('id'));
			if ($(this).attr('id') != select2)
			{
				$(this).hide();
			}
			else
			{
				$(this).show();
			}
		});
		

		$('#dropdown-menu2').add(triggerOpen2).toggleClass('open');
		m2.set('triangle').size(10);
		$('#ddl_3').show();
	});
	
	
	/////////////////
	// Dropdown #3 //
	/////////////////
	
	var triggerOpen3	= $('#input3');
	var triggerClose3 	= $('#dropdown-menu3').find('li');
	var marka3 			= $('#icon3');
	var payment 		= 0;

	// set initial Marka icon
	var m3 = new Marka('#icon');
	m3.set('triangle').size(10);
	m3.rotate('down');

	// trigger dropdown
    triggerOpen3.add(marka3).on('click', function(e) {
		e.preventDefault();
		$('#dropdown-menu3').add(triggerOpen3).toggleClass('open');


		if($('#icon').hasClass("marka-icon-times")) {
			m3.set('triangle').size(10);
		} else {
			m3.set('times').size(15);
		}
	});

	triggerClose3.on('click', function() {
		// set new placeholder for demo
		var options = $(this).find('a').html();
		
		// 선택한 값 반영
		triggerOpen3.text(options);
		
		// hidden value 변경
		var select3 = $(this).find('a').attr('value');
		console.log(select3);
		document.getElementById('room_idx').value = select3;
		

		$('#dropdown-menu3').add(triggerOpen3).toggleClass('open');
		m3.set('triangle').size(10);
		/*
		$('#payment_head').show();
		$('#payment').show();
		$('#payment_tail').show();
		*/
		
		$('#dropdown-menu3').find('input').each(function() 
		{
			if ($(this).attr('id') == select3)
			{
				console.log($(this).attr('value'));
				$('#payment').text('시간당 요금 : ' + $(this).attr('value') + '원');
				payment = $(this).attr('value');
				console.log('시간당요굼 : ' + payment);
			}
		});
		
		$("#payment_head").css("display","inline");
		$("#payment").css("display","inline");
		$("#payment_tail").css("display","inline");
	});
	
	/////////////////
	// Dropdown #4 //
	/////////////////
	
	var triggerOpen4	= $('#input4');
	var triggerClose4 	= $('#dropdown-menu4').find('li');
	var marka4 			= $('#icon4');
	var select4			= 0;

	// set initial Marka icon
	var m4 = new Marka('#icon');
	m4.set('triangle').size(10);
	m4.rotate('down');

	// trigger dropdown
    triggerOpen4.add(marka4).on('click', function(e) {
		e.preventDefault();
		$('#dropdown-menu4').add(triggerOpen4).toggleClass('open');

		if($('#icon').hasClass("marka-icon-times")) {
			m4.set('triangle').size(10);
		} else {
			m4.set('times').size(15);
		}
	});

	triggerClose4.on('click', function() {
		// set new placeholder for demo
		var options = $(this).find('a').html();
		triggerOpen4.text(options);
		
		// 선택한 값 반영
		
		triggerOpen5.text('퇴실 시간');
		
		// hidden value 변경
		select4 = $(this).find('a').attr('id');
		console.log(select4);
		
		var limitTime;
		
		document.getElementById('rsch_checkin').value = select4;
		document.getElementById('rsch_checkout').value = '';
		
		$('#dropdown-menu4').find('li').each(function() 
		{
			if (parseInt($(this).find('a').attr('id')) <= parseInt(select4))
			{
				return true;
			}
			else
			{
				if ($(this).find('a').attr('style') == 'display: none;')
				{
					limitTime = $(this).find('a').attr('id');
					return false;
				}
			}
		});
		
		console.log("limit Time : " + limitTime);
		
		$('#dropdown-menu5').find('li').each(function() 
		{
			if (parseInt($(this).find('a').attr('id')) <= parseInt(select4))
			{
				$(this).hide();
			}
			else
			{
				if (parseInt($(this).find('a').attr('id')) > parseInt(limitTime) + 1)
				{
					$(this).hide();
				}
				else
				{
					$(this).show();
				}
			}
		});

		$('#dropdown-menu4').add(triggerOpen4).toggleClass('open');
		$('#pay_amount').hide();
		m4.set('triangle').size(10);
		// $('#ddl_3').show();
	});
	
	
	/////////////////
	// Dropdown #5 //
	/////////////////
	
	var triggerOpen5	= $('#input5');
	var triggerClose5 	= $('#dropdown-menu5').find('li');
	var marka5 			= $('#icon5');
	var pay_amount		= 0;
	var select5			= 0;

	// set initial Marka icon
	var m5 = new Marka('#icon');
	m5.set('triangle').size(10);
	m5.rotate('down');

	// trigger dropdown
    triggerOpen5.add(marka5).on('click', function(e) {
		e.preventDefault();
		$('#dropdown-menu5').add(triggerOpen5).toggleClass('open');

		if($('#icon').hasClass("marka-icon-times")) {
			m5.set('triangle').size(10);
		} else {
			m5.set('times').size(15);
		}
	});

	triggerClose5.on('click', function() {
		// set new placeholder for demo
		var options = $(this).find('a').html();
		triggerOpen5.text(options);
		
		select5 = $(this).find('a').attr('id');
		console.log(select5);
		
		pay_amount = (select5 - select4) * payment;
		
		document.getElementById('rsch_checkout').value = select5;
		
		$('#pay_amount').text('총 요금 : ' + pay_amount + '원');
		$('#pay_amount').show();

		document.getElementById('rsch_pay').value = pay_amount;
		
		$('#dropdown-menu5').add(triggerOpen5).toggleClass('open');
		m5.set('triangle').size(10);
		// $('#ddl_3').show();
	});
	
	
});
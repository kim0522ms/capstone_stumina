$(document).ready(function() {
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
				$('#payment').text($(this).attr('value'));
			}
		});
		
		$("#payment_head").css("display","inline");
		$("#payment").css("display","inline");
		$("#payment_tail").css("display","inline");
	});
});
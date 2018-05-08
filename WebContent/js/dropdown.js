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
		triggerOpen.text(options);

		$('#dropdown-menu').add(triggerOpen).toggleClass('open');
		m.set('triangle').size(10);
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
		triggerOpen2.text(options);
		document.getElementById('detail_idx').value = options;

		$('#dropdown-menu2').add(triggerOpen2).toggleClass('open');
		m2.set('triangle').size(10);
	});
});
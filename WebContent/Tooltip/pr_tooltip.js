(function($) {
$(document).ready(function() {
	var	prPosition, prToolTip;
	$('.pr_tooltip').hover(function() {
		//mouse over
		prToolTip = false;
		if(title = $(this).attr('title')) {
			$(this).data('ttString', title).removeAttr('title');
			
			//컬러, 툴바위치 기본값 지정
			var ttcolor = 'prtt_black';
			prPosition = 21;

			if($(this).attr('pr_color')) {
				ttcolor = 'prtt_' + $(this).attr('pr_color');
			}
			var position = $(this).attr('pr_position');
			var pValue = {top: 1, bottom: 3, left: 12, right: 22, t_left: 11, t_right: 21, b_left: 13, b_right: 23};
			if(position && pValue[position]) {
				prPosition = pValue[position];
				position = 'prtt_' + position;
			}
			else {
				for(var key in pValue) {
					if(pValue[key] == prPosition) {
						position = 'prtt_' + key;
						break;
					}
				}
			}

			$('<p class="prtooltip"></p>').html(title).addClass(ttcolor + ' ' + position).appendTo('body').fadeIn('slow');
			prToolTip = $('.prtooltip');
		}		
	}, function() {
		//mouse out
		if(prToolTip) {
			$(this).attr('title', $(this).data('ttString'));
			prToolTip.remove();
		}
	}).mousemove(function(e) {
		if(prToolTip) {
			var prtt_X, prtt_Y;
			//Y position
			var remainder = parseInt(prPosition % 10);
			if(remainder == 3) {
				//bottom
				prtt_Y = e.pageY + 20;
			}
			else if(remainder == 1) {
				//top
				prtt_Y = e.pageY - prToolTip.outerHeight() - 35;
			}
			else {
				//ohter: middle
				prtt_Y = Math.round(e.pageY - prToolTip.outerHeight() / 2) - 10;
			}
			//X position
			if(prPosition > 20) {
				//right
				if(remainder == 2) {
					prtt_X = e.pageX + 30;
				}
				else {
					prtt_X = e.pageX - 20;
				}
			}
			else if(prPosition > 10) {
				//left
				if(remainder == 2) {
					prtt_X = e.pageX - prToolTip.outerWidth() - 30;
				}
				else {
					prtt_X = e.pageX - prToolTip.outerWidth() + 20;
				}
			}
			else {
				//other: center
				prtt_X = Math.round(e.pageX - prToolTip.outerWidth() / 2);
			}
			prToolTip.css({ top: prtt_Y, left: prtt_X });
		}
	});
});
}(jQuery));
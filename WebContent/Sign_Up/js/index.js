// Credits to Mohammad Shehbaaz for the design
// https://dribbble.com/shots/1819287-Register-Form?list=shots&sort=popular&timeframe=now&offset=387

$('input').bind('focus', function() {
  $(this).parent('.field').css({ 'background-color' : '#f5f8f9'});
});
$('input').bind('blur', function() {
  $(this).parent('.field').css({ 'background-color' : 'none'});
});

function checkValue(){
	var pw = document.getElementById('userpw');
	var rpw = document.getElementById('ruserpw');
	var rpw_div = document.getElementById('ruserpw_div');
	
	if (pw.value != rpw.value)
	{
		rpw_div.style.background = 'pink';
	}
	else
	{
		rpw_div.style = null;
	}
}
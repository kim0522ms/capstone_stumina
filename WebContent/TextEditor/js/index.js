$('#bold').on('click', function() {
   document.execCommand('bold', false, null);
});

$('#italic').on('click', function() {
   document.execCommand('italic', false, null);
});

$('#underline').on('click', function() {
   document.execCommand('underline', false, null);
});

$('#align-left').on('click', function() {
   document.execCommand('justifyLeft', false, null);
});

$('#align-center').on('click', function() {
   document.execCommand('justifyCenter', false, null);
});

$('#align-right').on('click', function() {
   document.execCommand('justifyRight', false, null);
});

$('#list-ul').on('click', function() {
   document.execCommand('insertUnorderedList', false, null);
});

$('#list-ol').on('click', function() {
   document.execCommand('insertOrderedList', false, null);
});

$('#fonts').on('change', function() {
   var font = $(this).val();
   document.execCommand('fontName', false, font);
});

$('#size').on('change', function() {
   var size = $(this).val();
   $('.editor').css('fontSize', size + 'px');
});

$('#color').spectrum({
   color: '#000',
   showPalette: true,
   showInput: true,
   showInitial: true,
   showInput: true,
   preferredFormat: "hex",
   showButtons: false,
   change: function(color) {
      color = color.toHexString();
      document.execCommand('foreColor', false, color);
   }
});

$('.editor').perfectScrollbar();


$(document).ready(function() {
    $('#uploadButton').click(function() {
    	
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "/Graduation_KMS/op/uploadThread");
    form.setAttribute("enctype", "multipart/form-data");

    var cont = $('#contents').html(); 
     
    console.log(cont);
    console.log(document.getElementById("rsch_idx").value);
    
    var hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "content");
	hiddenField.setAttribute("value", cont);
	
	console.log(hiddenField);
	form.appendChild(hiddenField);
	
	var hidden_rsch_idx = document.createElement("input");
	hidden_rsch_idx.setAttribute("type", "hidden");
	hidden_rsch_idx.setAttribute("name", "rsch_idx");
	hidden_rsch_idx.setAttribute("value", document.getElementById("rsch_idx").value);

	console.log(hidden_rsch_idx);
	form.appendChild(hidden_rsch_idx);
	
	var myFile = document.getElementById("myFile");
	var myFileName = document.getElementById("myFileName");

	console.log(myFile);
	form.appendChild(myFile);
	form.appendChild(myFileName);
	
	console.log(form);
	document.body.appendChild(form);

	form.submit();
	});
});

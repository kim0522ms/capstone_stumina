<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Text Editor</title>
	<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css'>
	<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/spectrum/1.7.1/spectrum.min.css'>
	<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/jquery.perfect-scrollbar/0.6.7/css/perfect-scrollbar.min.css'>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/TextEditor/css/style.css">
</head>
<body>
	<div class="wrap">
	<div class="toolbar">
		<button id="bold" title="Bold (Ctrl+B)"><i class="fa fa-bold"></i></button>
		<button id="italic" title="Italic (Ctrl+I)"><i class="fa fa-italic"></i></button>
		<button id="underline" title="Underline (Ctrl+U)"><i class="fa fa-underline"></i></button>
		<select name="fonts" id="fonts">
			<option value="Arial" selected>Arial</option>
			<option value="Georgia">Georgia</option>
			<option value="Tahoma">Tahoma</option>
			<option value="Times New Roman">Times New Roman</option>
			<option value="Verdana">Verdana</option>
			<option value="Impact">Impact</option>
			<option value="Courier New">Courier New</option>
		</select>
		<select name="size" id="size">
			<option value="8">8</option>
			<option value="10">10</option>
			<option value="12">12</option>
			<option value="14">14</option>
			<option value="16" selected>16</option>
			<option value="18">18</option>
			<option value="20">20</option>
			<option value="22">22</option>
			<option value="24">24</option>
			<option value="26">26</option>
		</select>
		<input type="text" id="color" />
		<button id="align-left" title="Left"><i class="fa fa-align-left"></i></button>
		<button id="align-center" title="Center"><i class="fa fa-align-center"></i></button>
		<button id="align-right" title="Right"><i class="fa fa-align-right"></i></button>
		<button id="list-ul" title="Unordered List"><i class="fa fa-list-ul"></i></button>
		<button id="list-ol" title="Ordered List"><i class="fa fa-list-ol"></i></button>
		<button id="upload_File" title ="Upload File"><i class="fa fa-save"></i></button>
	</div>
	<div id="contents" class="editor" contenteditable></div>
	</div>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/spectrum/1.7.1/spectrum.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery.perfect-scrollbar/0.6.7/js/min/perfect-scrollbar.jquery.min.js'></script>
	<script  src="${pageContext.request.contextPath}/TextEditor/js/index.js"></script>
</body>
</html>
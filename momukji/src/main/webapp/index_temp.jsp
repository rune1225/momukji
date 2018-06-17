<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>모먹지</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
	<img id="Atype" src="resources/images/temp/Atype.jpg" style="width: 100%; display: block;">
	<img id="Btype" src="resources/images/temp/Btype.jpg" style="width: 100%; display: none;">
</body>
<script type="text/javascript">

$( "#Atype" ).click(function() {
	$('#Atype').css('display', 'none');
	$('#Btype').css('display', 'block');
});
$( "#Btype" ).click(function() {
	$('#Atype').css('display', 'block');
	$('#Btype').css('display', 'none');
});
</script>
</html>
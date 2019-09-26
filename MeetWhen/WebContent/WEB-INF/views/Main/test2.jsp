<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
	
	<div id="sss">ggg</div>
	<script type="text/javascript">

		var data=document.getTextById("sss");
		console.log(data);
		document.getElementById("sss").innerHTML=data+"gegeeg";
	</script>
</body>
</html>
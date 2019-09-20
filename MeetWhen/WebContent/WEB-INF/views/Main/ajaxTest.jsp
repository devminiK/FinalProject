<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function pageStart(){
		window.setTimeout("pagereload()",3000);//1000 = 1초
	}
	function pagereload(){
		location.reload();
	}
</script>
</head>

<body onload="pageStart()">
	<h1>30초씩 리로딩</h1>
</body>
</html>
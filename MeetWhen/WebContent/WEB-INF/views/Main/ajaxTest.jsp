<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%--다른 페이지 리로딩 시키는 방법? --%>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
 <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<script type="text/javascript">
	function pageStart(){
		//window.setTimeout("pagereload()",3000);//1000 = 1초
	}
	function pagereload(){
		location.reload();
	}

</script>
</head>

<body onload="pageStart()">
	<h1>30초씩 리로딩</h1>
	<jsp:include page="test4.mw"/>
</body>
</html>
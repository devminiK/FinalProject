<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%--�ٸ� ������ ���ε� ��Ű�� ���? --%>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
 <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<script type="text/javascript">
	function pageStart(){
		//window.setTimeout("pagereload()",3000);//1000 = 1��
	}
	function pagereload(){
		location.reload();
	}

</script>
</head>

<body onload="pageStart()">
	<h1>30�ʾ� ���ε�</h1>
	<jsp:include page="test4.mw"/>
</body>
</html>
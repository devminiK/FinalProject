<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--크롤링1: 구글 검색결과  --%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>crawling 1</title>
</head>
<style>
table {
	border: 2px solid black;
	border-collapse: collapse;
	text-align: center;
	background-color:white;
}
td {
	font-weight: bold;
	border: 1px solid black;
	padding: 10px;
	font-size:18px;
}
</style>
<body>
	<table>
		<tr>
			<td>${cont}</td>
		</tr>
		<tr>
			<td>${vo.cwa2_ex1}</td>
		</tr>
		<tr>
			<td>${vo.cwa2_ex2}</td>
		</tr>
	</table>
</body>
</html>
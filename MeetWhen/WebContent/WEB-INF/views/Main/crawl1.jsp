<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--크롤링1: 네이버 검색결과  --%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Untitled Document</title>
</head>
<style>
table {
	border: 2px solid black;
	border-collapse: collapse;
	text-align: center;
}
td {
	font-weight: bold;
	border: 1px solid black;
	padding: 10px;
}
</style>
<body>
	<h1>[ ${contryName} ]</h1>
	<table>
		<tr>
			<td>국가명</td>
			<td>${contry}</td>
		</tr>
		<tr>
			<td>국기</td>
			<td><img src="${imgSrc}" height=150px/></td>
		</tr>
		<tr>
			<c:if test="${caseNum!='3'}">
				<td>위치</td>
			</c:if>
			<c:if test="${caseNum=='3'}">
				<td>수도</td>
			</c:if>
			<td>${capital}</td>
		</tr>
		<tr>
			<c:if test="${caseNum=='2'}">
				<td>통화</td>
			</c:if>
			<c:if test="${caseNum!='2'}">
				<td>환율</td>
			</c:if>
			<td>
				${rate}
			</td>
		</tr>
	</table>
</body>
</html>
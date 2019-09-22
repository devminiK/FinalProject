<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--크롤링1: 네이버 검색결과  --%>
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
}
</style>
<body>
	<table>
		<tr>
			<td>국가명</td>
			<td>${vo.cwaa1_cont}</td>
		</tr>
		<tr>
			<td>국기</td>
			<td><img src="${vo.cwa1_img}" width=200px; height=150px/></td>
		</tr>
		<tr>
			<c:if test="${vo.cwa1_type!='3'}">
				<td>위치</td>
			</c:if>
			<c:if test="${vo.cwa1_type=='3'}">
				<td>수도</td>
			</c:if>
			<td>${vo.cwa1_cap}</td>
		</tr>
		<tr>
			<c:if test="${vo.cwa1_type=='2'}">
				<td>통화</td>
			</c:if>
			<c:if test="${vo.cwa1_type!='2'}">
				<td>환율</td>
			</c:if>
			<td>
				${vo.cwa1_rat}
			</td>
		</tr>
	</table>
</body>
</html>
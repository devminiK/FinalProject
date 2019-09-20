<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--크롤링3: 세계 뉴스  --%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>crawling 3</title>
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
#artBox{
	float:left;
	border:2px solie black;
	width:200px;
	height:200px;
	text-align:center;
	line-height:100px;
	font-weight:bold;
}
</style>

<body>
	<h1>크롤링3_${clickCont}</h1>
	<h4>기사 이미지, 제목 15개 출력.</h4>
	<hr>
	<table>
		<tr>
			<td>0</td>
			<td>1</td>
			<td>2</td>
			<td>3</td>
		</tr>
	</table>
	

	<c:forEach items="${allList}" var="ent" begin="0" step="1" end="15">
	
	<div id="artBox">
		<a href="${ent.url}">
			<img src="${ent.src}" height=100px/><br>
			<b>${ent.title}</b>
		</a>
	</div>
	<hr>

	
	
	</c:forEach>
</body>

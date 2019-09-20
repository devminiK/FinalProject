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
	border:1px solid gray;
	width:25%;
	height:200px;
	text-align:center;
	line-height:100px;
	font-weight:bold;
}
#title{
	display:inline-block;
	width: 250px;
	white-space:nowrap;
	overflow:hidden;
	text-overflow:ellipsis;
	font-weight:bold;
	font-size:18px;
	
}
</style>

<body>
	<div id="artBox" onclick="location.href='${totalURL}'">
			<b>모든 기사 한눈에 보기</b>
	</div>
	<c:forEach items="${allList}" var="ent" begin="0" step="1" end="15">
	<div id="artBox">
		<a href="${ent.url}">
			<img src="${ent.src}" width=250px height=140px/><br>
			<b id="title">${ent.title}</b>
		</a>
	</div>
	</c:forEach><br>
</body>

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
.artBox{
	float:left;
	width: 25%;
	max-width: 285px;
  	heigth:267px;
	text-align:center;
	border:1px solid red;
	padding:8px;
}
#artTitle{
	display:inline-block;
	width: 250px;
	white-space:nowrap;
	overflow:hidden;
	text-overflow:ellipsis; /*생략단어는 ...로 대체*/
	font-weight:700;
	font-size:18px;
	font-color:black;
}
</style>

<body class="artsBox">
		<div class="artBox" onclick="location.href='${totalURL}'">
			<h1>모든 기사</h1>
			<h1>한 눈에 보기</h1>
		</div>
		<c:forEach items="${allList}" var="ent" begin="0" step="1" end="15">
			<div class="artBox" onclick="location.href='${ent.url}'">
				<p id="artTitle">${ent.title}</p>
				<c:if test="${ent.src!=null}">
					<img src="${ent.src}" width=250px height=200px />
				</c:if>
			</div>
		</c:forEach>
		<br>

</body>
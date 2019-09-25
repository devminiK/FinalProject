<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--크롤링3: 명소추천  --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
#opp:hover {
  -webkit-transform: scaleX(-1);
  transform: scaleX(-1);
}
</style>
</head>
<body>
<h3>${clickCont}의추천 명소</h3>
<img id="opp" src="/MeetWhen/img/recom/recom0.jpg" alt="RecomPlace" width="400" height="300">
</body>
</html>
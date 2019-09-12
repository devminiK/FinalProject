<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>DB내용 확인</h1>
<c:forEach var="ent" items="${dataList}" begin="0" step="1" end="${listSize}">
	<c:forEach var="atrr" items="${ent}" begin="0" step="1" end="2">
		
	</c:forEach>
</c:forEach>

<h1>TEST</h1>
<c:forEach var="w"  begin="0" step="1" end="${listSize}">
		w=${w}
</c:forEach>
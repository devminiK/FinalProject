<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>DB내용 확인 PAGE</h1>

<c:forEach items="${dataList}" var="ent" begin="0" step="1" end="${listSize}">
	<c:if test="${num==1}">
		<span>${ent.c_con} : ${ent.c_cnt}</span><br>
	</c:if>
	<c:if test="${num==2}">
		<span>${ent.lc_con} : ${ent.lc_cnt}, ${ent.lc_lat}, ${ent.lc_lon}</span><br>
	</c:if>
	<c:if test="${num==3}">
		<span>${ent.r_reg} : ${ent.r_cnt}</span><br>
	</c:if>
	<c:if test="${num==4}">
		<span>${ent.lr_reg} : ${ent.lr_cnt}, ${ent.lr_lat}, ${ent.lr_lon}</span><br>
	</c:if>
	
</c:forEach>

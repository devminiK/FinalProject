<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${listSize==0}">
	<h1>현재 DB${num}의 내용이 존재하지 않습니다.</h1>
</c:if>
<c:if test="${listSize!=0}">
	<h1>DB${num}의 정보</h1>
</c:if>

<c:forEach items="${dataList}" var="ent" begin="0" step="1" end="${listSize}">
	<c:if test="${num==1}">
		<span>${ent.c_num}) ${ent.c_con} : ${ent.c_cnt}</span><br>
	</c:if>
	<c:if test="${num==2}">
		<span>${ent.lc_num}) ${ent.lc_con} : ${ent.lc_cnt}, ${ent.lc_lat}, ${ent.lc_lon}</span><br>
	</c:if>
	<c:if test="${num==3}">
		<span>${ent.r_num}) ${ent.r_reg} : ${ent.r_cnt}</span><br>
	</c:if>
	<c:if test="${num==4}">
		<span>${ent.lr_num}) ${ent.lr_reg} : ${ent.lr_cnt}, ${ent.lr_lat}, ${ent.lr_lon}</span><br>
	</c:if>
	
</c:forEach>

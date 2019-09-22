<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table border=1>
	<tr>
		<td>
			<h3>[date.jsp]시간 출력</h3>
			<c:set var="t" value="<%=new java.util.Date()%>" />
			<fmt:formatDate value="${t}" type="both"/>
		</td>
	</tr>
</table>
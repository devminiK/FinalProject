<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--ũ�Ѹ�) 1.���̹� �˻����  --%>
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
			<td>������</td>
			<td>${contry}</td>
		</tr>
		<tr>
			<td>����</td>
			<td>Flag-img</td>
		</tr>
		<tr>
			<td>����</td>
			<td>${capital}</td>
		</tr>
		<tr>
			<td>ȯ��</td>
			<td>${rate}</td>
		</tr>
	</table>

</body>
</html>
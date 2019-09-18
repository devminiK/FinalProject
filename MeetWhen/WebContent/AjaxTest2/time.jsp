<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.Date"  %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date time = new Date();
	out.println(sdf.format(time));
%>
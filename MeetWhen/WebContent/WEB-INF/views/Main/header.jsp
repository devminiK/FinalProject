<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
    
	<nav class="navbar navbar-expand  navbar-dark bg-dark">
		<div class="container" >
			<div class="navbar-header">
				<a class="navbar-brand" href="/MeetWhen/Main/welcome.mw">LOGO</a>
				<%--session에 따라 로그인/아웃 --%>
				<%if(session.getAttribute("loginId") == null){%>
				<a class="navbar-brand" href="/MeetWhen/Member/registerForm.mw">| Sign-up</a>
				<a class="navbar-brand" href="/MeetWhen/Log/loginForm.mw">| Log-in</a>
				<%}else{%>
				<a class="navbar-brand" href="/MeetWhen/Member/registerForm.mw">| Sign-out</a>
				
				<% }	
				%>
				<a class="navbar-brand" href="#">| Product</a>
				<a class="navbar-brand" href="#">| Add Product</a>
			</div>
		</div>
	</nav>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>

<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>mini's</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	
	<%!
		String greeting = "Welcome to Mini's Web Shopping Mall";
		String tagline = "Welcome to Web Market!";
	%>
	<%
				Date day = new Date();
				int hour = day.getHours();
				int min = day.getMinutes();
				int sec = day.getSeconds();
				
				String ap="";
				if(hour/12 ==0){
					ap = "AM";
				}else{
					ap="PM";
					hour-=12;
				}	
			%>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">
				<jsp:include page="map_all.jsp"/>
			</h1>
		</div>
	</div>	
	<div class="container">
		<div class="text-center">
			<h3>
				<h3>나라 기사, 및 광고 자리</h3>
				<%=tagline%>
			</h3>		
			<%=	"현재 접속 시각: "+hour+":"+min+":"+sec+" "+ap %>
		</div>
		<hr>
	</div>	
	<jsp:include page="footer.jsp"/>
</body>
</html>
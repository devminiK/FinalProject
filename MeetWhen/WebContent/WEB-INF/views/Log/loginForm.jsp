<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Log-in</title>

<style>
#logMain {
	width:1200px;
	height: 100%;
	font-family: Arial, Helvetica, sans-serif;
	margin: 0 auto;
}
.bg-img {
	/* The image used */
	margin-top:10px;
	background-image: url("/MeetWhen/Images/desert.jpg");
	min-height: 600px;
	/* Center and scale the image nicely */
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
	position: relative;
	background-color:gray;
}
/* Add styles to the form container */
.log_container {
	position: absolute;
	right: 20px;
	margin: 20px;
	max-width: 300px;
	padding: 16px;
	background-color: white;
}
/* Full-width input fields */
input[type=text], input[type=password] {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	border: none;
	background: #f1f1f1;
}
input[type=text]:focus, input[type=password]:focus {
	background-color: #ddd;
	outline: none;
}
/* Set a style for the submit button */
.btn {
	background-color: #4CAF50;
	color: white;
	padding: 16px 20px;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
	margin: 3px;
}
.btn:hover {
	opacity: 1;
}
</style>
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<body id="logMain">
	<jsp:include page="/Main/header.mw"/>
	<div>
	<br>
		<div class="bg-img">
			<%if(session.getAttribute("loginId") == null){%>
			<form action="loginPro.jsp" method="post" class="log_container">
				<h1>로그인</h1>
				<label for="id"><b>ID</b></label> 
				<input type="text"	name="m_id" placeholder="Enter ID"  required> 
				<label	for="pw"><b>Password</b></label> 
				<input type="password" name="m_pw" placeholder="Enter Password" required>

				<button type="submit" class="btn">Login</button>
				<button type="reset" class="btn">Reset</button>
				<button type="button" 
				class="btn" onclick="javascript:window.location='/MeetWhen/Member/registerForm.mw'">Join Us</button>
				<hr>
				<%--다른 것들도 추가하기 --%>
				<jsp:include page="/Naver/naverlogin.mw"/>
			</form>
			<%}else{ //로그아웃시 main.jsp로 이동
				session.invalidate();
			%>
					<script>
						history.go(-1);
					</script>
			<%}%>
		</div>
	</div>
	<hr>
	<jsp:include page="/Main/footer.mw"/>
	
</body>
</html>
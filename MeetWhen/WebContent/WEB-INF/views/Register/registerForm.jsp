<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--성민 작성 : Form만 있는 것임, 정상 동작 x --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<body>
	<%-- <jsp:include page="/Main/header.mw" /> --%>
	
	<script type="text/JavaScript">
		function checkReg() {
			//var member = eval("document.member");
			var member = document.member;
			var regPhone = /^\d{3}-\d{3,4}-\d{4}$/;
			var regPw = member.m_pw.value;
			var regPwcheck = member.pwcheck.value;

			if (!regPhone.test(member.m_phone.value)) {
				alert("010-0000-0000형식으로 입력하세요^^");
				member.m_phone.focus();
				return false;
			}

			if (regPw != regPwcheck) {
				alert("비밀번호를 다시 입력하세요");
				member.pwcheck.focus();
				return false;
			}
		}

		// 아이디 중복 여부를 판단
		function openConfirmid(member) {
			// 아이디를 입력했는지 검사
			if (member.m_email.value == "") {
				alert("이메일을입력하세요");
				return;
			}
			// url과 사용자 입력 id를 조합합니다.
			url = "confirmId.mw?m_email=" + member.m_email.value;

			// 새로운 윈도우를 엽니다.
			open(
					url,
					"confirm",
					"toolbar=no, location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300, height=200");
		}
	</script>


	<%
		request.setCharacterEncoding("UTF-8");
		String m_email = request.getParameter("m_email");
	%>

	<form action="registerPro.mw" name="member" method="post" align="center">

		<table width="550" align="center">
			<br><br>
			<tr height="80" bgcolor="FDD262">
				<th colspan="3"><font color="white">회원가입</font></th>
			</tr>
			<tr height="50">
				<th>이 름(실명)</th>
				<th><input type="text" autofocus name="m_name" required /></th>
			</tr>
			<tr height="50">
				<th>ID</th>
				<th><input type="text" autofocus name="m_id" required /></th>
			</tr>
			<tr height="50">
				<th>전화번호</th>
				<th><input type="text" placeholder="010-0000-0000"
					name="m_phone" required /></th>
			</tr>
			<tr height="50">
				<th>이메일</th>
				<th><input type="text" placeholder="meetwhen@naver.com"
					name="m_email" required /></th>
				<th><input type="button" value="중복확인" name="confirm_id"
					onclick="openConfirmid(this.form)" /></th>
			</tr>
			<tr height="50">
				<th>비밀번호</th>
				<th><input type="password" name="m_pw" required /></th>
			</tr>
			<tr height="50">
				<th>비밀번호 재확인</th>
				<th><input type="password" name="pwcheck" required /></th>
			</tr>
			<tr height="50">
				<th>주소</th>
				<th><input type="text" name="m_add" /></th>
			</tr>


		</table>
		<br /> <input type="submit" value="가입하기"
			style="height: 50px; width: 500px;" onclick="return checkReg()" /> <br />
		<br /> <a href="/MeetWhen/Member/loginForm.mw">이미 회원이세요?</a>
	</form>
	<hr>
	<%-- <jsp:include page="/Main/footer.mw" />  --%>
</body>
</html>
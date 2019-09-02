<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--NAVER 로그인 버튼 화면 --%>
<!doctype html>
<html lang="ko">
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
 <script type="text/javascript">
  var naver_id_login = new naver_id_login("aEtf99yR3kaHUriPUGkA", "http://localhost:8080/MeetWhen/Naver/naverCallback.mw");
  var token, email,name,nick;
  
  // 접근 토큰 값 출력
  alert("네이버 토큰:"+naver_id_login.oauthParams.access_token);
  // 네이버 사용자 프로필 조회
  naver_id_login.get_naver_userprofile("naverSignInCallback()");
  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
  
  function naverSignInCallback() {
	  var e =naver_id_login.getProfileData('email');
	  elementbyid.innerHTNL=e;
    alert("네이버 이메일: "+naver_id_login.getProfileData('email'));
    alert("네이버 이름: "+naver_id_login.getProfileData('name'));
    alert("네이버 닉네임: "+naver_id_login.getProfileData('nickname'))
	
    //window.close(); //윈도우 닫기
  }
  </script>
<label id="email"></label>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%--다른 페이지 리로딩 시키는 방법? --%>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
 <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	function callCrawla2(){
		$(document).ready(function(){	
				$.ajax({
					type :"post",
					url : "/MeetWhen/Crawl/doCrawla2.mw",
					success : test(1) , 
					error : reqError(1)
				});
			
		});
	}
	function callCrawla1(){
		$(document).ready(function(){	
				$.ajax({
					type :"post",
					url : "/MeetWhen/Crawl/doCrawla1.mw",
					success : test(2) , 
					error : reqError(2)
				});
			
		});
	}
	function callCrawlb(){
		$(document).ready(function(){	
				$.ajax({
					type :"post",
					url : "/MeetWhen/Crawl/doCrawlb.mw",
					success : test(3) , 
					error : reqError(3)
				});
			
		});
	}
	function test(num){	
		if(num==1){
			$("#resultA1").text("실행 완료!");
		}
		else if(num==2){
			$("#resultA2").text("실행 완료!");
		}
		else if(num==3){
			$("#resultB").text("실행 완료!");
		}
				
	}
	function reqError(num){	
		if(num==1){
			$("#resultA1").text("실행 오류...!!");
		}
		else if(num==2){
			$("#resultA2").text("실행 오류...!!");
		}
		else if(num==3){
			$("#resultB").text("실행 오류...!!");
		}
				
	}	
	
	function StartClock(){
		callCrawl2();
		setInterval(callCrawl2,60000);//1000=1초, 60000=1분 
	}
</script>
</head>

<body onload="callCrawl2()">
	<h1>인터벌 test</h1>
	<div id="resultA1"></div>
	<div id="resultA2"></div>
	<div id="resultB"></div>


</body>
</html>
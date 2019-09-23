<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%--다른 페이지 리로딩 시키는 방법? --%>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
 <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	function callCrawl2(){
		$(document).ready(function(){	
				$.ajax({
					type :"post",
					url : "/MeetWhen/Crawl/doCrawla2.mw",
					success : test , 
					error : reqError
				});
			
		});
	}
	function test(){	$("#result").text("실행 완료!")		}
	function reqError(){	$("#result").text("실행 오류...!!");			}	
	
	function StartClock(){
		callCrawl2();
		setInterval(callCrawl2,60000);//1000=1초, 60000=1분 
	}

</script>
</head>

<body onload="callCrawl2()">
	<h1>인터벌 test</h1>
	<div id="result"></div>


</body>
</html>
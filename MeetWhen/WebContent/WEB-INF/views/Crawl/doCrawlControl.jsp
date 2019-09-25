<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>DB format & create</title>
<style>
	 .tabb {
    width: 100%;
    border-top: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border-bottom: 1px solid #444444;
    padding: 10px;
    text-align: center;
    font-weight:bold;
  }
  .tabb {
    background-color: #bbdefb;
  }
</style>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
function callCrawlA1(){
	$(document).ready(function(){	
			$.ajax({
				type :"post",
				url : "/MeetWhen/Crawl/doCrawla1.mw",
				success : function(){
					$("#resultA1").text("실행 완료!");	}, 
				error : function(){
					$("#resultA1").text("실행 오류...!!");}
			});
	});
}
function callCrawlA2(){
	$(document).ready(function(){	
		$.ajax({
			type :"post",
			url : "/MeetWhen/Crawl/doCrawla2.mw",
			success : function(){
				$("#resultA2").text("실행 완료!");	}, 
			error : function(){
				$("#resultA2").text("실행 오류...!!");}
		});	
	});
}
function callCrawlB1(){
	$(document).ready(function(){	
			$.ajax({
				type :"post",
				url : "/MeetWhen/Crawl/doCrawlb.mw?dbNum=1",
				success : function(){
					$("#resultB1").text("실행 완료!");	}, 
				error : function(){
					$("#resultB1").text("실행 오류...!!");}
			});
	});
}
function callCrawlB2(){
	$(document).ready(function(){	
			$.ajax({
				type :"post",
				url : "/MeetWhen/Crawl/doCrawlb.mw?dbNum=2",
				success : function(){
					$("#resultB2").text("실행 완료!");	}, 
				error : function(){
					$("#resultB2").text("실행 오류...!!");}
			});
	});
}
function callCrawlB3(){
	$(document).ready(function(){	
			$.ajax({
				type :"post",
				url : "/MeetWhen/Crawl/doCrawlb.mw?dbNum=3",
				success : function(){
					$("#resultB3").text("실행 완료!");	}, 
				error : function(){
					$("#resultB3").text("실행 오류...!!");}
			});
	});
}
function callCrawlB4(){
	$(document).ready(function(){	
			$.ajax({
				type :"post",
				url : "/MeetWhen/Crawl/doCrawlb.mw?dbNum=4",
				success : function(){
					$("#resultB4").text("실행 완료!");	}, 
				error : function(){
					$("#resultB4").text("실행 오류...!!");}
			});
	});
}
function callCrawlB5(){
	$(document).ready(function(){	
			$.ajax({
				type :"post",
				url : "/MeetWhen/Crawl/doCrawlb.mw?dbNum=5",
				success : function(){
					$("#resultB5").text("실행 완료!");	}, 
				error : function(){
					$("#resultB5").text("실행 오류...!!");}
			});
	});
}
function callCrawlB6(){
	$(document).ready(function(){	
			$.ajax({
				type :"post",
				url : "/MeetWhen/Crawl/doCrawlb.mw?dbNum=6",
				success : function(){
					$("#resultB6").text("실행 완료!");	}, 
				error : function(){
					$("#resultB6").text("실행 오류...!!");}
			});
	});
}

	function StartCrowling() {
		var sec = 1000;
		var min = sec * 60;
		var hour = min * 60;

		callCrawlA1();
		setInterval(callCrawlA1, min*10);//10분  min*10
		callCrawlA2();
		setInterval(callCrawlA2, min*15);//20분   min*15

		callCrawlB1();
		setInterval(callCrawlB1,  hour);//1시간  hour
		
		callCrawlB2();
		setInterval(callCrawlB2,  hour+min*10); //1시간  10분 hour
		
		callCrawlB3();
		setInterval(callCrawlB3,  hour+min*15); //1시간  15분 hour
		
		callCrawlB4();
		setInterval(callCrawlB4,  hour+min*20); //1시간 20분  hour
		
		callCrawlB5();
		setInterval(callCrawlB5,  hour+min*25); //1시간 25분  hour
		
		callCrawlB6();
		setInterval(callCrawlB6,  hour+min*30); //1시간 30분 hour
	}
</script>

</head>

<body onload="StartCrowling()">
	<h3>DB 자동 리셋 및 생성 PAGE</h3>
	<table class="tabb">
		<tr>
			<td>세계지도의 기본 정보 (네이버):</td>
			<td><div id="resultA1"></div></td>
		</tr>
		<tr>
			<td>대륙지도의 기본 정보 (구글):</td>
			<td><div id="resultA2"></div></td>
		</tr>
		<tr>
			<td>기사 검색(전체):</td>
			<td><div id="resultB1"></div></td>
		</tr>
		<tr>
			<td>기사 검색(유럽):</td>
			<td><div id="resultB2"></div></td>
		</tr>
		<tr>
			<td>기사 검색(아프리카/중동):</td>
			<td><div id="resultB3"></div></td>
		</tr>
		<tr>
			<td>기사 검색(호주/아시아):</td>
			<td><div id="resultB4"></div></td>
		</tr>
		<tr>
			<td>기사 검색(북미):</td>
			<td><div id="resultB5"></div></td>
		</tr>
		<tr>
			<td>기사 검색(남미):</td>
			<td><div id="resultB6"></div></td>
		</tr>
	</table>

</body>
</html>
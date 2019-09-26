<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Crawling > DB format & create</title>
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
	alert("[CrawlA1 : 재 리셋&생성_15분 간격]");
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
	alert("[CrawlA2 : 재 리셋&생성_15분 간격]");
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
	alert("[CrawlB1 : 재 리셋&생성_30분 간격]");
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
	alert("[CrawlB2 : 재 리셋&생성_30m분 간격]");
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
	alert("[CrawlB3 : 재 리셋&생성_30분 간격]");
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
	alert("[CrawlB4 : 재 리셋&생성_30분 간격]");
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
	alert("[CrawlB5 : 재 리셋&생성_30분 간격]");
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
	alert("[CrawlB6 : 재 리셋&생성_30분 간격]");
}

	function StartCrowling() {
		var sec = 1000;
		var min = sec * 60;
		var hour = min * 60;
        msg=" First Format&Create 시작";
        
	
		alert("1분 뒤 부터 자동 크롤링을 시작합니다.");
		
		setTimeout(function(){
			alert("[CrawlA1"+msg+"]");
			callCrawlA1();
		},min*1) //1분뒤에 진행
		setInterval(callCrawlA1, min*15); 
		
		setTimeout(function(){
			alert("[CrawlA2"+msg+"]");
			callCrawlA2();
		},min*2+sec*30) //2분30초 뒤에 진행
		setInterval(callCrawlA2, min*15);

		
		setTimeout(function(){
			alert("[CrawlB1"+msg+"]");
			callCrawlB1();
		},min*5) //5분뒤에 진행 
		setInterval(callCrawlB1, min*30); 
		
		setTimeout(function(){
			alert("[CrawlB2"+msg+"]");
			callCrawlB2();
		},min*5+sec*15) //5분15초 뒤에 진행 
		setInterval(callCrawlB2,  min*30);  
		
		setTimeout(function(){
			alert("[CrawlB3"+msg+"]");
			callCrawlB3();
		},min*5+sec*30) //5분30초 뒤에 진행 
		setInterval(callCrawlB3,  min*30); 
		
		setTimeout(function(){
			alert("[CrawlB4"+msg+"]");
			callCrawlB4();
		},min*5+sec*45) //5분45초 뒤에 진행 
		setInterval(callCrawlB4,  min*30);  
		
		setTimeout(function(){
			alert("[CrawlB5"+msg+"]");
			callCrawlB5();
		},min*6) //6분 뒤에 진행  
		setInterval(callCrawlB5,  min*30); 
		
		setTimeout(function(){
			alert("[CrawlB6"+msg+"]");
			callCrawlB6();
		},min*6+sec*15) //6분15초 뒤에 진행
		setInterval(callCrawlB6,  min*30); 
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
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%--�ٸ� ������ ���ε� ��Ű�� ���? --%>
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
			$("#resultA1").text("���� �Ϸ�!");
		}
		else if(num==2){
			$("#resultA2").text("���� �Ϸ�!");
		}
		else if(num==3){
			$("#resultB").text("���� �Ϸ�!");
		}
				
	}
	function reqError(num){	
		if(num==1){
			$("#resultA1").text("���� ����...!!");
		}
		else if(num==2){
			$("#resultA2").text("���� ����...!!");
		}
		else if(num==3){
			$("#resultB").text("���� ����...!!");
		}
				
	}	
	
	function StartClock(){
		callCrawl2();
		setInterval(callCrawl2,60000);//1000=1��, 60000=1�� 
	}
</script>
</head>

<body onload="callCrawl2()">
	<h1>���͹� test</h1>
	<div id="resultA1"></div>
	<div id="resultA2"></div>
	<div id="resultB"></div>


</body>
</html>
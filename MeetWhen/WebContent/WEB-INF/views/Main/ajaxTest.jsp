<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%--�ٸ� ������ ���ε� ��Ű�� ���? --%>
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
	function test(){	$("#result").text("���� �Ϸ�!")		}
	function reqError(){	$("#result").text("���� ����...!!");			}	
	
	function StartClock(){
		callCrawl2();
		setInterval(callCrawl2,60000);//1000=1��, 60000=1�� 
	}

</script>
</head>

<body onload="callCrawl2()">
	<h1>���͹� test</h1>
	<div id="result"></div>


</body>
</html>
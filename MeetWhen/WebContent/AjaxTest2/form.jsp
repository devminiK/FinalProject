<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script>
		$(document).ready(function(){	
			$("#btn").click(function(){
				$.ajax({
					type :"post",
					url : "formPro.jsp",
					data : { user : $("#user").val() }, 
					success : test , 
					error : reqError
				});
			});
		});
		function test(data){	$("#result").html(data);		}
		function reqError(){	$("#result").text("실행 오류...!!");			}	
	</script>
    
	user : <input type="text" id="user" />
    	    <input type="button" value="전송"  id="btn"  /> 
    	    
    	    <div id="result"></div>
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script>
		$(document).ready(function(){		window.setInterval('time()', 1000);  });
		function time(){
			$.ajax({
				type : "post" ,  url : "time.jsp",
				success : function(data){
					$("#time").html(data);
				}
			});
		}
	</script>
    	<h2> ½Ã°£ : <label id="time"></label> </h2>
    	
    
    
    
    
    
    
    
    
    
    
    
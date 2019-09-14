<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- DB내용 이용해서 정보 보여주기.--%>
<%--
1. 엑셀 정규화 정보 저장DB
2. ggmap이용해 위도경도 추가한 DB 저장
 --%>
 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
.btn {
  border: none;
  color: white;
  padding: 14px 28px;
  font-size: 16px;
  cursor: pointer;
}

.success {background-color: #4CAF50;} /* Green */
.success:hover {background-color: #46a049;}

.info {background-color: #2196F3;} /* Blue */
.info:hover {background: #0b7dda;}

.warning {background-color: #ff9800;} /* Orange */
.warning:hover {background: #e68a00;}

.danger {background-color: #f44336;} /* Red */ 
.danger:hover {background: #da190b;}

</style>
<script type="text/javascript">
	/*
	$(document).ready(function(){
		$('button').click(function(){
			alert("클릭함"+$(this).val());
			});
	});
	*/
	
	// 삭제 전 다시 질문 하기
	function deleteInfo(num){
		var msg = confirm("해당 DB의 내용을 삭제하시겠습니까?");
		if(msg){
			alert("삭제를 진행합니다.");
			location.href="/MeetWhen/Db/dbDelete.mw?num="+num;
		}
		//else는 아무동작 x
	}
</script>
</head>
<body>
	<h1>DB-Control PAGE</h1>
	※ DB 정보 생성은 1부터 4까지 순차적으로 진행하셔야합니다.<br>
	※ DB2,4는 경도위도 정보를 geocode로 추출해내기때문에, 시간이 걸릴 수 있습니다.<br><br>
	
	<form id="createfrm" action="dbCreate.mw">
		<table>
			<tr>
				<th>DB정보 생성</th>
				<td><button name="num" value="1" id=crtDb class="btn success">DB1</button></td>
				<td><button name="num" value="2" id=crtDb class="btn success">DB2</button></td>
				<td><button name="num" value="3" id=crtDb class="btn success">DB3</button></td>
				<td><button name="num" value="4" id=crtDb class="btn success">DB4</button></td>
			</tr>
		</table>
	</form>

	<table>
		<tr>
			<th>DB내용 삭제</th>
			<td><button name="num" value="1" id=deleDb class="btn danger"
					onclick="deleteInfo(1)">DB1</button></td>
			<td><button name="num" value="2" id=deleDb class="btn danger"
					onclick="deleteInfo(2)">DB2</button></td>
			<td><button name="num" value="3" id=deleDb class="btn danger"
					onclick="deleteInfo(3)">DB3</button></td>
			<td><button name="num" value="4" id=deleDb class="btn danger"
					onclick="deleteInfo(4)">DB4</button></td>
		</tr>
	</table>

	<table>
		<tr>
			<th>DB내용 확인</th>
			<td><button name="num" value="1" id=checkDb class="btn info"
					onclick="fetchPage('dbInfoCheck.mw?num=1')">DB1</button></td>
			<td><button name="num" value="2" id=checkDb class="btn info"
					onclick="fetchPage('dbInfoCheck.mw?num=2')">DB2</button></td>
			<td><button name="num" value="3" id=checkDb class="btn info"
					onclick="fetchPage('dbInfoCheck.mw?num=3')">DB3</button></td>
			<td><button name="num" value="4" id=checkDb class="btn info"
					onclick="fetchPage('dbInfoCheck.mw?num=4')">DB4</button></td>
		</tr>
	</table>

	<article></article>
	<script>
    //onclick에 삽입되는 중복코드를 간결하게 하기위한, 함수 정의 
  	function fetchPage(name){
  		fetch(name).then(function(response){   
		    response.text().then(function(text){ 
		       document.querySelector('article').innerHTML = text;
		        
		       var cut =name.indexOf('?');
		       console.log(name.substr(cut));
		       console.log('RealPath=http://localhost:8080/MeetWhen/Db/dbInfoCheck.mw'+name.substr(cut));
		    })
		});
  	} 	
  </script>
</body>
</html>

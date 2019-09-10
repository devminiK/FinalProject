<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%-- DB내용 이용해서 정보 보여주기.--%>
<%--
1. 엑셀 정규화 정보 저장DB
2. ggmap이용해 위도경도 추가한 DB 저장
완성.
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
</script>
</head>
<body>

<h1>DB-Control PAGE</h1>
<h1>관리자가 DB생성 및 삭제 할수 있는 페이지</h1>
<form id="createfrm" action="dbCreate.mw">
		<table>
			<tr>
				<th>DB 정보 생성</th>
				<td><button name="num" value="1" id=crtDb class="btn success">DB1</button></td>
				<td><button name="num" value="2" id=crtDb class="btn success">DB2</button></td>
				<td><button name="num" value="3" id=crtDb class="btn success">DB3</button></td>
				<td><button name="num" value="4" id=crtDb class="btn success">DB4</button></td>
			</tr>
		</table>
	</form>
	
	<form id="deletefrm" action="dbDelete.mw">
		<table>
			<tr>
				<th>DB내용 삭제</th>
				<td><button name="num" value="1" id=deleDb class="btn danger">DB1</button></td>
				<td><button name="num" value="2" id=deleDb class="btn danger">DB2</button></td>
				<td><button name="num" value="3" id=deleDb class="btn danger">DB3</button></td>
				<td><button name="num" value="4" id=deleDb class="btn danger">DB4</button></td>
			</tr>
		</table>
	</form>
<%-- 

--%>
<%--
<button class="btn success">Success</button>
<button class="btn info">Info</button>
<button class="btn warning">Warning</button>
<button class="btn danger">Danger</button>
--%>
</body>
</html>

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
	function deleteInfo(num){
		var msg = confirm("해당 DB의 내용을 삭제하시겠습니까?");
		if(msg){
			alert("삭제를 진행합니다.");
			location.href="/MeetWhen/rjava/dbDelete.mw?num="+num;
		}
		//else는 아무동작 x
	}
</script>
</head>
<body>

<h1>DB-Control PAGE</h1>
<h1>관리자가 DB생성 및 삭제 할수 있는 페이지</h1>
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
				<td><button name="num" value="1" id=deleDb class="btn danger" onclick="deleteInfo(1)">DB1</button></td>
				<td><button name="num" value="2" id=deleDb class="btn danger" onclick="deleteInfo(2)">DB2</button></td>
				<td><button name="num" value="3" id=deleDb class="btn danger" onclick="deleteInfo(3)">DB3</button></td>
				<td><button name="num" value="4" id=deleDb class="btn danger" onclick="deleteInfo(4)">DB4</button></td>
			</tr>
		</table>

		<table>
			<tr>
				<th>DB내용 확인</th>
				<td><button name="num" value="1" id=checkDb class="btn info" onclick="fetchPage('dbInfoCheck.mw?num=1')">DB1</button></td>
				<td><button name="num" value="2" id=checkDb class="btn info" onclick="fetchPage('dbInfoCheck.mw?num=2')">DB2</button></td>
				<td><button name="num" value="3" id=checkDb class="btn info" onclick="fetchPage('dbInfoCheck.mw?num=3')">DB3</button></td>
				<td><button name="num" value="4" id=checkDb class="btn info" onclick="fetchPage('dbInfoCheck.mw?num=4')">DB4</button></td>
			</tr>
		</table>
	<%-- <li><a href="#!html.html" onclick="fetchPage('html.html')">HTML</a></li>--%>
	<article></article>
	
	<script>
    /*Ex01) onclick에 삽입되는 중복코드를 간결하게 하기위한, 함수 정의 */
  	function fetchPage(name){//변할수 있는 부분은 인자로 넘겨준다.
  		fetch(name).then(function(response){     // 서버에게 인자 파일을 요청
		    response.text().then(function(text){ // 서버가 응답해 준 데이터가 text에 저장된다.
		       document.querySelector('article').innerHTML = text;
		    })
		});
  	}
  	
 	/*Ex02)  북마크 기능이 존재할 때 / 아닐 때 
  	if(location.hash){	
  		fetchPage(location.hash.substr(2));
  	}else{
  		fetchPage('welcome.html');
  	}*/
  	
 	/*Ex03) list.html의 내용을 id nav에 뿌려주는 기능 추가
  	fetch('list.html').then(function(response){    
	    response.text().then(function(text){
			
	        
	        var items= text.split(',');
	        var i=0;
	        var tags='';
	        while(i<items.length){
	        	//<li><a href="#!html.html" onclick="fetchPage('html.html')">HTML</a></li>
	        	var item=items[i];
	        	console.log('item['+i+']='+items[i]);
	        	item = item.trim();
	        	var tag='<li><a herf="#!'+item+'.html'+'" onclick="fetchPage(\''+item+'.html\')">'+item+'</a></li>';
	        	tags=tags+tag;
	        	i=i+1;
	        }
	        document.querySelector('#nav').innerHTML = tags;
	        console.log(text);
	        console.log(items);
	    })
	});*/
  	
  </script>
<%--
<button class="btn success">Success</button>
<button class="btn info">Info</button>
<button class="btn warning">Warning</button>
<button class="btn danger">Danger</button>
--%>
</body>
</html>

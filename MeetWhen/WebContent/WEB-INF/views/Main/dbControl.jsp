<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%-- DB���� �̿��ؼ� ���� �����ֱ�.--%>
<%--
1. ���� ����ȭ ���� ����DB
2. ggmap�̿��� �����浵 �߰��� DB ����
�ϼ�.
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
			alert("Ŭ����"+$(this).val());
			});
	});
	*/
</script>
</head>
<body>

<h1>DB-Control PAGE</h1>
<h1>�����ڰ� DB���� �� ���� �Ҽ� �ִ� ������</h1>
<form id="createfrm" action="dbCreate.mw">
		<table>
			<tr>
				<th>DB ���� ����: ���� dB�� ����x�ϰ�� ������ ������ msg �߰�</th>
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
				<th>DB���� ����: �����Ҳ��Ĵ� �˾� Ȯ�� ���� �߰��ϱ�</th>
				<td><button name="num" value="1" id=deleDb class="btn danger">DB1</button></td>
				<td><button name="num" value="2" id=deleDb class="btn danger">DB2</button></td>
				<td><button name="num" value="3" id=deleDb class="btn danger">DB3</button></td>
				<td><button name="num" value="4" id=deleDb class="btn danger">DB4</button></td>
			</tr>
		</table>
	</form>
	
	<b>DB���� Ȯ��</b>
	<form id="deletefrm" action="dbInfoCheck.mw">
		<table>
			<tr>
				<th>DB���� Ȯ��: ����� �����ϰ���, ajax�� �����غ���</th>
				<td><button name="num" value="1" id=checkDb class="btn info">DB1</button></td>
				<td><button name="num" value="2" id=checkDb class="btn info">DB2</button></td>
				<td><button name="num" value="3" id=checkDb class="btn info">DB3</button></td>
				<td><button name="num" value="4" id=checkDb class="btn info">DB4</button></td>
			</tr>
		</table>
	</form>
	
	
	
	
	
	<%-- -
	<button class="btn info" onclick="fetchPage('dbInfoCheck.mw')">DB1</button>
	-%>
	<%-- <li><a href="#!html.html" onclick="fetchPage('html.html')">HTML</a></li>--%>
	<article></article>
	
	<script>
    /*Ex01) onclick�� ���ԵǴ� �ߺ��ڵ带 �����ϰ� �ϱ�����, �Լ� ���� */
  	function fetchPage(name){//���Ҽ� �ִ� �κ��� ���ڷ� �Ѱ��ش�.
  		fetch(name).then(function(response){     // �������� ���� ������ ��û
		    response.text().then(function(text){ // ������ ������ �� �����Ͱ� text�� ����ȴ�.
		       document.querySelector('article').innerHTML = text;
		    })
		});
  	}
  	
 	/*Ex02)  �ϸ�ũ ����� ������ �� / �ƴ� �� 
  	if(location.hash){	
  		fetchPage(location.hash.substr(2));
  	}else{
  		fetchPage('welcome.html');
  	}*/
  	
 	/*Ex03) list.html�� ������ id nav�� �ѷ��ִ� ��� �߰�
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

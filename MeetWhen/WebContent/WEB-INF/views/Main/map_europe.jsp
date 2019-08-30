<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--유럽 카테고리만 보여주기// 이걸로 하는 중 --%>
<!DOCTYPE html>
<html>
<head>

<!-- Load the google API -->
<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCexlJx5Gqv4JLwdSxZIeYwAE2IIRN_iGw"></script>
<script type="text/javascript">

<%--지도 대륙별 위도/경도 값, zoom정보 
	var myCenter = new google.maps.LatLng(25.086105, 179.581105);
	var EuPos = new google.maps.LatLng(54.577821, 15.175375); //zoom(4)
	var AfPos = new google.maps.LatLng(3.740811, 23.643909); //zoom(3)
	var MEPos
	var AsPos
	var OcePos
	var NAmePos = new google.maps.LatLng(48.604936, -108.279445) //zoom(3)
	var SAmePos = new google.maps.LatLng(-21.033396, -62.316496) //zoom(3)
--%>
	<%-- 
	var locations = new Array;
	
	<c:forEach var="a1" items="${infoList}" begin="0" step="1" end="${size}">
		var locations2= new Array;
		<c:forEach var="a2" items="${a1}" begin="0" step>
		<c:set var="info" value="${infos}"/>
			location
		${info.l_conreg} / ${info.l_lon} / ${info.l_lat}<br>
	</c:forEach>
--%>


	
	<%--맵을 초기화 하기 위한 함수--%>
	function initialize() {
		var mapOp = {
			center : new google.maps.LatLng(25.086105, 179.581105),
			zoom : 1.4,
			mapTypeId : google.maps.MapTypeId.ROADMAP, 
			disableDefaultUI:true,
			panControl:false,			
			mapTypeControl:false,
			streetViewControl:false,
			zoomControl:false
		};
		<%-- 맵 생성 객체 --%>
		var map = new google.maps.Map(document.getElementById("goMap"), mapOp);

		<%-- 마크 여러개 찍기 도전.--%>
		var marker,i;
		
		marker = new google.maps.Marker({
			position: myCenter,
			title:'Center of WorldMap'
		});
		marker.setMap(map);

		var infowindow = new google.maps.InfoWindow({
			  content:"Hello World!"
		});

		<%-- 클릭, 2zoom 
		google.maps.event.addListener(marker,'click',function(){
			map.setZoom(2);
			map.serCenter(marker.getPosition());
			//infowindow.open(map,marker);
		});--%>		

		<%-- 3초마다 마커로 이동 
		google.maps.event.addListener(map,'center_changed',function() {
			  window.setTimeout(function() {
			    map.panTo(marker.getPosition());
			  },60000);
		});--%>


	}
	<%-- 페이지가 로드될 때 initialize()함수 실행--%>
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>

<body>
	<div id="goMap" style="width: 1000px; height: 380px;"></div>
	
</body>
	<c:forEach var="it" items="${infoList}" begin="0" step="1" end="${size}">
		${it}
	</c:forEach>
	<hr>
	<c:forEach items="${infoList}" var="infos">
		<c:set var="info" value="${infos}"/>
		${info.l_conreg} / ${info.l_lon} / ${info.l_lat}<br>
	</c:forEach>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--test용, 추후 삭제할 것 --%>
<!DOCTYPE html>
<html>
<head>

<!-- 대륙 카테고리 버튼 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial;
}

/* Style the tab */
.tab {
	overflow: hidden;
	border: 1px solid #ccc;
	background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
	background-color: inherit;
	float: left;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 14px 16px;
	transition: 0.3s;
	font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
	background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
	background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
	display: none;
	padding: 6px 12px;
	border: 1px solid #ccc;
	border-top: none;
}
</style>

<!-- Load the google API -->
<script
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCexlJx5Gqv4JLwdSxZIeYwAE2IIRN_iGw"></script>

<!-- 
	//Roadmap(normal, default 2D map)
	//Satellite(photographic map)
	//Hybrid(photographic map + roads and city names)
	//Terrain(map with mountains, rivers, etc.)		
	//center맵의 중심이 어디일지 지정, LatLng맵에 특정 포인트 지정, zoom 줌레벨 정의
	
 -->
<script>
	var myCenter = new google.maps.LatLng(25.086105, 179.581105);
	var EuPos = new google.maps.LatLng(54.577821, 15.175375);

	<%--맵을 초기화 하기 위한 함수--%>
	function initialize() {
		var mapProp = {
			center : EuPos,
			zoom : 4,
			mapTypeId : google.maps.MapTypeId.ROADMAP,
			
			disableDefaultUI:true,
			panControl:false,			
			scaleControl:true,
			overviewMapControl:true,
			rotateControl:true,
			mapTypeControl:false,
			streetViewControl:false,
			zoomControl:false
		};
		<%-- 맵 생성 객체 --%>
		var map = new google.maps.Map(document.getElementById("goMap"),
				mapProp);

		<%-- zoom 하기위한 marker --%>
		var marker = new google.maps.Marker({
			position: EuPos,
			title:'Center of WorldMap'
		});
		marker.setMap(map);

		var infowindow = new google.maps.InfoWindow({
			  content:"Hello World!"
		});

	}
	<%-- 페이지가 로드될 때 initialize()함수 실행--%>
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>

<body>
	<script>
function openCity(evt, cityName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
}
</script>
	<h2>세계지도</h2>
	<p>관심있는 지역을 골라보세요:</p>



		<div id="goMap" style="width: 1000px; height: 380px;"/>

	
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--대륙 카테고리랑 전체 화면 보여주기 --%>
<!DOCTYPE html>
<html>
<head>
<!-- 대륙 카테고리 버튼 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial;}

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
<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCexlJx5Gqv4JLwdSxZIeYwAE2IIRN_iGw"></script>

<!-- 
	//Roadmap(normal, default 2D map)
	//Satellite(photographic map)
	//Hybrid(photographic map + roads and city names)
	//Terrain(map with mountains, rivers, etc.)		
	//center맵의 중심이 어디일지 지정, LatLng맵에 특정 포인트 지정, zoom 줌레벨 정의
	
 -->
<script>
	var myCenter = new google.maps.LatLng(25.086105, 179.581105);
	var EuPos = new google.maps.LatLng(54.577821, 15.175375); //zoom(4)
	var AfPos = new google.maps.LatLng(3.740811, 23.643909); //zoom(3)
	var MEPos
	var AsPos
	var OcePos
	var NAmePos = new google.maps.LatLng(48.604936, -108.279445) //zoom(3)
	var SAmePos = new google.maps.LatLng(-21.033396, -62.316496) //zoom(3)
	
	<%--맵을 초기화 하기 위한 함수--%>
	function initialize() {
		var mapProp = {
			center : myCenter,
			zoom : 1.4,
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
		var map = new google.maps.Map(document.getElementById("goMap"), mapProp);
		
		<%-- zoom 하기위한 marker --%>
		var marker = new google.maps.Marker({
			position: myCenter,
			title:'Center of WorldMap'
		});
		marker.setMap(map);
		var infowindow = new google.maps.InfoWindow({
			  content:"Hello World!"
		});
		<%-- 클릭, 2zoom --%>		
		google.maps.event.addListener(marker,'click',function(){
			map.setZoom(2);
			map.serCenter(marker.getPosition());
			//infowindow.open(map,marker);
		});
		<%-- 3초마다 마커로 이동 --%>
		google.maps.event.addListener(map,'center_changed',function() {
			  window.setTimeout(function() {
			    map.panTo(marker.getPosition());
			  },60000);
		});
	}
	<%-- 페이지가 로드될 때 initialize()함수 실행--%>
	google.maps.event.addDomListener(window, 'load', initialize);
</script>

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
//Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();
</script>

</head>
<body>

<h2>세계 지도</h2>
<p>관심있는 지역을 골라보세요:</p>

<div class="tab">
	<button class="tablinks" onclick="openCity(event, 'All')" id="defaultOpen">All</button>
  	<button class="tablinks" onclick="openCity(event, 'Europe')">Europe</button>
	<button class="tablinks" onclick="openCity(event, 'Africa')">Africa</button>
	<button class="tablinks" onclick="openCity(event, 'Middle-East')">Middle-East</button>
	<button class="tablinks" onclick="openCity(event, 'Asia')">Asia</button>
	<button class="tablinks" onclick="openCity(event, 'Oceania')">Oceania</button>
	<button class="tablinks" onclick="openCity(event, 'N-America')">N-America</button>
	<button class="tablinks" onclick="openCity(event, 'S-America')">S-America</button>
</div>

<div id="All" class="tabcontent">
		<h3>Africa</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
		<div id="goMap" style="width: 1000px; height: 380px;"></div>
	</div>
	
	<div id="Europe" class="tabcontent">
		<h3>Europe</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
	</div>

	<div id="Africa" class="tabcontent">
		<h3>Africa</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
	</div>

	<div id="Middle-East" class="tabcontent">
		<h3>Middle-East</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
	</div>

	<div id="Asia" class="tabcontent">
		<h3>Asia</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
	</div>

	<div id="Oceania" class="tabcontent">
		<h3>Oceania</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
	</div>

	<div id="N-America" class="tabcontent">
		<h3>N-America</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
	</div>

	<div id="S-America" class="tabcontent">
		<h3>S-America</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
	</div>

   
</body>
</html> 

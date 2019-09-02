<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--대륙 카테고리랑 전체 화면 보여주기 --%>
<!DOCTYPE html>
<html>
<head>
<!-- Abt category -->
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
/*for map*/
#map {
	height: 400px;
	width: 100%;
}
</style>
<!-- Abt google map -->
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCexlJx5Gqv4JLwdSxZIeYwAE2IIRN_iGw&callback=initMap"></script>
<script>
	function initMap() {
		var uluru = {
			lat : -25.363,
			lng : 131.044
		};
		var map = new google.maps.Map(document.getElementById('map'), {
			zoom : 4,
			center : uluru
		});
		var marker = new google.maps.Marker({
			position : uluru,
			map : map
		});
	}
</script>
<!-- Abt category -->
<script type="text/javascript" >
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

function start(){
	document.getElementById("defaultOpen").click();
}
</script>

</head>
<body onload="start()">

<h2>세계 지도</h2>
<p>관심있는 지역을 골라보세요:</p>

<div class="tab">
	<button class="tablinks" onclick="openCity(event, 'All')" id="defaultOpen">All</button>
  	<button class="tablinks" onclick="openCity(event, 'Europe')">Europe</button>
	<button class="tablinks" onclick="openCity(event, 'Africa'); initMap()">Africa</button>
	<button class="tablinks" onclick="openCity(event, 'Middle-East')">Middle-East</button>
	<button class="tablinks" onclick="openCity(event, 'Asia')">Asia</button>
	<button class="tablinks" onclick="openCity(event, 'Oceania')">Oceania</button>
	<button class="tablinks" onclick="openCity(event, 'N-America')">N-America</button>
	<button class="tablinks" onclick="openCity(event, 'S-America')">S-America</button>
</div>

<div id="All" class="tabcontent">
		<h3>세계지도</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
	</div>
	<div id="map"></div>
	
	<div id="Europe" class="tabcontent">
		<h3>[대륙]유럽</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
	</div>

	<div id="Africa" class="tabcontent">
		<h3>아프리카</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
		<div id="map"></div>
	</div>

	<div id="Middle-East" class="tabcontent">
		<h3>중동</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
	</div>

	<div id="Asia" class="tabcontent">
		<h3>아시아</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
	</div>

	<div id="Oceania" class="tabcontent">
		<h3>오세아니아</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
	</div>

	<div id="N-America" class="tabcontent">
		<h3>북 아메리카</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
	</div>

	<div id="S-America" class="tabcontent">
		<h3>남 아메리카</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
	</div>
   
</body>
</html> 

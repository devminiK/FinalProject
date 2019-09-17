<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--구글 지도 : 유럽 --%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/MeetWhen/css/category.css">
<style>
/*for map*/
#map {
	height: 400px;
	width: 100%;
}
</style>
<!-- Abt category -->
<script type="text/javascript">
	function openCity(evt, cityName) {
		var i, tabcontent, tablinks;
		tabcontent = document.getElementsByClassName("tabcontent");
		for (i = 0; i < tabcontent.length; i++) {
			tabcontent[i].style.display = "none";
		}
		tablinks = document.getElementsByClassName("tablinks");
		for (i = 0; i < tablinks.length; i++) {
			tablinks[i].className = tablinks[i].className
					.replace(" active", "");
		}
		document.getElementById(cityName).style.display = "block";
		evt.currentTarget.className += " active";

	}

	function start() {
		document.getElementById("defaultOpen").click();
	}
	function al() {
		alert('hi');
	}
</script>
<!-- Load the google API -->
<script
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCexlJx5Gqv4JLwdSxZIeYwAE2IIRN_iGw"></script>
<script type="text/javascript">
<%-- 배열 생성해보기. --%>
	var total = new Array;
	<c:forEach var="ent" items="${total}" begin="0" step="1" end="${listSize}">
	var eachRow = new Array;
	<c:forEach var="atrr" items="${ent}" begin="0" step="1" end="4">
	eachRow.push("${atrr}");
	</c:forEach>
	total.push(eachRow);
	</c:forEach>

<%--맵을 초기화 하기 위한 함수--%>
	function initialize() {
		var mapOp = {
			center : new google.maps.LatLng(32.714584, 124.181167), //아시아
			zoom : 3,
			mapTypeId : google.maps.MapTypeId.ROADMAP,
			disableDefaultUI : true,
			panControl : false,
			mapTypeControl : false,
			streetViewControl : false,
			zoomControl : false
		};
<%-- 맵 생성 객체 --%>
	var map = new google.maps.Map(document.getElementById("goMap"), mapOp);
		var infowindow = new google.maps.InfoWindow();
<%-- 모든 마크 찍기ㄴ--%>
	var marker, i;
		for (i = 0; i < total.length; i++) {
			var lat = total[i][2];
			var lon = total[i][1];

			marker = new google.maps.Marker({
				position : new google.maps.LatLng(lat, lon),
				map : map
			});

			google.maps.event.addListener(marker, 'click',
					(function(marker, i) {
						return function() {
							infowindow.setContent('<h2>' + total[i][0]
									+ '</h2>' + '<p>방문객 수:' + total[i][3]
									+ '</p>');
							infowindow.open(map, marker);
						}
					})(marker, i));

			if (marker) {
				marker.addListener('click', function() {
					map.setZoom(5);
					map.setCenter(this.getPosition());
				});
			}

		}
	}
<%-- 페이지가 로드될 때 initialize()함수 실행--%>
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body onload="start()">
	<h2>세계 지도</h2>
	<p>관심있는 지역을 골라보세요:</p>
	<div class="tab">
		<button onclick="location.href='cate1_All.mw'">All</button>
		<button onclick="location.href='cate2.mw'">Europe</button>
		<button onclick="location.href='cate3.mw'">Africa</button>
		<button onclick="location.href='cate4.mw'">Middle-East</button>
		<button onclick="openCity(event, 'contry')" id="defaultOpen">Asia</button>
		<button onclick="location.href='cate6.mw'">Oceania</button>
		<button onclick="location.href='cate7.mw'">N-America</button>
		<button onclick="location.href='cate8.mw'">S-America</button>
	</div>
	<div id="contry" class="tabcontent">
		<h3>[대륙]아시아</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
		<div id="goMap" style="width: 1000px; height: 500px;"></div>
	</div>
</body>
</html>

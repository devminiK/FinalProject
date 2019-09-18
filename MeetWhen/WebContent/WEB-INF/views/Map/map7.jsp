<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--구글 지도 : 북아메리카 --%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/MeetWhen/css/category.css">
<style>
/*for map*/
#goMap {
	height: 500px;
	width: 1000px;
}
/* iframe을 숨기기 위한 css
#if {
	width: 0px;
	height: 0px;
	border: 0px;
}*/
</style>
<!-- Load the google API -->
<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCexlJx5Gqv4JLwdSxZIeYwAE2IIRN_iGw"></script>

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
	function clickBtn() {
		document.getElementById("defaultOpen").click();
	}
</script>
<!-- Abt Map -->
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
			center : new google.maps.LatLng(48.604936, -108.279445), //남 아메리카
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

		};
		google.maps.event.addListener(map, 'click', function(event) {
			infowindow.close();
			map.setCenter(new google.maps.LatLng(48.604936, -108.279445));
			map.setZoom(3);
		});
	}
</script>
</head>
<body onload="clickBtn(); initialize()">
	<jsp:include page="/Main/boots_menubar.mw"/>
	<!-- Map -->
  	<section class="page-section" id="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading text-uppercase">World Map</h2>
					<h3 class="section-subheading text-muted">관심있는 지역을 클릭하여 방문자수를 파악해보세요</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<ul style="list-style: none">
						<li>
							<div class="tab">
								<button onclick="location.href='map1.mw'">All</button>
								<button onclick="location.href='map2.mw'">Europe</button>
								<button onclick="location.href='map3.mw'">Africa</button>
								<button onclick="location.href='map4.mw'">Middle-East</button>
								<button onclick="location.href='map5.mw'">Asia</button>
								<button onclick="location.href='map6.mw'">Oceania</button>
								<button onclick="openCity(event, 'contry')" id="defaultOpen">N-America</button>
								<button onclick="location.href='map8.mw'">S-America</button>
							</div>
							<div id="contry" class="tabcontent">
								<div id="goMap"></div>
							</div>
							<div id="contryName"></div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</section>
  <jsp:include page="/Main/boots_footer.mw"/>
</body>
</html>

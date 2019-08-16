<!DOCTYPE html>
<html>
<head>
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
		var map = new google.maps.Map(document.getElementById("goMap"),
				mapProp);

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
</head>

<body>
	<div id="goMap" style="width: 1000px; height: 380px;"></div>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--세계 지도 보여주기.--%>
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
		var infowindow = new google.maps.InfoWindow();
		
		<%-- 모든 마크 찍기ㄴ--%>
		var marker,i;
		for(i=0;i<total.length;i++){
			var lat = total[i][2];
			var lon = total[i][1];
			
			marker = new google.maps.Marker({
				position: new google.maps.LatLng(lat,lon),
				map: map
			});

			 google.maps.event.addListener(marker, 'click', (function(marker, i) {
			        return function() {
			          infowindow.setContent('<h2>'+total[i][0]+'</h2>'+
					          				'<p>방문객 수:'+total[i][3]+'</p>');
			          infowindow.open(map, marker);
			        }
			      })(marker, i));
			      
			      if(marker){
			        marker.addListener('click', function() {
			          map.setZoom(5);
			          map.setCenter(this.getPosition());
			        });
			      }
		
		}

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
	<hr>

</html>
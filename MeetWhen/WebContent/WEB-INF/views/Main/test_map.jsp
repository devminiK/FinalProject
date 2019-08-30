<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- 지환이가 준 코드: 참고 중--%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Marker Animations With setTimeout()</title>
  </head>
  <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCexlJx5Gqv4JLwdSxZIeYwAE2IIRN_iGw&callback=initMap">
    </script>
  <script type="text/javascript">
   //반복문으로 자바스크립트 배열내에 이름과 좌표를 넣어야한다.
   var locations = new Array;
   <c:forEach var="a1" items="${total}" begin="0" step="1" end="${buslistsize}">
   	var locations2 = new Array;   
    <c:forEach var="a2" items="${a1}" begin="0" step="1" end="3" varStatus="st">     
    	locations2.push(${a2});        
    </c:forEach>     
    locations.push(locations2);       
   </c:forEach>

   ////지도 표시부분 --------------------------------------
    var mapOptions = {
         center : new google.maps.LatLng(${myylat}, ${myxlat}), /* 지도에 보여질 위치 */             
         zoom : 16, /* 지도 줌 (0축소 ~ 18확대),  */    
         mapTypeId : google.maps.MapTypeId.ROADMAP
      };
    var map = new google.maps.Map(document.getElementById("map_canvas"),mapOptions);
    var infowindow = new google.maps.InfoWindow();

    ////마커 관련 옵션---------------------------------------
    var marker, i;

    ////전달받은 마커들을 전부 찍는 작업(맵에 표시 까지 하는 부분)
    for (i = 0; i < locations.length; i++) {  
      marker = new google.maps.Marker({
        position: new google.maps.LatLng(locations[i][2], locations[i][1]),
        map: map
      });

      google.maps.event.addListener(marker, 'click', (function(marker, i) {
        return function() {
          infowindow.setContent(locations[i][0]);
          infowindow.open(map, marker);
        }
      })(marker, i));
      
      if(marker){
        marker.addListener('click', function() {
          map.setZoom(16);
          map.setCenter(this.getPosition());
        });
        }
    } 
    
</script>
    
  <body>
    <div id="map_canvas"></div>
  </body>
</html>
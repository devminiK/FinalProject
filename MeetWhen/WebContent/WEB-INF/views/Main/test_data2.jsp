<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- DB���� �̿��ؼ� ���� �����ֱ�.--%>
<%--
1. DB�� ��ũ �Ѹ���

 --%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCexlJx5Gqv4JLwdSxZIeYwAE2IIRN_iGw&callback=initMap">
    </script>
<body>
	<script type="text/javascript">
   //�ݺ������� �ڹٽ�ũ��Ʈ �迭���� �̸��� ��ǥ�� �־���Ѵ�.
   var locations = new Array;
   <c:forEach var="a1" items="${total}" begin="0" step="1" end="${buslistsize}">
   var locations2 = new Array;
      <c:forEach var="a2" items="${a1}" begin="0" step="1" end="3" varStatus="st">
         locations2.push(${a2});
      </c:forEach>
      locations.push(locations2);   
   </c:forEach>

   ////���� ǥ�úκ� --------------------------------------
    var mapOptions = {
         center : new google.maps.LatLng(${myylat}, ${myxlat}), /* ������ ������ ��ġ */             
         zoom : 16, /* ���� �� (0��� ~ 18Ȯ��),  */    
         mapTypeId : google.maps.MapTypeId.ROADMAP
      };
    var map = new google.maps.Map(document.getElementById("map_canvas"),mapOptions);
    var infowindow = new google.maps.InfoWindow();

    ////��Ŀ ���� �ɼ�---------------------------------------
    var marker, i;

    ////���޹��� ��Ŀ���� ���� ��� �۾�(�ʿ� ǥ�� ���� �ϴ� �κ�)
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
      
      if(marker)
      {
        marker.addListener('click', function() {
          map.setZoom(16);
          map.setCenter(this.getPosition());
        });
        }
    } 
    
</script>
	<div id="map"></div>
	<c:forEach items="${infoList}" var="infos">
		<c:set var="info" value="${infos}"/>
		${info.l_conreg} / ${info.l_lon} / ${info.l_lat}<br>
	</c:forEach>
	
</body>
</html>
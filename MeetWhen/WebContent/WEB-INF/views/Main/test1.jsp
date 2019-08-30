<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- DB���� �̿��ؼ� ���� �����ֱ�.--%>

 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 50%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #floating-panel {
        position: absolute;
        top: 10px;
        left: 25%;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
        text-align: center;
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
      }
      #floating-panel {
        margin-left: -52px;
      }
    </style>
</head>

<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCexlJx5Gqv4JLwdSxZIeYwAE2IIRN_iGw&callback=initMap">
</script>
  <script>

      // If you're adding a number of markers, you may want to drop them on the map
      // consecutively rather than all at once. This example shows how to use
      // window.setTimeout() to space your markers' animation.

      var neighborhoods = [			//�浵, ���� ��ǥ �迭
        {lat: 52.511, lng: 13.447},
        {lat: 52.549, lng: 13.422},
        {lat: 52.497, lng: 13.396},
        {lat: 52.517, lng: 13.394}
      ];
      var markers = [];	//��ũ��
      var map;			//����
      

      function initMap() {/*���� �ʱ�ȭ*/
        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 1.4,//12
          center: {lat: 25.086105, lng: 179.581105} //{lat: 52.520, lng: 13.410}
        });
      }
		
      function drop() {/*��ũ�� ���� �߸�*/
        clearMarkers();	//��ũ�� �����Ѵ�.
        for (var i = 0; i < neighborhoods.length; i++) {	//�迭�� ���̸�ŭ �ݺ�
          addMarkerWithTimeout(neighborhoods[i], i * 200);	//���������� ��ũ���
        }
      }

      function addMarkerWithTimeout(position, timeout) {
        window.setTimeout(function() {
          markers.push(new google.maps.Marker({
            position: position,
            map: map,
            animation: google.maps.Animation.DROP //DROP:����߸�, BOUNCE:����ٱ�
          }));
        }, timeout);
      }

      function clearMarkers() {/*��ũ ����*/
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(null);
        }
        markers = [];
      }
    </script>
<body>
	<div id="floating-panel">
      <button id="drop" onclick="drop()">Drop Markers</button>
    </div>
    <div id="map"></div>
    
	<c:forEach items="${infoList}" var="infos">
		<c:set var="info" value="${infos}"/>
		${info.l_conreg} / ${info.l_lon} / ${info.l_lat}<br>
	</c:forEach>
	
</body>
</html>
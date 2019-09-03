<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--대륙 카테고리랑 전체 화면 보여주기 --%>
<!DOCTYPE html>
<html>
<head>
<!-- use Ajax -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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

function start(){//시작과 함께, 클릭되어있도록 설정
	document.getElementById("defaultOpen").click();
}
</script>
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
	}
	<%-- 페이지가 로드될 때 initialize()함수 실행--%>
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body onload="start()">

<h2>세계 지도</h2>
<p>관심있는 지역을 골라보세요:</p>

<div class="tab">
	<button class="tablinks" onclick="openCity(event, 'All')" id="defaultOpen">All</button>
  	<button class="tablinks" onclick="openCity(event, 'Europe');">Europe</button>
	<button class="tablinks" onclick="openCity(event, 'Africa'); ">Africa</button>
	<button class="tablinks" onclick="openCity(event, 'Middle-East')">Middle-East</button>
	<button class="tablinks" onclick="openCity(event, 'Asia')">Asia</button>
	<button class="tablinks" onclick="openCity(event, 'Oceania')">Oceania</button>
	<button class="tablinks" onclick="openCity(event, 'N-America')">N-America</button>
	<button onclick="openCity(event, 'S-America')">S-America</button>
</div>

<div id="All" class="tabcontent">
		<h3>세계지도</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
		<div id="goMap"></div>
	</div>
	
	
	<div id="Europe" class="tabcontent">
		<h3>[대륙]유럽</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
		
	</div>

	<div id="Africa" class="tabcontent">
		<h3>아프리카</h3>
		<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.</p>
		
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

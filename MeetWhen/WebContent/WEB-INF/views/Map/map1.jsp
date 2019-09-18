<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--구글 지도 : 전체 --%>
<!DOCTYPE html>
<html>
<head>
<%--BootStrap --%>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
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

	function start() {
		document.getElementById("defaultOpen").click();
	}
	function al() {
		alert('hi');
	}
</script>
<!-- Abt Map -->
<script type="text/javascript">
	<%-- 배열 생성--%>
	var total = new Array;
	<c:forEach var="ent" items="${total}" begin="0" step="1" end="${listSize}">
		var eachRow = new Array;
		<c:forEach var="atrr" items="${ent}" begin="0" step="1" end="4">
			eachRow.push("${atrr}");
		</c:forEach>
		total.push(eachRow);
	</c:forEach>
<%--맵을 초기화 하기 위한 함수--%>
	function initialize() {//28.650966, 152.910042
		var mapOp = {
			center : new google.maps.LatLng(28.650966, 152.910042), //whole map
			zoom : 2,
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
<%-- 모든 마크 찍기--%>
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
									+ '</h2>' + '<p>방문객 수:'+total[i][3]+'</p>');
							infowindow.open(map, marker);	

							//클릭한 나라값으로 검색해보기.-ing
							document.getElementById('contryName').innerHTML=total[i][0];//나라확인

							//var URL = "/MeetWhen/Main/test2.mw"+total[i][0];
							//window.location.href=URL;
						}
					})(marker, i));
			if (marker) {
				marker.addListener('click', function(i) {
					map.setZoom(5);
					map.setCenter(this.getPosition());
				});
			}
		};
		
		google.maps.event.addListener(map, 'click', function(event) {
			infowindow.close();
			map.setCenter(new google.maps.LatLng(28.650966, 152.910042));//whole map
			map.setZoom(2);
		});
	}

	
	<%-- 페이지가 로드될 때 initialize()함수 실행--%>
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
  <!-- Bootstrap core CSS -->
  <link href="/MeetWhen/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="/MeetWhen/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
  <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

  <!-- Custom styles for this template -->
  <link href="/MeetWhen/css/agency.min.css" rel="stylesheet">
</head>
<body id="page-top" onload="start()">
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="#page-top">Start Bootstrap</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav text-uppercase ml-auto">
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#services">Services</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#portfolio">Portfolio</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#about">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#team">Team</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#contact">Contact</a>
          </li>
          <li class="nav-item">
            <a class="nav-link " href="/MeetWhen/Main/cate1_All.mw">Map</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Header -->
  <header class="masthead">
    <div class="container">
      <div class="intro-text"></div>
    </div>
  </header>

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
          <ul style="list-style:none">
            <li>
              <div class="tab">
					<button class="tablinks" onclick="openCity(event, 'contry')" id="defaultOpen">All</button>
					<button class="tablinks" onclick="location.href='map2.mw'">Europe</button>
					<button class="tablinks" onclick="location.href='map3.mw'">Africa</button>
					<button class="tablinks" onclick="location.href='map4.mw'">Middle-East</button>
					<button class="tablinks" onclick="location.href='map5.mw'">Asia</button>
					<button class="tablinks" onclick="location.href='map6.mw'">Oceania</button>
					<button class="tablinks" onclick="location.href='map7.mw'">N-America</button>
					<button class="tablinks" onclick="location.href='map8.mw'">S-America</button>
				</div>

				<div id="contry" class="tabcontent">
					<div id="goMap"></div>
				</div>
				
				<div id="contryName"></div>
				
				<form action="/MeetWhen/Main/test2.mw" method="get" target="param">
					<input type="text" value="${total[i][0]}"/>
        			<input type="submit" value="제출"/>
    			</form>
    
    <!-- iframe 설정 -->
    <iframe id="if" name="param"></iframe>
	<script>
    	function ifun(msg){
      	  alert(msg);
    	}	
	</script>
	
            </li>
            <li class="timeline-inverted">
              <jsp:include page="/Main/test.mw"/>
            </li>
            <li>
              <div class="timeline-image">
                <img class="rounded-circle img-fluid" src="/MeetWhen/img/about/3.jpg" alt="">
              </div>
              <div class="timeline-panel">
                <div class="timeline-heading">
                  <h4>December 2012</h4>
                  <h4 class="subheading">Transition to Full Service</h4>
                </div>
                <div class="timeline-body">
                  <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt ut voluptatum eius sapiente, totam reiciendis temporibus qui quibusdam, recusandae sit vero unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
                </div>
              </div>
            </li>
            <li class="timeline-inverted">
              <div class="timeline-image">
                <img class="rounded-circle img-fluid" src="/MeetWhen/img/about/4.jpg" alt="">
              </div>
              <div class="timeline-panel">
                <div class="timeline-heading">
                  <h4>July 2014</h4>
                  <h4 class="subheading">Phase Two Expansion</h4>
                </div>
                <div class="timeline-body">
                  <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt ut voluptatum eius sapiente, totam reiciendis temporibus qui quibusdam, recusandae sit vero unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
                </div>
              </div>
            </li>
            <li class="timeline-inverted">
              <div class="timeline-image">
                <h4>Be Part
                  <br>Of Our
                  <br>Story!</h4>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </section>

  <!-- Footer -->
  <footer class="footer">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-md-4">
          <span class="copyright">Copyright &copy; MeetWhen 2019</span>
        </div>
        <div class="col-md-4">
          <ul class="list-inline social-buttons">
            <li class="list-inline-item">
              <a href="#">
                <i class="fab fa-twitter"></i>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#">
                <i class="fab fa-facebook-f"></i>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#">
                <i class="fab fa-linkedin-in"></i>
              </a>
            </li>
          </ul>
        </div>
        <div class="col-md-4">
          <ul class="list-inline quicklinks">
            <li class="list-inline-item">
              <a href="#">Privacy Policy</a>
            </li>
            <li class="list-inline-item">
              <a href="#">Terms of Use</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
  <!-- Bootstrap core JavaScript -->
  <script src="/MeetWhen/vendor/jquery/jquery.min.js"></script>
  <script src="/MeetWhen/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- Plugin JavaScript -->
  <script src="/MeetWhen/vendor/jquery-easing/jquery.easing.min.js"></script>
  <!-- Contact form JavaScript -->
  <script src="/MeetWhen/js/jqBootstrapValidation.js"></script>
  <script src="/MeetWhen/js/contact_me.js"></script>
  <!-- Custom scripts for this template -->
  <script src="/MeetWhen/js/agency.min.js"></script>

</body>


</html>

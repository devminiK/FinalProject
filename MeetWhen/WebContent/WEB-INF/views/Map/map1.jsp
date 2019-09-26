<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--구글 지도 : 전체 --%>
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
/*배경색 그레이*/
.bdColor{
	background-color:#ddd;
}
/* COLUM에 필요*/
/* Create four equal columns that floats next to each other */
.column1 {
  float: left;
  width: 70%;
  padding: 5px;
  height: 350px; /* Should be removed. Only for demonstration */
}
/* Create four equal columns that floats next to each other */
.column2 {
  float: left;
  width: 30%;
  padding: 10px;
  height: 350px; /* Should be removed. Only for demonstration */
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
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
	function initialize() {
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

			google.maps.event.addListener(marker, 'click',(function(marker, i) {
						return function() {
							infowindow.setContent('<h2>' + total[i][0]
									+ '</h2>' + '<p>방문객 수:'+total[i][3]+'</p>');
							infowindow.open(map, marker);	

							document.getElementById('contryName').innerHTML=total[i][0]; //title 나라출력
							//ajax 구현- crawl 1(o)
							$.ajax({
								type:"post",
								url: "/MeetWhen/Crawl/showCrawla1.mw",
								data:{cont : total[i][0] },
								success : showResult1,
								error : reqError(1)
							});
							
							//ajax 구현- crawlb(ing)
							$.ajax({
								type:"post",
								url: "/MeetWhen/Crawl/doShowCrawlc.mw",
								data:{cont : total[i][0] },
								success : showResult2,
								error : reqError(2)
							});		
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
	function showResult1(data){
		$("#result1").html(data);
	}
	function showResult2(data){
		$("#result2").html(data);
	}
	
	function reqError(num){
		var msg = "<h1>실행 오류!!</h1>";
		if(num==1){
			$("#result1").html(msg);
		}
		else if(num==2){
			$("#result2").html(msg);
		}
		else{
			$("#result3").html(msg);
		}
		
	}
	/*크롤링3에 대한 코드 */
	function getArticle(){
		$.ajax({
			type:"post",
			url: "/MeetWhen/Crawl/showCrawlb.mw",
			data:{dbNum : 1 },
			success : showResult3,
			error : reqError(3)
		});
	}
	function showResult3(data){
		$("#result3").html(data);
	}
	
	<%-- 페이지가 로드될 때 initialize()함수 실행--%>
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body class="bdColor" id="page-top" onload="clickBtn(); getArticle();">
	<jsp:include page="/Main/boots_menubar.mw"/>

  <!-- Map -->
  <section class="page-section" id="about">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 text-center">
          <h2 class="section-heading text-uppercase">World Map: <b id="contryName"></b></h2>
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
            </li>

            <li>
				<!-- click evnet 결과: 크롤링1,2 -->
				<section class="page-section">
					<div class="container">
						<div class="row">
							<div class="column1">
            					<div id="result2"></div>
							</div>
							<div class="column2" style="background-color: #ddd;">
								<div id="result1"></div>
							</div>
						</div>
					</div>
				</section>            	
            </li>

            
            <li><%-- 크롤링3) 세계 뉴스: map1페이지가 실행될때. --%>
            	<div id="result3"></div>
            </li>
            
            
            
            
            <li class="timeline-inverted">
              <%-- <jsp:include page="/Main/test.mw"/>--%>
            </li>
            <%-- 	
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
            --%>
          </ul>
        </div>
      </div>
    </div>
  </section>

  <jsp:include page="/Main/boots_footer.mw"/>
</body>
</html>

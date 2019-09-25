<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--크롤링3: 명소추천 :: 구현못해서 코드만 남겨둠. --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.mySlides {
	display: none
}

img {
	vertical-align: middle;
	index-z:10;
}

/* Slideshow container */
.slideshow-container {
	max-width: 500px;
	position: relative;
	margin: auto;
}

/* Next & previous buttons */
.prev, .next {
	cursor: pointer;
	position: absolute;
	top: 50%;
	width: auto;
	padding: 16px;
	margin-top: -22px;
	color: white;
	font-weight: bold;
	font-size: 18px;
	transition: 0.6s ease;
	border-radius: 0 3px 3px 0;
	user-select: none;
}

/* Position the "next button" to the right */
.next {
	right: 0;
	border-radius: 3px 0 0 3px;
}

/* On hover, add a black background color with a little bit see-through */
.prev:hover, .next:hover {
	background-color: rgba(0, 0, 0, 0.8);
}

/* Caption text */
.title {
	color: #f2f2f2;
	font-size: 15px;
	padding: 8px 12px;
	position: absolute;
	top: 8px;
	width: 100%;
	text-align: center;
}

.explain {
	color: #f2f2f2;
	font-size: 15px;
	padding: 8px 12px;
	position: absolute;
	bottom: 8px;
	width: 100%;
	text-align: center;
}

/* Number text (1/3 etc) */
.numbertext {
	color: #f2f2f2;
	font-size: 12px;
	padding: 8px 12px;
	position: absolute;
	top: 0;
}

/* The dots/bullets/indicators */
.dot {
	cursor: pointer;
	height: 15px;
	width: 15px;
	margin: 0 2px;
	background-color: #bbb;
	border-radius: 50%;
	display: inline-block;
	transition: background-color 0.6s ease;
}

.active, .dot:hover {
	background-color: #717171;
}

/* Fading animation */
.fade {
	-webkit-animation-name: fade;
	-webkit-animation-duration: 1.5s;
	animation-name: fade;
	animation-duration: 1.5s;
}

@
-webkit-keyframes fade {
	from {opacity: .4
}

to {
	opacity: 1
}

}
@
keyframes fade {
	from {opacity: .4
}

to {
	opacity: 1
}

}

/* On smaller screens, decrease text size */
@media only screen and (max-width: 300px) {
	.prev, .next, .text {
		font-size: 11px
	}
}
</style>
</head>
<body>

	<div class="slideshow-container">
	<h3>${clickCont}의추천 명소</h3>
	</div>
	<div class="slideshow-container">
		<%-- 
		<c:forEach items="${dataList}" var="ent" begin="0" step="1"	end="${dataSize}">
		<img src="${ent.imgSrc}" style="width: 100%">
		<div class="mySlides fade">
			<div class="numbertext">0 / 5</div>
			<img src="${ent.imgSrc}" style="width: 100%">
			<div class="title">${ent.place}</div>
			<div class="explain">${ent.explain}</div>
		</div>
		</c:forEach>
		
	--%>
		<div class="mySlides fade">
  <div class="numbertext">1 / 5</div>
  <img src="/MeetWhen/img/recom/recom1.jpg" style="width:100%; z-indix:10;">
  <div class="title">추천 장소 1</div>
  <div class="explain">장소 설명.장소 설명.장소 설명.</div>
</div>

<div class="mySlides fade">
  <div class="numbertext">2 / 5</div>
  <img src="/MeetWhen/img/recom/recom1.jpg" style="width:100%">
  <div class="title">장소이름</div>
  <div class="explain">장소 설명.장소 설명.장소 설명.</div>
</div>

<div class="mySlides fade">
  <div class="numbertext">3 / 5</div>
  <img src="/MeetWhen/img/recom/recom1.jpg" style="width:100%">
  <div class="title">장소이름</div>
  <div class="explain">장소 설명.장소 설명.장소 설명.</div>
</div>
<div class="mySlides fade">
  <div class="numbertext">4 / 5</div>
  <img src="/MeetWhen/img/recom/recom1.jpg" style="width:100%">
  <div class="title">장소이름</div>
  <div class="explain">장소 설명.장소 설명.장소 설명.</div>
</div>
<div class="mySlides fade">
  <div class="numbertext">5 / 5</div>
  <img src="/MeetWhen/img/recom/recom1.jpg" style="width:100%">
  <div class="title">장소이름</div>
  <div class="explain">장소 설명.장소 설명.장소 설명.</div>
</div>
	
		
		<a class="prev" onclick="plusSlides(-1)">&#10094;</a> <a class="next"
			onclick="plusSlides(1)">&#10095;</a>

	</div>
	<br>

	<div style="text-align: center">
		<span class="dot" onclick="currentSlide(1)"></span> 
		<span class="dot" onclick="currentSlide(2)"></span> 
		<span class="dot" onclick="currentSlide(3)"></span> 
		<span class="dot" onclick="currentSlide(4)"></span> 
		<span class="dot"	onclick="currentSlide(5)"></span>
	</div>
	
	<script>
		var slideIndex = 1;
		showSlides(slideIndex);

		function plusSlides(n) {
			showSlides(slideIndex += n);
		}

		function currentSlide(n) {
			showSlides(slideIndex = n);
		}

		function showSlides(n) {
			var i;
			var slides = document.getElementsByClassName("mySlides");
			var dots = document.getElementsByClassName("dot");
			if (n > slides.length) {
				slideIndex = 1
			}
			if (n < 1) {
				slideIndex = slides.length
			}
			for (i = 0; i < slides.length; i++) {
				slides[i].style.display = "none";
			}
			for (i = 0; i < dots.length; i++) {
				dots[i].className = dots[i].className.replace(" active", "");
			}
			slides[slideIndex - 1].style.display = "block";
			dots[slideIndex - 1].className += " active";
		}
	</script>



	
	<%-- 
	<c:forEach items="${dataList}" var="ent" begin="0" step="1"
		end="${dataSize}">
		<div onclick="location.href='${ent.href}';">
		<h4>${ent.place} </h4>
		<p>: ${ent.explain}</p>
		<img src="${ent.imgSrc}" height=200px;/>
		</div>
	</c:forEach>
	--%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--크롤링3: 세계 뉴스(크롤링소요시간때문에 이미지 대체, 추후에 변경할 것)  --%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>crawling 3</title>
</head>
<style>
/* Create four equal columns that floats next to each other */
.column {
  float: left;
  width: 25%;
  padding:10px;
  height: 230px; /* Should be removed. Only for demonstration */
  text-align:center;
  
  background-color:white;
  position:relative;
}
/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}
/*클릭 하기 전 hovor 효과 */
.overlay {
  position: absolute; 
  bottom: 0; 
  background: rgb(0, 0, 0);
  background: rgba(0, 0, 0, 0.5); /* Black see-through */
  color: #f1f1f1; 
  width: 100%;
  transition: .5s ease;
  opacity:0;
  color: white;
  font-size: 20px;
  text-align: center;
  padding: 35px;
  height:100px;
}
.column:hover .overlay {
  opacity: 1;
}

.newsImg{
	width:250px;
	height:145px;
}
.newTitle{
	width: 100%;
	white-space:nowrap; /*넘어가는 줄 없음*/
	overflow:hidden;
	text-overflow:ellipsis; /*생략단어는 ...로 대체*/
}
</style>

<body>
	<div class="row">
  		<div class="column" onclick="location.href='${topURL}'">
    		<img src="/MeetWhen/img/article/newsLogo.jpg" width=195px; />
    		 <div class="overlay newTitle">
				<c:if test="${dbNum==1}">세계 뉴스로 이동</c:if>
				<c:if test="${dbNum==2}">유럽 뉴스로 이동</c:if>
				<c:if test="${dbNum==3}">아프리카&중동 뉴스로 이동</c:if>
				<c:if test="${dbNum==4}">호주&아시아 뉴스로 이동</c:if>
				<c:if test="${dbNum==5}">북미&미국 뉴스로 이동</c:if>
				<c:if test="${dbNum==6}">남미 뉴스로 이동</c:if>
			</div>
  		</div> 		
		<c:forEach items="${allList}" var="ent" begin="0" step="1" end="15">
  			<div class="column"  onclick="location.href='${ent.cwb_url}'">
    			<img class="newsImg" src="${ent.cwb_img}" />
    			<div class="overlay" >
    				<p class=" newTitle">${ent.cwb_title}</p>
    			</div>
  			</div>
  		</c:forEach>
	</div>			
</body>
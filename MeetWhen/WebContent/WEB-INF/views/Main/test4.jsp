<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="/MeetWhen/css/category.css">
<style>
/* Create four equal columns that floats next to each other */
.column1 {
  float: left;
  width: 70%;
  padding: 10px;
  height: 300px; /* Should be removed. Only for demonstration */
}
/* Create four equal columns that floats next to each other */
.column2 {
  float: left;
  width: 30%;
  padding: 10px;
  height: 300px; /* Should be removed. Only for demonstration */
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}
</style>
</head>
<body>

	<jsp:include page="/Main/boots_menubar.mw" />
	
	<!-- click evnet 결과: 크롤링1,2 -->
	<section class="page-section">
		<div class="container">
			<div class="row">
					<div class="column1" style="background-color: #aaa;">
						<h2>Column 1</h2>
						<p>Some text..</p>
					</div>
					<div class="column2" style="background-color: #bbb;">
						<h2>Column 2</h2>
						<p>Some text..</p>
					</div>
				</div>
		</div>
	</section>

	<jsp:include page="/Main/boots_footer.mw" />
</body>
</html>
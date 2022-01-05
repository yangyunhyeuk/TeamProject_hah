<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HAH_contact</title>
<mytag:icon />
<!--  googlemap 지도 API등록  -->
<script src="http://maps.google.com/maps/api/js?key=API인증키&region=kr"></script>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
</head>
<body class="is-preload">
	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<mytag:Header />

				<!-- Content -->
				<section>
				
					<!-- 지도API in-->
					<div>
						<article>
							<span class="image" id="map" style="width: 100%;"></span>
						</article>
						<article>
							<div class="mapInfo">
								<h2>Contact</h2>
								
								<!-- <span class="contact">Contact</span><br> -->
								<span><b>주소 : </b> 서울특별시 강남구 테헤란로 146 현익빌딩 3층, 4층 (신한은행건물)</span><br>
								<span>📞 <b>대표전화 : </b>02-538-0021</span><br><br>
								<span class="contactVehicle"><b>[대중교통]</b></span><br>
								<span>🚇<b>지하철 : </b>2호선 역삼역 3번출구(바로연결)</span><br>
								<span>🚌 <b>버스 </b></span><br>
								<span><b style="color: royalblue">Blue : </b> 146, 341, 360, 740, N13, N61, 147, 463</span><br>
								<span><b style="color: green">Green : </b> 4211</span><br>
								<span><b style="color: firebrick">Red : </b> 1100, 1700, 2000, 2000-1, 7007, 8001, 3600, 9600</span>
							</div>
						</article>
					</div>
					<br>
					<br>
					<br>
					<br>
					<hr class="major" />
					
					<!-- Form -->
					<h2>Q&#38;A</h2>
					<form method="post" action="qamail.do">
						<input type="hidden" name="to" value="ksh02190@gmail.com" />
						<div class="row gtr-uniform">
							<div class="col-6 col-12-xsmall">
								<input type="text" name="subject" id="demo-name" placeholder="성함 or 닉네임" />
							</div>
							<div class="col-6 col-12-xsmall">
								<input type="email" name="from" id="demo-email" placeholder="답변받을 이메일" />
							</div>
							<div class="col-12">
								<textarea name="content" id="demo-message" class="QaMailBox" placeholder="문의 내용" rows="6"></textarea>
							</div>
							<div class="col-12">
								<div class="mailButton">
									<input id="qamail" type="submit" value="문의하기" class="primary" />
									<input type="reset" class="primary" value="Reset" />
								</div>
							</div>
						</div>
					</form>
				</section>
			</div>
		</div>

		<!-- Sidebar -->
		<mytag:SideBar />
	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>
	<!-- contact.js 등록  -->
	<script src="assets/js/contact.js"></script>
	
</body>
</html>
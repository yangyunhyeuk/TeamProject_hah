<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Higher And Higher</title>
<mytag:icon />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />

<link rel="stylesheet"
	href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />

<script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>
</head>
<body class="is-preload">
	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<mytag:Header />
				<section id="banner">
					<div class="content" style="margin-top: 100px;">
						<header>
						<h1>
							<strong style="color : #f56a6a">HigherAndHigher</strong>
						</h1>
						<br>
							<p>Success is the ability to go from one failure to another with no loss of enthusiasm</p>	
						</header>
					</div>
					<div class="image object">
						<div class="swiper">
							<span class="swiper-wrapper"> <span class="swiper-slide"><img src="images/googoo1.png" alt="goo1" class="googoo" /></span> <span
								class="swiper-slide"><img src="images/googoo2.png" alt="goo2" class="googoo" /></span> <span class="swiper-slide">
								<img src="images/googoo3.png" alt="goo3" class="googoo" /></span> <span
								class="swiper-slide"><img src="images/googoo4.png"
									alt="goo4" class="googoo" /></span>
							</span>
						</div>
					</div>
				</section>
	

				<!-- Section -->
				<section>
					<header class="major">
						<h2>New</h2>
					</header>
					<div class="posts">
						<article>
							<h3 style="display: inline-block">NoticeBoard</h3>
							<span style="float: right"><a
								href="pLists.do?category=Notice">more+</a></span>
							<table>
								<tbody>
									<c:forEach var="v" items="${noticePnum}" varStatus="r" end="4"
										step="1">
										<tr>
											<td>${v.category}</td>
											<td><a
												href="pCnt.do?category=${v.category}&pnum=${v.pnum}">${v.ptitle}</a></td>
											<td>${v.cnt}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</article>
						<article>
							<h3 style="display: inline-block">FreeBoard</h3>
							<span style="float: right"><a
								href="pLists.do?category=Free">more+</a></span>
							<table>
								<tbody>
									<c:forEach var="v" items="${freePnum}" varStatus="r" end="4"
										step="1">
										<tr>
											<td>${v.category}</td>
											<td><a
												href="pCnt.do?category=${v.category}&pnum=${v.pnum}">${v.ptitle}</a></td>
											<td>${v.cnt}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</article>

						<article>
							<h3 style="display: inline-block">StudyBoard</h3>
							<span style="float: right"><a
								href="pLists.do?category=Study">more+</a></span>
							<table>
								<tbody>
									<c:forEach var="v" items="${studyPnum}" varStatus="r" end="4"
										step="1">
										<tr>
											<td>${v.category}</td>
											<td><a
												href="pCnt.do?category=${v.category}&pnum=${v.pnum}">${v.ptitle}</a></td>
											<td>${v.cnt}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</article>
					</div>
				</section>
				<section>
					<header class="major">
						<h2>Popular</h2>
					</header>
					<div class="posts">
						<article>
							<h3 style="display: inline-block">NoticeBoard</h3>
							<span style="float: right"><a
								href="pLists.do?category=Notice">more+</a></span>
							<table>
								<tbody>
									<c:forEach var="v" items="${noticeCnt}" varStatus="r" end="4"
										step="1">
										<tr>
											<td>${v.category}</td>
											<td><a
												href="pCnt.do?category=${v.category}&pnum=${v.pnum}">${v.ptitle}</a></td>
											<td>${v.cnt}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</article>
						<article>
							<h3 style="display: inline-block">FreeBoard</h3>
							<span style="float: right"><a
								href="pLists.do?category=Free">more+</a></span>
							<table>
								<tbody>
									<c:forEach var="v" items="${freeCnt}" varStatus="r" end="4"
										step="1">
										<tr>
											<td>${v.category}</td>
											<td><a
												href="pCnt.do?category=${v.category}&pnum=${v.pnum}">${v.ptitle}</a></td>
											<td>${v.cnt}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</article>

						<article>
							<h3 style="display: inline-block">StudyBoard</h3>
							<span style="float: right"><a
								href="pLists.do?category=Study">more+</a></span>
							<table>
								<tbody>
									<c:forEach var="v" items="${studyCnt}" varStatus="r" end="4"
										step="1">
										<tr>
											<td>${v.category}</td>
											<td><a
												href="pCnt.do?category=${v.category}&pnum=${v.pnum}">${v.ptitle}</a></td>
											<td>${v.cnt}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</article>
					</div>
				</section>
			</div>
		</div>

		<!-- Sidebar -->
		<mytag:SideBar />
	</div>
	
	<script type="text/javascript">
	const swiper = new Swiper('.swiper', {
		// Optional parameters
		direction : 'vertical',
		loop : true,

		// If we need pagination
		pagination : {
			el : '.swiper-pagination',
		},

		// Navigation arrows
		navigation : {
			nextEl : '.swiper-button-next',
			prevEl : '.swiper-button-prev',
		},

		// And if we need scrollbar
		scrollbar : {
			el : '.swiper-scrollbar',
		},
		
		autoplay: {
			delay: 1000,
		},
	});
</script>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

</body>
</html>
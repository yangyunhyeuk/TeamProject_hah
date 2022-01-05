<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HAH_write</title>
<mytag:icon />
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
				<section>
					<h3>New Post</h3>
					<form method="post" action="pInsert.do" id="postWriteForm">
						<input type="hidden" name="category" value="${param.category}">
						<input type="hidden" name="mid" value="${mem}">
						<table class="alt" id="writeForm">
							<tr>
								<td><input type="text" min="1" name="ptitle" placeholder="제목" id="titleInputCheck"></td>
							</tr>
							<tr>
							
								<!--  write.js스크립트 resize function수행  -->
								<td>
								<textarea class="autosize" onkeydown="resize(this)" placeholder="내용" onkeyup="resize(this)" name="pcontent" id="contentInputCheck"></textarea>
								</td>
							</tr>
						</table>
						<div align="right">
							<input type="submit" id="write" class="button primary" value="등록">
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
	<!-- write.js 등록 -->
	<script src="assets/js/write.js"></script>
	<script src="assets/js/textLengCheck.js"></script>
	<script src="assets/js/resize.js"></script>

</body>
</html>
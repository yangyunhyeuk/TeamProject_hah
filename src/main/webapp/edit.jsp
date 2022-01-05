<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HAH_edit</title>
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
					<h3>Edit Post</h3>
					<form method="post" action="pUpdate.do" id="postWriteForm">
						<input type="hidden" name="category" value="${post.category}">
						<input type="hidden" name="pnum" value="${post.pnum}">
						<table class="alt" id="writeForm">
							<tr>
								<!-- <td>제목</td> -->
								<td><input type="text" name="ptitle" placeholder="제목" id="titleInputCheck" value="${post.ptitle}"></td>
							</tr>
							<tr>
								<!-- <td>내용</td> -->
								<td>
								<textarea name="pcontent" onkeyup="resize(this)" onkeydown="resize(this)" placeholder="내용" id="contentInputCheck" class="autosize">${post.pcontent}</textarea>
								</td>
							</tr>
						</table>
						<div align="right">
							<input type="submit" class="button primary" id="edit" value="수정">
							<a href="pDelete.do?category=${post.category}&pnum=${post.pnum}" class="button primary" id="delete">삭제</a>
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
	<script src="assets/js/write.js"></script>
	<script src="assets/js/edit.js"></script>
	<script src="assets/js/textLengCheck.js"></script>
	<script src="assets/js/resize.js"></script>

</body>
</html>
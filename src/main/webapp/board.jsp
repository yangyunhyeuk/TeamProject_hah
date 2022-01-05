<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HAH_board</title>
<mytag:icon/>
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
				<mytag:Header/>
					<section>
					<div style="display:inline-block;">
					<h1>${category}board</h1>
					</div>

					<!-- 글쓰기 -->
					<div id="boardBtn">
					<p align="right">
						<c:if test="${! empty mem}">
							<c:choose>
								<c:when test="${category eq 'Notice'}">
									<c:if test="${mem eq 'admin'}">
										<a href="write.jsp?category=Notice" class="button">공지 작성</a>
									</c:if>
								</c:when>
								<c:otherwise>
									<a href="write.jsp?category=${category}" class="button">글쓰기</a>
								</c:otherwise>
							</c:choose>
						</c:if>
					</p>
					</div>
					<c:choose>
					<c:when test="${empty list}">
						<div align="center">
							<br><br>
							<h4>게시글이 존재하지 않습니다</h4>
							<br><br><br><br>
						</div>
					</c:when>
					<c:otherwise>
					<table>
						<thead>
						<tr>
							<th class="thText">번호</th>
							<th class="thText">카테고리</th>
							<th class="thText">제목</th>
							<th class="thText">작성자</th>
							<th class="thText">작성일</th>
							<th class="thText">조회</th>
							<th class="thText">댓글</th>
							
						</tr>
						</thead>
						<tbody>
						<c:forEach var="v" items="${list}">
								<tr>
									<td class="thText">${v.pnum}</td>
									<td class="thText">${v.category}</td>
									<td class="thText"><a href="pCnt.do?category=${v.category}&pnum=${v.pnum}">${v.ptitle}</a></td>
									<td class="thText">${v.mname}</td>
									<td class="thText">${v.pdate}</td>
									<td class="thText">${v.cnt}</td>
									<td class="thText">${v.replyCnt}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<!-- pagination -->
					<div class="paging toolbar-bottom" id="paging">
						<div class="toolbar mt-lg">
							<ul class="pagination">
								<c:if test="${search == 'dd'}">
									<c:if test="${paging.pageNo==paging.firstPageNo}">
										<li><a href="pLists.do?category=${category}&page=${paging.prevPageNo}" class="button disabled">prev</a></li>
									</c:if>
									<c:if test="${paging.pageNo!=paging.firstPageNo}">
										<li><a href="pLists.do?category=${category}&page=${paging.prevPageNo}" class="button">prev</a></li>
									</c:if>
									<c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
										<c:choose>
											<c:when test="${i eq paging.pageNo}">
												<li><a class="page active">${i}</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="search.do?category=${category}&page=${i}&condition=${condition}&content=${content}" class="page">${i}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${paging.pageNo==paging.finalPageNo}">
										<li><a href="pLists.do?category=${category}&page=${paging.nextPageNo}" class="button disabled">next</a></li>
									</c:if>
									<c:if test="${paging.pageNo!=paging.finalPageNo}">
										<li><a href="pLists.do?category=${category}&page=${paging.nextPageNo}" class="button">next</a></li>
									</c:if>
								</c:if>
								<c:if test="${search != 'dd'}">
									<c:if test="${paging.pageNo==paging.firstPageNo}">
										<li><a href="pLists.do?category=${category}&page=${paging.prevPageNo}" class="button disabled">prev</a></li>
									</c:if>
									<c:if test="${paging.pageNo!=paging.firstPageNo}">
										<li><a href="pLists.do?category=${category}&page=${paging.prevPageNo}" class="button">prev</a></li>
									</c:if>
									<c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
										<c:choose>
											<c:when test="${i eq paging.pageNo}">
												<li><a class="page active">${i}</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="pLists.do?category=${category}&page=${i}" class="page">${i}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								<c:if test="${paging.pageNo==paging.finalPageNo}">
									<li><a href="pLists.do?category=${category}&page=${paging.nextPageNo}" class="button disabled">next</a></li>
								</c:if>
								<c:if test="${paging.pageNo!=paging.finalPageNo}">
									<li><a href="pLists.do?category=${category}&page=${paging.nextPageNo}" class="button">next</a></li>
								</c:if>
								</c:if>
							</ul>
						</div>
					</div>
					</c:otherwise>
					</c:choose>

					<!-- Search -->
					<div style="text-align:-webkit-center">
					<form method="post" action="search.do" style="width:fit-content">
						<input type="hidden" name="category" value="${category}">
						<table>
							<tr>
								<td><select name="condition" id="demo-category">
										<option value="ptitle" selected>Title</option>
										<option value="pcontent">Content</option>
										<option value="mname">NickName</option>
								</select></td>
								<td><input type="text" name="content" id="demo-name" placeholder="검색할 내용을 입력하세요!" /></td>
								<td><input type="submit" value="Search" class="button"></td>
							</tr>
						</table>
					</form>
					</div>
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

</body>
</html>
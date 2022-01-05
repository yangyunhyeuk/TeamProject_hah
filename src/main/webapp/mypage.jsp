<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HAH_mypage</title>
<mytag:icon />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/login.css" />
</head>
<body class="is-preload">
	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<mytag:Header />

				<!-- Section -->
				<header class="major">
					<br>
					<h2>My Page</h2>
				</header>
				<div class="posts">
					<article>
						<ul class="actions">
							<c:choose>
								<c:when test="${category eq 'myBoard'}">
									<li><a href="myPage.do?category=myBoard" class="button primary large">내 글</a></li>
									<li><a href="myPage.do?category=checkUser" class="button large">회원정보수정</a></li>
									<li><a href="myFav.do?category=favBoard" class="button large">관심글</a></li>
								</c:when>
								<c:when test="${category eq 'checkUser'}">
									<li><a href="myPage.do?category=myBoard" class="button large">내 글</a></li>
									<li><a href="myPage.do?category=checkUser" class="button primary large">회원정보수정</a></li>
									<li><a href="myFav.do?category=favBoard" class="button large">관심글</a></li>
								</c:when>
								<c:when test="${category eq 'favBoard'}">
									<li><a href="myPage.do?category=myBoard" class="button large">내 글</a></li>
									<li><a href="myPage.do?category=checkUser" class="button large">회원정보수정</a></li>
									<li><a href="myFav.do?category=favBoard" class="button primary large">관심글</a></li>
								</c:when>
							</c:choose>
						</ul>
						<c:if test="${category=='myBoard'}">
							<ul class="actions">
								<c:if test="${stat eq 'Study'}">
									<li><a href="myPage.do?category=myBoard&stat=Free" class="button">Free</a></li>
									<li><a href="myPage.do?category=myBoard&stat=Study" class="button primary">Study</a></li>
								</c:if>
								<c:if test="${stat eq 'Free'}">
									<li><a href="myPage.do?category=myBoard&stat=Free" class="button primary">Free</a></li>
									<li><a href="myPage.do?category=myBoard&stat=Study" class="button">Study</a></li>
								</c:if>
							</ul>
						</c:if>
					</article>
				</div>
				<section>
					<c:if test="${category=='myBoard'}">
						<c:if test="${empty list}">
							<table>
								<thead>
									<tr>
										<th style="text-align: center;">작성한 게시글이 없습니다.</th>
									</tr>
								</thead>
							</table>
						</c:if>
						<c:if test="${! empty list}">
							<table>
								<thead>
									<tr>
										<th class="thText">번호</th>
										<th class="thText">카테고리</th>
										<th class="thText">제목</th>
										<th class="thText">작성자</th>
										<th class="thText">작성일</th>
										<th class="thText">조회</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="v" items="${list}" varStatus="r">
										<tr>
											<td class="thText">${v.pnum}</td>
											<td class="thText">${v.category}</td>
											<td class="thText"><a href="pCnt.do?category=${v.category}&pnum=${v.pnum}">${v.ptitle}</a></td>
											<td class="thText">${v.mname}</td>
											<td class="thText">${v.pdate}</td>
											<td class="thText">${v.cnt}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="toolbar-bottom" id="paging">
								<div class="toolbar mt-lg">
									<ul class="pagination">
										<c:if test="${paging.pageNo==paging.firstPageNo}">
											<li>
											<a href="myPage.do?category=${category}&stat=${stat}&page=${paging.prevPageNo}" class="button disabled">prev</a>
											</li>
										</c:if>
										<c:if test="${paging.pageNo!=paging.firstPageNo}">
											<li>
											<a href="myPage.do?category=${category}&stat=${stat}&page=${paging.prevPageNo}" class="button">prev</a>
											</li>
										</c:if>
										<c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
											<c:choose>
												<c:when test="${i eq paging.pageNo}">
													<li><a class="page active">${i}</a></li>
												</c:when>
												<c:otherwise>
													<li><a href="myPage.do?category=${category}&stat=${stat}&page=${i}" class="page">${i}</a></li>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<c:if test="${paging.pageNo==paging.finalPageNo}">
											<li>
											<a href="myPage.do?category=${category}&stat=${stat}&page=${paging.nextPageNo}" class="button disabled">next</a>
											</li>
										</c:if>
										<c:if test="${paging.pageNo!=paging.finalPageNo}">
											<li>
											<a href="myPage.do?category=${category}&stat=${stat}&page=${paging.nextPageNo}" class="button">next</a>
											</li>
										</c:if>
									</ul>
								</div>
							</div>
						</c:if>
					</c:if>
					<c:if test="${category=='checkUser'}">
						<c:if test="${check=='t' }">
							<form action="editUser.do" method="post" class="input-group">
								<input type="hidden" name="mid" value="${mem}">
								<table>
									<tr>
										<td>아이디</td>
										<td colspan="2">${mem}</td>
									</tr>
									<tr>
										<td>비밀번호</td>
										<td colspan="2">
										<input type="password" name="mpw" id="pwd" class="input-field password" value="${data.mpw}">
										</td>
									</tr>
									<tr>
										<td>닉네임</td>
										<td>
											<input type="text" style="width: 200px;" name="mname" id="mname" class="input-field" value="${data.mname}" required>
											<input type="hidden" id="mymname" value="${data.mname}" />
											<span id="noneCheck" class="passMname" style="display: none;">기존과 동일한 닉네임입니다</span>
											<span id="possible" class="passMname" style="display: none;">사용가능한 닉네임입니다</span>
											<span id="impossible" style="display: none;">존재하는 닉네임입니다</span>
											<span id="inputId" style="display: none;">닉네임을 입력해주세요</span>
										</td>
										<td>
											<input type="button" id="mnameCheck" class="primary" onclick="checkMname()" value="닉네임중복 체크">
										</td>
									</tr>
									<tr>
										<td>이메일</td>
										<td colspan="2">
											<input type="text" name="memail" id="str_email01" value="${data.memail}" required>
										</td>
									</tr>
									<tr>
										<td colspan="3" align="right">
											<input type="submit" class="primary" id="confirm" value="회원정보수정">&nbsp;&nbsp;
											<input type="button" class="primary" value="회원탈퇴" onClick="quit()">
										</td>
									</tr>
								</table>
							</form>
						</c:if>
						<c:if test="${check=='f' }">
							<form action="myPage.do?category=checkUser" method="post">
								<table>
									<tr>
										<td><input type="password" name="mpw"
											placeholder="비밀번호확인"></td>
										<td><input type="submit" value="비밀번호확인"></td>
									</tr>
								</table>
							</form>
						</c:if>
					</c:if>
					<c:if test="${category=='favBoard'}">
						<c:if test="${empty list}">
							<table>
								<thead>
									<tr>
										<th style="text-align: center;">등록된 관심글이 없습니다.</th>
									</tr>
								</thead>
							</table>
						</c:if>
						<c:if test="${! empty list}">
							<table>
								<thead>
									<tr>
										<th class="thText">관심글번호</th>
										<th class="thText">카테고리</th>
										<th class="thText">제목</th>
										<th class="thText">작성자</th>
										<th class="thText"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="v" items="${list}" varStatus="r">
										<tr>
											<td class="thText">${v.favnum}</td>
											<td class="thText">${v.category}</td>
											<td class="thText"><a href="pCnt.do?category=${v.category}&pnum=${v.pnum}">${v.ptitle}</a></td>
											<td class="thText">${v.mname}</td>
											<td align="right"><a href="deleteFav.do?category=${v.category}&favnum=${v.favnum}" class="button concermDelete">관심글삭제</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							<div class="toolbar-bottom" id="paging">
								<div class="toolbar mt-lg">
									<ul class="pagination">
										<c:if test="${paging.pageNo==paging.firstPageNo}">
											<li>
											<a href="myFav.do?category=${category}&page=${paging.prevPageNo}" class="button disabled">prev</a>
											</li>
										</c:if>
										<c:if test="${paging.pageNo!=paging.firstPageNo}">
											<li>
											<a href="myFav.do?category=${category}&page=${paging.prevPageNo}" class="button">prev</a>
											</li>
										</c:if>
										<c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
											<c:choose>
												<c:when test="${i eq paging.pageNo}">
													<li><a class="page active">${i}</a></li>
												</c:when>
												<c:otherwise>
													<li><a href="myFav.do?category=${category}&page=${i}" class="page">${i}</a></li>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<c:if test="${paging.pageNo==paging.finalPageNo}">
											<li>
											<a href="myFav.do?category=${category}&page=${paging.nextPageNo}" class="button disabled">next</a>
											</li>
										</c:if>
										<c:if test="${paging.pageNo!=paging.finalPageNo}">
											<li>
											<a href="myFav.do?category=${category}&page=${paging.nextPageNo}" class="button">next</a>
											</li>
										</c:if>
									</ul>
								</div>
							</div>
						</c:if>
					</c:if>
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
	<script src="assets/js/mypage.js"></script>

</body>
</html>
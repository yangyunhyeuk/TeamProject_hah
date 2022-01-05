<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HAH_post</title>
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
					<h3>${post.category}board</h3>
					<table>
						<tr>
							<td colspan="2">[ ${post.category} ] ${post.ptitle}</td>
							<td align="right">조회 ${post.cnt} | 댓글 ${cnt}</td>
						</tr>
						<tr>
							<td>${post.mname}| ${post.pdate}</td>
							<td align="right" colspan="2">
							<c:if test="${mem eq post.mid}">
								<a href="pEdit.do?category=${post.category}&pnum=${post.pnum}">수정</a>
							</c:if>
							<c:if test="${mem eq 'admin'}">
								<a href="pDelete.do?category=${post.category}&pnum=${post.pnum}">삭제</a>
							</c:if>
							<c:if test="${! empty mem}">
								<c:if test="${mem != post.mid}">
									<a href="addFav.do?category=${post.category}&pnum=${post.pnum}&ptitle=${post.ptitle}&mname=${post.mname}">관심글추가</a>
								</c:if>
							</c:if>
							</td>
						</tr>
					</table>
					<div id="innerPost">
						<div style="margin-left: 10px">
							<span class="postContent">${post.pcontent}</span>
						</div>
					</div>
				</section>
				<table>
					<tr>
						<td align="left">
						<c:if test="${post.pnum!=startNum}">
							<a href="pCnt.do?category=${post.category}&pnum=${prevNum}">이전글▼</a>
							<span> | </span>
						</c:if>
						 <a href="pLists.do?category=${post.category}">목록으로</a> 
						 <c:if test="${post.pnum!=lastNum}">
						 	<span> | </span>
						 	<a href="pCnt.do?category=${post.category}&pnum=${nextNum}">다음글▲</a>
						 </c:if>
						 </td>
					</tr>
				</table>
				<section>
					<h3>Comments (${cnt})</h3>
					<table>
						<c:if test="${empty comments}">
							<tr>
								<td>아직 작성된 댓글이 없습니다.</td>
							</tr>
						</c:if>
						<c:if test="${!empty comments}">
							<c:forEach var="v" items="${comments}">
								<tr>
									<td width="120px">${v.mname}님</td>
									<td style="word-break: break-all">${v.comm}</td>
									<td width="150px" align="center">${v.cdate}<br>
									<c:if test="${v.mid eq mem}">
										<a href="cDelete.do?category=${post.category}&pnum=${post.pnum}&cnum=${v.cnum}">댓글삭제</a>
									</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
					<c:if test="${! empty mem}">
						<form method="post" action="cInsert.do">
							<input type="hidden" name="category" value="${post.category}">
							<input type="hidden" name="pnum" value="${post.pnum}">
							<input type="hidden" name="mid" value="${mem}">
							<table>
								<tr>
									<td>
									<textarea rows="2" cols="120" name="comm" placeholder="댓글을 입력하세요!" style="resize: none" required></textarea>
									</td>
									<td><input type="submit" value="댓글작성"></td>
								</tr>
							</table>
						</form>
					</c:if>
					<c:if test="${empty mem}">
						<table>
							<tr>
								<td>
								<textarea rows="2" cols="120" placeholder="로그인후 이용가능한 기능입니다!" style="resize: none" readonly></textarea>
								</td>
								<td>
									<button onClick="location.href='signPage.do?mode=login'">로그인</button>
								</td>
							</tr>
						</table>
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

</body>
</html>
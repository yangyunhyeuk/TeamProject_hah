<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 헤더 태그 - 로고 & LogInNOut 버튼 & MyPage버튼 -->
<header id="header">
	<a href="main.do" class="logo"><strong>Higher And Higher</strong></a>

	<ul class="icons">
		<c:if test="${empty mem}">
			<li><a href="signPage.do?mode=login" class="button primary small">LogIn</a></li>
			<!--로그인시 마이페이지 가는기능구현  -->
		</c:if>
		<c:if test="${! empty mem}">
			<li><strong>${mem}님 :)</strong></li>
			<li><a href="logout.do" class="button primary small">LogOut</a></li>
			<li><a href="myPage.do?category=myBoard"
				class="button primary small">MyPage</a></li>
		</c:if>
	</ul>

</header>
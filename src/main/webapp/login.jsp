<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HAH_login</title>
<mytag:icon/>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<link rel="shortcut icon" href="login/css/img/data.ico" type="image/x-icon">
<script src="assets/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/login.css" />
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body class="is-preload">
	<!-- Main -->
	<div id="main login_size">
		<div class="inner">
			<c:choose>
				<c:when test="${mode eq 'login'}">
				
					<!-- Content -->
					<section id="login_form">
						<div class="container">
						
							<!-- <span class="image main">  -->
							<span class="logo_image">
								<a href="main.do"><img src="images/HAH_Logo.png" alt="HAH로고" class="container img" /></a>
							</span>
						</div>

						<div class="login_wrap">

							<ul class="menu_wrap" role="tablist">
								<li class="menu_item">
								
									<!--  로그인 폼  -->
									<form id="frmNIDLogin" name="frmNIDLogiin" target="_top" autocomplete="off" action="login.do" method="post">
										<input type="hidden" name="mode" value="${mode}">
										<c:if test="${loginCnt!=null}">
											<input type="hidden" name="loginCnt" value="${loginCnt}">
										</c:if>
										<ul class="panel_wrap">
											<li class="panel_item" style="display: _block;">

												<div class="panel_inner" role="tabpanel"
													aria-controls="loinid">
													<div class="id_pw_wrap">
														<input type="text" id="idCheck" name="mid" placeholder="아이디" title="아이디" class="input_text" maxlength="41" value=""> 
														<input type="password" id="pwCheck" name="mpw" placeholder="비밀번호" title="비밀번호" class="input_text" maxlength="16">
													</div>
													<c:if test="${str!=null }">
														<span id="strPossible" class="passId">${str}</span>
													</c:if>
													<br>
													
													<!--로그인 버튼 -->
													<input type="submit" class="btn_text btn_login primary" value="로그인" id="logCheck" />
												</div>
											</li>
										</ul>
									</form>
								</li>
							</ul>
						</div>
					</section>

					<!-- 임시비밀번호 회원가입 부분 -->
					<div id="password">
						<div class="find_wrap" id="find_wrap">

							<c:if test="${loginCnt >= 1}">
								<span><a href="#" onclick="send_mail()" class="find_text">임시비밀번호</a></span>
								<span class="find_text"> | </span>
							</c:if>
							<span><a href="signPage.do?mode=join" id="SignIn" class="find_text">회원가입</a></span>
							<span class="find_text"> | </span>
							<span> <a href="main.do" class="find_text">돌아가기</a></span>
						</div>
					</div>
				</c:when>
				
				<c:when test="${mode eq 'join'}">
					<div id="user_p">
						<div class="container">
						
							<!-- <span class="image main">  -->
							<span class="logo_image">
							<a href="main.do"><img src="images/HAH_Logo.png" alt="HAH로고" class="container img" /></a>
							</span>
						</div>
						
						<!-- 로그인 HAH 로고 부분 -->
						<div class="login_wrap ">
							<form id="frmNIDLogin" action="join.do" class="input-group">
								<input type="text" class="input-field" name="uid" id="uid" placeholder="아이디" style="width: 70%; display: inline-block;">
								<input type="button" id="idCheck1" class="primary" onclick="checkID()" value="중복 체크">
								<span id="possible" class="passId">사용가능한 아이디입니다</span>
								<span id="impossible">존재하는 아이디입니다</span>
								<span id="inputId" style="display: none;">아이디를입력해주세요</span><br><br>
								<input type="password" class="input-field password" name="pwd" id="pwd" placeholder="비밀번호 &emsp;영문자+숫자+특수문자 조합"><br>
								<input type="password" class="input-field password" name="repwd" id="repwd" placeholder="비밀번호 확인"><br>
								<input type="text" class="input-field" name="mname" id="mname" placeholder="닉네임"><br>
								<input type="text" name="str_email01" id="str_email01" placeholder="이메일 "><br>
								
								<!--주소랑 상세 주소는 임시로 넣는 위치 보려고 준겁니다.  -->
							 	<input type="text" id="postcode" name="postcode" placeholder="우편번호">
								<input class="primary" type="button" id="btn" onClick="searchPostCode()" value="주소검색"> 						
								<br><br>
								<input type="text" id="roadAddress" name="roadAddress" readonly placeholder="주소" /><br>
								<input type="text" id="detailAddress" name="detailAddress" placeholder="상세 주소" /><br> 
								
								<!-- <input type="text" id="address_kakao" name="address" readonly  placeholder="도로명 주소"/>
								<br> 
								<input type="text" id="address_detail" name="address_detail" name="address_detail" placeholder="상세 주소"/>
								<br> -->
								
								
								
								<textarea class="box" id="txtArea" readonly="readonly">&#10;개인정보보호법에 따라 해당 사이트에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.&#10;&#10; 1. 수집하는 개인정보&#10;이용자는 회원가입을 하지 않아도 정보 검색, 뉴스 보기 등 대부분의 해당 사이트 서비스를 회원과 동일하게 이용할 수 있습니다. 이용자가 메일, 캘린더, 카페, 블로그 등과 같이 개인화 혹은 회원제 서비스를 이용하기 위해 회원가입을 할 경우, 해당 사이트는 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.</textarea>
								<div class="col-6 col-12-small" id="agreeDiv">
									<input type="checkbox" id="demo-human" name="agree">
									<label for="demo-human">약관 동의 (필수)</label>
								</div>
								<input type="submit" class="btn_login primary" id="confirm" value="회원가입">
							</form>
						</div>
					</div>
				</c:when>
			</c:choose>
		</div>
	</div>

	<!-- Scripts -->
	<script src="assets/js/login.js"></script>

</body>
</html>
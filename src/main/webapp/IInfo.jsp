<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="web.mail.*"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HAH_IInfo</title>
<mytag:icon/>
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/login.css" />
</head>
<body class="is-preload">

	<div id="main login_size">
		<div class="inner">
			<section id="login_form">
				<div class="container">
				
					<!-- <span class="image main">  -->
					<span class="logo_image"><img src="images/HAH_Logo.png" alt="HAH로고" class="container img" /></span>
				</div>
				<div class="login_wrap">
					<ul class="menu_wrap" role="tablist">
						<li class="menu_item">
						
							<!--  로그인 폼  -->
							<form id="mail" action="mail.do" method="post"
								name="frmNIDLogiin" autocomplete="off">
								<ul class="panel_wrap">
									<li class="panel_item" style="display: _block;">
										<div class="panel_inner" role="tabpanel" aria-controls="loinid">
											<div class="id_pw_wrap">
												<input type="text" id="uid" name="uid" placeholder="아이디" title="회원 아이디" class="input_text" maxlength="41" value="">
												<input type="email" id="mymail" name="mymail" placeholder="이메일" title="받으실 이메일" class="input_text" maxlength="41" value="">
											</div>
											
											<!--로그인 버튼 -->
											<input type="submit" class="btn_text btn_login primary" value="정보입력 완료" />
										</div>
									</li>
								</ul>
							</form>
						</li>
					</ul>
				</div>
			</section>
		</div>
	</div>

</body>
</html>
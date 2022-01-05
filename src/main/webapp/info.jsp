<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<!--
   Editorial by HTML5 UP
   html5up.net | @ajlkn
   Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>HAH_info</title>
<meta charset="utf-8" />
<meta name="viewport"
   content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<mytag:icon />
<script type="text/javascript">

</script>
<style type="text/css">
#info{
   font-size: 20px;
}
</style>
</head>
<body class="is-preload">

   <!-- Wrapper -->
   <div id="wrapper">

      <!-- Main -->
      <div id="main">
         <div class="inner">

            <mytag:Header></mytag:Header>
            <!-- Content -->
            <section>
               <header class="main">
                  <h1>About Us</h1>
               </header>

               <span class="image main"><img src="images/team.jpg"
                  alt="천사강사님" title="천사강사님" /></span>


               <header class="major">
                  <h2>Higher And Higher</h2>
               </header>
               <div id="info">
               <strong>사용자 우선 마인드</strong>
               <p>동료 스태커에게 권한을 부여하고, 경청하고, 협력하여 사용자에게 서비스를 제공합니다.</p>
               <strong>유연하고 포용하기</strong>
               <p>우리는 다양한 사람들이 존중과 신뢰의 환경에서 협력할 때 최선을 다합니다.
               다양한 목소리를 들을 수 있는 공간을 만들고 사람들이 일하는 방식에 유연성을 허용합니다.</p>
               <strong>투명합니다.</strong>
               <p>회사 안팎에서 공개적이고 정직하게 의사 소통합니다.
               공감하고 신뢰할 수 있으며 정직하게 행동함으로써 다른 사람들의 투명성을 장려합니다.</p>
               <strong>뛰어난 결과를 제공하도록 사용자들에게 권한 부여합니다.</strong>
               <p>사용자들에게 업무를 수행할 수 있는 공간을 제공하고, 필요할 때 지원하고, 흠 없는 책임을 실천합니다.</p>
               <strong>우리 중심의 커뮤니티</strong>
               <p>커뮤니티는 우리가 하는 모든 일의 중심에 있습니다.
               모든 사람이 배우고 기부하도록 권장되는 건강한 커뮤니티를 육성합니다.</p>
               <strong>배우고, 공유하고, 성장하기</strong>
               <p>호기심을 갖고 배우고자 합니다.
               윤리적이고 지속 가능하며 장기적인 성장을 목표로 합니다.</p>
               </div>
               
            </section>

            <section>
               <header class="major">
                  <h2>Member</h2>
               </header>
               <div class="features">
                  <article>
                     <!--샘플 이미지 사이즈 100 100 단위 : px  -->
                     <a class="member" href="https://blog.naver.com/azz4622" target="_blank"><img alt="윤혁"
                        src="images/yh.jpg"></a>
                     <div class="content">
                        <h3>양윤혁</h3>
                        <p>Captain of Our Team / Part : Model / 칭찬봇
                        <br>모든 경험엔 배움이 있다.</p>
                     </div>
                  </article>
                  <article>
                     <a class="member" href="" target="_blank"><img alt="성민"
                        src="images/sm.jpg"></a>
                     <div class="content">
                        <h3>김성민</h3>
                        <p>Part : View / 봇
                        <br>Opportunities come to those who are prepared.</p>
                     </div>
                  </article>
                  <article>
                     <a class="member" href="" target="_blank"><img alt="희진"
                        src="images/hj.jpg"></a>
                     <div class="content">
                        <h3>김희진</h3>
                        <p>Presenter of Our Team / Part : Controller / 대답봇
                        <br>해보지도 않고 실패하는 것보다 일단 해보고 실패하자!</p>
                     </div>
                  </article>
                  <article>
                     <a class="member" href="https://blog.naver.com/ykouo" target="_blank"><img alt="여경"
                        src="images/yk.jpg"></a>
                     <div class="content">
                        <h3>안여경</h3>
                        <p>Part : Model / 질문봇 ver1
                        <br>First, think. Second, belive. Third, dream. And finally, dare.</p>
                     </div>
                  </article>
                  <article>
                     <a class="member" href="https://blog.naver.com/jogjin37" target="_blank"><img alt="경진"
                        src="images/gj.jpg"></a>
                     <div class="content">
                        <h3>조경진</h3>
                        <p>Part : Controller / 질문봇 ver2
                        <br>즐겁고 행복하게!</p>
                     </div>
                  </article>
               </div>
            </section>


         </div>
      </div>




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
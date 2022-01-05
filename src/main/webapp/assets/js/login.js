// 로그인 js
//임시번호 메일 보내기===========================================================================================================================
function send_mail() {
   window.open("./IInfo.jsp", "", "width=800, height=550, resizable=no, scrollbars=no, status=no");
}
//로그인 유효성 검사===========================================================================================================================
var logBtn = document.querySelector('#logCheck');
if (logBtn != null)
   logBtn.onclick = function() {
      console.log("1");
      var idCheck = document.getElementById("idCheck");
      var pwCheck = document.getElementById("pwCheck");

      if (idCheck.value == "") {
         alert("아이디를 입력하세요.");
         idCheck.focus();
         return false;
      }
      if (pwCheck.value == "") {
         alert("비밀번호를 입력하세요.");
         pwCheck.focus();
         return false;
      } else {
         return true;

      }

   };
//아이디 중복 검사 Ajax==========================================================================================================================


function checkID() {
   var uid = $('#uid').val();
   $.ajax({
      type: 'GET',
      url: "checkId.do?uid=" + uid,
      data: uid,
      success: function(result) {
         console.log(result);

         $("#impossible").hide();
         $("#possible").hide();
         $("#inputId").hide();



         var idRegExp = /^[a-zA-z0-9]{4,12}$/; //아이디 유효성 검사

         //아이디 유효성검사 
         if (!idRegExp.test($("#uid").val())) {
            alert("ajax_아이디는 영문 대소문자와 숫자 4~12자리로 입력해야합니다!");
            $("#uid").val("");
            $("#uid").focus();
         }




         else if (result == 'fail') {
            $('#uid').val('');
            $("#uid").focus();
            $("#impossible").show();
            $("#possible").hide();
            $("#inputId").hide();


         } else {
            if ($('#uid').val() == "") {
               $("#impossible").hide();
               $("#possible").hide();
               $("#inputId").show();
            }
            else {
               $("#impossible").hide();
               $("#possible").show();
               $("#inputId").hide();
            }
         }
      }
   })
}

//카카오 주소 api==========================================================================================================================

window.onload = function() {
   if (document.getElementById("address_kakao") != null) {
      document.getElementById("address_kakao").addEventListener("click", function() { //주소입력칸을 클릭하면
         //카카오 지도 발생
         new daum.Postcode(
            {
               oncomplete: function(data) { //선택시 입력값 세팅
                  document
                     .getElementById("address_kakao").value = data.address; // 주소 넣기
                  document
                     .querySelector(
                        "input[name=address_detail]")
                     .focus(); //상세입력 포커싱
               }
            }).open();
      });
   }
}


//회원가입 유효성 검사==========================================================================================================================
var btn = document.querySelector('#confirm');
if (btn != null)
   btn.onclick = function() {
      console.log("2");
      var uid = document.getElementById("uid");
      var pwd = document.getElementById("pwd");
      var repwd = document.getElementById("repwd");
      var str_email01 = document.getElementById("str_email01");
      var mname = document.getElementById("mname");
      var agree = $("input:checkbox[name='agree']").is(":checked");


     /* var address_kakao = document.getElementById("address_kakao");
      var address_detail = document.getElementById("address_detail");*/

      var postcode = document.getElementById("postcode");
      var roadAddress = document.getElementById("roadAddress");
      var detailAddress = document.getElementById("detailAddress");


      if (uid.value == "") {
         alert("아이디를 입력하세요.");
         uid.focus();
         return false;
      }

      var idRegExp = /^[a-zA-z0-9]{4,12}$/; //아이디 유효성 검사

      if (!idRegExp.test(uid.value)) {
         alert("아이디는 영문 대소문자와 숫자 4~12자리로 입력해야합니다!");
         uid.focus();
         return false;
      }



      if (!$(".passId").is(":visible")) {
         alert("아이디 중복확인하시기 바랍니다.");
         document.getElementById("uid").focus();
         return false;
      }

      if (pwd.value == "") {
         alert("비밀번호를 입력하세요.");
         pwd.focus();
         return false;
      }

      var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;

      if (!pwdCheck.test(pwd.value)) { // 비밀번호 유효성 검사
         alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야합니다.");
         pwd.focus();
         return false;
      }

      if (repwd.value !== pwd.value) { // 비밀번호 재확인 검사
         alert("비밀번호가 일치하지 않습니다.");
         repwd.focus();
         return false;
      }

      if (mname.value == "") {
         alert("닉네임을 입력하세요.");
         mname.focus();
         return false;
      }

      if (str_email01.value == "") {
         alert("이메일을 입력하세요.");
         str_email01.focus();
         return false;
      }

      var emailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      // ->  / / 안에 있는 내용은 정규표현식 검증에 사용되는 패턴이 이 안에 위치함
      // ->  / /i 정규표현식에 사용된 패턴이 대소문자를 구분하지 않도록 i를 사용함
      // ->  ^ 표시는 처음시작하는 부분부터 일치한다는 표시임
      // ->  [0-9a-zA-Z] 하나의 문자가 []안에 위치한 규칙을 따른다는 것으로 숫자와 알파벳 소문지 대문자인 경우를 뜻 함
      // ->  * 이 기호는 0또는 그 이상의 문자가 연속될 수 있음을 말함
      if (!emailRegExp.test(str_email01.value)) {
         alert("이메일 주소 확인 후 다시 작성하시기 바랍니다.");
         str_email01.focus();
         return false;
      }
     if (postcode.value == "") {
         alert("우편번호를 입력하세요.");
         postcode.focus();
         return false;
      }

     if (roadAddress.value == "") {
         alert("주소를 입력하세요.");
         roadAddress.focus();
         return false;
      }

      if (detailAddress.value == "") {
         alert("상세주소를 입력하세요.");
         detailAddress.focus();
         return false;
      }


      if (!agree) {
         alert("약관 동의를 체크하세요.");
         $('#demo-human').focus();  // 해당 체크박스로 포커스 이동.
         return false;
      }

      else {
         alert("회원가입이 완료되었습니다.");
         return true;
      }
   };
//우편번호 주소 등록==========================================================================================================================
   function searchPostCode(){new daum.Postcode({
		oncomplete: function(data){
	        // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	        // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	       // roadAddress = data.roadAddress; // 도로명 주소 변수
	        var extraRoadAddr = ''; // 참고 항목 변수

	        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	            extraRoadAddr += data.bname;
	        }
	        // 건물명이 있고, 공동주택일 경우 추가한다.
	        if(data.buildingName !== '' && data.apartment === 'Y'){
	           extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	        }
	        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	        if(extraRoadAddr !== ''){
	            extraRoadAddr = ' (' + extraRoadAddr + ')';
	        }
	        // 우편번호와 주소 정보를 해당 필드에 넣는다.
	        postcode.value = data.zonecode;
	        roadAddress.value = data.roadAddress;
	       // jibunAddress.value = data.jibunAddress;
	          
	        autoClose: true; // 선택하면 팝업창이 자동으로 닫힌다.     
	         
	        var detailAddress = document.getElementById("detailAddress");
	        detailAddress.focus(); // 팝업창이 닫히면 상세주소 란으로 포커싱 
		}
	}).open();}
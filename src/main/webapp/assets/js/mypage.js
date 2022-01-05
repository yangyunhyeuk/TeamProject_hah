function quit() {
	var isDeleteMember = confirm("정말로 탈퇴하시겠습니까?");
	if (!isDeleteMember) {
		return false;
	} else {
		document.location.href = "quitUser.do"

	}
}

//관심글 삭제시 정말로 삭제할건지 확인하는 창 출력 
$(document).ready(function() {
	$(".concermDelete").click(function() {
		var isdelete = confirm("정말로 삭제 하시겠습니까?");
		if (isdelete==false) {
			return false;
		}

	});
	//회원정보변경시 비밀번호/닉네임 글자수 제한
	$('#pwd').on('keyup', function() {

		if ($(this).val().length > 25) {
			alert("비밀번호는 25자까지 입니다.");
			$(this).val($(this).val().substring(0, 25));

		}

	});
	$('#mname').on('keyup', function() {

		if ($(this).val().length > 10) {
			alert("닉네임10자까지 입니다.");
			$(this).val($(this).val().substring(0, 10));

		}
	});


});

//회원정보 변경시 비밀번호 확인시 비밀번호칸공백이면 비밀번호입력 멘트를 띄운다.
var checkpwbtn = document.querySelector('#checkpwbtn');
if (checkpwbtn != null)
	checkpwbtn.onclick = function() {
	console.log("1");
	var checkpw = document.getElementById("checkpw");

	if (checkpw.value == "") {
		alert("비밀번호를 입력하세요.");sSS
		checkpw.focus();
		return false;
	}else {
		return true;
	}     
};

function checkMname() {
	var mname = $('#mname').val();
	$.ajax({
		type: 'GET',
		url: "checkMname.do?mname=" + mname,
		data: mname,
		success: function(result) {
			console.log(result);

			var mnameRegExp = /^[가 -힣a-zA-z0-9]{1,10}$/; //아이디 유효성 검사

			$("#impossible").hide();
			$("#possible").hide();
			$("#inputId").hide();
			$("#noneCheck").hide();

			if (!mnameRegExp.test($("#mname").val())) {
				alert("닉네임은 1~10자리로 입력해야합니다!");
				$("#mname").val("");
				$("#mname").focus();
			}

			else if (result == 'fail') {
				if($('#mname').val() == $('#mymname').val()){
					$("#impossible").hide();
					$("#possible").hide();
					$("#inputId").hide();
					$("#noneCheck").show();
				}
				else{
					$('#mname').val('');
					$("#mname").focus();
					$("#impossible").show();
					$("#possible").hide();
					$("#inputId").hide();
					$("#noneCheck").hide();					
				}

			} else {
				if ($('#mname').val() == "") {
					$("#impossible").hide();
					$("#possible").hide();
					$("#inputId").show();
					$("#noneCheck").hide();
				}

				else {
					$("#impossible").hide();
					$("#possible").show();
					$("#inputId").hide();
					$("#noneCheck").hide();
				}
			}
		}
	})
}




var btn = document.querySelector('#confirm');
if (btn != null)
	btn.onclick = function() {
	console.log("회원정보수정");
	var pwd = document.getElementById("pwd");
	var str_email01 = document.getElementById("str_email01");
	var mname = document.getElementById("mname");
	var idCheck = document.getElementById("mymname").value;

	console.log("체크");
	console.log(idCheck);
	console.log(mname.value);


	var mnameRegExp = /^[가-힣a-zA-z0-9]{1,10}$/; //닉네임 유효성 검사

	if (!mnameRegExp.test(mname.value)) {
		alert("닉네임은  1~10자리로 입력해야합니다!");
		mname.focus();
		return false;
	}

	if (!$(".passMname").is(":visible")) {
		alert("닉네임 중복확인하시기 바랍니다.");
		document.getElementById("mname").focus();
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

};

// 글작성시 null값 방지를 위해 입력값이 없을경우 alert창을 띄움
$(document).ready(function() {
	$("#edit").click(function() {
		if ($("#titleInputCheck").val().length == 0) {
			alert("제목을 입력해주세요");
			$("#titleInputCheck").focus();
			return false;
		}
		if ($("#contentInputCheck").val().length == 0) {
			alert("내용을 입력해주세요");
			$("#contentInputCheck").focus();
			return false;
		}
	});
});

// 글작성시 null값 방지를 위해 입력값이 없을경우 alert창을 띄움
$(document).ready(function() {
	$("#delete").click(function() {
		var isdelete = confirm("정말로 삭제 하시겠습니까?");
		console.log(isdelete);
		
		if(!isdelete){
			console.log("1");
			return false;
		}

	});
});
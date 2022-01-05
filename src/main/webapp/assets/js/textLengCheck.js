$(document).ready(function() {

	$('#titleInputCheck').on('keyup', function() {

		if ($(this).val().length > 10) {
			alert("제목은 10자까지 입니다.");
			$(this).val($(this).val().substring(0, 10));

		}

	});
	$('#contentInputCheck').on('keyup', function() {

		if ($(this).val().length > 50) {
			alert("내용은 50자까지 입니다.");
			$(this).val($(this).val().substring(0, 50));

		}

	});
	

});

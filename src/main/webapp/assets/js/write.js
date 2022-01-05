(function($) {

	var title = document.getElementById("titleInputCheck");
	var content = document.getElementById("contentInputCheck");

	if(!((title==null && content==null) || (title=="" &&content==""))){
		$(window).on("beforeunload", function() {
			return "작성중인 글이 존재합니다. 페이지를 나가시겠습니까?";
		});

		$("#postWriteForm").on("submit", function() {
			console.log("1");
			$(window).off("beforeunload");
			console.log("2");
			/*beforeunload 이벤트는 페이지를 벗어나기 전 확인 메시지를 출력하는 이벤트*/
		});
		 // edit.jsp페이지에서 삭제버튼 클릭시 변경사항이 있다는 alert창 뜨지 않겠끔 막음 
        $("#delete").click(function() {
            $(window).off("beforeunload");
            
        });
	}
})(jQuery);
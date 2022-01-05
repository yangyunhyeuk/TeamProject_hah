(function($) {
	// GoogleMAP 등록
	var map; // 편의상 map변수 선언
	function initMap() {
		var ll = {
			lat : 37.50003,
			lng : 127.03559
		}; // 위도 : latitude (lat) //경도 : longitude (lng)
		map = new google.maps.Map // Map(어디에,무엇을)
		(document.getElementById("map"), {
			zoom : 17,
			center : ll
		} // center라는 속성은 맵에서 내 현재위치를 표시하는 화살표 같은것
		); // zoom: 숫자가 클수록 확대가된다.
		var myIcon = new google.maps.MarkerImage("images/HAH.png")
		var marker = new google.maps.Marker({
			position : ll,
			map : map,
			icon : myIcon

		});// 마커 추가
	}
	initMap(); // 선언즉시 맵 생성
	
	
// 문의 메일 null값 방지 
		$(document).ready(function(){
			$("#qamail").click(function(){
				if($("#demo-name").val().length==0){
					alert("성함 또는 닉네임을 입력해주세요");
					$("#demo-name").focus();
					return false;
				}
				if($("#demo-message").val().length==0){
					alert("문의내용을 입력해주세요");
					$("#demo-message").focus();
					return false;
				}
				if($("#demo-email").val().length==0){
					alert("답변을 받으실 이메일을 입력해주세요");
					$("#demo-email").focus();
					return false;
				}
			});
		});

	
})(jQuery);
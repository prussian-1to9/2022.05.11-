$(document).ready(function(){
	// 홈버튼 기능 설정
	$('#hbtn').click(function(){
		$(location).attr('href', '/whistle/main.blp');
	});
	
	// lbtn 기능 설정
	$('.lbtn').click(function(){
		// 클릭된 회원의 회원번호 알아내기
		var sno = $(this).attr('id');
// 확인	alert(sno);

		/* get 방식으로 요청
		$(location).attr('href', '/whistle/member/memberInfo.blp?mno=' + sno);	*/
		
		// post 방식으로 요청
		$('#mno').val(sno);
		
		// 폼 제출
		$('#frm').submit();
	});
});
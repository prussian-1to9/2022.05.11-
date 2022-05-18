$(document).ready(function(){
	
	// 홈버튼 기능
	$('#hbtn').click(function(){
		$(location).attr('href', '/whistle/');
	});
	
	// 로그인 버튼 기능
	$('#lbtn').click(function(){
		$(location).attr('href', '/whistle/member/login.blp');
	});
	
	// 회원가입 버튼 기능
	$('#jbtn').click(function(){
		$(location).attr('href', '/whistle/member/join.blp');
	});
	
	// 로그아웃 버튼 기능
	$('#obtn').click(function(){
		$(location).attr('href', '/whistle/member/logout.blp');
	});
	
	// 페이지 버튼 기능
	$('.pbtn').click(function(){
		// 페이지 데이터 읽어오기
		var page = $(this).attr('id');
		
		// 데이터 세팅
		$('#nowPage').val(page);
		$('#frm').submit();
	});
	
	// 글작성 버튼 기능
	$('#wbtn').click(function(){
/*		var page = $('#nowPage').val();
		
		// get 방식 요청
		$(location).attr('href', '/whistle/guestBoard/gBoardWrite.blp?nowPage=' + page);
*/
		
		// post 방식 요청
		$('#frm').attr('action', '/whistle/guestBoard/gBoardWrite.blp');
		$('#frm').submit();
	});
});
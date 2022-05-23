/**
	/resources/js/whistle/main.js
*/
// jQuery함수! 이렇게도 쓸 수 이따.
$(function(){
	// join 버튼 기능
	$('#jbtn').click(function(){
		$(location).attr('href', '/whistle/member/join.blp');
	});
	
	// login 버튼 기능
	$('#lbtn').click(function(){
		$(location).attr('href', '/whistle/member/loginProc.blp');
	});
	
	// logout 버튼 기능
	$('#obtn').click(function(){
		$(location).attr('href', '/whistle/member/logout.blp');
	});
	
	// 정보 보기 버튼 기능
	$('#ibtn').click(function(){
		$(location).attr('href', '/whistle/member/myInfo.blp');
	});
	
	// 회원 목록 보기 함수
	$('#mlbtn').click(function(){
		$(location).attr('href', '/whistle/member/memberList.blp');
	});
	
	// 방명록 버튼 기능
	$('#gbtn').click(function(){
		$(location).attr('href', '/whistle/guestBoard/gBoardList.blp');
	});
	
	// 댓글 게시판 버튼
	$('#rbtn').click(function(){
		$(location).attr('href', '/whistle/reboard/reboardList.blp');
	});
	
	// 파일 게시판 버튼
	$('#fbtn').click(function(){
		$(location).attr('href', '/whistle/board/boardList.blp');
	});
});
$(document).ready(function(){
	// 홈버튼
	$('#hbtn').click(function(){
		$(location).attr('href', '/whistle/');
	});
	
	// 로그아웃 버튼
	$('#obtn').click(function(){
		$(location).attr('href', '/whistle/member/logout.blp');
	});
	
	// 리스트 버튼
	$('#lbtn').click(function(){
		$('#body').prop('disabled', true);
		$('#frm').attr('action', '/whistle/guestBoard/gBoardList.blp');
		$('#frm').submit();
	});
	
	// 리셋버튼
	$('#rbtn').click(function(){
		$('#body').val('');
	});

	// 등록버튼
	$('#wbtn').click(function(){
		// 입력데이터 유효성 검사
		var body = $('#body').val();
		var body = body.trim();
		
		if(!body||body.length==0){
			alert('내용을 입력하세요.');
			$('#body').val('');
			$('#body').focus();
			return;
		}
		
		$('#frm').submit();
	});
});
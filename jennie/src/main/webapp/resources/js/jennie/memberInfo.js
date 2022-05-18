$(document).ready(function(){
	// list 버튼
	$('#lbtn').click(function(){
		$(location).attr('href', '/whistle/member/memberList.blp');
	});
	
	// 탈퇴버튼
	$('#dbtn').click(function(){
		// 보낼 데이터 세팅
		var sno = $('#mno').html();
		var sid = $('#id').html();
		
		$('#smno').val(sno);
		$('#sid').val(sid);
		
		// 다시 한번 묻기
		if(confirm('정말 탈퇴 하시겠습니까?')){
			
			// 폼태그 전송
			$('#frm').attr('action', '/whistle/member/delInfo.blp');
			$('#frm').submit();
		}
		
	});
	
	// 수정버튼
	$('#ebtn').click(function(){
		$(location).attr('href', '/whistle/member/editInfo.blp');
	});
});
$(document).ready(function(){
	// 리셋 버튼 기능
	$('#rbtn').click(function(){
		document.frm.reset();
	});
	
	// 홈 버튼 기능
	$('#hbtn').click(function(){
		$(location).attr('href', '/whistle/');
	});
	
	// edit 버튼 기능
	$('#ebtn').click(function(){
		// 변경된 것만 찾기
		var tmail = $('#tmail').val();
		var ttel = $('#ttel').val();
		var tano = $('#tano').val();
		
		var pw = $('#pw').val();
		var mail = $('#mail').val();
		var tel = $('#tel').val();
		var ano = $('[name="ano"]:checked').val();
		
		// 결과에 따른 처리
		if(!pw){// 입력 안됐으면 전송 안되게 막기
			$('#pw').prop('disabled', true);
		}
		if(tmail == mail){
			$('#mail').prop('disabled', true);
		}
		if(ttel == tel){
			$('#tel').prop('disabled', true);
		}
		if(tano == ano){
			$('[name="ano"]').prop('disabled', true);
		}
		
		// 아무것도 변경되지 않았다면
		if(!pw && (tmail==mail) && (ttel==tel) && (tano==ano)){
			return;
		}
		
		// 비밀번호 확인
		if((pw!=null) && (pw!==$('#repw').val())){ 
			alert('비밀번호와 비밀번호 확인 값이 일치하지 않습니다.');
			return;
		}
		
		// 다 통과한 경우, repw name 지워주기
		$('#repw').removeAttr('name');
		
		// 통과시 제출
		$('#frm').attr('action', '/whistle/member/editProc.blp');
		$('#frm').submit();
	});
	
	// 비밀번호 확인 입력 이벤트
	$('#repw').change(function(){
		// 비밀번호 값 꺼내기
		var repw = $(this).val();
		var pw = $('#pw').val();
		
		if(pw!=repw){
			$('#repwmsg').html('# 비밀번호가 일치하지 않습니다.');
			$('#repwmsg').removeClass('w3-text-green w3-text-red').addClass('w3-text-red');
			
		}else{// 12345 인 경우
			$('#repwmsg').html('비밀번호가 일치합니다.');
			$('#repwmsg').removeClass('w3-text-green w3-text-red').addClass('w3-text-green');
		}
		// 가시화
		$('#repwmsg').css('display', 'block');
	});
});
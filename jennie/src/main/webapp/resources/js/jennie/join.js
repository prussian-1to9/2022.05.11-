$(document).ready(function(){
	$(document.frm.gen).change(function(){
		// 아바타들 감추기
		$('#avtfr').stop().slideUp();
		
		var sgen = $(this).val();
// 확인	alert(sgen);

		if(sgen=='F'){
			$('#mavt').css('display', 'none');
			$('#favt').css('display', 'block');
			$('#avtfr').stop().slideDown(1000);
		} else{
			$('#favt').css('display', 'none');
			$('#mavt').css('display', 'block');
			$('#avtfr').stop().slideDown(1000);
		}
	});
	
	// id check 버튼 기능
	$('#idck').click(function(){
		// 입력한 아이디 꺼내오기
		var sid = $('#id').val();
		
		if(!sid){// 입력내용이 없는 경우
			$('#id').focus();
			alert('* 아이디를 입력하세요.');
			return;
		}
		
		// 전달해 사용 가능 여부 파악
		$.ajax({
			url: '/whistle/member/idCheck.blp',
			type: 'post',
			dataType: 'json',
			data: {
				id: sid
			},
			success: function(data){// 뷰에 보여주기
				var result = data.result;
				
				// 보여주기 전에 class 초기화 해주기
				$('#idmsg').removeClass('w3-text-green w3-text-red');
				
				// 결과에 따른 처리
				if (result == 'OK'){
					$('#idmsg').html('* 사용 가능한 아이디입니다.');
					$('#idmsg').addClass('w3-text-green');
				}else{
					$('#idmsg').html('* 사용 불가능한 아이디입니다.');
					$('#idmsg').addClass('w3-text-red');
				}
				// 나와라 얍!
				$('#idmsg').css('display', 'block');
			},
			error: function(){
				alert('통신에러 발생');
			}
		});
	});
	
	// 비밀번호 입력 이벤트
	$('#pw').change(function(){
		// 비밀번호 값 꺼내기
		var pw = $(this).val();
		
		if(pw!='12345'){
			$('#pwmsg').html('# 비밀번호는 12345 만 가능합니다.');
			$('#pwmsg').removeClass('w3-text-green w3-text-red').addClass('w3-text-red');
			
		}else{// 12345 인 경우
			$('#pwmsg').html('사용 가능한 비밀번호입니다.');
			$('#pwmsg').removeClass('w3-text-green w3-text-red').addClass('w3-text-green');
		}
		// 가시화
		$('#pwmsg').css('display', 'block');
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
	
	// 홈버튼
	$('#hbtn').click(function(){
		$(location).attr('href', '/whistle/main.blp');
	});
	
	// 리셋버튼
	$('#rbtn').click(function(){
		$('#frm')[0].reset();
	});
	
	// 회원가입 버튼
	$('#jbtn').click(function(){
		// 데이터 유효성 검사
		// 값부터 따오기
		var sname = $('#name').val();
		var sid = $('#id').val();
		var spw = $('#pw').val();
		var srepw = $('#repw').val();
		var smail = $('#mail').val();
		var stel = $('#tel').val();
		var sgen = $('input[name="gen"]:checked').val();
		var savt = $('input[name="ano"]:checked').val();

		// 값의 유무 검사
		if(!sname){
			alert('이름을 입력하세요.');
			$('#name').focus();
			return;
		} else if(!sid){
			alert('아이디를 입력하세요.');
			$('#id').focus();
			return;
		} else if(!spw){
			alert('비밀번호를 입력하세요.');
			$('#pw').focus();
			return;
		} else if(!srepw){
			alert('비밀번호를 확인해 주세요.');
			$('#repw').focus();
			return;
		} else if(!smail){
			alert('메일을 입력하세요.');
			$('#mail').focus();
			return;
		} else if(!stel){
			alert('전화번호를 입력하세요.');
			$('#tel').focus();
			return;
		} else if(!sgen){
			alert('성별을 체크하세요.');
			return;
		} else if(!savt){
			alert('아바타를 체크하세요.');
			return;
		}
		
		// 아이디 중복확인 했는지
		if($('#idmsg').html()=='* 사용 불가능한 아이디입니다.'){
			alert('중복된 아이디는 사용할 수 없습니다.');
			return;
		}else if($('#idmsg').html()!='* 사용 가능한 아이디입니다.'){
			alert('아이디 중복검사를 해주세요.');
			return;
		}
		
		// 비밀번호 검사
		if(spw!=='12345'){
			alert('비밀번호 값을 확인해 주세요.');
			return;
		}else if(spw!==srepw){
			alert('비밀번호와 비밀번호 확인 값이 일치하지 않습니다.');
			return;
		}
		// 다 통과한 경우, repw name 지워주기
		$('#repw').removeAttr('name');
		
		// 이메일, 전화번호 검사
		var mailPat;
		var telPat = /^010-[0-9]{4}-[0-9]{4}$/;
		
		// 통과시 submit
		$('#frm').attr('action', '/whistle/member/joinProc.blp');
		$('#frm').submit();
	});
});
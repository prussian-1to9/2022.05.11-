$(document).ready(function(){
	
/*	// 홈버튼 기능
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
*/	
	// 페이지 버튼 기능
	$('.pbtn').click(function(){
		// 페이지 데이터 읽어오기
		var page = $(this).attr('id');
		
		// 데이터 세팅
		$('#nowPage').val(page);
		$('#frm').submit();
	});
	
	// switch문으로 처리해보자!
	$('.menubtn').click(function(){
		var bid = $(this).attr('id');
		var addr = '/whistle/';
		
		switch(bid){
			case 'hbtn':
				break;
				
			case 'lbtn':
				addr = '/whistle/member/login.blp';
				break;
				
			case 'jbtn':
				addr = '/whistle/member/join.blp';
				break;
				
			case 'obtn':
				addr = '/whistle/member/logout.blp';
				break;
				
			case 'wbtn':
				$('#frm').attr('action', '/whistle/reboard/reboardWrite.blp');
				$('#frm').submit();
				return;
		}
		
		$(location).attr('href', addr);
	});
	
	// 댓글/수정/삭제 버튼
	$('.w3-button.w70').click(function(){
		// 어떤 버튼이 클릭된건지 알아내기
		var btxt = $(this).html();
		
		// 글 번호 읽어오기
		var sno = $(this).parent().attr('id');
		$('#bno').val(sno);
		
		if(btxt == '댓글'){
/*			// 파라미터 꺼내, input 태그에 입력
			var tbody = $('#bd' + sno).html();
			if(tbody.length >= 10){
				tbody = tbody.substring(0, 10) + '...';
			}	// 복잡해지니까 컨트롤러에서 처리!
			
			$('#body').val(tbody);
			$('#bno').val(sno);
*/			

			// 보내기
			$('#frm').attr('action', '/whistle/reboard/reboardComment.blp');
			
		}else if(btxt == '삭제'){
			$('#frm').attr('action', '/whistle/reboard/reboardDel.blp');
		}else if(btxt == '수정'){
			$('#frm').attr('action', '/whistle/reboard/reboardEdit.blp');
		}
		
			$('#frm').submit();			
	});
	
// 작성/수정 폼에서만 있는 기능들
	
	// 리스트 버튼
	$('#listbtn').click(function(){
		// frm 향하는 경로 변경
		$('#frm').attr('action', '/whistle/reboard/reboardList.blp');
		
		// 비사용 태그 비활성화
		$('#mno').prop('disabled', true);
		$('#body').prop('disabled', true);
		
		// 제출
		$('#frm').submit();
	});
	
	// 리셋버튼
	$('#rbtn').click(function(){
		document.frm.reset();
	});
	
	// 게시글 등록 버튼
	$('#wpbtn').click(function(){
		// 유효성 검사
		var txt = $('#body').val();
		txt = txt.trim();	// 앞뒤 공백 제거
		if(!txt || txt.length == 0){
			alert('본문 내용을 입력하세요.');
			$('#body').val('');
			$('#body').focus();
			return;
		}
		
		// 200자 글자수 제한
		if(btxt.length > 200){
			btxt = btxt.substring(0, 200);
			$('#body').val(btxt);
			alert('댓글은 200자를 초과할 수 없습니다.');
			return;
		}
		
		// 통과시 데이터 넣기
		$('#body').val(txt);
		
		// 제출
		$('#frm').submit();
	});
	
	// 댓글 등록 버튼
	$('#cmtbtn').click(function(){
		var btxt = $('#body').val();
		btxt = btxt.trim();	// 앞 뒤 공백 제거
		
		if(!btxt || btxt.length == 0){
			alert('본문 내용을 입력하세요.');
			$('#body').val('');
			$('#body').focus();
			return;
		}
		
		// 200자 글자수 제한
		if(btxt.length > 200){
			btxt = btxt.substring(0, 200);
			$('#body').val(btxt);
			alert('댓글은 200자를 초과할 수 없습니다.');
			return;
		}
		
		// 통과시 데이터 넣고 제출
		$('#body').val(btxt);
		$('#frm').submit();
	});
	
	// 글 수정 버튼
	$('#editbtn').click(function(){
		var btxt = $('#body').val();
		btxt = btxt.trim();	// 앞 뒤 공백 제거
		
		if(!btxt || btxt.length == 0){
			alert('본문 내용을 입력하세요.');
			$('#body').val('');
			$('#body').focus();
			return;
		}
		
		// 200자 글자수 제한
		if(btxt.length > 200){
			btxt = btxt.substring(0, 200);
			$('#body').val(btxt);
			alert('댓글은 200자를 초과할 수 없습니다.');
			return;
		}
		
		// 통과시 데이터 넣고 제출
		$('#body').val(btxt);
		$('#frm').submit();
	});
});
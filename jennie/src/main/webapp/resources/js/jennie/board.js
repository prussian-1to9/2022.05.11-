$(document).ready(function(){
	
	// 페이지버튼 클릭 이벤트
	$('.pbtn').click(function(){
		// 페이지번호 읽어오기
		var pno = $(this).attr('id');
		
		// 세팅
		$('#nowPage').val(pno);

		// 폼 태그 전송
		$('#pageFrm').submit();
	});
	
	// 메뉴버튼 클릭 이벤트
	$('.menubtn').not('#wbtn').click(function(){
		// 메뉴버튼 아이디값 불러오기
		var bid = $(this).attr('id');
		
		// 주소값 초기화
		var addr = '/whistle/';
		
		switch(bid){
		case 'hbtn':
			// 기본 주소 사용
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
		}
		
		$(location).attr('href', addr);
	});
	
	// 게시판리스트 - 글 작성 버튼
	$('#wbtn').click(function(){
		$('#pageFrm').attr('action', '/whistle/board/boardWrite.blp');
		$('#pageFrm').submit();
	});
	
	// 파일 추가시 파일박스 늘어남
	$('#filebox').on('change', '.upfile', function(e){
		var txt = $(this).val();
		var len = $('.upfile').length;
		if(!txt && len > 1){
			$('#img' + $(this).attr('id').substring(4)).remove();
			$(this).remove();
			if($('.pic').length == 0){
				$('#previewbox').slideUp(100);
			}
		} else {
			$('#filebox').append('<input type="file" class="w3-input w3-border w3-margin-bottom upfile">');
			
			$('#previewbox').stop().slideUp(300, function(){
				
				var box = document.createElement('div');
				$(box).attr('class', 'inblock picbox');
				var img = document.createElement('img');
				$(img).attr('class', 'pic');
				var path = URL.createObjectURL(e.target.files[0]);
				$(img).attr('src', path);
				$(box).append($(img));
				$('#preview').append($(box));
				
				var cnt = $('.picbox').length;
				for(i = 1; i <= cnt ; i++ ){
					$('.picbox').eq(i-1).attr('id', 'img' + i);
				}
				$('#previewbox').stop().slideDown(300);
			});
		}
		len = $('.upfile').length;
		for(i = 1; i < len ; i++ ){
			$('.upfile').eq(i-1).attr('id', 'file' + i);
			$('.upfile').eq(i-1).attr('name', 'file' + i);
		}
	});
	
	// 글 작성 페이지 - 작성 처리 요청
	$('#wpbtn').click(function(){
		
		// 유효성 검사
		var title = $('#title').val();
		if(!title) {
			alert('제목을 입력하세요.');
			$('#title').focus();
			return;
		}
		var body = $('#body').val();
		if(!body){
			alert('본문을 입력하세요.');
			$('#body').focus();
			return;
		}
		
		var el = $('input[type="file"]');
		// 입력된 파일 없으면 name 값 제거
		for(i = 0 ; i < $(el).length ; i++ ){
			var tmp = $(el).eq(i).val();
			if(!tmp){
				$(el).eq(i).prop('disabled', true);
			}
		}
		
		$('.upfile').last().prop('disabled', true);
		
		$('#frm').submit();
	});
	
	// 게시글 div 태그 클릭시
	$('.brdList').click(function(){
		// 클릭된 게시글 id 불러오기
		var sno = $(this).attr('id');
		
		// name=bno 태그에 입력
		$(document.frm.bno).val(sno);
		
		// 전송!
		$('#frm').submit();
	});
});
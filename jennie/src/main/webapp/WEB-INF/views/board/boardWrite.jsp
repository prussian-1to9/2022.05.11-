<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pink 게시판 글쓰기</title>
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/user.css">
<script type="text/javascript" src="/whistle/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/whistle/resources/js/jennie/board.js"></script>
<style type="text/css">
	label {
		font-size: 14pt;
		font-weight: bold;
		color: gray;
		padding-right: 20px;
		text-align: right;
	}
	input[type="file"].w3-margin-bottom {
		margin-bottom: 5px!important;
	}
</style>
</head>
<body>
	<div class="w3-content mxw700">
		<h1 class="w3-blue w3-padding w3-center w3-card-4">게시글 작성</h1>
		<form method="POST" action="/whistle/board/boardWriteProc.blp" encType="multipart/form-data"
				id="frm" name="frm" class="w3-col w3-card-4 w3-padding">
			<%-- 
				우리는 이제까지, 서버와 통신시 Parameter(String) 방식의 통신을 했으나
				파일 전송의 경우는 Stream 방식으로 전송이 이루어져야 한다.
				
				클라이언트가 Stream 방식으로 서버에게 데이터를 전달할 수 있게 하려면
				form 태그 속성에
					
					encType="multipart/form-data"
				
				라고 기입해야지만 Stream 방식 전송이 가능하다.
			--%>
			<div class="w3-col w3-margin-top w3-margin-bottom pdr20">
				<label for="title" class="w3-col m2">Title</label>
				<input type="text" class="w3-col m10 w3-input w3-border">
			</div>
			<div class="w3-col w3-margin-bottom pdr20">
				<label class="w3-col m2">File</label>
				<div class="w3-col m10">
					<input type="file" id="file1" name="file1" class="w3-input w3-border w3-margin-bottom">
					<input type="file" id="file2" name="file2" class="w3-input w3-border w3-margin-bottom">
					<input type="file" id="file3" name="file3" class="w3-input w3-border w3-margin-bottom">
				</div>
			</div>
			<div class="w3-col pdr20">
				<label for="body" class="w3-col m2">글본문</label>
				<div class="w3-col m10">
					<textarea class="w3-col w3-input w3-padding w3-border w3-margin-bottom" rows="10" style="resize: none;"></textarea>
				</div>
			</div>
		</form>
		
		<div class="w3-col w3-margin-top w3-card-4">
			<div class="w3-third w3-button w3-green" id="listbtn">리스트</div>
			<div class="w3-third w3-button w3-blue" id="rbtn">리셋</div>
			<div class="w3-third w3-button w3-deep-orange" id="wpbtn">글등록</div>
		</div>
	</div>
</body>
</html>
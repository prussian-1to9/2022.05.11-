<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 글쓰기</title>
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/user.css">
<script type="text/javascript" src="/whistle/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/whistle/resources/js/jennie/gBoardWrite.js"></script>
<style type="text/css">
	.w3-button{
		padding:1px 16px;
	}
	.box120{
		width:135px;
	}
</style>
</head>
<body>
	<div class="w3-content mxw650 w3-margin-top">
		<%-- 페이지 header --%>
		<header class="w3-col w3-card-4 mgb20">
			<h1 class="w3-pink w3-center w3-padding mg0">Pink 방명록</h1>
			<nav class="w3-bar w3-pale-blue">
				<div class="w3-col w150 w3-button w3-small w3-green" id="hbtn">Home</div>
				<div class="w3-col w150 w3-button w3-small w3-lime w3-right" id="obtn">Logout</div>
			</nav>
		</header>
		
		<form method="POST" action="/whistle/guestBoard/gBoardWriteProc.blp" name="frm" id="frm"
			class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-padding mgb20">
			<input type="hidden" name="mno" value="${DATA.mno}">
			<div class="w3-col box120 pdAll10 w3-border-right">
				<img src="/whistle/resources/img/avatar/${DATA.savename}" class="inblock avtBox100 w3-border w3-border-grey">			
			</div>
			<div class="w3-rest w3-padding">
				<div class="w3-col w3-border-bottom">
					<span class="mgb10 ft10"><b>${SID}</b></span>
					<span class="w3-button w3-right w3-blue w3-hover-pale-blue mgb10 ft10">등록</span>
				</div>
				<div class="w3-col w3-margin-top">
					<textarea name="body" id="body"
						class="w3-col w3-padding ft12 w3-border-blue" style="resize:none; height:100%;"></textarea>
				</div>
			</div>
			<%-- list 돌아갈 때 기억하는 용도 --%>
			<input type="hidden" id="nowPage" name="nowPage" value="${param.nowPage}">
		</form>
		<%-- 버튼 --%>
		<div class="w3-col w3-card-4">
			<div class="w3-third w3-button w3-green w3-hover-lime" id="lbtn">List</div>
			<div class="w3-third w3-button w3-yellow w3-hover-pale-yellow" id="rbtn">Reset</div>
			<div class="w3-third w3-button w3-deep-orange w3-hover-orange" id="wbtn">Apply</div>
		</div>
	</div>
</body>
</html>
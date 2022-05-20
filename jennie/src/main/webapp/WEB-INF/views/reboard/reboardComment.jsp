<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pink 댓글 작성</title>
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/user.css">
<script type="text/javascript" src="/whistle/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/whistle/resources/js/jennie/reboard.js"></script>
<style type="text/css">
	.w3-button {
		padding: 1px 16px;
	}
	.box120 {
		width: 135px;
		height: auto;
	}
	.mid {
		position: relative;
		top: 5px;
		right:5px;
	}
</style>
</head>
<body>
	<div class="w3-content mxw750 w3-margin-top">
		<%-- 페이지 header --%>
		<header class="w3-col w3-card-4 mgb20">
			<h1 class="w3-pink w3-center w3-padding mg0">Pink 댓글 작성</h1>
			<nav class="w3-bar w3-pale-blue">
				<div class="w3-col w150 w3-button w3-small w3-green menubtn" id="hbtn">Home</div>
<c:if test="${empty SID}">
				<div class="w3-col w150 w3-button w3-small w3-pale-yellow w3-right menubtn" id="lbtn">Login</div>
				<div class="w3-col w150 w3-button w3-small w3-lime w3-right menubtn" id="jbtn">Join</div>
</c:if>
<c:if test="${not empty SID}">
				<div class="w3-col w150 w3-button w3-small w3-pale-yellow w3-right menubtn" id="wbtn">글 작성</div>
				<div class="w3-col w150 w3-button w3-small w3-lime w3-right menubtn" id="obtn">Logout</div>
</c:if>
			</nav>
		</header>
		
		<%-- 페이지 body --%>
		<%-- 얘가 글 한개 박스임 --%>
		<div class="w3-col" style="padding-left: ${data.step * 70}px">
			<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-padding">
				<div class="w3-col box120 pdAll10 w3-border-right">
					<img src="/whistle/resources/img/avatar/${DATA.avatar}" class="inblock avtBox100 w3-border w3-border-grey">
					<span class="w3-col w3-center ft10 mid"><b>${SID}</b></span>
				</div>
				<div class="w3-rest w3-padding">
					<div class="w3-col w3-border-bottom">
						<span class="w3-col w3-left mgb10 ft10"><b>원글 내용 : </b>${DATA.body}${DATA.body.length() le 10 ? '': '...'}</span>
					</div>
					<form method="POST" action="/whistle/reboard/reboardWriteProc.blp" id="frm"
						name="frm" class="w3-col w3-margin-top">
						<textarea class="w3-col w3-padding ft12" id="body" name="body" style="resize:none;"></textarea>
						<%-- 전송용 태그 --%>
						<input type="hidden" id="nowPage" name="nowPage" value="${param.nowPage}">
						<input type="hidden" id="mno" name="mno" value="${DATA.mno}">
						<input type="hidden" id="upno" name="upno" value="${DATA.upno}">
					</form>
				</div>
			</div>
		</div>
		
		<%-- 버튼들 --%>
		<div class="w3-col w3-margin-top">
			<div class="w3-third w3-button w3-yellow" id="listbtn">List</div>
			<div class="w3-third w3-button w3-lime" id="rbtn">Reset</div>
			<div class="w3-third w3-button w3-blue" id="cmtbtn">Apply</div>
		</div>

	</div>
</body>
</html>
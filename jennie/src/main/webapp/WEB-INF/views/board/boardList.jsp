<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pink 게시판</title>
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/user.css">
<script type="text/javascript" src="/whistle/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/whistle/resources/js/jennie/board.js"></script>
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
	<div class="w3-content mxw700 w3-margin-top">
		<%-- header --%>
		<header class="w3-col w3-card-4 mgb20">
			<h1 class="w3-pink w3-center w3-padding mg0">파일 게시판</h1>
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
			
		<%-- 본문 --%>
		<div class="w3-col w3-white w3-padding w3-card-4">
			<div class="w3-col w3-light-grey w3-center w3-border">
				<div class="w3-col m3">
					<div class="w3-col m5 w3-border-right">글번호</div>
					<div class="w3-col m7 w3-border-right">작성자</div>
				</div>
				<div class="w3-col m4 w3-border-right">글제목</div>
				<div class="w3-col m3 w3-border-right">작성일</div>
				<div class="w3-col m1 w3-border-right">클릭수</div>
				<div class="w3-col m1">파일</div>
			</div>
			<div class="w3-col w3-white w3-center w3-border-bottom">
				<div class="w3-col m3">
					<div class="w3-col m5 w3-border-right">100001</div>
					<div class="w3-col m7 w3-border-right">작성자</div>
				</div>
				<div class="w3-col m4 w3-border-right">글제목</div>
				<div class="w3-col m3 w3-border-right">작성일</div>
				<div class="w3-col m1 w3-border-right">클릭수</div>
				<div class="w3-col m1">파일</div>
			</div>
		</div>
	</div>
</body>
</html>
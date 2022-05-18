<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pink 방명록</title>
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/user.css">
<script type="text/javascript" src="/whistle/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/whistle/resources/js/jennie/gboard.js"></script>
<style type="text/css">
	.w3-button{
		padding:1px 16px;
	}
	.box120{
		width: 135px;
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
<c:if test="${empty SID}">
				<div class="w3-col w150 w3-button w3-small w3-pale-yellow w3-right" id="lbtn">Login</div>
				<div class="w3-col w150 w3-button w3-small w3-lime w3-right" id="jbtn">Join</div>
</c:if>
<c:if test="${not empty SID}">
	<c:if test="${CNT eq 0}">
				<div class="w3-col w150 w3-button w3-small w3-pale-yellow w3-right" id="wbtn">글 작성</div>
	</c:if>
				<div class="w3-col w150 w3-button w3-small w3-lime w3-right" id="obtn">Logout</div>
</c:if>
			</nav>
		</header>
		
		<%-- 페이지 body --%>
<c:forEach var="data" items="${LIST}">
		<%-- 얘가 글 한개 박스임 --%>
		<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-padding">
			<%-- 아바타 박스 --%>
			<div class="w3-col box120 pdAll10 w3-border-right">
				<img src="/whistle/resources/img/avatar/${data.avatar}" class="inblock avtBox100 w3-border w3-border-grey">			
			</div>
			<div class="w3-rest w3-padding">
				<div class="w3-col w3-border-bottom">
					<span class="mgb10 ft10"><b>${data.id}</b></span>
					<span class="w3-right mgb10 ft10">${data.sdate}</span>
				</div>
				<div class="w3-col w3-margin-top">
					<span class="w3-col w3-padding ft12">${data.body}</span>
				</div>
			</div>
		</div>
</c:forEach>

		<%-- 페이지 처리 --%>
		<div class="w3-center">
			<div class="w3-bar w3-border w3-margin-top w3-margin-bottom">
		<c:if test="${PAGE.startPage eq 1}"><%-- 1페이지의 경우, 이전페이지로 가면 안됨! --%>
				<div class="w3-bar-item w3-light-grey">&laquo;</div>
		</c:if>
		<c:if test="${PAGE.startPage ne 1}">
				<div class="w3-bar-item w3-button w3-hover-blue pbtn" id="${PAGE.startPage - 1}">&laquo;</div>
		</c:if>
		<c:forEach var="page" begin="${PAGE.startPage}" end ="${PAGE.endPage}">
			<c:if test="${page eq PAGE.nowPage }">
				<div class="w3-bar-item w3-light-grey">${page}</div>
			</c:if>
			<c:if test="${page ne PAGE.nowPage }">
				<div class="w3-bar-item w3-button w3-hover-blue pbtn" id="${page}">${page}</div>
			</c:if>
		</c:forEach>
		<c:if test="${PAGE.endPage eq PAGE.totalPage}"><%-- 마지막페이지면 다음 페이지로 갈 수 없음! --%>
				<div class="w3-bar-item w3-light-grey">&raquo;</div>
		</c:if>
		<c:if test="${PAGE.endPage ne PAGE.totalPage}">
				<div class="w3-bar-item w3-button w3-hover-blue pbtn" id="${PAGE.endPage + 1}">&raquo;</div>
		</c:if>
			</div>
		</div>
		
	<%-- 전송용 form --%>
	<form method="POST" action="/whistle/guestBoard/gBoardList.blp" id="frm" name="frm">
		<input type="hidden" id="nowPage" name="nowPage" value="${PAGE.nowPage}">
	</form>
	</div>
</body>
</html>
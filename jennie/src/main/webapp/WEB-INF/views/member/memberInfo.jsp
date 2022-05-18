<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Info</title>
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/base.css">
<script type="text/javascript" src="/whistle/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/whistle/resources/js/jennie/memberInfo.js"></script>
<style type="text/css">
	p{
		margin-top: 2px;
		margin-bottom: 2px;
	}
</style>
</head>
<body>
<%-- 부가정보 전달용 태그 --%>
	<form method="POST" action="" id="frm" name="frm">
		<input type="hidden" id="smno" name="mno">
		<input type="hidden" id="sid" name="id">
	</form>

	<div class="w3-content w3-center mx650">
<c:if test="${DATA.gen ne 'F'}">
		<h1 class="w3-blue w3-center w3-padding w3-card-4">
	<c:if test="${DATA.id eq SID}">
			My Information
	</c:if>
	<c:if test="${DATA.id ne SID}">
			${DATA.name} 회원 정보
	</c:if>
		</h1>
</c:if>
<c:if test="${DATA.gen eq 'F'}">
		<h1 class="w3-pink w3-center w3-padding w3-card-4">
	<c:if test="${DATA.id eq SID}">
			My Information
	</c:if>
	<c:if test="${DATA.id ne SID}">
			${DATA.name} 회원 정보
	</c:if>
		</h1>
</c:if>
		<div class="w3-content" style="border:1px solid #2196F3; height:200px;">
			<div class="w3-col m4 w3-border-right" style="height:200px;">
				<img src="/whistle/resources/img/avatar/img_avatar${DATA.ano - 10}.png" width="170px" height="170px"
					class="w3-border w3-margin" id="avtimg">
			</div>
			<div class="w3-col m8">
				<p class="w3-col m3 w3-right-align">회원번호 : </p>
				<p class="w3-col m9 w3-left-align" id="mno">${DATA.mno}</p>
				<p class="w3-col m3 w3-right-align">회원이름 : </p>
				<p class="w3-col m9 w3-left-align">${DATA.name}</p>
				<p class="w3-col m3 w3-right-align">아이디 : </p>
				<p class="w3-col m9 w3-left-align" id="id">${DATA.id}</p>
				<p class="w3-col m3 w3-right-align">메일 : </p>
				<p class="w3-col m9 w3-left-align">${DATA.mail}</p>
				<p class="w3-col m3 w3-right-align">전화번호 : </p>
				<p class="w3-col m9 w3-left-align">${DATA.tel}</p>
				<p class="w3-col m3 w3-right-align">가입일 : </p>
				<p class="w3-col m9 w3-left-align">${DATA.jdate}</p>
				<p class="w3-col m3 w3-right-align">성별 : </p>
				<p class="w3-col m9 w3-left-align">${DATA.gen eq "F" ? "여자" : "남자"}</p>
			</div>
		</div>
		<div class="w3-margin-top"><!-- 버튼 -->
			<div class="w3-third w3-blue w3-button" id="lbtn">Member List</div>
			<div class="w3-third w3-yellow w3-button" id="ebtn">Edit</div>
			<div class="w3-third w3-red w3-button" id="dbtn">회원 탈퇴</div>
		</div>
	</div>
</body>
</html>
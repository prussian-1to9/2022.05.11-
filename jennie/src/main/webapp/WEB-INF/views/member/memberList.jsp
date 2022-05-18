<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/base.css">
<script type="text/javascript" src="/whistle/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/whistle/resources/js/jennie/memberList.js"></script>
</head>
<body>
<%-- 데이터 전송용 폼태그 --%>
	<form method="POST" action="/whistle/member/memberInfo.blp" name="frm" id="frm">
		<input type="hidden" name="mno" id="mno">
	</form>
	
	<div class="w3-content mx650 w3-center">
		<!-- 타이틀 태그 -->
		<h1 class="w3-pink w3-padding w3-card-4">Jennie Member List</h1>
		<div class="w3-col w3-margin-top">
<c:forEach var="data" items="${LIST}">
<%-- 회원 이름당 버튼 하나 씩 출력 --%>
			<div class="box w150 w3-button w3-blue ml10 mb5 lbtn" id="${data.mno}">${data.name}</div>
<%-- lbtn 클래스는 이벤트 등록용 클래스임! --%>
</c:forEach>		
		</div>
		<div class="w3-col w3-margin-top">
			<div class="w3-col w3-button w3-orange w3-hover-amber w3-card-4" id="hbtn">home</div>
		</div>
	</div>
</body>
</html>
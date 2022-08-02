<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ include file="../default/header.jsp" %>
	<div class="wrap">
		<div style="width: 300px; margin: 0 auto;">
			<h3>${info.id } 정보</h3>
			<label>아이디</label><b>${info.id }</b><hr>
			<label>비밀번호</label><b>${info.pw }</b><hr>
			<label>주소</label><b>${info.addr }</b><hr>
			<c:if test="${info.id == loginUser }">
			<button type="button" onclick="location.href='modify_form?id=${info.id}'">수정</button>
			<button type="button" onclick="location.href='delete?id=${info.id}'">삭제</button>
			</c:if>
		</div>
	</div>
</body>
</html>
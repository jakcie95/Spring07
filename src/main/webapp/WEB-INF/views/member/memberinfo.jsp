<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%@ include file="../default/header.jsp" %>
<h3 align="center">회원정보</h3>
<div class="wrap">
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>주소</th>
		</tr>
		<c:forEach var="dto" items="${ list }">
			<tr>
				<td>
					<a href="${contextPath}/member/info?id=${dto.id}">
						${dto.id }
					</a>
				</td>
				<td>${dto.pw }</td>
				<td>${dto.addr }</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>







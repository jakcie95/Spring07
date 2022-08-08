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
<%@ include file="../default/header.jsp" %>
<div class="wrap" align="center">
<h1>게시판</h1>
<table border="1">
	<tr>
		<th>번호</th>
		<th>id</th>
		<th>제목</th>
		<th>날짜</th>
		<th>조회수</th>
		<th>image_file_name</th>
	</tr>
	<tr>
		<c:if test="${list.size() == 0}">
			<tr>
				<th colspan="6">등록된 글이 없습니다.</th>
			</tr>
		</c:if>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.write_no }</td>
				<td>${dto.id }</td>
				<td>${dto.title }</td>
				<td>${dto.savedate }</td>
				<td>${dto.hit }</td>
				<td>${dto.image_file_name }</td>
			</tr>
		</c:forEach>
		<tr>
		<td colspan="6">
			<a href="${contextPath }/board/writeForm">글작성</a>	
		</td>
		</tr>
</table>
</div>
</body>
</html>
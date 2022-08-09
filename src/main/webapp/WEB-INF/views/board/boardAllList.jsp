<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../default/header.jsp" />
		<h3 align="center">게시판</h3>
		<div style="margin: auto; width: 500px;">
		<div class="wrap">
			<table border="1">
				<tr>
					<th>번호</th>
					<th>id</th>
					<th>제목</th>
					<th>날짜</th>
					<th>조회수</th>
					<th>image_file_name</th>
				</tr>
				<c:if test="${boardList.size() == 0 }">
					<tr>
						<th colspan="6">저장 데이터 없음</th>
					</tr>
				</c:if>
				<c:forEach var="dto" items="${boardList }">
					<tr>
						<td>${dto.writeNo }</td>
						<td>${dto.id }</td>
						<td>
						<a href="contentView?title=${dto.title }">
						${dto.title }
						</a>
						</td>
						<td>${dto.saveDate }</td>
						<td>${dto.hit }</td>
						<td>${dto.imageFileName }</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="6"><a href="${contextPath }/board/writeForm">글작성</a>
					</td>
				</tr>
			</table>
		</div>
		</div>
</body>
</html>

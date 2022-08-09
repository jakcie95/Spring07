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
	<h3 align="center">개인정보</h3>
	<div style="margin: auto; width: 700px;">
	<div class="wrap">
		<form action="" method="post">
		
		<table border="1">
			<tr>
				<th>글 번호</th>
				<td>${dto.writeNo }</td>
				<th>작성자</th>
				<td>${dto.id }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.title }</td>
				<th>등록일자</th>
				<td>${dto.saveDate }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td width="300px" height="100px">${dto.content }</td>
				<td width="300px" height="100px" colspan="2">
				<img src="download?file=${dto.imageFileName }" width="100px" height="100px">
				</td>
			</tr>
			<tr>
				<th colspan="4">
					<c:if test="${dto.id == loginUser }">
					<button type="button" onclick="">수정하기</button>
					<button type="button" onclick="location.href='delete?file=${dto.imageFileName }&title=${dto.title}'">
					삭제하기
					</button>
					</c:if>
					<button type="button" onclick="">답글달기</button>
					<button type="button" "onclick="">리스트로 돌아가기</button>
				</th>
			</tr>
		</table>
		</form>
	</div>
	</div>
</body>
</html>
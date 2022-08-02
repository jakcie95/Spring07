<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/daum_post.js"></script>
	
	
	
	<%@ include file="../default/header.jsp" %>
	<div align="center">
		<form action="register" method="post">
			<input type="text" name="id" placeholder="아이디"><br>
			<input type="text" name="pw" placeholder="비밀번호"><br>
			<input type="text" readonly id="addr1" name="addr" placeholder="우편번호">
			<button type="button" onclick="daumPost()">우편찾기</button><br>
			<input type="text" readonly id="addr2" name="addr" placeholder="주 소">
			<input type="text" name="addr" id="addr3" placeholder="상 세 주 소"><br>
			<input type="submit" value="가입">
		</form>
	</div>
</body>
</html>
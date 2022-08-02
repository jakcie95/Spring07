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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../default/header.jsp" %>
<div align="center">
	<form action="modify" method="post">
		<input type="text" value="${dto.id }" name="id" readonly><br>
		<input type="password" value="******" name="new_pw"><br>
		<input type="hidden" value="${dto.pw }" name="old_pw"><br>
		<input type="text" id="addr1"value="${addrs[0] }" readonly name="addr"><br>
		<button type="button" onclick="daumPost()">우편번호 찾기</button>
		<input type="text" id="addr2" value="${addrs[1] }" readonly name="addr"><br>
		<input type="text" id="addr3" value="${addrs[2] }" name="addr"><br>
		<input type="submit" value="수정">
		<a href="#" onclick="history.back()">이전으로 이동</a>
	</form>
</div>
</body>
</html>
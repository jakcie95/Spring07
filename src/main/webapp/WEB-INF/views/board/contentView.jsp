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
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:import url="../default/header.jsp" />
<div style="width: 700px; margin: 0 auto;">
 <h3 align="center">개인 정보</h3>
<table border="1">
   <tr>
      <th width="100">글 번호</th> <td width="200">${dto.writeNo}</td> 
      <th width="100">작성자</th>  <td width="200">${dto.id}</td>
   </tr>
   <tr>
      <th>제목</th> <td>${dto.title}</td> 
      <th>등록일자</th> <td>${dto.saveDate}</td>
   </tr>
   <tr>
      <th>내용</th><td>${dto.content}</td> 
      <td colspan="2">
         <c:if test="${ dto.imageFileName == 'nan' }"> <b>이미지가 없습니다</b> </c:if>
         <c:if test="${ dto.imageFileName != 'nan' }">
            <img width="200px" height="100px" 
               src="${contextPath}/board/download?imageFileName=${dto.imageFileName}">
         </c:if>
      </td>
   </tr>
   <tr>
      <td colspan="4" align="center">
         <c:if test="${ loginUser == dto.id }">
            <input type="button" onclick="location.href='${contextPath}/board/modifyForm?writeNo=${dto.writeNo}'" value="수정하기"> 
            <input type="button" onclick="location.href='${contextPath}/board/delete?writeNo=${dto.writeNo}&imageFileName=${dto.imageFileName}'" value="삭제하기">
         </c:if>
         <input type="button" onclick="" value="답글달기"> 
         <input type="button" onclick="location.href='${contextPath}/board/boardAllList'" value="리스트로 돌아가기">
      </td>
   </tr>
</table>
</div>
</body>
</html>





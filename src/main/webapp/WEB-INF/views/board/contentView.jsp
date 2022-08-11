<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#modal_wrap{
		display: none;
		position: fixed; z-index: 9; margin: auto;
		top: 0; left: 0; right: 0; width: 100%; height: 100%;
		background-color: rgba(0, 0, 0, 0.9);
	}
	#first{
		display: none;
		position: fixed; z-index: 10; margin: auto;
		top: 30px; left: 0; right: 0; width: 350px; height: 300px;
		background-color: rgba(0, 216, 255, 0.4);
	}
	
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function slidClick(){
	$("#first").slideDown("slow");
	$("#modal_wrap").show()
}
function slide_hide(){
	$("#title").val("")
	$("#content").val("")
	
	$("#first").hide();
	$("#modal_wrap").hide();
}
function rep(){
	/*
	let form={write_group : $("#write_no").val(),
			title : $("title").val(),
			content : $("#content").val()
			}
	console.log(form)
	*/
	let form ={}
	let arr = $("#frm").serializeArray();
	console.log(arr)
	
	for(i=0; i<arr.length; i++){
		form[arr[i].name] = arr[i].value
		
	}
	console.log(form)
	$.ajax({
		url : "addReply", type : "post",
		data : JSON.stringify( form ),
		contentType : "application/json;charset=utf-8",
		success : function(){
			alert("답글이 달렸습니다.");
			slide_hide();
			replyData();
		}
	})
}
function replyData(){
	$.ajax({
		//url : "replyData??writeNo="+${dto.writeNo}
		//컨트롤러 매개변수 int writeNo 받으면 된다.
		
		url : "replyData/"+${dto.writeNo}, type : "get",
		data : "json",
		success : function(rep){
			console.log(rep)
			let html = ""
			for(i=0; i <rep.length; i++){
				let date = new Date(rep[i].write_date) //sql형식 날짜데이터를 자바스크립트형식으로 저장
				let wd = date.getFullYear()+"년";
				wd += (date.getMonth()+1) + "월"; //월은 0부터 시작
				wd += (date.getDate()) + "일";
				wd += (date.getHours()) + "시";
				wd += (date.getMinutes()) + "분";
				wd += (date.getSeconds()) + "초";
				html += "<div align='left'><b>아이디: </b>"+rep[i].id+"님/"
				html += "<b>작성일 : </b>"+wd+"<br>"
				html += "<b>제목 : </b>"+rep[i].title+"<br>"
				html += "<b>내용 : </b>"+rep[i].content+"<hr></div>"
			}
			$("#reply").html(html)
		}
	})
	
}
replyData(); //최초 한번 호출 body에 onload태그도 가능
</script>

</head>
<body>

<div id="modal_wrap"><!-- 배경  -->
<div id="first"><!-- 댓글 입력창  -->
	<div style="width: 250px; margin: auto; padding-top: 20px;">
	<form id="frm">
		<input type="hidden" name="write_no" id="write_no"
								value="${dto.writeNo }">
			<b>답글 작성 페이지</b><hr>
			<b>작성자 :</b>${loginUser }<hr>
			<b>제목</b><br>
			<input type="text" id="title" name="title" size="30"><hr>
			<b>내용</b><br>
			<textarea rows="5" cols="30" name="content" id="content"></textarea>					
			<hr>
			<button type="button" onclick="rep()">답글</button>
			<button type="button" onclick="slide_hide()">취소</button>
	</form>
</div>
</div>
</div>

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
            <input type="button" onclick="location.href='${contextPath}/board/modifyForm?writeNo=${dto.writeNo}'" 
            value="수정하기"> 
            <input type="button" onclick="location.href='${contextPath}/board/delete?writeNo=${dto.writeNo}&imageFileName=${dto.imageFileName}'" 
            value="삭제하기">
         </c:if>
         <input type="button" onclick="slidClick()" value="답글달기"> 
         <input type="button" onclick="location.href='${contextPath}/board/boardAllList'" value="리스트로 돌아가기">
      </td>
   </tr>
   <tr>
   		<td colspan="4">
   			<div id="reply"></div>
   		</td>
   </tr>
</table>
</div>
</body>
</html>





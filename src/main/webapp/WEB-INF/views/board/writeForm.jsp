<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
   function readURL(input) {
      var file = input.files[0] //파일에 대한 정보
      console.log(file)
      if (file != '') {
         var reader = new FileReader();
         reader.readAsDataURL(file); //파일의 정보를 토대로 파일을 읽고 
         reader.onload = function (e) { // 파일 로드한 값을 표현한다
          //e : 이벤트 안에 result값이 파일의 정보를 가지고 있다.
          console.log( e.target.result) 
          $('#preview').attr('src', e.target.result);
          }
      }
  }  
</script>
<body>
<%@ include file="../default/header.jsp" %>
<h1 align="center">글쓰기</h1>
<div class="wrap">
	<form action="write" method="post"
								enctype="multipart/form-data">
		<h4>작성자</h4><br>
			<input type="text" name="id">
		<hr>
		<h4>제목</h4><br>
			<input style="width:500px;" type="text" name="title">
		<hr>
		<h4>내용</h4><br>
			<textarea name="content" cols="100" rows="10"></textarea>
		<hr>
		<h4>이미지 파일 첨부</h4>
			<input type="file" name="image_file_name" onchange="readURL(this);"/>
			<img id="preview" src="#" width=100 height=100 alt="선택된 이미지가 없습니다"/>
		<hr>
			<input type="submit" value="글쓰기">
			<button type="button" onclick="location.href='${contextPath }/board/boardAllList'">목록보기</button>
	</form>
</div>
</body>
</html>
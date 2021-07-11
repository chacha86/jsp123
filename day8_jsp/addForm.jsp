<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp_day7.ArticleDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>게시물 등록</h1>

<form action="/JSP/article8/add">
	<div>
		<div>
			제목<br>
			<input type="text" placeholder="제목을 입력해주세요." name="title">
		</div>
		<div>
			내용<br>
			<textarea name="body"></textarea>
		</div>
		<input type="submit"/>
	</div>
</form>
<a href="/JSP/article8/list">목록으로</a>
</body>
</html>
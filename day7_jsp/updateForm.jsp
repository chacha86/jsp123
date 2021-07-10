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

<h1>게시물 수정</h1>

<form action="/JSP/article7/update">
	<div>
		<div>
			제목<br>
			<input type="text" placeholder="제목을 입력해주세요." name="title" value="${ article.title }">
		</div>
		<div>
			내용<br>
			<textarea name="body">${ article.body }</textarea>
		</div>
		<input type="hidden" name="id" value="${ article.id }"/>
		<input type="submit"/>
	</div>
</form>
<a href="http://127.0.0.1:9000/JSP/jsp/day7/list.jsp">목록으로</a>
</body>
</html>
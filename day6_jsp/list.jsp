<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp_day6.ArticleDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jsp_day6.Article" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 게시물 목록 </h1>
<a href="/JSP/jsp/day6/addForm.jsp">글쓰기</a>

<c:choose>
	<c:when test="${ loginedUser == null}">
		<a href="/JSP/article6/login">로그인</a> <br>
	</c:when>
	<c:otherwise>
		<c:out value="${ loginedUser.name }"/> <a href="/JSP/article6/doLogout">로그아웃</a> <br>
	</c:otherwise>
</c:choose>

<!-- 출력부 -->
<c:forEach items="${articles}" var="article">
	번호 : ${article.id} <br>
	제목 : ${article.title} <br>
	내용 : ${article.body} <br>
	<hr>
</c:forEach>

</body>
</html>
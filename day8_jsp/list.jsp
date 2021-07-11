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
<%@ include file="topMenu.jsp" %>
<!-- 출력부 -->
<c:forEach items="${articles}" var="article">
	번호 : ${article.id} <br>
	제목 : <a href="/JSP/article8/detail?articleId=${article.id}">${article.title} </a><br>
	<hr>
</c:forEach>
<a href="/JSP/article8/addForm">글쓰기</a>
</body>
</html>
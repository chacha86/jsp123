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

<a href="/JSP/article8/list?pageNo=${ startPageNo - 1 }">이전</a>
<c:forEach begin="${startPageNo}" end="${endPageNo}" var="num">
	<c:choose>
		<c:when test="${ num == pageNo }">
			<a href="/JSP/article8/list?pageNo=${num}">[${num}]</a>
		</c:when>
		<c:otherwise>
			<a href="/JSP/article8/list?pageNo=${num}">${num}</a>	
		</c:otherwise>
	</c:choose>
</c:forEach>
<a href="/JSP/article8/list?pageNo=${ endPageNo + 1 }">다음</a><br>
<a href="/JSP/article8/addForm">글쓰기</a>
</body>
</html>
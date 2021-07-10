<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세보기</title>

</head>
<body>
	<h1>게시물 상세보기</h1>
	<%@ include file="topMenu.jsp" %>
	<div>
		제목  : <c:out value="${ article.title }"/>
	</div>
	<hr>
	<div>
		내용 <br> 
		<c:out value="${ article.body }"/>
	</div>
	<hr>
	<div>
		작성자 : <c:out value="${ article.name }"/>
	</div>
	<hr>
	<c:if test="${ loginedUser != null }">
		<div>
			<a href="/JSP/article7/showUpdate?id=${article.id}">수정</a>
			<a href="/JSP/article7/delete?id=${article.id}">삭제</a>
		</div>	
	</c:if>
</body>
</html>
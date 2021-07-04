<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="#">공지사항</a> <a href="#">자유게시판</a> <a href="#">질문과 답변</a>

	<c:choose>
		<c:when test="${ loginedUser == null}">
			<a href="/JSP/article6/login">로그인</a> <br>
		</c:when>
		<c:otherwise>
			<c:out value="${ loginedUser.name }"/> <a href="/JSP/article6/doLogout">로그아웃</a> <br>
		</c:otherwise>
	</c:choose>
</body>
</html>
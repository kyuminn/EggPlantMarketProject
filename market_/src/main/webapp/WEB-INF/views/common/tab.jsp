<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
		.nav-link{
			font-size:20px;
		}
</style>
</head>
<body>
<c:if test="${!empty category }">
  <ul class="nav nav-tabs">
  <li class="nav-item">
  	<c:if test="${category eq 'CLOTHES'}">
  		<a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/item/search/category?category=CLOTHES">의류</a>
  	</c:if>
  	<c:if test="${category ne 'CLOTHES'}">
    <a class="nav-link" href="${pageContext.request.contextPath}/item/search/category?category=CLOTHES">의류</a>
    </c:if>
  </li>
  
      <li class="nav-item">
  	<c:if test="${category eq 'ELECTRO'}">
  		<a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/item/search/category?category=ELECTRO">가전</a>
  	</c:if>
  	<c:if test="${category ne 'ELECTRO'}">
    <a class="nav-link" href="${pageContext.request.contextPath}/item/search/category?category=ELECTRO">가전</a>
    </c:if>
  </li>

    <li class="nav-item">
  	<c:if test="${category eq 'LIVING'}">
  		<a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/item/search/category?category=LIVING">생활용품</a>
  	</c:if>
  	<c:if test="${category ne 'LIVING'}">
    <a class="nav-link" href="${pageContext.request.contextPath}/item/search/category?category=LIVING">생활용품</a>
    </c:if>
  </li>
  
    <li class="nav-item">
  	<c:if test="${category eq 'ETC'}">
  		<a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/item/search/category?category=ETC">기타</a>
  	</c:if>
  	<c:if test="${category ne 'ETC'}">
    <a class="nav-link" href="${pageContext.request.contextPath}/item/search/category?category=ETC">기타</a>
    </c:if>
  </li>
	</ul>
	<hr/>
</c:if>
<c:if test="${empty category }">
  <ul class="nav nav-tabs">
  <li class="nav-item">
    <a class="nav-link" href="${pageContext.request.contextPath}/item/search/category?category=CLOTHES">의류</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="${pageContext.request.contextPath}/item/search/category?category=ELECTRO">가전</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="${pageContext.request.contextPath}/item/search/category?category=LIVING">생활용품</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="${pageContext.request.contextPath}/item/search/category?category=ETC">기타</a>
  </li>
	</ul>
	<br><br>
</c:if>
</body>
</html>
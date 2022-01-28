<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body>
	<div class="container">
	<h2>마이 페이지</h2>
	<hr/>
	<br>
  	<div class="row">
     	<div class="col-sm-4 col-md-4 col-lg-4 col-xs-4">
     		<h3> 나의 정보</h3>
     		<br>
			${loginMember.name}님
	 		평점 : ${ratingAvg} <br><br>
	 		<input type="button" class="btn btn-secondary" value="프로필 수정" onclick="window.location.href='${pageContext.request.contextPath}/member/edit/${loginMember.id }'"><br><br>
	 		<input type="button" class="btn btn-success" value="주문배송 조회" onclick="window.location.href='${pageContext.request.contextPath}/order/myList/${loginMember.id}'">
	 		<!-- <a href="${pageContext.request.contextPath}/order/myList/${loginMember.id}">주문배송 조회</a><br> -->
     	</div>
     	<div class="col-sm-4 col-md-4 col-lg-4 col-xs-4">
     		<h3>구매</h3><br>
     		<input type="button" class="btn btn-primary" value="찜 목록" onclick="window.location.href='${pageContext.request.contextPath}/wish/list'"><br><br>
     		<input type="button" class="btn btn-primary" value="나의 문의" onclick="window.location.href='${pageContext.request.contextPath}/question/myList/${loginMember.id}'">
     		<!-- 
			<a href="${pageContext.request.contextPath}/wish/list"> 찜 목록</a><br><br>
			<a href="${pageContext.request.contextPath}/question/myList/${loginMember.id}">나의 문의</a><br> -->
     	</div>
     	<div class="col-sm-4 col-md-4 col-lg-4 col-xs-4">
     		<h3>판매</h3><br>
     		      		<input type="button" class="btn btn-primary" value="나의 판매글 목록" onclick="window.location.href='${pageContext.request.contextPath}/item/myList/${loginMember.id}'"><br><br>
     		<input type="button" class="btn btn-primary" value="가지포인트 관리" onclick="window.location.href='${pageContext.request.contextPath}/member/myPoint/${loginMember.id}'">
			<!-- 
			<a href="${pageContext.request.contextPath}/item/myList/${loginMember.id}">나의 판매글 목록</a><br><br>
			<a href="${pageContext.request.contextPath}/member/myPoint/${loginMember.id}">가지포인트 관리</a><br>
			 -->
     	</div>
  	</div>
</div>

</body>
</html>
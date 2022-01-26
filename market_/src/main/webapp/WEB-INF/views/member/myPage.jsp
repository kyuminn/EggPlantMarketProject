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
     	<div class="col-sm-2 col-md-2 col-lg-2 col-xs-2">
     		<h3> 나의 정보</h3>
     		<br>
			${loginMember.name}님
	 		평점 : ${ratingAvg}
	 		<input type="button" value="프로필 수정" onclick="window.location.href='${pageContext.request.contextPath}/member/edit/${loginMember.id }'"><br>
	 					<a href="${pageContext.request.contextPath}/order/myList/${loginMember.id}">주문배송 조회</a><br>
     	</div>
     	<div class="col-sm-4 col-md-4 col-lg-4 col-xs-4">
     		<h3>구매</h3><br>
			<a href="${pageContext.request.contextPath}/wish/list"> 찜 목록</a><br>
			<a href="${pageContext.request.contextPath}/question/myList/${loginMember.id}">나의 문의</a><br>
     	</div>
     	<div class="col-sm-4 col-md-4 col-lg-4 col-xs-4">
     		<h3>판매</h3><br>
			<a href="${pageContext.request.contextPath}/item/myList/${loginMember.id}">나의 판매글 목록</a><br>
			<a href="${pageContext.request.contextPath}/member/myPoint/${loginMember.id}">가지포인트 관리</a><br>
     	</div>
  	</div>
</div>

	<!--  
	<div class="container">
	<h2>마이 페이지</h2>
	<hr/>
	<h3> 나의 정보</h3>
		${loginMember.name}님
	 	평점 : ${ratingAvg}

	<input type="button" value="프로필 수정" onclick="window.location.href='${pageContext.request.contextPath}/member/edit/${loginMember.id }'">
	<hr/>
	<h3>구매</h3>
	<a href="${pageContext.request.contextPath}/like/list"> 찜 목록</a><br>
	<a href="#">주문배송 조회</a><br>
	<a href="${pageContext.request.contextPath}/question/myList/${loginMember.id}">나의 문의</a><br>
	<hr/>
	<h3>판매</h3>
	<a href="${pageContext.request.contextPath}/item/myList/${loginMember.id}">나의 판매글 목록</a><br>
	<a href="#">판매글 질문 관리</a><br>
	<a href="#">배송 관리</a><br>
	<a href="${pageContext.request.contextPath}/member/myPoint/${loginMember.id}">가지포인트 관리</a><br>
	</div>
	-->
</body>
</html>
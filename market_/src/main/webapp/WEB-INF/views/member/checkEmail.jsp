<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>이메일 인증 요청</title>
     <jsp:include page="../common/header.jsp"/>
     <style>
		.wrapper{
			padding-top:30;
			height:auto;
			min-height:100%;
			padding-bottom:10;
			z-index:2;
		}
     </style>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<!-- <h3>${email } 로 인증 메일이 발송되었습니다</h3> -->
			 <c:set var ="email" value="${email}"/>
 				<c:if test ="${fn:contains(email,'naver')}">
 					<h2>
   				 	<a href="<c:url value='https://mail.naver.com'/>">${email}</a></h2>
 				</c:if>
 				<c:if test ="${fn:contains(email,'gmail')}">
    				<h2><a href="<c:url value='https://mail.google.com/mail'/>">>${email}</a></h2>
 				</c:if>
 				<h2>인증 메일을 전송했습니다. 메일함을 확인해주세요!</h2>
		</div>
	</div>
	
	<jsp:include page="../common/footer.jsp"/>


</body>
</html>
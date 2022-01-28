<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
  <head>
	<title>비밀번호 찾기</title>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<jsp:include page="../common/header.jsp"/>
	<link rel="stylesheet" href="/css/login.css">
  </head>

  <body>
<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-4 col-md-offset-4">
            <h2>비밀번호 찾기</h2>
            <hr/>
            <div class="account-wall">
                <img class="profile-img" src="/images/eggPlant.png">
					<!-- <h3> ${userEmail }로 임시 비밀번호가 전송되었습니다</h3> -->
				<c:set var ="email" value="${userEmail}"/>
 				<c:if test ="${fn:contains(email,'naver')}">
 					<h2>
   				 	<a href="<c:url value='https://mail.naver.com'/>">${userEmail}</a></h2>
 				</c:if>
 				<c:if test ="${fn:contains(email,'gmail')}">
    				<h2><a href="<c:url value='https://mail.google.com/mail'/>">>${userEmail}</a></h2>
 				</c:if>
 				<h3>메일로 임시 비밀번호가 전송되었습니다</h3>
                <br>
            </div>
            
        </div>
    </div>
</div>
  </body>

</html>
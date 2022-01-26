<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
  <head>
	<title>이메일 찾기</title>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<jsp:include page="../common/header.jsp"/>
	<link rel="stylesheet" href="/css/login.css">
  </head>

  <body>
<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-4 col-md-offset-4">
            <h1>이메일 찾기</h1>
            <hr/>
            <div class="account-wall">
                <img class="profile-img" src="/images/eggPlant.png">
					나의 이메일: <h3> ${userEmail }</h3>
                	<button class="btn btn-lg btn-primary btn-block" type="button" onclick="window.location.href='${pageContext.request.contextPath}/login'">로그인</button><br>
                	<a href="${pageContext.request.contextPath}/login/find/pwd">비밀번호 찾기</a>
                <br>
                
            </div>
            
        </div>
    </div>
</div>
  </body>

</html>
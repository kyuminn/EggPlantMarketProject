<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
  <head>
	<title>로그인</title>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<jsp:include page="../common/header.jsp"/>
	<link rel="stylesheet" href="/css/login.css">
  </head>

  <body>
<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-4 col-md-offset-4">
            <h1>로그인</h1>
            <hr/>
            <div class="account-wall">
                <img class="profile-img" src="/images/eggPlant.png">
                <form:form class="form-signin" modelAttribute="loginFormData" action="" method="post">
                	<form:input path="email" class="form-control" placeholder="Email"/>
                	<input type="password" name="password" class="form-control" placeholder="Password" required>
                	<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button><br>
                	<form:errors/><br>
                	<label class="checkbox pull-left">
                    	<form:checkbox path="rememberEmail"/>이메일 기억하기
                    </label>
                <br>
                <a href="${pageContext.request.contextPath}/member/add">회원가입 </a>&nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/login/find/email">이메일 찾기</a>&nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/login/find/pwd">비밀번호 찾기</a>
                <hr/>
                <a href="https://kauth.kakao.com/oauth/authorize?client_id=f98f37d47a62be09db4e5ccfed265b61&redirect_uri=http://localhost:8080/myapp/loginCallBack&response_type=code">
            		<img src="/images/kakao_login.png">
            	</a>
                </form:form>
            </div>
            
        </div>
    </div>
</div>
  </body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h1>비밀번호 찾기</h1>
            <hr/>
                <form:form class="form-signin" modelAttribute="findPwdFormData" action="${pageContext.request.contextPath}/login/find/pwd" method="post">
                	<form:input path="email" class="form-control" placeholder="이메일"/><br><br>
                	<form:errors class="errorMsg"/>
                	<button class="btn btn-sm btn-primary btn-block" type="submit">비밀번호 찾기</button><br>
                <hr/>
                </form:form>
            </div>
        </div>
    </div>
  </body>
</html>
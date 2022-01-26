<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>이메일 인증 요청</title>
     <jsp:include page="../common/header.jsp"/>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<h1>${email }</h1>로 인증 메일이 발송되었습니다
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>
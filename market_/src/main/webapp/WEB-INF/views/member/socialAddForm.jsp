<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
	<!-- header 추가 -->
	<jsp:include page="../common/header.jsp"></jsp:include>
    <meta charset="UTF-8">
    <title>회원가입</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        
     <!-- 주소 검색 스크립트 -->
		<script src="/javascript/addrPopup.js"></script>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<style type="text/css">
		.wrapper{
			height:auto;
			min-height:100%;
			padding-bottom:10;
		}
	</style>
</head>
<body>
<div class="wrapper">
	<div class="container"><!-- 좌우측의 공간 확보 -->
			<h1>SNS회원가입</h1>
            <hr/>
            <form:form class="form-horizontal" modelAttribute="member" role="form" method="post" action="${pageContext.request.contextPath}/member/socialAdd">
                <div class="form-group">
                    <label for="provision" class="col-lg-2 control-label">회원가입약관</label>
                    <div class="col-lg-10" id="provision">
                        <textarea class="form-control" rows="8" style="resize:none">
							가지마켓 약관 내용 입력
                        </textarea>
                        <div class="radio">
                            <label>
                                <!-- <input type="radio" id="provisionYn" name="provisionYn" value="Y" autofocus="autofocus"> -->
                                <form:checkbox path="provisionYn"/>
                                동의합니다.
                                <form:errors path="provisionYn"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group" id="divName">
                    <label for="inputName" class="col-lg-2 control-label">이름</label>
                    <div class="col-lg-5">
                        <form:input path="name" class="form-control onlyHangul" id="name" data-rule-required="true" placeholder="한글만 입력 가능합니다." maxlength="15" required="required"/>
                    	<form:errors path="name"/>
                    </div>
                </div>
                
                <div class="form-group" id="divEmail">
                    <label for="inputEmail" class="col-lg-2 control-label">이메일</label>
                    <div class="col-lg-5">
                        <!-- <input type="text" class="form-control" name="email" id="email" value=${email } data-rule-required="true"> -->
                        <form:input path="email" class="form-control" id="email" value="${email }" data-rule-required="true" required="required" readonly="true"/>
                    	<form:errors path="email"/>
                    </div>
                </div>
                
                <div class="form-group" id="divNickname">
                    <label for="inputNickname" class="col-lg-2 control-label">별명</label>
                    <div class="col-lg-5">
                        <!-- <input type="text" class="form-control" name="nickname" id="nickname" data-rule-required="true" maxlength="15" value=${nickname }> -->
                        <form:input path="nickname" class="form-control" id="nickname" value="${nickname }" data-rule-required="true" placeholder="별명" maxlength="15" required="required"/>
                    	<form:errors path="nickname"/>
                    </div>
                </div>

                <div class="form-group" id="divPhoneNumber">
                    <label for="inputPhoneNumber" class="col-lg-2 control-label">휴대폰 번호</label>
                    <div class="col-lg-5">
                        <form:input path="phoneNum" class="form-control onlyNumber" id="phoneNumber" data-rule-required="true" placeholder="-를 제외하고 숫자만 입력하세요." maxlength="11" required="required"/>
                    	<form:errors path="phoneNum"/>
                    </div>
                </div>
                 <div class="form-inline-form-group">
                    <label for="postCode" class="col-lg-2 control-label">우편번호</label>
                    <div class="col-lg-5">
                        <form:input path="postCode" class="form-control"/>
                        <input type="button" class="btn btn-secondary btn-sm" onClick="execDaumPostcode()" value="우편번호 검색">
                    	<form:errors path="postCode"/>
                    </div>
                </div>
                <br>
                <div class="form-group">
                    <label for="roadAddr" class="col-lg-2 control-label">도로명 주소</label>
                    <div class="col-lg-5">
                        <form:input path="roadAddr" class="form-control" required="required"/>&nbsp;
                    	<form:errors path="roadAddr"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="detailAddr" class="col-lg-2 control-label">상세 주소</label>
                    <div class="col-lg-10">
                        <form:input path="detailAddr" class="form-control" required="required"/>&nbsp;
                    	<form:errors path="detailAddr"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <input type="submit" class="btn btn-primary btn-lg" value="가입하기">
                    </div>
                </div>
                <!-- access 경로 보내주기 -->
                <input type="hidden" name="access" value="${access }">
            </form:form>
        </div>
</div>
        <jsp:include page="../common/footer.jsp"/>
</body>
</html>
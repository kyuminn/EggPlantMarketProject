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
        <link rel="stylesheet" href="/css/member.css">
     <!-- 주소 검색 스크립트 -->
		<script src="/javascript/addrPopup.js"></script>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
<div class="wrapper">
	<div class="container"><!-- 좌우측의 공간 확보 -->
			<h1>회원가입</h1>
            <hr/>
            <h3>소셜 계정으로 시작하기</h3>
            	<a href="https://kauth.kakao.com/oauth/authorize?client_id=f98f37d47a62be09db4e5ccfed265b61&redirect_uri=http://localhost:8080/myapp/loginCallBack&response_type=code">
            		<img src="/images/kakao_login.png">
            	</a>&nbsp;
            	<!--<a><img src="#"></a>-->
            <hr/>
            <h3>홈페이지에서 시작하기</h3>
            <!-- form:form tag 안의 form:errors 로 에러 메세지 보여주기 -->	
            <form:form class="form-horizontal" modelAttribute="member" role="form" method="post" action="${pageContext.request.contextPath}/member/add">
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
                                <form:errors path="provisionYn" class="errorMsg"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group" id="divName">
                    <label for="inputName" class="col-lg-2 control-label">이름</label>
                    <div class="col-lg-5">                
                        <!-- <input type="text" class="form-control onlyHangul" name="name" id="name" data-rule-required="true" placeholder="한글만 입력 가능합니다." maxlength="15"> -->
                        <form:input path="name" class="form-control onlyHangul" id="name" data-rule-required="true" placeholder="이름" maxlength="15" required="required"/>
                        <form:errors path="name" class="errorMsg"/>
                    </div>
                </div>
                
                <div class="form-group" id="divEmail">
                    <label for="inputEmail" class="col-lg-2 control-label">이메일</label>
                    <div class="col-lg-5">
                        <!-- <input type="text" class="form-control" name="email" id="email" data-rule-required="true"> -->
                        <form:input path="email" class="form-control" id="email" data-rule-required="true" placeholder="이메일" required="required"/>
                        <form:errors path="email" class="errorMsg"/>
                    </div>
                </div>
                
                <div class="form-group" id="divNickname">
                    <label for="inputNickname" class="col-lg-2 control-label">별명</label>
                    <div class="col-lg-5">
                        <!-- <input type="text" class="form-control" name="nickname" id="nickname" data-rule-required="true" placeholder="별명" maxlength="15"> -->
                        <form:input path="nickname" class="form-control" id="nickname" data-rule-required="true" placeholder="별명" maxlength="15" required="required"/>
                    	<form:errors path="nickname" class="errorMsg"/>
                    </div>
                </div>


                <div class="form-group" id="divPassword">
                    <label for="inputPassword" class="col-lg-2 control-label">패스워드</label>
                    <div class="col-lg-5">
                        
                        <!-- <input type="password" class="form-control" id="password" name="pwd" data-rule-required="true" placeholder="패스워드" maxlength="30"> -->
                    	<form:password path="pwd" class="form-control" id="password"  data-rule-required="true" placeholder="패스워드" maxlength="30" required="required"/>
                    	<form:errors path="pwd" class="errorMsg"/>
                    </div>
                </div>
                <div class="form-group" id="divPasswordCheck">
                    <label for="inputPasswordCheck" class="col-lg-2 control-label">패스워드 확인</label>
                    <div class="col-lg-5">
                        <!--  <input type="password" class="form-control" id="passwordCheck" name="confirmPwd" data-rule-required="true" placeholder="패스워드 확인" maxlength="30">-->
                    	<form:password path="confirmPwd" class="form-control" id="passwordCheck" data-rule-required="true" placeholder="패스워드 확인" maxlength="30" required="required"/>
                    	<form:errors path="confirmPwd" class="errorMsg"/>
                    </div>
                </div>

                <div class="form-group" id="divPhoneNumber">
                    <label for="inputPhoneNumber" class="col-lg-2 control-label">휴대폰 번호</label>
                    <div class="col-lg-5">
                    
                        <!--  <input type="tel" class="form-control onlyNumber" id="phoneNumber" name="phoneNum" data-rule-required="true" placeholder="-를 제외하고 숫자만 입력하세요." maxlength="11">-->
                    	<form:input path="phoneNum" class="form-control onlyNumber" id="phoneNumber" data-rule-required="true" placeholder="-를 제외하고 숫자만 입력하세요." maxlength="11" required="required"/>
                    	<form:errors path="phoneNum" class="errorMsg"/>
                    </div>
                </div>
                 <div class="form-inline-form-group">
                    <label for="postCode" class="col-lg-2 control-label">우편번호</label>
                    <div class="col-lg-5">
                    	<form:input path="postCode" class="form-control"/>
                        <!-- <input type="text" class="form-control" name="postCode" id="postCode"> -->
                        <input type="button" class="btn btn-secondary btn-sm" onClick="execDaumPostcode()" value="우편번호 검색" required="required">
                        <form:errors path="postCode" class="errorMsg"/>
                    </div>
                </div>
                <br>
                <div class="form-group">
                    <label for="roadAddr" class="col-lg-2 control-label">도로명 주소</label>
                    <div class="col-lg-5">
                        <form:input path="roadAddr" class="form-control" required="required"/>&nbsp;
                        <form:errors path="roadAddr" class="errorMsg"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="detailAddr" class="col-lg-2 control-label">상세 주소</label>
                    <div class="col-lg-10">
                        <form:input path="detailAddr" class="form-control" required="required"/>&nbsp;
                        <form:errors path="detailAddr" class="errorMsg"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <input type="submit" class="btn btn-primary btn-lg" value="가입하기">
                    </div>
                </div>
            </form:form>
        </div>
</div>
        <jsp:include page="../common/footer.jsp"/>
</body>
</html>
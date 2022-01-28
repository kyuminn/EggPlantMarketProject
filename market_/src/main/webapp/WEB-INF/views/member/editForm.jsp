<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
	<!-- header 추가 -->
	<jsp:include page="../common/header.jsp"></jsp:include>
    <meta charset="UTF-8">
    <title>프로필 수정</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        
     <!-- 주소 검색 스크립트 -->
		<script src="/javascript/addrPopup.js"></script>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
	<link rel="stylesheet" href="/css/member.css">
</head>
<body>
<div class="wrapper">
	<div class="container"><!-- 좌우측의 공간 확보 -->
			<h1>정보 수정</h1>
            <hr/>
            <!-- form:form tag 안의 form:errors 로 에러 메세지 보여주기 -->	
            <form:form class="form-horizontal" modelAttribute="member" role="form" method="post" action="${pageContext.request.contextPath}/member/edit/${id }">
                
                <div class="form-group" id="divNickname">
                    <label for="inputNickname" class="col-lg-2 control-label">별명</label>
                    <div class="col-lg-5">
                        <!-- <input type="text" class="form-control" name="nickname" id="nickname" data-rule-required="true" placeholder="별명" maxlength="15" value=${member.nickname }> -->
                    	 <form:input path="nickname" class="form-control" id="nickname" value="${member.nickname }" data-rule-required="true" placeholder="별명" maxlength="15" required="required"/>
                    	<form:errors path="nickname" class="errorMsg"/>
                    </div>
                </div>

                <div class="form-group" id="divPhoneNumber">
                    <label for="inputPhoneNumber" class="col-lg-2 control-label">휴대폰 번호</label>
                    <div class="col-lg-5">
                        <!--  <input type="tel" class="form-control onlyNumber" id="phoneNumber" name="phoneNum" data-rule-required="true" placeholder="-를 제외하고 숫자만 입력하세요." 
                        maxlength="11" value="${member.phoneNum }">-->
                        <form:input path="phoneNum" class="form-control onlyNumber" value="${member.phoneNum }" id="phoneNumber" data-rule-required="true" placeholder="-를 제외하고 숫자만 입력하세요." maxlength="11" required="required"/>
                    	<form:errors path="phoneNum" class="errorMsg"/>
                    	<!-- 기존 휴대폰 번호와 변경값이 있는지 비교 위해 데이터 전송 -->
                    	<input type="hidden" name="currentPhoneNum" value="${member.phoneNum }">
                    </div>
                </div>
                
				<div class="form-inline-form-group">
                    <label for="postCode" class="col-lg-2 control-label">우편번호</label>
                    <div class="col-lg-5">
                        <!-- <input type="text" class="form-control" name="postCode" id="postCode" value="${member.postCode}"> -->
                        <form:input path="postCode" class="form-control" value="${member.postCode}"/>
                        <input type="button" class="btn btn-secondary btn-sm" onClick="execDaumPostcode()" value="우편번호 검색">
                        <form:errors path="postCode" class="errorMsg"/>
                    </div>
                </div>
                <br>
                <div class="form-group">
                    <label for="roadAddr" class="col-lg-2 control-label">도로명 주소</label>
                    <div class="col-lg-5">
                        <!--<input type="text" class="form-control" name="roadAddr" id="roadAddr" value="${member.roadAddr}">&nbsp;  -->
                         <form:input path="roadAddr" class="form-control" value="${member.roadAddr}" required="required"/>&nbsp;
                        <form:errors path="roadAddr" class="errorMsg"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="detailAddr" class="col-lg-2 control-label">상세 주소</label>
                    <div class="col-lg-10">
                        <!-- <input type="text" class="form-control" name="detailAddr" id="detailAddr" value="${member.detailAddr}">&nbsp; -->
                        <form:input path="detailAddr" class="form-control" required="required" value="${member.detailAddr}"/>&nbsp;
                        <form:errors path="detailAddr" class="errorMsg"/>
                    </div>
                </div>
                <hr/>
                <h1>비밀번호 변경</h1>
                <div class="errorMsg">
                <small>공백 입력 시 비밀번호는 유지됩니다</small>
                </div>
                <div class="form-group" id="divPassword">
                    <label for="inputPassword" class="col-lg-2 control-label">현재 비밀번호</label>
                    <div class="col-lg-5">
                        <input type="password" class="form-control" id="password" name="currentPwd" data-rule-required="true" placeholder="패스워드" maxlength="30">
                    	<form:errors path="currentPwd" class="errorMsg"/>
                    </div>
                </div>
                <div class="form-group" id="divPasswordCheck">
                    <label for="inputPasswordCheck" class="col-lg-2 control-label">변경할 비밀번호</label>
                    <div class="col-lg-5">
                        <input type="password" class="form-control" id="passwordCheck" name="changePwd" data-rule-required="true" placeholder="패스워드 확인" maxlength="30">
                    	
                    </div>
                </div>
	               <div class="form-group" id="divPassword">
                    <label for="inputPassword" class="col-lg-2 control-label">변경할 비밀번호 확인</label>
                    <div class="col-lg-5">
                        <input type="password" class="form-control" id="password" name="confirmChangePwd" data-rule-required="true" placeholder="패스워드" maxlength="30">
                    	<form:errors path="confirmChangePwd" class="errorMsg"/>
                    </div>
                </div>
                 
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <input type="submit" class="btn btn-primary btn-lg" value="회원정보 수정">
                    </div>
                </div>
            </form:form>
        </div>
</div>
        <jsp:include page="../common/footer.jsp"/>
</body>
</html>
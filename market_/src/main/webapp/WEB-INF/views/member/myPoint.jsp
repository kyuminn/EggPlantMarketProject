<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가지포인트 관리</title>
<jsp:include page="../common/header.jsp"/>

</head>
<body>
	<div class="container">
		<br><br><br>
  		<div class="row">
     		<div class="col-sm-4 col-md-4 col-lg-4 col-xs-4">
     		 	<h3>나의 가지포인트</h3><br>
     		 	<hr/>
     		 	<img src="/images/purpleCheck.png" width="50" height="50">
     		 	${loginMember.point }원
     		</div>
     		<div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
     			<h3>현금 전환</h3><br>
     			<hr/>
     			<img src="/images/cash.png" width="50" height="50"><br><br>
     			<form method="post" action="${pageCsontext.request.contextPath}/member/convertPoint/${loginMember.id}">
     			은행명 : <select name="account_bank_id">
                        	<option value="0">은행명을 선택하세요</option>
                        	<option value="1">국민은행</option>
                        	<option value="2">기업은행</option>
                        	<option value="3">신한은행</option>
                        	<option value="4">외환은행</option>
                        	<option value="5">우리은행</option>
                        	<option value="6">우체국</option>
                        	<option value="7">카카오뱅크</option>
                        	<option value="8">하나은행</option>
						</select>
						<br><br>
				<label for="accountNum">계좌번호:</label>
				<div class=" col-lg-6">
					<input type="text" id="accountNum" name="accountNum" class="form-control" required><br>
				</div>
				
				<label for="owner">예금주:</label>
				<div class=" col-lg-4">
					<input type="text" id="owner" name="owner" class="form-control" required><br><br>
					<input type="submit" class="btn btn-primary" value="현금 전환"> 		
				</div><br><br>
				</form>	
     		</div>
  		</div>
	</div>
</body>
</html>
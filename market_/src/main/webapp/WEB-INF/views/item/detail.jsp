<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>상품 디테일</title>
    
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	
	<!-- toss pay sdk -->
	<script src="https://js.tosspayments.com/v1"></script>
	<style>
		.addContainer{
			width:800px;
			height:500px;
			margin:0 auto; /*container 가운데 정렬*/
		}
	</style>
</head>
<body>
<div class="addContainer">
<form:form>
	<h1>상품 디테일 <a href="${pageContext.request.contextPath}/"><img src="/images/eggPlant.png" width="70" height="70"></a></h1>
	<hr/>
		<c:if test="${loginSession eq member.email }">
				<input type="button" onclick="window.location.href='${pageContext.request.contextPath}/item/edit/${item.id }'" value="수정">&nbsp;
				<input type="button" onclick="window.location.href='${pageContext.request.contextPath}/item/delete/${item.id }'" value="삭제">
		</c:if>
	<table class="table table-borderless">
			<tr>
				<td rowspan="6">
					<div id='View_area' style='position:relative; width:100% ; height:100%; color: black; border: 0px solid black; dispaly: inline; '>
						<img src="${item.filePath}" width=300px height=300px>
					</div>
				</td>
				<th>상품명</th>
				<td>
					${item.name}
				</td>
			</tr>
			<tr>
				<th>가격</th>
				<td>
					${item.price}
				</td>
			</tr>
			<tr>
				<th>판매자</th>
				<td>
					${member.nickname}
				</td>
			</tr>
			<tr>
				<th>수량</th>
				<td>1
				</td>
			</tr>
			<tr>
				<th>총가격</th>
				<td>${item.price*1}
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" class="btn_cart"><img src="/icons/wish.png" height="50" width="50"></button>&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/kakao/pay/${item.id}"><img src="/images/kakao_pay.png"></a>&nbsp;&nbsp;&nbsp;
					<button type="button" id="payment-button"><img src="/images/toss_pay.png" height ="50" width="100" /></button>&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn btn-success" value="상품 문의" onclick="window.location.href='${pageContext.request.contextPath}/question/list/${item.id}'" >
					<!--  <a href="${pageContext.request.contextPath}/question/list/${item.id}">상품 문의</a>-->
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div class="summernote" id="content">
						<textarea name="content" readonly>${item.content}</textarea>
						<!--  ${item.content}-->
					</div> 
				</td>
			</tr>
		</table>
	</form:form>
</div>
	<script>
    $('.summernote').summernote({
  	  height:700,
  	  minHeight:null
    })
    
	  var content = '${item.content}';
      $('.summernote').summernote('code',content);
      $('.summernote').summernote('disable');
    </script>
    
    <!-- 장바구니 버튼 -->
    <script>
		var loginSession = '${loginSession}';
		var itemId = '${item.id}'
		
    	const form = {
    		loginSession : loginSession,
    			itemId : itemId
    	}
    	
    	$(".btn_cart").on("click",function(e){
    		if (loginSession == ''){
			alert("로그인이 필요합니다!");
			location.href="/login?redirectURL=/item/detail/${item.id}";
		}
    		$.ajax({
    			url : '/wish',
    			type : 'POST',
    			data : form,
    			success :function(result){
    				if (result =='1'){
    					alert("상품 찜 완료!");
    			    	location.href="/item/detail/${item.id}";
    				}else if(result=='0'){
    					alert("이미 찜한 상품입니다!");
    					location.href="/item/detail/${item.id}";
    				}
    			}
    		})
    	});
    	

    </script>
    <!-- toss pay request -->
    <script>
    var tossPayments = TossPayments("test_ck_7DLJOpm5QrlbBQepgno3PNdxbWnY");
    var button = document.getElementById("payment-button");

    var orderId = '${item.orderKey}';

    button.addEventListener("click", function () {
        //var method = document.querySelector('input[name=method]:checked').value; // "카드" 혹은 "가상계좌"
		var method = '카드';
        
        var paymentData = {
            amount: '${item.price}',
            orderId: orderId,
            orderName: '${item.name}',
            customerName: '${loginSession}',
            successUrl: window.location.origin + "/toss/success",
            failUrl: window.location.origin + "/toss/fail",
        };
	
        //if (method === '가상계좌') {
        //    paymentData.virtualAccountCallbackUrl = window.location.origin + '/virtual-account/callback'
        //}

        tossPayments.requestPayment(method, paymentData);
    });
</script>
</body>
</html>
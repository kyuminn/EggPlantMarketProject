<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.1/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<meta charset="UTF-8">
<title>Rating</title>
<script src="/javascript/rating.js"></script>
<link rel="stylesheet" href="/css/rating.css">

</head>
<body>
	<div class="container">
	<h1>별점</h1>
	<div class="row" style="margin-top:40px;">
		<div class="col-md-6">
    	<div class="well well-sm">
            <div class="text-right">
                <a class="btn btn-success btn-green" href="#reviews-anchor" id="open-review-box">별점 남기기</a>
            </div>
        
            <div class="row" id="post-review-box" style="display:none;">
                <div class="col-md-12">
                    <form accept-charset="UTF-8" action="${pageContext.request.contextPath}/order/rating/${item.id}" method="post">

                    	<input type="hidden" name="buyerEmail" value="${loginSession}">
                        <input id="ratings-hidden" name="rating" type="hidden"> 
                        <textarea class="form-control animated" cols="50" id="new-review" name="comment" rows="5" readonly>
                        	상품 이름: ${item.name }
                        	결제 금액: ${item.price }
                        </textarea>
                        <div class="text-right">
                            <div class="stars starrr" data-rating="0"></div>
                            <a class="btn btn-danger btn-sm" href="#" id="close-review-box" style="display:none; margin-right: 10px;">
                            <span class="glyphicon glyphicon-remove"></span>취소</a>
                            <button class="btn btn-success btn-lg" type="submit">저장</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>        
		</div>
	</div>
</div>

</body>
</html>
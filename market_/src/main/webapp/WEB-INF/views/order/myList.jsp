<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/css/list.css">
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<jsp:include page="../common/header.jsp"/>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div class="cart-wrap">
		<div class="container">
	        <div class="row">
			    <div class="col-md-12">
			        <div class="main-heading mb-10">주문/배송</div>
			        <hr/>
			        <c:if test="${!empty readyItem}">
			        <h3>결제완료</h3>
			        <div class="table-wishlist">
				        <table cellpadding="0" cellspacing="0" border="0" width="100%">
				        	<thead>
				        		<!-- 
					        	<tr>
					        		<th width="45%"></th>
					        		<th width="15%"></th>
					        		<th width="15%"></th>
					        		<th width="15%"></th>
					        		<th width="10%"></th>
					        	</tr>-->
					        </thead>
					        <tbody>
					        	<c:forEach var="item" items="${readyItem }">
					        	<tr>
					        		<td width="45%">
					        			<div class="display-flex align-center">
		                                    <div class="img-product">
		                                        <img src="${item.filePath }" width="200" height="200">
		                                    </div>
		                                    <div class="name-product">
		                                        <a href="${pageContext.request.contextPath}/item/detail/${item.id}">${item.name }</a>
		                                    </div>
	                                    </div>
	                                </td>
					        		<td width="15%" class="price"></td>
					        		<td width="15%"><button class="round-black-btn small-btn" 
					        			onclick="window.location.href='${pageContext.request.contextPath}/question/add/${item.id}'">상품 문의</button></td>
					        		<td width="15%"><button class="round-black-btn small-btn" 
					        		onclick="window.location.href='${pageContext.request.contextPath}/order/revoke/${item.id }'">주문 취소</button></td>
					        		<td width="10%" class="text-center"><a href="#" class="trash-icon"><i class="far fa-trash-alt"></i></a></td>
					        	</tr>
					        	</c:forEach>
				        	</tbody>
				        </table>
				    </div>
				    </c:if>
				    
				  	<c:if test="${!empty shippingItem}">
				  	<hr/>
			        <h3>배송중</h3>
			        <div class="table-wishlist">
				        <table cellpadding="0" cellspacing="0" border="0" width="100%">
				        	<thead>
				        		<!-- 
					        	<tr>
					        		<th width="45%"></th>
					        		<th width="15%"></th>
					        		<th width="15%"></th>
					        		<th width="15%"></th>
					        		<th width="10%"></th>
					        	</tr>-->
					        </thead>
					        <tbody>
					        	<c:forEach var="item" items="${shippingItem }">
					        	<tr>
					        		<td width="45%">
					        			<div class="display-flex align-center">
		                                    <div class="img-product">
		                                        <img src="${item.filePath }" width="200" height="200">
		                                    </div>
		                                    <div class="name-product">
		                                        <a href="${pageContext.request.contextPath}/item/detail/${item.id}">${item.name }</a>
		                                    </div>
	                                    </div>
	                                </td>
					        		<td width="15%" class="price"></td>
					        		<td width="15%"><button class="round-black-btn small-btn" 
					        			onclick="window.location.href='${pageContext.request.contextPath}/question/add/${item.id}'">상품 문의</button></td>
					        		<td width="15%"><button class="round-black-btn small-btn" 
					        		onclick="window.location.href='${pageContext.request.contextPath}/order/confirm/${item.id}'">구매 확정</button></td>
					        		<td width="10%" class="text-center"><a href="#" class="trash-icon"><i class="far fa-trash-alt"></i></a></td>
					        	</tr>
					        	</c:forEach>
				        	</tbody>
				        </table>
				    </div>
				    </c:if>
				    
				    <c:if test="${!empty completeItem}">
				  	<hr/>
			        <h3>배송완료</h3>
			        <div class="table-wishlist">
				        <table cellpadding="0" cellspacing="0" border="0" width="100%">
				        	<thead>
				        		<!-- 
					        	<tr>
					        		<th width="45%"></th>
					        		<th width="15%"></th>
					        		<th width="15%"></th>
					        		<th width="15%"></th>
					        		<th width="10%"></th>
					        	</tr>-->
					        </thead>
					        <tbody>
					        	<c:forEach var="item" items="${completeItem }">
					        	<tr>
					        		<td width="45%">
					        			<div class="display-flex align-center">
		                                    <div class="img-product">
		                                        <img src="${item.filePath }" width="200" height="200">
		                                    </div>
		                                    <div class="name-product">
		                                        <a href="${pageContext.request.contextPath}/item/detail/${item.id}">${item.name }</a>
		                                    </div>
	                                    </div>
	                                </td>
					        		<td width="15%" class="price"></td>
					        		<td width="15%"><button class="round-black-btn small-btn" 
					        			onclick="window.location.href='${pageContext.request.contextPath}/question/add/${item.id}'">상품 문의</button></td>
					        		<td width="10%" class="text-center"><a href="#" class="trash-icon"><i class="far fa-trash-alt"></i></a></td>
					        	</tr>
					        	</c:forEach>
				        	</tbody>
				        </table>
				    </div>
				    </c:if>
				    
			    </div>
			</div>
		</div>
	</div>
</body>
</html>
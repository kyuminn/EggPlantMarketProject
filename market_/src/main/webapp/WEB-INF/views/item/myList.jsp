<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
	<link rel="stylesheet" href="/css/list.css">
	 <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <meta charset="UTF-8">
    <title>나의 판매글 목록</title>
</head>
<body>
	<div class="cart-wrap">
		<div class="container">
	        <div class="row">
			    <div class="col-md-12">
			        <div class="main-heading mb-10">나의 판매글 목록
			        <a href="${pageContext.request.contextPath }/"><img src="/images/eggPlant.png" width="50" height="50"></a>
			        </div>
			       <hr/> 
					<c:if test="${!empty onSaleItemList}">
			        <h3>판매중</h3>
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
					        	<c:forEach var="item" items="${onSaleItemList }">
					        	<tr>
					        		<td width="45%">
					        			<div class="display-flex align-center">
		                                    <div class="img-product">
		                                        <img src="${item.filePath }" width="100" height="100">
		                                    </div>
		                                    <div class="name-product">
		                                        <a href="${pageContext.request.contextPath}/item/detail/${item.id}">${item.name }</a>
		                                    </div>
	                                    </div>
	                                </td>
					        		<td width="15%"><button class="round-black-btn small-btn" 
					        			onclick="window.location.href='${pageContext.request.contextPath}/question/list/${item.id}'">문의사항 보기</button></td>
					        		<td width="15%"><button class="round-black-btn small-btn" 
					        			onclick="window.location.href='${pageContext.request.contextPath}/item/edit/${item.id}'">판매글 수정</button></td>
					        		<td width="15%"><button class="round-black-btn small-btn"
					        		 onclick="window.location.href='${pageContext.request.contextPath}/item/delete/${item.id }'">판매글 삭제</button></td>
					        		<td width="10%" class="text-center"><a href="#" class="trash-icon"><i class="far fa-trash-alt"></i></a></td>
					        	</tr>
					        	</c:forEach>
				        	</tbody>
				        </table>
				    </div>
				    </c:if>
				    <c:if test="${!empty readyItemList}">
				    <hr/>
			        <h3>결제완료</h3>
			        <div class="table-wishlist">
				        <table cellpadding="0" cellspacing="0" border="0" width="100%">
				        	<thead>
					        </thead>
					        <tbody>
					        	<c:forEach var="item" items="${readyItemList }">
					        	<tr>
					        		<td width="45%">
					        			<div class="display-flex align-center">
		                                    <div class="img-product">
		                                        <img src="${item.filePath }" width="100" height="100">
		                                    </div>
		                                    <div class="name-product">
		                                        <a href="${pageContext.request.contextPath}/item/detail/${item.id}">${item.name }</a>
		                                    </div>
	                                    </div>
	                                </td>
					        		<td width="15%"><button class="round-black-btn small-btn" 
					        			onclick="window.location.href='${pageContext.request.contextPath}/question/list/${item.id}'">문의사항 보기</button></td>
					        		<td width="15%"><button class="round-black-btn small-btn" 
					        			onclick="window.location.href='${pageContext.request.contextPath}/order/release/${item.id}'">출고완료</button></td>
					        		<td width="15%"><button class="round-black-btn small-btn"></button></td>
					        		<td width="10%" class="text-center"><a href="#" class="trash-icon"><i class="far fa-trash-alt"></i></a></td>
					        	</tr>
					        	</c:forEach>
				        	</tbody>
				        </table>
				    </div>
				    </c:if>
				    <hr/>
				    <c:if test="${!empty shippingItemList}">
				    <hr/>
			        <h3>배송중</h3>
			        <div class="table-wishlist">
				        <table cellpadding="0" cellspacing="0" border="0" width="100%">
				        	<thead>
					        </thead>
					        <tbody>
					        	<c:forEach var="item" items="${shippingItemList }">
					        	<tr>
					        		<td width="45%">
					        			<div class="display-flex align-center">
		                                    <div class="img-product">
		                                        <img src="${item.filePath }" width="100" height="100">
		                                    </div>
		                                    <div class="name-product">
		                                        <a href="${pageContext.request.contextPath}/item/detail/${item.id}">${item.name }</a>
		                                    </div>
	                                    </div>
	                                </td>
					        		<td width="15%"><button class="round-black-btn small-btn" 
					        			onclick="window.location.href='${pageContext.request.contextPath}/question/list/${item.id}'">문의사항 보기</button></td>
					        		<td width="15%"></td>
					        		<td width="15%"></td>
					        		<td width="10%" class="text-center"><a href="#" class="trash-icon"><i class="far fa-trash-alt"></i></a></td>
					        	</tr>
					        	</c:forEach>
				        	</tbody>
				        </table>
				    </div>
				    </c:if>
				     <c:if test="${!empty completeItemList}">
				    <hr/>
			        <h3>배송완료</h3>
			        <div class="table-wishlist">
				        <table cellpadding="0" cellspacing="0" border="0" width="100%">
				        	<thead>
					        </thead>
					        <tbody>
					        	<c:forEach var="item" items="${completeItemList }">
					        	<tr>
					        		<td width="45%">
					        			<div class="display-flex align-center">
		                                    <div class="img-product">
		                                        <img src="${item.filePath }" width="100" height="100">
		                                    </div>
		                                    <div class="name-product">
		                                        <a href="${pageContext.request.contextPath}/item/detail/${item.id}">${item.name }</a>
		                                    </div>
	                                    </div>
	                                </td>
					        		<td width="15%" class="price"></td>
					        		<td width="15%"></td>
					        		<td width="15%">
					        			<c:forEach items="${map}" var="entry">
					        				<c:if test="${entry.key eq item.id }">
					        					<button class="round-black-btn small-btn">${entry.value} / 5.0</button>
					        				</c:if>
					        			</c:forEach>
					        		</td>
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
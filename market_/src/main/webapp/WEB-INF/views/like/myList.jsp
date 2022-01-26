<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>찜 목록</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link rel="stylesheet" href="/css/list.css">
	<jsp:include page="../common/header.jsp"/>
</head>
<body>
		<c:if test="${empty myLikesItems }">
			<div class="container">
				<h3> 찜한 상품이 없습니다</h3>
			</div>
		</c:if>
		<c:if test="${!empty myLikesItems }">
		<div class="cart-wrap">
		<div class="container">
	        <div class="row">
			    <div class="col-md-12">
			        <div class="main-heading mb-10"><h2>나의 찜 목록</h2></div>
			        <br><br>
			        <div class="table-wishlist">
				        <table cellpadding="0" cellspacing="0" border="0" width="100%" class="table table-striped">
				        	<thead>
					        	<tr>
					        		<th width="45%">상품</th>
					        		<th width="15%">상품이름</th>
					        		<th width="15%">가격</th>
					        		<th width="15%"></th>
					        		<th width="10%"></th>
					        	</tr>
					        </thead>
					        <tbody>
					        	<c:forEach var="item" items="${myLikesItems }">
					        	<tr>
					        		<td width="45%">
					        			<div class="display-flex align-center">
		                                    <div class="img-product">
		                                        <img src="${item.filePath }" alt="" class="mCS_img_loaded" width="200" height="200">
		                                    </div>
		                                    <!--  <div class="name-product">
		                                        ${item.name }
		                                    </div>-->
	                                    </div>
	                                </td>
	                                <td width="15%" class="name-product">${item.name }</td>
					        		<td width="15%" class="price">${item.price }</td>
					        		<td width="15%">
					        			<button type="button" class="round-black-btn small-btn" onclick="window.location.href='${pageContenxt.request.contextPath}/item/detail/${item.id}'">자세히 보기</button>
					        		</td>
					        		<td width="15%"><button class="round-black-btn small-btn"
					        		onclick="window.location.href='${pageContext.request.contextPath}/like/remove/${item.id }'">제거</button></td>
					        		<td width="10%" class="text-center"><a href="#" class="trash-icon"><i class="far fa-trash-alt"></i></a></td>
					        	</tr>
					        	</c:forEach>
					        	<!--  
					        	<tr>
					        		<td width="45%">
					        			<div class="display-flex align-center">
		                                    <div class="img-product">
		                                        <img src="https://www.91-img.com/pictures/laptops/asus/asus-x552cl-sx019d-core-i3-3rd-gen-4-gb-500-gb-dos-1-gb-61721-large-1.jpg" alt="" class="mCS_img_loaded">
		                                    </div>
		                                    <div class="name-product">
		                                        Apple iPad Mini
		                                    </div>
	                                    </div>
	                                </td>
					        		<td width="15%" class="price">$110.00</td>
					        		<td width="15%"><span class="in-stock-box">In Stock</span></td>
					        		<td width="15%"><button class="round-black-btn small-btn">Add to Cart</button></td>
					        		<td width="10%" class="text-center"><a href="#" class="trash-icon"><i class="far fa-trash-alt"></i></a></td>
					        	</tr>
					        	<tr>
					        		<td width="45%">
					        			<div class="display-flex align-center">
		                                    <div class="img-product">
		                                        <img src="https://www.91-img.com/pictures/laptops/asus/asus-x552cl-sx019d-core-i3-3rd-gen-4-gb-500-gb-dos-1-gb-61721-large-1.jpg" alt="" class="mCS_img_loaded">
		                                    </div>
		                                    <div class="name-product">
		                                        Apple iPad Mini
		                                    </div>
	                                    </div>
	                                </td>
					        		<td width="15%" class="price">$110.00</td>
					        		<td width="15%"><span class="in-stock-box">In Stock</span></td>
					        		<td width="15%"><button class="round-black-btn small-btn">Add to Cart</button></td>
					        		<td width="10%" class="text-center"><a href="#" class="trash-icon"><i class="far fa-trash-alt"></i></a></td>
					        	</tr>-->
				        	</tbody>
				        </table>
				    </div>
			    </div>
			</div>
		</div>
	</div>
	</c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>키워드로 검색하기</title>
<jsp:include page="../common/header.jsp"/>
<link rel="stylesheet" href="/css/main.css">
</head>
<body>
	<hr/>
	<c:if test="${!empty ls }">
	<div class="container">
    <div class="row">
    <c:forEach var="item" items="${ls }">
        <div class="col-md-3 col-sm-6">
            <div class="product-grid4">
                <div class="product-image4">
                    <a href="${pageContext.request.contextPath}/item/detail/${item.id}">
                        <img class="pic-1" width="300" height="400" src="${item.filePath }">
                        <!--
                        마우스 갖다댔을 때 나오는 이미지
                         <img class="pic-2" src="http://bestjquery.com/tutorial/product-grid/demo5/images/img-2.jpg"> -->
                    </a>
                    <span class="product-new-label">New</span>
                </div>
                <div class="product-content">
                    <h3 class="title">${item.name }</h3>
                    <div class="price">${item.price }
                    </div>
                    <!--  <a class="add-to-cart" href="">ADD TO CART</a>-->
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
</div>	
</c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>메인!</title>
    <jsp:include page="common/header.jsp"/>
	<link rel="stylesheet" href="/css/main.css">

</head>
<body>
	<div class="container">
	<!--  
	<input type="button" class="btn btn-secondary btn-lg" value="의류" onclick="windonw.href.location='#'">
	<input type="button" class="btn btn-secondary btn-lg" value="가전" onclick="windonw.href.location='#'">
	<input type="button" class="btn btn-secondary btn-lg" value="생활용품" onclick="windonw.href.location='#'">
	<input type="button" class="btn btn-secondary btn-lg" value="기타" onclick="windonw.href.location='#'">
	aria-current="page"
	aria-disabled="true"
	-->
	<!--  <ul class="nav nav-tabs">
  <li class="nav-item">
    <a class="nav-link" href="${pageContext.request.contextPath}/search/category?category=CLOTHES">의류</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#">가전</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#">생활용품</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#">기타</a>
  </li>
	</ul>
	<hr/>-->
	<jsp:include page="common/tab.jsp"/>
	<c:if test="${!empty items }">
	<h3>이 상품은 어떠세요?</h3><br><br>
	<div class="container">
    <div class="row">
    <c:forEach var="item" items="${items }">
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
</div>
	<!--<jsp:include page="common/footer.jsp"/>-->
</body>
</html>
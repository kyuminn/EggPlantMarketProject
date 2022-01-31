<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/javascript/header.js"></script>
<link rel="stylesheet" href="/css/header.css">
<style>
	/*.main_nav_menu{
		z-index:1;
	}*/

</style>
</head>
<body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/2.1.3/TweenMax.min.js"></script>

<div class="super_container">
    <!-- Header -->
    <header class="header">
        <!-- Top Bar -->
        <div class="top_bar">
            <div class="container">
                <div class="row">
                    <div class="col d-flex flex-row">
                        <div class="top_bar_content ml-auto">
                            <div class="top_bar_user">
                            	<c:if test="${empty loginSession }">
                                <div><a href="${pageContext.request.contextPath}/member/add">회원가입</a></div>&nbsp;&nbsp;&nbsp;
                                <div><a href="${pageContext.request.contextPath}/login">로그인</a></div>
                                </c:if>
                                <c:if test="${!empty loginSession }">
                                	<div>${loginSession}</div>님&nbsp;&nbsp;&nbsp;
                                	<div><a href="${pageContext.request.contextPath}/logout">로그아웃</a></div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- Header Main -->
        <div class="header_main">
            <div class="container">
                <div class="row">
                    <!-- Logo -->
                    <div class="col-lg-2 col-sm-3 col-3 order-1">
                        <div class="logo_container">
                            <div class="logo"><a href="/"><img src="/images/eggPlant.png" width=80px height=80px></a></div>
                        </div>
                    </div> <!-- Search -->
                    <div class="col-lg-6 col-12 order-lg-2 order-3 text-lg-left text-right">
                        <div class="header_search">
                            <div class="header_search_content">
                                <div class="header_search_form_container">
                                    <form action="${pageContext.request.contextPath}/item/search/keyword"  method="get" class="header_search_form clearfix">
                                    		 <input type="search" name="keyword" required="required" class="header_search_input" placeholder="상품 검색하기">
											<button type="submit" class="header_search_button trans_300" value="Submit"><img src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560918770/search.png" alt=""></button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div> <!-- Wishlist -->
                    <div class="col-lg-4 col-9 order-lg-3 order-2 text-lg-left text-right">
                        <div class="wishlist_cart d-flex flex-row align-items-center justify-content-end">
                            <div class="add"><a href="${pageContext.request.contextPath}/item/add"><img src="/icons/add.png" width=40px height=40px></a></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div class="mypage"><a href="${pageContext.request.contextPath}/member/myPage"><img src="/icons/mypage.png" width=60px height=60px></a></div>&nbsp;&nbsp;&nbsp;
							<div class="shipping"><a href="${pageContext.request.contextPath}/order/myList/${loginMember.id}"><img src="/icons/shipping.png" width=80px height=80px></a></div>&nbsp;&nbsp;&nbsp;&nbsp;
							<div class="wish"><a href="${pageContext.request.contextPath}/wish/list"><img src="/icons/wish.png" width=60px height=60px></a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div style="height: 100px"> </div>
    </div>
</body>
</html>
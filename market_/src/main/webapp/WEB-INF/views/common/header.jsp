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
                    	<!--  
                        <div class="top_bar_contact_item">
                            <div class="top_bar_icon"><img src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560918577/phone.png" alt=""></div>+91 9823 132 111
                        </div>
                        <div class="top_bar_contact_item">
                            <div class="top_bar_icon"><img src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560918597/mail.png" alt=""></div><a href="mailto:fastsales@gmail.com">contact@bbbootstrap.com</a>
                        </div>
                        -->
                        <div class="top_bar_content ml-auto">
                        	<!--  
                            <div class="top_bar_menu">
                                <ul class="standard_dropdown top_bar_dropdown">
                                    <li> <a href="#">English<i class="fas fa-chevron-down"></i></a>
                                        <ul>
                                            <li><a href="#">Italian</a></li>
                                            <li><a href="#">Spanish</a></li>
                                            <li><a href="#">Japanese</a></li>
                                        </ul>
                                    </li>
                                    <li> <a href="#">$ US dollar<i class="fas fa-chevron-down"></i></a>
                                        <ul>
                                            <li><a href="#">EUR Euro</a></li>
                                            <li><a href="#">GBP British Pound</a></li>
                                            <li><a href="#">JPY Japanese Yen</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>-->
                            <div class="top_bar_user">
                            	<div><a href="#">QnA</a></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            	<c:if test="${empty loginSession }">
                                <div><a href="${pageContext.request.contextPath}/member/add">회원가입</a></div>&nbsp;&nbsp;&nbsp;
                                <div><a href="${pageContext.request.contextPath}/login">로그인</a></div>
                                </c:if>
                                <c:if test="${!empty loginSession }">
                                	<div>${loginSession}</div>
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
                                    <form action="#" class="header_search_form clearfix"> <input type="search" required="required" class="header_search_input" placeholder="상품 검색하기">
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
							<div class="wish"><a href="${pageContext.request.contextPath}/like/list"><img src="/icons/wish.png" width=60px height=60px></a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
         
         <!-- Main Navigation 
        <nav class="main_nav">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="main_nav_content d-flex flex-row">
                            <div class="main_nav_menu">
                                <ul class="standard_dropdown main_nav_dropdown">
                                    <li><a href="#">Home<i class="fas fa-chevron-down"></i></a></li>
                                    <li class="hassubs"> <a href="#">Laptop<i class="fas fa-chevron-down"></i></a>
                                        <ul>
                                            <li> <a href="#">Lenovo<i class="fas fa-chevron-down"></i></a>
                                                <ul>
                                                    <li><a href="#">Lenovo 1<i class="fas fa-chevron-down"></i></a></li>
                                                    <li><a href="#">Lenovo 3<i class="fas fa-chevron-down"></i></a></li>
                                                    <li><a href="#">Lenovo 2<i class="fas fa-chevron-down"></i></a></li>
                                                </ul>
                                            </li>
                                            <li><a href="#">DELL<i class="fas fa-chevron-down"></i></a></li>
                                            <li><a href="#">APPLE<i class="fas fa-chevron-down"></i></a></li>
                                            <li><a href="#">HP<i class="fas fa-chevron-down"></i></a></li>
                                        </ul>
                                    </li>
                                    <li class="hassubs"> <a href="#">Featured Brands<i class="fas fa-chevron-down"></i></a>
                                        <ul>
                                            <li> <a href="#">APPLE<i class="fas fa-chevron-down"></i></a>
                                                <ul>
                                                    <li><a href="#">Laptop<i class="fas fa-chevron-down"></i></a></li>
                                                    <li><a href="#">Mobiles<i class="fas fa-chevron-down"></i></a></li>
                                                    <li><a href="#">Ipads<i class="fas fa-chevron-down"></i></a></li>
                                                </ul>
                                            </li>
                                            <li><a href="#">Samsung<i class="fas fa-chevron-down"></i></a></li>
                                            <li><a href="#">Lenovo<i class="fas fa-chevron-down"></i></a></li>
                                            <li><a href="#">DELL<i class="fas fa-chevron-down"></i></a></li>
                                        </ul>
                                    </li>
                                    <li class="hassubs"> <a href="#">Pages<i class="fas fa-chevron-down"></i></a>
                                        <ul>
                                            <li><a href="shop.html">Shop<i class="fas fa-chevron-down"></i></a></li>
                                            <li><a href="product.html">Product<i class="fas fa-chevron-down"></i></a></li>
                                            <li><a href="blog.html">Blog<i class="fas fa-chevron-down"></i></a></li>
                                            <li><a href="blog_single.html">Blog Post<i class="fas fa-chevron-down"></i></a></li>
                                            <li><a href="regular.html">Regular Post<i class="fas fa-chevron-down"></i></a></li>
                                            <li><a href="cart.html">Cart<i class="fas fa-chevron-down"></i></a></li>
                                            <li><a href="contact.html">Contact<i class="fas fa-chevron-down"></i></a></li>
                                        </ul>
                                    </li>
                                    <li><a href="blog.html">Blog<i class="fas fa-chevron-down"></i></a></li>
                                    <li><a href="contact.html">Contact<i class="fas fa-chevron-down"></i></a></li>
                                </ul>
                            </div> <!-- Menu Trigger 
                            <div class="menu_trigger_container ml-auto">
                                <div class="menu_trigger d-flex flex-row align-items-center justify-content-end">
                                    <div class="menu_burger">
                                        <div class="menu_trigger_text">menu</div>
                                        <div class="cat_burger menu_burger_inner"><span></span><span></span><span></span></div>
                                    </div>
                                </div>
                            </div> -
                        </div>
                    </div>
                </div>
            </div>
        </nav>-->
         
         <!--  
        <div class="page_menu">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="page_menu_content">
                            <div class="page_menu_search">
                                <form action="#"> <input type="search" required="required" class="page_menu_search_input" placeholder="상품 검색하기"> </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        -->
    </header>
    <div style="height: 100px"> </div>
    </div>
</body>
</html>
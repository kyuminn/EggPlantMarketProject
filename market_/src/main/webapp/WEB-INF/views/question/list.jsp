<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>문의 목록</title>
    <jsp:include page="../common/header.jsp"></jsp:include>
</head>
<link rel="stylesheet" href="/css/list.css">
<body>
<div class="cart-wrap">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="main-heading mb-10">
                	문의 리스트 &nbsp;
                	
                	<c:if test="${loginSession ne sellerEmail }">
                	 <button type="button" class="btn btn-info" onclick="location.href='/question/add/${itemId}'">문의하기</button>
                	</c:if>
                </div>

           

                <div class="table-wishlist">
                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                        <thead>
                        <tr>
                            <th width="45%">문의 제목</th>
                            <th width="15%">작성자</th>
                            <th width="15%">답변 상황</th>
                            <th width="15%"></th>
                            <th width="10%"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="list" items="${questionList}" varStatus="status">
                        <tr>
                            <td width="45%">
                                <div class="display-flex align-center">
                                    <div class="name-product">
                                       ${list.title}
                                    </div>
                                </div>
                            </td>
                            <td width="15%" class="price">${list.writer}</td>
                            <td width="15%"><span class="in-stock-box">${list.isReplied}</span></td>
                            <td width="15%"><button class="round-black-btn small-btn" onclick="location.href='/question/detail/${list.id}'">문의&답변 보기</button></td>
                            <td width="10%" class="text-center"><a href="#" class="trash-icon"><i class="far fa-trash-alt"></i></a></td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>QnA 수정</title>

    <!-- include libraries(jQuery, bootstrap) -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <!-- include summernote css/js -->
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
    <!-- 파일 업로드 스크립트 -->
    <script src="/javascript/fileUploadScript.js"></script>

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
    <form:form modelAttribute="question" action="${pageContext.request.contextPath}/question/edit/${question.id}" method="post">
        <h1>문의내용 수정</h1>
        <hr/>
        <table class="table table-borderless">
            <tr>
                <th>제목</th>
                <td><form:input path="title"/>
                    <form:errors path="title"/>
                </td>
            </tr>
            <tr>
                <th>날짜</th>
                <td>
                    <form:input path="currentTime" readonly="true"/>
                </td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>
                    <form:input path="writer" readonly="true"/>
                </td>
            </tr>
            <tr>
                <th colspan="3">질문 내용</th>
                <form:errors path="content"/>
            </tr>
            <tr>
                <td colspan="3">
                    <textarea class="summernote" name="content">${question.content }</textarea>
                </td>
            </tr>
        </table>
        <input type="submit" class="btn btn-primary btn-lg" value="질문 수정">

        <!-- sellerId 조회를 위해 session에 저장된 에미엘 값 넣어주기 -->
        <input type="hidden" name="loginSession" value="${loginSession}">
    </form:form>
</div>
<script>
    $('.summernote').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 500,
        width:800,
        lang: "ko-KR"
    });
</script>
</body>
</html>
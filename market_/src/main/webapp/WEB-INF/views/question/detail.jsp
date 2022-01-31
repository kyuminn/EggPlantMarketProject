<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>문의 내용 및 답변</title>
    
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

	<style>
		.addContainer{
			width:800px;
			height:500px;
			margin:0 auto; /*container 가운데 정렬*/
			padding-top:50px; /*위쪽 여백 설정*/
		}
	</style>
</head>
<body>
<div class="addContainer">
	<form:form>
	<h1>문의 내용 <input type="button" class="btn btn-primary" value="목록" onclick="location.href='/question/list/${question.itemId}'"></h1>
	<hr/>
		<c:if test="${isAsker}">
				<input type="button" value="수정" onclick="location.href='/question/edit/${question.id}'">&nbsp;
				<input type="button" value="삭제" onclick="location.href='/question/delete/${question.id}'">
		</c:if>
		
	<table class="table table-borderless">
			<tr>
				<th>제목</th>
				<td>
					${question.title}
				</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td>
					${question.currentTime}
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					${question.writer}
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div class="summernote" id="content">
						<textarea name="content" readonly>${question.content}</textarea>
					</div> 
				</td>
			</tr>
		</table>
	</form:form>
	<!-- 판매자만 질문글에 댓글 달 수 있도록 -->
	<c:if test="${status}">
	<div class="card">
		<form action="/reply/${question.id}" method="post">
			<div class="card-body"><textarea class="form-control" rows="1" name="content"></textarea></div>
			<div class="card-footer"><button type="submit" class="btn btn-primary">댓글 달기</button></div>
		</form>
	</div>
	</c:if>
	<br>
	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<c:forEach var="list" items="${replyList}" varStatus="status">
		<ul class="list-group">
			<li class="list-group-item d-flex justify-content-between">
				<div>${list.content}</div>
				<div class="d-flex">
					<div>작성자 : ${member.nickname} &nbsp;</div>
					<c:if test="${reply_state == 1}">
						<button type="button" onclick="location.href='/reply/${list.id}'">삭제</button>
					</c:if>
				</div>
			</li>
		</ul>
		</c:forEach>
	</div>
	<br><br><br>
</div>
	<script>
		$('.summernote').summernote({
			height:300,
			minHeight:null
		})

		var content = '${question.content}';
		$('.summernote').summernote('code',content);
		$('.summernote').summernote('disable');
    </script>
</body>
</html>
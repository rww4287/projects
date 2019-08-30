<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">


<head>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$().ready(function(){
		$("#replyBtn").click(function(){
			var writer =  $('#replyWriter').val(); // 글쓴이 
			var content = $('#replyContent').val(); // 댓글 내용 
			var movieId = $('#movieId').val(); // 영화 id
			$.post("<c:url value="/movie/reply/write"/>", {
				"writer" : writer,
				"content" : content, 
				"movieId" : movieId
			}, function(response) {
				var res = JSON.parse(response);
				if( res.status == "success"){
					//$(".replyForm").html();
					location.reload();
				}
				else if ( res.status == "fail"){
					alert("댓글 등록 실패!");
				}
			});
		});
		$(".deleteReply").click(function(){
 			var result = confirm('정말로 삭제 하시겠습니까?');
			if(result) { 
				var replyId = $(this).data('id'); // reply id
				$.post("<c:url value="/movie/reply/remove"/>",{
					"replyId": replyId
				}, function(response){
					var res = JSON.parse(response);
					if( res.status == "success"){
						location.reload();
					}
					else if ( res.status == "fail"){
						alert(" 실패!");
					}
				});
				
			} 

		}); 
		
		$(".hashClass").click(function(){
			var keyword = $(this).data('keyword');
			console.log(keyword);
			$("#keyword").val(keyword);
 	 			$("#hashSearchForm").attr({
					"action" : "<c:url value="/movie"/>"
				}).submit();   
		});
	

		$(".likeBtn").click(function(){
			
			var likeVal = $("#likeCheck").val();
			var movieId = $("#movieId").val();
			
 			if(likeVal == "false") {
 				$.post("<c:url value="/movie/like/plus"/>", {
 					"movieId" : movieId
 				}, function (response) {
 					var res = JSON.parse(response);
 					if(res.status == "success"){
 						$(".likeBtn").val("♥");
 						console.log(res.likeCount);
 						$("#likeCount").html(res.likeCount);
 						$("#likeCheck").val("true");
 					}					
 				});
				
			} else {
				var likeId = 
				$.post("<c:url value="/movie/like/minus"/>", {
					"movieId" : movieId
 				}, function (response) {
 					var res = JSON.parse(response);
 					if(res.status == "success"){
 						$(".likeBtn").val("♡");
 						console.log(res.likeCount);
 						$("#likeCount").html(res.likeCount);
 						$("#likeCheck").val("false");
 						
 					}					
 				});
			} 
		});
		

	});
</script>

</head>
<body>
	<jsp:include page="../common/top.jsp"></jsp:include>
	<input type="hidden" id="movieId" value="${movie.movieId}">
	<div class="row" style="margin-left: 20px; margin-top: 20px;">
	  <div class="col-sm-6 col-md-4">
	    <div class="thumbnail">
	      <img src="${movie.moviePoster}" style="width: 300px; height: 420px;"/>
	      <div class="caption">
	        <h3>${movie.movieName}</h3>
	        <p>${movie.movieYear} 개봉 | ${movie.movieDirector}감독 </p>
	        <c:if test="${sessionScope._USER_.userEmail eq movie.movieWriter}"> 
	        	<p><a href="<c:url value="/movie/delete/${movie.movieId}"/>" class="btn btn-default" role="button">delete </a>
	        	<a href="<c:url value="/movie/update/${movie.movieId}"/>" class="btn btn-default" role="button">update</a></p>
	        	</c:if>
	        <c:forEach items="${movie.hashTagList}" var="hashTag">
	        	<a href="javascript:void(0)" class="hashClass" data-keyword="${hashTag.content}" >#${hashTag.content}</a> 
	        </c:forEach>
	      </div>
	    </div>
	  </div>
	</div>
	
	<form id="hashSearchForm">
 	    <input type="hidden" name="keyword" id="keyword">
 	</form> 
<%-- 	<input type="hidden" id="like" value="${like}"/> --%>
	
	<div style="margin-left: 20px">
		<form class="replyForm">
			<c:forEach items="${replyList}" var="reply">
				<input type="hidden" data-id="${reply.replyId}">
				${reply.replyWriter} | 	${reply.replyContent} (${reply.createdAt}) 
				<c:if test="${sessionScope._USER_.userName eq reply.replyWriter}"> <a href="javascript:void(0)" data-id="${reply.replyId}" class="deleteReply" style="margin-left: 5px">delete</a></c:if><br/>
			</c:forEach>
		</form>
	</div><br/>
 	좋아요 : <span id="likeCount">${likeCount}</span>
	<c:if test="${!empty sessionScope._USER_.userId}">
		<c:if test="${!likeCheck}">
			<input style="margin-left: 20px; background-color: transparent; border-color: transparent;" type="button" class="likeBtn" value="♡"/>
		</c:if>
		<c:if test="${likeCheck}">
			<input style="margin-left: 20px; background-color: transparent; border-color: transparent;" type="button" class="likeBtn" value="♥"/>
		</c:if>
	<input type="hidden" id="likeCheck" value="${likeCheck}"/>
	<form id="replyForm" style="margin-left: 20px">	
		<input type="hidden" id="replyWriter" value="${sessionScope._USER_.userName}"> 
		<input style="width: 50%;" type="text" id="replyContent" placeholder="댓글 내용 " > 
		<button id="replyBtn" style="margin-left: 10px">Submit</button>
	</form>
	</c:if>
	<nav>
	  <ul class="pager" style="margin-left: 20px;">
	    <li class="previous"><a href="<c:url value="/movie"/>"><span aria-hidden="true"></span> list </a></li>
	 
	  </ul>
	</nav>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$().ready(function(){
		
		//alert("dfds");

		
		$(".hashClass").click(function(){
			var keyword = $(this).data('keyword');
			console.log(keyword);
			$("#keyword").val(keyword);
 	 			$("#hashSearchForm").attr({
					"action" : "<c:url value="/movie"/>"
				}).submit();   
		});
		


	});
</script>

<style>

.t1 {
	font-size: 12px;
	color: #7b94b0;
}
.t2 {
	font-size: 13px;
	color: #6a89b0;
}
.t3 {
	font-size: 14px;
	color: #5880b0;
}
.t4 {
	font-size: 16px;
	color: #4676b0;
}
.t5 {
	font-size: 18px;
	color: #356cb0;
}
.t6 {
	font-size: 19px;
	color: #2363b0;
}
.t7 {
	font-size: 24px;
	color: #1259b0;
}
</style>



<body>  
	<jsp:include page="../common/top.jsp"></jsp:include>
	

	<div class="row">
		<c:forEach items="${movieList}" var="movie">
		  <div class="col-xs-4">
		  
			    <div class="thumbnail">
				    <a href="<c:url value="/movie/detail/${movie.movieId}"/>">
				      	<img src="${movie.moviePoster}" style="width: 300px; height: 420px;"/>
				      </a>
			      <div class="caption">
			        <p style="font-size: 25px;">${movie.movieName} </p>
			        <p>감독 : ${movie.movieDirector}</p>
			        <p>개봉 : ${movie.movieYear}</p>
			       	<c:forEach items="${movie.hashTagList}" var="hashTag">
	        			<a href="#" class="hashClass" data-keyword="${hashTag.content}" >#${hashTag.content}</a>
	        		</c:forEach>
			      </div>

		    </div>
		  
		  </div>
	    </c:forEach>
	</div>
	
	<form id="hashSearchForm">
 	    <input type="hidden" name="keyword" id="keyword">
 	</form> 

<%-- 	<div>
		<c:forEach items="${popularHashTagList}" var="popularHashTag">
			${popularHashTag.count}
			${popularHashTag.content}
		</c:forEach>
	</div> --%>
	
	<c:if test="${!empty sessionScope._USER_.userId}">
	<a class="glyphicon glyphicon-pencil" href="<c:url value="/movie/write"/>">영화추가 </a>
	</c:if>
	
</body>
</html>
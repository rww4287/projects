<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$().ready(function(){

	});
</script>


<body>
	<jsp:include page="../common/top.jsp"></jsp:include>
	
	<br/><br/><br/>

	<form action="<c:url value="/movie/write"/>" method="post">
	
		<div class="input-group">
		 	 <input type="text" class="form-control" name="movieName" placeholder="영화 제목" aria-describedby="sizing-addon2">
		</div>
		
		<div class="input-group">
			<input type="text" class="form-control" name="movieYear" placeholder="개봉 년도" aria-describedby="sizing-addon2">
		</div>
		<div class="input-group">
			 <input type="text" class="form-control" name="movieDirector" placeholder="영화 감독" aria-describedby="sizing-addon2">
		</div>
		<div class="input-group">
			<input type="text" class="form-control" name="moviePoster" placeholder="포스터 link" aria-describedby="sizing-addon2">
		</div>
		
		<div class="input-group input-group-sm">
		  <span class="input-group-addon" id="sizing-addon3">#</span>
		  <input type="text" class="form-control" name="hashTag" placeholder="HashTag" aria-describedby="sizing-addon3" style="width: 80%;">
		</div>
	
		<button type="submit" class="btn btn-default btn-sm" >
		  <span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 등록하기
		</button>

	</form>

</body>
</html>
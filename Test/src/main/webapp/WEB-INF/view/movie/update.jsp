<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">


<body>
	<jsp:include page="../common/top.jsp"></jsp:include>

	<form action="<c:url value="/movie/update"/>" method="post">
	
		<input type="hidden" name="movieId" value="${movie.movieId}"/>
		<div class="input-group input-group-sm">
		 	 <input type="text" class="form-control" name="movieName"  value="${movie.movieName}" aria-describedby="sizing-addon3" >
		</div>
		
		<div class="input-group input-group-sm">
			<input type="text" class="form-control" name="movieYear" value="${movie.movieYear}" aria-describedby="sizing-addon3">
		</div>
		<div class="input-group input-group-sm">
			 <input type="text" class="form-control" name="movieDirector" value="${movie.movieDirector}" aria-describedby="sizing-addon3">
		</div>
		<div class="input-group input-group-sm">
			<input type="text" class="form-control" name="moviePoster" value="${movie.moviePoster}" aria-describedby="sizing-addon3">
		</div>
		
		<button class="btn btn-default btn-sm" type="submit">
		  <span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 수정하기 
		</button>

	</form>

</body>
</html>
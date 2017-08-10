<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/static/css/bootstrap.min.css"/>" rel="stylesheet">
<style type="text/css">
@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
html, body, h1, p, a {padding:0;margin:0;line-height:1;font-family: 'Jeju Gothic', serif;} 
img {border: 0} 
div.imgTopic {position:relative;width:300px;height:420px;border: 1px solid #eee} 
div.imgTopic a {text-decoration: none} 
div.title {position:absolute;z-index:1;left:10px;bottom:50px} 
div.title a{font-size:18px;font-weight:bold;color:#fff;}
div.title a:hover, a:focus {text-decoration:underline;}
p.content a {font-size:12px;} 
span.genre {position:absolute;display:block;left:0;bottom:0;width:295px;height:35px;padding:0 0 5px 15px;background:url(‘./blackOpacity.png’) repeat;color:#000000;}

</style>


<title>Movie</title>
	<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
	
	<script type="text/javascript">
		$().ready(function(){
			
		});
	</script>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container">
			<a class="navbar-brand" href="#">Movie</a>
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#">리뷰</a></li>
				<li><a href="#">community</a></li>
			</ul>
		</div>
	</nav>
	
	<div class="container">


	</div>
	
</body>
</html>
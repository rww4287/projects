<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$().ready(function(){
		
		$("#hashTagBtn").click(function(){
			if($("#contentDiv").css('display') == 'none'){
				$("#contentDiv").css('display','block');
			} else if ($("#contentDiv").css('display') == 'block'){
				$("#contentDiv").css('display', 'none');
			}
			
		});
		
		$(".hash").click(function(){
			var keyword = $(this).data('keyword');
			console.log(keyword);
			$("#keyword").val(keyword);
 	 			$("#hashSearchForm").attr({
					"action" : "<c:url value="/movie"/>"
				}).submit();   
		});

	});
</script>

</head>
<body>
	<ul id="ultop" class="nav nav-tabs">
	  <li role="presentation" ><a href="<c:url value="/movie"/>">MOVIE</a></li>
	  <c:if test="${empty sessionScope._USER_.userId}">
	  <li role="presentation"><a href="<c:url value="/movie/user/login"/>">Login</a></li>
	  <li role="presentation"><a href="<c:url value="/movie/user/signUp"/>">SignUp</a></li>
	  </c:if>
	  <c:if test="${!empty sessionScope._USER_.userId}">
	  <li role="presentation"><a href="<c:url value="/movie/user/logout"/>">Logout</a></li>
	  </c:if>
	  <li role="presentation"><a href="<c:url value="/mail/send"/>">문의하기</a></li>
	 <li role="presentation"> <li role="presentation"><a href="javascript:void(0)" id="hashTagBtn">해시태그</a></li>
	</ul>
		<div id="contentDiv" style="width:200px; height: 200px; display: none; margin-top: 20px;">
			<script type="text/javascript">
				var contentArray = [];
				var sizeArray = [];
				
				<c:forEach items="${popularHashTagList}" var="popularHashTag">
					contentArray.push("${popularHashTag.content}");
				</c:forEach>
				
				<c:forEach items="${popularHashTagList}" var="popularHashTag">
					sizeArray.push("${popularHashTag.count}");
				</c:forEach>
				
				for ( var i in contentArray) {
					document.write("<a href='#' class='hash t" + sizeArray[i] + "' data-keyword='" + contentArray[i] + "' style='font-family: 'Nanum Pen Script''>"+ contentArray[i] + "</a>\n");
				}
			</script>
		</div> 
		
	<form id="hashSearchForm">
 	    <input type="hidden" name="keyword" id="keyword">
 	</form> 

</body>
</html>
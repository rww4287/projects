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
		$("#emailSumitBtn").click(function(){
			if($("#storeName").val() == "" || $("#storeName").val() == null){
				alert("매장 이름을 입력해주세요!");
				$("#storeName").focus();
				return false;
			} else if($("#position").val() == "" || $("#position").val() == null){
				alert("매장 위치 입력해주세요!");
				$("#position").focus();
				return false;
			} else if($("#phone").val() == "" || $("#phone").val() == null){
				alert("연락처 입력해주세요!");
				$("#phone").focus();
				return false;
			} else if($("#email").val() == "" || $("#email").val() == null){
				alert("이메일을 입력해주세요!");
				$("#phone").focus();
				return false;
			}
		});
	});
</script>

</head>
</head>
<body>
<jsp:include page="../common/top.jsp"></jsp:include>

	<div class="container t-m" style="text-align: center; margin-bottom: 24px;"> 
	  <h4>문의하기 </h4>
	</div>
	<div>
	<form id="emailForm" method="post" action="<c:url value="/mail/send"/>">
		
		<input type="hidden" name="title" value="고객문의메일">
			<div class="col-xs-4 col-md-1 desc">
				<span class="start">*</span>
				매장이름
			</div>
			<div class="col-xs-8 col-md-5">
				<input type="text" name="storeName" id="storeName" style="width: 50%;" placeholder="매장이름 " class="form-control">
		    </div>
		    
		    <div class="col-xs-4 col-md-1 desc">
				<span class="start">*</span>
				매장위치 
			</div>
			<div class="col-xs-8 col-md-5">
		    	<input type="text" name="position" id="position" style="width: 50%; margin-bottom: 5%;" placeholder="매장 위치 " class="form-control">
		    </div>
	    
	    <div class="col-xs-4 col-md-1 desc">
			<span class="start">*</span>
				연락처 
		</div>
		<div class="col-xs-8 col-md-5">
	    	<input type="text" name="phone" id="phone" style="width: 50%;" placeholder="연락처 " class="form-control">
		</div>
		
		<div class="col-xs-4 col-md-1 desc">
			<span class="start">*</span>
				이메일  
		</div>
		<div class="col-xs-8 col-md-5">
	   		<input type="text" name="email" id="email" style="width: 50%; margin-bottom: 5%;" placeholder="이메일 주소 " class="form-control">
	    </div>
	    
	    
	  	<div class="col-xs-4 col-md-1 desc">
			<span class="start">*</span>
			문의내용 
		</div>
	    
	    <div class="col-xs-4 col-md-9">
	    <textarea name="content" placeholder="문의 내용 "
	            class="form-control"></textarea>
		</div>
		<br/><br/><br/>
		

		<button id="emailSumitBtn" class="btn btn-default btn-sm">
				문의하기 
		</button> 
	   </form>
   </div>
    
</body>
</html>
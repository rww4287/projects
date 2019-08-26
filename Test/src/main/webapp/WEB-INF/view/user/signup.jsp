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
		$("#signUpBtn").click(function(){
			if($("#userEmail").val() == null || $("#userEmail").val() == ""){
				alert("이메일 입력해주세요!");
				$("#userEmail").focus();
				return false;
			}else if($("#userName").val() == null || $("#userName").val() == ""){
				alert("이름을 입력해주세요!");
				$("#userName").focus();
				return false;
			}else if($("#userPassword").val() == null || $("#userPassword").val() == ""){
				alert("비밀번호를 입력해주세요!");
				$("#userPassword").focus();
				return false;
			}
			
		}); 	



	});
</script>
</head>
<body>
	<jsp:include page="../common/top.jsp"></jsp:include>

	<form id="signUpForm" method="post" action="<c:url value="/movie/user/signUp"/>" style="margin-left: 20px; margin-top: 20px;">
	
<!-- 		<div class="input-group input-group-sm">
		 	 <input type="text" class="form-control" name="userId" placeholder="id를 입력해주세요" aria-describedby="sizing-addon3">
		</div> -->
		
		<div class="input-group input-group-sm">
			<input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="e-mail을 입력해주세요 " aria-describedby="sizing-addon3">
		</div>
		<div class="input-group input-group-sm">
			 <input type="text" class="form-control" id="userName" name="userName" placeholder="이름을 입력해주세요 " aria-describedby="sizing-addon3">
		</div>
		<div class="input-group input-group-sm">
			<input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="password를 입력해주세요 " aria-describedby="sizing-addon3">
		</div>
		
		<button type="submit" id="signUpBtn" class="btn btn-default btn-sm" style ="margin-top: 20px;" >
		  <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>회원 가입
		</button>

	</form>

</body>
</html>


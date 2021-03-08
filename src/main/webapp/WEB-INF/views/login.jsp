<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1 align="center">Login</h1>

		<div
			style="width: 500px; margin: 0 auto; margin-top: 100px; padding: 60px"
			class="bg-success">
			<form onsubmit="return checkForm()" action='<c:url value="/login"/>'
				method="POST">
				<div class="form-group">
					<label for="email">Email:</label> <input type="email"
						class="form-control" id="email" placeholder="Enter email"
						name="txtEmail">
					<p id='messageEmail' style="color: red"></p>
				</div>
				<div class="form-group">
					<label for="pwd">Password:</label> <input type="password"
						class="form-control" id="pwd" placeholder="Enter password"
						name="txtPassword">
					<p id='messagePassword' style="color: red"></p>
				</div>
				<div>
					<c:if test="${sessionScope.message!=null}">
						<c:choose>
							<c:when test="${sessionScope.message.status==false}">
								<p style="color: red">${sessionScope.message.content}</p>
							</c:when>
						</c:choose>
						<c:remove var="message" scope="session" />
					</c:if>
				</div>
				<button type="submit" class="btn btn-success">Đăng nhập</button>
				<a href="register" type="button" class="btn btn-danger">Đăng ký</a>
			</form>
		</div>

	</div>
</body>
<script type="text/javascript">
	const checkForm=()=>{
		var messEmail=document.getElementById('messageEmail');
		var messPassword=document.getElementById('messagePassword');
		messEmail.innerHTML=""
		messPassword.innerHTML=""
		var email= document.getElementById('email').value
		var password= document.getElementById("pwd").value
		if(!email&&!password){
			messEmail.innerHTML="Invalid email"
			messPassword.innerHTML="Invalid password"
			return false;
		}
		if(!email){
			messEmail.innerHTML="Invalid email"
			return false;
		}
		if(!password){
			messPassword.innerHTML="Invalid password"
			return false;
		}
		return true;
	}
</script>
</html>
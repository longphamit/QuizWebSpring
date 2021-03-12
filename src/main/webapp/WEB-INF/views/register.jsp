<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REGISTER</title>
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
		<h1 align="center">Register</h1>

		<div
			style="width: 500px; margin: 0 auto; margin-top: 100px; padding: 60px"
			class="bg-success">
			<form onsubmit="return checkForm()" action='<c:url value="/register"/>'
				method="POST">
				<div class="form-group">
					<label for="email">Email:</label> <input type="email"
						class="form-control" id="email" placeholder="Enter email"
						name="txtEmail">
					<p id='messageEmail' style="color: red"></p>
				</div>
				<div class="form-group">
					<label for="name">Name:</label> <input type="text"
						class="form-control" id="name" placeholder="Enter your name"
						name="txtName">
					<p id='messageName' style="color: red"></p>
				</div>
				<div class="form-group">
					<label for="phone">Phone:</label> <input type="text"
						class="form-control" id="phone" placeholder="Enter your phone"
						name="txtPhone">
					<p id='messagePhone' style="color: red"></p>
				</div>

				<div class="form-group">
					<label for="pwd">Password:</label> <input type="password"
						class="form-control" id="pwd" placeholder="Enter password"
						name="txtPassword">
					<p id='messagePassword' style="color: red"></p>
				</div>
				<div class="form-group">
					<label for="confirm">Confirm:</label> <input type="password"
						class="form-control" id="confirm" placeholder="Enter confirm"
						name="txtConfirm">
					<p id='messageConfirm' style="color: red"></p>
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
				
				<button type="submit" class="btn btn-danger">Đăng ký</button>
			</form>
			<a href="<c:url value='/'/>">Quay về đăng nhập</a>
		</div>

	</div>
</body>
<script type="text/javascript">
	const checkForm=()=>{
		var messageEmail=document.getElementById('messageEmail');
		var messagePassword=document.getElementById('messagePassword');
		var messageConfirm=document.getElementById('messageConfirm');
		var messagePhone=document.getElementById('messagePhone');
		var messageName=document.getElementById('messageName');
		
		var email= document.getElementById('email').value.trim()
		var password= document.getElementById("pwd").value.trim()
		var phone= document.getElementById('phone').value.trim()
		var name= document.getElementById("name").value.trim()
		var confirm= document.getElementById('confirm').value.trim()
		
		
		messageEmail.innerHTML=""
		messagePassword.innerHTML=""
		messagePhone.innerHTML=""
		messageConfirm.innerHTML=""
		messageName.innerHTML=""
		if(!email&&!password&&!name&&!phone&&!confirm){
			messageEmail.innerHTML="Invalid email"
			messagePassword.innerHTML="Invalid password"
			messageConfirm.innerHTML="Invalid confirm"
			messagePhone.innerHTML="Invalid phone"
			messageName.innerHTML="Invalid name"
			return false;
		}
		if(!email){
			messageEmail.innerHTML="Invalid email"
			return false;
		}
		if(!name){
			messageName.innerHTML="Invalid name"
			return false;
		}
		if(!phone){
			messagePhone.innerHTML="Invalid phone"
			return false;
		}
		if(!password){
			messagePassword.innerHTML="Invalid password"
			return false;
		}
		if(!confirm){
			messageConfirm.innerHTML="Invalid confirm"
			return false;
		}else{
			if(password&&password!=confirm){
				messageConfirm.innerHTML="Invalid confirm"
				return false;
			}
		}
		return true;
	}
</script>
</html>
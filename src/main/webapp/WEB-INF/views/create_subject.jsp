<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CREATE SUBJECT</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div>
			<%@ include file="navbar/nav_admin.jsp"%>
		</div>
		<div>
			<c:if test="${sessionScope.message!=null}">
				<c:choose>
					<c:when test="${sessionScope.message.status==false}">
						<p
							style="color: red; margin: auto; text-align: center; width: 50%; border: 3px solid green; padding: 10px">${sessionScope.message.content}</p>
					</c:when>
					<c:when test="${sessionScope.message.status==true}">
						<p
							style="color: green; margin: auto; text-align: center; width: 50%; border: 3px solid green; padding: 10px">${sessionScope.message.content}</p>
					</c:when>
				</c:choose>
				<c:remove var="message" scope="session" />
			</c:if>
			<div
				style="width: 1000px; margin: 0 auto; padding-right: 50px; padding-left: 50px; padding-top: 40px; padding-bottom: 50px"
				class="bg-success">
				<form:form modelAttribute="subject" action="create_subject" method="POST"
					onsubmit="return checkForm()">
					<div class="form-group">
						<label for="question">Tên</label>
						<form:input class="form-control" id="name"
							placeholder="Enter name" path="name"></form:input>
						<p id='messageName' style="color: red"></p>
					</div>
					<button type="submit" class="btn btn-success">Lưu</button>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="static/footer.jsp"%>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript">
	const checkForm=()=>{
		var messageName=document.getElementById('messageName');
		var name= document.getElementById('name').value.trim()
		messageName.innerHTML=""
		if(!name){
			messageName.innerHTML="Invalid Name"
			return false;
		}
		return true;
	}
	</script>
</body>
</html>
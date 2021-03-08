<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
				<form:form modelAttribute="quiz"
					action="create_quiz" method="POST">
					<div class="form-group">
						<label for="question">Question</label>
						<form:input class="form-control" id="name"
							placeholder="Enter name" path="name"></form:input>
						<p id='messageName' style="color: red"></p>
					</div>
					<div class="form-group">
						<label for="ansB">Time Take</label>
						<form:input class="form-control answer" id="timeTake"
							type="text" path="timeTake"></form:input>
						<p id='messageTimeTake' style="color: red"></p>
					</div>
					<div class="form-group">
						<label for="ansB">Num Question</label>
						<form:input class="form-control answer" id="numQuestion"
							type="text" path="numQuestion"></form:input>
						<p id='messageNumQuestion' style="color: red"></p>
					</div>
					<div class="form-group container">
						<div class="row">
							<table>
								<tr>
									<td>
										<div style="margin-right: 30px">
											<form:select path="subjectId">
												<form:options items="${listSubject}" />
											</form:select>
										</div>
									</td>
								</tr>
							</table>
						</div>
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
					<button type="submit" class="btn btn-success">Lưu</button>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="static/footer.jsp"%>
	<script type="text/javascript">
	const checkForm=()=>{
		var messageName=document.getElementById('messageName');
		var messageTimeTake=document.getElementById('messageTimeTake');
		var messageNumQuestion=document.getElementById('messageNumQuestion');
	
		var name= document.getElementById('name').value.trim()
		var timeTake= document.getElementById("timeTake").value.trim()
		var numQuestion= document.getElementById('numQuestion').value.trim()
		
		
		messageName.innerHTML=""
		messageTimeTake.innerHTML=""
		messageNumQuestion.innerHTML=""
		
		if(!messageQuestion&&!messageA&&!messageB&&!messageC&&!messageD	){
			messageName.innerHTML="Invalid Name"
			messageTimeTake.innerHTML="Invalid Time Take"
			messageNumQuestion.innerHTML="Invalid Num Question"
			return false;
		}
		
		return true;
	}
	
	
	
	
	</script>
</body>

</html>
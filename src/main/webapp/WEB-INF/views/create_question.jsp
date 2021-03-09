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
				<form:form onsubmit="return checkForm()" modelAttribute="question"
					action="create_question" method="POST">
					<div class="form-group">
						<label for="question">Question</label>
						<form:textarea class="form-control" id="questionContent"
							placeholder="Enter question" name="txtQuestion" path="content"></form:textarea>
						<p id='messageQuestion' style="color: red"></p>
					</div>
					<div class="form-group">
						<label for="ansA">A</label>
						<form:textarea class="form-control answer" id="ansA"
							placeholder="Enter Answer A" name="txtAnswerA" path="answerA"></form:textarea>
						<p id='messageA' style="color: red"></p>
					</div>
					<div class="form-group">
						<label for="ansB">B</label>
						<form:textarea class="form-control answer" id="ansB"
							placeholder="Enter Answer B" name="txtAnswerB" path="answerB"></form:textarea>
						<p id='messageB' style="color: red"></p>
					</div>
					<div class="form-group">
						<label for="ansC">C</label>
						<form:textarea class="form-control answer" id="ansC"
							placeholder="Enter Answer C" name="txtAnswerC" path="answerC"></form:textarea>
						<p id='messageC' style="color: red"></p>
					</div>
					<div class="form-group">
						<label for="ansD">D</label>
						<form:textarea class="form-control answer" id="ansD"
							placeholder="Enter Answer D" name="txtAnswerD" path="answerD"></form:textarea>
						<p id='messageD' style="color: red"></p>
					</div>
					<label style="color: red">Choose right answer</label>
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
									<td><div style="margin-right: 30px">
											<label for="male">A</label>
											<form:radiobutton id="male" checked="checked" name="ansRight"
												value="A" path="rightAnswer" />
										</div></td>
									<td><div style="margin-right: 30px">
											<label for="female">B</label>
											<form:radiobutton id="female" name="ansRight" value="B"
												path="rightAnswer" />
										</div></td>
									<td><div style="margin-right: 30px">
											<label for="other">C</label>
											<form:radiobutton id="other" name="ansRight" value="C"
												path="rightAnswer" />
										</div></td>
									<td><div style="margin-right: 30px">
											<label for="other">D</label>
											<form:radiobutton id="other" name="ansRight" value="D"
												path="rightAnswer" />
										</div></td>
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
		var messageQuestion=document.getElementById('messageQuestion');
		var messageA=document.getElementById('messageA');
		var messageB=document.getElementById('messageB');
		var messageC=document.getElementById('messageC');
		var messageD=document.getElementById('messageD');
		
		var ansA= document.getElementById('ansA').value.trim()
		var ansB= document.getElementById("ansB").value.trim()
		var ansC= document.getElementById('ansC').value.trim()
		var ansD= document.getElementById("ansD").value.trim()
		var question= document.getElementById('questionContent').value
		messageQuestion.innerHTML=""
		messageA.innerHTML=""
		messageB.innerHTML=""
		messageC.innerHTML=""
		messageD.innerHTML=""
		
			if(!messageQuestion&&!messageA&&!messageB&&!messageC&&!messageD	){
				messageA.innerHTML="Invalid answer A"
				messageB.innerHTML="Invalid answer B"
				messageC.innerHTML="Invalid answer C"
				messageD.innerHTML="Invalid answer D"
				messageQuestion.innerHTML="Invalid question"
				return false;
			}
			if(!question){
				messageQuestion.innerHTML="Invalid question"
				return false;
			}
			if(!ansA){
				messageA.innerHTML="Invalid answer A"
				return false;
			}
			if(!ansB){
				messageB.innerHTML="Invalid answer B"
				return false;
			}
			if(!ansC){
				messageC.innerHTML="Invalid answer C"
				return false;
			}
			if(!ansD){
				messageD.innerHTML="Invalid answer D"
				return false;
			}
			var x = document.getElementsByClassName("answer");
			for (var i = 0; i < x.length-1; i++) {
				for(var j=i+1;j<x.length;j++){
					if(x.item(i).value==x.item(j).value){
						alert("Giá trị "+x.item(i).value+" bị trùng")
						console.log(x.item(i).value)
						return false;
					}
				}
			}	
		return true;
	}
	
	
	
	
	</script>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CREATE QUIZ</title>
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
				<form:form modelAttribute="quiz" action="create_quiz" method="POST"
					onsubmit="return checkForm()">
					<div class="form-group">
						<label for="question">Quiz name</label>
						<form:input class="form-control" id="name"
							placeholder="Enter name" path="name"></form:input>
						<p id='messageName' style="color: red"></p>
					</div>
					
					<div class="form-group">
						<label for="ansB">Time Take (minutes)</label>
						<form:input class="form-control answer" id="timeTake" type="text"
							path="timeTake"></form:input>
						<p id='messageTimeTake' style="color: red"></p>
					</div>

					<div class="form-group container">
						<div class="row">
							<table>
								<tr>
									<td>
										<div style="margin-right: 30px">
											<form:select path="subjectId" onchange="checkSubject(this)">
												<form:option value="">M??n H???c</form:option>
												<form:options items="${listSubject}" />
											</form:select>
											<p id='messageSubject' style="color: red"></p>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="form-group" id="inputNumQuestion"
						style="visibility: hidden;">
						<label for="ansB">Num Question</label> <select id="cbxNumQuestion"
							name="cbxNumQuestion">
						</select>
						<p id="messageNumQuestion" style="color: red"></p>
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
					<button type="submit" class="btn btn-success">L??u</button>
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
		var rexNum=new RegExp('[A-Za-z\.]{1,}')
		var messageName=document.getElementById('messageName');
		var messageTimeTake=document.getElementById('messageTimeTake');
		var messageSubject=document.getElementById('messageSubject');
		var messageNumQuestion=document.getElementById('messageNumQuestion');
		
		var numQuestion=document.getElementById('cbxNumQuestion').value.trim();
		var subject=document.getElementById('subjectId').value.trim()
		var name= document.getElementById('name').value.trim()
		var timeTake= document.getElementById("timeTake").value.trim()

		messageName.innerHTML=""
		messageTimeTake.innerHTML=""
		messageSubject.innerHTML=""
		if(!name&&timeTake==0&&!subject){
			messageName.innerHTML="Invalid Name"
			messageTimeTake.innerHTML="Invalid Time Take"
			messageSubject.innerHTML="Invalid Subject"	
			return false;
		}
		if(!name){
			messageName.innerHTML="Invalid Name"
			return false;
		}
		if(timeTake==0){
			messageTimeTake.innerHTML="Invalid Time Take"
			return false;
		}else{
			if(rexNum.test(timeTake)){
				messageTimeTake.innerHTML="Invalid Time Take"
				return false;	
			}
		}	
		if(!subject){
			messageSubject.innerHTML="Invalid Subject"	
			return false;
		}
		if(!numQuestion){
			messageNumQuestion.innerHTML="Invalid num of question."
			return false;
		}
		
		
		return true;
	}
	const checkSubject=(select)=>{
		if(select.options[select.selectedIndex].value==""){
			$("#cbxNumQuestion").html("")
			document.getElementById("inputNumQuestion").style.visibility="hidden"
		}else{
			$.ajax({
	            url: "http://localhost:8080/QuizWebSpring/api/num_question",
	            method: "GET",
	            cache: false,
	            data: {
	                subjectId:select.options[select.selectedIndex].value
	            },
	            success: function (data, textStatus, jqXHR) {
	            	var options="";
	            	for(var i=1;i<=parseInt(data);i++){
	            		options+='<option value="'+i+'">'+i+'</option>'
	            	}
	            	$("#cbxNumQuestion").html(options)
	                document.getElementById("inputNumQuestion").style.visibility="visible"
	            }
	        });
		}
	}
	</script>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
			<%@ include file="navbar/nav_home.jsp"%>
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
		</div>
		<div style="margin-left: 20px">
		
			<c:if test="${sessionScope.quiz_do.currentTimeDo eq null}">
				<input type="hidden" id="timeTake"
					value="${sessionScope.quiz_do.quizDTO.timeTake}" />
				</c:if>
			<c:if test="${sessionScope.quiz_do.currentTimeDo ne null}">
				<input type="hidden" id="timeTake"
					value="${sessionScope.quiz_do.currentTimeDo}" />
			</c:if>
			<h3 id="timer"></h3>

			<a style="margin-bottom: 20px" class="btn btn-danger" href="#">Finish
				Quiz</a>
		</div>
		<div class="col-sm-4">
			<div
				style="border-style: solid; background-color: #ffffe6; border-color: #e6f2ff;">
				<div style="display: inline;">
					<input type="hidden" value="${quiz_do.numQuestion }" id="numQuestion"/>
					<c:forEach begin="1" end="${quiz_do.numQuestion}" var="i">
						<form style="display: inline-block;" action="do_quiz"
							method="POST">
							<input type="hidden" id="inputTimer${i}" name="inputTimer"/>
								 <input type="hidden"
								name="id" value="${param.id }"></input> <input type="hidden"
								name="questionId" value="${quiz_do.listQuestion.get(i-1).id}"></input>
							<c:if test="${param.questionId eq null }">
								<c:if test="${i==1}">
									<button type="submit" 
										style="border-color: red; background-color: #e6f2ff; display: inline-block; width: 30px; border-style: solid; border-width: thin; text-align: center; margin: 10px">${i}</button>
								</c:if>
								<c:if test="${i!=1 }">
									<button type="submit" 
										style="background-color: #e6f2ff; display: inline-block; width: 30px; border-style: solid; border-width: thin; text-align: center; margin: 10px">${i}</button>
								</c:if>
							</c:if>
							<c:if test="${param.questionId ne null }">
								<c:if
									test="${quiz_do.listQuestion.get(i-1).id eq param.questionId }">
									<button type="submit" 
										style="border-color: red; background-color: #e6f2ff; display: inline-block; width: 30px; border-style: solid; border-width: thin; text-align: center; margin: 10px">${i}</button>
								</c:if>
								<c:if
									test="${quiz_do.listQuestion.get(i-1).id ne param.questionId}">
									<button type="submit" 
										style="background-color: #e6f2ff; display: inline-block; width: 30px; border-style: solid; border-width: thin; text-align: center; margin: 10px">${i}</button>
								</c:if>
							</c:if>
						</form>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="col-sm-8">
			<div
				style="height: 500px; border-style: solid; padding: 50px; background-color: #ffffe6; border-color: #e6f2ff;">
				<label>Question: </label>
				<p>${requestScope.questionDo.content}</p>
				<div style="display: inline-block;">
					<b style="text-decoration:">A</b> <input name="rdAnswer"
						type="radio" onclick="chooseAnswer(this)" value="A">
					<p>${requestScope.questionDo.answerA}</p>
				</div>
				<div>
					<b>B</b> <input onclick="chooseAnswer(this)" value="B" name="rdAnswer" type="radio">
					<p>${requestScope.questionDo.answerB}</p>
				</div>
				<div>
					<b>C</b> <input name="rdAnswer" type="radio" onclick="chooseAnswer(this)" value="C">
					<p>${requestScope.questionDo.answerC}</p>
				</div>
				<div>
					<b>D</b> <input name="rdAnswer" type="radio" onclick="chooseAnswer(this)" value="D">
					<p>${requestScope.questionDo.answerD}</p>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="static/footer.jsp"%>
</body>
<script type="text/javascript">
	var count = document.getElementById("timeTake").value;
	var counter = setInterval(timer, 1000); //1000 will  run it every 1 second
	var numQuestion=document.getElementById("numQuestion").value
	function timer() {
		count = count - 1;
		if (count == -1) {
			clearInterval(counter);
			return;
		}

		var seconds = count % 60;
		var minutes = Math.floor(count / 60);
		minutes %= 60;
		for(var i=1;i<=numQuestion;i++){
			document.getElementById("inputTimer"+i).value = minutes * 60 + seconds;
		}
		document.getElementById("timer").innerHTML = minutes + ":" + seconds; // watch for spelling
	}
	const chooseAnswer=(input)=>{
		
		alert(input.value)
	}
</script>
</html>
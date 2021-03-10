<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DO QUIZ</title>
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
			<div style="height: 50px">
				<h3 id="timer"></h3>
			</div>

			<form action="<c:url value='/finish_quiz'/>" method="POST">
				<button class="btn btn-danger">Finish Quiz</button>
			</form>

		</div>
		<div class="col-sm-4">

			<div
				style="border-style: solid; background-color: #ffffe6; border-color: #e6f2ff;">
				<div style="display: inline;">
					<input type="hidden" value="${quiz_do.numQuestion }"
						id="numQuestion" />
					<c:forEach begin="1" end="${quiz_do.numQuestion}" var="i">
						<form style="display: inline-block;" action="do_quiz"
							method="POST">
							<input type="hidden" id="inputTimer${i}" name="inputTimer" /> <input
								type="hidden" name="id" value="${param.id }"></input> <input
								type="hidden" name="questionId"
								value="${quiz_do.listQuestion.get(i-1).id}"></input>
							<c:if test="${param.questionId eq null }">
								<c:if test="${i==1}">
									<c:set var="next" value="${i+1 }"></c:set>
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
									<c:set var="next" value="${i+1}"></c:set>
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
				style="border-style: solid; padding: 50px; background-color: #ffffe6; border-color: #e6f2ff;">
				<label>Question: </label>
				<p>${sessionScope.questionDo.content}</p>
				<input type="hidden" id="questionDoId"
					value="${sessionScope.questionDo.id }">
				<div style="border-style: solid; margin-bottom: 10px;padding: 10px;border-color: #ebfaeb">
					<b style="text-decoration:">A</b>
					<c:choose>
						<c:when test="${'A'== sessionScope.questionDo.chooseAnswer }">
							<input checked name="rdAnswer" type="radio"
								onclick="chooseAnswer(this)" value="A">
						</c:when>
						<c:when test="${'A' != sessionScope.questionDo.chooseAnswer }">
							<input name="rdAnswer" type="radio" onclick="chooseAnswer(this)"
								value="A">
						</c:when>
					</c:choose>

					<p>${sessionScope.questionDo.answerA}</p>
				</div>
				<div style="border-style: solid; margin-bottom: 10px;padding: 10px;border-color: #ebfaeb">

					<b>B</b>
					<c:choose>
						<c:when test="${'B' == sessionScope.questionDo.chooseAnswer }">
							<input checked name="rdAnswer" type="radio"
								onclick="chooseAnswer(this)" value="B">
						</c:when>
						<c:when test="${'B' != sessionScope.questionDo.chooseAnswer }">
							<input name="rdAnswer" type="radio" onclick="chooseAnswer(this)"
								value="B">
						</c:when>
					</c:choose>
					<p>${sessionScope.questionDo.answerB}</p>
				</div>
				<div style="border-style: solid; margin-bottom: 10px;padding: 10px;border-color: #ebfaeb">
					<b>C</b>
					<c:choose>
						<c:when test="${'C' == sessionScope.questionDo.chooseAnswer }">
							<input checked name="rdAnswer" type="radio"
								onclick="chooseAnswer(this)" value="C">
						</c:when>
						<c:when test="${'C' != sessionScope.questionDo.chooseAnswer }">
							<input name="rdAnswer" type="radio" onclick="chooseAnswer(this)"
								value="C">
						</c:when>
					</c:choose>
					<p>${sessionScope.questionDo.answerC}</p>
				</div>
				<div style="border-style: solid; margin-bottom: 10px;padding: 10px;border-color: #ebfaeb">
					<b>D</b>
					<c:choose>
						<c:when test="${'D' == sessionScope.questionDo.chooseAnswer }">
							<input checked name="rdAnswer" type="radio"
								onclick="chooseAnswer(this)" value="D">
						</c:when>
						<c:when test="${'D' != sessionScope.questionDo.chooseAnswer }">
							<input name="rdAnswer" type="radio" onclick="chooseAnswer(this)"
								value="D">
						</c:when>
					</c:choose>
					<p>${sessionScope.questionDo.answerD}</p>
				</div>
			</div>
			<c:if test="${quiz_do.listQuestion.size()>=next}">
				<form action="<c:url value='next_question'/>" method="POST">
					<input type="hidden" name="id" value="${param.id}"></input> <input
						type="hidden" name="questionId"
						value="${quiz_do.listQuestion.get(next-1).id}"></input> <input
						type="hidden" id="inputTimerNext" name="inputTimer" /> <input
						type="hidden" name="next" value="${next}" />
					<button type="submit" class="btn btn-warning">Next
						Question</button>
				</form>
			</c:if>


		</div>
	</div>
	<%@ include file="static/footer.jsp"%>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
		if(seconds==0&&minutes==0){
			$.ajax({
	            url: "http://localhost:8080/QuizWebSpring/api/finish_quiz",
	            method: "POST",
	            cache: false,
	            success: function (data, textStatus, jqXHR) {
	            	alert("Time out")
	            	location.replace("http://localhost:8080/QuizWebSpring/review_quiz");
	            }
	        });
		}else{
			for(var i=1;i<=numQuestion;i++){
				document.getElementById("inputTimer"+i).value = minutes * 60 + seconds;
			}
			if(null!=document.getElementById("inputTimerNext")){
				document.getElementById("inputTimerNext").value=minutes * 60 + seconds;
			}
			document.getElementById("timer").innerHTML = minutes + ":" + seconds; // watch for spelling
		}
	}
	const chooseAnswer=(input)=>{
		var questionId= document.getElementById("questionDoId").value;
		$.ajax({
            url: "http://localhost:8080/QuizWebSpring/api/choose_answer",
            method: "GET",
            cache: false,
            data: {
                answer: input.value,
                questionId:questionId
            },
            success: function (data, textStatus, jqXHR) {
                return true;
            }
        });
	}
</script>
</html>
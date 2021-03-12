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
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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

		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<div style="margin-left: 20px">
				<div style="height: 50px">
					<h3 id="timer"></h3>
				</div>
				<div class="row justify-content-center mt-2">
					<ul id="pagination"></ul>
					<form action="<c:url value='/finish_quiz'/>" method="POST">
						<button class="btn btn-danger">Finish Quiz</button>
					</form>
				</div>

			</div>
			<div
				style="border-style: solid; background-color: #ffffe6; border-color: #e6f2ff; margin: 0 auto;">
				<div style="display: inline;">
					<input type="hidden" value="${quiz_do.numQuestion }"
						id="numQuestion" />
					<form id="submit" action="finish_quiz" method="POST">
						<c:forEach var="question"
							items="${sessionScope.quiz_do.listQuestion}" varStatus="counter">
							<input type="hidden" id="questionDoId${counter.count}"
								value="${question.id }" />
							<div class="contentPage">
								<div class="card">
									<div class="card-header">
										<h3>
											<strong>Question: ${counter.count}</strong>
										</h3>
									</div>
									<div class="card-body">
										<input type="hidden" name="questionId${counter.count}"
											value="${question.id}" />
										<ul class="list-group list-group-flush">
											<li class="list-group-item"><strong>${question.content}</strong></li>
											<li class="list-group-item"><input type="radio"
												name="txtAnswer${counter.count}" value="A"
												onclick="chooseAnswer(this,${counter.count})">A.
												${question.answerA}</li>
											<li class="list-group-item"><input type="radio"
												name="txtAnswer${counter.count}" value="B"
												onclick="chooseAnswer(this,${counter.count})">B.
												${question.answerB}</li>
											<li class="list-group-item"><input type="radio"
												name="txtAnswer${counter.count}" value="C"
												onclick="chooseAnswer(this,${counter.count})">C.
												${question.answerC}</li>
											<li class="list-group-item"><input type="radio"
												name="txtAnswer${counter.count}" value="D"
												onclick="chooseAnswer(this,${counter.count})">D.
												${question.answerD}</li>
										</ul>
									</div>
								</div>
							</div>
						</c:forEach>
					</form>
				</div>
			</div>
		</div>
		<div class="col-sm-2"></div>
	</div>
	<%@ include file="static/footer.jsp"%>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="http://1892.yn.lt/blogger/JQuery/Pagging/js/jquery.twbsPagination.js"
	type="text/javascript"></script>
<script type="text/javascript">
	
	 //1000 will  run it every 1 second
	var numQuestion=document.getElementById("numQuestion").value
	 $(function () {
         var pageSize = 1; //Show 1 one question per page
         showPage = function (page) {
             $(".contentPage").hide();
             $(".contentPage").each(function (n) {
                 if (n >= pageSize * (page - 1) && n < pageSize * page)
                     $(this).show();
             });
         };
         showPage(1);

         var totalRows = ${sessionScope.quiz_do.numQuestion}; //Total question to show
         var btnPage = 5; //The number of buttons displaye for choose page
         var iTotalPages = Math.ceil(totalRows / pageSize);

         var obj = $('#pagination').twbsPagination({
             totalPages: iTotalPages,
             visiblePages: btnPage,
             onPageClick: function (event, page) {
                 showPage(page);
             }
         });
         window.onload = function () {
             var endTime = ${sessionScope.quiz_do.startTime};
             var currentTime = new Date().getTime();
             var count = Math.round((endTime - currentTime) / 1000);
             console.log(count)
             timer(count);
             
         };
     });
	function cal(timer) {
	    let minutes = parseInt(timer / 60, 10);
	    let seconds = parseInt(timer % 60, 10);

	    minutes = minutes < 10 ? "0" + minutes : minutes;
	    seconds = seconds < 10 ? "0" + seconds : seconds;

	    document.querySelector('#timer').textContent = minutes + ":" + seconds;
	}
	function timer(count) {
		let timer = count;
	    cal(timer);
	    let intervalCount = setInterval(function() {
	        cal(timer);
	        if (--timer < 0) {
	        	$.ajax({
		            url: "http://localhost:8080/QuizWebSpring/api/finish_quiz",
		            method: "POST",
		            cache: false,
		            success: function (data, textStatus, jqXHR) {
		            	alert("Time out")
		            	location.replace("http://localhost:8080/QuizWebSpring/review_quiz");
		            }
		        });
	            clearInterval(intervalCount);
	        }else{
	        	document.getElementById("timer").innerHTML = minutes + ":" + seconds;
	        }
	    }, 1000);
	}
	const chooseAnswer=(input,count)=>{
		var questionId= document.getElementById("questionDoId"+count).value;
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
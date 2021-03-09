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
	<div class='container-fluid'>
		<div>
			<%@ include file="navbar/nav_home.jsp"%>
		</div>
		<div>
			<div style="background-color: #ffffe6" class="container">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Total Question</th>
							<th scope="col">Your Right</th>
							<th scope='col'>Score</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${resultQuiz.total }</td>
							<td>${resultQuiz.numRight}</td>
							<td>${resultQuiz.score}</td>
							<td><c:if test="${resultQuiz.score<5 }">
									<span class="label label-danger">Not pass</span>
								</c:if>
								<c:if test="${resultQuiz.score>=5 }">
									<span class="label label-success">Pass</span>
								</c:if>
								</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
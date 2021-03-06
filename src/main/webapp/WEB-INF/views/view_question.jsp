<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VIEW QUESTION</title>
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
			<%@ include file="navbar/nav_admin.jsp"%>
		</div>
		<div class='container'>
			<div>
				<%@ include file="search_admin.jsp"%>
			</div>

		</div>
		<div style="margin-left: 100px">
			<nav aria-label="Page navigation example"
				class="d-flex justify-content-center">

				<ul class="pagination">
					<c:forEach begin="${sessionScope.minPage}"
						end="${sessionScope.maxPage}" var="i">
						<c:url value="?page=${i}" var="url">
							<c:param name="subjectId" value="${param.subjectId}"></c:param>
							<c:param name="status" value="${param.status}"></c:param>
							<c:param name="content" value="${param.content}"></c:param>
						</c:url>
						<li class="page-item"><a class="page-link"
							href="${pageScope.url}">${i}</a></li>
					</c:forEach>
				</ul>
			</nav>
		</div>
		<div style="margin-left: 100px; margin-right: 100px">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Content</th>
						<th scope="col">Answer</th>
						<th scope="col">Right Answer</th>
						<th scope='col'>Status</th>
						<th scope='col'>Set status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listQuestion}" var="item">
						<tr>
							<td>${item.content }</td>
							<td>
								<div>
									<b>A:</b> ${item.answerA}
								</div>
								<div>
									<b>B:</b> ${item.answerB}
								</div>
								<div>
									<b>C:</b> ${item.answerC}
								</div>
								<div>
									<b>D:</b> ${item.answerD}
								</div>
							</td>
							<td>${item.rightAnswer}</td>
							<td><c:if test="${item.status ne 'DISABLE' }">
									<h4>
										<span class="label label-success">${item.status}</span>
									</h4>
								</c:if> <c:if test="${item.status eq 'DISABLE' }">
									<h4>
										<span class="label label-danger">${item.status}</span>
									</h4>
								</c:if></td>
							<td><c:if test="${item.status ne 'DISABLE' }">
									<button id="${item.id }" title="DISABLE"
										onclick="setStatusQuestion(this)" class='btn btn-danger'>DISABLE</button>
								</c:if> <c:if test="${item.status eq 'DISABLE' }">
									<button id="${item.id }" title="ACTIVE"
										onclick="setStatusQuestion(this)" class='btn btn-success'>ACTIVE</button>
								</c:if></td>
							<td>
								<button class="btn btn-warning">UPDATE</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		const setStatusQuestion=(button)=>{
			if(button.title=='DISABLE'){
				if(confirm("B???n c?? mu???n DISABLE")){
					$.ajax({
			            url: "http://localhost:8080/QuizWebSpring/api/update_status",
			            method: "POST",
			            cache: false,
			            data: {
			            	questionId:button.id,
			            	status:button.title,
			            },
			            success: function (data, textStatus, jqXHR) {
			            	window.location.reload(true); 
			            }
			        });
				}
				}else{
					$.ajax({
			            url: "http://localhost:8080/QuizWebSpring/api/update_status",
			            method: "POST",
			            cache: false,
			            data: {
			            	questionId:button.id,
			            	status:button.title,
			            },
			            success: function (data, textStatus, jqXHR) {
			            	window.location.reload(true); 
			            }
			        });
				}
			}
			
	</script>
</body>
</html>
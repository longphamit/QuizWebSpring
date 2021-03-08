<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						<c:param name="searchName" value="${param.searchName}"></c:param>
						<c:param name="searchFromDate" value="${param.searchFromDate}"></c:param>
						<c:param name="searchToDate" value="${param.searchToDate}"></c:param>
						<c:param name="searchCategory" value="${param.searchCategory}"></c:param>
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
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listQuestion}" var="item">
						<tr>
							<td>${item.content }</td>
							<td>${item.answerA }${item.answerB }${item.answerC }
								${item.answerD }</td>
							<td>${item.rightAnswer}</td>
							<td><c:if test="${item.status ne 'DISABLE' }">
									<button class='btn btn-danger'>Disable</button>
								</c:if> <c:if test="${item.status eq 'DISABLE' }">
									<button class='btn btn-success'>ACTIVE</button>
								</c:if></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
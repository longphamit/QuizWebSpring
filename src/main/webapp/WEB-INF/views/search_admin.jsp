<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div style="height: 50px;">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<form:form class="form-inline" action="#" method="GET"
				modelAttribute="searchQuestionDTO"
			>
				<div class="form-group">
					<form:input placeholder="content" path="content" />
				</div>
				<div class="form-group">
					<form:select path="status">
						<form:option value = "" label = "Trạng thái"/>
						<form:option value = "ACTIVE" label = "Active"/>
						<form:option value = "DISABLE" label = "Disable"/>
					</form:select>
				</div>
				<div class="form-group">
					<form:select path="subjectId">
						<form:option value="" label="Môn học"></form:option>
						<form:options items="${listSubject}" />
					</form:select>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-success">Search</button>
				</div>
			</form:form>
		</div>
		<div class="col-sm-3"></div>

	</div>
</div>
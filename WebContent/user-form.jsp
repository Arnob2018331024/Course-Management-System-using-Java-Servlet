<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>User Mangement Application</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
		crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
		style="background-color: blue">
			<div>
				<ul class="navbar-nav">
					<li>
						<a href="<%=request.getContextPath()%>/list"
						class="nav-link">Users</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user!=null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user==null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${user != null}">
							Edit User
						</c:if>
						<c:if test="${user == null}">
							Add New User
						</c:if>
					</h2>
				</caption>
				
				<c:if test="${user!=null}">
					<input type="hidden"  name="id" value="<c:out value='${user.id}' />"/>
				</c:if>
				<fieldset class="form-group">
					<label>User name</label><input type="text"
					value="<c:out value='${user.name}'/>" class="form-control"
					name="name" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>User Email</label><input type="text"
					value="<c:out value='${user.email}'/>" class="form-control"
					name="email">
				</fieldset>
				<fieldset class="form-group">
					<label>User Country</label><input type="text"
					value="<c:out value='${user.country}'/>" class="form-control"
					name="country">
				</fieldset>
				
				<button type="submit" class="btn btn-success">Save</button>
				
				</form>				
			</div>
		</div>
	</div>
</body>
</html>
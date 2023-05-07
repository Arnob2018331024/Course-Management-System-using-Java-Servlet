<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>UserMangementApplication</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
		crossorigin="anonymous">
	</head>
	<body>
		<header>
			<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color:blue">
				<div>
					<a href="http://www.xadmin.net" class="navbar-brand">User Management Application</a>
				</div>
				<ul class="navbar-nav">
					<li>
						<a href="<%= request.getContextPath()%>/list"
						class="nav-link">UserLst</a>
					</li>
				</ul>
			</nav>
		</header>
		<br>
		
		<div class="row">
			<div class="container">
				<h3 class="text-center">List of Users</h3>
				<hr>
				<div class="container text-left">
					<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
				</div>
				<br>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Email</th>
							<th>Country</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${listUser}">
						
							<tr>
								<td><c:out value="${user.id}"/></td>
								<td><c:out value="${user.name}"/></td>
								<td><c:out value="${user.email}"/></td>
								<td><c:out value="${user.country}"/></td>
								<td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
									&nbsp;&nbsp;&nbsp;&nbsp; <a
									href="delete?id=<c:out value='${user.id}' />">Delete</a>
								</td>
							</tr>
						
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>
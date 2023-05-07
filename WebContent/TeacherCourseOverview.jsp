<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/enrolledCourses.css">

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      href="https://getbootstrap.com/docs/5.3/assets/css/docs.css"
      rel="stylesheet"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script>
    <title>Courses</title>
</head>
<body>
    <div class="nav">
        <ul class="nav justify-content-center nav-underline">
          
          <li class="nav-item lists">
            <a class="nav-link text-black text-bolder" href="<%= request.getContextPath()%>/dashboard">MyCourses</a>
          </li>
          <li class="nav-item lists">
            <a class="nav-link text-black text-bolder" href="<%= request.getContextPath()%>/dashboard?logout=true">LogOut</a>
          </li>
        </ul>
      </div>
      <div class="heading">
        <h1>Enrolled Students</h1>
        <div class="line"></div>
      </div>
        <table class="table  table-striped">
            <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Student User Name</th>
                  <th scope="col">Student Reg.</th>
                  <th scope="col">Status</th>
                </tr>
              </thead>
              <tbody>
              <% int i=1; %>
              <c:forEach var="student" items="${students}">
				
                <tr>
                  <th scope="row"><%=i %></th>
                  <td><c:out value="${student.user_name}"/></td>
                  <td><c:out value="${student.reg}"/></td>
                  <td><c:out value="${student.status}"/></td>
                </tr>
                
              	<% i++; %>
               </c:forEach>
              </tbody>
          </table>
</body>
</html>
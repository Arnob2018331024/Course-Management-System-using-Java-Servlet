<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      href="https://getbootstrap.com/docs/5.3/assets/css/docs.css"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="styles/teacherPage.css" />
    <title>Teacher Page</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script>
  </head>
  <body class="p-3 m-0 border-0 bd-example">
    <!-- Example Code -->
    <div class="nav">
      <ul class="nav justify-content-center nav-underline">
        
        <!-- <li class="nav-item lists">
          <a class="nav-link text-black text-bolder" href="/client/public/enrolledCourses.html">Courses</a>
        </li> -->
        <li class="nav-item lists">
          <a class="nav-link text-black text-bolder" href="<%= request.getContextPath()%>/dashboard?logout=true">LogOut</a>
        </li>
      </ul>
    </div>
    <div class="heading">
      <h1>My Courses</h1>
    </div>

    <div class="container">
      
      
      <c:forEach var="course" items="${courses}">
		
      <div class="card" style="width: 18rem">
        <div class="card-body">
          <h5 class="card-title"><c:out value="${course.course_name}"/></h5>
          <div class="btn_1">
            <a href="<%= request.getContextPath()%>/dashboard?overview=true&course=${course.course_name}"><button class="button"><span>Overview</span></button></a>
          </div>
        </div>
      </div>					
	 </c:forEach>
      
    </div>

    
     <footer></footer>
   

    <!-- End Example Code -->
  </body>
</html>

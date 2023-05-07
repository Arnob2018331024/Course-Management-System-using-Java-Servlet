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
    <link rel="stylesheet" href="styles/studentPage.css" />
    <title>Student Page</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script>
  </head>
  <body class=" m-0 border-0 bd-example">
    <!-- Example Code -->
    <div class="nav">
      <ul class="nav justify-content-center nav-underline">
        
        <li class="nav-item lists">
          <a class="nav-link text-black text-bolder" href="<%= request.getContextPath()%>/dashboard?history=true">ErolledCourses</a>
        </li>
        <li class="nav-item lists">
          <a class="nav-link text-black text-bolder" href="<%= request.getContextPath()%>/dashboard?logout=true">LogOut</a>
        </li>
      </ul>
    </div>
    <div class="heading">
      <h1>Available Courses</h1>
    </div>

    <div class="container">
    <c:forEach var="course" items="${unRegCourses}">
		<form class="card" style="width: 18rem" method="POST" >
        <input class="card-title" type="text" name="title" value="${course.course_name}" style="display: none;">
        <input class="card-text" type="text" name="teacher" value="${course.teacher}" style="display: none;"  >
        <div class="card-body">
          <h5 class="card-title"><c:out value="${course.course_name}"/></h5>
          <p class="card-text"><c:out value="${course.teacher}"/></p>
          <div class="btn_1">
            <button class="button" type="submit"><span>Register </span></button>
          </div>
        </div>
      </form>						
	 </c:forEach>
     </div>
    
     <footer></footer>
   

    <!-- End Example Code -->
  </body>
</html>

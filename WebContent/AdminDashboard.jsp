<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Courses Page</title>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
<link rel="stylesheet" href="/client/styles/admin.css">

<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
  rel="stylesheet"
/>
<link
  href="https://getbootstrap.com/docs/5.3/assets/css/docs.css"
  rel="stylesheet"
/>
<link rel="stylesheet" href="styles/studentPage.css" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<div class="nav">
      <ul class="nav justify-content-center nav-underline">
        <li class="nav-item lists">
          <a
            class="nav-link text-black text-bolder"
            aria-current="page"
            href=""
            >DashBoard</a
          >
        </li>
        <li class="nav-item lists">
          <a class="nav-link text-black text-bolder" href="<%= request.getContextPath()%>/dashboard?logout=true">LogOut</a>
        </li>
      </ul>
    </div>
	
	<div class="container-fluid p-3">
		<div class="row">

			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Add Courses</p>
						
						<form method="post">
							<div class="mb-3">
								<label class="form-label">Course Name</label> <input type="text"
									required name="coursename" class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Teacher</label> <select name="teacher"
									required class="form-control">
									<c:forEach var="teacher" items="${teachers}">
									<option value="" selected disabled hidden>Choose here</option>
									<option value=${teacher.username}><c:out value="${teacher.username}"/></option>
									</c:forEach>
							
							</select>
							</div>

							<button type="submit" class="btn btn-primary">Submit</button>
						</form>
					</div>
				</div>
			</div>
          
		    <div class="col-md-8">
		       <div class="card paint-card">
		         <div class="card-body">
		           <p class="fs-3 text-center"> Course Details </p>
		             <table class="table">
		               <thead>
		                 <tr> 
					          <th scope="col">Course Name</th>
					          <th scope="col">Teacher Name</th>
					          <th scope="col">Action</th>
		                </tr>
		              </thead>
		              <tbody>
		               <c:forEach var="course" items="${courses}">
							
						<tr>
						  <form>
		                   <td><input name="course_name" class="border-0 p-0 m-0" name="course_name" value= "${course.course_name}" readonly></input></td>
		                   <td><select name="teacher" class="form-control form-control-sm border-0" >
		                   <option value=${course.teacher} selected disabled hidden><c:out value="${course.teacher}"/></option>
		                   	<c:forEach var="teacher" items="${teachers}">
							<option value=${teacher.username}><c:out value="${teacher.username}"/></option>
							</c:forEach>
		                   </select></td>
		                 
		                   <td>
		                   <button type="submit" class="btn btn-sm btn-primary">Update</button>
		                   </form>
		                   <form style="display: inline;">
		                   <input name="course"  value= "${course.course_name}" class="d-none">
		                   <button type="submit"  class="btn btn-sm btn-danger">Delete</button>
		                   </form>
		                   </td>
		                 	</tr>
		                 	
		                 
						
						</c:forEach>
		                 
		                 
		          
		             </tbody>
		          
		          </table>
		          </div>
		          </div>
		          
		      </div>

			
		</div>
	</div>
</body>
</html>
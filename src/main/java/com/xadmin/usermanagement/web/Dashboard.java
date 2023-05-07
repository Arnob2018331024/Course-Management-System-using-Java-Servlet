package com.xadmin.usermanagement.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xadmin.usermanagement.bean.Chache;
import com.xadmin.usermanagement.bean.Course;
import com.xadmin.usermanagement.bean.CourseStudent;
import com.xadmin.usermanagement.bean.Registration;
import com.xadmin.usermanagement.bean.RegistrationCourse;
import com.xadmin.usermanagement.bean.user;
import com.xadmin.usermanagement.dao.CourseDao;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseDao dao=new CourseDao();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  HttpSession session = request.getSession(true);
	      user user=(com.xadmin.usermanagement.bean.user) session.getAttribute("user");
	      
		if(request.getParameter("logout") != null)
			{
			Chache.user=null;
			session.setAttribute("user", null);
			user=null;
			}
		if(user==null)
		{
			String redirectUrl = "/UserManagement/login";
		    response.sendRedirect(redirectUrl);
		    return;
		}
		if(user.getType().equals("student")&&request.getParameter("history") != null)
		{

			System.out.print(user.toString());
			List<RegistrationCourse> courses=dao.getRegisteredCourses(user.getUsername());
			request.setAttribute("regCourses", courses);
			RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterHistory.jsp");
		    dispatcher.forward(request, response);
			
			return ;
		}
		if(user.getType().equals("student"))
			{
			List<Course> un_reg_courses= dao.getUnregisteredCourses(user.getUsername());
			System.out.println(un_reg_courses);
			request.setAttribute("unRegCourses", un_reg_courses);
			RequestDispatcher dispatcher = request.getRequestDispatcher("StudentDashboard.jsp");
		    dispatcher.forward(request, response);
			
			return ;
			}
		
		if(user.getType().equals("teacher")&&request.getParameter("overview") != null&&request.getParameter("course") != null)
		{
		String course=request.getParameter("course");
		System.out.println(course);
		System.out.println("---");
		List<CourseStudent> students= dao.getCourseStudents(course);
		System.out.print(students);
		request.setAttribute("students", students);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("TeacherCourseOverview.jsp");
	    dispatcher.forward(request, response);
		
		return ;
		}
		
		if(user.getType().equals("teacher"))
		{
		List<Course> courses= dao.getTeachersCourses(user.getUsername());
		System.out.print(courses);
		request.setAttribute("courses", courses);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("TeacherDashBoard.jsp");
	    dispatcher.forward(request, response);
		
		return ;
		}
		
		  
		if(user.getType().equals("admin")&&request.getParameter("course") != null)
			{
			dao.deleteCourse(request.getParameter("course"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("AdminDashboard.jsp");

			String redirectUrl = "/UserManagement/dashboard";
		    response.sendRedirect(redirectUrl);
		    return;
			}
		if(user.getType().equals("admin")&&request.getParameter("course_name") != null)
		{	
			String course_name=request.getParameter("course_name");
			String teacher=request.getParameter("teacher");
			System.out.print(course_name+"---"+teacher);
			if(teacher!=null) {
				try {
					dao.updateCourse(course_name, teacher);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String redirectUrl = "/UserManagement/dashboard";
		    response.sendRedirect(redirectUrl);
		    return;
		}
		if(user.getType().equals("admin"))
		{	
		List<user> teachers= dao.getAllTeachers();
		System.out.println(teachers);
		List<Course> courses=dao.getAllCourses();
		request.setAttribute("teachers", teachers);
		request.setAttribute("courses", courses);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminDashboard.jsp");
	    dispatcher.forward(request, response);
		
		return ;
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession(true);
	     user user=(com.xadmin.usermanagement.bean.user) session.getAttribute("user");
		if(user.getType().equals("student"))
		{String course_name=request.getParameter("title");
		String student=user.getUsername();
		Registration reg=new Registration(course_name,student);
		try {
			dao.register(reg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String redirectUrl = "/UserManagement/dashboard	";
	    response.sendRedirect(redirectUrl);
	    return;}
		
		if(user.getType().equals("admin"))
		{String course_name=request.getParameter("coursename");
		String teacher=request.getParameter("teacher");
		System.out.println(course_name+" "+teacher);
		Course course=new Course(course_name,teacher);
		try {
			dao.insertCourse(course);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String redirectUrl = "/UserManagement/dashboard	";
	    response.sendRedirect(redirectUrl);
	    return;
	    }
		
		doGet(request, response);
	}

}

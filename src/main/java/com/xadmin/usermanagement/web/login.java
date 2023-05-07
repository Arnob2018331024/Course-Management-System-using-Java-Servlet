package com.xadmin.usermanagement.web;
import com.xadmin.usermanagement.bean.Chache;
import com.xadmin.usermanagement.bean.user;
import com.xadmin.usermanagement.dao.CourseDao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
//@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CourseDao cd=new CourseDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	    dispatcher.forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		user user=cd.selectUser(name);
		if(user!=null&&user.getPassword().equals(password))
			{System.out.println("logged in...");
				Chache.user=user;
				  HttpSession session = request.getSession(true);
				  session.setAttribute("user", user);

				String redirectUrl = "/UserManagement/dashboard";
			    response.sendRedirect(redirectUrl);
			    return;
			}
		else
			System.out.println("wrong credntials...");
		
		doGet(request, response);
	}

}

package com.xadmin.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.usermanagement.bean.Course;
import com.xadmin.usermanagement.bean.CourseStudent;
import com.xadmin.usermanagement.bean.Registration;
import com.xadmin.usermanagement.bean.RegistrationCourse;
import com.xadmin.usermanagement.bean.user;

public class CourseDao {
	private String jdbcURL="jdbc:mysql://localhost:3306/coursedb?useSSL=false";
	private String jdbcUsername="root";
	/**
	 * 
	 */
	private String jdbcPassword="yourpassword";
	private String jdbcDriver="com.mysql.jdbc.Driver";
	
	private static final String SELECT_USER_BY_NAME="SELECT * FROM users WHERE user_name=?;";
	private static final String SELECT_ALL_USERS="select * from users;";
	private static final String DELETE_USERS_SQL="delete from users where user_name=?;";
	private static final String UPDATE_USERS_SQL="update users set password=?,type=? where user_name=?;";
	private static final String INSERT_USERS_SQL="INSERT INTO users VALUES(?,?,?);";
	private static final String GET_UNREG_COURSES_SQL="SELECT * FROM courses WHERE course_name NOT IN (SELECT course_name FROM registrations WHERE student = ? );";
	private static final String REGISTER_SQL="INSERT INTO registrations(course_name,student) VALUES(?,?);";
	private static final String GET_REG_COURSES_SQL="SELECT * FROM courses INNER JOIN registrations ON courses.course_name=registrations.course_name WHERE student=?;";
	private static final String GET_TEACHERS_COURSES_SQL="SELECT * FROM courses WHERE teacher=?;";
	private static final String GET_COURSE_STUDENTS_SQL="SELECT * FROM users JOIN registrations ON users.user_name = registrations.student WHERE course_name=?;";
	private static final String GET_ALL_TEACHERS_SQL="SELECT * FROM users WHERE user_type=\"teacher\";";
	private static final String GET_ALL_COURSES_SQL="SELECT * FROM courses;";
	private static final String INSERT_COURSES_SQL="INSERT INTO courses VALUES(?,?);";
	private static final String DELETE_COURSES_SQL="DELETE FROM courses WHERE course_name=?;";
	private static final String UPDATE_COURSES_SQL="UPDATE courses SET teacher=? WHERE course_name=?;";
	
	
	
	public CourseDao() {
	}
	protected Connection getConnection() {
		Connection connection=null;
		try {
			Class.forName(jdbcDriver);
			connection=DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
			System.out.println("------>"+connection.toString());
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	///Insert
	public void insertUser(user user) throws SQLException{
		System.out.println(INSERT_USERS_SQL);
		try (
			Connection connection=getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(INSERT_USERS_SQL);){
			preparedStatement.setString(1,user.getUsername());
			preparedStatement.setString(2,user.getPassword());
			preparedStatement.setString(3,user.getType());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			printSQLException(e);
		}
	}
	///Select By id
	public user selectUser(String name) {
		user user=null;
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(SELECT_USER_BY_NAME);){
			preparedStatement.setString(1,name);
			System.out.println(preparedStatement);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				String nam=rs.getString("user_name");
				String password=rs.getString("password");
				String type=rs.getString("user_type");
				user=new user(nam,password,type);
			}
		}catch(SQLException e) {
				printSQLException(e);
			}
			return user;
		
	}
	///Select All
	public List<user> selectAllUsers(){
		List<user> users=new ArrayList<>();
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement =connection.prepareStatement(SELECT_ALL_USERS);){
			System.out.println(preparedStatement);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				String name=rs.getString("user_name");
				String password=rs.getString("password");
				String type=rs.getString("user_type");
				users.add(new user(name,password,type));
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
		return users;
	} 
	///Update user
	public boolean updateUser(user user) throws SQLException{
		boolean rowUpdated=false;
		try(Connection connection=getConnection();
				PreparedStatement statement =connection.prepareStatement(UPDATE_USERS_SQL);){
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getType());
			statement.setString(3, user.getUsername());
			System.out.println("updated user:"+statement);
			rowUpdated=statement.executeUpdate()>0;
			
		}catch(SQLException e) {
			printSQLException(e);
		}
		return rowUpdated;
	}
	///Delete user
	public boolean deleteUser(String name) {
		boolean rowDeleted=false;
		try(Connection connection=getConnection();
				PreparedStatement statement=connection.prepareStatement(DELETE_USERS_SQL);){
			statement.setString(1, name);
			rowDeleted=statement.execute();
		}catch(SQLException e) {
			printSQLException(e);
		}
		return rowDeleted;
				
	}
	
	
	public List<Course> getUnregisteredCourses(String user_name){
		List<Course> courses=new ArrayList<>();
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement =connection.prepareStatement(GET_UNREG_COURSES_SQL);){
			preparedStatement.setString(1, user_name);
			System.out.println(preparedStatement);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				String name=rs.getString("course_name");
				String teacher=rs.getString("teacher");
				courses.add(new Course(name,teacher));
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
		return courses;
	}
	
	public List<RegistrationCourse> getRegisteredCourses(String user_name){
		List<RegistrationCourse> courses=new ArrayList<>();
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement =connection.prepareStatement(GET_REG_COURSES_SQL);){
			preparedStatement.setString(1, user_name);
			System.out.println(preparedStatement);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				String course_name=rs.getString("course_name");
				String teacher=rs.getString("teacher");
				String student=rs.getString("student");
				String status=rs.getString("status");
				courses.add(new RegistrationCourse(course_name,student,status,teacher));
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
		return courses;
	}
	
	
	public void register(Registration reg) throws SQLException{
		System.out.println(REGISTER_SQL);
		try (
			Connection connection=getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(REGISTER_SQL);){
			preparedStatement.setString(1,reg.getCourse_name());
			preparedStatement.setString(2,reg.getStudent());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			printSQLException(e);
		}
	}
	
	public List<Course> getTeachersCourses(String user_name){
		List<Course> courses=new ArrayList<>();
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement =connection.prepareStatement(GET_TEACHERS_COURSES_SQL);){
			preparedStatement.setString(1, user_name);
			System.out.println(preparedStatement);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				String name=rs.getString("course_name");
				String teacher=rs.getString("teacher");
				courses.add(new Course(name,teacher));
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
		return courses;
	}
	
	public List<CourseStudent> getCourseStudents(String course){
		List<CourseStudent> students=new ArrayList<>();
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement =connection.prepareStatement(GET_COURSE_STUDENTS_SQL);){
			preparedStatement.setString(1, course);
			System.out.println(preparedStatement);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				String name=rs.getString("student");
				String reg=rs.getString("reg");
				String status=rs.getString("status");
				students.add(new CourseStudent(name,reg,status));
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
		return students;
	}
	
	public List<user> getAllTeachers(){
		List<user> users=new ArrayList<>();
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement =connection.prepareStatement(GET_ALL_TEACHERS_SQL);){
			System.out.println(preparedStatement);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				String name=rs.getString("user_name");
				String password=rs.getString("password");
				String type=rs.getString("user_type");
				users.add(new user(name,password,type));
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
		return users;
	}
	
	public List<Course> getAllCourses(){
		List<Course> courses=new ArrayList<>();
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement =connection.prepareStatement(GET_ALL_COURSES_SQL);){
			System.out.println(preparedStatement);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				String name=rs.getString("course_name");
				String teacher=rs.getString("teacher");
				courses.add(new Course(name,teacher));
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
		return courses;
	}
	public void insertCourse(Course course) throws SQLException{
		System.out.println(INSERT_USERS_SQL);
		try (
			Connection connection=getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(INSERT_COURSES_SQL);){
			preparedStatement.setString(1,course.getCourse_name());
			preparedStatement.setString(2,course.getTeacher());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			printSQLException(e);
		}
	}
	
	public boolean deleteCourse(String name) {
		boolean rowDeleted=false;
		try(Connection connection=getConnection();
				PreparedStatement statement=connection.prepareStatement(DELETE_COURSES_SQL);){
			statement.setString(1, name);
			System.out.println(statement);
			rowDeleted=statement.execute();
		}catch(SQLException e) {
			printSQLException(e);
		}
		return rowDeleted;
				
	}
	public boolean updateCourse(String course_name,String teacher) throws SQLException{
		boolean rowUpdated=false;
		try(Connection connection=getConnection();
				PreparedStatement statement =connection.prepareStatement(UPDATE_COURSES_SQL);){
			statement.setString(1, teacher);
			statement.setString(2, course_name);
			System.out.println("updated user:"+statement);
			rowUpdated=statement.executeUpdate()>0;
			
		}catch(SQLException e) {
			printSQLException(e);
		}
		return rowUpdated;
	}
	
	
	
	private void printSQLException(SQLException ex) {
		for(Throwable e : ex) {
			if(e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: "+((SQLException) e).getSQLState());
				System.err.println("Error Code: "+((SQLException) e).getErrorCode());
				System.err.println("Message: "+e.getMessage());
				Throwable t= ex.getCause();
				while(t!=null) {
					System.out.println("Cause: "+t);
					t=t.getCause();
				}
			}
		}
		// TODO Auto-generated method stub
		
	}
}

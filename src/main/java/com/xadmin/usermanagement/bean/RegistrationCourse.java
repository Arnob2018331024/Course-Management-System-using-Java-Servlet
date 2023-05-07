package com.xadmin.usermanagement.bean;

public class RegistrationCourse {
	 private String course_name;
	 private String student;
	 private String status;
	 private String teacher;
	public RegistrationCourse(String course_name, String student, String status, String teacher) {
		super();
		this.course_name = course_name;
		this.student = student;
		this.status = status;
		this.teacher = teacher;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	

}

package com.xadmin.usermanagement.bean;

public class Registration {
 private String course_name;
 private String student;
 private String status;
public Registration(String course_name, String student) {
	super();
	this.course_name = course_name;
	this.student = student;
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
 
}

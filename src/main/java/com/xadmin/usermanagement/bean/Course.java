package com.xadmin.usermanagement.bean;

public class Course {
private String course_name;
private String teacher;
public String getCourse_name() {
	return course_name;
}
public void setCourse_name(String course_name) {
	this.course_name = course_name;
}
public String getTeacher() {
	return teacher;
}
public void setTeacher(String teacher) {
	this.teacher = teacher;
}
public Course(String course_name, String teacher) {
	super();
	this.course_name = course_name;
	this.teacher = teacher;
}
}

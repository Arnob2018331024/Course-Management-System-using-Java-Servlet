package com.xadmin.usermanagement.bean;

public class CourseStudent {
private String user_name;
private String reg;
private String status;
public CourseStudent(String user_name, String reg, String status) {
	super();
	this.user_name = user_name;
	this.reg = reg;
	this.status = status;
}
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public String getReg() {
	return reg;
}
public void setReg(String reg) {
	this.reg = reg;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

}

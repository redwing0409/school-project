package com.employee.model;

public class EmployeeVO implements java.io.Serializable{
	
	private String emp_no;
	private String emp_account;
	private String emp_psw;
	private String emp_name;
	private Integer emp_sex;
	private String emp_mobile;

	
	
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getEmp_account() {
		return emp_account;
	}
	public void setEmp_account(String emp_account) {
		this.emp_account = emp_account;
	}
	public String getEmp_psw() {
		return emp_psw;
	}
	public void setEmp_psw(String emp_psw) {
		this.emp_psw = emp_psw;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public Integer getEmp_sex() {
		return emp_sex;
	}
	public void setEmp_sex(Integer emp_sex) {
		this.emp_sex = emp_sex;
	}
	public String getEmp_mobile() {
		return emp_mobile;
	}
	public void setEmp_mobile(String emp_mobile) {
		this.emp_mobile = emp_mobile;
	}
	
	
}
package com.employee.model;
import java.util.List;

public class EmployeeService {
	
	private EmployeeDAO_interface dao;

	public EmployeeService() {
		dao = new EmployeeDAO();
	}

	public EmployeeVO addEmp(String emp_account, String emp_psw, String emp_name,
			Integer emp_sex,String emp_mobile) {

		EmployeeVO employeeVO = new EmployeeVO();

		employeeVO.setEmp_account(emp_account);
		employeeVO.setEmp_psw(emp_psw);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_sex(emp_sex);
		employeeVO.setEmp_mobile(emp_mobile);
		dao.insert(employeeVO);
		return employeeVO;
	}
	
	
	public EmployeeVO updateEmp(String emp_no,String emp_account, String emp_psw, String emp_name,
			Integer emp_sex,String emp_mobile) {

		EmployeeVO employeeVO = new EmployeeVO();

		employeeVO.setEmp_no(emp_no);
		employeeVO.setEmp_account(emp_account);
		employeeVO.setEmp_psw(emp_psw);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_sex(emp_sex);
		employeeVO.setEmp_mobile(emp_mobile);
		dao.update(employeeVO);
		return employeeVO;
	}

	public void deleteEmp(String emp_no) {
		dao.delete(emp_no);
	}

	public EmployeeVO getOneEmp(String emp_no) {
		return dao.findByPrimaryKey(emp_no);
	}

	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}
	
	

}

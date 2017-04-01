package com.employee.controller;
import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.employee.model.*;
import com.sup.model.SupService;
import com.sup.model.SupVO;
@WebServlet("/employee/employee.do")

public class EmployeeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("emp_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String emp_no = null;
				try {
					emp_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmp(emp_no);
				if (employeeVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back_end/employee/listOneEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/employee/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}  //	if ("getOne_For_Display".equals(action))
		
		
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String emp_no = req.getParameter("emp_no");
				
				/***************************2.�}�l�d�߸��****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmp(emp_no);
				System.out.println(employeeVO==null);				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("employeeVO", employeeVO);         // ��Ʈw���X��empVO����,�s�Jreq
				
				String url = "/back_end/employee/update_employee_input.jsp";
				
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/employee/listAllEmployee.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String emp_no = req.getParameter("emp_no").trim();
				String emp_account = req.getParameter("emp_account").trim();
				String emp_psw = req.getParameter("emp_psw").trim();				
				
				System.out.println(emp_no);
				
				String emp_name = null;
				try {
					emp_name = req.getParameter("emp_name").trim();
				} catch (IllegalArgumentException e) {
					emp_name="Vito";
					errorMsgs.add("�п�JNAME!");
				}

				Integer emp_sex = null;
				try {
					emp_sex = new Integer(req.getParameter("emp_sex").trim());
				} catch (NumberFormatException e) {
					emp_sex = 0;
					errorMsgs.add("�~���ж�sex.");
				}

				String emp_mobile = null;
				try {
					emp_mobile = new String(req.getParameter("emp_mobile").trim());
				} catch (NumberFormatException e) {
					emp_mobile = "0912345678";
					errorMsgs.add("�����ж�mobil.");
				}

//				Integer deptno = new Integer(req.getParameter("deptno").trim());

				EmployeeVO employeeVO = new EmployeeVO();
//				System.out.println("AAAAAAA");
				employeeVO.setEmp_no(emp_no);
				employeeVO.setEmp_account(emp_account);
				employeeVO.setEmp_psw(emp_psw);
				employeeVO.setEmp_name(emp_name);
				employeeVO.setEmp_sex(emp_sex);
				employeeVO.setEmp_mobile(emp_mobile);
//				empVO.setDeptno(deptno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", employeeVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/employee/update_employee_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
					
				}
				
				/***************************2.�}�l�ק���*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				
				employeeVO = employeeSvc.updateEmp(emp_no,emp_account,emp_psw,emp_name,
					emp_sex,emp_mobile);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
		System.out.println(emp_no);
				req.setAttribute("employeeVO", employeeVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				
				String url = "/back_end/employee/employee_firstpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/employee/employee_firstpage.jsp");
				failureView.forward(req, res);
			}
		}  //end of if ("update".equals(action)) 
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String emp_no = req.getParameter("emp_no");
				
				/***************************2.�}�l�R�����***************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeSvc.deleteEmp(emp_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back_end/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/employee/listAllEmployee.jsp");
				failureView.forward(req, res);
			}
		}//if ("delete".equals(action))
		
		 if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
					EmployeeService empSvc = new EmployeeService();
					List <EmployeeVO> list1 = empSvc.getAll();
				    String emp_account = req.getParameter("emp_account").trim();
				    for (EmployeeVO m:list1){
				    	if(emp_account.equals(m.getEmp_account())){
				    		errorMsgs.add("���b���w�s�b");
				    	}
				    	else{}
				    }
//					String emp_psw = req.getParameter("emp_psw").trim();
					    String emp_psw = String.valueOf((int) (Math.random()*1000));
					    System.out.println(emp_psw);
				
					
					String emp_name = null;
					try {
						emp_name = req.getParameter("emp_name").trim();
					} catch (NumberFormatException e) {
						emp_name = "�d22";
						errorMsgs.add("�ж�m�W.");
					}
					
					Integer emp_sex = null;
					try {
						emp_sex = new Integer(req.getParameter("emp_sex").trim());
					} catch (NumberFormatException e) {
						emp_sex = 1;
						errorMsgs.add("�ж�ʧO.");
					}
					
					String emp_mobile = req.getParameter("emp_mobile").trim();

					EmployeeVO employeeVO = new EmployeeVO();
					employeeVO.setEmp_account(emp_account);
					employeeVO.setEmp_psw(emp_psw);
					employeeVO.setEmp_name(emp_name);
					employeeVO.setEmp_sex(emp_sex);
					employeeVO.setEmp_mobile(emp_mobile);
//					empVO.setDeptno(deptno);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("employeeVO", employeeVO); // �t����J�榡���~��empVO����,�]�s�Jreq
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/employee/addEmployee.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.�}�l�s�W���***************************************/
					EmployeeService employeeSvc = new EmployeeService();
					employeeVO = employeeSvc.addEmp(emp_account, emp_psw, emp_name, emp_sex, emp_mobile);
					
					/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
					Send se = new Send();
				 	String[] tel ={emp_mobile};
				 	String message = "�z���K�X��:"  + emp_psw + "�A�w��z���[�J";
				 	se.sendMessage(tel , message);
				 	
					String url = "/back_end/employee/listAllEmployee.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************��L�i�઺���~�B�z**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/employee/addEmployee.jsp");
					failureView.forward(req, res);
				}
			}
	
		 if ("logout".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();

				req.setAttribute("errorMsgs", errorMsgs);

				try {
					HttpSession session = req.getSession();

					session.removeAttribute("empAccount");

					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emplogin.jsp");
					// RequestDispatcher failureView =
					// req.getRequestDispatcher("/supplier-end/sup/sup_firstpage.jsp");
					failureView.forward(req, res);

//					Enumeration<String> Enumeration = session.getAttributeNames();
//					while (Enumeration.hasMoreElements()) {
//						String name = (String) Enumeration.nextElement();
//						System.out.println(name + ":" + session.getAttribute(name));
//					}
				} catch (Exception e) {

				}

			}
	
	}
}

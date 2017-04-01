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
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("emp_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String emp_no = null;
				try {
					emp_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmp(emp_no);
				if (employeeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/employee/listOneEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/employee/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}  //	if ("getOne_For_Display".equals(action))
		
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.接收請求參數****************************************/
				String emp_no = req.getParameter("emp_no");
				
				/***************************2.開始查詢資料****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmp(emp_no);
				System.out.println(employeeVO==null);				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("employeeVO", employeeVO);         // 資料庫取出的empVO物件,存入req
				
				String url = "/back_end/employee/update_employee_input.jsp";
				
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/employee/listAllEmployee.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String emp_no = req.getParameter("emp_no").trim();
				String emp_account = req.getParameter("emp_account").trim();
				String emp_psw = req.getParameter("emp_psw").trim();				
				
				System.out.println(emp_no);
				
				String emp_name = null;
				try {
					emp_name = req.getParameter("emp_name").trim();
				} catch (IllegalArgumentException e) {
					emp_name="Vito";
					errorMsgs.add("請輸入NAME!");
				}

				Integer emp_sex = null;
				try {
					emp_sex = new Integer(req.getParameter("emp_sex").trim());
				} catch (NumberFormatException e) {
					emp_sex = 0;
					errorMsgs.add("薪水請填sex.");
				}

				String emp_mobile = null;
				try {
					emp_mobile = new String(req.getParameter("emp_mobile").trim());
				} catch (NumberFormatException e) {
					emp_mobile = "0912345678";
					errorMsgs.add("獎金請填mobil.");
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
					req.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/employee/update_employee_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
					
				}
				
				/***************************2.開始修改資料*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				
				employeeVO = employeeSvc.updateEmp(emp_no,emp_account,emp_psw,emp_name,
					emp_sex,emp_mobile);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
		System.out.println(emp_no);
				req.setAttribute("employeeVO", employeeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				
				String url = "/back_end/employee/employee_firstpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/employee/employee_firstpage.jsp");
				failureView.forward(req, res);
			}
		}  //end of if ("update".equals(action)) 
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String emp_no = req.getParameter("emp_no");
				
				/***************************2.開始刪除資料***************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeSvc.deleteEmp(emp_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/employee/listAllEmployee.jsp");
				failureView.forward(req, res);
			}
		}//if ("delete".equals(action))
		
		 if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					EmployeeService empSvc = new EmployeeService();
					List <EmployeeVO> list1 = empSvc.getAll();
				    String emp_account = req.getParameter("emp_account").trim();
				    for (EmployeeVO m:list1){
				    	if(emp_account.equals(m.getEmp_account())){
				    		errorMsgs.add("此帳號已存在");
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
						emp_name = "吳22";
						errorMsgs.add("請填姓名.");
					}
					
					Integer emp_sex = null;
					try {
						emp_sex = new Integer(req.getParameter("emp_sex").trim());
					} catch (NumberFormatException e) {
						emp_sex = 1;
						errorMsgs.add("請填性別.");
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
						req.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/employee/addEmployee.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					EmployeeService employeeSvc = new EmployeeService();
					employeeVO = employeeSvc.addEmp(emp_account, emp_psw, emp_name, emp_sex, emp_mobile);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					Send se = new Send();
				 	String[] tel ={emp_mobile};
				 	String message = "您的密碼為:"  + emp_psw + "，歡迎您的加入";
				 	se.sendMessage(tel , message);
				 	
					String url = "/back_end/employee/listAllEmployee.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
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

package login;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.employee.model.*;

import javax.servlet.annotation.WebServlet;

@WebServlet("/login/emploginhandler")
public class EmpLoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

   //�i�ˬd�ϥΪ̿�J���b��(account) �K�X(password)�O�_���ġj
   //�i��ڤW���ܸ�Ʈw�j�M���j
  protected EmployeeVO allowEmployee(String account, String password) {
	  EmployeeService EmployeeSvc = new EmployeeService();
	  List<EmployeeVO> vo = EmployeeSvc.getAll();
	  for(EmployeeVO EmployeeVO : vo){
		  String emp_account = EmployeeVO.getEmp_account();
		  String emp_psw = EmployeeVO.getEmp_psw();
		  if (emp_account.equals(account) && emp_psw.equals(password)){
		      return EmployeeVO;
		  } else {continue;}
	  }
	  return null;
  }
  
  public void doPost(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
    req.setCharacterEncoding("Big5");
    res.setContentType("text/html; charset=Big5");
    PrintWriter out = res.getWriter();

    // �i���o�ϥΪ� �b��(account) �K�X(password)�j
    String account = req.getParameter("account");
    String password = req.getParameter("password");
 
    // �i�ˬd�ӱb�� , �K�X�O�_���ġj
    if (allowEmployee(account, password) == null) {          //�i�b�� , �K�X�L�Įɡj
    	req.setAttribute("errorMsgs", "�b���K�X���~");
		RequestDispatcher failureView = req
				.getRequestDispatcher("/back_end/emplogin.jsp");
		failureView.forward(req, res);
		return;//�{�����_
    }else {                                       //�i�b�� , �K�X���Į�, �~���H�U�u�@�j
    	HttpSession session = req.getSession();
//      session.setAttribute("account", account);   //*�u�@1: �~�bsession�����w�g�n�J�L������
      session.setAttribute("empAccount", allowEmployee(account, password));
      
       try {                                                        
    	 String location = (String) session.getAttribute("location");
         if (location != null) {
           session.removeAttribute("location");   //*�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)
           res.sendRedirect(location);            
           return;
         }else
         {
             res.sendRedirect(req.getContextPath()+"/back_end/employee/employee_firstpage.jsp");  //*�u�@3: (-->�p�L�ӷ�����:�h���ɦ�login_success.jsp)
         }
       }catch (Exception ignored) { 
    	   ignored.printStackTrace();
       }

    }
  }
}
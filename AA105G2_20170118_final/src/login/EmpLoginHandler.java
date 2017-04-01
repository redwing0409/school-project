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

   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
   //【實際上應至資料庫搜尋比對】
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

    // 【取得使用者 帳號(account) 密碼(password)】
    String account = req.getParameter("account");
    String password = req.getParameter("password");
 
    // 【檢查該帳號 , 密碼是否有效】
    if (allowEmployee(account, password) == null) {          //【帳號 , 密碼無效時】
    	req.setAttribute("errorMsgs", "帳號密碼錯誤");
		RequestDispatcher failureView = req
				.getRequestDispatcher("/back_end/emplogin.jsp");
		failureView.forward(req, res);
		return;//程式中斷
    }else {                                       //【帳號 , 密碼有效時, 才做以下工作】
    	HttpSession session = req.getSession();
//      session.setAttribute("account", account);   //*工作1: 才在session內做已經登入過的標識
      session.setAttribute("empAccount", allowEmployee(account, password));
      
       try {                                                        
    	 String location = (String) session.getAttribute("location");
         if (location != null) {
           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
           res.sendRedirect(location);            
           return;
         }else
         {
             res.sendRedirect(req.getContextPath()+"/back_end/employee/employee_firstpage.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
         }
       }catch (Exception ignored) { 
    	   ignored.printStackTrace();
       }

    }
  }
}
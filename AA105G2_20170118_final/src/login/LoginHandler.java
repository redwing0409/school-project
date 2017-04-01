package login;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.*;

import javax.servlet.annotation.WebServlet;

@WebServlet("/login/loginhandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
   //【實際上應至資料庫搜尋比對】
  protected MemberVO allowUser(String account, String password) {
	  MemberService memberSvc = new MemberService();
	  List<MemberVO> vo = memberSvc.getAll();
	  for(MemberVO memberVO : vo){
		  String member_acc = memberVO.getMember_acc();
		  String member_pw = memberVO.getMember_pw();
		  if (member_acc.equals(account) && member_pw.equals(password)){
		      return memberVO;
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
    if (allowUser(account, password) == null) {          //【帳號 , 密碼無效時】
    	req.setAttribute("errorMsgs", "帳號密碼錯誤");
		RequestDispatcher failureView = req
				.getRequestDispatcher("/login.jsp");
		failureView.forward(req, res);
		return;//程式中斷
    }else {                                       //【帳號 , 密碼有效時, 才做以下工作】
      HttpSession session = req.getSession();
//      session.setAttribute("account", account);   //*工作1: 才在session內做已經登入過的標識
      session.setAttribute("account", allowUser(account, password));
      session.setAttribute("addViews", new ArrayList<String>());
      
      
       try {                                                        
         String location = (String) session.getAttribute("location");
         System.out.println(location + "  -----LoginHandler");
         if (location != null) {
           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
           res.sendRedirect(location);            
           return;
         }
       }catch (Exception ignored) { }

      res.sendRedirect(req.getContextPath()+"/front_end/frontIndex.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
    }
  }
}
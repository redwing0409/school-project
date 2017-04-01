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

   //�i�ˬd�ϥΪ̿�J���b��(account) �K�X(password)�O�_���ġj
   //�i��ڤW���ܸ�Ʈw�j�M���j
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

    // �i���o�ϥΪ� �b��(account) �K�X(password)�j
    String account = req.getParameter("account");
    String password = req.getParameter("password");
    // �i�ˬd�ӱb�� , �K�X�O�_���ġj
    if (allowUser(account, password) == null) {          //�i�b�� , �K�X�L�Įɡj
    	req.setAttribute("errorMsgs", "�b���K�X���~");
		RequestDispatcher failureView = req
				.getRequestDispatcher("/login.jsp");
		failureView.forward(req, res);
		return;//�{�����_
    }else {                                       //�i�b�� , �K�X���Į�, �~���H�U�u�@�j
      HttpSession session = req.getSession();
//      session.setAttribute("account", account);   //*�u�@1: �~�bsession�����w�g�n�J�L������
      session.setAttribute("account", allowUser(account, password));
      session.setAttribute("addViews", new ArrayList<String>());
      
      
       try {                                                        
         String location = (String) session.getAttribute("location");
         System.out.println(location + "  -----LoginHandler");
         if (location != null) {
           session.removeAttribute("location");   //*�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)
           res.sendRedirect(location);            
           return;
         }
       }catch (Exception ignored) { }

      res.sendRedirect(req.getContextPath()+"/front_end/frontIndex.jsp");  //*�u�@3: (-->�p�L�ӷ�����:�h���ɦ�login_success.jsp)
    }
  }
}
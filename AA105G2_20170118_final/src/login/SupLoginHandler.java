package login;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.sup.model.*;

import javax.servlet.annotation.WebServlet;

@WebServlet("/login/suploginhandler")
public class SupLoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	// 【實際上應至資料庫搜尋比對】
	protected SupVO allowSup(String account, String password) {
		SupService SupSvc = new SupService();
		List<SupVO> vo = SupSvc.getAll();
		for (SupVO SupVO : vo) {
			String sup_acct = SupVO.getSup_acct();
			String sup_pwd = SupVO.getSup_pwd();
			if (sup_acct.equals(account) && sup_pwd.equals(password)) {
				return SupVO;
			}
		}
		return null;
	}
	// 登出
	// public void doLogout(HttpServletRequest request, HttpServletResponse
	// response)
	// throws IOException, ServletException {
	// HttpSession session = request.getSession();
	//
	// session.removeAttribute("supAccount"); // 把身分驗證旗號清掉
	// session.invalidate(); // 清除 session 內所有 attributes 與物件的繫結關係
	// doPost(request, response); // 再轉往登入畫面
	// }

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("Big5");
		res.setContentType("text/html; charset=Big5");
		PrintWriter out = res.getWriter();

		// 【取得使用者 帳號(account) 密碼(password)】
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		//String note = req.getParameter("sup_note");
//System.out.println(note);
		// 【檢查該帳號 , 密碼是否有效】
		if (allowSup(account, password) == null) { // 【帳號 , 密碼無效時】
			req.setAttribute("errorMsgs", "帳號密碼錯誤");
			RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/suplogin.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}
		else if(allowSup(account, password).getSup_note().equals("0")){
			req.setAttribute("errorMsgs", "尚未完成驗證");
		   RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/suplogin.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		} 
		else { // 【帳號 , 密碼有效時, 才做以下工作】
			HttpSession session = req.getSession();
			// session.setAttribute("account", account); //*工作1:
			// 才在session內做已經登入過的標識
			session.setAttribute("supAccount", allowSup(account, password));

			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location"); // *工作2: 看看有無來源網頁
															// (-->如有來源網頁:則重導至來源網頁)
					res.sendRedirect(location);
					return;
				} else {
					res.sendRedirect(req.getContextPath() + "/supplier_end/sup_firstpage.jsp"); // *工作3:
																									// (-->如無來源網頁:則重導至login_success.jsp)
				}
			} catch (Exception ignored) {
				ignored.printStackTrace();
			}

		}
	}

}
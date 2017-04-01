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

	// �i�ˬd�ϥΪ̿�J���b��(account) �K�X(password)�O�_���ġj
	// �i��ڤW���ܸ�Ʈw�j�M���j
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
	// �n�X
	// public void doLogout(HttpServletRequest request, HttpServletResponse
	// response)
	// throws IOException, ServletException {
	// HttpSession session = request.getSession();
	//
	// session.removeAttribute("supAccount"); // �⨭�����ҺX���M��
	// session.invalidate(); // �M�� session ���Ҧ� attributes �P����ô�����Y
	// doPost(request, response); // �A�੹�n�J�e��
	// }

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("Big5");
		res.setContentType("text/html; charset=Big5");
		PrintWriter out = res.getWriter();

		// �i���o�ϥΪ� �b��(account) �K�X(password)�j
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		//String note = req.getParameter("sup_note");
//System.out.println(note);
		// �i�ˬd�ӱb�� , �K�X�O�_���ġj
		if (allowSup(account, password) == null) { // �i�b�� , �K�X�L�Įɡj
			req.setAttribute("errorMsgs", "�b���K�X���~");
			RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/suplogin.jsp");
			failureView.forward(req, res);
			return;// �{�����_
		}
		else if(allowSup(account, password).getSup_note().equals("0")){
			req.setAttribute("errorMsgs", "�|����������");
		   RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/suplogin.jsp");
			failureView.forward(req, res);
			return;// �{�����_
		} 
		else { // �i�b�� , �K�X���Į�, �~���H�U�u�@�j
			HttpSession session = req.getSession();
			// session.setAttribute("account", account); //*�u�@1:
			// �~�bsession�����w�g�n�J�L������
			session.setAttribute("supAccount", allowSup(account, password));

			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location"); // *�u�@2: �ݬݦ��L�ӷ�����
															// (-->�p���ӷ�����:�h���ɦܨӷ�����)
					res.sendRedirect(location);
					return;
				} else {
					res.sendRedirect(req.getContextPath() + "/supplier_end/sup_firstpage.jsp"); // *�u�@3:
																									// (-->�p�L�ӷ�����:�h���ɦ�login_success.jsp)
				}
			} catch (Exception ignored) {
				ignored.printStackTrace();
			}

		}
	}

}
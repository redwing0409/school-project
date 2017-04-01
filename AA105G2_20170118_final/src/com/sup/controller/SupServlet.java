package com.sup.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.place.model.PlaceVO;
import com.sup.model.*;
import com.adv.model.*;

public class SupServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				String str = req.getParameter("sup_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�t�ӽs��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/sup/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String sup_no = null;
				try {
					sup_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�t�ӽs���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/sup/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				SupService SupSvc = new SupService();
				SupVO SupVO = SupSvc.getOneSup(sup_no);
				if (SupVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/sup/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 *************/
				req.setAttribute("SupVO", SupVO); // ��Ʈw���X��SupVO����,�s�Jreq
				String url = "/supplier_end/sup/listOneSup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																				// listOneSup.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/sup/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllSup.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String num = req.getParameter("number");
			// try {
			/*************************** 1.�����ШD�Ѽ� ****************************************/
			String sup_no = new String(req.getParameter("sup_no"));

			/*************************** 2.�}�l�d�߸�� ****************************************/
			SupService SupSvc = new SupService();
			SupVO SupVO = SupSvc.getOneSup(sup_no);

			/***************************
			 * 3.�d�ߧ���,�ǳ����(Send the Success view)
			 ************/
			if(num.equals("1")) {
			req.setAttribute("SupVO1", SupVO); // ��Ʈw���X��SupVO����,�s�Jreq
			String url = "/supplier_end/sup_firstpage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
			successView.forward(req, res);
			}
			
			if(num.equals("2")) {
				req.setAttribute("SupVO2", SupVO); // ��Ʈw���X��SupVO����,�s�Jreq
				String url = "/supplier_end/sup/supplierhome.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
				successView.forward(req, res);
			}
	
			/*************************** ��L�i�઺���~�B�z **********************************/
			// } catch (Exception e) {
			// errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/front-end/sup/listAllSup.jsp");
			// failureView.forward(req, res);
			// }
		}

		if ("update".equals(action)) { // �Ӧ�update_sup_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String num = req.getParameter("number");
						
//			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				String sup_no = new String(req.getParameter("sup_no").trim());
				String sup_name = req.getParameter("sup_name").trim();
				String sup_acct = req.getParameter("sup_acct").trim();
				
				String sup_pwd = req.getParameter("sup_pwd").trim();
				if (sup_pwd == null || (sup_pwd.trim()).length() == 0) {
					errorMsgs.add("�п�J�K�X");
				}
		
				Integer sup_id = null;
				try {
					sup_id = new Integer(req.getParameter("sup_id").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�п�J�Τ@�s��");
				}

				String sup_con = req.getParameter("sup_con").trim();
				if (sup_con == null || (sup_con.trim()).length() == 0) {
					errorMsgs.add("�ж�J�pô���f");
				}
				
				Integer sup_telcode = null;
				try {
					sup_telcode = new Integer(req.getParameter("sup_telcode").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�п�J�pô�q�ܰϽX");
				}
				
				Integer sup_tel = null;
				try {
					sup_tel = new Integer(req.getParameter("sup_tel").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�п�J�pô�q��");
				}
				
				String sup_tax = req.getParameter("sup_tax").trim();		
				if (sup_tax == null || (sup_tax.trim()).length() == 0) {
					errorMsgs.add("�п�J������X");	
				} else if (!sup_tax.matches("[0-9]*")){
					errorMsgs.add("������X�п�J�Ʀr");
				}
				Integer sup_mobile = 0;
				if(sup_tax != null && (sup_tax.trim()).length() != 0){
					sup_mobile  = Integer.parseInt(sup_tax);
				}
				
				Integer sup_adcode = null;
				try {
					sup_adcode = new Integer(req.getParameter("sup_adcode").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�п�J�l���ϸ�");
				}

				String sup_addr = req.getParameter("sup_addr").trim();
				if (sup_addr == null || (sup_addr.trim()).length() == 0) {
					errorMsgs.add("�п�J�a�}");
				}
				String sup_note = req.getParameter("sup_note").trim();
				String sup_type = req.getParameter("sup_type").trim();

				SupVO SupVO = new SupVO();
				SupVO.setSup_no(sup_no);
				SupVO.setSup_name(sup_name);
				SupVO.setSup_pwd(sup_pwd);
				SupVO.setSup_id(sup_id);
				SupVO.setSup_con(sup_con);
				SupVO.setSup_telcode(sup_telcode);
				SupVO.setSup_tel(sup_tel);
				SupVO.setSup_tax(sup_mobile);
				SupVO.setSup_adcode(sup_adcode);
				SupVO.setSup_addr(sup_addr);
				SupVO.setSup_note(sup_note);
				SupVO.setSup_type(sup_type);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					if(num.equals("1")) {
						req.setAttribute("SupVO1", SupVO); // �t����J�榡���~��SupVO����,�]�s�Jreq
						RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/sup_firstpage.jsp");
						failureView.forward(req, res);
						return; // �{�����_
					}
					if(num.equals("2")) {				 
						req.setAttribute("SupVO2", SupVO); // �t����J�榡���~��SupVO����,�]�s�Jreq
						RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/sup/supplierhome.jsp");
						failureView.forward(req, res);
						return; // �{�����_
					}
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				SupService SupSvc = new SupService();
				SupVO = SupSvc.updateSup(sup_no, sup_name, sup_acct, sup_pwd, sup_id, sup_con, sup_telcode, sup_tel,
						sup_mobile, sup_adcode, sup_addr, sup_note, sup_type);

				/***************************
				 * 3.�ק粒��,�ǳ����(Send the Success view)
				 *************/
				if(num.equals("1")) {
					req.setAttribute("updateSupVO1", SupVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
					String url = "/supplier_end/sup_firstpage.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
					successView.forward(req, res);
				}
				if(num.equals("2")) {
					req.setAttribute("updateSupVO2", SupVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
					String url = "/supplier_end/sup/supplierhome.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
					successView.forward(req, res);
				}
				/*************************** ��L�i�઺���~�B�z *************************************/
//			} catch (Exception e) {
//				if(num.equals("1")) {
//					errorMsgs.add("�ק��ƥ���:" + e.getMessage());
//					RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/sup_firstpage.jsp");
//					failureView.forward(req, res);
//				}
//				if(num.equals("2")) {
//					errorMsgs.add("�ק��ƥ���:" + e.getMessage());
//					RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/sup/supplierhome.jsp");
//					failureView.forward(req, res);
//				}
				
//			}
		}
		

		if ("getOne_For_Check".equals(action)) { // �Ӧ�listAllSup.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/*************************** 1.�����ШD�Ѽ� ****************************************/
			String sup_no = new String(req.getParameter("sup_no"));

			/*************************** 2.�}�l�d�߸�� ****************************************/
			SupService SupSvc = new SupService();
			SupVO SupVO = SupSvc.getOneSup(sup_no);

			/***************************
			 * 3.�d�ߧ���,�ǳ����(Send the Success view)
			 ************/
			req.setAttribute("SupVO", SupVO); // ��Ʈw���X��SupVO����,�s�Jreq
			String url = "/back_end/sup/check_sup_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
																			// update_sup_input.jsp
			successView.forward(req, res);

			/*************************** ��L�i�઺���~�B�z **********************************/
			// } catch (Exception e) {
			// errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/front-end/sup/listAllSup.jsp");
			// failureView.forward(req, res);
			// }
		}
		
		if ("update_sup_note".equals(action)) { // �Ӧ�update_sup_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				String sup_no = req.getParameter("sup_no");
				String sup_note = req.getParameter("sup_note");
				System.out.println(sup_no);

				/*************************** 2.�}�l�ק��� *****************************************/
				SupService SupSvc = new SupService();
				SupVO SupVO = SupSvc.getOneSup(sup_no);
				
				SupVO.setSup_note(sup_note);
				SupSvc.updateSup(sup_no, SupVO.getSup_name(), SupVO.getSup_acct(), SupVO.getSup_pwd(), SupVO.getSup_id(), SupVO.getSup_con(), SupVO.getSup_telcode(), SupVO.getSup_tel(),
						SupVO.getSup_tax(), SupVO.getSup_adcode(), SupVO.getSup_addr(), SupVO.getSup_note(), SupVO.getSup_type());

				/***************************
				 * 3.�ק粒��,�ǳ����(Send the Success view)
				 *************/
				req.setAttribute("SupVO", SupVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back_end/sup/listAllSup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/sup/check_sup_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // �Ӧ�addSup.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/
				SupService SupSvc = new SupService();
				List<SupVO> list1 = SupSvc.getAll();

				String sup_name = req.getParameter("sup_name");
				if (sup_name == null || (sup_name.trim()).length() == 0) {
					errorMsgs.add("�п�J�W��");
				}
				String sup_acct = req.getParameter("sup_acct");
				if (sup_acct == null || (sup_acct.trim()).length() == 0) {
					errorMsgs.add("�п�J�b��");
				} 
				for (SupVO m : list1) {
					if (sup_acct.equals(m.getSup_acct())) {
						errorMsgs.add("���b���w�s�b");
					} else {
					}
				}

				String sup_pwd = req.getParameter("sup_pwd").trim();
				if (sup_pwd == null || (sup_pwd.trim()).length() == 0) {
					errorMsgs.add("�п�J�K�X");
				}
				Integer sup_id = null;
				try {
					sup_id = new Integer(req.getParameter("sup_id").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�ж�J�Τ@�s��");
				}

				String sup_con = req.getParameter("sup_con").trim();
				if (sup_con == null || (sup_con.trim()).length() == 0) {
					errorMsgs.add("�п�J�pô���f");
				}

				Integer sup_telcode = null;
				try {
					sup_telcode = new Integer(req.getParameter("sup_telcode").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�ж�J�q�ܰϽX");
				}

				Integer sup_tel = null;
				try {
					sup_tel = new Integer(req.getParameter("sup_tel").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�ж�J�q��");
				}

				Integer sup_tax = null;
				try {
					sup_tax = new Integer(req.getParameter("sup_tax").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�ж�J�ǯu�X");
				}

				Integer sup_adcode = null;
				try {
					sup_adcode = new Integer(req.getParameter("sup_adcode").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�ж�J�l���ϸ�");
				}

				String sup_addr = req.getParameter("sup_addr").trim();
				if (sup_addr == null || (sup_addr.trim()).length() == 0) {
					errorMsgs.add("�п�J�a�}");
				}
				String sup_note = req.getParameter("sup_note").trim();
				String sup_type = req.getParameter("sup_type").trim();

				SupVO SupVO = new SupVO();
				SupVO.setSup_name(sup_name);
				SupVO.setSup_pwd(sup_pwd);
				SupVO.setSup_id(sup_id);
				SupVO.setSup_con(sup_con);
				SupVO.setSup_telcode(sup_telcode);
				SupVO.setSup_tel(sup_tel);
				SupVO.setSup_tax(sup_tax);
				SupVO.setSup_adcode(sup_adcode);
				SupVO.setSup_addr(sup_addr);
				SupVO.setSup_note(sup_note);
				SupVO.setSup_type(sup_type);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("SupVO", SupVO); // �t����J�榡���~��SupVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/sup_keyin.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				// SupService SupSvc = new SupService();
				SupVO = SupSvc.addSup(sup_name, sup_acct, sup_pwd, sup_id, sup_con, sup_telcode, sup_tel, sup_tax,
						sup_adcode, sup_addr, sup_note, sup_type);

				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				String url = "/supplier_end/suplogin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/sup_keyin.jsp");
				failureView.forward(req, res);
			}
		}

		if ("listPlace_BySup_no_A".equals(action) || "listPlace_BySup_no_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String sup_no = new String(req.getParameter("sup_no"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				SupService SupSvc = new SupService();
				Set<PlaceVO> set = SupSvc.getPlaceBySup_no(sup_no);

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("listPlace_BySup_no", set); // ��Ʈw���X��set����,�s�Jrequest

				String url = null;
				if ("listPlace_BySup_no_A".equals(action))
					url = "/supplier_end/sup/listPlace_BySup_no.jsp"; // ���\���
																		// sup/listPlace_BySup_no.jsp
				else if ("listPlace_BySup_no_B".equals(action))
					url = "/supplier_end/sup/listAllSup.jsp"; // ���\���
																// sup/listAllSup.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("listAdv_BySup_no_A".equals(action) || "listAdv_BySup_no_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String sup_no = new String(req.getParameter("sup_no"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				SupService SupSvc = new SupService();
				Set<AdvVO> set = SupSvc.getAdvBySup_no(sup_no);

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("listAdv_BySup_no", set); // ��Ʈw���X��set����,�s�Jrequest

				String url = null;
				if ("listAdv_BySup_no_A".equals(action))
					url = "/supplier_end/sup/listAdv_BySup_no.jsp"; // ���\���
																	// sup/listPlace_BySup_no.jsp
				else if ("listAdv_BySup_no_B".equals(action))
					url = "/supplier_end/sup/listAllSup.jsp"; // ���\���
																// sup/listAllSup.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllSup.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String sup_no = new String(req.getParameter("sup_no"));

				/*************************** 2.�}�l�R����� ***************************************/
				SupService SupSvc = new SupService();
				SupSvc.deleteSup(sup_no);

				/***************************
				 * 3.�R������,�ǳ����(Send the Success view)
				 ***********/
				String url = "/supplier_end/sup/listAllSup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/sup/listAllSup.jsp");
				failureView.forward(req, res);
			}
		}

		if ("logout".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = req.getSession();

				session.removeAttribute("supAccount");

				RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/suplogin.jsp");
				// RequestDispatcher failureView =
				// req.getRequestDispatcher("/supplier_end/sup/sup_firstpage.jsp");
				failureView.forward(req, res);

//				Enumeration<String> Enumeration = session.getAttributeNames();
//				while (Enumeration.hasMoreElements()) {
//					String name = (String) Enumeration.nextElement();
//					System.out.println(name + ":" + session.getAttribute(name));
//				}
			} catch (Exception e) {

			}

		}

	}
}

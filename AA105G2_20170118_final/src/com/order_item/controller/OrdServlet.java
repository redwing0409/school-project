package com.order_item.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.commodity.model.ComService;
import com.commodity.model.ComVO;
import com.order_item.model.*;
import com.sup.model.SupVO;

public class OrdServlet extends HttpServlet {

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
			String num = req.getParameter("number");
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("pur_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���ʭq��s��");
				}
				// Send the use back to the form, if there were errors

				if(num.equals("2")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/ord/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
				}
				if(num.equals("1")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/ord/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
				}		
				String pur_no = "";
				try {
					pur_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("���ʭq��s���榡�����T");
				}
				// Send the use back to the form, if there were errors

				if(num.equals("2")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/ord/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
				}
				if(num.equals("1")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/ord/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
				}
				/***************************2.�}�l�d�߸��*****************************************/
				OrdService ordSvc = new OrdService();
				List<OrdVO> OrdVO_List = ordSvc.getOneOrd(pur_no);

				if (OrdVO_List == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors

				if(num.equals("2")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/ord/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					   }
				}
				if(num.equals("1")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/ord/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					   }
				}
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				if(num.equals("2")){
					req.setAttribute("OrdVO_List", OrdVO_List); // ��Ʈw���X��ordVO����,�s�Jreq
					String url = "/back_end/ord/listOneOrd.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneCom.jsp
					successView.forward(req, res);
					return;
				}
				if(num.equals("1")){
					req.setAttribute("OrdVO_List", OrdVO_List); // ��Ʈw���X��ordVO����,�s�Jreq
					String url = "/front_end/ord/listOneOrd.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneCom.jsp
					successView.forward(req, res);
					return;
				}
				/***************************��L�i�઺���~�B�z*************************************/
				} catch (Exception e) {
					if(num.equals("1")){
					errorMsgs.add("�L�k���o���:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/ord/select_page.jsp");
					failureView.forward(req, res);
				   }
					if(num.equals("2")){
					errorMsgs.add("�L�k���o���:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/ord/select_page.jsp");
					failureView.forward(req, res);
				   }
				}
		     }
		
			if ("getOne_For_Update".equals(action)) { // �Ӧ�select_page.jsp���ШD
	
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.�����ШD�Ѽ�****************************************/
					String pur_no = req.getParameter("pur_no");
					String com_no = req.getParameter("com_no");
					/***************************2.�}�l�d�߸��****************************************/
					OrdService ordSvc = new OrdService();
					OrdVO ordVO = ordSvc.getCompositeOrd(pur_no,com_no);
					
					/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
					req.setAttribute("ordVO", ordVO);         // ��Ʈw���X��ordVO����,�s�Jreq
					String url = "/supplier_end/ord/listAllOrderItem.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_com_input.jsp
					successView.forward(req, res);
				  
					/***************************��L�i�઺���~�B�z**********************************/
				} catch (Exception e) {
					errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/ord/listAllOrderItem.jsp");
					failureView.forward(req, res);
				}		
			}
			
			
			if ("update".equals(action)) { // �Ӧ�update_ord_input.jsp���ШD
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			
				try {
					/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
					String pur_no = req.getParameter("pur_no").trim();
					String com_no = req.getParameter("com_no").trim();
						
					Integer ord_price = null;
					try {
						ord_price = new Integer(req.getParameter("ord_price").trim());
					} catch (NumberFormatException e) {
						ord_price = null;
						errorMsgs.add("�п�J�ӫ~���.");
					}
					
					Integer ord_qty = null;
					try {
						ord_qty = new Integer(req.getParameter("ord_qty").trim());
					} catch (NumberFormatException e) {
						ord_qty = null;
						errorMsgs.add("�п�J�q�ʼƶq.");
					}
					
					Integer return_qty = null;
					try {
						return_qty = new Integer(req.getParameter("return_qty").trim());
					} catch (NumberFormatException e) {
						return_qty = null;
						errorMsgs.add("�п�J�h�f�ƶq.");
					}
					
					Integer ship_status = null;
					try {
						ship_status = new Integer(req.getParameter("ship_status").trim());
					} catch (NumberFormatException e) {
						ship_status = null;
						errorMsgs.add("�п�ܥX�f���A.");
					}	
					OrdVO ordVO = new OrdVO();
					ordVO.setPur_no(pur_no);
					ordVO.setCom_no(com_no);
					ordVO.setOrd_price(ord_price);
					ordVO.setOrd_qty(ord_qty);
					ordVO.setReturn_qty(return_qty);
					ordVO.setShip_status(ship_status);
					
					// Send the use back to the form, if there were errors			
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("ordVO", ordVO); // �t����J�榡���~��ordVO����,�]�s�Jreq	
						RequestDispatcher failureView = req
								.getRequestDispatcher("/supplier_end/ord/listAllOrderItem.jsp");
						failureView.forward(req, res);
						return; //�{�����_
					}
					
					/***************************2.�}�l�ק���*****************************************/
					OrdService ordSvc = new OrdService();
					ordVO = ordSvc.updateOrd(pur_no, com_no, ord_price, ord_qty, return_qty, ship_status);
					
					/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
					req.setAttribute("ordVO2", ordVO); // ��Ʈwupdate���\��,���T����ordVO����,�s�Jreq
					String url = "/supplier_end/ord/listAllOrderItem.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneOrd.jsp
					successView.forward(req, res);

					/***************************��L�i�઺���~�B�z*************************************/
				} catch (Exception e) {
					errorMsgs.add("�ק��ƥ���:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/ord/listAllOrderItem.jsp");
					failureView.forward(req, res);
				}
			}
			
			 if ("insert".equals(action)) { // �Ӧ�addOrd.jsp���ШD  
                  
				 List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
				 req.setAttribute("errorMsgs", errorMsgs);
				
				 try {
						/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
						String pur_no = req.getParameter("pur_no").trim();
						String com_no = req.getParameter("com_no").trim();
						
						Integer ord_price = null;
						try {
							ord_price = new Integer(req.getParameter("ord_price").trim());
						} catch (NumberFormatException e) {
							ord_price = null;
							errorMsgs.add("�q����ӻ���ж�Ʀr.");
						}
						
						Integer ord_qty = null;
						try {
							ord_qty = new Integer(req.getParameter("ord_qty").trim());
						} catch (NumberFormatException e) {
							ord_qty = null;
							errorMsgs.add("�q�ʼƶq�ж�Ʀr.");
						}
						
						Integer return_qty = null;
						try {
							return_qty = new Integer(req.getParameter("return_qty").trim());
						} catch (NumberFormatException e) {
							return_qty = null;
							errorMsgs.add("�h�f�ƶq�ж�Ʀr.");
						}
						
						Integer ship_status = null;
						try {
							ship_status = new Integer(req.getParameter("ship_status").trim());
						} catch (NumberFormatException e) {
							ship_status = null;
							errorMsgs.add("�X�f���A�ж�Ʀr.");
						}	
												
						OrdVO ordVO = new OrdVO();
						ordVO.setPur_no(pur_no);
						ordVO.setCom_no(com_no);
						ordVO.setOrd_price(ord_price);
						ordVO.setOrd_qty(ord_qty);
						ordVO.setReturn_qty(return_qty);
						ordVO.setShip_status(ship_status);

						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("ordVO", ordVO); // �t����J�榡���~��ordVO����,�]�s�Jreq
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back_end/ord/addOrd.jsp");
							failureView.forward(req, res);
							return; //�{�����_
						}
						
						/***************************2.�}�l�s�W���***************************************/
						OrdService ordSvc = new OrdService();
						ordVO = ordSvc.addOrd(pur_no, com_no, ord_price, ord_qty, return_qty, ship_status);
						
						/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
						String url = "/back_end/ord/listAllOrd.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllOrd.jsp
						successView.forward(req, res);				
						
						/***************************��L�i�઺���~�B�z**********************************/
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/ord/listAllOrd.jsp");
						failureView.forward(req, res);
					}			 
			 }
			  
			      if ("delete".equals(action)) { // �Ӧ�listAllOrd.jsp

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
			
					try {
						/***************************1.�����ШD�Ѽ�***************************************/
						String pur_no = req.getParameter("pur_no");
						String com_no = req.getParameter("com_no");
						/***************************2.�}�l�R�����***************************************/
						OrdService ordSvc = new OrdService();
						ordSvc.deleteOrd(pur_no,com_no);
						
						/***************************3.�R������,�ǳ����(Send the Success view)***********/								
						String url = "/back_end/ord/listAllOrd.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
						successView.forward(req, res);
						
						/***************************��L�i�઺���~�B�z**********************************/
					} catch (Exception e) {
						errorMsgs.add("�R����ƥ���:"+e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/ord/listAllOrd.jsp");
						failureView.forward(req, res);
					}
				}
			      
		      if ("listOrderItem_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
	
					try {			
						/***************************1.�N��J����ରMap**********************************/ 
						//�ĥ�Map<String,String[]> getParameterMap()����k 
						//�`�N:an immutable java.util.Map 
						//Map<String, String[]> map = req.getParameterMap();
						HttpSession session = req.getSession();
						Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
						System.out.println(map);
						if (req.getParameter("whichPage") == null){
							HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
							HashMap<String, String[]> map2 = new HashMap<String, String[]>();
							map2 = (HashMap<String, String[]>)map1.clone();
							session.setAttribute("map",map2);
							map = (HashMap<String, String[]>)req.getParameterMap();
						} 
						
						/***************************2.�}�l�ƦX�d��***************************************/
						/*OrdService ordSvc = new OrdService();
						List<OrdVO> list  = ordSvc.getAll(map);*/
						/***************************3.��@�t�Ӫ��ƦX�d��***************************************/
						SupVO supVO=(SupVO)session.getAttribute("supAccount");            //���o�bseesion���t�Ӫ���
						
						ComService comSvc = new ComService();
						List<ComVO> ComVOList  = comSvc.getAll();                      //�ƦX�d�߰ӫ~���
						
						OrdService ordSvc = new OrdService();
						List<OrdVO> OrdVOList  = ordSvc.getAll(map);                         //�w�q�@��List<OrdVO>����A��X���󤺪��q��s��
						
						List<OrdVO> OrdVOList_ToPrint  = new ArrayList<OrdVO>();          //�ۦ�w�q�@��List<OrdVO>����A�H����᪺���G
						
						for(ComVO comVO_item : ComVOList) {                             
							if(supVO.getSup_no().equals(comVO_item.getSup_no())) {       //���session���t�ӽs���O�_���b�ӫ~���̪��t�ӽs���A�H��X�ӫ~��檺�ӫ~�s��
								for(OrdVO OrdVOlist_item : OrdVOList) {                  
									if(comVO_item.getCom_no().equals(OrdVOlist_item.getCom_no())) //�A�z�L�ӫ~��檺�ӫ~�s���A���q����Ӹ̪��ӫ~�s���A�H��X�n�J�t�Ӫ��q�����
										OrdVOList_ToPrint.add(OrdVOlist_item);
								}
							}
						}
						/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
						req.setAttribute("listOrderItem_ByCompositeQuery", OrdVOList_ToPrint); // ��Ʈw���X��list����,�s�Jrequest
						RequestDispatcher successView = req.getRequestDispatcher("/supplier_end/ord/listAllOrderItem.jsp"); // ���\���listAllOrderItem.jsp
						successView.forward(req, res);
						/***************************��L�i�઺���~�B�z**********************************/
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/supplier_end/ord/listAllOrderItem.jsp");
						failureView.forward(req, res);
					}
				}	
		
	     }	
	
}

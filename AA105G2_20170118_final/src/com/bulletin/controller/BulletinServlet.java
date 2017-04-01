package com.bulletin.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.bulletin.model.*;
import com.groups.model.GroupsService;
import com.groups.model.GroupsVO;

@WebServlet("/bulletin/bulletinServlet.do")
public class BulletinServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String groups_no = req.getParameter("groups_no");
				if (groups_no == null || (groups_no.trim()).length() == 0) {
					errorMsgs.add("�п�J���s�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/bulletin/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				BulletinService bulletinSvc = new BulletinService();
				List<BulletinVO> list = bulletinSvc.getGroupsBulletin(groups_no);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/bulletin/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				GroupsService groupsSvc = new GroupsService();
				String url = "/front_end/bulletin/listBulletinByGroups.jsp";
				
				if(requestURL.equals("/front_end/groups/listOneGroups_Front.jsp")||requestURL.equals("/front_end/groups/groupsContent.jsp")){
					req.setAttribute("listOneGroups_Front",groupsSvc.getOneGroups(groups_no));
					url = requestURL;
				} else if(requestURL.equals("/front_end/groups/groupsIndex.jsp")){
					req.setAttribute("listOneGroups_Front",groupsSvc.getOneGroups(groups_no));
					url = "/front_end/groups/groupsContent.jsp";
				}
				
				req.setAttribute("listBulletinByGroups", list); // ��Ʈw���X��list����,�s�Jreq
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listBulletinByGroups.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/bulletin/select_page.jsp");
				failureView.forward(req, res);
			}
		}//getOne_For_Display
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllBulletin_Front.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String bulletin_no = req.getParameter("bulletin_no");
				
				/***************************2.�}�l�d�߸��****************************************/
				BulletinService bulletinSvc = new BulletinService();
				BulletinVO bulletinVO = bulletinSvc.getOneBulletin(bulletin_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("update_bulletin_input", bulletinVO);         // ��Ʈw���X��bulletinVO����,�s�Jreq
				String url = "/front_end/bulletin/update_bulletin_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_bulletin_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}//getOne_For_Update
		
		if ("update".equals(action)) { // �Ӧ�update_bulletin_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String bulletin_no = req.getParameter("bulletin_no");
				String groups_no = req.getParameter("groups_no");
				String member_no = req.getParameter("member_no");
				String bulletin_content = req.getParameter("bulletin_content");
				java.sql.Timestamp bulletin_time = java.sql.Timestamp.valueOf(req.getParameter("bulletin_time").trim());
				
				BulletinVO bulletinVO = new BulletinVO();
				bulletinVO.setBulletin_no(bulletin_no);
				bulletinVO.setGroups_no(groups_no);
				bulletinVO.setMember_no(member_no);
				bulletinVO.setBulletin_content(bulletin_content);
				bulletinVO.setBulletin_time(bulletin_time);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("update_bulletin_input", bulletinVO); // �t����J�榡���~��groupsVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/bulletin/update_bulletin_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.�}�l�ק���*****************************************/
				BulletinService bulletinSvc = new BulletinService();
				GroupsService groupsSvc = new GroupsService();
				bulletinVO = bulletinSvc.updateBulletin(bulletin_no, groups_no, member_no, bulletin_content, bulletin_time);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
//				req.setAttribute("update_bulletin_input", bulletinVO); // ��Ʈwupdate���\��,���T����bulletinVO����,�s�Jreq
				req.setAttribute("listBulletinByGroups",bulletinSvc.getGroupsBulletin(groups_no));
				
				if(requestURL.equals("/front_end/groups/listOneGroups_Front.jsp")||requestURL.equals("/front_end/groups/groupsContent.jsp")){
					req.setAttribute("listOneGroups_Front",groupsSvc.getOneGroups(groups_no));
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���^�e�X�ק諸�ӷ�����
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} 
			catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/bulletin/update_bulletin_input.jsp");
				failureView.forward(req, res);
			} 
		}//Update
		
		if ("insert".equals(action)) { // �Ӧ�addBulletin.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String groups_no = req.getParameter("groups_no").trim();
				String member_no = req.getParameter("member_no").trim();
				String bulletin_content = req.getParameter("bulletin_content").trim();
				if (bulletin_content == null || (bulletin_content.trim()).length() == 0) {
					errorMsgs.add("�п�J���e");
				} 
				
				java.sql.Timestamp bulletin_time = java.sql.Timestamp.valueOf(req.getParameter("bulletin_time").trim());
				
				BulletinVO bulletinVO = new BulletinVO();
				bulletinVO.setGroups_no(groups_no);
				bulletinVO.setMember_no(member_no);
				bulletinVO.setBulletin_content(bulletin_content);
				bulletinVO.setBulletin_time(bulletin_time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addBulletin", bulletinVO); // �t����J�榡���~��bulletinVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/bulletin/addBulletin.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				BulletinService bulletinSvc = new BulletinService();
				GroupsService groupsSvc = new GroupsService();
				bulletinVO = bulletinSvc.addBulletin(groups_no, member_no, bulletin_content, bulletin_time);
				
//				List<BulletinVO> list = bulletinSvc.getAllFront();
//				BulletinVO vo = list.get(list.size()-1);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				req.setAttribute("listBulletinByGroups",bulletinSvc.getGroupsBulletin(groups_no));
				
				if(requestURL.equals("/front_end/groups/listOneGroups_Front.jsp")||requestURL.equals("/front_end/groups/groupsContent.jsp")){
					req.setAttribute("listOneGroups_Front",groupsSvc.getOneGroups(groups_no));
				}
				
//				String url = "/front_end/bulletin/listAllBulletin_Front.jsp?whichPage=50&bulletin_no="+vo.getBulletin_no();
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // �s�W���\�����listAllGroups_Front.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/bulletin/addBulletin.jsp");
				failureView.forward(req, res);
			}
		}//insert
		
		if ("delete".equals(action)) { // �Ӧ�listAllBulletin_Front.jsp ��  listBulletinByGroups.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String bulletin_no = req.getParameter("bulletin_no");
				String groups_no = req.getParameter("groups_no");
				
				/***************************2.�}�l�R�����***************************************/
				BulletinService bulletinSvc = new BulletinService();
				GroupsService groupsSvc = new GroupsService();
				bulletinSvc.deleteBulletin(bulletin_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
				String url = requestURL;
				
				if(requestURL.equals("/front_end/groups/listOneGroups_Front.jsp")||requestURL.equals("/front_end/groups/groupsContent.jsp")){
					req.setAttribute("listOneGroups_Front",groupsSvc.getOneGroups(groups_no));
				}
				
				req.setAttribute("listBulletinByGroups",bulletinSvc.getGroupsBulletin(groups_no));
				RequestDispatcher successView = req.getRequestDispatcher(url); // �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}//delete
		
	}//doPost

}

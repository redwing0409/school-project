package com.groups.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.bulletin.model.BulletinService;
import com.bulletin.model.BulletinVO;
import com.groups.model.*;
import com.groups_list.model.*;
import com.member.model.*;

@WebServlet("/groups/groupsServlet.do")
public class GroupsServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = null;
			System.out.println("test");
			if(req.getParameter("requestURL") != null){
				requestURL = req.getParameter("requestURL");
			}
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("groups_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���s�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/groups/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer groups_no = null;
				try {
					groups_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���s�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/groups/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				GroupsService groupsSvc = new GroupsService();
				GroupsVO groupsVO = groupsSvc.getOneGroups(str);
				if (groupsVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/groups/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("listOneGroups_Front", groupsVO); // ��Ʈw���X��groupsVO����,�s�Jreq
//				String url = "/front_end/groups/listOneGroups_Front.jsp";
				String url = "/front_end/groups/groupsContent.jsp";
				
//				if(requestURL!=null && requestURL.equals("/front_end/groups/GroupsIndex.jsp")){
//					url = "/front_end/groups/GroupsIndex.jsp";
//				}
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneGroups_Front.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/groups/select_page.jsp");
				failureView.forward(req, res);
			}
		}//getOne_For_Display
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllGroups.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String groups_no = req.getParameter("groups_no");
				
				/***************************2.�}�l�d�߸��****************************************/
				GroupsService groupsSvc = new GroupsService();
				GroupsVO groupsVO = groupsSvc.getOneGroups(groups_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("update_groups_input", groupsVO);         // ��Ʈw���X��groupsVO����,�s�Jreq
				String url = "/front_end/groups/update_groups_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_groups_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}//getOne_For_Update
		
		if ("update".equals(action)) { // �Ӧ�update_groups_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String groups_no = req.getParameter("groups_no");
				String groups_owner = req.getParameter("groups_owner");
				String groups_title = req.getParameter("groups_title");
				java.sql.Date groups_time = java.sql.Date.valueOf(req.getParameter("groups_time").trim());
				
				GroupsVO groupsVO = new GroupsVO();
				groupsVO.setGroups_no(groups_no);
				groupsVO.setGroups_owner(groups_owner);
				groupsVO.setGroups_title(groups_title);
				groupsVO.setGroups_time(groups_time);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("update_groups_input", groupsVO); // �t����J�榡���~��groupsVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/groups/update_groups_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.�}�l�ק���*****************************************/
				GroupsService groupsSvc = new GroupsService();
				BulletinService bulletinSvc = new BulletinService();
				groupsVO = groupsSvc.updateGroups(groups_no, groups_owner, groups_title, groups_time);
				List<BulletinVO> list = bulletinSvc.getGroupsBulletin(groups_no);
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("listOneGroups_Front", groupsVO); // ��Ʈwupdate���\��,���T����groupsVO����,�s�Jreq
				req.setAttribute("listBulletinByGroups", list);
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���^�e�X�ק諸�ӷ�����
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} 
			catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/groups/update_groups_input.jsp");
				failureView.forward(req, res);
			} 
		}//Update
		
		if ("insert".equals(action)) { // �Ӧ�addGroups.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String groups_owner = req.getParameter("groups_owner").trim();
				String groups_title = req.getParameter("groups_title").trim();
				if (groups_title == null || (groups_title.trim()).length() == 0) {
					errorMsgs.add("�п�J���ΦW��");
				} 
				
				java.sql.Date groups_time = java.sql.Date.valueOf(req.getParameter("groups_time").trim());
				
				GroupsVO groupsVO = new GroupsVO();
				groupsVO.setGroups_owner(groups_owner);
				groupsVO.setGroups_title(groups_title);
				groupsVO.setGroups_time(groups_time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addGroups", groupsVO); // �t����J�榡���~��groupsVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/groups/addGroups.jsp");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/groups/groupsIndex.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				GroupsService groupsSvc = new GroupsService();
				groupsVO = groupsSvc.addGroups(groups_owner, groups_title, groups_time);
				
				List<GroupsVO> list = new GroupsService().getAllFront();
				GroupsVO vo = list.get(list.size()-1);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				String url = "/front_end/groups/listAllGroups_Front.jsp?whichPage=50&groups_no="+vo.getGroups_no();
				String url = "/front_end/groups/groupsIndex.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllGroups_Front.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/groups/groupsIndex.jsp");
				failureView.forward(req, res);
			}
		}//insert
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j

			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String groups_no = req.getParameter("groups_no");
				
				/***************************2.�}�l�R�����***************************************/
				GroupsService groupsSvc = new GroupsService();
				groupsSvc.deleteGroups(groups_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
				String url = "/front_end/groups/groupsIndex.jsp";
//				String url = requestURL;
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
		
		if ("listMembers_ByGroups_no".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String groups_no = req.getParameter("groups_no");
				String requestURL = req.getParameter("requestURL");
				/*************************** 2.�}�l�d�߸�� ****************************************/
				
				Groups_listService groups_listSvc = new Groups_listService();
				List<Groups_ListVO> list = groups_listSvc.getOneByGroupsNo(groups_no);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				if(requestURL.equals("/front_end/groups/groupsContent.jsp")){
					req.setAttribute("listOneGroups_Front", new GroupsService().getOneGroups(groups_no));
				}
				req.setAttribute("listOneByGroupsNo", list);    // ��Ʈw���X��list����,�s�Jrequest
				
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

	}//doPost

}

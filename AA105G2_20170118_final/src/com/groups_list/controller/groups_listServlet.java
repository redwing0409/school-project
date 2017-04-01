package com.groups_list.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.groups.model.*;
import com.groups_list.model.*;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/groups_list/groups_listServlet.do")
public class groups_listServlet extends HttpServlet {
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			GroupsService groupsSvc = new GroupsService();
			List<GroupsVO> gList = groupsSvc.getAllBack();
			
			Groups_listService groups_listSvc = new Groups_listService();
			String url = null;
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				/***************************2.�}�l�d�߸��*****************************************/
				String groups_no = req.getParameter("groups_no");
				String member_no = req.getParameter("member_no");
				if (member_no == null) {
//					for(GroupsVO g:gList){
//						if(g.getGroups_no().equals(groups_no) && g.getGroups_status().equals(0)){
//							errorMsgs.add("---�d�L���---");
//						}
//					}
					List<Groups_ListVO> listByGroups = groups_listSvc.getOneByGroupsNo(groups_no);
					if (listByGroups.size() == 0) {
						errorMsgs.add("�d�L���");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/groups_list/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
					req.setAttribute("listOneByGroupsNo", listByGroups); // ��Ʈw���X��List<Groups_ListVO>����,�s�Jreq
					url = "/front_end/groups_list/listOneByGroupsNo.jsp";
				}
				if (groups_no == null) {
					List<Groups_ListVO> listByMember = groups_listSvc.getOneByMemberNo(member_no);
					if (listByMember.size() == 0) {
						errorMsgs.add("�d�L���");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/groups_list/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
					req.setAttribute("listOneByMemberNo", listByMember); // ��Ʈw���X��List<Groups_ListVO>����,�s�Jreq
					url = "/front_end/groups_list/listOneByMemberNo.jsp";
				}
				
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneByGroupsNo.jsp��listOneByMemberNo.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/groups_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}//getOne_For_Display
		
		if ("insert".equals(action)) { // �Ӧ�addGroups_list.jsp���ШD  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String groups_no = req.getParameter("groups_no").trim();
				String member_no = req.getParameter("member_no").trim();
				String requestURL = req.getParameter("requestURL");
				System.out.println(requestURL);
				if (member_no == null || (member_no.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}

				Groups_ListVO groups_ListVO = new Groups_ListVO();
				groups_ListVO.setGroups_no(groups_no);
				groups_ListVO.setMember_no(member_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("groups_ListVO", groups_ListVO); // �t����J�榡���~��groups_ListVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/groups_list/addGroups_list.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Groups_listService groups_listSvc = new Groups_listService();
				groups_ListVO = groups_listSvc.addGroups_list(groups_no, member_no);
//				req.setAttribute("listOneByGroupsNo",groups_listSvc.getOneByGroupsNo(groups_no));
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				String url = "/front_end/groups_list/listOneByGroupsNo.jsp?member_no=" + member_no;
//				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // �s�W���\�����listAllGroups_list_Front.jsp
//				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/groups_list/addGroups_list.jsp");
				failureView.forward(req, res);
			}
		}//insert
		
		if ("delete".equals(action)) { // �Ӧ�/groups/listAllGroups_Front.jsp ��  /groups/listAllGroups_Front.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|: �i�ର/groups/listAllGroups_Front.jsp ��  /groups/listAllGroups_Front.jsp
//			System.out.println(requestURL);
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String groups_no = req.getParameter("groups_no");
				String member_no = req.getParameter("member_no");
				/***************************2.�}�l�R�����***************************************/
				Groups_listService groups_listSvc = new Groups_listService();
				GroupsService groupsSvc = new GroupsService();
				groups_listSvc.deleteGroups_list(groups_no,member_no);
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
//				if(requestURL.equals("/front_end/groups/listAllGroups_Front.jsp")||requestURL.equals("/front_end/groups_list/listOneByGroupsNo.jsp")){
//					req.setAttribute("listOneByGroupsNo",groups_listSvc.getOneByGroupsNo(groups_no));
//				}
				req.setAttribute("listOneByGroupsNo",groups_listSvc.getOneByGroupsNo(groups_no));
				req.setAttribute("listOneGroups_Front",groupsSvc.getOneGroups(groups_no));
				
				if(req.getParameter("leaveGroupsButton") != null){
					requestURL = "/front_end/groups/groupsIndex.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}//delete
		
		if ("findMemberNOByGroupsNO".equals(action)) { // �Ӧ�addGroups_list.jsp���ШD
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			GroupsService groupsSvc = new GroupsService();
			List<GroupsVO> list = groupsSvc.getAllBack();
			
			
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String groups_no = req.getParameter("groups_no");
				
				for(GroupsVO g:list){
					if(g.getGroups_no().equals(groups_no) && g.getGroups_status().equals(0)){
						errorMsgs.add("---�d�L���---");
					}
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					out.println("<font color='red'>�Эץ��H�U���~:");
					out.println("<ul>");
					for(String message:errorMsgs){
						out.println("<li>" + message + "</li>");
					}
					out.println("</ul>");
					out.println("</font>");
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Groups_listService groups_listSvc = new Groups_listService();
				List<Groups_ListVO> list1 = groups_listSvc.getOneByGroupsNo(groups_no); //����
				GroupsVO groupsVO = groupsSvc.getOneGroups(groups_no);
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				MemberService memberSvc = new MemberService();
				List<MemberVO> memberVO_list = memberSvc.getAll();
				List<MemberVO> memberVO_list1 = new ArrayList<MemberVO>();
				JSONObject jsonObj = new JSONObject();
				
				for(MemberVO g:memberVO_list){
					boolean isSame = true;
					if(list1.size() == 0){ 
						if(groupsVO.getGroups_owner().equals(g.getMember_no())){
							isSame = false;
						}
					} else {
						for(Groups_ListVO g_list:list1){
							if(g_list.getMember_no().equals(g.getMember_no())||groupsVO.getGroups_owner().equals(g.getMember_no())){
								isSame = false;
							}
						}
					}
					if(isSame){
						memberVO_list1.add(g);
						jsonObj.put(g.getMember_no(), g.getMember_name());
					}
				}
				out.println(jsonObj);
				out.flush();
				out.close();
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/groups_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}//findMemberNOByGroupsNO
	}

}

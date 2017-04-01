package com.friend.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.friend.model.*;
import com.member.model.*;


public class FriendServlet extends HttpServlet {
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

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("member_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/friend/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String member_no = null;
				try {
					new Integer(str);
					member_no=str;
				} catch (Exception e) {
					errorMsgs.add("�|���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/friend/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				FriendService friendSvc = new FriendService();
				List<FriendVO> list = friendSvc.getOneFriend(member_no);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/friend/select_page.jsp");
					failureView.forward(req, res);
					
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); // ��Ʈw���X��list����,�s�Jreq
				String url = "/front_end/friend/listOneFriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneFriend.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/friend/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		

        if ("insert".equals(action)) { // �Ӧ�addFriend.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				MemberService memSvc=new MemberService();
				List<MemberVO> list = memSvc.getAll();
				MemberVO memberVO=null;
				
				String member_no = req.getParameter("member_no");//�n�J���|���b��
				String member_acc = req.getParameter("member_acc");	
				
				
				boolean self=false;
				if(member_acc==null) errorMsgs.add("�b����J���~");
				else{
					for(MemberVO mem:list){						
							if(mem.getMember_acc().equals(member_acc)&&!(self=mem.getMember_no().equals(member_no))){
								memberVO=mem;//�n�s�W���n�ͷ|��
							}
					}
					if(memberVO==null){
						if(self){
							errorMsgs.add("����[�ۤv���n��");
						}
						else errorMsgs.add("�d�L���b��");
					}
				}
				MemberVO memberVOer = new MemberVO();
				memberVOer.setMember_acc(member_acc);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVOer); // �t����J�榡���~��friendVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				FriendService friendSvc = new FriendService();
				int updateCount = friendSvc.addFriend(member_no,memberVO.getMember_no());
				List<FriendVO> Friendlist = friendSvc.getOneFriend(member_no);
				req.setAttribute("list", Friendlist);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front_end/friend/listOneFriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // �s�W���\�����listAllArticle.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllArticle.jsp

			
			//����R���峹
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String member_no = req.getParameter("member_no");
				String friend_no = req.getParameter("friend_no");
				
				/***************************2.�}�l�R�����***************************************/
				FriendService friendSvc = new FriendService();
				friendSvc.deleteFriend(member_no,friend_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front_end/friend/listOneFriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		
	}

}

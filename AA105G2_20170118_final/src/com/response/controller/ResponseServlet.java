package com.response.controller;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.article.model.*;
import com.member.model.*;
import com.response.model.*;

public class ResponseServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
//		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				String str = req.getParameter("article_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("�п�J�峹�s��");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/article/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				String article_no = null;
//				try {
//					new Integer(str);
//					article_no=str;
//				} catch (Exception e) {
//					errorMsgs.add("�峹�s���榡�����T");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/article/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************2.�}�l�d�߸��*****************************************/
//				ResponseService responseSvc = new ResponseService();
//				ResponseVO responseVO = responseSvc.getOneArticle(article_no);
//				if (responseVO == null) {
//					errorMsgs.add("�d�L���");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/article/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
//				req.setAttribute("responseVO", responseVO); // ��Ʈw���X��responseVO����,�s�Jreq
//				String url = "/front_end/article/listOneArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneArticle.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front_end/article/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllArticle.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			String requestURL = req.getParameter("requestURL");
//			
//			try {
//				/***************************1.�����ШD�Ѽ�****************************************/
//				String article_no = req.getParameter("article_no");
//				
//				/***************************2.�}�l�d�߸��****************************************/
//				ResponseService responseSvc = new ResponseService();
//				ResponseVO responseVO = responseSvc.getOneArticle(article_no);
//								
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//				req.setAttribute("responseVO", responseVO);         // ��Ʈw���X��responseVO����,�s�Jreq
//				String url ="/front_end/article/update_article_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_article_input.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(requestURL);
//				failureView.forward(req, res);
//			}
//		}
//		
//		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String article_no = req.getParameter("article_no");		
				String response_no = req.getParameter("response_no");
				String member_no = req.getParameter("member_no");
				String response_content = req.getParameter("modify_content");
				if(response_content.isEmpty()){
					errorMsgs.add("�п�J���e");
				}
				Timestamp response_time = Timestamp.valueOf(req.getParameter("response_time"));

				

				ResponseVO responseVO = new ResponseVO();
				responseVO.setArticle_no(article_no);
				responseVO.setMember_no(member_no);
				responseVO.setResponse_content(response_content);
				responseVO.setResponse_time(response_time);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("responseVO", responseVO); // �t����J�榡���~��responseVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/article/listArticleContent.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				ResponseService responseSvc = new ResponseService();
				responseVO = responseSvc.updateResponse(response_no,article_no,member_no,
						response_content, response_time);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("responseVO", responseVO); // ��Ʈwupdate���\��,���T����responseVO����,�s�Jreq
				String url = "/front_end/article/listArticleContent.jsp?response_no="+response_no;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				

				

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/article/listArticleContent.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				String article_no = req.getParameter("article_no");
				String member_no = req.getParameter("member_no");
				Timestamp response_time = Timestamp.valueOf(req.getParameter("response_time"));

				String response_content = req.getParameter("response_content");
				if(response_content.isEmpty()){
					errorMsgs.add("�п�J���e");
				}

				

				ResponseVO responseVO = new ResponseVO();
				responseVO.setArticle_no(article_no);
				responseVO.setMember_no(member_no);
				responseVO.setResponse_content(response_content);
				responseVO.setResponse_time(response_time);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("responseVO", responseVO); // �t����J�榡���~��responseVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/article/listArticleContent.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				ResponseService responseSvc = new ResponseService();
				responseVO = responseSvc.addResponse(article_no, member_no,
						response_content, response_time);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front_end/article/listArticleContent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listArticleContent.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/article/listArticleContent.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllArticle.jsp

			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String response_no = req.getParameter("response_no");
				
				/***************************2.�}�l�R�����***************************************/
				ResponseService responseSvc = new ResponseService();
				responseSvc.deleteResponse(response_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = req.getParameter("requestURL");
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/article/listArticleContent.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
	}

}

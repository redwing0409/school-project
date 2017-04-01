package com.article.controller;

import java.io.*;
import java.util.*;
import java.sql.Timestamp;

import javax.servlet.*;
import javax.servlet.http.*;

import com.article.model.*;
import com.member.model.*;



public class ArticleServlet extends HttpServlet {

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
//				ArticleService articleSvc = new ArticleService();
//				ArticleVO articleVO = articleSvc.getOneArticle(article_no);
//				if (articleVO == null) {
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
//				req.setAttribute("articleVO", articleVO); // ��Ʈw���X��articleVO����,�s�Jreq
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
//				ArticleService articleSvc = new ArticleService();
//				ArticleVO articleVO = articleSvc.getOneArticle(article_no);
//								
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//				req.setAttribute("articleVO", articleVO);         // ��Ʈw���X��articleVO����,�s�Jreq
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
		
		
		if ("update".equals(action)) { // �Ӧ�update_article_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
		
//			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String article_no = req.getParameter("article_no").trim();
				String member_no = req.getParameter("member_no");
				String article_title = req.getParameter("article_title").trim();
				if(article_title.isEmpty()){
					errorMsgs.add("�п�J���D");
				}
				String article_content = req.getParameter("modify_content");
				if(article_content.isEmpty()){
					errorMsgs.add("�п�J���e");
				}
				Integer article_views = new Integer(req.getParameter("article_views"));
				Timestamp article_time = Timestamp.valueOf(req.getParameter("article_time"));
				Integer article_status = new Integer(req.getParameter("article_status"));
				

				ArticleVO articleVO = new ArticleVO();
				articleVO.setArticle_no(article_no);
				articleVO.setMember_no(member_no);
				articleVO.setArticle_title(article_title);
				articleVO.setArticle_content(article_content);
				articleVO.setArticle_views(article_views);
				articleVO.setArticle_time(article_time);
				articleVO.setArticle_status(article_status);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // �t����J�榡���~��articleVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/article/listArticleContent.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.updateArticle(article_no, member_no, article_title, article_content, article_views,article_time, article_status);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO); // ��Ʈwupdate���\��,���T����articleVO����,�s�Jreq
				String url = "/front_end/article/listArticleContent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneArticle.jsp
				successView.forward(req, res);
				

				

				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front_end/article/listArticleContent.jsp");
//				failureView.forward(req, res);
//			}
		}

        if ("insert".equals(action)) { // �Ӧ�addArticle.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				String member_no = req.getParameter("member_no");
				
				String article_title = req.getParameter("article_title").trim();
				if(article_title.isEmpty()){
					errorMsgs.add("�п�J���D");
				}
				String article_content = req.getParameter("article_content");
				if(article_content.isEmpty()){
					errorMsgs.add("�п�J���e");
				}
				Integer article_views = new Integer(req.getParameter("article_views"));
				Timestamp article_time = Timestamp.valueOf(req.getParameter("article_time"));
				Integer article_status = new Integer(req.getParameter("article_status"));
				

				ArticleVO articleVO = new ArticleVO();
				articleVO.setMember_no(member_no);
				articleVO.setArticle_title(article_title);
				articleVO.setArticle_content(article_content);
				articleVO.setArticle_views(article_views);
				articleVO.setArticle_time(article_time);
				articleVO.setArticle_status(article_status);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // �t����J�榡���~��articleVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.addArticle(member_no,article_title,
						article_content, article_views, article_time, article_status);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front_end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllArticle.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/article/addArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllArticle.jsp
			String article_no = req.getParameter("article_no");
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO=articleSvc.getOneArticle(article_no);
			articleSvc.updateArticle(article_no, articleVO.getMember_no(), articleVO.getArticle_title(), articleVO.getArticle_content(), articleVO.getArticle_views(), articleVO.getArticle_time(), 0);
			String url = "/front_end/article/listAllArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			//����R���峹
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.�����ШD�Ѽ�***************************************/
//				String article_no = req.getParameter("article_no");
//				
//				/***************************2.�}�l�R�����***************************************/
//				ArticleService articleSvc = new ArticleService();
//				articleSvc.deleteArticle(article_no);
//				
//				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
//				String url = "/front_end/article/listAllArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//				successView.forward(req, res);
//				
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front_end/article/listAllArticle.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("listArticle_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
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
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					//map2 = (HashMap<String, String[]>)map1.clone();
					//��clone��map2�̵M�L�k�ק�A�G�ϥ�putAll
					map2.putAll(map1);
					//���F�o��|���b���������|���s��
					String member_acc=req.getParameter("member_acc");
					MemberService memberSvc=new MemberService();
					List<MemberVO> list=memberSvc.getAll();
					for(MemberVO memberVO : list){
						if(memberVO.getMember_acc().equals(member_acc)){
							map2.remove("member_acc");
							map2.put("member_no", new String[] {memberVO.getMember_no()});
						}
					}

					session.setAttribute("map",map2);
					//map = (HashMap<String, String[]>)req.getParameterMap();
					map=map2;
				} 
				
				/***************************2.�}�l�ƦX�d��***************************************/
				ArticleService articleSvc = new ArticleService();
				List<ArticleVO> list  = articleSvc.getAll(map);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("listArticle_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/article/listAllArticle.jsp"); 
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}

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
		
		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("article_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入文章編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/article/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String article_no = null;
//				try {
//					new Integer(str);
//					article_no=str;
//				} catch (Exception e) {
//					errorMsgs.add("文章編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/article/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				ArticleService articleSvc = new ArticleService();
//				ArticleVO articleVO = articleSvc.getOneArticle(article_no);
//				if (articleVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/article/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req
//				String url = "/front_end/article/listOneArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneArticle.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front_end/article/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllArticle.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			String requestURL = req.getParameter("requestURL");
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				String article_no = req.getParameter("article_no");
//				
//				/***************************2.開始查詢資料****************************************/
//				ArticleService articleSvc = new ArticleService();
//				ArticleVO articleVO = articleSvc.getOneArticle(article_no);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("articleVO", articleVO);         // 資料庫取出的articleVO物件,存入req
//				String url ="/front_end/article/update_article_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_article_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(requestURL);
//				failureView.forward(req, res);
//			}
//		}
		
		
		if ("update".equals(action)) { // 來自update_article_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
		
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String article_no = req.getParameter("article_no").trim();
				String member_no = req.getParameter("member_no");
				String article_title = req.getParameter("article_title").trim();
				if(article_title.isEmpty()){
					errorMsgs.add("請輸入標題");
				}
				String article_content = req.getParameter("modify_content");
				if(article_content.isEmpty()){
					errorMsgs.add("請輸入內容");
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
					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的articleVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/article/listArticleContent.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.updateArticle(article_no, member_no, article_title, article_content, article_views,article_time, article_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO); // 資料庫update成功後,正確的的articleVO物件,存入req
				String url = "/front_end/article/listArticleContent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneArticle.jsp
				successView.forward(req, res);
				

				

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front_end/article/listArticleContent.jsp");
//				failureView.forward(req, res);
//			}
		}

        if ("insert".equals(action)) { // 來自addArticle.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String member_no = req.getParameter("member_no");
				
				String article_title = req.getParameter("article_title").trim();
				if(article_title.isEmpty()){
					errorMsgs.add("請輸入標題");
				}
				String article_content = req.getParameter("article_content");
				if(article_content.isEmpty()){
					errorMsgs.add("請輸入內容");
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
					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的articleVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.addArticle(member_no,article_title,
						article_content, article_views, article_time, article_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllArticle.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/article/addArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllArticle.jsp
			String article_no = req.getParameter("article_no");
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO=articleSvc.getOneArticle(article_no);
			articleSvc.updateArticle(article_no, articleVO.getMember_no(), articleVO.getArticle_title(), articleVO.getArticle_content(), articleVO.getArticle_views(), articleVO.getArticle_time(), 0);
			String url = "/front_end/article/listAllArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			//不能刪除文章
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				String article_no = req.getParameter("article_no");
//				
//				/***************************2.開始刪除資料***************************************/
//				ArticleService articleSvc = new ArticleService();
//				articleSvc.deleteArticle(article_no);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/front_end/article/listAllArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front_end/article/listAllArticle.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("listArticle_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					//map2 = (HashMap<String, String[]>)map1.clone();
					//用clone的map2依然無法修改，故使用putAll
					map2.putAll(map1);
					//為了得到會員帳號對應的會員編號
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
				
				/***************************2.開始複合查詢***************************************/
				ArticleService articleSvc = new ArticleService();
				List<ArticleVO> list  = articleSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listArticle_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/article/listAllArticle.jsp"); 
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}

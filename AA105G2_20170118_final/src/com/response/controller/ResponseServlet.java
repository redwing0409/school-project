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
//				ResponseService responseSvc = new ResponseService();
//				ResponseVO responseVO = responseSvc.getOneArticle(article_no);
//				if (responseVO == null) {
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
//				req.setAttribute("responseVO", responseVO); // 資料庫取出的responseVO物件,存入req
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
//				ResponseService responseSvc = new ResponseService();
//				ResponseVO responseVO = responseSvc.getOneArticle(article_no);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("responseVO", responseVO);         // 資料庫取出的responseVO物件,存入req
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
//		
//		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String article_no = req.getParameter("article_no");		
				String response_no = req.getParameter("response_no");
				String member_no = req.getParameter("member_no");
				String response_content = req.getParameter("modify_content");
				if(response_content.isEmpty()){
					errorMsgs.add("請輸入內容");
				}
				Timestamp response_time = Timestamp.valueOf(req.getParameter("response_time"));

				

				ResponseVO responseVO = new ResponseVO();
				responseVO.setArticle_no(article_no);
				responseVO.setMember_no(member_no);
				responseVO.setResponse_content(response_content);
				responseVO.setResponse_time(response_time);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("responseVO", responseVO); // 含有輸入格式錯誤的responseVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/article/listArticleContent.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ResponseService responseSvc = new ResponseService();
				responseVO = responseSvc.updateResponse(response_no,article_no,member_no,
						response_content, response_time);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("responseVO", responseVO); // 資料庫update成功後,正確的的responseVO物件,存入req
				String url = "/front_end/article/listArticleContent.jsp?response_no="+response_no;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				

				

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
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
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String article_no = req.getParameter("article_no");
				String member_no = req.getParameter("member_no");
				Timestamp response_time = Timestamp.valueOf(req.getParameter("response_time"));

				String response_content = req.getParameter("response_content");
				if(response_content.isEmpty()){
					errorMsgs.add("請輸入內容");
				}

				

				ResponseVO responseVO = new ResponseVO();
				responseVO.setArticle_no(article_no);
				responseVO.setMember_no(member_no);
				responseVO.setResponse_content(response_content);
				responseVO.setResponse_time(response_time);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("responseVO", responseVO); // 含有輸入格式錯誤的responseVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/article/listArticleContent.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ResponseService responseSvc = new ResponseService();
				responseVO = responseSvc.addResponse(article_no, member_no,
						response_content, response_time);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/article/listArticleContent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listArticleContent.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/article/listArticleContent.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllArticle.jsp

			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String response_no = req.getParameter("response_no");
				
				/***************************2.開始刪除資料***************************************/
				ResponseService responseSvc = new ResponseService();
				responseSvc.deleteResponse(response_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = req.getParameter("requestURL");
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/article/listArticleContent.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
	}

}

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
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String num = req.getParameter("number");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("pur_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入選購訂單編號");
				}
				// Send the use back to the form, if there were errors

				if(num.equals("2")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/ord/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				}
				if(num.equals("1")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/ord/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				}		
				String pur_no = "";
				try {
					pur_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("選購訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors

				if(num.equals("2")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/ord/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				}
				if(num.equals("1")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/ord/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				}
				/***************************2.開始查詢資料*****************************************/
				OrdService ordSvc = new OrdService();
				List<OrdVO> OrdVO_List = ordSvc.getOneOrd(pur_no);

				if (OrdVO_List == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors

				if(num.equals("2")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/ord/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					   }
				}
				if(num.equals("1")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/ord/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					   }
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				if(num.equals("2")){
					req.setAttribute("OrdVO_List", OrdVO_List); // 資料庫取出的ordVO物件,存入req
					String url = "/back_end/ord/listOneOrd.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCom.jsp
					successView.forward(req, res);
					return;
				}
				if(num.equals("1")){
					req.setAttribute("OrdVO_List", OrdVO_List); // 資料庫取出的ordVO物件,存入req
					String url = "/front_end/ord/listOneOrd.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCom.jsp
					successView.forward(req, res);
					return;
				}
				/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					if(num.equals("1")){
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/ord/select_page.jsp");
					failureView.forward(req, res);
				   }
					if(num.equals("2")){
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/ord/select_page.jsp");
					failureView.forward(req, res);
				   }
				}
		     }
		
			if ("getOne_For_Update".equals(action)) { // 來自select_page.jsp的請求
	
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					String pur_no = req.getParameter("pur_no");
					String com_no = req.getParameter("com_no");
					/***************************2.開始查詢資料****************************************/
					OrdService ordSvc = new OrdService();
					OrdVO ordVO = ordSvc.getCompositeOrd(pur_no,com_no);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("ordVO", ordVO);         // 資料庫取出的ordVO物件,存入req
					String url = "/supplier_end/ord/listAllOrderItem.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_com_input.jsp
					successView.forward(req, res);
				  
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/ord/listAllOrderItem.jsp");
					failureView.forward(req, res);
				}		
			}
			
			
			if ("update".equals(action)) { // 來自update_ord_input.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String pur_no = req.getParameter("pur_no").trim();
					String com_no = req.getParameter("com_no").trim();
						
					Integer ord_price = null;
					try {
						ord_price = new Integer(req.getParameter("ord_price").trim());
					} catch (NumberFormatException e) {
						ord_price = null;
						errorMsgs.add("請輸入商品單價.");
					}
					
					Integer ord_qty = null;
					try {
						ord_qty = new Integer(req.getParameter("ord_qty").trim());
					} catch (NumberFormatException e) {
						ord_qty = null;
						errorMsgs.add("請輸入訂購數量.");
					}
					
					Integer return_qty = null;
					try {
						return_qty = new Integer(req.getParameter("return_qty").trim());
					} catch (NumberFormatException e) {
						return_qty = null;
						errorMsgs.add("請輸入退貨數量.");
					}
					
					Integer ship_status = null;
					try {
						ship_status = new Integer(req.getParameter("ship_status").trim());
					} catch (NumberFormatException e) {
						ship_status = null;
						errorMsgs.add("請選擇出貨狀態.");
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
						req.setAttribute("ordVO", ordVO); // 含有輸入格式錯誤的ordVO物件,也存入req	
						RequestDispatcher failureView = req
								.getRequestDispatcher("/supplier_end/ord/listAllOrderItem.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料*****************************************/
					OrdService ordSvc = new OrdService();
					ordVO = ordSvc.updateOrd(pur_no, com_no, ord_price, ord_qty, return_qty, ship_status);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("ordVO2", ordVO); // 資料庫update成功後,正確的的ordVO物件,存入req
					String url = "/supplier_end/ord/listAllOrderItem.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneOrd.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/ord/listAllOrderItem.jsp");
					failureView.forward(req, res);
				}
			}
			
			 if ("insert".equals(action)) { // 來自addOrd.jsp的請求  
                  
				 List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
				 req.setAttribute("errorMsgs", errorMsgs);
				
				 try {
						/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
						String pur_no = req.getParameter("pur_no").trim();
						String com_no = req.getParameter("com_no").trim();
						
						Integer ord_price = null;
						try {
							ord_price = new Integer(req.getParameter("ord_price").trim());
						} catch (NumberFormatException e) {
							ord_price = null;
							errorMsgs.add("訂單明細價格請填數字.");
						}
						
						Integer ord_qty = null;
						try {
							ord_qty = new Integer(req.getParameter("ord_qty").trim());
						} catch (NumberFormatException e) {
							ord_qty = null;
							errorMsgs.add("訂購數量請填數字.");
						}
						
						Integer return_qty = null;
						try {
							return_qty = new Integer(req.getParameter("return_qty").trim());
						} catch (NumberFormatException e) {
							return_qty = null;
							errorMsgs.add("退貨數量請填數字.");
						}
						
						Integer ship_status = null;
						try {
							ship_status = new Integer(req.getParameter("ship_status").trim());
						} catch (NumberFormatException e) {
							ship_status = null;
							errorMsgs.add("出貨狀態請填數字.");
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
							req.setAttribute("ordVO", ordVO); // 含有輸入格式錯誤的ordVO物件,也存入req
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back_end/ord/addOrd.jsp");
							failureView.forward(req, res);
							return; //程式中斷
						}
						
						/***************************2.開始新增資料***************************************/
						OrdService ordSvc = new OrdService();
						ordVO = ordSvc.addOrd(pur_no, com_no, ord_price, ord_qty, return_qty, ship_status);
						
						/***************************3.新增完成,準備轉交(Send the Success view)***********/
						String url = "/back_end/ord/listAllOrd.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllOrd.jsp
						successView.forward(req, res);				
						
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/ord/listAllOrd.jsp");
						failureView.forward(req, res);
					}			 
			 }
			  
			      if ("delete".equals(action)) { // 來自listAllOrd.jsp

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
			
					try {
						/***************************1.接收請求參數***************************************/
						String pur_no = req.getParameter("pur_no");
						String com_no = req.getParameter("com_no");
						/***************************2.開始刪除資料***************************************/
						OrdService ordSvc = new OrdService();
						ordSvc.deleteOrd(pur_no,com_no);
						
						/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
						String url = "/back_end/ord/listAllOrd.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
						successView.forward(req, res);
						
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add("刪除資料失敗:"+e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/ord/listAllOrd.jsp");
						failureView.forward(req, res);
					}
				}
			      
		      if ("listOrderItem_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
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
						System.out.println(map);
						if (req.getParameter("whichPage") == null){
							HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
							HashMap<String, String[]> map2 = new HashMap<String, String[]>();
							map2 = (HashMap<String, String[]>)map1.clone();
							session.setAttribute("map",map2);
							map = (HashMap<String, String[]>)req.getParameterMap();
						} 
						
						/***************************2.開始複合查詢***************************************/
						/*OrdService ordSvc = new OrdService();
						List<OrdVO> list  = ordSvc.getAll(map);*/
						/***************************3.單一廠商的複合查詢***************************************/
						SupVO supVO=(SupVO)session.getAttribute("supAccount");            //取得在seesion的廠商物件
						
						ComService comSvc = new ComService();
						List<ComVO> ComVOList  = comSvc.getAll();                      //複合查詢商品表格
						
						OrdService ordSvc = new OrdService();
						List<OrdVO> OrdVOList  = ordSvc.getAll(map);                         //定義一個List<OrdVO>物件，找出物件內的訂單編號
						
						List<OrdVO> OrdVOList_ToPrint  = new ArrayList<OrdVO>();          //自行定義一個List<OrdVO>物件，以放比對後的結果
						
						for(ComVO comVO_item : ComVOList) {                             
							if(supVO.getSup_no().equals(comVO_item.getSup_no())) {       //比對session的廠商編號是否有在商品表格裡的廠商編號，以找出商品表格的商品編號
								for(OrdVO OrdVOlist_item : OrdVOList) {                  
									if(comVO_item.getCom_no().equals(OrdVOlist_item.getCom_no())) //再透過商品表格的商品編號，比對訂單明細裡的商品編號，以找出登入廠商的訂單明細
										OrdVOList_ToPrint.add(OrdVOlist_item);
								}
							}
						}
						/***************************3.查詢完成,準備轉交(Send the Success view)************/
						req.setAttribute("listOrderItem_ByCompositeQuery", OrdVOList_ToPrint); // 資料庫取出的list物件,存入request
						RequestDispatcher successView = req.getRequestDispatcher("/supplier_end/ord/listAllOrderItem.jsp"); // 成功轉交listAllOrderItem.jsp
						successView.forward(req, res);
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/supplier_end/ord/listAllOrderItem.jsp");
						failureView.forward(req, res);
					}
				}	
		
	     }	
	
}

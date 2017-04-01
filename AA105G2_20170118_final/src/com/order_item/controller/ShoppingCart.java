package com.order_item.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.commodity.model.ComService;
import com.commodity.model.ComVO;
import com.order_item.model.*;

public class ShoppingCart extends HttpServlet { 
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException,IOException {
		res.setCharacterEncoding("UTF-8");
		 
		  
		HttpSession session = req.getSession();
		
		 //得到該頁面消費者點選的商品物件，以利回到商品細節頁面。
		ComVO comVO = new ComService().getOneCom(req.getParameter("com_no"));  
		req.setAttribute("comVO", comVO);
		
		//得到購物車的商品物件。
		Vector<OrdVO> buylist = (Vector<OrdVO>)session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
	
		if(!action.equals("CHECKOUT")) {
			
			if(action.equals("DELETE")) {
				String del = req.getParameter("del");
				int delCount = Integer.parseInt(del); 
				buylist.removeElementAt(delCount);
				
				//將刪除後的商品物件，再放回session，以顯示剩餘商品物件。
				session.setAttribute("shoppingcart", buylist);
				String url="/front_end/shoppingcar/ShoppingCart.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				return;

			}	
			
			 if(action.equals("ADD")) {
				boolean match = false;
				// 取得後來新增的商品
				OrdVO aCom = getOrdList(req);
				if(buylist == null){
					buylist = new Vector<OrdVO>();
					buylist.add(aCom);
				}
				else {
					for(int i=0 ;i<buylist.size();i++){
						OrdVO ordgoods = buylist.get(i);
						// 假若購物車的商品和新增的商品一樣時
						if(ordgoods.getCom_no().equals(aCom.getCom_no())){
							ordgoods.setOrd_qty(ordgoods.getOrd_qty()+aCom.getOrd_qty());
							buylist.setElementAt(ordgoods, i);
							match = true;					
						}
					}
					// 假若購物車的商品和新增的商品不一樣時，即新增一筆新的商品物件。
					if (!match)
						buylist.add(aCom);
				}
			}		
			 
			session.setAttribute("shoppingcart", buylist);
			try{
				// 得到修改的來源網頁在第幾頁
				String whichPage =(String) req.getParameter("whichPage"); 
				// 將得到修改的來源網頁在第幾頁之頁數，再送回去給欲修改的來源網頁。
				req.setAttribute("whichPage", whichPage);   
				// 得到修改的來源網頁之網址。
				String location = (String) req.getParameter("location");
				
				//當有來源網址時，即執行以下程式。
				if(location != null){
	                //因得到的網址跟頁數皆為字串，故以forward重導回去。   			
					String url =location+"?whichPage="+whichPage; 
					System.out.println(url);
					//因forward後的網頁，會自動加上專案路徑，故以substring擷取不必要的部分。
					RequestDispatcher rd = req.getRequestDispatcher(url.substring(8, url.length()));
					rd.forward(req, res);

				} else {
					
					String url="/front_end/shoppingcar/ShoppingPage.jsp";
					RequestDispatcher rd = req.getRequestDispatcher(url);
					rd.forward(req, res);
				}
			} catch(Exception ignored){
				ignored.printStackTrace();
			}
		}
		
		// 結帳，計算購物車商品總價
		else if (action.equals("CHECKOUT")) {
			int total = 0;
			for(int i=0;i<buylist.size();i++) {
				OrdVO ordlist = buylist.get(i);
				float price  = ordlist.getOrd_price();
				int quantity = ordlist.getOrd_qty();
				total +=(int)(price*quantity);
			}
			String amount = String.valueOf(total);
			session.setAttribute("amount", amount);
			String url = "/front_end/shoppingcar/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}
	
	private OrdVO getOrdList(HttpServletRequest req) {
		 String com_no = req.getParameter("com_no");
		 String ord_price = req.getParameter("ord_price");
		 String ord_qty = req.getParameter("ord_qty");
		 
		 OrdVO ordlist = new OrdVO();
		 ordlist.setCom_no(com_no);
		 ordlist.setOrd_price(new Integer(ord_price).intValue());
		 ordlist.setOrd_qty(new Integer(ord_qty).intValue());
		 return ordlist;
	}
}

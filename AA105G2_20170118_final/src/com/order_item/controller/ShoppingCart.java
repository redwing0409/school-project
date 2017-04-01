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
		
		 //�o��ӭ������O���I�諸�ӫ~����A�H�Q�^��ӫ~�Ӹ`�����C
		ComVO comVO = new ComService().getOneCom(req.getParameter("com_no"));  
		req.setAttribute("comVO", comVO);
		
		//�o���ʪ������ӫ~����C
		Vector<OrdVO> buylist = (Vector<OrdVO>)session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
	
		if(!action.equals("CHECKOUT")) {
			
			if(action.equals("DELETE")) {
				String del = req.getParameter("del");
				int delCount = Integer.parseInt(del); 
				buylist.removeElementAt(delCount);
				
				//�N�R���᪺�ӫ~����A�A��^session�A�H��ܳѾl�ӫ~����C
				session.setAttribute("shoppingcart", buylist);
				String url="/front_end/shoppingcar/ShoppingCart.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				return;

			}	
			
			 if(action.equals("ADD")) {
				boolean match = false;
				// ���o��ӷs�W���ӫ~
				OrdVO aCom = getOrdList(req);
				if(buylist == null){
					buylist = new Vector<OrdVO>();
					buylist.add(aCom);
				}
				else {
					for(int i=0 ;i<buylist.size();i++){
						OrdVO ordgoods = buylist.get(i);
						// ���Y�ʪ������ӫ~�M�s�W���ӫ~�@�ˮ�
						if(ordgoods.getCom_no().equals(aCom.getCom_no())){
							ordgoods.setOrd_qty(ordgoods.getOrd_qty()+aCom.getOrd_qty());
							buylist.setElementAt(ordgoods, i);
							match = true;					
						}
					}
					// ���Y�ʪ������ӫ~�M�s�W���ӫ~���@�ˮɡA�Y�s�W�@���s���ӫ~����C
					if (!match)
						buylist.add(aCom);
				}
			}		
			 
			session.setAttribute("shoppingcart", buylist);
			try{
				// �o��ק諸�ӷ������b�ĴX��
				String whichPage =(String) req.getParameter("whichPage"); 
				// �N�o��ק諸�ӷ������b�ĴX�������ơA�A�e�^�h�����ק諸�ӷ������C
				req.setAttribute("whichPage", whichPage);   
				// �o��ק諸�ӷ����������}�C
				String location = (String) req.getParameter("location");
				
				//���ӷ����}�ɡA�Y����H�U�{���C
				if(location != null){
	                //�]�o�쪺���}�򭶼ƬҬ��r��A�G�Hforward���ɦ^�h�C   			
					String url =location+"?whichPage="+whichPage; 
					System.out.println(url);
					//�]forward�᪺�����A�|�۰ʥ[�W�M�׸��|�A�G�Hsubstring�^�������n�������C
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
		
		// ���b�A�p���ʪ����ӫ~�`��
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

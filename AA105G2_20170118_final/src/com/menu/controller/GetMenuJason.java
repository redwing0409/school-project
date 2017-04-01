package com.menu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appointment.model.*;
import java.sql.Date;
import org.json.*;
import com.menu.model.*;



public class GetMenuJason extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String place_no=req.getParameter("place_no");
System.out.println(place_no);		
		MenuService menuSvc=new MenuService();
		List<MenuVO> menuList=new ArrayList<MenuVO>();
		menuList=menuSvc.getMenusByPlace(place_no);
//System.out.println(menuList);		
		JSONArray array = new JSONArray();
		for(MenuVO menuVO:menuList){
			JSONObject obj = new JSONObject();
			try{
				obj.put("menu_no", menuVO.getMenu_no());
				obj.put("place_no", menuVO.getPlace_no());
				obj.put("menu_name", menuVO.getMenu_name());
				obj.put("menu_note", menuVO.getMenu_note());
				obj.put("menu_price", menuVO.getMenu_price());
			}catch(Exception e){}
			array.put(obj);
		}
		res.setContentType("text/html");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		out.write(array.toString());
		System.out.println(array);
		out.flush();
		out.close();

	}	
}

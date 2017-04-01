package com.menu.controller;

import java.io.*;
import java.sql.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;

import com.menu.model.MenuService;
import com.menu.model.MenuVO;



public class ShowMenu_Pic extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		OutputStream out = res.getOutputStream();
		
		try {
			String menu_no = req.getParameter("menu_no");
			MenuService MenuSvc = new MenuService();
			MenuVO MenuVO = MenuSvc.getOneMenu(menu_no);
				
			byte[] buffer = MenuVO.getMenu_pic();
			out.write(buffer);
			
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/nodata/nopic.gif");
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			out.write(buffer);
			in.close();
		} 
	}
}

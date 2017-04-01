package com.adv.controller;

import java.io.*;
import java.sql.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;

import com.adv.model.AdvService;
import com.adv.model.AdvVO;


@WebServlet("/adv/ShowAdv_Pic.do")
public class ShowAdv_Pic extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		OutputStream out = res.getOutputStream();
		
		try {
			String adv_no = req.getParameter("adv_no");
			AdvService AdvSvc = new AdvService();
			AdvVO AdvVO = AdvSvc.getOneAdv(adv_no);
				
			byte[] buffer = AdvVO.getAdv_pic();
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

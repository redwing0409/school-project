package com.place.controller;

import java.io.*;
import java.sql.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;

import com.place.model.PlaceService;
import com.place.model.PlaceVO;


@WebServlet("/Place/ShowPlace_Pic.do")
public class ShowPlace_Pic extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		OutputStream out = res.getOutputStream();
		
		try {
			String place_no = req.getParameter("place_no");
			PlaceService placeSvc = new PlaceService();
			PlaceVO placeVO = placeSvc.getOnePlace(place_no);
			
			byte[] buffer = placeVO.getPlace_pic();
			out.write(buffer);
			
			
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/front_end/Place/images/notImg.jpg");
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			out.write(buffer);
			in.close();
		} 
	}

}

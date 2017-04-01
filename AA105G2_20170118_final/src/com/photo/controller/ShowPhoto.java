package com.photo.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photo.model.*;


@WebServlet("/photo/ShowPhoto.do")
public class ShowPhoto extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		OutputStream out = res.getOutputStream();
		
		try {
			String photo_no = req.getParameter("photo_no");
			PhotoService photoSvc = new PhotoService();
			PhotoVO photoVO = photoSvc.getOnePhoto(photo_no);
				
			byte[] buffer = photoVO.getPhoto_file();
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

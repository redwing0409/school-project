package com.groups.controller;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photo.model.*;


@WebServlet("/groups/ShowGroupsPhoto.do")
public class ShowGroupsPhoto extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		OutputStream out = res.getOutputStream();
		
		try {
			String groups_no = req.getParameter("groups_no");
			PhotoService photoSvc = new PhotoService();
			List<PhotoVO> list = photoSvc.getGroupsPhoto(groups_no);
			PhotoVO photoVO = list.get(0);
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

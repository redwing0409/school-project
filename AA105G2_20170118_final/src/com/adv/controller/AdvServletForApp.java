package com.adv.controller;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adv.model.AdvDAO;
import com.adv.model.AdvVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.menu.model.MenuVO;
import com.photo.controller.ImageUtil;
@SuppressWarnings("serial")
@WebServlet("/AdvServletForApp")
public class AdvServletForApp extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);                  //get json string 
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);                 //convert json to object
	
		AdvDAO advDAO = new AdvDAO();
		String action = jsonObject.get("action").getAsString();     // receive getAll action 

		if (action.equals("AdvTitleGetAllInfo")) {
			List<AdvVO> advs = advDAO.getAllForTitle();
			writeText(response, gson.toJson(advs));
		} 
		else if (action.equals("AdvPicGetAllImageNoText")) {                    // receive getImage action 
//			OutputStream os = response.getOutputStream();
//			int imageSize = jsonObject.get("imageSize").getAsInt();         //get imageSize
		
			List<AdvVO> advPicVO = advDAO.getAllForPic();
			
			writeText(response, gson.toJson(advPicVO));
	    }
		
		
		

	}//end of doPost
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		PlaceDAO placeDAO = new PlaceDAO();
//		List<PlaceVO> places = placeDAO.getAll();
//		writeText(response, new Gson().toJson(places));
		doPost(request, response);
	}

	private void writeText(HttpServletResponse response, String outText)	throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		// System.out.println("outText: " + outText);
		out.print(outText);
	}	
}

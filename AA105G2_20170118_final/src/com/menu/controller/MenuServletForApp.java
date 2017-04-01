package com.menu.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.menu.model.MenuDAO;
import com.menu.model.MenuVO;
import com.photo.controller.ImageUtil;

@SuppressWarnings("serial")
@WebServlet("/MenuServletForApp")
public class MenuServletForApp extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
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
		
		 MenuDAO menuDAO = new MenuDAO();
		 
		 String action = jsonObject.get("action").getAsString();
			if (action.equals("MenuGetAllInfo")) {
				List<MenuVO> menuVO = menuDAO.getMenuAllNoPicture();
				writeText(response, gson.toJson(menuVO));
			} 
		 
			else if (action.equals("getMenuImage")) {                    // receive getImage action 
				OutputStream os = response.getOutputStream();
				String place_no = jsonObject.get("place_no").getAsString();    
				int imageSize = jsonObject.get("imageSize").getAsInt();         //get imageSize
			
//				System.out.println("MenuServletForApp_place_no"+place_no);
				
				byte[] image = menuDAO.findByPrimaryKeyForPicture(place_no).getMenu_pic();   //get image bytes[]
				if (image != null) {
					image = ImageUtil.shrink(image, imageSize);
					response.setContentType("image/png");
					response.setContentLength(image.length);
				}
				os.write(image);
		    }
			else if (action.equals("MenuGetAllImageNoText")) {
				String place_no = jsonObject.get("place_no").getAsString();    
				List<MenuVO> menuVO = menuDAO.MenuGetAllImageNoTextByPlaceNo(place_no);
				writeText(response, gson.toJson(menuVO));
			} 
		
	}// end of doPost

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	private void writeText(HttpServletResponse response, String outText)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		// System.out.println("outText: " + outText);
		out.print(outText);
	}
}

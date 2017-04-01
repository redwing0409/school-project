package com.place.controller;

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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.photo.controller.ImageUtil;
import com.place.model.PlaceDAO;
import com.place.model.PlaceVO;

@SuppressWarnings("serial")
@WebServlet("/PlaceServletForApp")
public class PlaceServletForApp extends HttpServlet {
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
		
		PlaceDAO placeDAO = new PlaceDAO();
		String action = jsonObject.get("action").getAsString();     // receive getAll action 
//		System.out.println("action: " + action);

		if (action.equals("PlaceGetAllInfo")) {
			List<PlaceVO> places = placeDAO.getAllNoPicture();
			writeText(response, gson.toJson(places));
		} 
		else if (action.equals("getAllImage")) {                    // receive getAllImage action 
			OutputStream os = response.getOutputStream();
//			
			String place_no = jsonObject.get("place_no").getAsString();    
//			System.out.println("PlaceServletForApp_place_no="+place_no);
//			System.out.println("place_no_getAsString="+jsonObject.get("place_no").getAsString());
			int imageSize = jsonObject.get("imageSize").getAsInt();         //get imageSize
			
			
			byte[] image = placeDAO.findByPrimaryKey(place_no).getPlace_pic();   //get image bytes[]
			
//			System.out.println("CCCCCCCCCCCCCCCCCCCC"+imageSize+place_no);
			
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				response.setContentType("image/jpeg");
				response.setContentLength(image.length);
			}
			os.write(image);
		}
	
		else if (action.equals("PlaceAreaGetInfo")) {
			String placeArea = jsonObject.get("placeArea").getAsString();      //why can not use toString? 
//			System.out.println("placeArea: "+placeArea);
			List<PlaceVO> places = placeDAO.getAllNoPicturePlaceArea(placeArea);
			writeText(response, gson.toJson(places));
		} 
		else if (action.equals("PlaceTypeGetInfo")) {
			String placeType = jsonObject.get("placeType").getAsString();      //why can not use toString? 
//			System.out.println("placeType: "+placeType);
			List<PlaceVO> places = placeDAO.getAllNoPicturePlaceType(placeType);
			writeText(response, gson.toJson(places));
		} 
		else if (action.equals("PlaceAreaTypeGetInfo")) {
			String placeArea = jsonObject.get("placeArea").getAsString();
			String placeType = jsonObject.get("placeType").getAsString();      //why can not use toString? 
//			System.out.println("placeArea: "+placeArea);
//			System.out.println("placeType: "+placeType);
			List<PlaceVO> places = placeDAO.getAllNoPicturePlaceAreaType(placeArea,placeType);
			writeText(response, gson.toJson(places));
		} 
		
	} //end of doPost
	
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
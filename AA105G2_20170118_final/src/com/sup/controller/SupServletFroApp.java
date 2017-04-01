package com.sup.controller;
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
import com.sup.model.SupDAO;
import com.sup.model.SupVO;
@SuppressWarnings("serial")
@WebServlet("/SupServletFroApp")
public class SupServletFroApp extends HttpServlet {
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
		
		SupDAO supDAO = new SupDAO();
		String action = jsonObject.get("action").getAsString();
		 if (action.equals("SupplierGetAllInfo")) {
			 String sup_no = jsonObject.get("sup_no").getAsString(); 
			 
//	 System.out.println("SupServletFroApp:"+sup_no);	
	 
			 SupVO supVO = supDAO.findByPrimaryKey(sup_no); 
	
//	 System.out.println("SupServletFroApp:"+supVO);	
			 writeText(response, gson.toJson(supVO));   //response to client
		 }
		
	
	
	
	
	} //end of SupServletFroApp
	
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
}//end of SupServletFroApp

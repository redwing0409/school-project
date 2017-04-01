package com.groups.controller;

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
import com.groups.model.GroupsVO;
import com.groups.model.GroupsDAO;

@SuppressWarnings("serial")
@WebServlet("/GroupsServletForApp")
public class GroupsServletForApp extends HttpServlet {
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
		
		GroupsDAO groupsDAO = new GroupsDAO();
		
		String action = jsonObject.get("action").getAsString();
		 if (action.equals("GroupsGetAll")) {
			 String member_no = jsonObject.get("member_no").getAsString(); 
//			 String member_name = memberDAO.findByACCOUNT_NAME(member_acc,member_pw);  // According to member_acc&member_pw get member_name
			 List<GroupsVO> groupsVo = groupsDAO.GroupsGetAll(member_no); 
			
//				System.out.println("PhotoServletForApp->photoVO:"+groupsVo);
			 writeText(response, gson.toJson(groupsVo));   //response to client
		 }
	
	}
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

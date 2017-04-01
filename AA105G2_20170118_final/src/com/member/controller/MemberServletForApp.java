package com.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;

@SuppressWarnings("serial")
@WebServlet("/MemberServletForApp")
public class MemberServletForApp extends HttpServlet {
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
		
		 MemberDAO memberDAO = new MemberDAO();
		 
		 String action = jsonObject.get("action").getAsString();
		 if (action.equals("login")) {
			 String member_acc = jsonObject.get("member_acc").getAsString(); 
			 String member_pw = jsonObject.get("member_pw").getAsString();
//			 String member_name = memberDAO.findByACCOUNT_NAME(member_acc,member_pw);  // According to member_acc&member_pw get member_name
			 MemberVO memberVo = memberDAO.findByAccountPassword(member_acc,member_pw); 
//				System.out.println("memberVo:"+memberVo);
//				System.out.println("member_name:"+member_name);
				
			 writeText(response, gson.toJson(memberVo));   //response to client
		 }
		 else if (action.equals("getImage")) {                    // receive getImage action 
				OutputStream os = response.getOutputStream();
				String member_no = jsonObject.get("member_no").getAsString();      //why can not use toString? 
//				System.out.println("BBBBBBBBBBBBBBBB"+place_no);
				int imageSize = jsonObject.get("imageSize").getAsInt();         //get imageSize
				
				byte[] image = memberDAO.findByPrimaryKeyForPicture(member_no).getMember_pic();   //get image bytes[]
//				System.out.println("CCCCCCCCCCCCCCCCCCCC"+image+place_no);
				if (image != null) {
					image = ImageUtil.shrink(image, imageSize);
//					response.setContentType("image/jpeg");
					response.setContentType("image/png");
					response.setContentLength(image.length);
				}
				os.write(image);
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
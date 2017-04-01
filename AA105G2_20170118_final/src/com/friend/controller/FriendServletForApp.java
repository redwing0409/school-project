package com.friend.controller;
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

import com.friend.model.FriendDAO;
import com.friend.model.FriendVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.photo.controller.ImageUtil;

@SuppressWarnings("serial")
@WebServlet("/FriendsServletForApp")
public class FriendServletForApp extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);                  //get json string 
		}
		
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);                 //convert json to object
		
		FriendDAO friendDAO = new FriendDAO();
		MemberDAO memberDAO = new MemberDAO();
		
		String action = jsonObject.get("action").getAsString();
		 if (action.equals("FriendsGetAll")) {
//			 List<MemberVO> list = new ArrayList<MemberVO>();
		 String member_no = jsonObject.get("member_no").getAsString(); 
		 
			 List<FriendVO> friendsNo = friendDAO.findByPrimaryKey(member_no); 
		
			 
			 List<MemberVO> friendsNameList = new ArrayList<MemberVO>();
			 for(FriendVO FriendVOItem : friendsNo) {       //according to friendsNo to member table to find mamber_name
				 friendsNameList.add(memberDAO.findByPrimaryKey(FriendVOItem.getFriend_no()));
			    }
//		System.out.println("FriendsServletForApp_friendsNameList:"+friendsNameList);
			 writeText(response, gson.toJson(friendsNameList));   //response to client
		 } //end of  if (action.equals("FriendsGetAll")) 
		 else if (action.equals("getFriendImage")) {                    // receive getPhotoImage action 
				OutputStream os = response.getOutputStream();
				String member_no = jsonObject.get("member_no").getAsString();      //
//				System.out.println("BBBBBBBBBBBBBBBB"+place_no);
				int imageSize = jsonObject.get("imageSize").getAsInt();         //get imageSize
				
				byte[] image = memberDAO.findByPrimaryKey(member_no).getMember_pic();   //get image bytes[]
				if (image != null) {                                                                   //photo_status= 1  
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

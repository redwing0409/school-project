package com.photo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.photo.model.PhotoDAO;
import com.photo.model.PhotoVO;

@SuppressWarnings("serial")
@WebServlet("/PhotoServletForApp")
public class PhotoServletForApp extends HttpServlet {
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
	
		PhotoDAO photoDAO = new PhotoDAO();
		
		String action = jsonObject.get("action").getAsString();
		
		 if (action.equals("PhotoGetAllInfo")) {
			 String member_no = jsonObject.get("member_no").getAsString(); 
			 String groups_no = jsonObject.get("groups_no").getAsString();
//			 String member_name = memberDAO.findByACCOUNT_NAME(member_acc,member_pw);  // According to member_acc&member_pw get member_name
			 
			 List<PhotoVO> photoVO = photoDAO.findByMemberNo_ForAll(member_no,groups_no); 
			 
//			System.out.println("PhotoServletForApp->photoVO:"+photoVO);
//				System.out.println("member_name:"+member_name);
			 writeText(response, gson.toJson(photoVO));   //response to client
		 }
		 else if (action.equals("getPhotoImage")) {                    // receive getPhotoImage action 
				OutputStream os = response.getOutputStream();
				String photo_no = jsonObject.get("photo_no").getAsString();      //
//				System.out.println("BBBBBBBBBBBBBBBB"+place_no);
				int imageSize = jsonObject.get("imageSize").getAsInt();         //get imageSize
				
				byte[] image = photoDAO.findByPrimaryKey(photo_no).getPhoto_file();   //get image bytes[]
				if (image != null) {                                                                   //photo_status= 1  
					image = ImageUtil.shrink(image, imageSize);
//					response.setContentType("image/jpeg");
					response.setContentType("image/png");
					response.setContentLength(image.length);
				}
				os.write(image);
		 }else if (action.equals("PhotoInsert")) {
				String photoJson = jsonObject.get("photo").getAsString();
				PhotoVO photo = gson.fromJson(photoJson, PhotoVO.class);
				String imageBase64 = jsonObject.get("imageBase64").getAsString();
				byte[] image = Base64.getMimeDecoder().decode(imageBase64);;
				int count = 0;
				
				DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date_current=df.format(Calendar.getInstance().getTimeInMillis());
				java.sql.Timestamp sqlTimestamp=java.sql.Timestamp.valueOf(date_current);
				photo.setPhoto_createdate(sqlTimestamp);
				photo.setPhoto_file(image);
				
//				count = photoDAO.insert(spot, image);
				count = photoDAO.PhotoInsert_FromApp(photo);
//				count = photoDAO.PhotoInsert_FromApp(photo);
				writeText(response, String.valueOf(count));
		 }else if (action.equals("photoDelete")) {
				String photoJson = jsonObject.get("photo").getAsString();
				PhotoVO photo = gson.fromJson(photoJson, PhotoVO.class);
				int count = photoDAO.delete(photo.getPhoto_no());
				writeText(response, String.valueOf(count));
		 }
		
	
	} //end of doPost
	
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

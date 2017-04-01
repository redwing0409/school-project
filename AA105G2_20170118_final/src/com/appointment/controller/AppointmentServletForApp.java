package com.appointment.controller;

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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.photo.model.PhotoVO;
import com.appointment.model.AppointmentDAO;
import com.appointment.model.AppointmentVO;

import java.util.Base64;
import java.util.List;
@SuppressWarnings("serial")
@WebServlet("/AppointmentServletForApp")
public class AppointmentServletForApp extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		Gson gson = new Gson();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
//		Gson gsonForAppointmentList = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);                  //get json string 
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);                 //convert json to object
		
		 AppointmentDAO appointmentDAO = new AppointmentDAO();
		 
		 String action = jsonObject.get("action").getAsString();
		
		 if (action.equals("AppointmentGetInfo")) {
			 String place_no = jsonObject.get("place_no").getAsString(); 
//			 String member_name = memberDAO.findByACCOUNT_NAME(member_acc,member_pw);  // According to member_acc&member_pw get member_name
			 List<AppointmentVO> appointmentVO = appointmentDAO.getAllFromPlaceNo(place_no); 
//			System.out.println("AppointmentServletForApp_appointmentVO:"+appointmentVO);
			 
			 writeText(response, gson.toJson(appointmentVO));   //response to client
		 }else if (action.equals("AppointmentInsert")) {
			 String place_no = jsonObject.get("place_no").getAsString();
				String member_no = jsonObject.get("member_no").getAsString();
				String date_current = jsonObject.get("date_current").getAsString();
				String app_place_date = jsonObject.get("app_place_date").getAsString();
				int app_status = 1;
				int count = 0;
//    System.out.println("AppointmentServletForApp_AppointmentInsert:"+place_no+"-"+member_no+"-"+date_current+"-"+app_place_date);	
	     count = appointmentDAO.insertAppointmentFromAPP(place_no,member_no,date_current,app_place_date,app_status);
				writeText(response, String.valueOf(count));
		 }else if (action.equals("AppointmentListGetAll")) {
		    Gson gsonForAppointmentListGetAll = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			 String member_no = jsonObject.get("member_no").getAsString(); 
			 List<AppointmentVO> appointmentListVO = appointmentDAO.getAllFromMemberNo(member_no); 
//			System.out.println("appointmentListVO:"+appointmentListVO);
			 
			 writeText(response, gsonForAppointmentListGetAll.toJson(appointmentListVO));   //response to client
		 }else if (action.equals("AppointmentDelete")) {
			 String App_no = jsonObject.get("App_no").getAsString(); 
			 int count = 0;
			 System.out.println("AppointmentDelete_App_no:"+App_no);
				count = appointmentDAO.deleteByAppNO(App_no);
				writeText(response, String.valueOf(count));
		 }
		 
		
		
		
	}//end of doPost
	
	
	
	

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
}//end of 

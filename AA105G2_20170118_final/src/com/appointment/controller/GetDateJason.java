package com.appointment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appointment.model.*;
import java.sql.Date;
import org.json.*;


public class GetDateJason extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		String place_no=req.getParameter("place_no");
//System.out.println("place_no="+place_no);
		List<String> list=new ArrayList();
		AppointmentService appSvc=new AppointmentService();
		for(AppointmentVO appointmentVO:appSvc.getAll()){
//System.out.println(appointmentVO.getApp_place_date());
			if(place_no.equals(appointmentVO.getPlace_no())){
				if(appointmentVO.getApp_status()==1){
					list.add(appointmentVO.getApp_place_date().toString());
				}
			}
		}
System.out.println(list);
		JSONObject forbiddenDate=new JSONObject();
		try {
			forbiddenDate.put("forbiddenDate",list);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(forbiddenDate);
		out.close();
	}	
}

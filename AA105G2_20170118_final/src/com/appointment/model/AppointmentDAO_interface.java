package com.appointment.model;

import java.util.*;

public interface AppointmentDAO_interface {
          public void insert(AppointmentVO appointmentVO);
          public void update(AppointmentVO appointmentVO);
          public void delete(String app_no);
          public AppointmentVO findByPrimaryKey(String app_no);
          public List<AppointmentVO> getAll();
          public List<AppointmentVO> getOwn(String member_no);
          public List<AppointmentVO> getAppBySup(String sup_no);
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
          // for app
          public List<AppointmentVO> getAllFromPlaceNo(String place_no);
        public int insertAppointmentFromAPP(String place_no,String member_no,String date_current,String app_place_date,int app_status);
        public List<AppointmentVO> getAllFromMemberNo(String member_no);
        public int deleteByAppNO(String App_no);
          
          
}

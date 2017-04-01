package com.place.model;

import java.util.*;
import com.appointment.model.*;
import com.menu.model.*;

public interface PlaceDAO_interface {
          public void insert(PlaceVO placeVO);
          public void update(PlaceVO placeVO);
          public void delete(String place_no);
          public PlaceVO findByPrimaryKey(String place_no);
          public List<PlaceVO> findByPrimaryKeyType(Integer place_type);
          public List<PlaceVO> findByPrimaryKeyArea(String place_area);
          public List<PlaceVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
          public Set<MenuVO> getMenusByPlace_no(String place_no);
          public Set<AppointmentVO> getAppointmentByPlace_no(String place_no);
          
//for app
    	  public List<PlaceVO> getAllNoPicture();
    	  
    	  public List<PlaceVO> getAllNoPicturePlaceArea(String PlaceArea);
    	  
    	  public List<PlaceVO> getAllNoPicturePlaceType(String PlaceType);
    	  
    	  public List<PlaceVO> getAllNoPicturePlaceAreaType(String PlaceArea,String PlaceType);
          
}

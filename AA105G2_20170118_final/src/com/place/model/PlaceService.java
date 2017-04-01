package com.place.model;

import java.util.List;
import java.util.Set;

import com.menu.model.*;
import com.appointment.model.*;

public class PlaceService {

	private PlaceDAO_interface dao;

	public PlaceService() {
		dao = new PlaceJNDIDAO();
	}

	public PlaceVO addPlace(String sup_no, Integer place_type,
			String place_name, String place_area, String place_adds, byte[] place_pic, 
			String place_note, Integer place_status) {

		PlaceVO placeVO = new PlaceVO();

		placeVO.setSup_no(sup_no);
		placeVO.setPlace_type(place_type);
		placeVO.setPlace_name(place_name);
		placeVO.setPlace_area(place_area);
		placeVO.setPlace_adds(place_adds);
		placeVO.setPlace_pic(place_pic);
		placeVO.setPlace_note(place_note);
		placeVO.setPlace_status(place_status);
		dao.insert(placeVO);

		return placeVO;
	}

	public PlaceVO updatePlace(String place_no, String sup_no, Integer place_type,
			String place_name, String place_area, String place_adds, byte[] place_pic, 
			String place_note, Integer place_status) {

		PlaceVO placeVO = new PlaceVO();

		placeVO.setPlace_no(place_no);
		placeVO.setSup_no(sup_no);
		placeVO.setPlace_type(place_type);
		placeVO.setPlace_name(place_name);
		placeVO.setPlace_area(place_area);
		placeVO.setPlace_adds(place_adds);
		placeVO.setPlace_pic(place_pic);
		placeVO.setPlace_note(place_note);
		placeVO.setPlace_status(place_status);
		dao.update(placeVO);

		return placeVO;
	}

	public void deletePlace(String place_no) {
		dao.delete(place_no);
	}

	public PlaceVO getOnePlace(String place_no) {
		return dao.findByPrimaryKey(place_no);
	}
	public List<PlaceVO> getOnePlaceType(Integer place_type) {
		return dao.findByPrimaryKeyType(place_type);
	}
	public List<PlaceVO> getOnePlaceArea(String place_area) {
		return dao.findByPrimaryKeyArea(place_area);
	}	

	public List<PlaceVO> getAll() {
		return dao.getAll();
	}
	public Set<MenuVO> getMenusByPlace_no(String place_no) {
		return dao.getMenusByPlace_no(place_no);
	}
	
	public Set<AppointmentVO> getAppointmentByPlace_no(String place_no) {
		return dao.getAppointmentByPlace_no(place_no);
	}
	
}

package com.appointment.model;

import java.sql.Date;
import java.util.List;

public class AppointmentService {

	private AppointmentDAO_interface dao;

	public AppointmentService() {
		dao = new AppointmentDAO();
	}

	public AppointmentVO addAppointment(String place_no, String member_no,
			Date app_date, Date app_place_date, Integer app_status) {

		AppointmentVO appointmentVO = new AppointmentVO();
		
		appointmentVO.setPlace_no(place_no);
		appointmentVO.setMember_no(member_no);
		appointmentVO.setApp_date(app_date);
		appointmentVO.setApp_place_date(app_place_date);
		appointmentVO.setApp_status(app_status);

		dao.insert(appointmentVO);

		return appointmentVO;
	}

	public AppointmentVO updateAppointment(String app_no, String place_no, String member_no,
			java.sql.Date app_date, java.sql.Date app_place_date, Integer app_status) {

		AppointmentVO appointmentVO = new AppointmentVO();

		appointmentVO.setApp_no(app_no);
		appointmentVO.setPlace_no(place_no);
		appointmentVO.setMember_no(member_no);
		appointmentVO.setApp_date(app_date);
		appointmentVO.setApp_place_date(app_place_date);
		appointmentVO.setApp_status(app_status);

		dao.update(appointmentVO);

		return appointmentVO;
	}

	public void deleteAppointment(String app_no) {
		dao.delete(app_no);
	}

	public AppointmentVO getOneAppointment(String app_no) {
		return dao.findByPrimaryKey(app_no);
	}

	public List<AppointmentVO> getAll() {
		return dao.getAll();
	}
	public List<AppointmentVO> getOwn(String member_no) {
		return dao.getOwn(member_no);
	}	
	public List<AppointmentVO> getAppBySup(String sup_no) {
		return dao.getAppBySup(sup_no);
	}	
}

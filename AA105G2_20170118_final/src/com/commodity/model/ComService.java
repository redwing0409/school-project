package com.commodity.model;

import java.util.List;
import java.util.Map;

public class ComService {

	private ComDAO_interface dao;

	public ComService() {
		dao = new ComDAO();
	}

	public ComVO addCom(String sup_no, Integer pcm_no, String com_name, String com_desc,
			             Integer com_price, Integer com_status, java.sql.Timestamp com_shelf_date,
			             java.sql.Timestamp com_off_date, String com_note,byte[] com_pic) {
			
		ComVO comVO = new ComVO();
		comVO.setSup_no(sup_no);
		comVO.setPcm_no(pcm_no);
		comVO.setCom_name(com_name);
		comVO.setCom_desc(com_desc);
		comVO.setCom_price(com_price);
		comVO.setCom_status(com_status);
		comVO.setCom_shelf_date(com_shelf_date);
		comVO.setCom_off_date(com_off_date);
		comVO.setCom_note(com_note);
		comVO.setCom_pic(com_pic);
		dao.insert(comVO);
		
		return comVO;
	}
		
	public ComVO updateCom(String com_no, String sup_no, Integer pcm_no, String com_name, String com_desc,
            Integer com_price, Integer com_status, java.sql.Timestamp com_shelf_date,
            java.sql.Timestamp com_off_date, String com_note, byte[] com_pic) {

		ComVO comVO = new ComVO();
		comVO.setCom_no(com_no);
		comVO.setSup_no(sup_no);
		comVO.setPcm_no(pcm_no);
		comVO.setCom_name(com_name);
		comVO.setCom_desc(com_desc);
		comVO.setCom_price(com_price);
		comVO.setCom_status(com_status);
		comVO.setCom_shelf_date(com_shelf_date);
		comVO.setCom_off_date(com_off_date);
		comVO.setCom_note(com_note);
		comVO.setCom_pic(com_pic);
		dao.update(comVO);
		
		return comVO;
	}

	public void deleteCom(String com_no) {
		dao.delete(com_no);
	}

	public ComVO getOneCom(String com_no) {
		return dao.findByPrimaryKey(com_no);
	}

	public List<ComVO> getAll() {
		return dao.getAll();
	}
	
	public List<ComVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}

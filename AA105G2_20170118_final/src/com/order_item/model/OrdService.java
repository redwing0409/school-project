package com.order_item.model;

import java.sql.Connection;
import java.util.*;



public class OrdService {
	
	private OrdDAO_interface dao;

	public OrdService() {
		dao = new OrdDAO();
	}

	public OrdVO addOrd(String pur_no, String com_no, Integer ord_price, Integer ord_qty, 
			Integer return_qty, Integer ship_status) {
			
		OrdVO ordVO = new OrdVO();
		ordVO.setPur_no(pur_no);
		ordVO.setCom_no(com_no);
		ordVO.setOrd_price(ord_price);
		ordVO.setOrd_qty(ord_qty);
		ordVO.setReturn_qty(return_qty);
		ordVO.setShip_status(ship_status);
		dao.insert(ordVO);
		return ordVO;
	}
	
	public OrdVO updateOrd(String pur_no, String com_no, Integer ord_price, Integer ord_qty, 
			Integer return_qty, Integer ship_status) {

		OrdVO ordVO = new OrdVO();
		ordVO.setPur_no(pur_no);
		ordVO.setCom_no(com_no);
		ordVO.setOrd_price(ord_price);
		ordVO.setOrd_qty(ord_qty);
		ordVO.setReturn_qty(return_qty);
		ordVO.setShip_status(ship_status);
		dao.update(ordVO);
		
		return ordVO;
	}

	public void deleteOrd(String pur_no, String com_no) {
		dao.delete(pur_no, com_no);
	}

	public List<OrdVO> getOneOrd(String pur_no) {
		return dao.findByPrimaryKey(pur_no);
	}
	
	public OrdVO getOneKeyOrd(String pur_no) {
		return dao.findByKey(pur_no);
	}
	
	public OrdVO getCompositeOrd(String pur_no, String com_no) {
		return dao.get_composite_stmt(pur_no, com_no);
	}

	public List<OrdVO> getAll() {
		return dao.getAll();
	}
	
	public List<OrdVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}

package com.sup.model;

import java.util.List;
import java.util.Set;

import com.place.model.PlaceVO;
import com.adv.model.*;

public class SupService {

	private SupDAO_interface dao;

	public SupService() {
		dao = new SupJNDIDAO();
	}

	public SupVO addSup(String sup_name, String sup_acct, String sup_pwd,Integer sup_id,String sup_con,Integer sup_telcode,Integer sup_tel,Integer sup_tax,Integer sup_adcode,String sup_addr,String sup_note,String sup_type
			) {

		SupVO SupVO = new SupVO();

		SupVO.setSup_name(sup_name);
		SupVO.setSup_acct(sup_acct);
		SupVO.setSup_pwd(sup_pwd);
		SupVO.setSup_id(sup_id);
		SupVO.setSup_con(sup_con);
		SupVO.setSup_telcode(sup_telcode);
		SupVO.setSup_tel(sup_tel);
		SupVO.setSup_tax(sup_tax);
		SupVO.setSup_adcode(sup_adcode);
		SupVO.setSup_addr(sup_addr);
		SupVO.setSup_note(sup_note);
		SupVO.setSup_type(sup_type);
		dao.insert(SupVO);

		return SupVO;
	}

	public SupVO updateSup(String sup_no,String sup_name, String sup_acct, 
			String sup_pwd,Integer sup_id,String sup_con,Integer sup_telcode,
			Integer sup_tel,Integer sup_tax,Integer sup_adcode,String sup_addr,
			String sup_note,String sup_type
			) {

		SupVO SupVO = new SupVO();
        
		SupVO.setSup_no(sup_no);
		SupVO.setSup_name(sup_name);
		SupVO.setSup_acct(sup_acct);
		SupVO.setSup_pwd(sup_pwd);
		SupVO.setSup_id(sup_id);
		SupVO.setSup_con(sup_con);
		SupVO.setSup_telcode(sup_telcode);
		SupVO.setSup_tel(sup_tel);
		SupVO.setSup_tax(sup_tax);
		SupVO.setSup_adcode(sup_adcode);
		SupVO.setSup_addr(sup_addr);
		SupVO.setSup_note(sup_note);
		SupVO.setSup_type(sup_type);
		dao.update(SupVO);

		return SupVO;
	}

	public void deleteSup(String sup_no) {
		dao.delete(sup_no);
	}

	public SupVO getOneSup(String sup_no) {
		return dao.findByPrimaryKey(sup_no);
	}

	public List<SupVO> getAll() {
		return dao.getAll();
	}
	public List<SupVO> getAllCheck() {
		return dao.getAllCheck();
	}
	
	public List<SupVO> getAllUnCheck() {
		return dao.getAllUnCheck();
	}

	public Set<PlaceVO> getPlaceBySup_no(String sup_no) {
		return dao.getPlaceBySup_no(sup_no);
	}
	
	public Set<AdvVO> getAdvBySup_no(String sup_no) {
		return dao.getAdvBySup_no(sup_no);
	}
}

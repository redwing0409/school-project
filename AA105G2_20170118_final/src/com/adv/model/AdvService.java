package com.adv.model;

import java.util.List;

import com.adv.model.AdvDAO_interface;
import com.adv.model.AdvJNDIDAO;
import com.adv.model.AdvVO;

public class AdvService {

	private AdvDAO_interface dao;

	public AdvService() {
		dao = new AdvJNDIDAO();
	}

	public AdvVO addAdv(String sup_no, byte[] adv_pic, String adv_note) 
			 throws Throwable{
		AdvVO AdvVO = new AdvVO();

		AdvVO.setSup_no(sup_no);
		AdvVO.setAdv_pic(adv_pic);
		AdvVO.setAdv_note(adv_note);
		dao.insert(AdvVO);

		return AdvVO;
	}

	public AdvVO updateAdv(String adv_no, String sup_no, byte[] adv_pic, String adv_note)  throws Throwable{

		AdvVO AdvVO = new AdvVO();
		AdvVO.setAdv_no(adv_no);
		AdvVO.setSup_no(sup_no);
		AdvVO.setAdv_pic(adv_pic);
		AdvVO.setAdv_note(adv_note);
		
		dao.update(AdvVO);

		return AdvVO;
	}

	public void deleteAdv(String adv_no) {
		dao.delete(adv_no);
	}

	public AdvVO getOneAdv(String adv_no) {
		return dao.findByPrimaryKey(adv_no);
	}
//	public List<AdvVO> getOwnAdv(String sup_no) {
//		return dao.findBySup(sup_no);
//	}	

	public List<AdvVO> getAll() {
		return dao.getAll();
	}
}


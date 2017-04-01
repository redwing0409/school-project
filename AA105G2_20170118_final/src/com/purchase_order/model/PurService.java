package com.purchase_order.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.order_item.model.OrdVO;

public class PurService {
	
	private PurDAO_interface dao;
	
	public PurService() {
		dao = new PurDAO();
	}

	public PurVO addPurOrd(String member_no, java.sql.Timestamp pur_date, java.sql.Timestamp pur_money, 
							java.sql.Timestamp pur_product, java.sql.Timestamp pur_close, Integer pur_status,
							Integer pur_sum, String pur_name, String pur_add, String pur_tel, String pur_memo){
		   
		   PurVO purVO = new PurVO();
		   purVO.setMember_no(member_no);
		   purVO.setPur_date(pur_date);
		   purVO.setPur_money(pur_money);
		   purVO.setPur_product(pur_product);
		   purVO.setPur_close(pur_close);
		   purVO.setPur_status(pur_status);
		   purVO.setPur_sum(pur_sum);
		   purVO.setPur_name(pur_name);
		   purVO.setPur_add(pur_add);
		   purVO.setPur_tel(pur_tel);
		   purVO.setPur_memo(pur_memo);
		   dao.insert(purVO);
		   return purVO;
	}
	
	public PurVO addPurOrd2(String member_no, java.sql.Timestamp pur_date, java.sql.Timestamp pur_money, 
			java.sql.Timestamp pur_product, java.sql.Timestamp pur_close, Integer pur_status,
			Integer pur_sum, String pur_name, String pur_add, String pur_tel, String pur_memo,List<OrdVO> list){

			PurVO purVO = new PurVO();
			purVO.setMember_no(member_no);
			purVO.setPur_date(pur_date);
			purVO.setPur_money(pur_money);
			purVO.setPur_product(pur_product);
			purVO.setPur_close(pur_close);
			purVO.setPur_status(pur_status);
			purVO.setPur_sum(pur_sum);
			purVO.setPur_name(pur_name);
			purVO.setPur_add(pur_add);
			purVO.setPur_tel(pur_tel);
			purVO.setPur_memo(pur_memo);
						
			dao.insertWithOrditems(purVO,list);
		    return purVO;
}
	
	public PurVO updatePurOrd(String Pur_no,String member_no, java.sql.Timestamp pur_date, java.sql.Timestamp pur_money, 
			java.sql.Timestamp pur_product, java.sql.Timestamp pur_close, Integer pur_status,
			Integer pur_sum, String pur_name, String pur_add, String pur_tel, String pur_memo) {
		
		   PurVO purVO = new PurVO();
		   purVO.setPur_no(Pur_no);
		   purVO.setMember_no(member_no);
		   purVO.setPur_date(pur_date);
		   purVO.setPur_money(pur_money);
		   purVO.setPur_product(pur_product);
		   purVO.setPur_close(pur_close);
		   purVO.setPur_status(pur_status);
		   purVO.setPur_sum(pur_sum);
		   purVO.setPur_name(pur_name);
		   purVO.setPur_add(pur_add);
		   purVO.setPur_tel(pur_tel);
		   purVO.setPur_memo(pur_memo);
		   dao.update(purVO);
		   return purVO;
	}
	
	public void deletePurOrd(String pur_no) {
		dao.delete(pur_no);
	}
	
	public PurVO getOnePurOrd(String pur_no) {
		return dao.findByPrimaryKey(pur_no);
	}
	
	public List<PurVO> getAll() {
       return dao.getAll();		
	}
	
	public Set<OrdVO> getOrdItemByPurno(String pur_no) {
	       return dao.getOrdItemByPurno(pur_no);		
	}
	
	public List<PurVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}

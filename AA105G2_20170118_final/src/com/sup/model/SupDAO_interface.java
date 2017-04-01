package com.sup.model;

import java.util.*;

import com.adv.model.AdvVO;
import com.place.model.PlaceVO;
//import com.adv.model.*;

public interface SupDAO_interface {
          public void insert(SupVO SupVO);
          public void update(SupVO SupVO);
          public void delete(String sup_no);
          public SupVO findByPrimaryKey(String sup_no);
          public List<SupVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<SupVO> getAll(Map<String, String[]> map); 		
		public Set<PlaceVO> getPlaceBySup_no(String sup_no);
		
//		public Set<AdvVO> getAdvBySup_no(String sup_no);
        public List<SupVO> getAllCheck();
        public List<SupVO> getAllUnCheck();
		public Set<AdvVO> getAdvBySup_no(String sup_no);
		
}

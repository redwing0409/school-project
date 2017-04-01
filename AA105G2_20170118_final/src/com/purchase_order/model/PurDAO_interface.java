package com.purchase_order.model;

import java.util.*;
import com.order_item.model.OrdVO;

public interface PurDAO_interface {
	 public void insert(PurVO purVO);
     public void update(PurVO purVO);
     public void delete(String com_no);
     public PurVO findByPrimaryKey(String purVO);
     public List<PurVO> getAll();
     
     //查詢某筆訂單的明細(一對多)(回傳 Set)
     public Set<OrdVO> getOrdItemByPurno(String pur_no);
     //同時新增訂單與訂單明細
     public void insertWithOrditems(PurVO purVO, List<OrdVO> list);
     //萬用複合查詢(傳入參數型態Map)(回傳 List)
     public List<PurVO> getAll(Map<String, String[]> map); 
}

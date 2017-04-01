package com.purchase_order.model;

import java.util.*;
import com.order_item.model.OrdVO;

public interface PurDAO_interface {
	 public void insert(PurVO purVO);
     public void update(PurVO purVO);
     public void delete(String com_no);
     public PurVO findByPrimaryKey(String purVO);
     public List<PurVO> getAll();
     
     //�d�߬Y���q�檺����(�@��h)(�^�� Set)
     public Set<OrdVO> getOrdItemByPurno(String pur_no);
     //�P�ɷs�W�q��P�q�����
     public void insertWithOrditems(PurVO purVO, List<OrdVO> list);
     //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
     public List<PurVO> getAll(Map<String, String[]> map); 
}

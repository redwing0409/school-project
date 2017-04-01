package com.commodity.model;

import java.util.*;


public interface ComDAO_interface {
	 public void insert(ComVO comVO);
     public void update(ComVO comVO);
     public void delete(String com_no);
     public ComVO findByPrimaryKey(String com_no);
     public List<ComVO> getAll();
     
   //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
     public List<ComVO> getAll(Map<String, String[]> map); 

}

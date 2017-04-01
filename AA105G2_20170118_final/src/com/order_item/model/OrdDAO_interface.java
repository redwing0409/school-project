package com.order_item.model;

import java.util.*;

public interface OrdDAO_interface {
	 public void insert(OrdVO ordVO);
	 public void update(OrdVO ordVO);
	 public void delete(String pur_no,String com_no);
	 //�d�ߤ@�q�渹�X�U���X�Ӱӫ~
	 public List<OrdVO> findByPrimaryKey(String pur_no);
	 //�d�ߤ@���q��
	 public OrdVO findByKey(String pur_no);
	 
	 public List<OrdVO> getAll();
	 
	 public OrdVO get_composite_stmt(String pur_no, String com_no);
	 
	 //�P�ɷs�W�q��P�q�����
     public void insert2 (OrdVO ordVO , java.sql.Connection con);
     
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
     public List<OrdVO> getAll(Map<String, String[]> map); 
}

package com.order_item.model;

import java.util.*;

public interface OrdDAO_interface {
	 public void insert(OrdVO ordVO);
	 public void update(OrdVO ordVO);
	 public void delete(String pur_no,String com_no);
	 //查詢一訂單號碼下有幾個商品
	 public List<OrdVO> findByPrimaryKey(String pur_no);
	 //查詢一筆訂單
	 public OrdVO findByKey(String pur_no);
	 
	 public List<OrdVO> getAll();
	 
	 public OrdVO get_composite_stmt(String pur_no, String com_no);
	 
	 //同時新增訂單與訂單明細
     public void insert2 (OrdVO ordVO , java.sql.Connection con);
     
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
     public List<OrdVO> getAll(Map<String, String[]> map); 
}

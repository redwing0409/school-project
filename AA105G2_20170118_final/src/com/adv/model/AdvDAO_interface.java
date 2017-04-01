package com.adv.model;

import java.util.List;

public interface AdvDAO_interface {
    public void insert(AdvVO advVO);
    public void update(AdvVO advVO);
    public void delete(String adv_no);
    public AdvVO findByPrimaryKey(String adv_no);
    public List<AdvVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<AdvVO> getAll(Map<String, String[]> map); 
    //For APP
    public List<AdvVO> getAllForTitle();
    public List<AdvVO> getAllForPic();
}
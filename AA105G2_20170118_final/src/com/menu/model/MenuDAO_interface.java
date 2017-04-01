package com.menu.model;

import java.util.*;

public interface MenuDAO_interface {
          public void insert(MenuVO menuVO);
          public void update(MenuVO menuVO);
          public void delete(String menu_no);
          public MenuVO findByPrimaryKey(String menu_no);
          public List<MenuVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<MenuVO> getAll(Map<String, String[]> map);
          public List<MenuVO> getMenusBySup(String sup_no);
          public List<MenuVO> getMenusByPlace(String menu_no);
          
          //for App
          public MenuVO findByPrimaryKeyForPicture(String menu_no);
          public List<MenuVO> getMenuAllNoPicture();
          public List<MenuVO> MenuGetAllImageNoTextByPlaceNo(String place_no);
          
}

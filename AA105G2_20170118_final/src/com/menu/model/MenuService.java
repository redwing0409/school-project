package com.menu.model;

import java.util.List;


public class MenuService {

	private MenuDAO_interface dao;

	public MenuService() {
		dao = new MenuJNDIDAO();
	}

	public MenuVO addMenu(String place_no, String menu_name, String menu_note,
			byte[] menu_pic, Integer menu_price) {

		MenuVO MenuVO = new MenuVO();

		MenuVO.setPlace_no(place_no);
		MenuVO.setMenu_name(menu_name);
		MenuVO.setMenu_note(menu_note);
		MenuVO.setMenu_pic(menu_pic);
		MenuVO.setMenu_price(menu_price);
		dao.insert(MenuVO);

		return MenuVO;
	}

	public MenuVO updateMenu(String menu_no, String place_no, String menu_name, String menu_note,
			byte[] menu_pic, Integer menu_price) {

		MenuVO MenuVO = new MenuVO();
		System.out.println("menu_no =" + menu_no);
		MenuVO.setMenu_no(menu_no);
		MenuVO.setPlace_no(place_no);
		MenuVO.setMenu_name(menu_name);
		MenuVO.setMenu_note(menu_note);
		MenuVO.setMenu_pic(menu_pic);
		MenuVO.setMenu_price(menu_price);
		dao.update(MenuVO);

		return MenuVO;
	}

	public void deleteMenu(String menu_no) {
		dao.delete(menu_no);
	}

	public MenuVO getOneMenu(String menu_no) {
		return dao.findByPrimaryKey(menu_no);
	}

	public List<MenuVO> getAll() {
		return dao.getAll();
	}
	
	public List<MenuVO> getMenusBySup(String sup_no){
		return dao.getMenusBySup(sup_no);
	}
	public List<MenuVO> getMenusByPlace(String menu_no){
		return dao.getMenusByPlace(menu_no);
	}	
}

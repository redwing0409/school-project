package com.groups_list.model;

import java.util.List;


public class Groups_listService {

	private Groups_ListDAO_interface dao;
	
	public Groups_listService(){
		dao = new Groups_ListDAO();
	}
	
	public Groups_ListVO addGroups_list(String groups_no, String member_no) {

		Groups_ListVO groups_ListVO = new Groups_ListVO();
		groups_ListVO.setGroups_no(groups_no);
		groups_ListVO.setMember_no(member_no);
		dao.insert(groups_ListVO);

		return groups_ListVO;
	}


	public void deleteGroups_list(String gorups_no,String member_no) {
		dao.delete(gorups_no, member_no);
	}

	public List<Groups_ListVO> getOneByGroupsNo(String groups_no) {
		return dao.findByGroupsNo(groups_no);
	}
	
	public List<Groups_ListVO> getOneByMemberNo(String member_no) {
		return dao.findByMemberNo(member_no);
	}

	public List<Groups_ListVO> getAll() {
		return dao.getAll();
	}
	
}

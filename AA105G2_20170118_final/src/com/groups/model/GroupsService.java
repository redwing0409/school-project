package com.groups.model;

import java.sql.Date;
import java.util.List;


public class GroupsService {
	private GroupsDAO_interface dao;
	
	public GroupsService() {
		dao = new GroupsDAO();
	}
	
	public GroupsVO addGroups(String groups_owner, String groups_title, Date groups_time ) {

		GroupsVO groupsVO = new GroupsVO();
		groupsVO.setGroups_owner(groups_owner);
		groupsVO.setGroups_title(groups_title);
		groupsVO.setGroups_time(groups_time);
		dao.insert(groupsVO);

		return groupsVO;
	}

	public GroupsVO updateGroups(String groups_no, String groups_owner, String groups_title, Date groups_time) {

		GroupsVO groupsVO = new GroupsVO();
		groupsVO.setGroups_no(groups_no);
		groupsVO.setGroups_owner(groups_owner);
		groupsVO.setGroups_title(groups_title);
		groupsVO.setGroups_time(groups_time);
		dao.update(groupsVO);
		
		return groupsVO;
	}

	public void deleteGroups(String groups_no) {
		dao.delete(groups_no);
	}

	public GroupsVO getOneGroups(String groups_no) {
		return dao.findByPrimaryKey(groups_no);
	}

	public List<GroupsVO> getAllBack() {
		return dao.getAllBack();
	}
	
	public List<GroupsVO> getAllFront() {
		return dao.getAllFront();
	}

}

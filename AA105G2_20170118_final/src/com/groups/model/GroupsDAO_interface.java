package com.groups.model;

import java.util.*;

public interface GroupsDAO_interface {
	public void insert(GroupsVO groupsVO);
	public void update(GroupsVO groupsVO);
	public void delete(String groups_no);
	public GroupsVO findByPrimaryKey(String groups_no);
	public List<GroupsVO> getAllBack();
	public List<GroupsVO> getAllFront();
	//for app
	
	public List<GroupsVO> GroupsGetAll(String member_no);		
	
}

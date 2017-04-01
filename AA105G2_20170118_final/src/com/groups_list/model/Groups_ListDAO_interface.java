package com.groups_list.model;

import java.util.*;


public interface Groups_ListDAO_interface {
	public void insert(Groups_ListVO groups_ListVO);
	public void delete(String gorups_no,String member_no);
	public List<Groups_ListVO> findByGroupsNo(String gorups_no);
	public List<Groups_ListVO> findByMemberNo(String member_no);
	public List<Groups_ListVO> getAll();
}

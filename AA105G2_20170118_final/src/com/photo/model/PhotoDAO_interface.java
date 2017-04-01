package com.photo.model;

import java.util.*;


public interface PhotoDAO_interface {
	public void insert(PhotoVO photoVO);
	public void update(PhotoVO photoVO);
	public int delete(String photo_no);
	public PhotoVO findByPrimaryKey(String photo_no);
	public List<PhotoVO> findPhotoByGroupsNo(String groups_no);
	public List<PhotoVO> getAllBack();
	public List<PhotoVO> getAllFront();
	
	//for APP
	public List<PhotoVO> findByMemberNo_ForAll(String member_no,String groups_no);
	public int PhotoInsert_FromApp(PhotoVO photoVO);
	
}

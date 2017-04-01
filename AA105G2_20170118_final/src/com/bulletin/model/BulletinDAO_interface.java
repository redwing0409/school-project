package com.bulletin.model;

import java.util.*;


import java.sql.*;

public interface BulletinDAO_interface {
	public void insert(BulletinVO bulletinVO);
	public void update(BulletinVO bulletinVO);
	public void delete(String bulletin_no);
	public BulletinVO findByPrimaryKey(String bulletin_no);
	public List<BulletinVO> findBulletinByGroupsNo(String groups_no);
	public List<BulletinVO> getAllBack();
	public List<BulletinVO> getAllFront();
}

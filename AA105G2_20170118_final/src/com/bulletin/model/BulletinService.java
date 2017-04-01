package com.bulletin.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.groups.model.GroupsVO;


public class BulletinService {
	private BulletinDAO_interface dao;
	
	public BulletinService() {
		dao = new BulletinDAO();
	}
	
	public BulletinVO addBulletin(String groups_no, String member_no, String bulletin_content, Timestamp bulletin_time) {

		BulletinVO bulletinVO = new BulletinVO();
		bulletinVO.setGroups_no(groups_no);
		bulletinVO.setMember_no(member_no);
		bulletinVO.setBulletin_content(bulletin_content);
		bulletinVO.setBulletin_time(bulletin_time);
		dao.insert(bulletinVO);

		return bulletinVO;
	}

	public BulletinVO updateBulletin(String bulletin_no, String groups_no, String member_no, String bulletin_content, Timestamp bulletin_time) {

		BulletinVO bulletinVO = new BulletinVO();
		bulletinVO.setBulletin_no(bulletin_no);
		bulletinVO.setGroups_no(groups_no);
		bulletinVO.setMember_no(member_no);
		bulletinVO.setBulletin_content(bulletin_content);
		bulletinVO.setBulletin_time(bulletin_time);
		dao.update(bulletinVO);
		
		return bulletinVO;
	}

	public void deleteBulletin(String bulletin_no) {
		dao.delete(bulletin_no);
	}
	
	public BulletinVO getOneBulletin(String bulletin_no) {
		return dao.findByPrimaryKey(bulletin_no);
	}

	public List<BulletinVO> getGroupsBulletin(String groups_no) {
		return dao.findBulletinByGroupsNo(groups_no);
	}

	public List<BulletinVO> getAllBack() {
		return dao.getAllBack();
	}
	
	public List<BulletinVO> getAllFront() {
		return dao.getAllFront();
	}

}

package com.photo.model;

import java.sql.Timestamp;
import java.util.List;


public class PhotoService {
	private PhotoDAO_interface dao;
	
	public PhotoService() {
		dao = new PhotoDAO();
	}
	
	public PhotoVO addPhoto(String member_no, String groups_no, String photo_title, byte[] photo_file, Timestamp bulletin_time) {

		PhotoVO photoVO = new PhotoVO();
		photoVO.setMember_no(member_no);
		photoVO.setGroups_no(groups_no);
		photoVO.setPhoto_title(photo_title);
		photoVO.setPhoto_file(photo_file);
		photoVO.setPhoto_createdate(bulletin_time);
		dao.insert(photoVO);	

		return photoVO;
	}

	public PhotoVO updatePhoto(String photo_no, String member_no, String groups_no, String photo_title, byte[] photo_file, Timestamp bulletin_time) {

		PhotoVO photoVO = new PhotoVO();
		photoVO.setPhoto_no(photo_no);
		photoVO.setMember_no(member_no);
		photoVO.setGroups_no(groups_no);
		photoVO.setPhoto_title(photo_title);
		photoVO.setPhoto_file(photo_file);
		photoVO.setPhoto_createdate(bulletin_time);
		dao.update(photoVO);
		
		return photoVO;
	}

	public void deletePhoto(String photo_no) {
		dao.delete(photo_no);
	}
	
	public PhotoVO getOnePhoto(String photo_no) {
		return dao.findByPrimaryKey(photo_no);
	}

	public List<PhotoVO> getGroupsPhoto(String groups_no) {
		return dao.findPhotoByGroupsNo(groups_no);
	}

	public List<PhotoVO> getAllBack() {
		return dao.getAllBack();
	}
	
	public List<PhotoVO> getAllFront() {
		return dao.getAllFront();
	}

}

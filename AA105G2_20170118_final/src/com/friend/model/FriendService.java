package com.friend.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;



public class FriendService {
	private FriendDAO_interface dao;

	public FriendService() {
		dao = new FriendDAO();
	}

	public int addFriend(String member_no,String friend_no ) {

		FriendVO FriendVO = new FriendVO();
		FriendVO.setMember_no(member_no);
		FriendVO.setFriend_no(friend_no);
		
		//dao.insert(FriendVO);

		return dao.insert(FriendVO);
	}


	public void deleteFriend(String member_no,String Friend_no) {
		dao.delete(member_no,Friend_no);
	}

	public List<FriendVO> getOneFriend(String member_no) {
		return dao.findByPrimaryKey(member_no);
	}

	public List<FriendVO> getAll() {
		return dao.getAll();
	}



}

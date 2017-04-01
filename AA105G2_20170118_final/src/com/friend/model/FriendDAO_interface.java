package com.friend.model;

import java.util.*;


public interface FriendDAO_interface {
    public int insert(FriendVO friendVO);
    //public int update(FriendVO friendVO);
    public int delete(String article_no,String member_no);
    public List<FriendVO> findByPrimaryKey(String article_no);
    public List<FriendVO> getAll();

}

package com.response.model;

import java.util.*;


public interface ResponseDAO_interface {
	public int insert(ResponseVO responseVO);
    public int update(ResponseVO responseVO);
    public int delete(String response_no);
    public ResponseVO findByPrimaryKey(String response_no);
    public Set<String> getOwnResponse(String member_no);
    public List<ResponseVO> getAll();
}

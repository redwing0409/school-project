package com.response.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.response.model.*;


public class ResponseService {
	private ResponseDAO_interface dao;

	public ResponseService() {
		dao = new ResponseDAO();
	}

	public ResponseVO addResponse(String article_no, String member_no,
			String response_content, Timestamp response_time) {

		ResponseVO responseVO = new ResponseVO();

		
		responseVO.setArticle_no(article_no);
		responseVO.setMember_no(member_no);
		responseVO.setResponse_content(response_content);
		responseVO.setResponse_time(response_time);

		dao.insert(responseVO);

		return responseVO;
	}

	public ResponseVO updateResponse(String response_no, String article_no, String member_no,
			String response_content, Timestamp response_time) {

		ResponseVO responseVO = new ResponseVO();	
		responseVO.setResponse_no(response_no);
		responseVO.setArticle_no(article_no);
		responseVO.setMember_no(member_no);
		responseVO.setResponse_content(response_content);
		responseVO.setResponse_time(response_time);
		dao.update(responseVO);

		return responseVO;
	}

	public void deleteResponse(String response_no) {
		dao.delete(response_no);
	}

	public Set<String> getOwnResponse(String member_no) {
		return dao.getOwnResponse(member_no);
	}

	public List<ResponseVO> getAll() {
		return dao.getAll();
	}


}

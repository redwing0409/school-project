package com.article.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.response.model.*;



public class ArticleService {

	private ArticleDAO_interface dao;

	public ArticleService() {
		dao = new ArticleDAO();
	}

	public ArticleVO addArticle(String member_no, String article_title,
			String article_content, Integer article_views, Timestamp article_time, Integer article_status) {

		ArticleVO articleVO = new ArticleVO();

		
		articleVO.setMember_no(member_no);
		articleVO.setArticle_title(article_title);
		articleVO.setArticle_content(article_content);
		articleVO.setArticle_views(article_views);
		articleVO.setArticle_time(article_time);
		articleVO.setArticle_status(article_status);
		dao.insert(articleVO);

		return articleVO;
	}

	public ArticleVO updateArticle(String article_no, String member_no, String article_title,
			String article_content, Integer article_views, Timestamp article_time, Integer article_status) {

		ArticleVO articleVO = new ArticleVO();
		
		articleVO.setArticle_no(article_no);
		articleVO.setMember_no(member_no);
		articleVO.setArticle_title(article_title);
		articleVO.setArticle_content(article_content);
		articleVO.setArticle_views(article_views);
		articleVO.setArticle_time(article_time);
		articleVO.setArticle_status(article_status);
		dao.update(articleVO);

		return articleVO;
	}

	public void deleteArticle(String article_no) {
		dao.delete(article_no);
	}

	public ArticleVO getOneArticle(String article_no) {
		return dao.findByPrimaryKey(article_no);
	}

	public List<ArticleVO> getAllFront() {
		return dao.getAllFront();
	}
	public List<ArticleVO> getAllBack() {
		return dao.getAllBack();
	}
	public List<ArticleVO> getOwnArticle(String member_no) {
		return dao.getOwn(member_no);
	}	
	public List<ResponseVO> getAllResponse(String article_no) {
		return dao.getAllResponse(article_no);
	}		
	public List<ArticleVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}

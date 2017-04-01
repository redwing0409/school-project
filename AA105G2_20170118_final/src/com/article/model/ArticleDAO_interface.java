package com.article.model;

import java.util.*;
import com.response.model.*;

public interface ArticleDAO_interface {
          public int insert(ArticleVO articleVO);
          public int update(ArticleVO articleVO);
          public int delete(String article_no);
          public ArticleVO findByPrimaryKey(String article_no);
          public List<ArticleVO> getAllBack();
          public List<ArticleVO> getAllFront();
          public List<ArticleVO> getOwn(String member_no);
          public List<ResponseVO> getAllResponse(String article_no);
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
          public List<ArticleVO> getAll(Map<String, String[]> map); 

}
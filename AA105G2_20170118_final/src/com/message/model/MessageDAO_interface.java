package com.message.model;

import java.util.List;

public interface MessageDAO_interface {
	  public int insert(MessageVO messageVo);
      public int update(MessageVO messageVo);
      public int delete(String empno);
      public MessageVO findByPrimaryKey(String empno);
      public List<MessageVO>getAll();

}


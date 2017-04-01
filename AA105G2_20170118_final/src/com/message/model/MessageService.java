package com.message.model;

import java.sql.Timestamp;
import java.util.List;

public class MessageService {

	private MessageDAO_interface dao;

	public MessageService() {
		dao = new MessageDAO();
	}
	
	public MessageVO addMessage(String sender_no, String recipient_no, Timestamp message_time,
			String message_extname,String message_content, Integer message_visibility) {

		MessageVO messageVo = new MessageVO();

		messageVo.setSender_no(sender_no);
		messageVo.setRecipient_no(recipient_no);
		messageVo.setMessage_time(message_time);
		messageVo.setMessage_extname(message_extname);
		messageVo.setMessage_content(message_content);
		messageVo.setMessage_visibility(message_visibility);
		dao.insert(messageVo);
		return messageVo;
	}
	
	public MessageVO updateMessage(String message_no,String sender_no, String recipient_no, Timestamp message_time,
			String message_extname,String message_content, Integer message_visibility) {


		MessageVO messageVo = new MessageVO();
		
//		System.out.println(messageVo==null);
		
		messageVo.setMessage_no(message_no);
		messageVo.setSender_no(sender_no);
		messageVo.setRecipient_no(recipient_no);
		messageVo.setMessage_time(message_time);
		messageVo.setMessage_extname(message_extname);
		messageVo.setMessage_content(message_content);
		messageVo.setMessage_visibility(message_visibility);
		dao.update(messageVo);
		return messageVo;
	}

	public void deleteMessage(String message_no) {
		dao.delete(message_no);
	}

	public MessageVO getOneMessage(String message_no) {
		return dao.findByPrimaryKey(message_no);
	}

	public List<MessageVO> getAll() {
		return dao.getAll();
	}
	
	
}

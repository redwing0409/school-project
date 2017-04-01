package com.message.controller;

import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import com.friend.model.FriendDAO;
import com.friend.model.FriendVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/MessageServletForApp/{member_no}")
public class MessageServletForApp { 
//	private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<>());
	private static Map<String , Session> userInfo = new LinkedHashMap<String , Session>();
	boolean friend_flag=false;

	@OnOpen
	public void onOpen(@PathParam("member_no")  String member_no, Session userSession) throws IOException {
//		FriendDAO friendDAO = new FriendDAO();
//		 List<FriendVO> friendsNo = friendDAO.findByPrimaryKey(member_no); 
//		 for(FriendVO FriendVOItem : friendsNo) {       //according to friendsNo to member table to find mamber_name
//			 if(FriendVOItem.getFriend_no().equals(friend_no)){
//				  System.out.println(FriendVOItem.getFriend_no());
//				 friend_flag=true;
//			 }
//			  System.out.println(friend_flag==true);
//		    }
		userInfo.put(member_no, userSession); //連線進來的(會員編號 , session)
//		System.out.println(userSession.getId() + ": MessageServletForApp已連線");
		System.out.println(member_no + ": MessageServletForApp已連線");
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
//System.out.println("--onMessage-- mapSize= " + userInfo.size());
		  try { 
		 MemberService memberSvc = new MemberService();   		  
		 JSONObject jsonObjectIn = new JSONObject(message); //original jsonObjectIn word
//System.out.println("jsonObjectIn" + jsonObjectIn);	
		
		String friend_no = jsonObjectIn.get("friend_no").toString();
		MemberVO memberVO_Friend = memberSvc.getOneMember(friend_no);
				 
		String member_no = jsonObjectIn.get("member_no").toString();	
		MemberVO memberVO_Member = memberSvc.getOneMember(member_no);
		
		JSONObject jsonObjectInAddMemberName=jsonObjectIn.put("friend_name", memberVO_Friend.getMember_name()); //add Member name to original jsonObject
				   jsonObjectInAddMemberName=jsonObjectIn.put("my_name", memberVO_Member.getMember_name()); 
//System.out.println("jsonObjectInAddMemberName" + jsonObjectInAddMemberName);	
		   
        for (String myKey : userInfo.keySet()) {       //check LinkedHashMap contains member_no& friend_no
        	if(myKey.equals(member_no)||myKey.equals(friend_no)){
        		String myKeyVal = myKey+"="+userInfo.get(myKey);
        		if (userInfo.get(myKey).isOpen()){
String jsonText= jsonObjectInAddMemberName.toString();
//System.out.println("jsonText:"+jsonText);
        			userInfo.get(myKey).getAsyncRemote().sendText(jsonText);
//    				userInfo.get(myKey).getAsyncRemote().sendText(message);
    			}
//        		System.out.println(myKeyVal);
        	}
         }
        
			System.out.println("Message received: " + message);
		 }catch (JSONException e) {
			 System.out.println(e.toString());
         }
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		userInfo.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d", userSession.getId(),
				reason.getCloseCode().getCode());
		System.out.println(text);
	}
}

package com.groups.controller;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.*;

import com.groups.model.*;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/groups/InviteServer/{myMember_no}")
public class InviteServer {
	
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
private static final Map<String , Session> userInfo = Collections.synchronizedMap(new LinkedHashMap<String , Session>());
private static final Map<String , Set<String>> userRequest = Collections.synchronizedMap(new LinkedHashMap<String , Set<String>>());
int i = 0;
	@OnOpen
	public void onOpen(@PathParam("myMember_no") String myMember_no, Session userSession) throws IOException {
		allSessions.add(userSession);
		userInfo.put(myMember_no, userSession); //連線進來的(會員編號 , session)
		
		
		
		GregorianCalendar cal = new GregorianCalendar();
		if(userRequest.containsKey(myMember_no)){
			Set<String> s = userRequest.get(myMember_no);
			Session targetSession = userInfo.get(myMember_no);
			List<String> list = new ArrayList<String>(s);
			
			if(targetSession.isOpen()){
				Timer timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask(){
					public void run(){
						if(i == (list.size()-1)){
							timer.cancel();
						}
						targetSession.getAsyncRemote().sendText(list.get(i));
						i++;
		    	    }
		        }, cal.getTime(), 1000);
			}
//			userRequest.remove(myMember_no);
		}
//		System.out.println(userSession.getId() + ": 社群已連線");
//		System.out.println(myMember_no + ": 已連線");
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}

	
	@OnMessage
	public void onMessage(Session userSession, String message) throws JSONException {
		System.out.println("--onMessage-- mapSize= " + userInfo.size());
		JSONObject jsonObj = new JSONObject(message);
		System.out.println("jsonObj= " + jsonObj);//邀請加入的社團編號，邀請誰(會員編號)
		
		System.out.println("action= " + jsonObj.get("action"));
		String action = (String) jsonObj.get("action");
		GroupsService groupsSvc = new GroupsService();
		
		if("invite".equals(action)){
			System.out.println("inviteArea");
			
			GroupsVO groupsVO = groupsSvc.getOneGroups(jsonObj.get("invite_groups").toString());
			String messageOut = jsonObj.put("groups_title", groupsVO.getGroups_title()).toString();
			
			String target = jsonObj.get("target").toString();
			System.out.println("target= " + target + " userInfo contain? " + userInfo.containsKey(target));
			
			if(userInfo.containsKey(target) && userInfo.get(target).isOpen()){
				
				Session targetSession = userInfo.get(target);
				targetSession.getAsyncRemote().sendText(messageOut);
				userSession.getAsyncRemote().sendText("已送出邀請");
				
				if(!userRequest.containsKey(target)){
					Set<String> inviteRequest = new LinkedHashSet<String>();
					inviteRequest.add(messageOut);
					userRequest.put(target, inviteRequest);
					return;
					
				} 
				Set<String> inviteRequest = userRequest.get(target);
				inviteRequest.add(messageOut);
				userRequest.put(target, inviteRequest);
				
			} else{
				userSession.getAsyncRemote().sendText("會員尚未登入，已送出邀請");
				
				if(!userRequest.containsKey(target)){
					Set<String> inviteRequest = new LinkedHashSet<String>();
					inviteRequest.add(messageOut);
					userRequest.put(target, inviteRequest);
					return;
				} 
				Set<String> inviteRequest = userRequest.get(target);
				inviteRequest.add(messageOut);
				userRequest.put(target, inviteRequest);
			}
		}//inviteArea
		if("agree".equals(action)){
			System.out.println("agreeArea");
			String groups_no =  jsonObj.get("invite_groups").toString();
			String member_no =  jsonObj.get("target").toString();
			if(userRequest.containsKey(member_no)){
				Set<String> requestSet = userRequest.get(member_no);
				Set<String> agreeNO = new LinkedHashSet<String>();
				GroupsVO groupsVO = groupsSvc.getOneGroups(groups_no);
				String groups_title = groupsVO.getGroups_title();
				String str = "{\"action\":\"invite\",\"invite_groups\":\"" + groups_no + "\",\"groups_title\":\"" + groups_title + "\",\"target\":\"" + member_no + "\"}";
				agreeNO.add(str);
				
				requestSet.removeAll(agreeNO);
				
				System.out.println(requestSet.size());
				if(requestSet.size() == 0){
					userRequest.remove(member_no);
				}
			}
		}//agree
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": inveteServer Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

 
}

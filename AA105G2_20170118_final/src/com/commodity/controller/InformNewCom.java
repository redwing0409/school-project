package com.commodity.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/commodity/InformNewCom")
public class InformNewCom {
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnOpen
    public void onOpen(@PathParam("member_no") String member, Session ComSession) throws IOException{
    	allSessions.add(ComSession);
    	System.out.println(ComSession.getId() + ": �ӫ��w�s�u");
//		System.out.println(member + ": �w�s�u");
    }
    @OnMessage
    //�Ȥ�ݦ^�ǰT�������A����
	public void onMessage(Session ComSession, String Com_inform) {
		for (Session session : allSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(Com_inform);
		}
		System.out.println("�ӫ� Message received: " + Com_inform);
	}
    
    @OnError
	public void onError(Session ComSession, Throwable e){

	}
    
	@OnClose
	public void onClose(Session ComSession, CloseReason reason) {
		allSessions.remove(ComSession);
		System.out.println(ComSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}


}

package serverEndpoint;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;
import org.json.*;

@ServerEndpoint("/MyEchoServerCal")
public class MyEchoServerCal {
	
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
private static String message=null;
	
	@OnOpen
	public void onOpen(Session userSession) throws IOException {

		allSessions.add(userSession);

		if(message!=null){
			onMessage(userSession,message);
		}
		//
		System.out.println(userSession.getId() + ": 已連線");
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");

	}
	
	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		
		try {
			this.message=message;
			JSONObject json=new JSONObject(message);
			JSONObject json2=new JSONObject();
			for (Session session : allSessions) {
				if (session.isOpen()){
					String place_no =String.valueOf(json.get("place_no"));
					String forbiddenDate = (String) json.get("forbiddenDate");
					ArrayList<String> arrayList=new ArrayList<String>();

					JSONArray array= (JSONArray)json.get("array");
										
System.out.println("從前端接收不能點的日期陣列: "+array);
					int replace=(Integer) json.get("indexOfArray");
System.out.println("需取代array的index: "+replace);
					if(replace!=-1){
						array.put(replace,forbiddenDate);
System.out.println("array的index元素需更換為: "+forbiddenDate);					
					}
System.out.println("更換後的array: "+array);
					json2.put("array", array);
					json2.put("place_no", place_no);
					session.getAsyncRemote().sendText(json2.toString());
				}
			}
System.out.println("送出給前端不能點的日期陣列: " + json2);
System.out.println("-----------------------------------------");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ":  Cal Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

 
}

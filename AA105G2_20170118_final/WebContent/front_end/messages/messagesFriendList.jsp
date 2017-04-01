<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.member.model.*"%>
<%@ page import="com.friend.model.*"%>
<%@ page import="java.util.*"%>
<%
	MemberVO memberVO=(MemberVO)session.getAttribute("account");
	
	FriendService friendSvc = new FriendService();
	List<FriendVO> friendVO = friendSvc.getOneFriend(memberVO.getMember_no());
	
	MemberService memberSvc = new MemberService();
	List<MemberVO> memberVOForcheck = memberSvc.getAll();
	
	List<MemberVO> member_list = new ArrayList<MemberVO>();
	for(FriendVO friendVO_item:friendVO){
		for(MemberVO memberVOForcheck_item:memberVOForcheck){
			if(friendVO_item.getFriend_no().equals(memberVOForcheck_item.getMember_no()))
			{
				member_list.add(memberVOForcheck_item);
			}
		}//end of for(MemberVO memberVOForcheck_item:memberVOForcheck)
	}//end of for(FriendVO friendVO_item:friendVO)
	pageContext.setAttribute("member_list", member_list);
		
%>
<html>
<head>
<link href="<%=request.getContextPath()%>/front_end/css/chat.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<!--   <script src="https://code.jquery.com/jquery-1.10.2.js"></script> -->
<title>AA150G2 Groups:Home</title>
</head>
<body>

	<div class="FriendListTitle" align="center" style="font-size:60%;font-weight: bold;">--FriendList--</div>
<div  id="FriendList">
	<table>
		<tr>
		<c:forEach var="member_list" items="${member_list}">
			<tr class="eachFriend" data-memberno="${member_list.member_no}" data-membername="${member_list.member_name}">	
				<td ><img src="<%=request.getContextPath()%>/message/DBGif.do?name=${member_list.member_no}" width=75vw></td>
				<td>${member_list.member_name}</td>
			</tr>
		</c:forEach>
	</table>	
	</div>
	<div class="chatWrapper chatWrapperRight">
				<div class="chatDock clearfix">
					<div class="clearfix chatContainer rNubContainer">
						<div id="ChatTabsPagelet">
							<div>
								<div class="fbNubGroup clearfix _56oy _20fw _3__- _4ml1">
									<div class="chatNubGroup clearfix" id="">
										<div class="_59v1" style="align-items: flex-end; display: flex;"><!-- 視窗append至此 -->
								
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
</body>
<%-- 	<% --%>
<%-- 	for(MemberVO member_list_item:member_list){
<%--		out.println(member_list_item.getMember_no());
<%-- 	}--%>
<%-- 	%> --%>

<style>
#messagesAndFriendArea{
	position:fixed;
	bottom:0px;
	left:0px;
	z-index:9999;
	width:10vw;
	background-color:#FFFFFF;
}
</style>
<script>
var loginMem_no1 = "<%=memberVO.getMember_no() %>";
var FriendListHeight;
var showFriend = false;
	//以下為friend list for init setting
	
	function doFirst(){
		FriendListHeight=$("#FriendList").height();
		console.log("FriendListHeight= " + FriendListHeight);
		getFriendListInit();
	}
	function getFriendListInit () {
		$("#FriendList").animate({height:'-'+FriendListHeight+'px'},600,'swing');  //init FriendList fix bottom
	}
	
	$(".FriendListTitle").click(function() {
		if(!showFriend){
			 $("#FriendList").animate({height:FriendListHeight+'px'},600,'swing');
			 showFriend = true;
		} else{
			$("#FriendList").animate({height:'-'+FriendListHeight+'px'},600,'swing');
			showFriend = false;
		}
	});
	
	window.onload=doFirst;
</script>
<script>
	//以下為websocket
	var loginMem_no = "<%=memberVO.getMember_no() %>";
	var MyPoint = "/MessageServletForApp/"+loginMem_no;
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	var my_name = "<%=memberVO.getMember_name() %>";
	
	function connectChat() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
//console.log(webSocket);
	webSocket.onopen = function(event) {
		};
		
//$(".eachFriend").click(function(){
//	console.log( $(this).data('memberno'));
//});
		
		
$(".eachFriend").bind("click",function(){//發送者點擊
			var friend_no = $(this).data('memberno');
			var my_name = $(this).data('membername');
//var friend_no = $(this).attr("id").replace("memBoxNo_","");
//console.log( friend_no);
			var msgBox='<div class="'+friend_no+'" style="margin:0 0 0 16px; position:relative;">';//事件focus
			msgBox+='<div>';
			msgBox+='<div class="fbNub _50-v _50mz _50m_ _27_3 opened">';//事件focus
			msgBox+='<div class="fbNubFlyout fbDockChatTabFlyout uiContextualLayerParent" style="max-height: 326px;" aria-label="xxx">';
			msgBox+='<div class="fbNubFlyoutOuter" style="max-height: 326px;">';
			msgBox+='<div class="fbNubFlyoutInner">';
			msgBox+='<div class="clearfix fbNubFlyoutTitlebar titlebar">';
			msgBox+='<div class="mls titlebarButtonWrapper rfloat _ohf">';//title按鈕位置
			msgBox+='<span class="_3a61">';
			msgBox+='<button type="button" class="close" id="close_'+friend_no+'"  title="關閉" style="border:0px; margin-left:10px;">&times;</button>';
			msgBox+='</span>';
			msgBox+='</div>';
			msgBox+='<div class="titlebarLabel clearfix">';
			msgBox+='<h4 class="titlebarTextWrapper">';
			msgBox+='<span class="_3a61"  style="position: absolute; left:20px;">';//上線燈號
			msgBox+='<span class="_3olv _2nlt"></span>';// 觸發事件燈號 																																								
			msgBox+='</span>';
			msgBox+='<span>';// 會員名稱 
			msgBox+='<a href="#" class="titlebarText"  style="position: absolute; left:20px;">';
			msgBox+='<span>'+my_name+'</span>';
			msgBox+='</a>';
			msgBox+='</span>';
			msgBox+='</h4>';
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='<div class="fbNubFlyoutHeader">';
			msgBox+='<div class="_1sk5">';
			msgBox+='<div class="_1sk6 hidden_elem"></div>';
			msgBox+='</div>';																	
			msgBox+='</div>';
			msgBox+='<div class="_1ia" id="js_b1m">';
			msgBox+='<div class="_3bpu"></div>';
			msgBox+='<div class="_2hc2"></div>';
			msgBox+='<div class="_4g6x"></div>';
			msgBox+='<div class="_2v5j">';
			msgBox+='<div class="fbNubFlyoutBody scrollable" id="scroll_'+friend_no+'" style="height: 242px;">';
			msgBox+='<div class="fbNubFlyoutBodyContent">';
			msgBox+='<div class="_av1 hidden_elem"></div>';
			msgBox+='<table class="uiGrid _51mz conversationContainer" cellspacing="0" cellpadding="0" style="margin:0px;">';
			msgBox+='<tbody style=" display: table-row-group;  vertical-align: middle; border-color: inherit;">';
			msgBox+='<tr class="_51mx" style="display: table-row; vertical-align: inherit; border-color: inherit;">';
			msgBox+='<td class="_51m- vBot _51mw" style="text-align: left;     font-size: 13px;  display: table-cell;">';
			msgBox+='<div class="accessible_elem">聊天室已啟動</div>';
			msgBox+='<div class="conversation" aria-live="polite" aria-atomic="false">';
			msgBox+='<div data-reactroot>';
			msgBox+='<div id="msgArea_'+friend_no+'">'//放聊天記錄
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='<div class="accessible_elem">聊天室窗結束</div>';
			msgBox+='<div></div>';
			msgBox+='<div>';
			msgBox+='<div data-reactroot></div>';
			msgBox+='</div>';
			msgBox+='<div></div>';
			msgBox+='<div></div>';
			msgBox+='</td>';
			msgBox+='</tr>';
			msgBox+='</tbody>';
			msgBox+='</table>';
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='<div class="fbNubFlyoutFooter">';
			msgBox+='<div class="_1d4_">';
			msgBox+='<div class="_552h"_style="min-height: 16px;">';
			msgBox+='<div class="">';
			msgBox+='<div class="_5rp7 _5rp8">';
			msgBox+='<div class="_1p1t">';
			msgBox+='<div class="_1p1v">';
			msgBox+='<em class="_4qba">輸入訊息......</em>';
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='<div class="_5rpb">';
			msgBox+='<textarea class="_5rpu" id="5rpu_'+friend_no+'" placeholder="輸入訊息......" style="resize:none; width:100%; outline: none; white-space: pre-wrap; word-wrap: break-word;"></textarea>';																					
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='<div class="_552n"></div>';
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='</div>';
			msgBox+='</div>';
		
			
			
			if($("."+friend_no).size() > 0) {//檢視發送者這個視窗是不是存在，不存在就新增，避免同樣視窗出現
			 	return;
			} else {
				$("._59v1").append(msgBox);
			}
			
				$("._5rpu").bind("keypress",function (e) {//輸入訊息的事件
   					var friend_no = $(this).attr("id").replace("5rpu_","");
//console.log(friend_no);
				     if (e.which == 13) {			         	    				         
sendMessage(loginMem_no1,$(this).val().trim(),friend_no);
				         $("#5rpu_"+friend_no).val("");			
				         $("#5rpu_"+friend_no).focus();
				     } 
				 });
   				
   				$(".close").bind("click",function(){//關閉對話框，將對話存入資料庫
   					var indexNo = $(this).attr("id").replace("close_","");
//sendMessage(loginMem_no1,"saveMessage",friend_no);	       				
   					$( "."+indexNo ).slideDown( "slow", function() {
   						$( "."+indexNo ).remove();
   					  });
   				});
			
		}); //end of $("#FriendList").on("click",function()
		

webSocket.onmessage = function(event) {
	var jsonObj = JSON.parse(event.data);//3
	console.log("jsonObj:"+jsonObj);
	var message = jsonObj.message;
	var memNo = jsonObj.member_no;//傳送訊息的
	var friendNo = jsonObj.friend_no;//接收訊息的
	
	
	var my_name = jsonObj.my_name;
	var friend_name = jsonObj.friend_name;
	var my_account = jsonObj.my_account;	
	
	console.log("memNo123:"+memNo);
	console.log("friendNo:"+friendNo);
	console.log("my_name:"+my_name);
	console.log("friend_name:"+friend_name);
		if(loginMem_no == memNo){//自己發送的訊息
       		var myMsg = '<div class="_4tdt _ua1">';
       		myMsg +='<div class="_31o4">';
       		myMsg +='<a class="_4tdw">'+my_name+'</a>';	
 			myMsg +='</div>';
       		myMsg+='<div class="_ua2">';			
       		myMsg+='<div class="_4tdv">';
       		myMsg+='<div class="_5wd4 _1nc7 direction_ltr">';
       		myMsg+='<div class="_ht8">';
       		myMsg+='<div class="_5wd9">';
       		myMsg+='<div class="_5wde _n4o">';
       		myMsg+='<div class="_5w1r _3_om _5wdf" style="max-width: 179px; word-wrap: inherit;">';
       		myMsg+='<div class="_4gx_">';
       		myMsg+='<div class="_d97">';
       		myMsg+='<span class="_5yl5">';
       		myMsg+='<span>'+message+'</span>';
       		myMsg+='</span>';
       		myMsg+='</div>';
       		myMsg+='</div>';
       		myMsg+='</div>';
       		myMsg+='</div>';
       		myMsg+='</div>';
       		myMsg+='</div>';
       		myMsg+='</div>';
       		myMsg+='</div>';
       		myMsg+='</div>';
       		myMsg+='</div>';
       		
       		$("#msgArea_"+friendNo).append(myMsg);
       		
				if($("#scroll_"+friendNo)){
					$("#scroll_"+friendNo).scrollTop(($("#scroll_"+friendNo)[0].scrollHeight));
				}
   			}//自己發送的訊息	
   		
   		//receive	
  //console.log("friendNo receive:"+loginMem_no);		       		
		if(loginMem_no != memNo){//接收者
// console.log("memNo).size():"+ $("."+memNo).size());	       			
			if($("."+memNo).size() > 0) {//選擇這個視窗是否存在，不存在就新增
	       		
	       		}else{	
       			if(loginMem_no==friendNo){//傳送的friendNo只有login的編號一樣才會收到
 	       		var msgBox='<div class="'+memNo+'" style="margin:0 0 0 16px; position:relative;">';//事件focus
				msgBox+='<div>';
				msgBox+='<div class="fbNub _50-v _50mz _50m_ _27_3 opened">';//事件focus
				msgBox+='<div class="fbNubFlyout fbDockChatTabFlyout uiContextualLayerParent" style="max-height: 326px;" aria-label="xxx">';
				msgBox+='<div class="fbNubFlyoutOuter" style="max-height: 326px;">';
				msgBox+='<div class="fbNubFlyoutInner">';
				msgBox+='<div class="clearfix fbNubFlyoutTitlebar titlebar">';
				msgBox+='<div class="mls titlebarButtonWrapper rfloat _ohf">';//title按鈕位置
				msgBox+='<span class="_3a61">';
				msgBox+='<button type="button" class="close" id="close_'+memNo+'"  title="關閉" style="border:0px; margin-left:10px;">&times;</button>';
				msgBox+='</span>';
				msgBox+='</div>';
				msgBox+='<div class="titlebarLabel clearfix">';
				msgBox+='<h4 class="titlebarTextWrapper">';
				msgBox+='<span class="_3a61"  style="position: absolute; left:20px;">';//上線燈號
				msgBox+='<span class="_3olv _2nlt"></span>';// 觸發事件燈號 																																								
				msgBox+='</span>';
				msgBox+='<span>';// 會員名稱 
				msgBox+='<a href="http://localhost:8081/AA105G4/front-end/member/memberPageForOther.jsp?mem_account='+my_account+'" class="titlebarText"  style="position: absolute; left:20px;">';
				msgBox+='<span>'+my_name+'</span>';
				msgBox+='</a>';
				msgBox+='</span>';
				msgBox+='</h4>';
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='<div class="fbNubFlyoutHeader">';
				msgBox+='<div class="_1sk5">';
				msgBox+='<div class="_1sk6 hidden_elem"></div>';
				msgBox+='</div>';																	
				msgBox+='</div>';
				msgBox+='<div class="_1ia" id="js_b1m">';
				msgBox+='<div class="_3bpu"></div>';
				msgBox+='<div class="_2hc2"></div>';
				msgBox+='<div class="_4g6x"></div>';
				msgBox+='<div class="_2v5j">';
				msgBox+='<div class="fbNubFlyoutBody scrollable" id="scroll_'+memNo+'" style="height: 242px;">';
				msgBox+='<div class="fbNubFlyoutBodyContent">';
				msgBox+='<div class="_av1 hidden_elem"></div>';
				msgBox+='<table class="uiGrid _51mz conversationContainer" cellspacing="0" cellpadding="0" style="margin:0px;">';
				msgBox+='<tbody style=" display: table-row-group;  vertical-align: middle; border-color: inherit;">';
				msgBox+='<tr class="_51mx" style="display: table-row; vertical-align: inherit; border-color: inherit;">';
				msgBox+='<td class="_51m- vBot _51mw" style="text-align: left;     font-size: 13px;  display: table-cell;">';
				msgBox+='<div class="accessible_elem">聊天室已啟動</div>';
				msgBox+='<div class="conversation" aria-live="polite" aria-atomic="false">';
				msgBox+='<div data-reactroot>';
				msgBox+='<div id="msgArea_'+memNo+'">'//放聊天記錄
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='<div class="accessible_elem">聊天室窗結束</div>';
				msgBox+='<div></div>';
				msgBox+='<div>';
				msgBox+='<div data-reactroot></div>';
				msgBox+='</div>';
				msgBox+='<div></div>';
				msgBox+='<div></div>';
				msgBox+='</td>';
				msgBox+='</tr>';
				msgBox+='</tbody>';
				msgBox+='</table>';
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='<div class="fbNubFlyoutFooter">';
				msgBox+='<div class="_1d4_">';
				msgBox+='<div class="_552h"_style="min-height: 16px;">';
				msgBox+='<div class="">';
				msgBox+='<div class="_5rp7 _5rp8">';
				msgBox+='<div class="_1p1t">';
				msgBox+='<div class="_1p1v">';
				msgBox+='<em class="_4qba">輸入訊息......</em>';
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='<div class="_5rpb">';
				msgBox+='<textarea class="_5rpu" id="5rpu_'+memNo+'" placeholder="輸入訊息......" style="resize:none; width:100%; outline: none; white-space: pre-wrap; word-wrap: break-word;"></textarea>';																					
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='<div class="_552n"></div>';
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='</div>';
				msgBox+='</div>';
	       			
	       			$("._59v1").append(msgBox);
	       			


 	       		$("._5rpu").bind("keypress",function (e) {//輸入訊息的事件
 	       			var friend_no = $(this).attr("id").replace("5rpu_","");
 				     if (e.which == 13) {			         		 				        
sendMessage(loginMem_no1,$(this).val().trim(),friend_no,my_name);
 				         $(this).val(""); 
 				        $(this).focus();
 				     } //比對
 				 });//輸入訊息的事件
 	       		
 	       	$(".close").bind("click",function(){
					var indexNo = $(this).attr("id").replace("close_","");
sendMessage(loginMem_no1,"saveMessage",memNo);       					
					$( "."+memNo ).slideDown( "slow", function() {
						$( "."+memNo ).remove();
					  });
				});
 				 
				}
	       	 }//else  		
    			var friendMsg ='<div class="_4tdt _ua1">';//我發送給對方的訊息
    				friendMsg +='<div class="_31o4">';
     			friendMsg +='<a class="_4tdw" href="http://localhost:8081/AA105G4/front-end/member/memberPageForOther.jsp?mem_account='+my_account+'">'+my_name+'</a>';	
     	 	 	friendMsg +='</div>';
     	 	 	friendMsg +='<div class="_ua2">';
     	 	 	friendMsg +='<div class="_4tdv">';
     	 	 	friendMsg +='<div class="_5wd4 _1nc7 direction_ltr _2cnu">';
     	 	 	friendMsg +='<div class="_h8t">';
     	 	 	friendMsg +='<div class="_5wd9">';
     	 	 	friendMsg +='<div class="_5wde _n4o">';
     	 	 	friendMsg +='<div class="_5w1r _3_om _5wdf" style="max-width: 179px; word-wrap: inherit;">';
     	 	 	friendMsg +='<div class="_4gx_">';
     	 	 	friendMsg +='<div class="_d97">';
     	 	 	friendMsg +='<span class="_5yl5">';	        	 	 	
     	 	 	friendMsg +='<span>'+message+'</span>';
     	 	 	friendMsg +='</span>';
     	 	 	friendMsg +='</div>';
     	 	 	friendMsg +='</div>';
     	 	 	friendMsg +='</div>';
     	 	 	friendMsg +='</div>';
     	 	 	friendMsg +='</div>';
     	 	 	friendMsg +='</div>';
     	 	 	friendMsg +='</div>';
     	 	 	friendMsg +='</div>';
     	 	 	friendMsg +='</div>';
     	 	 	friendMsg +='</div>';
   	console.log("memNoTEST:"+memNo);		   	 	 	
     	 	 	$("#msgArea_"+memNo).append(friendMsg);
     	 	 	
     	 	 	if($("#scroll_"+memNo)){//捲軸自動往下移
					$("#scroll_"+memNo).scrollTop(($("#scroll_"+memNo)[0].scrollHeight));
				}
     	 	 	
    		}	
		}//end of webSocket.onmessage = function(event)	
	}//end of connectChat
	
	function sendMessage(loginMem_no,message,friendNo,my_name) {
	    
	    if (message === ""){
	    	return;
	    }else{
	        var jsonObj = {"member_no" : loginMem_no, "message" : message, "friend_no" : friendNo};//2以json送出
	        webSocket.send(JSON.stringify(jsonObj));
	    }
	}
	
	function disconnect () {
		webSocket.close();			
	}
	
	// use this to avoid websocket conflict 
	window.addEventListener('load',connectChat,false);
	
	
</script>

</html>
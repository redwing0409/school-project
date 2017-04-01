<%@page import="com.member.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	MemberVO memberVO = (MemberVO) session.getAttribute("account");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>AA150G2 Groups:Home</title>
</head>
<body onload="connect()" onunload="disconnect()">

	<div id="statusOutput" class="statusOutput" align="center" style="font-size:50%;font-weight: bold">
	</div>
	<div id="showInviteArea"></div>
	<iframe src="" name="inviteIframe" style="display:none"></iframe>
</body>
<style>
 #statusOutput{ 
 	background-color:white; 
 } 
#showInviteArea{
/* 	background-color:blue; */
}
.webSocketMessage{
	background-color:white;
	border:2px solid #e7e7e7;
}
#websocketArea{
	position:fixed;
	bottom: 0px;
	right:0px;
	z-index:9999;
	width:15vw;
	background-color:transparent;
}
.floatLeft{
	float: left;
}
.floatRight{
	float:right;
}
</style>
<script>
    var MyPoint3 = "/groups/InviteServer/${account.member_no}";
    var host3 = window.location.host;
    var path3 = window.location.pathname;
    var webCtx3 = path3.substring(0, path3.indexOf('/', 1));
    var endPointURL3 = "ws://" + window.location.host + webCtx3 + MyPoint3;
    
	var statusOutput = document.getElementById("statusOutput");
	var showInviteArea = document.getElementById("showInviteArea");
	var webSocket3;
	
	function connect() {
		// 建立 websocket 物件
		console.log(host3);
		console.log(path3);
		console.log(endPointURL3);
		webSocket3  = new WebSocket(endPointURL3);
		
		webSocket3.onopen = function(event) {
			statusOutput.style.backgroundColor = 'lightgreen';
			updateStatus("${account.member_name}");
			showInviteArea.innerHTML = "";
		};

		webSocket3.onmessage = function(event) {
			var divNode = document.createElement("div");
			var div = divNode.setAttribute("class", "webSocketMessage");
			showInviteArea.appendChild(divNode);
			console.log("onMessage Test");
			
			 try {
				 	var jsonObj = JSON.parse(event.data);
			        var invite_groups = jsonObj.invite_groups;
			        var target = jsonObj.target;
			        var groups_title = jsonObj.groups_title;
			        
			        var str = "";
			        str += "社團 <font color='red'><strong>" + groups_title + "</strong></font> 邀請加入 ";
			        str += "<FORM METHOD=\"post\" ACTION=\""+"<%= request.getContextPath() %>/groups_list/groups_listServlet.do\" name=\"form1\" target=\"inviteIframe\">";
			        str += "<input type=\"hidden\" name=\"groups_no\" value=\"" + invite_groups + "\">";
			        str += "<input type=\"hidden\" name=\"member_no\" value=\"" + target + "\">";
			        str += "<input type=\"hidden\" name=\"requestURL\" value=\"<%=request.getServletPath()%>\">";
			        str += "<input type=\"hidden\" name=\"action\" value=\"insert\">"
			        str += "<input type=\"submit\" class=\"agreeBtn\" value=\"確認\"" + "data-groupsno=\"" + invite_groups + "\"" + "\">"
			        str += "<button type=\"button\" class=\"refuse floatRight\" data-groupsno=\"" + invite_groups + "\">拒絕</button>";
			        str += "</FORM>";
			        
			        
			        $("#showInviteArea .webSocketMessage:last-child").html(str);
			        
			  } catch(e) {
// 			    	showInviteArea.innerHTML = "<font color='red'><strong>" + event.data + "</strong></font>";
			    	$("#showInviteArea .webSocketMessage:last-child").html("<font color='red'><strong>" + event.data + "</strong></font>");
			  }
			  
	    	  
			    $("#showInviteArea").slideDown(1000);
			    
			    $(".webSocketMessage").unbind("click");
		    	$(".webSocketMessage").bind("click" , function() {
		    		var index = $(".webSocketMessage").index(this);
		    		$(".webSocketMessage")[index].remove();
		    	});
			    
		    	$(".agreeBtn").unbind("click");
		    	$(".agreeBtn").bind("click" , function(){
		    		console.log("agreeBtn click" + this);
		    		var index = $(".agreeBtn").index(this);
		    		
		    		
		    		var groups_no = $(".agreeBtn").eq(index).data("groupsno");
		    		var member_no = <%= memberVO.getMember_no()%>
		    		console.log("groups_no= " + groups_no + " member_no= " + member_no);
		    		
		    		var jsonObj = {"action" : "agree", "invite_groups" : groups_no, "target" : member_no, };
		    		webSocket3.send(JSON.stringify(jsonObj));
		    	});
		    	
		    	$(".refuse").unbind("click");
		    	$(".refuse").bind("click" , function(){
		    		console.log("refuse click" + this);
		    		var index = $(".refuse").index(this);
		    		console.log(index);
		    		
		    		var groups_no = $(".refuse").eq(index).data("groupsno");
		    		var member_no = <%= memberVO.getMember_no()%>
		    		console.log("refuseBtn groups_no= " + groups_no + " refuseBtn member_no= " + member_no);
		    		
		    		var jsonObj = {"action" : "agree", "invite_groups" : groups_no, "target" : member_no, };
		    		webSocket3.send(JSON.stringify(jsonObj));
		    	});
		    	
		};
		webSocket3.onclose = function(event) {
			updateStatus("");
			statusOutput.style.backgroundColor = 'white';
		};
	}
	
	function disconnect () {
		webSocket3.close();
		statusOutput.style.backgroundColor = 'white';
	}

	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
	
	$(document).ready(function() {
		$("#showInviteArea").hide();
		$("#statusOutput").click(function() {
			$("#showInviteArea").slideToggle(1000);
		});
	});
	
	
	
</script>
</html>
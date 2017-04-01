var group_no = 　document.getElementById("groupsInfo").dataset.groupno;

function doFirst(){
	console.log(group_no);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function (){
	  if( xhr.readyState == 4){
	    if( xhr.status == 200){
		    var jsonObj = JSON.parse(xhr.responseText);
		    var str = "<select id=\"memberSelect\" size=\"1\" name=\"member_no\" onChange=\"changed(this)\"> ";
		    str += "<option value=\"test\">" + "新增成員";
	    	for (var key in jsonObj){
	    		if(jsonObj.hasOwnProperty(key)){
	    			str += "<option value=\"" + key + "\">" + jsonObj[key] + "</option>";
	    		}
	    	}
		    str += "</select>";
		    document.getElementById("showInviteSelect").innerHTML = str;
	    }else{
	       alert( xhr.status );
	    }//xhr.status == 200
	  }//xhr.readyState == 4
	};//onreadystatechange 
	
	var url= window.location.pathname.substring(0, path3.indexOf('/', 1))+"/groups_list/groups_listServlet.do";
	xhr.open("Post",url,true); 
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send( "action=findMemberNOByGroupsNO&groups_no=" +  group_no);
}
function changed(member_no) {
	member_no.options[member_no.options.selectedIndex].disabled = true;
	sendMessage();
}
function sendMessage() {
	memberSelect = document.getElementById("memberSelect");
	var member_no = memberSelect.value;
	var jsonObj = {"action" : "invite", "invite_groups" : group_no, "target" : member_no, };
	webSocket3.send(JSON.stringify(jsonObj));
}
window.addEventListener('load',doFirst,false);
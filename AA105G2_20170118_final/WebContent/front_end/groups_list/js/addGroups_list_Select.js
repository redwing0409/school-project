function doFirst(){
	console.log("test")
	changed(document.getElementsByName("groups_no")[0]);
}
function changed(groups_no) {
	//===建立xhr物件(填入程式碼)
	var xhr = new XMLHttpRequest();
	//設定好回呼函數   
	xhr.onreadystatechange = function (){
	  if( xhr.readyState == 4){
	    if( xhr.status == 200){
	    //取回...回傳的資料
	    var jsonObj = JSON.parse(xhr.responseText);
	    var str = "<select size=\"1\" name=\"member_no\" > ";
    	for (var key in jsonObj){
    		if(jsonObj.hasOwnProperty(key)){
    			str += "<option value=\"" + key + "\">" + key + " - " + jsonObj[key];
    		}
    	}
	    str += "</select>";
	    document.getElementById("showPanel").innerHTML = str;
	    }else{
	       alert( xhr.status );
	    }//xhr.status == 200
	  }//xhr.readyState == 4
	};//onreadystatechange 
	//建立好Get連接
	var url= window.location.pathname.substring(0, path.indexOf('/', 1))+"/groups_list/groups_listServlet.do";
	xhr.open("Post",url,true); 
	//送出請求 
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send( "action=findMemberNOByGroupsNO&groups_no=" +  groups_no.value);
}
window.addEventListener('load',doFirst,false);
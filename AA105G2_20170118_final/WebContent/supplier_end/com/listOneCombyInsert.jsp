<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.commodity.model.*"%>
<%
    ComService comSvc = new ComService();
    List<ComVO> list = comSvc.getAll();
    int listsize = list.size()-1;
    /* 取得最後一筆的產品編號 */
    String com_no = list.get(listsize).getCom_no();
    /* 存入page，以利下面程式取得 */
    pageContext.setAttribute("com_no",com_no);
%>
<html>
<head>
<title>supplier_end 單件商品資料 - listOneCombyInsert.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/com/css/listAllCommodity.css">
<script>
   var MyPoint = "/commodity/InformNewCom";
   var host = window.location.host;
   var path = window.location.pathname;
   var webCtx = path.substring(0, path.indexOf('/', 1));
   var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
   
   var webSocket;
   
   function connectShopping() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		//建立連線
		webSocket.onopen = function(event) {
			document.getElementById('sendMessage').disabled = false;	
		};
		//收到server傳來的訊息
		webSocket.onmessage = function(event) {
	       var jsonObj = JSON.parse(event.data);
	       var message = jsonObj.message + "\r\n"; 
	       alert("通知成功!!");
		};		
      }
 	
   //clinet發送的訊息
   var com_no = null;
   function sendMessage() {
	   com_no = document.getElementById("com_no").value;
	   var jsonObj = {"com_no": com_no, "message" : "有新品上市囉，趕緊去看看!!"};
	   webSocket.send(JSON.stringify(jsonObj));
   }
   
   function disconnect () {
		webSocket.close();
	}
</script>
</head>
<body bgcolor='white' onload="connectShopping();" onunload="disconnect();">
<%
  //設定顯示的時間格式
  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
  pageContext.setAttribute("sf", sf);
%>
<h5 style="color:red">商品新增成功!</h5>
<div>
<button type="submit" id="sendMessage" class="btn btn-info" onclick="sendMessage();"/>新產品通知</button>
<input type="hidden" id="com_no" value="${com_no}"/>
</div>
<table  table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:80%">
	<tr>
		<th>商品編號</th>
		<th>分類項目</th>
		<th>商品名稱</th>
		<th>商品描述</th>
		<th>商品價格</th>
		<th>商品狀態</th>
		<th>上架日期</th>
		<th>下架日期</th>
		<th>商品備註</th>
		<th>商品圖片</th>
	</tr>
	<tr align='center' valign='middle'>
	    <td>${com_no}</td>
		<td>
		      <c:if test="${comVO3.pcm_no == 1}">喜帖</c:if>
	          <c:if test="${comVO3.pcm_no == 2}">婚紗相本</c:if>
	          <c:if test="${comVO3.pcm_no == 3}">婚禮小物</c:if>
	          <c:if test="${comVO3.pcm_no == 4}">婚禮用品</c:if>
	    </td>
		<td>${comVO3.com_name}</td>
		<td>${comVO3.com_desc}</td>
		<td>${comVO3.com_price}</td>
		<td>
		   <c:if test="${comVO3.com_status==1}">上架</c:if>
		   <c:if test="${comVO3.com_status==2}">下架</c:if>
		</td>
		<td>${sf.format(comVO3.com_shelf_date)}</td>
		<td>${sf.format(comVO3.com_off_date)}</td>
		<td>${comVO3.com_note}</td>
		<td><img src="<%=request.getContextPath()%>/DBGif.do?name=${com_no}" width='100'></td>	
	</tr>
</table>
</body>
</html>

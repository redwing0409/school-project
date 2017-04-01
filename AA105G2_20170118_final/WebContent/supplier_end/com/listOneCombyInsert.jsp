<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.commodity.model.*"%>
<%
    ComService comSvc = new ComService();
    List<ComVO> list = comSvc.getAll();
    int listsize = list.size()-1;
    /* ���o�̫�@�������~�s�� */
    String com_no = list.get(listsize).getCom_no();
    /* �s�Jpage�A�H�Q�U���{�����o */
    pageContext.setAttribute("com_no",com_no);
%>
<html>
<head>
<title>supplier_end ���ӫ~��� - listOneCombyInsert.jsp</title>
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
		// �إ� websocket ����
		webSocket = new WebSocket(endPointURL);
		
		//�إ߳s�u
		webSocket.onopen = function(event) {
			document.getElementById('sendMessage').disabled = false;	
		};
		//����server�ǨӪ��T��
		webSocket.onmessage = function(event) {
	       var jsonObj = JSON.parse(event.data);
	       var message = jsonObj.message + "\r\n"; 
	       alert("�q�����\!!");
		};		
      }
 	
   //clinet�o�e���T��
   var com_no = null;
   function sendMessage() {
	   com_no = document.getElementById("com_no").value;
	   var jsonObj = {"com_no": com_no, "message" : "���s�~�W���o�A����h�ݬ�!!"};
	   webSocket.send(JSON.stringify(jsonObj));
   }
   
   function disconnect () {
		webSocket.close();
	}
</script>
</head>
<body bgcolor='white' onload="connectShopping();" onunload="disconnect();">
<%
  //�]�w��ܪ��ɶ��榡
  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
  pageContext.setAttribute("sf", sf);
%>
<h5 style="color:red">�ӫ~�s�W���\!</h5>
<div>
<button type="submit" id="sendMessage" class="btn btn-info" onclick="sendMessage();"/>�s���~�q��</button>
<input type="hidden" id="com_no" value="${com_no}"/>
</div>
<table  table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:80%">
	<tr>
		<th>�ӫ~�s��</th>
		<th>��������</th>
		<th>�ӫ~�W��</th>
		<th>�ӫ~�y�z</th>
		<th>�ӫ~����</th>
		<th>�ӫ~���A</th>
		<th>�W�[���</th>
		<th>�U�[���</th>
		<th>�ӫ~�Ƶ�</th>
		<th>�ӫ~�Ϥ�</th>
	</tr>
	<tr align='center' valign='middle'>
	    <td>${com_no}</td>
		<td>
		      <c:if test="${comVO3.pcm_no == 1}">�ߩ�</c:if>
	          <c:if test="${comVO3.pcm_no == 2}">�B���ۥ�</c:if>
	          <c:if test="${comVO3.pcm_no == 3}">�B§�p��</c:if>
	          <c:if test="${comVO3.pcm_no == 4}">�B§�Ϋ~</c:if>
	    </td>
		<td>${comVO3.com_name}</td>
		<td>${comVO3.com_desc}</td>
		<td>${comVO3.com_price}</td>
		<td>
		   <c:if test="${comVO3.com_status==1}">�W�[</c:if>
		   <c:if test="${comVO3.com_status==2}">�U�[</c:if>
		</td>
		<td>${sf.format(comVO3.com_shelf_date)}</td>
		<td>${sf.format(comVO3.com_off_date)}</td>
		<td>${comVO3.com_note}</td>
		<td><img src="<%=request.getContextPath()%>/DBGif.do?name=${com_no}" width='100'></td>	
	</tr>
</table>
</body>
</html>

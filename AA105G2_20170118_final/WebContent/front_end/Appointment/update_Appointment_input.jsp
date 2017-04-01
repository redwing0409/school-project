<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.appointment.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.sql.Date"%>
<%
	AppointmentVO appointmentVO = (AppointmentVO) request.getAttribute("appointmentVO"); //APPOINTMENTServlet.java (Concroller), �s�Jreq��APPOINTMENTVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
	MemberVO memberVO=(MemberVO)session.getAttribute("account");
	pageContext.setAttribute("memberVO", memberVO);
%>

<html>
<head>
<title>�w���q���ƭק� - update_Appointment_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

<script>
var arrayOri=[];
$(document).ready(function() {
	var forbidddenDate;
	var xhr = new XMLHttpRequest(); 
	var url= "<%=request.getContextPath()%>/GetDateJason.do?place_no=${param.place_no}";
						xhr.open("Get", url, true);
						xhr.send(null);
						xhr.onreadystatechange = function() {
							if (xhr.readyState == 4) {
								if (xhr.status == 200) {
									//console.log(xhr.responseText);
									forbidddenDate = JSON.parse(xhr.responseText);
									arrayOri = forbidddenDate.forbiddenDate;
									//console.log(arrayOri);

								} else
									alert(xhr.status);
							}
						};
						var sty = {
							dayNames : [ "�P����", "�P���@", "�P���G", "�P���T", "�P���|",
									"�P����", "�P����" ],
							dayNamesMin : [ "��", "�@", "�G", "�T", "�|", "��", "��" ],
							monthNames : [ "�@��", "�G��", "�T��", "�|��", "����", "����",
									"�C��", "�K��", "�E��", "�Q��", "�Q�@��", "�Q�G��" ],

							showMonthAfterYear : true,
							dateFormat : "yy-mm-dd",
							numberOfMonths : 2,
							showButtonPanel : true,
							minDate : -0,
							beforeShowDay : notcheck
						};

						function notcheck(date) {
							var arrayForbidden=[];
							arrayForbidden=arrayOri.concat(arrayFinal);
							//console.log(arrayForbidden);
							var string = jQuery.datepicker.formatDate(
									'yy-mm-dd', date);
							return [ arrayForbidden.indexOf(string) == -1 ];
						}
						
						$("#datepicker").datepicker(sty);
						$("#datepicker").datepicker("refresh");
					});

//�H�U��websocket
	var MyPoint = "/MyEchoServerCal";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var webSocket;
	var arrayFinal=[];

	function connect() {
		// �إ� websocket ����
				webSocket = new WebSocket(endPointURL);
				webSocket.onopen = function(event) {
			};
		
		var indexOfArray=0;
		var time=0;
		var date=document.getElementById("datepicker");
		var array=[];
		date.onchange=function(){
			if(time==0) {
				indexOfArray=array.length;
				array.push(date.value);
				var jsonObj={"place_no":${param.place_no},"forbiddenDate":date.value,"array":array,"indexOfArray":-1};
				webSocket.send(JSON.stringify(jsonObj));
			}
			else{
				var jsonObj={"place_no":${param.place_no},"forbiddenDate":date.value,"array":array,"indexOfArray":indexOfArray};
				webSocket.send(JSON.stringify(jsonObj));
			}
			time++;
		}	
		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			array = jsonObj.array;
			if(jsonObj.place_no==${param.place_no}){
					arrayFinal=array.concat(arrayOri);
			}
			$("#datepicker").datepicker("refresh");
		};

	}

	function disconnect() {
		webSocket.close();
	}

</script>



<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�w���q���ƭק� - update_Appointment_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/front_end/Appointment/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/Appointment/images/back1.gif" width="100" height="32" border="0">�^����</a></td>
	</tr>
</table>

<h3>��ƭק�:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="AppointmentServlet.do" name="form1">
<table border="0">
	<tr>
		<td>�q��s��:<font color=red><b>*</b></font></td>
		<td><%=appointmentVO.getApp_no()%></td>
		
	</tr>
	<tr>
		<td>���a�s��:<font color=red><b>*</b></font></td>
		<td><%=appointmentVO.getPlace_no()%><input type="hidden" name="place_no" value="<%=appointmentVO.getPlace_no()%>"></td>
	</tr>
	<tr>
		<td>�|���s��:<font color=red><b>*</b></font></td>
		<td><%=appointmentVO.getMember_no()%><input type="hidden" name="member_no" value="<%=appointmentVO.getMember_no()%>"></td>
	</tr>
	<tr>
		<td>�w���q�榨�ߤ��:</td>
		<td bgcolor="#CCCCFF"><%=appointmentVO.getApp_date()%><input type="hidden" name="app_date" value="<%=appointmentVO.getApp_date()%>">
		</td>
	</tr>
	<tr>
		<td>�w�����a���:</td>
		<td bgcolor="#CCCCFF">
		    <input type="text" id="datepicker" name="app_place_date" name="app_place_date" value="<%=appointmentVO.getApp_place_date()%>">		    
		</td>
	</tr>
	<tr>
		<td>�w���H�m�W:</td>
		<td>${memberVO.member_name}</td>
		<input type="hidden" name="app_name" value=${memberVO.member_name}>
	</tr>
	<tr>
		<td>�w���H�q��:</td>
		<td>${memberVO.member_mobile}</td>
		<input type="hidden" name="app_tel" value=${memberVO.member_mobile}>
	</tr>
</table>	

	
	<br>
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="app_status" value="<%=appointmentVO.getApp_status()%>">
	<input type="hidden" name="app_no" value="<%=appointmentVO.getApp_no()%>">
	<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
	<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:listAllPlace.jsp-->
	<input type="submit" value="�e�X�ק�">
</FORM>

<br>�e�X�ק諸�ӷ��������|:<br><b>
   <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br>
   <font color=blue>request.getParameter("whichPage"):</font> <%= request.getParameter("whichPage")%> </b>

</body>
</html>

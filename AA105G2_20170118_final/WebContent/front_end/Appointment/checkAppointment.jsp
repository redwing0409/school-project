<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.appointment.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.sql.Date"%>
<%@ page import="com.place.model.*"%>

<%
	AppointmentVO appointmentVO = (AppointmentVO) session.getAttribute("AppointmentVO");
%>

<html>
<head>
<title>預約訂單確認 - checkAppointment.jsp</title>
</head>



<body>
	<jsp:include page="/front_end/place/header.jsp" />
	<div id="intro">
		<!-- 這邊是大家頁面內容 -->
		<div id="bgpanel">
			<table border='1' cellpadding='5' cellspacing='0' width='100%'>
				<tr bgcolor='#c59b83' align='center' valign='middle' height='20'>
					<td>
						<h3>預約訂單確認</h3>
					</td>
				</tr>
			</table>

			<h3>訂單資料:</h3>
			<%-- 錯誤表列 --%>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/Appointment/AppointmentServlet.do"
				name="form1">
				<table border="0">
					<tr>
						<td>場地編號:</td>
						<td>${appointmentVO_S.place_no}</td>
						<input type="hidden" name="place_no" value="${appointmentVO_S.place_no}">
					</tr>
					<tr>
						<td>會員編號:</td>
						<td>${account.member_no}</td>
						<input type="hidden" name="member_no" value="${account.member_no}">
					</tr>
					<tr>
						<td>預約訂單成立日期:</td>
						<td>${appointmentVO_S.app_date}</td>
						<input type="hidden" name="app_date"
							value="${appointmentVO_S.app_date}">
					</tr>
					<tr>
						<td>預約場地日期:</td>
						<td>${appointmentVO_S.app_place_date}</td>
						<input type="hidden" name="app_place_date"
							value="${appointmentVO_S.app_place_date}">
					</tr>


					<tr>
						<td>預約人姓名:</td>
						<td>${account.member_name}</td>
					</tr>
					<tr>
						<td>預約人電話:</td>
						<td>${account.member_mobile}</td>
					</tr>



				</table>
				<br> <input type="hidden" name="action" value="insert">
				<input type="hidden" name="app_status" value="1"> <input
					type="submit" value="送出新增">
			</FORM>
			<Form ACTION=<%=request.getContextPath()%>/front_end/place/place.jsp>
				<input type="submit" value="放棄預約">
			</Form>
		</div>
	</div>
</body>

</html>

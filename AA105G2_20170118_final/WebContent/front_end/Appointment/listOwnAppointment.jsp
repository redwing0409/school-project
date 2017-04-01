<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.appointment.model.*"%>
<%@ page import="com.member.model.*"%>


<%
    AppointmentService appSvc = new AppointmentService();
	MemberVO memberVO=(MemberVO)session.getAttribute("account");
	pageContext.setAttribute("memberVO", memberVO);
    List<AppointmentVO> list = appSvc.getOwn(memberVO.getMember_no());
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有預約訂單資料 - listOwnAppointment.jsp</title>
</head>
<body>
<jsp:include page="/front_end/place/header.jsp" />
	<div id="intro">
		<!--#intro -->
		<!-- 這邊是大家頁面內容 -->
		<div id="bgpanel">
<table border='1' cellpadding='5' cellspacing='0' width='100%'>
	<tr bgcolor='#c59b83' align='center' valign='middle' height='20'>
		<td>
		<h3>預約訂單資料 </h3>
<%-- 		<a href="<%=request.getContextPath()%>/front_end/Appointment/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/Appointment/images/back1.gif" width="100" height="32" border="0">回首頁</a> --%>
<%-- 		<a href="<%=request.getContextPath()%>/front_end/Place/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/Place/images/back1.gif" width="100" height="32" border="0">查看所有場地</a> --%>
		</td>
	</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='0' bordercolor='#CCCCFF' width='100%' height='65%'>
	<tr>
		<th>訂單編號</th>
		<th>場地編號</th>
		<th>會員編號</th>
		<th>預約訂單成立日期</th>
		<th>預約場地日期</th>
		<th>預約訂單狀態</th>
		<th>預約人姓名</th>
		<th>預約人電話</th>
<!-- 		<th>修改</th> -->
<!-- 		<th>刪除</th> -->
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="appointmentVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${appointmentVO.app_no}</td>
			<td>${appointmentVO.place_no}</td>
			<td>${appointmentVO.member_no}(${memberVO.member_name})</td>
			<td>${appointmentVO.app_date}</td>
			<td>${appointmentVO.app_place_date}</td>
			<td>
				<c:if test="${appointmentVO.app_status == 1}">未付訂金</c:if>
	          	<c:if test="${appointmentVO.app_status == 2}">未付尾款</c:if>
	          	<c:if test="${appointmentVO.app_status == 3}">已結案</c:if>
	          	<c:if test="${appointmentVO.app_status == 4}">未退費</c:if>
	          	<c:if test="${appointmentVO.app_status == 5}">已取消</c:if>
			</td>
			<td>${memberVO.member_name}</td>
			<td>${memberVO.member_mobile}</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Appointment/AppointmentServlet.do"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="app_no" value="${appointmentVO.app_no}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Appointment/AppointmentServlet.do"> --%>
<!-- 			    <input type="submit" value="刪除"> -->
<%-- 			    <input type="hidden" name="app_no" value="${appointmentVO.app_no}"> --%>
<%-- 			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

<!-- <br>本網頁的路徑:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b> --%>
</div>
</div>
</body>

</html>

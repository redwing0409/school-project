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
<title>�Ҧ��w���q���� - listOwnAppointment.jsp</title>
</head>
<body>
<jsp:include page="/front_end/place/header.jsp" />
	<div id="intro">
		<!--#intro -->
		<!-- �o��O�j�a�������e -->
		<div id="bgpanel">
<table border='1' cellpadding='5' cellspacing='0' width='100%'>
	<tr bgcolor='#c59b83' align='center' valign='middle' height='20'>
		<td>
		<h3>�w���q���� </h3>
<%-- 		<a href="<%=request.getContextPath()%>/front_end/Appointment/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/Appointment/images/back1.gif" width="100" height="32" border="0">�^����</a> --%>
<%-- 		<a href="<%=request.getContextPath()%>/front_end/Place/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/Place/images/back1.gif" width="100" height="32" border="0">�d�ݩҦ����a</a> --%>
		</td>
	</tr>
</table>

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

<table border='0' bordercolor='#CCCCFF' width='100%' height='65%'>
	<tr>
		<th>�q��s��</th>
		<th>���a�s��</th>
		<th>�|���s��</th>
		<th>�w���q�榨�ߤ��</th>
		<th>�w�����a���</th>
		<th>�w���q�檬�A</th>
		<th>�w���H�m�W</th>
		<th>�w���H�q��</th>
<!-- 		<th>�ק�</th> -->
<!-- 		<th>�R��</th> -->
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
				<c:if test="${appointmentVO.app_status == 1}">���I�q��</c:if>
	          	<c:if test="${appointmentVO.app_status == 2}">���I����</c:if>
	          	<c:if test="${appointmentVO.app_status == 3}">�w����</c:if>
	          	<c:if test="${appointmentVO.app_status == 4}">���h�O</c:if>
	          	<c:if test="${appointmentVO.app_status == 5}">�w����</c:if>
			</td>
			<td>${memberVO.member_name}</td>
			<td>${memberVO.member_mobile}</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Appointment/AppointmentServlet.do"> --%>
<!-- 			     <input type="submit" value="�ק�"> -->
<%-- 			     <input type="hidden" name="app_no" value="${appointmentVO.app_no}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller--> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Appointment/AppointmentServlet.do"> --%>
<!-- 			    <input type="submit" value="�R��"> -->
<%-- 			    <input type="hidden" name="app_no" value="${appointmentVO.app_no}"> --%>
<%-- 			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--> --%>
<%-- 			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller--> --%>
<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

<!-- <br>�����������|:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b> --%>
</div>
</div>
</body>

</html>

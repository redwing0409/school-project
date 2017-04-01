<%@page import="com.groups.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	GroupsVO groupsVO = (GroupsVO) request.getAttribute("update_groups_input"); //GroupsServlet.java (Concroller)
	pageContext.setAttribute("groupsVO",groupsVO);
%>
<html>
<head>
<title>社群資料修改 - update_groups_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/groups/js/calendar.css">
<script language="JavaScript" src="<%=request.getContextPath()%>/front_end/groups/js/calendarcode.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/front_end/groups/js/image.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>社群資料修改 - update_groups_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/front_end/groups/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/groups/images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/groups/groupsServlet.do" name="form1">
<table border="0">
	<tr>
		<td>社群編號:<font color=red><b>*</b></font></td>
		<td><%=groupsVO.getGroups_no()%></td>
	</tr>
	<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
	<tr>
		<td>建立人姓名:</td>
		<td>
			<input type="hidden" name="groups_owner" value="<%=groupsVO.getGroups_owner()%>">
			
			<c:forEach var="memberVO" items="${memberSvc.all}">
                <c:if test="${groupsVO.groups_owner==memberVO.member_no}">
                 ${memberVO.member_name}
                </c:if>
            </c:forEach>
		</td>
	</tr>
	<tr>
		<td>社群名稱:</td>
		<td><input type="TEXT" name="groups_title" size="45" 
			value="<%= (groupsVO==null)? "" : groupsVO.getGroups_title()%>" /></td>
	</tr>
	<tr>
		<td>建立日期:</td>
		<td><%= groupsVO.getGroups_time() %>
			<input type="hidden" name="groups_time" value="<%= groupsVO.getGroups_time() %>">
		</td>
	</tr>

</table>
	
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="groups_no" value="<%=groupsVO.getGroups_no()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
<input type="submit" value="送出修改">

</FORM>
<!-- <br><br> -->
<!-- 	<hr> -->
<!-- 	<b> -->
<%-- 		<font color=blue>request.getServletPath():</font> <%= request.getParameter("requestURL")%><br> --%>
<%-- 	    <font color=blue>whichPage: </font> <%= request.getParameter("whichPage")%>  --%>
<!--     </b> -->
</body>
</html>

<%@page import="com.groups.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	GroupsVO groupsVO = (GroupsVO) request.getAttribute("addGroups");
%>

<html>
<head>
<title>���s��Ʒs�W - addGroups.jsp</title></head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/groups/js/calendar.css">
<script language="JavaScript" src="<%=request.getContextPath()%>/front_end/groups/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���s��Ʒs�W - addGroups.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/front_end/groups/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/groups/images/tomcat.gif" width="100" height="100" border="1">�^����</a>
	    </td>
	</tr>
</table>

<h3>���s���:</h3>
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

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/groups/groupsServlet.do" name="form1">
<br>
<table border="0">
	<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
	<tr>
		<td>�إߤH�m�W:</td>
		<td>
			<c:forEach var="memberVO" items="${memberSvc.all}">
                <c:if test="${account.member_acc==memberVO.member_acc}">
                 ${memberVO.member_name}
                 <input type="hidden" name="groups_owner" value="${memberVO.member_no}">
                </c:if>
            </c:forEach>
		</td>
	</tr>
	<tr>
		<td>���s�W��:</td>
		<td><input type="TEXT" name="groups_title" size="45" 
			value="<%= (groupsVO==null)? "" : groupsVO.getGroups_title()%>" /></td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>�إߤ��:</td>
		<td>
			<%= date_SQL%>
			<input type="hidden" name="groups_time" value="<%= date_SQL%>" >
		</td>
	</tr>

</table>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>

</html>

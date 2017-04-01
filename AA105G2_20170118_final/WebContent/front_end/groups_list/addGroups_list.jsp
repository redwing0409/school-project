<%@page import="com.groups_list.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Cache-Control","no-store"); 
	response.setHeader("Pragma","no-cache");        
	response.setDateHeader ("Expires", 0);

	Groups_ListVO groups_ListVO = (Groups_ListVO) request.getAttribute("groups_ListVO");
%>
<html>
<head>
<title>社群新增會員 - addGroups_list.jsp</title></head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/groups_list/js/calendar.css">
<script language="JavaScript" src="<%=request.getContextPath()%>/front_end/groups_list/js/calendarcode.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/front_end/groups_list/js/addGroups_list_Select.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>社群新增會員 - addGroups_list.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/front_end/groups_list/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/groups_list/images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>社群資料:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groups_list/groups_listServlet.do" name="form1">
<table border="0">

	<jsp:useBean id="groupsSvc" scope="page" class="com.groups.model.GroupsService" />
	<tr>
	   <td>社群編號:</td>
	   <td>
		<select size="1" name="groups_no" onChange="changed(this)">
         <c:forEach var="groupsVO" items="${groupsSvc.allFront}">
             <option value="${groupsVO.groups_no}" ${(groups_ListVO.groups_no==groupsVO.groups_no)? 'selected':'' }>${groupsVO.groups_title}-${groupsVO.groups_no}
         </c:forEach>   
        </select>
       </td>
	</tr>
	
	<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
	<jsp:useBean id="groups_listSvc" scope="page" class="com.groups_list.model.Groups_listService" />
	
	<tr>
		<td>會員編號:</td>
		<td>
			<div id="showPanel"></div>
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="加入">
</FORM>



</body>

</html>

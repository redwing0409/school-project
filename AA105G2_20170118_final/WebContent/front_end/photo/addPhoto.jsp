<%@page import="com.member.model.*"%>
<%@page import="java.util.*"%>
<%@page import="com.groups.model.*"%>
<%@page import="com.photo.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	PhotoVO photoVO = (PhotoVO) request.getAttribute("addPhoto");

	GroupsService groupsSvc = new GroupsService();
	List<GroupsVO> groupsVO = groupsSvc.getAllFront();
	
	List<GroupsVO> list = new ArrayList<GroupsVO>();
	MemberVO memberVO = (MemberVO)session.getAttribute("account");
	for(GroupsVO g_list:groupsVO){
			if(memberVO.getMember_no().equals(g_list.getGroups_owner()))
			list.add(g_list);
	}
	pageContext.setAttribute("list",list);
%>
<html>
<head>
<title>社群照片新增 - addPhoto.jsp</title></head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/photo/js/calendar.css">
<script language="JavaScript" src="<%=request.getContextPath()%>/front_end/photo/js/calendarcode.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/front_end/photo/js/image.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>社群照片新增 - addPhoto.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/front_end/photo/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/photo/images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>照片資料:</h3>
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
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<c:if test="${not empty list}">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/photo/photoServlet.do" name="form1" enctype="multipart/form-data">
	<table border="0">
		<tr>
			<td>會員編號:</td>
			<td>
				<c:forEach var="memberVO" items="${memberSvc.all}">
	                <c:if test="${account.member_acc==memberVO.member_acc}">
	                 ${memberVO.member_name}
	                 <input type="hidden" name="member_no" value="${memberVO.member_no}">
	                </c:if>
	            </c:forEach>
			</td>
		</tr>
		<tr>
			<td>社群編號:</td>
			<td>
				<select size="1" name="groups_no">
			        <c:forEach var="groupsVO" items="${list}">
				    	<option value="${groupsVO.groups_no}" ${(groups_ListVO.groups_no==groupsVO.groups_no)? 'selected':'' }>${groupsVO.groups_title}-${groupsVO.groups_no}
			        </c:forEach>  
		        </select>
			</td>
		</tr>
		<tr>
			<td>照片說明:</td>
			<td><input type="TEXT" name="photo_title" size="45" 
				value="<%= (photoVO==null)? "" : photoVO.getPhoto_title()%>" /></td>
		</tr>
		<tr>
			<td>照片:</td>
			<td><input type="file" id="theFile" name="photo_file" size="45"/></td>
		</tr>
		<tr>
			<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");%>
			<%java.sql.Timestamp date_SQL = new java.sql.Timestamp(System.currentTimeMillis());%>
			<td>日期/時間:</td>
			<td>
				<%= sdf.format(date_SQL)%>
				<input type="hidden" name="photo_createdate" value="<%= date_SQL%>" >
			</td>
		</tr>
	</table>
	<br>
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增">
</FORM>
<img id="image" width="20%">
</c:if>
<c:if test="${empty list}">
	<font color='red'><strong>此會員沒有建立任何社團</strong></font>
</c:if>
</body>

</html>

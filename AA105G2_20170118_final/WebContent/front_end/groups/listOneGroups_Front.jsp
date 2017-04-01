<%@page import="com.member.model.*"%>
<%@page import="com.groups.model.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  GroupsVO groupsVO = (GroupsVO) request.getAttribute("listOneGroups_Front"); //GroupsServlet.java(Concroller), 存入req的groupsVO物件
  request.setAttribute("groupsVO",groupsVO);
  MemberService memberSvc = new MemberService();
  MemberVO memberVO = memberSvc.getOneMember(groupsVO.getGroups_owner());
%>
<html>
<head>
<title>社群資料 - ListOneGroups_Front.jsp</title>
</head>
<%-- <script language="JavaScript" src="<%=request.getContextPath()%>/front_end/groups/js/Groups_Invite.js"></script> --%>
<body>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
			<h3><%=groupsVO.getGroups_title()%> 會員資料 - ListOneGroups_Front.jsp</h3>
			<a href="<%=request.getContextPath()%>/front_end/groups/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/groups/images/back1.gif" width="100" height="32" border="0">回首頁</a>
			</td>
		</tr>
	</table>
	
	<table border='1' bordercolor='#CCCCFF' width='800'>
		<tr>
			<th>社群編號</th>
			<th>建立人姓名</th>
			<th>社群名稱</th>
			<th>建立日期</th>
			<c:if test="${account.member_no==groupsVO.groups_owner}">
				<th>修改</th>
				<th>刪除</th>
			</c:if>
			<th>查詢成員</th>
			<th>查詢照片</th>
		</tr>
		
		<jsp:useBean id="groupsSvc" scope="page" class="com.groups.model.GroupsService" />
		
		<tr align='center' valign='middle'}>
			<td><%= groupsVO.getGroups_no()%></td>
			<td><%= memberVO.getMember_name() %></td>
			<td><%= groupsVO.getGroups_title()%></td>
			<td><%= groupsVO.getGroups_time()%></td>
			<c:if test="${account.member_no==groupsVO.groups_owner}">
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groups/groupsServlet.do">
				     <input type="submit" value="修改">
				     <input type="hidden" name="groups_no" value="<%= groupsVO.getGroups_no()%>">
				     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				     <input type="hidden" name="action"	value="getOne_For_Update">
				  </FORM>
				</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groups/groupsServlet.do">
				     <input type="submit" value="刪除">
				     <input type="hidden" name="groups_no" value="<%= groupsVO.getGroups_no()%>">
				     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				     <input type="hidden" name="action"	value="delete">
				  </FORM>
				</td>
			</c:if>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groups/groupsServlet.do">
				    <input type="submit" value="查詢成員"> 
				    <input type="hidden" name="groups_no" value="<%= groupsVO.getGroups_no()%>">
				    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				    <input type="hidden" name="action" value="listMembers_ByGroups_no">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/photo/photoServlet.do">
				    <input type="submit" value="查詢照片"> 
				    <input type="hidden" name="groups_no" value="${groupsVO.groups_no}">
				    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				    <input type="hidden" name="action" value="getOne_For_Display">
				</FORM>
			</td>
		</tr>
	</table>
	<%if (request.getAttribute("listOneByGroupsNo")!=null){%>
	       <jsp:include page="/front_end/groups_list/listOneByGroupsNo.jsp" />
	<%} %>
	
	<%if (request.getAttribute("listPhotoByGroups")!=null){%>
	       <jsp:include page="/front_end/photo/listPhotoByGroups.jsp" />
	<%} %>
</body>

</html>
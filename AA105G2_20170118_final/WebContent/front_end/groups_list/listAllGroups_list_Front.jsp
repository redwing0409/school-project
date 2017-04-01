<%@page import="com.groups.model.*"%>
<%@page import="com.groups_list.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Groups_listService groups_listSvc = new Groups_listService();
	GroupsService groupsSvc = new GroupsService();
	
	List<Groups_ListVO> list1 = groups_listSvc.getAll();
	List<GroupsVO> list_groups = groupsSvc.getAllFront();
	
	
	List<Groups_ListVO> list = new ArrayList<Groups_ListVO>();
	
	for(Groups_ListVO g_list:list1){
		for(GroupsVO g:list_groups){
			if(g_list.getGroups_no().equals(g.getGroups_no()))
			list.add(g_list);
		}
	}
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

<html>
<head>
<title>所有未被刪除的社群 - listAllGroups_list_Front.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有未被刪除的社群 - listAllGroups_list_Front.jsp</h3>
		<a href="<%=request.getContextPath()%>/front_end/groups_list/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/groups_list/images/back1.gif" width="100" height="32" border="0">回首頁</a>
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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>社群名稱</th>
		<th>會員</th>
		<th>刪除</th>
	</tr>
	<%@ include file="pages/page1.file" %>
	<c:forEach var="Groups_ListVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(Groups_ListVO.groups_no==param.groups_no && Groups_ListVO.member_no==param.member_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${Groups_ListVO.groups_no}
				<c:forEach var="groupsVO" items="${groupsSvc.allBack}">
                    <c:if test="${Groups_ListVO.groups_no==groupsVO.groups_no}">
 						${groupsVO.groups_title}
                    </c:if>
                </c:forEach>
			</td>
			<td>${Groups_ListVO.member_no}
				<c:forEach var="memberVO" items="${memberSvc.all}">
	                <c:if test="${Groups_ListVO.member_no==memberVO.member_no}">
	                 ${memberVO.member_name}
	                </c:if>
	            </c:forEach>
            </td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groups_list/groups_listServlet.do">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="groups_no" value="${Groups_ListVO.groups_no}">
			     <input type="hidden" name="member_no" value="${Groups_ListVO.member_no}">
			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="whichPage" value="<%=whichPage%>">
			     <input type="hidden" name="action"	value="delete">
			  </FORM>
			</td>
		</tr>
	</c:forEach>
	<%@ include file="pages/page2.file" %>
</table>

</body>
</html>

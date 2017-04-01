<%@page import="com.groups_list.model.*"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	List<Groups_ListVO> list = (List<Groups_ListVO>) request.getAttribute("listOneByGroupsNo"); 
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="groupsSvc" scope="page" class="com.groups.model.GroupsService" />
<html>
<head>
<title>社群成員 - listOneByGroupsNo.jsp</title>
<style>
.table-striped > tbody > tr:nth-of-type(2n+1) {
    background-color: white;
}
.table-striped > tbody > tr:nth-of-type(2n) {
    background-color: #f9dbe5;
}
#deleteGroupsMemberButton,#leaveGroupsButton {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 0px 16px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    margin: 4px 2px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    cursor: pointer;
}

#deleteGroupsMemberButton,#leaveGroupsButton {
    background-color: white; 
    color: black; 
    border: 2px solid #555555;
}

#deleteGroupsMemberButton:hover,#leaveGroupsButton:hover {
    background-color: #555555;
    color: white;
}

</style>
</head>
<body>
<div class="col-xs-12 col-sm-2"></div>
<div class="col-xs-12 col-sm-8" >
<div id="listGroupsMemberTitle" align="center" style="background-color:#39599c ; color:#FFFFFF">
<strong>${groupsVO.groups_title}</strong> &nbsp&nbsp  共<strong><%= list.size() %></strong>名成員
</div>
<table  width='100%' class="table table-hover table-striped table-bordered table-condensed">
	<c:forEach var="Groups_ListVO" items="${list}" >
		<tr align='center' valign='middle' ${(Groups_ListVO.member_no==param.member_no) ? 'bgcolor=#CCCCFF':''}>
			<td>
<%-- 				${Groups_ListVO.member_no} --%>
				<c:forEach var="memberVO" items="${memberSvc.all}">
	                <c:if test="${Groups_ListVO.member_no==memberVO.member_no}">
	                 ${memberVO.member_name}
	                </c:if>
	            </c:forEach>
            </td>
            <c:if test="${account.member_no==groupsVO.groups_owner}">
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groups_list/groups_listServlet.do">
				     <input type="submit" id="deleteGroupsMemberButton" value="刪除">
				     <input type="hidden" name="groups_no" value="${Groups_ListVO.groups_no}">
				     <input type="hidden" name="member_no" value="${Groups_ListVO.member_no}">
				     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				     <input type="hidden" name="action"	value="delete">
				  </FORM>
				</td>
			</c:if>
		</tr>
	</c:forEach>
</table>
</div>
<div class="col-xs-12 col-sm-2">
 	<c:if test="${account.member_no!=groupsVO.groups_owner}">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groups_list/groups_listServlet.do">
			<input type="submit" id="leaveGroupsButton"  value="離開社群">
			<input type="hidden" name="groups_no" value="${groupsVO.groups_no}">
			<input type="hidden" name="member_no" value="${account.member_no}">
			<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			<input type="hidden" name="leaveGroupsButton" value="leaveGroupsButton">
			<input type="hidden" name="action"	value="delete">
		</FORM>
	</c:if>
</div>
</body>
</html>
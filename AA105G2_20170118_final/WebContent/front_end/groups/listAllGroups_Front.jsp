<%@page import="com.groups.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	GroupsService groupsSvc = new GroupsService();
	List<GroupsVO> list = groupsSvc.getAllFront();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<%= request.getAttribute("noData")!=null %>
<html>
<head>
<title>�Ҧ����s��� - listAllGroups_Front.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ����s��� - listAllGroups_Front.jsp</h3>
		<a href="<%=request.getContextPath()%>/front_end/groups/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/groups/images/back1.gif" width="100" height="32" border="0">�^����</a>
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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>���s�s��</th>
		<th>�إߤH�m�W</th>
		<th>���s�W��</th>
		<th>�إߤ��</th>
		<th>�ק�</th>
		<th>�R��</th>
		<th>�d�ߦ���</th>
		<th>�d�߷Ӥ�</th>
	</tr>
	<%@ include file="pages/page1.file" %>
	<c:forEach var="groupsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(groupsVO.groups_no==param.groups_no) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
			<td>${groupsVO.groups_no}</td>
			<td>
				<c:forEach var="memberVO" items="${memberSvc.all}">
                    <c:if test="${groupsVO.groups_owner==memberVO.member_no}">
	                    ${memberVO.member_name}
                    </c:if>
                </c:forEach>
			</td>
			<td>${groupsVO.groups_title}</td>
			<td>${groupsVO.groups_time}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groups/groupsServlet.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="groups_no" value="${groupsVO.groups_no}">
			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="whichPage" value="<%=whichPage%>">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groups/groupsServlet.do">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="groups_no" value="${groupsVO.groups_no}">
			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="whichPage" value="<%=whichPage%>">
			     <input type="hidden" name="action"	value="delete">
			  </FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groups/groupsServlet.do">
				    <input type="submit" value="�d�߷|��"> 
				    <input type="hidden" name="groups_no" value="${groupsVO.groups_no}">
				    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     	<input type="hidden" name="whichPage" value="<%=whichPage%>">
				    <input type="hidden" name="action" value="listMembers_ByGroups_no">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/photo/photoServlet.do">
				    <input type="submit" value="�d�߷Ӥ�"> 
				    <input type="hidden" name="groups_no" value="${groupsVO.groups_no}">
				    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     	<input type="hidden" name="whichPage" value="<%=whichPage%>">
				    <input type="hidden" name="action" value="getOne_For_Display">
				</FORM>
			</td>
		</tr>
	</c:forEach>
	<%@ include file="pages/page2.file" %>
</table>
<%if (request.getAttribute("listOneByGroupsNo")!=null){%>
       <jsp:include page="/front_end/groups_list/listOneByGroupsNo.jsp" />
<%} %>

<%if (request.getAttribute("listPhotoByGroups")!=null){%>
       <jsp:include page="/front_end/photo/listPhotoByGroups.jsp" />
<%} %>

</body>
</html>

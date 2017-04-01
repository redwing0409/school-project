<%@page import="com.groups.model.*"%>
<%@page import="com.groups_list.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	Groups_listService groups_listSvc = new Groups_listService();
	List<Groups_ListVO> list = groups_listSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="groupsSvc" scope="page" class="com.groups.model.GroupsService" />

<html>
<head>
<title>�Ҧ����s����(�t���A) - listAllGroups_list.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ����s����(�t���A) - listAllGroups_list.jsp</h3>
		<a href="<%=request.getContextPath()%>/front_end/groups_list/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/groups_list/images/back1.gif" width="100" height="32" border="0">�^����</a>
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
		<th>���s�W��</th>
		<th>�|��</th>
		<th>���s���A</th>
		<th>�R��</th>
	</tr>
	<%@ include file="pages/page1.file" %>
	<c:forEach var="Groups_ListVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'><!--�N�ק諸���@���[�J����Ӥw-->
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
            	<c:forEach var="groupsVO" items="${groupsSvc.allBack}">
	                <c:if test="${Groups_ListVO.groups_no==groupsVO.groups_no}">
						<c:if test="${groupsVO.groups_status==1}">
						�s�b
	                	</c:if>
	                	<c:if test="${groupsVO.groups_status==0}">
						���s�w�R��
	                	</c:if>
	                </c:if>
                </c:forEach>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groups_list/groups_listServlet.do">
			     <input type="submit" value="�R��">
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

<%@page import="com.photo.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>
<%
	PhotoService photoSvc = new PhotoService();
	List<PhotoVO> list = photoSvc.getAllFront();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="groupsSvc" scope="page" class="com.groups.model.GroupsService" />
<html>
<head>
<title>�Ҧ����s�Ӥ� - listAllPhoto_Front.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ����s�Ӥ� - listAllPhoto_Front.jsp</h3>
		<a href="<%=request.getContextPath()%>/front_end/photo/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/photo/images/back1.gif" width="100" height="32" border="0">�^����</a>
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

<table border='1' bordercolor='#CCCCFF' width='100%'>
	<tr>
		<th>�Ӥ��s��</th>
		<th>�|���s��</th>
		<th>���s�s��</th>
		<th>�Ӥ�����</th>
		<th>�ɮ�</th>
		<th>���/�ɶ�</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="pages/page1.file" %>
	<c:forEach var="photoVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<jsp:useBean id="photoVO" scope="page" class="com.photo.model.PhotoVO"/>
		<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");%>
		<tr align='center' valign='middle' ${(photoVO.photo_no==param.photo_no) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
			<td>${photoVO.photo_no}</td>
			<td>${photoVO.member_no} - 
				<c:forEach var="memberVO" items="${memberSvc.all}">
                    <c:if test="${photoVO.member_no==memberVO.member_no}">
	                    ${memberVO.member_name}
                    </c:if>
                </c:forEach>
				
			</td>
			<td>${photoVO.groups_no} - 
				<c:forEach var="groupsVO" items="${groupsSvc.allFront}">
	                    <c:if test="${photoVO.groups_no==groupsVO.groups_no}">
		                    ${groupsVO.groups_title}
	                    </c:if>
                </c:forEach>
			</td>
			<td>${photoVO.photo_title}</td>
			<td>
				<img src="<%= request.getContextPath() %>/photo/ShowPhoto.do?photo_no=${photoVO.photo_no}" width="50%"/>
			</td>
			<td><%= sdf.format(photoVO.getPhoto_createdate()) %></td>
			<td>
			  <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/photo/photoServlet.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="photo_no" value="${photoVO.photo_no}">
			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="whichPage" value="<%=whichPage%>">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/photo/photoServlet.do">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="photo_no" value="${photoVO.photo_no}">
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

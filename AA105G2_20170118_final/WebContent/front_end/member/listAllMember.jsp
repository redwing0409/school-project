<%@page import="java.util.*"%>
<%@page import="com.member.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	MemberService memberSvc = new MemberService();
	List<MemberVO> list = memberSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�Ҧ��|����T</title>
</head>
<body>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
			<h3>�Ҧ��|����� - ListAllMember.jsp</h3>
			<a href="<%= request.getContextPath() %>/front_end/member/select_page.jsp"><img src="<%= request.getContextPath() %>/front_end/member/images/back1.gif" width="100" height="32" border="0">�^����</a>
			</td>
		</tr>
	</table>

	<table border='1' bordercolor='#CCCCFF' style="width:100%;height:70px;">
		<tr>
			<th>�|���s��</th>
			<th>�b��</th>
			<th>�K�X</th>
			<th>�m�W</th>
			<th>��}</th>
			<th>E-mail</th>
			<th>���</th>
			<th>�ʧO</th>
			<th>�ͤ�</th>
			<th>���U���</th>
			<th>�Ӥ�</th>
			<th>�ק�</th>
		</tr>
		<%@ include file="pages/page1.file" %>
		<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle' ${(memberVO.member_no==param.member_no) ? 'bgcolor=#CCCCFF':''}>
				<td>${memberVO.member_no}</td>
				<td>${memberVO.member_acc}</td>
				<td>${memberVO.member_pw}</td>
				<td>${memberVO.member_name}</td>
				<td>${memberVO.member_addr}</td>
				<td>${memberVO.member_email}</td>
				<td>${memberVO.member_mobile}</td>
				<td>${memberVO.member_sex}</td>
				<td>${memberVO.member_birthday}</td>
				<td>${memberVO.enroll_time}</td>
				<td>
					<img src="<%= request.getContextPath() %>/member/ShowMember_Pic.do?member_no=${memberVO.member_no}" width="20%"/>
				</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/memberServlet.do">
				     <input type="submit" value="�ק�">
				     <input type="hidden" name="member_no" value="${memberVO.member_no}">
				     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				     <input type="hidden" name="whichPage" value="<%=whichPage%>">
				     <input type="hidden" name="action"	value="getOne_For_Update">
				  </FORM>
				</td>
			</tr>
		</c:forEach>
		<%@ include file="pages/page2.file" %>
	</table>
<!-- 	<br><br> -->
<!-- 	<hr> -->
<!-- 	<b> -->
<%-- 	<font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br> --%>
<%--     <font color=blue>whichPage: </font> <%= whichPage%>  --%>
<!--     </b> -->
		
</body>
</html>
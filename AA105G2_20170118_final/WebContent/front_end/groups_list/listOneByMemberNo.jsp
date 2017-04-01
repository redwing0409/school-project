<%@page import="com.groups_list.model.*"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//GroupsServlet.java(Concroller), 存入req的List<Groups_ListVO>物件
	List<Groups_ListVO> list = (List<Groups_ListVO>) request.getAttribute("listOneByMemberNo"); 
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="groupsSvc" scope="page" class="com.groups.model.GroupsService" />
<html>
<head>
<title>會員參加的社群 - listOneByMemberNo.jsp</title>
</head>
<body>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>會員參加的社群 - listOneByMemberNo.jsp</h3>
		<a href="<%=request.getContextPath()%>/front_end/groups_list/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/groups_list/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>會員</th>
		<th>社群名稱</th>
		<th>刪除</th>
	</tr>
	<c:forEach var="Groups_ListVO" items="${list}" >
		<tr align='center' valign='middle' ${(Groups_ListVO.groups_no==param.groups_no) ? 'bgcolor=#CCCCFF':''}>
			
			<td>${Groups_ListVO.member_no}
				<c:forEach var="memberVO" items="${memberSvc.all}">
	                <c:if test="${Groups_ListVO.member_no==memberVO.member_no}">
	                 ${memberVO.member_name}
	                </c:if>
	            </c:forEach>
            </td>
            <td>${Groups_ListVO.groups_no}
				<c:forEach var="groupsVO" items="${groupsSvc.allBack}">
                    <c:if test="${Groups_ListVO.groups_no==groupsVO.groups_no}">
 						${groupsVO.groups_title}
                    </c:if>
                </c:forEach>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groups_list/groups_listServlet.do">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="groups_no" value="${Groups_ListVO.groups_no}">
			     <input type="hidden" name="member_no" value="${Groups_ListVO.member_no}">
			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="action"	value="delete">
			  </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
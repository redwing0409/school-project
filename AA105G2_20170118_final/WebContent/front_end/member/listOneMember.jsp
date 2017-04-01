<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.member.model.*"%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("listOneMember"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>會員資料 - listOneMember.jsp</title>
</head>
<body>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3><%=memberVO.getMember_name()%> 會員資料 - ListOneMember.jsp</h3>
		<a href="<%= request.getContextPath() %>/front_end/member/select_page.jsp"><img src="<%= request.getContextPath() %>/front_end/member/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' style="width:100%;height:70px;">
	<tr>
		<th>會員編號</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>姓名</th>
		<th>住址</th>
		<th>E-mail</th>
		<th>手機</th>
		<th>性別</th>
		<th>生日</th>
		<th>註冊日期</th>
		<th>照片</th>
<!-- 		<th>修改</th> -->
	</tr>
	<tr align='center' valign='middle'>
		<td><%=memberVO.getMember_no()%></td>
		<td><%=memberVO.getMember_acc()%></td>
		<td><%=memberVO.getMember_pw()%></td>
		<td><%=memberVO.getMember_name()%></td>
		<td><%=memberVO.getMember_addr()%></td>
		<td><%=memberVO.getMember_email()%></td>
		<td><%=memberVO.getMember_mobile()%></td>
		<td><%=memberVO.getMember_sex()%></td>
		<td><%=memberVO.getMember_birthday()%></td>
		<td><%=memberVO.getEnroll_time()%></td>
		<td>
			<img src="<%= request.getContextPath() %>/member/ShowMember_Pic.do?member_no=<%=memberVO.getMember_no()%>" width="20%"/>
		</td>
<!-- 		<td> -->
<%-- 		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/memberServlet.do"> --%>
<!-- 		     <input type="submit" value="修改"> -->
<%-- 		     <input type="hidden" name="member_no" value="${memberVO.member_no}"> --%>
<!-- 		     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 		</td> -->
		
	</tr>
</table>
</body>
</html>
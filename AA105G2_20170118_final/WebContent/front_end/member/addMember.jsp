<%@page import="com.member.model.MemberVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("addMember");
%>

<html>
<head>
<title>會員資料新增 - addMember.jsp</title></head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/member/js/calendar.css">
<script language="JavaScript" src="<%=request.getContextPath()%>/front_end/member/js/calendarcode.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/front_end/member/js/image.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>會員資料新增 - addMember.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/front_end/member/select_page.jsp"><img src="<%=request.getContextPath()%>/front_end/member/images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>會員資料:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/memberServlet.do" name="form1" enctype="multipart/form-data">
<table border="0">

	
	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="member_acc" size="45" 
			value="<%= (memberVO==null)? "" : memberVO.getMember_acc()%>" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="password" name="member_pw" size="45" 
			value="<%= (memberVO==null)? "" : memberVO.getMember_pw()%>" /></td>
	</tr>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="member_name" size="45" 
			value="<%= (memberVO==null)? "" : memberVO.getMember_name()%>" /></td>
	</tr>
	<tr>
		<td>住址:</td>
		<td><input type="TEXT" name="member_addr" size="45"
			value="<%= (memberVO==null)? "" : memberVO.getMember_addr()%>" /></td>
	</tr>
	<tr>
		<td>E-mail:</td>
		<td><input type="TEXT" name="member_email" size="45"
			value="<%= (memberVO==null)? "" : memberVO.getMember_email()%>" /></td>
	</tr>
	<tr>
		<td>手機:</td>
		<td><input type="TEXT" name="member_mobile" size="45" maxlength="10"
			value="<%= (memberVO==null)? "" : memberVO.getMember_mobile()%>" /></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td>
			<input type="radio" name="member_sex" size="45" 
			value="1" <%= (memberVO==null || memberVO.getMember_sex().equals(1))? "checked" : ""%>/>男
			
			<input type="radio" name="member_sex" size="45"
			value="0" <%= (memberVO==null || memberVO.getMember_sex().equals(1))? "" : "checked"%>/>女
		</td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL1 = new java.sql.Date(System.currentTimeMillis());%>
		<td>生日:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="member_birthday" value="<%= (memberVO==null)? date_SQL1 : memberVO.getMember_birthday()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','member_birthday','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="<%=request.getContextPath()%>/front_end/member/images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>
		</td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL2 = new java.sql.Date(System.currentTimeMillis());%>
		<td>註冊日期:</td>
		<td>
			<%= date_SQL2%>
			<input type="hidden" name="enroll_time" value="<%= date_SQL2%>" >
		</td>
	</tr>
	<tr>
		<td>照片:</td>
		<td><input type="file" id="theFile" name="member_pic" size="45"/></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

<img id="image" width="20%">

</body>

</html>

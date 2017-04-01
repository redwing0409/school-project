<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.member.model.*"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("/front_end/updateMemberInfo.jsp");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>把我打婚吧!!~會員資料修改</title>
<link rel="stylesheet"	href="<%=request.getContextPath()%>/front_end/css/bootstrap.min.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/image.js"></script>	


<style type="text/css">
	
.modal-body{
   border-radius:50%;
   height:450px;
   width: 100%;
   background:linear-gradient(top,#ffffff,rgba(51,51,51,0.4)),url(<%=request.getContextPath()%>/front_end/images/logo/MarryMe-logo.png) center   no-repeat;
   background:-moz-linear-gradient(top,#ffffff,rgba(51,51,51,0.4)),url(<%=request.getContextPath()%>/front_end/images/logo/MarryMe-logo.png) center  no-repeat;
   background:-webkit-linear-gradient(top,#ffffff,rgba(51,51,51,0.4)),url(<%=request.getContextPath()%>/front_end/images/logo/MarryMe-logo.png) center  no-repeat; 
}	
#aa{
	margin-top: 50px;
}

body{
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
    background:url(<%=request.getContextPath()%>/front_end/images/secondBG.png) 50% 0 no-repeat fixed;     
}			
</style>
</head>
<body>

<div class="col-xs-12 col-sm-2">
	<c:if test="${not empty errorMsgsFromUpdate}">
		<div id="errorMsgsArea3">
			<font color='red'>請修正以下錯誤:
				<ul>
					<c:forEach var="message" items="${errorMsgsFromUpdate}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</div>
	</c:if>
</div>

<div class="col-xs-12 col-sm-8" id="aa">
<div class="modal-header">
	<h2 class="modal-title">修改會員資料</h2>
</div>

<!-- 輸入內容開始 -->
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/memberServlet.do" name="form1" enctype="multipart/form-data" id="updateMemberData">
	<div class="modal-body place">
		
		<div class="col-xs-12 col-sm-6">
			<div class="input-group">
				<div class="input-group-addon">
					密碼
				</div>
				<input type="password" name="member_pw" id="member_pw" class="form-control" value="${account.member_pw}">					
			</div>						
			<br>
			<div class="input-group">
				<div class="input-group-addon">
					地址
				</div>
				<input type="text" name="member_addr" id="member_addr" class="form-control" value="${account.member_addr}">	
			</div>	
			<br>
			<div class="input-group">
				<div class="input-group-addon">
					行動電話
				</div>
				<input type="number" name="member_mobile" id="member_mobile" class="form-control" maxlength="10" value="${account.member_mobile}" >							
			</div>	
			<br>
		</div>
		<div class="col-xs-12 col-sm-6">
			<div>
				<img src="<%= request.getContextPath() %>/member/ShowMember_Pic.do?member_no=${account.member_no}" width="20%"/>
				 <img id="image" style="width: 200px; height: 200px">	
				 <input type="file" id="theFile" name="member_pic" size="45"/>	
			</div>				 
		</div>
	</div> 
	<!-- 輸入內容結束 -->
	<div class="modal-footer">
		<input type="hidden" name="member_no" value="${account.member_no}">
		<input type="hidden" name="member_acc" value="${account.member_acc}">
		<input type="hidden" name="member_name" value="${account.member_name}">
		<input type="hidden" name="member_email" value="${account.member_email}">
		<input type="hidden" name="member_birthday" value="${account.member_birthday}">
		<input type="hidden" name="enroll_time" value="${account.enroll_time}">
		<input type="hidden" name="member_sex" value="${account.member_sex}">
	    <input type="hidden" name="action" value="update">
	    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
		<button type="button" class="btn btn-success"  data-toggle="modal" onclick="submit();">送出</button>
		<script> function submit(){$("#updateMemberData").submit();}</script>
	</div>
</FORM>
</div>

<div class="col-xs-12 col-sm-2">
</div>
</body>
</html>
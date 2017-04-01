<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.member.model.*"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("/front_end/frontIndex.jsp");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>把我打婚吧!!~會員註冊</title>
<link rel="stylesheet"	href="<%=request.getContextPath()%>/front_end/css/bootstrap.min.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/image.js"></script>	

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

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
	<c:if test="${not empty errorMsgsFromInsert}">
		<div id="errorMsgsArea1">
			<font color='red'>請修正以下錯誤:
				<ul>
					<c:forEach var="message" items="${errorMsgsFromInsert}">
					<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</div>
	</c:if>
</div>

<div class="col-xs-12 col-sm-8" id="aa">
<div class="modal-header">
	<h2 class="modal-title">會員資料輸入</h2>
</div>

<!-- 輸入內容開始 -->
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/memberServlet.do" name="form1" enctype="multipart/form-data" id="regist">
	<div class="modal-body place">
		
		<div class="col-xs-12 col-sm-6">
			<div class="input-group">
				<div class="input-group-addon">
					帳號
				</div>
				<input type="text" name="member_acc" id="member_acc" class="form-control" value="<%= (memberVO==null)? "" : memberVO.getMember_acc()%>">							
			</div>
			<br>
			<div class="input-group">
				<div class="input-group-addon">
					密碼
				</div>
				<input type="password" name="member_pw" id="member_pw" class="form-control" value="<%= (memberVO==null)? "" : memberVO.getMember_pw()%>">							
			</div>						
			<br>
			<div class="input-group">
				<div class="input-group-addon">
					姓名
				</div>
				<input type="text" name="member_name" id="member_name" class="form-control" value="<%= (memberVO==null)? "" : memberVO.getMember_name()%>">							
				</div>		
			<br>
			<div class="input-group">
				<div class="input-group-addon">
					地址
				</div>
				<input type="text" name="member_addr" id="member_addr" class="form-control" value="<%= (memberVO==null)? "" : memberVO.getMember_addr()%>">							
			</div>	
			<br>
			<div class="input-group">
				<div class="input-group-addon">
					電子信箱
				</div>
				<input type="text" name="member_email" id="member_email" class="form-control" value="<%= (memberVO==null)? "" : memberVO.getMember_email()%>"/>							
			</div>	
			<br>
			<div class="input-group">
				<div class="input-group-addon">
					行動電話
				</div>
				<input type="number" name="member_mobile" id="member_mobile" class="form-control" maxlength="10" value="<%= (memberVO==null)? "" : memberVO.getMember_mobile()%>" >							
			</div>	
			<br>
			<div class="input-group">
				<div class="input-group-addon">
					出生日期
				</div>
				<input type="date" name="member_birthday" id="member_birthday" class="form-control">							
			</div>	
			<br>
			<%java.sql.Date date_SQL2 = new java.sql.Date(System.currentTimeMillis());%>
			<input type="hidden" name="enroll_time" id="enroll_time" class="form-control" value="<%=date_SQL2%>">							
		</div>
		<div class="col-xs-12 col-sm-6">
			<div>
				<label for="member_sex">性別:</label><br> 
				<label class="radio-inline">
				<input type="radio" name="member_sex" value="1" <%= (memberVO==null || memberVO.getMember_sex().equals(1))? "checked" : ""%>>男性</label>
				<label class="radio-inline">
				<input type="radio" name="member_sex" value="0" <%= (memberVO==null || memberVO.getMember_sex().equals(1))? "" : "checked"%>>女性</label>						      					    
			</div>
			<br>
			<div>
				<img id="image" width = "200px" height = "200px">	
				<input type="file" id="theFile" name="member_pic" size="45"/>	
			</div>				 
		</div>
	</div> 
	<!-- 輸入內容結束 -->
	<div class="modal-footer">
		<input type="hidden" name="action" value="insert">
		<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
		<button type="button" class="btn btn-success"  data-toggle="modal" onclick="submit();">送出</button>
		<input type="button" id="incredibleButton"  value="一鍵輸入">
		<script> function submit(){$("#regist").submit();}</script>
	</div>
</FORM>
</div>

<div class="col-xs-12 col-sm-2">
</div>
</body>

<script type="text/javascript">
$(document).ready(function(){
	  $('#incredibleButton').click(function(e){
		  $('#member_acc').attr("value","aaa111");
		  $('#member_pw').val("abc123");
		  $('#member_name').attr("value","金城武");
		  $('#member_addr').attr("value","台北市士林區士林夜市");
          $('#member_email').attr("value","abcd11@gmail.com");
          $('#member_mobile').attr("value","0911416931");
          $('#member_birthday').attr("value","2017-01-19");
		}); 
});
</script>
</html>
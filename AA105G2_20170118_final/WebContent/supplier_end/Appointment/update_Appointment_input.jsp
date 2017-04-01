<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.appointment.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.sup.model.*"%>

<%
AppointmentVO appointmentVO = (AppointmentVO) request.getAttribute("appointmentVO"); //APPOINTMENTServlet.java (Concroller), 存入req的APPOINTMENTVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<jsp:useBean id="PlaceSvc" scope="page" class="com.place.model.PlaceService" />
<jsp:useBean id="MemSvc" scope="page" class="com.member.model.MemberService" />

<html>
<head>
		<title>預約訂單資料修改 - update_Appointment_input.jsp</title>
		<meta charset="big5">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/css/orderQuery.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/Appointment/css/table2.css">
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<script src="https://code.jquery.com/jquery.js"></script>
	    <script src="<%=request.getContextPath()%>/supplier_end/Appointment/js/jquery-ui.js"></script>
		<script>
			 $(function() {
				var $j = jQuery.noConflict();
				$j("#datepicker1,#datepicker2").datepicker({
					    dateFormat:"yy-mm-dd",
					    showOtherMonths: true,
					    selectOtherMonths: true,
						changeMonth: true,
						changeYear: true
					});
				});
		</script>
</head>

<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>
        <nav class="navbar navbar-default" role="navigation">
			<!-- 手機隱藏選單區 -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 左導覽 -->
				<div class="col-xs-12 col-sm-4 pull-left">
						<img src="<%=request.getContextPath()%>/supplier_end/images/MarryMe-logo.png" id="logo">
						<img src="<%=request.getContextPath()%>/supplier_end/images/MarryMe-Word.png" id="logo-word">
				</div>
				<!-- 左導覽結束 -->
			
				<!-- 右導覽 -->
				<div class="col-xs-12  col-sm-offset-4 col-sm-4">
					<ul class="nav navbar-nav navbar-right">
						<li class="login option_text"><a href="#"><font size="3" color="orange">${supAccount.sup_name}</font> ，您好</a></li>
						<li class="login option_text"><a href="<%=request.getContextPath()%>/sup/sup.do?action=logout">登出</a></li>
						<li class="login option_text"><a href="<%=request.getContextPath()%>/sup/sup.do?action=getOne_For_Update&sup_no=${supAccount.sup_no}&number=1">個人設定</a></li>		
					</ul>
				</div>
				<!-- 右導覽結束 -->
			</div>
			<!-- 手機隱藏選單區結束 -->
		</nav>
		<!--左邊功能選單-->
         <!--左邊會館功能選單-->
         <div class="divider"></div>
         <div class="col-xs-12 col-sm-2">
			<div class="panel-group">
				<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class=" glyphicon glyphicon-home"></i>
			        		<a href="<%=request.getContextPath()%>/supplier_end/sup_firstpage.jsp">HOME</a>
			     		 </h4>
			    	</div>
			    </div>
			    <div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-cog"></i>
			        		<a data-toggle="collapse" href="#collapse1">喜宴場地管理</a>
			     		 </h4>
			    	</div>
			    	<div id="collapse1" class="panel-collapse collapse">
			     	 <ul class="list-group">
			        	<li class="list-group-item"><i class="glyphicon glyphicon-tasks"></i><a href="<%=request.getContextPath()%>/sup/sup.do?action=listPlace_BySup_no_A&sup_no=${supAccount.sup_no}">
			        	場地查詢</a></li>
			        	<li class="list-group-item"><i class="glyphicon glyphicon-plus"></i><a href="<%=request.getContextPath()%>/supplier_end/Place/addPlace.jsp">
			        	新增場地</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			  	<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-th-list"></i>
			        		<a data-toggle="collapse" href="#collapse2">菜單管理</a>
			     		 </h4>
			    	</div>
			    	<div id="collapse2" class="panel-collapse collapse">
			     	 <ul class="list-group">
			         	<li class="list-group-item"><i class="glyphicon glyphicon-tags"></i><a href="<%=request.getContextPath()%>/supplier_end/menu/listSupMenus.jsp">菜單</a></li>
			        	<li class="list-group-item"><i class="glyphicon glyphicon-plus"></i><a href="<%=request.getContextPath() %>/supplier_end/menu/addMenu.jsp">新增菜單</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			  	<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-shopping-cart"></i>
			        		<a data-toggle="collapse" href="#collapse3">客戶預約管理</a>
			     		</h4>
			    	</div>
			    	<div id="collapse3" class="panel-collapse collapse">
			     	 <ul class="list-group">
			        	<li class="list-group-item"><i class="glyphicon glyphicon-tags"></i><a href="<%=request.getContextPath()%>/supplier_end/Appointment/listSupAppointment.jsp">歷史清單</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			</div>
			 <!--左邊會館功能選單結束-->
			</div>
			
			<div class="divider"></div>
			        <div class="col-xs-12 col-sm-10">
				         <div class="row"> 
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font color='red'>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="list-style-type:none;">${message}</li>
									</c:forEach>
								</ul>
								</font>
							</c:if>	
							<FORM METHOD="post" ACTION="AppointmentServlet.do" name="form1">
							<h4 style="margin-left:80px;font-weight:bold">修改預約訂單資料</h4>  
							<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:auto">
								<tr>
									<td>訂單編號:</td>
									<td><%=appointmentVO.getApp_no()%></td>							
								</tr>
								<tr>
									<td>場地名稱:</td>
									<td>
										<c:forEach var="PlaceVO" items="${PlaceSvc.all}">
										 <c:if test="${appointmentVO.place_no==PlaceVO.place_no}">
	   										${PlaceVO.place_name}</font>
	   									</c:if>
									    </c:forEach>
										<input type="hidden" name="place_no" value="<%=appointmentVO.getPlace_no()%>">
									</td>
								</tr>
								<tr>
									<td>會員姓名:</td>
									<td>
									<c:forEach var="MemberVO" items="${MemSvc.all}">
									<c:if test="${appointmentVO.member_no==MemberVO.member_no}">
   										${MemberVO.member_name}
   									</c:if>
								    </c:forEach>
									<input type="hidden" name="member_no" value="<%=appointmentVO.getMember_no()%>">
									</td>
								</tr>
								<tr>
									<td>預約訂單成立日期<font color=red><b>*</b></font>:</td>
									<td>
									   <input id="datepicker1" type="text" class="form-control" name="app_date" value="<%=appointmentVO.getApp_date()%>" size="20"></input>
									</td>								
								</tr>
								<tr>
									<td>預約場地日期<font color=red><b>*</b></font>:</td>
									<td>
									    <input id="datepicker2" type="text" class="form-control" name="app_place_date" value="<%=appointmentVO.getApp_place_date()%>" size="20"></input>
									</td>
								</tr>
								<tr>
									<td>預約訂單狀態:</td>
									<td>
										<select size="1" name="app_status">
							         		<option value="1" ${(1==appointmentVO.app_status)?'selected':''}>未結案
											<option value="2" ${(2==appointmentVO.app_status)?'selected':''}>已結案
							  			</select>
									</td>
								</tr>							
							</table>			
								<br>
								<input type="hidden" name="action" value="update">
								<input type="hidden" name="app_no" value="<%=appointmentVO.getApp_no()%>">
								<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
								<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--只用於:listAllPlace.jsp-->
								<input type="submit" value="送出修改" style="margin-top:-35px">
							</FORM>
					</div>
			  </div>
                    <!--footer開始-->
					<div class="divider"></div>
				  	
					<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>

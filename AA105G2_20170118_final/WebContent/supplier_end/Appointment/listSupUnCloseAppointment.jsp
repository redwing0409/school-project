<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.appointment.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.sup.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    AppointmentService appSvc = new AppointmentService();
	SupVO supVO=(SupVO)session.getAttribute("supAccount");
    List<AppointmentVO> list1 = appSvc.getAppBySup(supVO.getSup_no());
    List<AppointmentVO> list = new ArrayList<AppointmentVO>();
    for(AppointmentVO vo:list1){
    	Integer status1 = vo.getApp_status();
    	if(status1.equals(1)){
    		list.add(vo);
    	}
    }
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="PlaceSvc" scope="page" class="com.place.model.PlaceService" />
<jsp:useBean id="MemSvc" scope="page" class="com.member.model.MemberService" />

<html>
<head>
		<title>所有預約訂單資料 - listSupAppointment.jsp</title>
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
			$j("#datepicker").datepicker({
					showOn : "button",
					buttonImage : "images/calendar.gif",
					buttonImageOnly : true,
					changeMonth: true,
					changeYear: true
				});
			});
	   </script>
</head>
<body>
		<div class="divider"></div>
	        <div class="col-xs-12 col-sm-10">
		       <div class="row"> 
				<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue">
					  <tr style="font-weight:bold">
						<th>訂單編號</th>
						<th>場地名稱</th>
						<th>會員姓名</th>
						<th>會員地址</th>
						<th>會員手機</th>
						<th>預約訂單成立日期</th>
						<th>預約場地日期</th>
						<th>預約訂單狀態</th>
						<th>修改</th>
					</tr>
					<%@ include file="pages/page1.file" %> 
					<c:forEach var="appointmentVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr align='center' valign='middle' ${(appointmentVO.app_no==param.app_no) ? 'bgcolor=lightblue':''}>
							<td>${appointmentVO.app_no}</td>
							<td>
							    <c:forEach var="PlaceVO" items="${PlaceSvc.all}">
									 <c:if test="${appointmentVO.place_no==PlaceVO.place_no}">
   										${PlaceVO.place_name}
   									</c:if>
								</c:forEach>
							</td>
							<td>
								<c:forEach var="MemberVO" items="${MemSvc.all}">
									 <c:if test="${appointmentVO.member_no==MemberVO.member_no}">
   										${MemberVO.member_name}
   									</c:if>
								</c:forEach>
							</td>
							<td>
								<c:forEach var="MemberVO" items="${MemSvc.all}">
									 <c:if test="${appointmentVO.member_no==MemberVO.member_no}">
   										${MemberVO.member_addr}
   									</c:if>
								</c:forEach>
							</td>
							<td>
								<c:forEach var="MemberVO" items="${MemSvc.all}">
									 <c:if test="${appointmentVO.member_no==MemberVO.member_no}">
   										${MemberVO.member_mobile}
   									</c:if>
								</c:forEach>
							</td>
							<td>${appointmentVO.app_date}</td>
							<td>${appointmentVO.app_place_date}</td>
							<td>
								<c:if test="${appointmentVO.app_status == 1}">未結案</c:if>
							</td>
							<td>
							<Form METHOD="post" ACTION="<%=request.getContextPath()%>/Appointment/AppointmentServlet.do">
							<input type="submit" value="修改">
							<input type="hidden" name="app_no" value="${appointmentVO.app_no}">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
							<input type="hidden" name="whichPage" 	value="<%=whichPage%>">  <!--只用於:listAllPlace.jsp-->
							<input type="hidden" name="action" value="getOne_For_Update">
							</Form>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="pages/page2.file" %>
				</div>
			</div>
			<!--footer開始-->
		<div class="divider"></div>
	  
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>

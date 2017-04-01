<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.appointment.model.*"%>
<%@ page import="com.member.model.*"%>


<%
    AppointmentService appSvc = new AppointmentService();
	MemberVO memberVO=(MemberVO)session.getAttribute("account");
	pageContext.setAttribute("memberVO", memberVO);
    List<AppointmentVO> list = appSvc.getOwn(memberVO.getMember_no());
    pageContext.setAttribute("list",list);
%>
<html>
	<head>		
		<title>預約訂單資料</title>       
		<style>
			* {
				font-family:      'Lucida Grande', 'Hiragino Kaku Gothic ProN', 'ヒラギノ角ゴ ProN W3', Meiryo, 'メイリオ', sans-serif;
				font-size:        98.5%;
			}
			h1 {
				font-size:        46px;
				margin-bottom:    12px;
			}
			.container {
				width:            940px;
				margin:           auto;
			}
			iframe {
				border: solid 1px #000;
			}
		</style>
		<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<!-- 載入樣式 -->
		<link rel="stylesheet"   href="<%=request.getContextPath()%>/front_end/Appointment/DataTables-1.9.4/media/css/jquery.dataTables.css">
		<!-- Themeroller的主題 -->
		<link rel="stylesheet"   href="<%=request.getContextPath()%>/front_end/Appointment/DataTables-1.9.4/media/css/jquery.dataTables_themeroller.css">
		<!-- 載入jQuery  -->
		<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/Appointment/DataTables-1.9.4/media/js/jquery.js"></script>
		<!-- 載入DataTables  -->
		<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/Appointment/DataTables-1.9.4/media/js/jquery.dataTables.js"></script>
		<script>
			(function() {
				$(function() {
					$('#datatable').dataTable({
						"oLanguage": {
						    "sSearch": "搜尋:",
						    "sLengthMenu": "顯示件數 ：_MENU_",
						    "sInfo": "_TOTAL_件中，從第_START_件顯示到第_END_件",
						    "sInfoFiltered": " ( _MAX_件中搜尋 )",
						    "sZeroRecords": "找無資料。",
						    "sInfoEmpty": "0 件",
						    "oPaginate": {
						        "sFirst": "最初",
						        "sLast": "最後",
						        "sPrevious": "上一頁",
						        "sNext": "下一頁"
						    }
						},
						"iDisplayLength" : 10,
					});
				});
			})();
		</script>
	</head>
	<body>
	<jsp:include page="/front_end/place/header.jsp" />
	<div id="intro">
		<!-- 這邊是大家頁面內容 -->
		<div id="bgpanel">
			<div class="container">
				<div>
					<h1 style="margin-top: 40px">預約訂單資料</h1>				
				</div>
				<hr />
				<div>
					<table id="datatable" width="80%">
						<thead>
						<c:forEach var="appointmentVO" items="${list}">
							<tr>
								<th>訂單編號</th>
								<th>場地編號</th>
								<th>會員編號</th>
								<th>預約訂單成立日期</th>
								<th>預約場地日期</th>
								<th>預約訂單狀態</th>
								<th>預約人姓名</th>
								<th>預約人電話</th>
								<th>修改</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${appointmentVO.app_no}</td>
								<td>${appointmentVO.place_no}</td>
								<td>${appointmentVO.member_no}(${memberVO.member_name})</td>
								<td>${appointmentVO.app_date}</td>
								<td>${appointmentVO.app_place_date}</td>
								<td>
									<c:if test="${appointmentVO.app_status == 1}">未付訂金</c:if>
						          	<c:if test="${appointmentVO.app_status == 2}">未付尾款</c:if>
						          	<c:if test="${appointmentVO.app_status == 3}">已結案</c:if>
						          	<c:if test="${appointmentVO.app_status == 4}">未退費</c:if>
						          	<c:if test="${appointmentVO.app_status == 5}">已取消</c:if>
								</td>
								<td>${memberVO.member_name}</td>
								<td>${memberVO.member_mobile}</td>
								<td>
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Appointment/AppointmentServlet.do">
								     <input type="submit" value="修改">
								     <input type="hidden" name="app_no" value="${appointmentVO.app_no}">
								     <input type="hidden" name="action"	value="getOne_For_Update">
							     </FORM>
								</td>
							</tr>
						</c:forEach>	
						</tbody>
					</table>
				</div>
				<div class="sixteen columns">
					<hr />
				</div>
			</div>
		</div>
	</div>
	</body>
</html>
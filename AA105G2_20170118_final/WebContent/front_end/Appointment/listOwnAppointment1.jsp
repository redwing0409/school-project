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
		<title>�w���q����</title>       
		<style>
			* {
				font-family:      'Lucida Grande', 'Hiragino Kaku Gothic ProN', '�jǣ�F�f���L ProN W3', Meiryo, '�y��Ǥ�B', sans-serif;
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
		<!-- ���J�˦� -->
		<link rel="stylesheet"   href="<%=request.getContextPath()%>/front_end/Appointment/DataTables-1.9.4/media/css/jquery.dataTables.css">
		<!-- Themeroller���D�D -->
		<link rel="stylesheet"   href="<%=request.getContextPath()%>/front_end/Appointment/DataTables-1.9.4/media/css/jquery.dataTables_themeroller.css">
		<!-- ���JjQuery  -->
		<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/Appointment/DataTables-1.9.4/media/js/jquery.js"></script>
		<!-- ���JDataTables  -->
		<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/Appointment/DataTables-1.9.4/media/js/jquery.dataTables.js"></script>
		<script>
			(function() {
				$(function() {
					$('#datatable').dataTable({
						"oLanguage": {
						    "sSearch": "�j�M:",
						    "sLengthMenu": "��ܥ�� �G_MENU_",
						    "sInfo": "_TOTAL_�󤤡A�q��_START_����ܨ��_END_��",
						    "sInfoFiltered": " ( _MAX_�󤤷j�M )",
						    "sZeroRecords": "��L��ơC",
						    "sInfoEmpty": "0 ��",
						    "oPaginate": {
						        "sFirst": "�̪�",
						        "sLast": "�̫�",
						        "sPrevious": "�W�@��",
						        "sNext": "�U�@��"
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
		<!-- �o��O�j�a�������e -->
		<div id="bgpanel">
			<div class="container">
				<div>
					<h1 style="margin-top: 40px">�w���q����</h1>				
				</div>
				<hr />
				<div>
					<table id="datatable" width="80%">
						<thead>
						<c:forEach var="appointmentVO" items="${list}">
							<tr>
								<th>�q��s��</th>
								<th>���a�s��</th>
								<th>�|���s��</th>
								<th>�w���q�榨�ߤ��</th>
								<th>�w�����a���</th>
								<th>�w���q�檬�A</th>
								<th>�w���H�m�W</th>
								<th>�w���H�q��</th>
								<th>�ק�</th>
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
									<c:if test="${appointmentVO.app_status == 1}">���I�q��</c:if>
						          	<c:if test="${appointmentVO.app_status == 2}">���I����</c:if>
						          	<c:if test="${appointmentVO.app_status == 3}">�w����</c:if>
						          	<c:if test="${appointmentVO.app_status == 4}">���h�O</c:if>
						          	<c:if test="${appointmentVO.app_status == 5}">�w����</c:if>
								</td>
								<td>${memberVO.member_name}</td>
								<td>${memberVO.member_mobile}</td>
								<td>
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Appointment/AppointmentServlet.do">
								     <input type="submit" value="�ק�">
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
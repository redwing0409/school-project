<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.appointment.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.sup.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    AppointmentService appSvc = new AppointmentService();
	SupVO supVO=(SupVO)session.getAttribute("supAccount");
    List<AppointmentVO> list1 = appSvc.getAppBySup(supVO.getSup_no());
    List<AppointmentVO> list = new ArrayList<AppointmentVO>();
    for(AppointmentVO vo:list1){
    	Integer status1 = vo.getApp_status();
    	if(status1.equals(2)){
    		list.add(vo);
    	}
    }
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="PlaceSvc" scope="page" class="com.place.model.PlaceService" />
<jsp:useBean id="MemSvc" scope="page" class="com.member.model.MemberService" />

<html>
<head>
		<title>�Ҧ��w���q���� - listSupAppointment.jsp</title>
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
<nav class="navbar navbar-default" role="navigation">
			
			<!-- ������ÿ��� -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- ������ -->
				<div class="col-xs-12 col-sm-4 pull-left">
						<img src="<%=request.getContextPath()%>/supplier_end/images/MarryMe-logo.png" id="logo">
						<img src="<%=request.getContextPath()%>/supplier_end/images/MarryMe-Word.png" id="logo-word">
				</div>
				<!-- ���������� -->
			
				<!-- �k���� -->
				<div class="col-xs-12  col-sm-offset-4 col-sm-4">
					<ul class="nav navbar-nav navbar-right">
						<li class="login option_text"><a href="#"><font size="3" color="orange">${supAccount.sup_name}</font> �A�z�n</a></li>
						<li class="login option_text"><a href="<%=request.getContextPath()%>/sup/sup.do?action=logout">�n�X</a></li>
						<li class="login option_text"><a href="<%=request.getContextPath()%>/sup/sup.do?action=getOne_For_Update&sup_no=${supAccount.sup_no}&&number=1">�ӤH�]�w</a></li>		
					</ul>
				</div>
				<!-- �k�������� -->
			</div>
			<!-- ������ÿ��ϵ��� -->
		</nav>
        <!--����\����-->
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
			        		<a data-toggle="collapse" href="#collapse1">�߮b���a�޲z</a>
			     		 </h4>
			    	</div>
			    	<div id="collapse1" class="panel-collapse collapse">
			     	 <ul class="list-group">
			        	<li class="list-group-item"><i class="glyphicon glyphicon-tasks"></i></i><a href="<%=request.getContextPath()%>/sup/sup.do?action=listPlace_BySup_no_A&sup_no=${supAccount.sup_no}">
			        	���a�d��</a></li>
			        	<li class="list-group-item"><i class="glyphicon glyphicon-plus"></i><a href="<%=request.getContextPath()%>/supplier_end/Place/addPlace.jsp">
			        	�s�W���a</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			  	<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-th-list"></i>
			        		<a data-toggle="collapse" href="#collapse2">���޲z</a>
			     		 </h4>
			    	</div>
			    	<div id="collapse2" class="panel-collapse collapse">
			     	 <ul class="list-group">
			         	<li class="list-group-item"><i class="glyphicon glyphicon-tags"></i><a href="<%=request.getContextPath()%>/supplier_end/menu/listSupMenus.jsp">���</a></li>
			        	<li class="list-group-item"><i class="glyphicon glyphicon-plus"></i><a href="<%=request.getContextPath() %>/supplier_end/menu/addMenu.jsp">�s�W���</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			  	<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-shopping-cart"></i>
			        		<a data-toggle="collapse" href="#collapse3">�Ȥ�w���޲z</a>
			     		</h4>
			    	</div>
			    	<div id="collapse3" class="panel-collapse collapse">
			     	 <ul class="list-group">
			        	<li class="list-group-item"><i class="glyphicon glyphicon-tags"></i><a href="<%=request.getContextPath()%>/supplier_end/Appointment/listSupAppointment.jsp">���v�M��</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			</div>
           <!--����\���浲��-->
           </div>
			
			<div class="divider"></div>
	        <div class="col-xs-12 col-sm-10">
		         <div class="row"> 
				<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue">
					  <tr style="font-weight:bold">
						<th>�q��s��</th>
						<th>���a�W��</th>
						<th>�|���m�W</th>
						<th>�|���a�}</th>
						<th>�|�����</th>
						<th>�w���q�榨�ߤ��</th>
						<th>�w�����a���</th>
						<th>�w���q�檬�A</th>
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
					          	<c:if test="${appointmentVO.app_status == 2}">�w����</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="pages/page2.file" %>
				</div>
			</div>
			<!--footer�}�l-->
					<div class="divider"></div>
				  	
					<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>

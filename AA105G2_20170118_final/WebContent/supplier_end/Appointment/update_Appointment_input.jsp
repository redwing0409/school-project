<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.appointment.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.sup.model.*"%>

<%
AppointmentVO appointmentVO = (AppointmentVO) request.getAttribute("appointmentVO"); //APPOINTMENTServlet.java (Concroller), �s�Jreq��APPOINTMENTVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<jsp:useBean id="PlaceSvc" scope="page" class="com.place.model.PlaceService" />
<jsp:useBean id="MemSvc" scope="page" class="com.member.model.MemberService" />

<html>
<head>
		<title>�w���q���ƭק� - update_Appointment_input.jsp</title>
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
						<li class="login option_text"><a href="<%=request.getContextPath()%>/sup/sup.do?action=getOne_For_Update&sup_no=${supAccount.sup_no}&number=1">�ӤH�]�w</a></li>		
					</ul>
				</div>
				<!-- �k�������� -->
			</div>
			<!-- ������ÿ��ϵ��� -->
		</nav>
		<!--����\����-->
         <!--����|�]�\����-->
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
			        	<li class="list-group-item"><i class="glyphicon glyphicon-tasks"></i><a href="<%=request.getContextPath()%>/sup/sup.do?action=listPlace_BySup_no_A&sup_no=${supAccount.sup_no}">
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
			 <!--����|�]�\���浲��-->
			</div>
			
			<div class="divider"></div>
			        <div class="col-xs-12 col-sm-10">
				         <div class="row"> 
							<%-- ���~��C --%>
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
							<h4 style="margin-left:80px;font-weight:bold">�ק�w���q����</h4>  
							<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:auto">
								<tr>
									<td>�q��s��:</td>
									<td><%=appointmentVO.getApp_no()%></td>							
								</tr>
								<tr>
									<td>���a�W��:</td>
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
									<td>�|���m�W:</td>
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
									<td>�w���q�榨�ߤ��<font color=red><b>*</b></font>:</td>
									<td>
									   <input id="datepicker1" type="text" class="form-control" name="app_date" value="<%=appointmentVO.getApp_date()%>" size="20"></input>
									</td>								
								</tr>
								<tr>
									<td>�w�����a���<font color=red><b>*</b></font>:</td>
									<td>
									    <input id="datepicker2" type="text" class="form-control" name="app_place_date" value="<%=appointmentVO.getApp_place_date()%>" size="20"></input>
									</td>
								</tr>
								<tr>
									<td>�w���q�檬�A:</td>
									<td>
										<select size="1" name="app_status">
							         		<option value="1" ${(1==appointmentVO.app_status)?'selected':''}>������
											<option value="2" ${(2==appointmentVO.app_status)?'selected':''}>�w����
							  			</select>
									</td>
								</tr>							
							</table>			
								<br>
								<input type="hidden" name="action" value="update">
								<input type="hidden" name="app_no" value="<%=appointmentVO.getApp_no()%>">
								<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
								<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:listAllPlace.jsp-->
								<input type="submit" value="�e�X�ק�" style="margin-top:-35px">
							</FORM>
					</div>
			  </div>
                    <!--footer�}�l-->
					<div class="divider"></div>
				  	
					<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>

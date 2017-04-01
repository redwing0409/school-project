<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sup.model.*"%>
<%@ page import="com.appointment.model.*"%>
<%@ page import="com.place.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	AppointmentService appSvc = new AppointmentService();
	SupVO supVO = (SupVO) session.getAttribute("supAccount");
	List<AppointmentVO> list = appSvc.getAppBySup(supVO.getSup_no());
	pageContext.setAttribute("list", list);
%>
<%-- <%  session.setAttribute("sup_no","6001") ;%> --%>

<jsp:useBean id="SupSvc" scope="page" class="com.sup.model.SupService" />

<html>
<head>
<meta charset="big5">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>�t�Ӻݭ���</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/supplier_end/css/orderQuery.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/supplier_end/css/table2.css">

<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<script src="https://code.jquery.com/jquery.js"></script>
<script src="js/jquery-ui.js"></script>
<script>
	$(function() {
		var $j = jQuery.noConflict();
		$j("#datepicker").datepicker({
			showOn : "button",
			buttonImage : "images/calendar.gif",
			buttonImageOnly : true,
			changeMonth : true,
			changeYear : true
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
				<img
					src="<%=request.getContextPath()%>/supplier_end/images/MarryMe-logo.png"
					id="logo"> <img
					src="<%=request.getContextPath()%>/supplier_end/images/MarryMe-Word.png"
					id="logo-word">
			</div>
			<!-- ���������� -->

			<!-- �k���� -->
			<div class="col-xs-12  col-sm-offset-4 col-sm-4">
				<ul class="nav navbar-nav navbar-right">
					<li class="login option_text"><a href="#"><font size="3"
							color="orange">${supAccount.sup_name}</font> �A�z�n</a></li>
					<li class="login option_text"><a href="<%=request.getContextPath()%>/sup/sup.do?action=logout">�n�X</a></li>
				   <c:if test="${supAccount.sup_type== 'H'}"	>	
						<li class="login option_text"><a href="<%=request.getContextPath()%>/sup/sup.do?action=getOne_For_Update&sup_no=${supAccount.sup_no}&number=1">�ӤH�]�w</a></li>
				   </c:if>
				   <c:if test="${supAccount.sup_type== 'C'}"	>	
						<li class="login option_text"><a href="<%=request.getContextPath()%>/sup/sup.do?action=getOne_For_Update&sup_no=${supAccount.sup_no}&number=2">�ӤH�]�w</a></li>
				   </c:if>
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
		<c:if test="${supAccount.sup_type=='H'}">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<i class=" glyphicon glyphicon-home"></i> <a
								href="<%=request.getContextPath()%>/supplier_end/sup_firstpage.jsp">HOME</a>
						</h4>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<i class="glyphicon glyphicon-cog"></i> <a data-toggle="collapse"
								href="#collapse1">�߮b���a�޲z</a>
						</h4>
					</div>
					<div id="collapse1" class="panel-collapse collapse">
						<ul class="list-group">
							<li class="list-group-item"><i
								class="glyphicon glyphicon-tasks"></i><a
								href="<%=request.getContextPath()%>/sup/sup.do?action=listPlace_BySup_no_A&sup_no=${supAccount.sup_no}">
									���a�d��</a></li>
							<li class="list-group-item"><i
								class="glyphicon glyphicon-plus"></i><a
								href="<%=request.getContextPath()%>/supplier_end/Place/addPlace.jsp">
									�s�W���a</a></li>
						</ul>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<i class="glyphicon glyphicon-th-list"></i> <a
								data-toggle="collapse" href="#collapse2">���޲z</a>
						</h4>
					</div>
					<div id="collapse2" class="panel-collapse collapse">
						<ul class="list-group">
							<li class="list-group-item"><i
								class="glyphicon glyphicon-tags"></i><a
								href="<%=request.getContextPath()%>/supplier_end/menu/listSupMenus.jsp">���</a></li>
							<li class="list-group-item"><i
								class="glyphicon glyphicon-plus"></i><a
								href="<%=request.getContextPath()%>/supplier_end/menu/addMenu.jsp">�s�W���</a></li>
						</ul>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<i class="glyphicon glyphicon-shopping-cart"></i> <a
								data-toggle="collapse" href="#collapse3">�Ȥ�w���޲z</a>
						</h4>
					</div>
					<div id="collapse3" class="panel-collapse collapse">
						<ul class="list-group">
							<li class="list-group-item"><i class="glyphicon glyphicon-tags"></i><a href="<%=request.getContextPath()%>/supplier_end/Appointment/listSupAppointment.jsp">���v�M��</a></li>
						</ul>
					</div>
				</div>
			</div>
		</c:if>
		<!--����|�]�\���浲��-->

		<!--����@��ʥ\����-->
		<c:if test="${supAccount.sup_type== 'C'}">
			<div class="panel-group">
				<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class=" glyphicon glyphicon-home"></i>
			        		<a href="<%=request.getContextPath()%>/supplier_end/sup/supplierhome.jsp">HOME</a>
			     		 </h4>
			    	</div>
			    </div>
			  	<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-th-list"></i>
			        		<a data-toggle="collapse" href="#collapse2">�ӫ~�޲z</a>
			     		 </h4>
			    	</div>
			    	<div id="collapse2" class="panel-collapse collapse">
			     	 <ul class="list-group">
			         	<li class="list-group-item"><i class="glyphicon glyphicon-tags"></i><a href="<%=request.getContextPath()%>/supplier_end/com/listAllCommodity.jsp">�d�߰ӫ~</a></li>
			        	<li class="list-group-item"><i class="glyphicon glyphicon-plus"></i><a href="<%=request.getContextPath()%>/supplier_end/com/addCom.jsp">�s�W�ӫ~</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			  	<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-shopping-cart"></i>
			        		<a href="<%=request.getContextPath()%>/supplier_end/purord/listAllPurOrd.jsp">�q��޲z</a>
			     		</h4>
			    	</div>
			  	</div>
			     <div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-list-alt"></i>
			        		<a href="<%=request.getContextPath()%>/supplier_end/ord/listAllOrderItem.jsp">�q����Ӻ޲z</a>
			     		</h4>
			    	</div>
			  	</div>
			</div>
		</c:if>
		<!--����@��ʥ\���浲��-->
	</div>

	<!--����\���浲��-->

	<div class="divider"></div>

	<div class="col-xs-12 col-sm-10">
		<div class="row">
			<!--�w���椺�e-->
			<c:if test="${supAccount.sup_type== 'H'}&&${requestScope.attribute==null}">
			</c:if>
		</div>
		
		<!--�w���椺�e����-->

		<!--�q�椺�e-->
		<div class="row">
			<c:if test="${supAccount.sup_type=='C'}">
				<div class="row"> 	
			        <form METHOD="post" ACTION="<%=request.getContextPath()%>/com/com.do">
			            <div class="col-xs-12 col-sm-5">
			            	<div class="form-group">
						        <label for="input1" class="col-sm-3 control-label">�ӫ~�W��</label>
						        <div class="col-sm-4 pull-left">
						            <input type="text" class="form-control col-sm-4 " name="com_name" placeholder="�п�J">		
						        </div>
					        </div>
			            </div>
			            <div class="col-xs-12 col-sm-5">
			            	<div class="input-append date">
						        <label class="col-sm-3 control-label">�W�[���</label>
						        <div class="col-sm-4 pull-left">	           
						            <input  id="datepicker" type="text" onFocus="this.blur()" class="form-control" name="com_shelf_date" placeholder="���I��"></input>
						        </div>
					        </div>
			            </div>
			            <div class="col-xs-12 col-sm-2">
						    <button type="submit" class="btn btn-info">�}�l�d��</button>
					        <input type="hidden" name="action" value="listCom_ByCompositeQuery">
			            </div>  
			        </form>
		   		 </div> <!-- row end -->
			</c:if>
		<!--�q�椺�e����-->
		</div>
	</div>
	    <%if (request.getAttribute("SupVO1")==null && supVO.getSup_type().equals("H")){%>
	         <%if (request.getAttribute("updateSupVO1")==null){%>
	  			 <jsp:include page="/supplier_end/Appointment/listSupUnCloseAppointment.jsp" flush="true"/>
	  		 <%}%> 
	  		 <%if (request.getAttribute("updateSupVO1")!=null){%>
	  			<jsp:include page="/supplier_end/sup/listplaceSup_afterUpdate.jsp" flush="true"/>
	  		 <%}%> 
	    <%}%> 
	    
		<%if (request.getAttribute("SupVO1")!=null){%>
     		 <jsp:include page="/supplier_end/sup/update_placeSup_input.jsp" flush="true"/>
		<%}%> 
	<!--footer�}�l-->

	<div class="divider"></div>
	
	<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>

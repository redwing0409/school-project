<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>


<%-- �����m�߱ĥ� EL ���g�k���� --%>

<jsp:useBean id="listMenus_ByPlace_no" scope="request" type="java.util.Set" />
<jsp:useBean id="PlaceSvc" scope="page" class="com.place.model.PlaceService" />
<html>
<head>
<title>���a��� - listMenus_ByPlace_no.jsp</title>
	    <meta charset="big5">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/css/orderQuery.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/Place/css/table2.css">
	    
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
					changeMonth: true,
					changeYear: true
				});
			});
	   </script>
</head>

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
						<li class="login option_text"><a href="#">�ӤH�]�w</a></li>		
					</ul>
				</div>
				<!-- �k�������� -->
			</div>
			<!-- ������ÿ��ϵ��� -->
		</nav>
		
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
			         	<li class="list-group-item"><i class="glyphicon glyphicon-tags"></i><a href="<%=request.getContextPath()%>/Place/PlaceServlet.do?action=listMenus_ByPlace_no_A&sup_no=${supAccount.sup_no}">���</a></li>
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
		
					<table border='1' cellpadding='5' cellspacing='0' width='100%'>
						<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
							<td>
							<h3>���a��� - listMenus_ByPlace_no.jsp</h3>
							<a href="<%= request.getContextPath() %>/supplier_end/sup/sup_firstpage.jsp"><img src="<%= request.getContextPath() %>/supplier_end/images/back1.gif" width="100" height="32" border="0">�^����</a>
							</td>
						</tr>
					</table>
					
					<%-- ���~��C --%>
					<c:if test="${not empty errorMsgs}">
						<font color='red'>�Эץ��H�U���~:
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li>${message}</li>
							</c:forEach>
						</ul>
						</font>
					</c:if>
					
					<table border='1' bordercolor='#CCCCFF' width='100%'>
						<tr>
							<th>���s��</th>
							<th>���a�s��</th>
							<th>���W��</th>
							<th>��⤶��</th>
							<th>���Ϥ�</th>
							<th>����</th>
							
						</tr>					
						<c:forEach var="MenuVO" items="${listMenus_ByPlace_no}" >
							<tr align='center' valign='middle' ${(MenuVO.menu_no==param.menu_no) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
								<td>${MenuVO.menu_no}</td>
								<td><c:forEach var="PlaceVO" items="${PlaceSvc.all}">
					                    <c:if test="${MenuVO.place_no==PlaceVO.place_no}">
						                    ${PlaceVO.place_no}�i<font color=orange>${PlaceVO.place_name}</font>�j
					                    </c:if>
					                </c:forEach>
					                </td>
								<td>${MenuVO.menu_name}</td>
								<td>${MenuVO.menu_note}</td>
								<td width="300"><img src="<%=request.getContextPath()%>/ShowMenu_Pic.do?menu_no=${MenuVO.menu_no}" width="20%"></td>
								<td>${MenuVO.menu_price}</td>	
								
								<td>
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/menu.do">
								    <input type="submit" value="�ק�"> 
								    <input type="hidden" name="menu_no"value="${MenuVO.menu_no}">
								    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
								    <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
								</td>
								<td>
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/menu.do">
								    <input type="submit" value="�R��">
								    <input type="hidden" name="menu_no" value="${MenuVO.menu_no}">
								    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
								    <input type="hidden" name="action"value="delete"></FORM>
								</td>
							</tr>
						</c:forEach>
					</table>
					</div>
				</div>

       <div class="divider"></div>
	  	
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>

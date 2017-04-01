<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.place.model.*"%>

<%-- �����m�߱ĥ� EL ���g�k���� --%>

<jsp:useBean id="listPlace_BySup_no" scope="request" type="java.util.Set" />
<jsp:useBean id="SupSvc" scope="page" class="com.sup.model.SupService" />
<html>
<head>
		<meta charset="big5">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>�d�߳��a</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/css/orderQuery.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/sup/css/table2.css">
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
						<li class="login option_text"><a href="<%=request.getContextPath()%>/sup/sup.do?action=getOne_For_Update&sup_no=${supAccount.sup_no}&number=1">�ӤH�]�w</a></li>		
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

        <!--���a���e-->
        <div class="divider"></div>
        <div class="col-xs-12 col-sm-10">
           <div class="row">
				<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue">
					<tr style="font-weight:bold">
						<th>���a�s��</th>
						<th>�t�ӽs��</th>
						<th>���a���O</th>
						<th>���a�W��</th>
						<th>���a�a��</th>
						<th>���a�a�}</th>
						<th>���a�Ϥ�</th>
						<th>���a����</th>
						<th>���a���A</th>
						<th>�s��</th>
					</tr>
					<%@ include file="pages/listPlace_BySup_no_page1.file" %>
					<c:forEach var="placeVO" items="${listPlace_BySup_no}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr align='center' valign='middle' ${(placeVO.place_no==param.place_no) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
							<td>${placeVO.place_no}</td>
							<td><c:forEach var="SupVO" items="${SupSvc.all}">
				                    <c:if test="${placeVO.sup_no==SupVO.sup_no}">
					                    ${SupVO.sup_no}�i<font color=orange>${SupVO.sup_name}</font>�j
				                    </c:if>
				                </c:forEach>
				                </td>
							<td>
								<c:if test="${placeVO.place_type == 1}">�s���B����</c:if>
					          	<c:if test="${placeVO.place_type == 2}">�x��B����</c:if>
					          	<c:if test="${placeVO.place_type == 3}">�B�b�\�U</c:if>
							</td>
							<td>${placeVO.place_name}</td>
							<td>
								<c:if test="${placeVO.place_area == 'N'}">�_��</c:if>
					          	<c:if test="${placeVO.place_area == 'C'}">����</c:if>
					          	<c:if test="${placeVO.place_area == 'S'}">�n��</c:if>
					          	<c:if test="${placeVO.place_area == 'E'}">�F��</c:if>
							</td>
							<td>${placeVO.place_adds}</td>
							<td>
								<img src="<%=request.getContextPath()%>/Place/ShowPlace_Pic.do?place_no=${placeVO.place_no}" width="100" height="80"/>
							</td>
							<td>${placeVO.place_note}</td>
							<td>
							   <c:if test="${placeVO.place_status=='1'}">���</c:if>
							   <c:if test="${placeVO.place_status=='0'}">�����</c:if>
							</td>
							<td>
							     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Place/PlaceServlet.do">
							     <input type="submit" value="�ק�">
							     <input type="hidden" name="place_no" value="${placeVO.place_no}">
							     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
							     <input type="hidden" name="whichPage"	value="<%=whichPage%>"> 
							     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="pages/listPlace_BySup_no_page2.file" %>
            </div>
         </div>

   
	  	<div class="divider"></div>
	  	
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>

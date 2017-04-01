<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sup.model.*"%>
<%
	SupVO SupVO = (SupVO) request.getAttribute("SupVO"); //SupServlet.java (Concroller), �s�Jreq��SupVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<html>
<head>
<title>�t�ӻ{�� - update_sup_input.jsp</title>
<meta charset="big5">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/css/orderQuery.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/sup/css/table2.css">
		
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
	   
	   <style type="text/css">
			body{
		   margin:0;
	       padding:0;
		   height:100%;
		   width: 100%;
		   background:linear-gradient(top,#ffffff,rgba(80%,80%,80%,0)),url(<%=request.getContextPath()%>/back_end/images/p8.jpg) no-repeat;
		   background:-moz-linear-gradient(top,#ffffff,rgba(80%,80%,80%,0)),url(<%=request.getContextPath()%>/back_end/images/p8.jpg) no-repeat;
		   background:-webkit-linear-gradient(top,#ffffff,rgba(80%,80%,80%,0)),url(<%=request.getContextPath()%>/back_end/images/p8.jpg) no-repeat;
		   background-size: cover;
		}		
			
			</style>
	   	
	</head>
	<body>
		<nav class="navbar navbar-default" role="navigation">
			
			<!-- ������ÿ��� -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- ������ -->
				<div class="col-xs-12 col-sm-4 pull-left">
						<img src="<%=request.getContextPath()%>/back_end/images/MarryMe-logo.png" id="logo">
						<img src="<%=request.getContextPath()%>/back_end/images/MarryMe-Word.png" id="logo-word">
				</div>
				<!-- ���������� -->
			
				<!-- �k���� -->
				<div class="col-xs-12  col-sm-offset-4 col-sm-4">
					<ul class="nav navbar-nav navbar-right">
						<li class="login option_text"><a href="#"><font size="3" color="orange">${empAccount.emp_name}</font> �A�z�n</a></li>
						<li class="login option_text"><a href="<%=request.getContextPath()%>/employee/employee.do?action=logout">�n�X</a></li>
						<li class="login option_text"><a href="<%=request.getContextPath()%>/employee/employee.do?action=getOne_For_Update&emp_no=${empAccount.emp_no}">�ӤH�]�w</a></li>		
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
			        		<a href="<%=request.getContextPath()%>/back_end/employee/employee_firstpage.jsp">HOME</a>
			     		 </h4>
			    	</div>
			    </div>
			    <div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-cog"></i>
			        		<a data-toggle="collapse" href="#collapse1">�t�Ӻ޲z</a>
			     		 </h4>
			    	</div>
			    	<div id="collapse1" class="panel-collapse collapse">
			     	 <ul class="list-group">
			        	<li class="list-group-item"><i class="glyphicon glyphicon-tasks"></i><a href="<%=request.getContextPath()%>/back_end/sup/listAllSup.jsp">
			        	�t�Ӭd��</a></li>
			        	<li class="list-group-item"><i class="glyphicon glyphicon-plus"></i><a href="<%=request.getContextPath()%>/back_end/sup/listUnCheckSup.jsp">
			        	�t�ӻ{��</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			  	<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-th-list"></i>
			        		<a data-toggle="collapse" href="#collapse2">���u�޲z</a>
			     		 </h4>
			    	</div>
			    	<div id="collapse2" class="panel-collapse collapse">
			     	 <ul class="list-group">
			         	<li class="list-group-item"><i class="glyphicon glyphicon-tags"></i><a href="<%=request.getContextPath()%>/back_end/employee/listAllEmployee.jsp">�Ҧ����u</a></li>
			        	<li class="list-group-item"><i class="glyphicon glyphicon-plus"></i><a href="<%=request.getContextPath() %>/back_end/employee/addEmployee.jsp">�s�W���u</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			  	
			  	
			</div>
			 <!--����|�]�\���浲��-->
			 </div>
				    
	<div class="divider"></div>

	<div class="col-xs-12 col-sm-10">
		<div class="row">
			<div class="modal-body">
			
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

				<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="margin-left:250px;width:auto;height:500px">
				    <tr>
				        <td colspan="2"><h3>�t�ӻ{��:</h3></td>
				    </tr>
					<tr>
						<td>�t�ӽs��:</td>
						<td><%=SupVO.getSup_no()%></td>
						<input type="hidden" name="sup_no" value="<%=SupVO.getSup_no()%>"/>
					</tr>
					<tr>
						<td>�t�ӦW��:</td>
						<td><%=SupVO.getSup_name()%></td>
						<input type="hidden" name="sup_name" value="<%=SupVO.getSup_name()%>"/>
					</tr>
					<tr>
						<td>�t�ӱb��:</td>
						<td><%=SupVO.getSup_acct()%></td>
						<input type="hidden" name="sup_acct" value="<%=SupVO.getSup_acct()%>"/>
					</tr>

					<tr>
						<td>�t�ӲΤ@�s��:</td>
						<td><%=SupVO.getSup_id()%></td>
						<input type="hidden" name="sup_id" value="<%=SupVO.getSup_id()%>"/>
					</tr>
					<tr>
						<td>�t�ӵ��f:</td>
						<td><%=SupVO.getSup_con()%></td>
						<input type="hidden" name="sup_con" value="<%=SupVO.getSup_con()%>"/>
					</tr>
					<tr>
						<td>�t�ӹq�ܰϽX:</td>
						<td>0<%=SupVO.getSup_telcode()%></td>
						<input type="hidden" name="sup_telcode" value="<%=SupVO.getSup_telcode()%>"/>
					</tr>
					<tr>
						<td>�t�ӹq��:</td>
						<td><%=SupVO.getSup_tel()%></td>
						<input type="hidden" name="sup_tel" value="<%=SupVO.getSup_tel()%>"/>
					</tr>
					<tr>
						<td>�t�Ӥ�����X:</td>
						<td>0<%=SupVO.getSup_tax()%></td>
						<input type="hidden" name="sup_tax" value="<%=SupVO.getSup_tax()%>"/>
					</tr>
					<tr>
						<td>�t�Ӷl���ϸ�:</td>
						<td><%=SupVO.getSup_adcode()%></td>
						<input type="hidden" name="sup_adcode" value="<%=SupVO.getSup_adcode()%>"/>
					</tr>
					<tr>
						<td>�t�Ӧa�}:</td>
						<td><%=SupVO.getSup_addr()%></td>
						<input type="hidden" name="sup_addr" value="<%=SupVO.getSup_addr()%>"/>
					</tr>
					
					<tr>
						<td>�t������:</td>
<%-- 						<td><%=SupVO.getSup_type()%></td> --%>
						<td>${SupVO.sup_type=="H"?"�|�]":"�P��ӫ~"}</td>
						<input type="hidden" name="sup_type" value="<%=SupVO.getSup_type()%>"/>
					</tr>

				<tr>
				<td colspan="2">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/sup/sup.do">
				<input type="hidden" name="action" value="update_sup_note">
				<input type="hidden" name="sup_no" value="<%=SupVO.getSup_no()%>">
				<input type="hidden" name="sup_pwd" value="<%=SupVO.getSup_pwd()%>"/>
				<input type="hidden" name="sup_note" value="1">
				<input type="submit" class="btn btn-success" value="�q�L�f��">
			    </FORM>
			    </td>
			    </tr>
			    </table>
          </div>
		</div>
	</div>

	
	<script
		src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>

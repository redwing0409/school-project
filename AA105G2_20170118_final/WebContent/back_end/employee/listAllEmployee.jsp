<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
		EmployeeService employeeSvc = new EmployeeService();
		List<EmployeeVO> list = employeeSvc.getAll();
		pageContext.setAttribute("list",list);
//     EmpService empSvc = new EmpService();
//     List<EmpVO> list = empSvc.getAll();
//     pageContext.setAttribute("list",list);
%>

<html>
<head>
		<title>�Ҧ����u��� - listAllEmployee.jsp</title>
		<meta charset="big5">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>���u���x</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/css/orderQuery.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/employee/css/table1.css">
		
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
		#aa{
		margin-top: 50px;
		}	

      </style> 
      
</head>

<body bgcolor='white'>
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
								<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue">
					                <tr style="font-weight:bold">
										<th>���u�s��</th>
										<th>���u�b��</th>
<!-- 										<th>���u�K�X</th> -->
										<th>���u�m�W</th>
										<th>���u�ʧO</th>
										<th>���u�q��</th>
								<!-- 		<th>����</th> -->
<!-- 										<th>�ק�</th> -->
<!-- 										<th>�R��</th> -->
									</tr>
									<%@ include file="page1.file" %> 
									<c:forEach var="employeeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
										<tr align='center' valign='middle'>
											<td>${employeeVO.emp_no}</td>
											<td>${employeeVO.emp_account}</td>
<%-- 											<td>${employeeVO.emp_psw}</td> --%>
											<td>${employeeVO.emp_name}</td>
											<td>
											        ${employeeVO.emp_sex==0?"�k":"�k"}
											</td>
											<td>${employeeVO.emp_mobile}</td>
								<%-- 			<td>${employeeVo.deptno}</td> --%>
<!-- 											<td> -->
<%-- 											  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do"> --%>
<!-- 											     <input type="submit" value="�ק�"> -->
<%-- 											     <input type="hidden" name="emp_no" value="${employeeVO.emp_no}"> --%>
<!-- 											     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 											</td> -->
								<!-- 			<td> -->
								<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do"> --%>
								<!-- 			    <input type="submit" value="�R��"> -->
								<%-- 			    <input type="hidden" name="emp_no" value="${employeeVO.emp_no}"> --%>
								<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
								<!-- 			</td> -->
										</tr>
									</c:forEach>
								</table>
								<%@ include file="page2.file" %>
								</div>
                             </div>
				      </div>
				    
					<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>

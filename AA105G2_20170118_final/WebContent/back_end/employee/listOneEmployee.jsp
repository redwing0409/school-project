<%@ page contentType="text/html; charset=Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>
<% 
  EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%> 
<html>
<head>
<title>���u��� - listOneEmployee.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/com/css/listAllCommodity.css">

</head>
<body bgcolor='white' onload="connect();" onunload="disconnect();">

  <h5 style="color:red">�ק令�\!</h5>
<table table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:80%">
	<tr align='center' valign='middle'>
		<th>���u�s��</th>
		<th>���u�b��</th>
		<th>���u�K�X</th>
		<th>���u�m�W</th>
		<th>���u�q��</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=employeeVO.getEmp_no()%></td>
		<td><%=employeeVO.getEmp_account()%></td>
		<td><%=employeeVO.getEmp_psw()%></td>
		<td><%=employeeVO.getEmp_name()%></td>
		<td><%=employeeVO.getEmp_mobile()%></td>
<%--             <td>${employeeVo.emp_no}</td> --%>
<%--             <td>${employeeVo.emp_account}</td> --%>
<%--             <td>${employeeVo.emp_psw}</td> --%>
<%--             <td>${employeeVo.emp_name}</td> --%>
<%--             <td>${employeeVo.emp_sex}</td> --%>
<%--             <td>${employeeVo.emp_mobile}</td> --%>
		</tr>
</table>

</body>
</html>

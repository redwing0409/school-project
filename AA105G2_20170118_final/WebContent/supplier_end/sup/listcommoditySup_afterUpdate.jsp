<%@ page contentType="text/html; charset=Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>supplier_end �t�ӭק����- listSup_afterUpdate.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/com/css/listAllCommodity.css">

</head>
<body bgcolor='white'>

<h5 style="color:red">�ק令�\!</h5>

<table  table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:80%">
	<tr>
		<th>�b��</th>
		<th>�Τ@�s��</th>
		<th>�t�ӵ��f</th>
		<th>�ϽX</th>
		<th>�q��</th>
		<th>�ǯu���X</th>
		<th>�l���ϸ�</th>
		<th>�a�}</th>
	</tr>
	<tr align='center' valign='middle'>
		<td>${updateSupVO2.sup_acct}</td>
		<td>${updateSupVO2.sup_id}</td>
		<td>${updateSupVO2.sup_con}</td>
		<td>${updateSupVO2.sup_telcode}</td>
		<td>${updateSupVO2.sup_tel}</td>	
		<td>${updateSupVO2.sup_tax}</td>
		<td>${updateSupVO2.sup_adcode}</td>
		<td>${updateSupVO2.sup_addr}</td>
	</tr>
</table>
</body>
</html>

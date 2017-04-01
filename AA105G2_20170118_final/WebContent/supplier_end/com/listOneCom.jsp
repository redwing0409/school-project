<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.commodity.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>supplier_end ���ӫ~��� - listOneCom.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/com/css/listAllCommodity.css">
</head>
<body bgcolor='white'>
<%
  //�]�w��ܪ��ɶ��榡
  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
  pageContext.setAttribute("sf", sf);
%>
<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:80%">
	<tr>
		<th>�ӫ~�s��</th>
		<th>��������</th>
		<th>�ӫ~�W��</th>
		<th>�ӫ~�y�z</th>
		<th>�ӫ~����</th>
		<th>�ӫ~���A</th>
		<th>�W�[���</th>
		<th>�U�[���</th>
		<th>�ӫ~�Ƶ�</th>
		<th>�ӫ~�Ϥ�</th>
	</tr>
	<tr align='center' valign='middle'>
	    <td>${comVO2.com_no}</td>
		<td>
	 	  <c:if test="${comVO2.pcm_no == 1}">�ߩ�</c:if>
          <c:if test="${comVO2.pcm_no == 2}">�B���ۥ�</c:if>
          <c:if test="${comVO2.pcm_no == 3}">�B§�p��</c:if>
          <c:if test="${comVO2.pcm_no == 4}">�B§�Ϋ~</c:if>
	    </td>
		<td>${comVO2.com_name}</td>
		<td>${comVO2.com_desc}</td>
		<td>${comVO2.com_price}</td>
		<td>
			  <c:if test="${comVO2.com_status == 1}">�W�[</c:if>
	          <c:if test="${comVO2.com_status == 2}">�U�[</c:if>
		</td>
		<td>${sf.format(comVO2.com_shelf_date)}</td>
		<td>${sf.format(comVO2.com_off_date)}</td>
		<td>${comVO2.com_note}</td>
		<td><img src="<%=request.getContextPath()%>/DBGif.do?name=${comVO2.com_no}" width='100'></td>	
	</tr>
</table>
</body>
</html>

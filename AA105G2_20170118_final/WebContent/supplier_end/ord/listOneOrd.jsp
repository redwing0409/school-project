<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.order_item.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.commodity.model.*"%>

<html>
<head>
<title>supplier_end �浧�q����Ӹ�� - listOneOrd.jsp</title>
</head>
<body bgcolor='white'>
<jsp:useBean id="comSvc" scope="page" class="com.commodity.model.ComService"/>
<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" width='600' style="width:50%">
	<tr>
		<th>�q��s�� </th>
		<th>�ӫ~�W��</th>
		<th>�ӫ~���</th>
		<th>�q�ʼƶq(��)</th>
		<th>�h�f�ƶq(��)</th>
		<th>�X�f���A</th>
	</tr>
	<tr align='center' valign='middle'>
        <td>${ordVO2.pur_no}</td>
		<td>${comSvc.getOneCom(ordVO2.com_no).com_name}</td>
		<td>${ordVO2.ord_price}</td>
		<td>${ordVO2.ord_qty}</td>
		<td>${ordVO2.return_qty}</td>
		<td>
		   <c:if test="${ordVO2.ship_status==0}">�w�X�f</c:if>
		   <c:if test="${ordVO2.ship_status==1}">���X�f</c:if>
		</td>
	</tr>

</table>

</body>
</html>

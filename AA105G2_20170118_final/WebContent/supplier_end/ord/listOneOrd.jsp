<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.order_item.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.commodity.model.*"%>

<html>
<head>
<title>supplier_end 單筆訂單明細資料 - listOneOrd.jsp</title>
</head>
<body bgcolor='white'>
<jsp:useBean id="comSvc" scope="page" class="com.commodity.model.ComService"/>
<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" width='600' style="width:50%">
	<tr>
		<th>訂單編號 </th>
		<th>商品名稱</th>
		<th>商品單價</th>
		<th>訂購數量(組)</th>
		<th>退貨數量(組)</th>
		<th>出貨狀態</th>
	</tr>
	<tr align='center' valign='middle'>
        <td>${ordVO2.pur_no}</td>
		<td>${comSvc.getOneCom(ordVO2.com_no).com_name}</td>
		<td>${ordVO2.ord_price}</td>
		<td>${ordVO2.ord_qty}</td>
		<td>${ordVO2.return_qty}</td>
		<td>
		   <c:if test="${ordVO2.ship_status==0}">已出貨</c:if>
		   <c:if test="${ordVO2.ship_status==1}">未出貨</c:if>
		</td>
	</tr>

</table>

</body>
</html>

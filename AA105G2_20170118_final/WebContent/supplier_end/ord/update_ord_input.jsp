<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order_item.model.*"%>
<%@page import="com.commodity.model.*"%>

<%
    OrdVO ordVO = (OrdVO) request.getAttribute("ordVO"); //OrdServlet.java (Concroller), 存入req的comVO物件 (包括幫忙取出的OrdVO, 也包括輸入資料錯誤時的ordVO物件)
%>
<jsp:useBean id="comSvc" scope="page" class="com.commodity.model.ComService"/>
<html>
<head>
<title>supplier_end 訂單明細資料修改 - update_ord_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="list-style-type:none;">${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ord/ord.do" name="form1">
<h4 style="margin-left:170px;font-weight:bold">修改訂單明細資料</h4>  
<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:40%">
    <tr>
		<td>訂單編號:</td>
		<td><%=ordVO.getPur_no()%></td>	
	</tr>
	<tr>
		<td>商品名稱:</td>
       <td>${comSvc.getOneCom(ordVO.com_no).com_name}</td>
	</tr>
	<tr>
		<td>商品單價<font color=red><b>*</b></font>:</td>
		<td><input type="TEXT" name="ord_price" class="form-control" size="45" value="<%=ordVO.getOrd_price()%>" style="width:30%"/></td>
	</tr>
	<tr>
		<td>訂購數量<font color=red><b>*</b></font>:</td>
		<td><input type="TEXT" name="ord_qty" class="form-control" size="45" value="<%=ordVO.getOrd_qty()%>" style="width:30%"/></td>
	</tr>
    <tr>
        <td>退貨數量<font color=red><b>*</b></font>:</td>
		<td><input type="TEXT" name="return_qty" class="form-control" size="45" value="<%=ordVO.getReturn_qty()%>" style="width:30%"/></td>
	</tr>
	<tr>
	    <td>出貨狀態:</td>
	    <td>
		<select name="ship_status" class="selectpicker form-control" style="width:30%">
		     <option value="0">已出貨</option>
		     <option value="1">未出貨</option>
		</select>
		</td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="pur_no"  value="<%=ordVO.getPur_no()%>">
<input type="hidden" name="com_no"  value="<%=ordVO.getCom_no()%>">
<input type="submit" value="送出修改" style="margin-top:-35px"></FORM>

</body>
</html>

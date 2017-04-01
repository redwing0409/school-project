<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order_item.model.*"%>
<%@page import="com.commodity.model.*"%>

<%
    OrdVO ordVO = (OrdVO) request.getAttribute("ordVO"); //OrdServlet.java (Concroller), �s�Jreq��comVO���� (�]�A�������X��OrdVO, �]�]�A��J��ƿ��~�ɪ�ordVO����)
%>
<jsp:useBean id="comSvc" scope="page" class="com.commodity.model.ComService"/>
<html>
<head>
<title>supplier_end �q����Ӹ�ƭק� - update_ord_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<%-- ���~��C --%>
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
<h4 style="margin-left:170px;font-weight:bold">�ק�q����Ӹ��</h4>  
<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:40%">
    <tr>
		<td>�q��s��:</td>
		<td><%=ordVO.getPur_no()%></td>	
	</tr>
	<tr>
		<td>�ӫ~�W��:</td>
       <td>${comSvc.getOneCom(ordVO.com_no).com_name}</td>
	</tr>
	<tr>
		<td>�ӫ~���<font color=red><b>*</b></font>:</td>
		<td><input type="TEXT" name="ord_price" class="form-control" size="45" value="<%=ordVO.getOrd_price()%>" style="width:30%"/></td>
	</tr>
	<tr>
		<td>�q�ʼƶq<font color=red><b>*</b></font>:</td>
		<td><input type="TEXT" name="ord_qty" class="form-control" size="45" value="<%=ordVO.getOrd_qty()%>" style="width:30%"/></td>
	</tr>
    <tr>
        <td>�h�f�ƶq<font color=red><b>*</b></font>:</td>
		<td><input type="TEXT" name="return_qty" class="form-control" size="45" value="<%=ordVO.getReturn_qty()%>" style="width:30%"/></td>
	</tr>
	<tr>
	    <td>�X�f���A:</td>
	    <td>
		<select name="ship_status" class="selectpicker form-control" style="width:30%">
		     <option value="0">�w�X�f</option>
		     <option value="1">���X�f</option>
		</select>
		</td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="pur_no"  value="<%=ordVO.getPur_no()%>">
<input type="hidden" name="com_no"  value="<%=ordVO.getCom_no()%>">
<input type="submit" value="�e�X�ק�" style="margin-top:-35px"></FORM>

</body>
</html>

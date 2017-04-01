<%@page import="java.util.List"%>
<%@page import="com.commodity.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="listOrderItem_ByCompositeQuery" scope="request" type="java.util.List" />
<jsp:useBean id="comSvc" scope="page" class="com.commodity.model.ComService"/>


<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/ord/css/listAllOrderItem.css">
<title>listOrdItem_ByCompositeQuery.jsp</title>
</head>
<body bgcolor='white'>
<%
    //設定顯示的時間格式
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    pageContext.setAttribute("sf", sf);
%>
<table table class="table table-hover table-striped table-bordered table-condensed"  style="text-align:center;">
	<tr style="font-weight:bold" align="center">
		<th>訂單編號 </th>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>訂單總價</th>
		<th>訂購數量(組)</th>
		<th>退貨數量</th>
		<th>出貨狀態</th>
		<th>修改</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="ordVO" items="${listOrderItem_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(ordVO.pur_no==param.pur_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${ordVO.pur_no}</td>
			<td>${ordVO.com_no}</td>
			<td>${comSvc.getOneCom(ordVO.com_no).com_name}</td>
			<td>${ordVO.ord_price}</td>
			<td>${ordVO.ord_qty}</td>
			<td>${ordVO.return_qty}</td>
			<td>
			   <c:if test="${ordVO.ship_status==0}">已出貨</c:if>
			   <c:if test="${ordVO.ship_status==1}">未出貨</c:if>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ord/ord.do">
				   <c:if test="${ordVO.ship_status==0}">
				    	 <input type="submit" value="修改"  style="visibility:hidden;">
				    </c:if>
				      <c:if test="${ordVO.ship_status==1}">
				    	 <input type="submit" value="修改" >
				    </c:if>
			     <input type="hidden" name="pur_no" value="${ordVO.pur_no}">
			     <input type="hidden" name="com_no" value="${ordVO.com_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			 </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>
</body>
</body>
</html>

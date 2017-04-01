<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="listCom_ByCompositeQuery" scope="request" type="java.util.List" />
<jsp:useBean id="orditemSvc" scope="page" class="com.commodity.model.ComService" />
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/com/css/listAllCommodity.css">
<title>listCom_ByCompositeQuery.jsp</title>
</head>
<body bgcolor='white'>
<%
    //設定顯示的時間格式
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    pageContext.setAttribute("sf", sf);
%>
<table table class="table table-hover table-striped table-bordered table-condensed" color="lightblue">
	<tr style="font-weight:bold;text-align:center">
		<th>商品編號</th>
		<th>分類項目</th>
		<th>商品名稱</th>
		<th>商品描述</th>
		<th>商品價格</th>
		<th>商品狀態</th>
		<th>上架日期</th>
		<th>下架日期</th>
		<th>商品備註</th>
		<th>商品圖片</th>
		<th>修改</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="comVO" items="${listCom_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(comVO.com_no==param.com_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${comVO.com_no}</td>
			<td>
			  <c:if test="${comVO.pcm_no == 1}">喜帖</c:if>
	          <c:if test="${comVO.pcm_no == 2}">婚紗相本</c:if>
	          <c:if test="${comVO.pcm_no == 3}">婚禮小物</c:if>
	          <c:if test="${comVO.pcm_no == 4}">婚禮用品</c:if>
	       </td>
			<td>${comVO.com_name}</td>
			<td>${comVO.com_desc}</td>
			<td>${comVO.com_price}</td>
			<td>
			  <c:if test="${comVO.com_status == 1}">上架</c:if>
	          <c:if test="${comVO.com_status == 2}">下架</c:if>
			</td>
			<td>${sf.format(comVO.com_shelf_date)}</td>
			<td>${sf.format(comVO.com_off_date)}</td>
			<td>${comVO.com_note}</td>
			<td><img src="<%=request.getContextPath()%>/DBGif.do?name=${comVO.com_no}" width='100'></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/com/com.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="com_no" value="${comVO.com_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>	
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>
</body>
</body>
</html>

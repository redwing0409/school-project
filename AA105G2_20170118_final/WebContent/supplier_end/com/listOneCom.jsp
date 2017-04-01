<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.commodity.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>supplier_end 單件商品資料 - listOneCom.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/com/css/listAllCommodity.css">
</head>
<body bgcolor='white'>
<%
  //設定顯示的時間格式
  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
  pageContext.setAttribute("sf", sf);
%>
<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:80%">
	<tr>
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
	</tr>
	<tr align='center' valign='middle'>
	    <td>${comVO2.com_no}</td>
		<td>
	 	  <c:if test="${comVO2.pcm_no == 1}">喜帖</c:if>
          <c:if test="${comVO2.pcm_no == 2}">婚紗相本</c:if>
          <c:if test="${comVO2.pcm_no == 3}">婚禮小物</c:if>
          <c:if test="${comVO2.pcm_no == 4}">婚禮用品</c:if>
	    </td>
		<td>${comVO2.com_name}</td>
		<td>${comVO2.com_desc}</td>
		<td>${comVO2.com_price}</td>
		<td>
			  <c:if test="${comVO2.com_status == 1}">上架</c:if>
	          <c:if test="${comVO2.com_status == 2}">下架</c:if>
		</td>
		<td>${sf.format(comVO2.com_shelf_date)}</td>
		<td>${sf.format(comVO2.com_off_date)}</td>
		<td>${comVO2.com_note}</td>
		<td><img src="<%=request.getContextPath()%>/DBGif.do?name=${comVO2.com_no}" width='100'></td>	
	</tr>
</table>
</body>
</html>

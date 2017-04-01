<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.purchase_order.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<html>
<head>
<title>單筆選購訂單資料 - listOnePurOrd.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/purord/css/listAllPurOrd.css">
</head>
<body bgcolor='white'>

<%
    //設定顯示的時間格式
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    pageContext.setAttribute("sf", sf);
%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"/>
<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" width='600'>
	<tr align='center' valign='middle'>
		<th>編號</th>
		<th>姓名</th>
		<th>訂單日</th>
		<th>收款日</th>
		<th>出貨日</th>
		<th>結案日</th>
		<th>狀態</th>
		<th>總金額</th>
		<th>收件人</th>
		<th>地址</th>
		<th>電話</th>
		<th>備註</th>
	</tr>
	<tr align='center' valign='middle'>
		<td>${purVO2.pur_no}</td>
		<td>${memSvc.getOneMember(purVO2.member_no).member_name}</td>
		<td>${sf.format(purVO2.pur_date)}</td>
		<td>${sf.format(purVO2.pur_money)}</td>
		<td>${sf.format(purVO2.pur_product)}</td>
		<td>${sf.format(purVO2.pur_close)}</td>
		<td>
		  <c:if test="${purVO2.pur_status == 1}">未付訂金</c:if>
          <c:if test="${purVO2.pur_status == 2}">未付尾款</c:if>
          <c:if test="${purVO2.pur_status == 3}">已結案</c:if>
          <c:if test="${purVO2.pur_status == 4}">未退費</c:if>
          <c:if test="${purVO2.pur_status == 5}">已取消</c:if>
		</td>
		<td>${purVO2.pur_sum}</td>
		<td>${purVO2.pur_name}</td>
		<td>${purVO2.pur_add}</td>
		<td>${purVO2.pur_tel}</td>
		<td>${purVO2.pur_memo}</td>
	</tr>
</table>

</body>
</html>

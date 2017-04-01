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
    //�]�w��ܪ��ɶ��榡
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    pageContext.setAttribute("sf", sf);
%>
<table table class="table table-hover table-striped table-bordered table-condensed" color="lightblue">
	<tr style="font-weight:bold;text-align:center">
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
		<th>�ק�</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="comVO" items="${listCom_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(comVO.com_no==param.com_no) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
			<td>${comVO.com_no}</td>
			<td>
			  <c:if test="${comVO.pcm_no == 1}">�ߩ�</c:if>
	          <c:if test="${comVO.pcm_no == 2}">�B���ۥ�</c:if>
	          <c:if test="${comVO.pcm_no == 3}">�B§�p��</c:if>
	          <c:if test="${comVO.pcm_no == 4}">�B§�Ϋ~</c:if>
	       </td>
			<td>${comVO.com_name}</td>
			<td>${comVO.com_desc}</td>
			<td>${comVO.com_price}</td>
			<td>
			  <c:if test="${comVO.com_status == 1}">�W�[</c:if>
	          <c:if test="${comVO.com_status == 2}">�U�[</c:if>
			</td>
			<td>${sf.format(comVO.com_shelf_date)}</td>
			<td>${sf.format(comVO.com_off_date)}</td>
			<td>${comVO.com_note}</td>
			<td><img src="<%=request.getContextPath()%>/DBGif.do?name=${comVO.com_no}" width='100'></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/com/com.do">
			     <input type="submit" value="�ק�">
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

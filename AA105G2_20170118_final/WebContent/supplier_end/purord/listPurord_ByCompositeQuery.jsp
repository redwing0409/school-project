<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="listPurord_ByCompositeQuery" scope="request" type="java.util.List" />

<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/purord/css/listAllPurOrd.css">
<title>listPurord_ByCompositeQuery.jsp</title>
</head>
<body bgcolor='white'>
<%
    //�]�w��ܪ��ɶ��榡
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    pageContext.setAttribute("sf", sf);
%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"/>
<table table class="table table-hover table-striped table-bordered table-condensed" color="lightblue">
	<tr style="font-weight:bold;text-align:center">
	    <th>�s��</th>
		<th>�m�W</th>
		<th>�q���</th>
		<th>���ڤ�</th>
		<th>�X�f��</th>
		<th>���פ�</th>
		<th>���A</th>
		<th>�`���B</th>
		<th>����H</th>
		<th>�a�}</th>
		<th>�q��</th>
		<th>�Ƶ�</th>
		<th>�s��</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="purVO" items="${listPurord_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(purVO.pur_no==param.pur_no) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
			<td>${purVO.pur_no}</td>
			<td>${memSvc.getOneMember(purVO.member_no).member_name}</td>
			<td>${sf.format(purVO.pur_date)}</td>
			<td>${sf.format(purVO.pur_money)}</td>
			<td>${sf.format(purVO.pur_product)}</td>
			<td>${sf.format(purVO.pur_close)}</td>
			<td>
			  <c:if test="${purVO.pur_status == 1}">���I�q��</c:if>
	          <c:if test="${purVO.pur_status == 2}">���I����</c:if>
	          <c:if test="${purVO.pur_status == 3}">�w����</c:if>
	          <c:if test="${purVO.pur_status == 4}">���h�O</c:if>
	          <c:if test="${purVO.pur_status == 5}">�w����</c:if>
			</td>
				<td>${purVO.pur_sum}</td>
				<td>${purVO.pur_name}</td>
				<td>${purVO.pur_add}</td>
				<td>${purVO.pur_tel}</td>
				<td>${purVO.pur_memo}</td>	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/purord/purord.do">
			        <c:if test="${purVO.pur_status == 3 || purVO.pur_status == 5}">
					    	 <input type="submit" value="�ק�"   style="visibility:hidden;">
					</c:if>			
					 <c:if test="${purVO.pur_status == 1 || purVO.pur_status == 2 || purVO.pur_status == 4}">
					    	 <input type="submit" value="�ק�" >
					</c:if>
			     <input type="hidden" name="pur_no" value="${purVO.pur_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>
</body>
</body>
</html>

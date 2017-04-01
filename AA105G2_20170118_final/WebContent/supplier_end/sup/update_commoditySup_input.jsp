<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sup.model.*"%>
<%
	SupVO SupVO = (SupVO) request.getAttribute("SupVO2"); //SupServlet.java (Concroller), �s�Jreq��SupVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<html>
<head>
<title>�t�Ӹ�ƭק� - update_sup_input.jsp</title>
<meta charset="big5">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/css/orderQuery.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/css/table2.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
<script src="https://code.jquery.com/jquery.js"></script>
<script src="<%=request.getContextPath()%>/supplier_end/com/js/jquery-ui.js"></script>
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
 
<script>
$(function() {
	var $j = jQuery.noConflict();
	$j("#datepicker").datepicker({
			showOn : "button",
			buttonImage : "images/calendar.gif",
			buttonImageOnly : true,
			changeMonth: true,
			changeYear: true
		});
	});
</script>
<script>
   $(document).ready(function(){
	  $("#comdata").click(function(){
		 $("#sup_con").attr("value","Danny");
		 $("#sup_telcode").attr("value","07");
		 $("#sup_tel").attr("value","7392048");
		 $("#sup_tax").attr("value","0911416931");
		 $("#sup_adcode").attr("value","833");
		 $("#sup_addr").attr("value","���������Q�Ϭ��s��70��");
	  });
   });
</script>
</head>
<body>

	<div class="divider"></div>

	<div class="col-xs-12 col-sm-10">
		<div class="row">
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

			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/sup/sup.do" 	name="form1">
				<table  class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:auto">
					<tr>
						<td>�W��:</td>
						<td>${supAccount.sup_name}</td>
					</tr>
					<tr>
						<td>�b��:</td>
						<td>${supAccount.sup_acct}</td>
					</tr>
					<tr>
						<td>�Τ@�s��:</td>
						<td>${supAccount.sup_id}</td>
					</tr>
					<tr>
						<td>�K�X<font color="red"><b>*</b></font>:</td>
						<td><input type="password" class="form-control" name="sup_pwd" style="width:40%" /></td>
					</tr>
					<tr>
						<td>�pô���f<font color="red"><b>*</b></font>:</td>
						<td><input type="TEXT" id="sup_con" class="form-control" name="sup_con" style="width:40%"
							value="<%=(SupVO == null) ? "" : SupVO.getSup_con()%>" /></td>
					</tr>
					<tr>
						<td>�pô�q�ܰϽX<font color="red"><b>*</b></font>:</td>
						<td><input type="TEXT" id="sup_telcode" class="form-control" name="sup_telcode" style="width:40%"
							value="<%=(SupVO == null) ? "" : SupVO.getSup_telcode()%>" /></td>
					</tr>
					<tr>
						<td>�pô�q��<font color="red"><b>*</b></font>:</td>
						<td><input type="TEXT" id="sup_tel" class="form-control" name="sup_tel" style="width:40%"
							value="<%=(SupVO == null) ? "" : SupVO.getSup_tel()%>" /></td>
					</tr>
					<tr>
						<td>������X<font color="red"><b>*</b></font>:</td>
						<td><input type="TEXT" id="sup_tax" class="form-control" name="sup_tax" style="width:40%"
							value="<%=(SupVO == null) ? "" : SupVO.getSup_tax()%>" /></td>
					</tr>
					<tr>
						<td>�l���ϸ�<font color="red"><b>*</b></font>:</td>
						<td><input type="TEXT" id="sup_adcode" class="form-control" name="sup_adcode" style="width:40%"
							value="<%=(SupVO == null) ? "" : SupVO.getSup_adcode()%>" /></td>
					</tr>
					<tr>
						<td>�a�}<font color="red"><b>*</b></font>:</td>
						<td><input type="TEXT" id="sup_addr" class="form-control" name="sup_addr" size="45"
							value="<%=(SupVO == null) ? "" : SupVO.getSup_addr()%>" /></td>
					</tr>
				</table>
				<br> <input type="hidden" name="action" value="update"/>
				<input type="hidden" name="sup_no" value="${supAccount.sup_no}"/>
				<input type="hidden" name="sup_name" value="${supAccount.sup_name}" />
				<input type="hidden" name="sup_acct" value="${supAccount.sup_acct}"/>
				<input type="hidden" name="sup_id" value="${supAccount.sup_id}"/>
				<input type="hidden" name="sup_note" value="<%=SupVO.getSup_note()%>"/>
				<input type="hidden" name="sup_type" value="${supAccount.sup_type}"/>
				<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"/>
				<input type="hidden" name="number" value="2"/>
				<!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
				<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>"/>		
				<div style="margin-top:-35px">
					<input type="submit" value="�e�X�ק�">
					<input type="button" id="comdata" value="magic">
				</div>
			</FORM>
			
		</div>
		
	</div>
	
	<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		
</body>
</html>

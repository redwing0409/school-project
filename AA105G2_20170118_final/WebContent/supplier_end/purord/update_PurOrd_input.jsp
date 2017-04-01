<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.purchase_order.model.*"%>
<%@ page import="com.member.model.*"%>

<%
    PurVO purVO = (PurVO) request.getAttribute("purVO"); //PurServlet.java (Concroller), �s�Jreq��purVO���� (�]�A�������X��purVO, �]�]�A��J��ƿ��~�ɪ�purVO����)
%>
<html>
<head>
<title>back-end ���ʭq���ƭק� - update_PurOrd_input.jsp</title></head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/purord/css/listAllPurOrd.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="<%=request.getContextPath()%>/supplier_end/purord/js/jquery-ui.js"></script>
<script>
	 $(function() {
		var $j = jQuery.noConflict();
		$j("#datepicker1,#datepicker2,#datepicker3,#datepicker4").datepicker({
			    dateFormat:"yy-mm-dd",
			    showOtherMonths: true,
			    selectOtherMonths: true,
				changeMonth: true,
				changeYear: true
			});
		});
</script>
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

<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"/>

<div class="col-xs-12 col-sm-8 col-sm-offset-2">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/purord/purord.do" name="form1">
<h4 style="margin-left:170px;font-weight:bold">�ק�q����</h4>  
	<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:auto">
	    <tr>
			<td>�q��s��:</td>
			<td><%=purVO.getPur_no()%></td>	
		</tr>
		<tr>
			<td>�|���m�W:</td>
			<td>${memSvc.getOneMember(purVO.member_no).member_name }</td>
		</tr>
		<tr>
			<td>�q����<font color=red><b>*</b></font>:</td>
			<td>
			   <input id="datepicker1" type="text" class="form-control" name="pur_date" placeholder="���I��" style="width:40%"></input>
			</td>
		</tr>
		<tr>
			<td>���ڤ��<font color=red><b>*</b></font>:</td>
			<td>
			   <input id="datepicker2" type="text" class="form-control" name="pur_money" placeholder="���I��" style="width:40%"></input>
			</td>
		</tr>
		<tr>
			<td>�X�f���<font color=red><b>*</b></font>:</td>
			<td>
			   <input id="datepicker3" type="text" class="form-control" name="pur_product" placeholder="���I��" style="width:40%"></input>
			</td>
		</tr>
		<tr>
			<td>���פ��<font color=red><b>*</b></font>:</td>
			<td>
			   <input id="datepicker4" type="text" class="form-control" name="pur_close" placeholder="���I��" style="width:40%"></input>
			</td>
		</tr>
		<tr>
		    <td>�q�檬�A:</td>
			<td>
				<select name="pur_status" class="selectpicker form-control" style="width:40%">
		            <option value="1">���I�q��</option>
		            <option value="2">���I����</option>
		            <option value="3">�w����</option> 
		            <option value="4">���h�O</option> 
		            <option value="5">�w����</option> 
				</select>
			</td>
		</tr>	
		<tr>
		    <td>�q����B<font color=red><b>*</b></font>:</td>
			<td><input type="TEXT" name="pur_sum" class="form-control" size="45" value="<%=purVO.getPur_sum()%>" style="width:40%"/></td>
		</tr>
		<tr>
		    <td>����H<font color=red><b>*</b></font>:</td>
			<td><input type="TEXT" name="pur_name" class="form-control" size="45" value="<%=purVO.getPur_name()%>" style="width:40%"/></td>
		</tr>
		<tr>
		    <td>����H�a�}<font color=red><b>*</b></font>:</td>
			<td><input type="TEXT" name="pur_add" class="form-control" size="45" value="<%=purVO.getPur_add()%>" style="width:90%"/></td>
		</tr>
		<tr>
		    <td>����H�q��<font color=red><b>*</b></font>:</td>
			<td><input type="TEXT" name="pur_tel" class="form-control" size="45" value="<%=purVO.getPur_tel()%>" style="width:40%"/></td>
		</tr>
			</tr>
		<tr>
		    <td>�H�e�Ƶ�:</td>
			<td><input type="TEXT" name="pur_memo" class="form-control" size="45" value="<%=purVO.getPur_memo()%>" style="width:90%"/></td>
		</tr>
	
	</table>
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="pur_no" value="<%=purVO.getPur_no()%>">
	<input type="hidden" name="member_no" value="<%=purVO.getMember_no()%>">
	<input type="submit" value="�e�X�ק�" style="margin-top:-15px"></FORM>
</div>


</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.purchase_order.model.*"%>
<%@ page import="com.member.model.*"%>

<%
    PurVO purVO = (PurVO) request.getAttribute("purVO"); //PurServlet.java (Concroller), 存入req的purVO物件 (包括幫忙取出的purVO, 也包括輸入資料錯誤時的purVO物件)
%>
<html>
<head>
<title>back-end 選購訂單資料修改 - update_PurOrd_input.jsp</title></head>
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

<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"/>

<div class="col-xs-12 col-sm-8 col-sm-offset-2">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/purord/purord.do" name="form1">
<h4 style="margin-left:170px;font-weight:bold">修改訂單資料</h4>  
	<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:auto">
	    <tr>
			<td>訂單編號:</td>
			<td><%=purVO.getPur_no()%></td>	
		</tr>
		<tr>
			<td>會員姓名:</td>
			<td>${memSvc.getOneMember(purVO.member_no).member_name }</td>
		</tr>
		<tr>
			<td>訂單日期<font color=red><b>*</b></font>:</td>
			<td>
			   <input id="datepicker1" type="text" class="form-control" name="pur_date" placeholder="請點選" style="width:40%"></input>
			</td>
		</tr>
		<tr>
			<td>收款日期<font color=red><b>*</b></font>:</td>
			<td>
			   <input id="datepicker2" type="text" class="form-control" name="pur_money" placeholder="請點選" style="width:40%"></input>
			</td>
		</tr>
		<tr>
			<td>出貨日期<font color=red><b>*</b></font>:</td>
			<td>
			   <input id="datepicker3" type="text" class="form-control" name="pur_product" placeholder="請點選" style="width:40%"></input>
			</td>
		</tr>
		<tr>
			<td>結案日期<font color=red><b>*</b></font>:</td>
			<td>
			   <input id="datepicker4" type="text" class="form-control" name="pur_close" placeholder="請點選" style="width:40%"></input>
			</td>
		</tr>
		<tr>
		    <td>訂單狀態:</td>
			<td>
				<select name="pur_status" class="selectpicker form-control" style="width:40%">
		            <option value="1">未付訂金</option>
		            <option value="2">未付尾款</option>
		            <option value="3">已結案</option> 
		            <option value="4">未退費</option> 
		            <option value="5">已取消</option> 
				</select>
			</td>
		</tr>	
		<tr>
		    <td>訂單金額<font color=red><b>*</b></font>:</td>
			<td><input type="TEXT" name="pur_sum" class="form-control" size="45" value="<%=purVO.getPur_sum()%>" style="width:40%"/></td>
		</tr>
		<tr>
		    <td>收件人<font color=red><b>*</b></font>:</td>
			<td><input type="TEXT" name="pur_name" class="form-control" size="45" value="<%=purVO.getPur_name()%>" style="width:40%"/></td>
		</tr>
		<tr>
		    <td>收件人地址<font color=red><b>*</b></font>:</td>
			<td><input type="TEXT" name="pur_add" class="form-control" size="45" value="<%=purVO.getPur_add()%>" style="width:90%"/></td>
		</tr>
		<tr>
		    <td>收件人電話<font color=red><b>*</b></font>:</td>
			<td><input type="TEXT" name="pur_tel" class="form-control" size="45" value="<%=purVO.getPur_tel()%>" style="width:40%"/></td>
		</tr>
			</tr>
		<tr>
		    <td>寄送備註:</td>
			<td><input type="TEXT" name="pur_memo" class="form-control" size="45" value="<%=purVO.getPur_memo()%>" style="width:90%"/></td>
		</tr>
	
	</table>
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="pur_no" value="<%=purVO.getPur_no()%>">
	<input type="hidden" name="member_no" value="<%=purVO.getMember_no()%>">
	<input type="submit" value="送出修改" style="margin-top:-15px"></FORM>
</div>


</body>
</html>

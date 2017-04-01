<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.member.model.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>��ڥ��B�a!!~�t�ӵ��U</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<style type="text/css">
.modal-body {
	border-radius: 50%;
	height: 450px;
	width: 100%;
	background: linear-gradient(top, #ffffff, rgba(51, 51, 51, 0.4)),
		url(<%=request.getContextPath()%>/front_end/images/logo/MarryMe-logo.png)
		center no-repeat;
	background: -moz-linear-gradient(top, #ffffff, rgba(51, 51, 51, 0.4)),
		url(<%=request.getContextPath()%>/front_end/images/logo/MarryMe-logo.png)
		center no-repeat;
	background: -webkit-linear-gradient(top, #ffffff, rgba(51, 51, 51, 0.4)),
		url(<%=request.getContextPath()%>/front_end/images/logo/MarryMe-logo.png)
		center no-repeat;
}

#aa {
	margin-top: 50px;
}

body {
	margin: 0;
	padding: 0;
	width: 100%;
	height: 100%;
	background: url(<%=request.getContextPath()%>/front_end/images/secondBG.png)
		50% 0 no-repeat fixed;
}
</style>

</head>
<body onload="connect();">
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/sup/sup.do"
		name="form1" id="regist2">
		<div class="col-xs-12 col-sm-2">
			<c:if test="${not empty errorMsgs}">
				<div id="errorMsgsArea1">
					<font color='red'>�Эץ��H�U���~:
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li>${message}</li>
							</c:forEach>
						</ul>
					</font>
				</div>
			</c:if>
		</div>
		<div class="col-xs-12 col-sm-8" id="aa">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h2 class="modal-title">�t�Ӹ�ƿ�J</h2>
			</div>
			<div class="modal-body place">
				<div class="col-xs-12 col-sm-6">
					<div class="input-group">
						<div class="input-group-addon">�t�ӱb��</div>
						<input type="text" name="sup_acct" id="sup_acct" class="form-control">
					</div>
					<br>
					<div class="input-group">
						<div class="input-group-addon">�t�ӱK�X</div>
						<input type="password" name="sup_pwd" id="sup_pwd" class="form-control">
					</div>
					<br>
					<div class="input-group">
						<div class="input-group-addon">�t�ӦW��</div>
						<input type="text" name="sup_name" id="sup_name" class="form-control">
					</div>
					<br>
					<div class="input-group">
						<div class="input-group-addon">�l���ϸ�</div>
						<input type="number" name="sup_adcode" id="sup_adcode" class="form-control">
					</div>
					<br>
					<div class="input-group">
						<div class="input-group-addon">�t�Ӧa�}</div>
						<input type="text" name="sup_addr" id="sup_addr" class="form-control">
					</div>
					<br>
					<div class="input-group">
						<div class="input-group-addon">�������f</div>
						<input type="text" name="sup_con" id="sup_con" class="form-control">
					</div>
					<br>
					<div class="input-group">
						<div class="input-group-addon">�t�Ӳνs</div>
						<input type="number" name="sup_id" id="sup_id" class="form-control">
					</div>
				</div>

				<div class="col-xs-12 col-sm-6">

					<div class="input-group">
						<div class="input-group-addon">�q�ܰϽX</div>
						<input type="number" name="sup_telcode" id="sup_telcode" class="form-control">
					</div>
					<br>
					<div class="input-group">
						<div class="input-group-addon">���q�q��</div>
						<input type="number" name="sup_tel" id="sup_tel" class="form-control">
					</div>
					<br>
					<div class="input-group">
						<div class="input-group-addon">���q������X</div>
						<input type="number" name="sup_tax" id="sup_tax" class="form-control">
					</div>
					<br>
					<div class="input-group">
						<div class="input-group-addon">�t�����O</div>
						<select name="sup_type" id="sup_type" class="form-control">
							<option value="H">�|�]</option>
							<option value="C">�P�䪫�~</option>
						</select>
					</div>
					<br> <input type="hidden" name="sup_note" id="sup_note" class="form-control" value="0">


				</div>
			</div>
			<div class="modal-footer">
				<input type="hidden" name="action" value="insert">
				<button type="button" class="btn btn-success" data-toggle="modal"
					onclick="submit();">�e�X</button>
				  <input type="button" class="incredibleButton" id="incredibleButton"  value="�@���J">	
				<script>
					function submit() {
						$("#regist2").submit();
					}
				</script>
			</div>
			<div class="col-xs-12 col-sm-2"></div>
	</FORM>
</body>

<script type="text/javascript">
$(document).ready(function(){
	  $('.incredibleButton').click(function(e){
		  $('#sup_acct').attr("value","aa105");
		  $('#sup_pwd').val("aa105");
		  $('#sup_name').attr("value","�x����~");
		  $('#sup_adcode').attr("value","320");
          $('#sup_addr').attr("value","���c�Ϥ��j��300��");
		  $('#sup_con').attr("value","Leon");
		  $('#sup_id').attr("value","416931");
		  $('#sup_telcode').attr("value","03");
		  $('#sup_tel').attr("value","4257387");
		  $('#sup_tax').attr("value","0911416931");
		}); 
});
</script>

</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.place.model.*"%>
<%
PlaceVO placeVO = (PlaceVO) request.getAttribute("placeVO"); //PlaceServlet.java (Concroller), �s�Jreq��placeVO���� (�]�A�������X��placeVO, �]�]�A��J��ƿ��~�ɪ�placeVO����)
%>

<html>
<head>
<title>���a��ƭק� - update_Place_input.jsp</title>
        <meta charset="big5">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/css/orderQuery.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/Place/css/table2.css">
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<script src="https://code.jquery.com/jquery.js"></script>
	    <script src="<%=request.getContextPath()%>/supplier_end/Place/js/jquery-ui.js"></script>
	    <script src="<%=request.getContextPath() %>/supplier_end/Place/js/image.js"></script>
	    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
	  $("#upPlace").click(function(){
		 $("#place_name").val("�����s�Ǭ����");
		 $("#place_adds").val("�x�_���h�L�ϥ��w�j�D�T�q250��11��51��");
		 $("#place_note").val("�Ǭ��H���O�۵M���u�k�A��y�����s��ͺ��״I���_�Ყ��έ�ͺA�˪L�C");
	  });
   });
</script>
</head>

<body bgcolor='white'>
<nav class="navbar navbar-default" role="navigation">	
			<!-- ������ÿ��� -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- ������ -->
				<div class="col-xs-12 col-sm-4 pull-left">
						<img src="<%=request.getContextPath()%>/supplier_end/images/MarryMe-logo.png" id="logo">
						<img src="<%=request.getContextPath()%>/supplier_end/images/MarryMe-Word.png" id="logo-word">
				</div>
				<!-- ���������� -->
			
				<!-- �k���� -->
				<div class="col-xs-12  col-sm-offset-4 col-sm-4">
					<ul class="nav navbar-nav navbar-right">
						<li class="login option_text"><a href="#"><font size="3" color="orange">${supAccount.sup_name}</font> �A�z�n</a></li>
						<li class="login option_text"><a href="<%=request.getContextPath()%>/sup/sup.do?action=logout">�n�X</a></li>
						<li class="login option_text"><a href="<%=request.getContextPath()%>/sup/sup.do?action=getOne_For_Update&sup_no=${supAccount.sup_no}&number=1">�ӤH�]�w</a></li>		
					</ul>
				</div>
				<!-- �k�������� -->
			</div>
			<!-- ������ÿ��ϵ��� -->
		</nav>
        <!--����\����-->
          <!--����|�]�\����-->
         <div class="divider"></div>
         <div class="col-xs-12 col-sm-2">
			<div class="panel-group">
				<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class=" glyphicon glyphicon-home"></i>
			        		<a href="<%=request.getContextPath()%>/supplier_end/sup_firstpage.jsp">HOME</a>
			     		 </h4>
			    	</div>
			    </div>
			    <div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-cog"></i>
			        		<a data-toggle="collapse" href="#collapse1">�߮b���a�޲z</a>
			     		 </h4>
			    	</div>
			    	<div id="collapse1" class="panel-collapse collapse">
			     	 <ul class="list-group">
			        	<li class="list-group-item"><i class="glyphicon glyphicon-tasks"></i><a href="<%=request.getContextPath()%>/sup/sup.do?action=listPlace_BySup_no_A&sup_no=${supAccount.sup_no}">
			        	���a�d��</a></li>
			        	<li class="list-group-item"><i class="glyphicon glyphicon-plus"></i><a href="<%=request.getContextPath()%>/supplier_end/Place/addPlace.jsp">
			        	�s�W���a</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			  	<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-th-list"></i>
			        		<a data-toggle="collapse" href="#collapse2">���޲z</a>
			     		 </h4>
			    	</div>
			    	<div id="collapse2" class="panel-collapse collapse">
			     	 <ul class="list-group">
			         	<li class="list-group-item"><i class="glyphicon glyphicon-tags"></i><a href="<%=request.getContextPath()%>/supplier_end/menu/listSupMenus.jsp">���</a></li>
			        	<li class="list-group-item"><i class="glyphicon glyphicon-plus"></i><a href="<%=request.getContextPath() %>/supplier_end/menu/addMenu.jsp">�s�W���</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			  	<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-shopping-cart"></i>
			        		<a data-toggle="collapse" href="#collapse3">�Ȥ�w���޲z</a>
			     		</h4>
			    	</div>
			    	<div id="collapse3" class="panel-collapse collapse">
			     	 <ul class="list-group">
			        	<li class="list-group-item"><i class="glyphicon glyphicon-tags"></i><a href="<%=request.getContextPath()%>/supplier_end/Appointment/listSupAppointment.jsp">���v�M��</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			</div>
           <!--����\���浲��-->
           </div>
		
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
							
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Place/PlaceServlet.do" name="form1" enctype="multipart/form-data">
						<h4 style="margin-left:300px;font-weight:bold">�ק���a���</h4>  
						<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:auto">
							<tr>
								<td>���a�s��:</td>
								<td><%=placeVO.getPlace_no()%></td>
							</tr>
							<%-- <jsp:useBean id="SupSvc" scope="page" class="com.sup.model.SupService" />
							<tr>
								<td>�t�ӦW��:</td>
								<td>
								        <c:forEach var="SupVO" items="${SupSvc.all}">
						                <c:if test="${placeVO.sup_no==SupVO.sup_no}">
						                 ${SupVO.sup_name}
						                </c:if>
						            </c:forEach></td>
							</tr> --%>
							<tr>
								<td>���a�W��<font color="red"><b>*</b></font>:</td>
								<td><input type="TEXT" id="place_name" class="form-control" name="place_name" size="45"	value="<%=placeVO.getPlace_name()%>" style="width:50%"/></td>
							</tr>
							<tr>
								<td>���a���O:</td>
								<td>
									<select size="1" name="place_type" class="selectpicker form-control" style="width:30%">
					          			<option value="1" ${(1==placeVO.place_type)?'selected':''}>�s���B����
					 					<option value="2" ${(2==placeVO.place_type)?'selected':''}>�x��B����
					 					<option value="3" ${(3==placeVO.place_type)?'selected':''}>�B�b�\�U
					       			</select>
								</td>
							</tr>
						
						
							<tr>
								<td>���a�a��:</td>
								<td>
									<select size="1" name="place_area" class="selectpicker form-control" style="width:30%">
											<option value="N" ${("N"==placeVO.place_area)?'selected':''}>�_��
						 					<option value="E" ${("E"==placeVO.place_area)?'selected':''}>�F��
						 					<option value="S" ${("S"==placeVO.place_area)?'selected':''}>�n��
											<option value="C" ${("C"==placeVO.place_area)?'selected':''}>����		   
					       			</select>
					    		</td>
							</tr>
							<tr>
								<td>���a�a�}<font color="red"><b>*</b></font>:</td>
								<td><input type="TEXT" id="place_adds" name="place_adds" size="45" class="form-control" value="<%=placeVO.getPlace_adds()%>" /></td>
							</tr>
							<tr>
								<td>����a�Ϥ�:</td>
								<td style="text-align:center;vertical-align:middle" ><img src="ShowPlace_Pic.do?place_no=<%=placeVO.getPlace_no()%>" width="200" height="150"/>
								�n�ק諸���a�Ϥ�:
								<img id="image" width="200" >
								<input type="file" id="theFile" name="place_pic" size="45"	value="<%=placeVO.getPlace_pic()%>" />
								</td>
							</tr>
							<tr>
								<td>���a����<font color="red"><b>*</b></font>:</td>
								<td><input type="TEXT" id="place_note" name="place_note" size="45"	class="form-control" value="<%=placeVO.getPlace_note()%>" /></td>
							</tr>
							<tr>
								<td>���a���A:</td>
								<td>
									<select name="place_status" class="selectpicker form-control" style="width:30%">
							            <option value="1">���</option>
							            <option value="0">�����</option>
									</select>
						       </td>
							</tr>
							
						</table>
						<br>
						<input type="hidden" name="action"	value="update">
						<input type="hidden" name="place_no" value="<%=placeVO.getPlace_no()%>">
						<input type="hidden" name="sup_no" value="<%=placeVO.getSup_no()%>">
						<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
					    <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:listAllPlace.jsp-->
						<input type="submit" value="�e�X�ק�" style="margin-top:-35px">
						<input type="button" id="upPlace" value="magic" style="margin-top:-35px">
					</FORM>
					</div>
			</div>
	
         <!--footer�}�l-->
		<div class="divider"></div>
	  	
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>

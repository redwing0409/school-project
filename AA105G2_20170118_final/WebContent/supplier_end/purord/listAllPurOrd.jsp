<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.purchase_order.model.*"%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>�d�߭q��</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/purord/css/listAllPurOrd.css">
	
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<script src="https://code.jquery.com/jquery.js"></script>
	    <script src="<%=request.getContextPath()%>/supplier_end/purord/js/jquery-ui.js"></script>
		<script>
		 $(function() {
			var $j = jQuery.noConflict();
			$j("#datepicker").datepicker({
				    dateFormat:"yy-mm-dd",
				    showOtherMonths: true,
				    selectOtherMonths: true,
					changeMonth: true,
					changeYear: true
				});
			});
	   </script>
		
	</head>
	<body>
		<nav class="navbar navbar-default" role="navigation">
			
			<!-- ������ÿ��� -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- ������ -->
				<div class="col-xs-12 col-sm-4 pull-left">
						<img src="<%=request.getContextPath()%>/supplier_end/purord/logo/MarryMe-logo.png" id="logo">
						<img src="<%=request.getContextPath()%>/supplier_end/purord/logo/MarryMe-Word.png" id="logo-word">
				</div>
				<!-- ���������� -->
				<!-- �k���� -->
				<div class="col-xs-12  col-sm-offset-4 col-sm-4">
					<ul class="nav navbar-nav navbar-right">
						<li class="login option_text"><a href="#"><font size="3"
							color="orange">${supAccount.sup_name}</font> �A�z�n</a></li>
						<li class="login option_text"><a
							href="<%=request.getContextPath()%>/sup/sup.do?action=logout">�n�X</a></li>
						<li class="login option_text"><a href="<%=request.getContextPath()%>/sup/sup.do?action=getOne_For_Update&sup_no=${supAccount.sup_no}&number=2">�ӤH�]�w</a></li>		
					</ul>
				</div>
				<!-- �k�������� -->
			</div>
			<!-- ������ÿ��ϵ��� -->
		</nav>
        <!--����\����-->
        <div class="col-xs-12 col-sm-2">          
			 <div class="panel-group">
				<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class=" glyphicon glyphicon-home"></i>
			        		<a href="<%=request.getContextPath()%>/supplier_end/sup/supplierhome.jsp">HOME</a>
			     		 </h4>
			    	</div>
			    </div>
			  	<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-th-list"></i>
			        		<a data-toggle="collapse" href="#collapse2">�ӫ~�޲z</a>
			     		 </h4>
			    	</div>
			    	<div id="collapse2" class="panel-collapse collapse">
			     	 <ul class="list-group">
			         	<li class="list-group-item"><i class="glyphicon glyphicon-tags"></i><a href="<%=request.getContextPath()%>/supplier_end/com/listAllCommodity.jsp">�d�߰ӫ~</a></li>
			        	<li class="list-group-item"><i class="glyphicon glyphicon-plus"></i><a href="<%=request.getContextPath()%>/supplier_end/com/addCom.jsp">�s�W�ӫ~</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			  	<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-shopping-cart"></i>
			        		<a href="<%=request.getContextPath()%>/supplier_end/purord/listAllPurOrd.jsp">�q��޲z</a>
			     		</h4>
			    	</div>
			  	</div>
			    <div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-list-alt"></i>
			        		<a href="<%=request.getContextPath()%>/supplier_end/ord/listAllOrderItem.jsp">�q����Ӻ޲z</a>
			     		</h4>
			    	</div>
			  	</div>
			</div>
        </div>
        <!--����\���浲��-->
        <!--�q�椺�e--> 
        <div class="col-xs-12 col-sm-10">
            <div class="row"> 	
		        <form METHOD="post" ACTION="<%=request.getContextPath()%>/purord/purord.do">
		            <div class="col-xs-12 col-sm-5">
		            	<div class="form-group">
					        <label for="input1" class="col-sm-3 control-label">�q��s��</label>
					        <div class="col-sm-4 pull-left">
					            <input type="text" class="form-control col-sm-4 " name="pur_no" placeholder="�п�J">		
					        </div>
				        </div>
		            </div>
		            <div class="col-xs-12 col-sm-5">
		            	<div class="input-append date">
					        <label class="col-sm-3 control-label">�q����</label>
					        <div class="col-sm-4 pull-left">	           
					            <input  id="datepicker" type="text" onFocus="this.blur()" class="form-control" name="pur_date" placeholder="���I��"></input>
					        </div>
				        </div>
		            </div>
		            <div class="col-xs-12 col-sm-2">
					    <button type="submit" class="btn btn-info">�}�l�d��</button>
				        <input type="hidden" name="action" value="listPurord_ByCompositeQuery">
		            </div>  
		        </form>
		    </div>     
        <!-- Default panel contents -->
       
        <div class="divider"></div>
        <div class="row">
      		<%if (request.getAttribute("listPurord_ByCompositeQuery")!=null){%>
     		 <jsp:include page="/supplier_end/purord/listPurord_ByCompositeQuery.jsp" flush="true"/>
			<%}%> 
			
			<%if (request.getAttribute("purVO")!=null) {%>
			  <jsp:include page="/supplier_end/purord/update_PurOrd_input.jsp" flush="true" />
			<%}%>
			
			<%if (request.getAttribute("purVO2")!=null) {%>
			  <jsp:include page="/supplier_end/purord/listOnePurOrd.jsp" flush="true" />
			<%}%>
      	</div>
  
    <!--footer�}�l-->	

        <div class="divider"></div>
	  
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>
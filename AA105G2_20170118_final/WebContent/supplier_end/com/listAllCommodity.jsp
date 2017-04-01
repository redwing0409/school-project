<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="">
	<head>		
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>查詢商品</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/com/css/listAllCommodity.css">
	
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<script src="https://code.jquery.com/jquery.js"></script>
	    <script src="<%=request.getContextPath()%>/supplier_end/com/js/jquery-ui.js"></script>
	    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	    
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
			
			<!-- 手機隱藏選單區 -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 左導覽 -->
				<div class="col-xs-12 col-sm-4 pull-left">
						<img src="<%=request.getContextPath()%>/supplier_end/purord/logo/MarryMe-logo.png" id="logo">
						<img src="<%=request.getContextPath()%>/supplier_end/purord/logo/MarryMe-Word.png" id="logo-word">
				</div>
				<!-- 左導覽結束 -->
				<!-- 右導覽 -->
				<div class="col-xs-12  col-sm-offset-4 col-sm-4">
					<ul class="nav navbar-nav navbar-right">
						<li class="login option_text"><a href="#"><font size="3"
							color="orange">${supAccount.sup_name}</font> ，您好</a></li>
						<li class="login option_text"><a
						href="<%=request.getContextPath()%>/sup/sup.do?action=logout">登出</a></li>
						<li class="login option_text"><a href="<%=request.getContextPath()%>/sup/sup.do?action=getOne_For_Update&sup_no=${supAccount.sup_no}&number=2">個人設定</a></li>	
					</ul>
				</div>
				<!-- 右導覽結束 -->
			</div>
			<!-- 手機隱藏選單區結束 -->
		</nav>
        <!--左邊功能選單-->
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
			        		<a data-toggle="collapse" href="#collapse2">商品管理</a>
			     		</h4>
			    	</div>
			    	<div id="collapse2" class="panel-collapse collapse">
				     	 <ul class="list-group">
				         	<li class="list-group-item"><i class="glyphicon glyphicon-tags"></i><a href="<%=request.getContextPath()%>/supplier_end/com/listAllCommodity.jsp">查詢商品</a></li>
				        	<li class="list-group-item"><i class="glyphicon glyphicon-plus"></i><a href="<%=request.getContextPath()%>/supplier_end/com/addCom.jsp">新增商品</a></li>
				      	 </ul>
			    	</div>
			  	</div>
			  	<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-shopping-cart"></i>
			        		<a href="<%=request.getContextPath()%>/supplier_end/purord/listAllPurOrd.jsp">訂單管理</a>
			     		</h4>
			    	</div>
			  	</div>
			  	<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-list-alt"></i>
			        		<a href="<%=request.getContextPath()%>/supplier_end/ord/listAllOrderItem.jsp">訂單明細管理</a>
			     		</h4>
			    	</div>
			  	</div>
			</div>
        </div>
        <!--左邊功能選單結束-->
        <!--查詢商品內容--> 
        <div class="col-xs-12 col-sm-10">
            <div class="row"> 	
		        <form METHOD="post" ACTION="<%=request.getContextPath()%>/com/com.do">
		            <div class="col-xs-12 col-sm-5">
		            	<div class="form-group">
					        <label for="input1" class="col-sm-3 control-label">商品名稱</label>
					        <div class="col-sm-4 pull-left">
					            <input type="text" class="form-control col-sm-4 " name="com_name" placeholder="請輸入">		
					        </div>
				        </div>
		            </div>
		            <div class="col-xs-12 col-sm-5">
		            	<div class="input-append date">
					        <label class="col-sm-3 control-label">上架日期</label>
					        <div class="col-sm-4 pull-left">	           
					            <input  id="datepicker" type="text" onFocus="this.blur()" class="form-control" name="com_shelf_date" placeholder="請點選"></input>
					        </div>
				        </div>
		            </div>
		            <div class="col-xs-12 col-sm-2">
					    <button type="submit" class="btn btn-info">開始查詢</button>
				        <input type="hidden" name="action" value="listCom_ByCompositeQuery">
		            </div>  
		        </form>
		    </div> <!-- row end -->
		
        <!-- Default panel contents -->

        <div class="divider"></div>
        <div class="row">
      		<%if (request.getAttribute("listCom_ByCompositeQuery")!=null){%>
     		    <jsp:include page="/supplier_end/com/listCom_ByCompositeQuery.jsp" flush="true"/>
			<%}%> 
			
			<%if (request.getAttribute("comVO")!=null) {%>
				<jsp:include page="/supplier_end/com/update_com_input.jsp" flush="true"/>
			<%}%>
			
			<%if (request.getAttribute("comVO2")!=null) {%>
			    <jsp:include page="/supplier_end/com/listOneCom.jsp" flush="true"/>
			<%}%>
			
			<%if (request.getAttribute("comVO3")!=null) {
			     if(((List<String>)request.getAttribute("errorMsgs")).size()!=0){%>
			    	<jsp:forward page="/supplier_end/com/addCom.jsp"/> 
			     <%}else{%>
			  <jsp:include page="/supplier_end/com/listOneCombyInsert.jsp" flush="true"/>
			
            <%}}%>
             
		   <%-- 	<%if (request.getAttribute("comVO3")!=null) {%>
			  <%@ include file="/supplier_end/com/listOneCombyInsert.jsp" %>
			<%}%> --%>
      	</div>
 
		
	</body>
</html>
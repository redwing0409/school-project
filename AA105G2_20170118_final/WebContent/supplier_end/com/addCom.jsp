<%@page import="java.util.List"%>
<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.commodity.model.*"%>

<%
   ComVO comVO = (ComVO) request.getAttribute("comVO3");
%>

<html>
<head>
<title> supplier_end 商品資料新增 </title></head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/com/css/listAllCommodity.css">

<script language="JavaScript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>

<script src="<%=request.getContextPath()%>/supplier_end/com/js/jquery-ui.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script>
	 $(function() {
		var $j = jQuery.noConflict();
		$j("#datepicker1,#datepicker2").datepicker({
			    dateFormat:"yy-mm-dd",
			    showOtherMonths: true,
			    selectOtherMonths: true,
				changeMonth: true,
				changeYear: true
			});
		});
</script>
<script>
   $(document).ready(function(){
	  $("#addCom").click(function(){
		 $("#com_name").attr("value","愛麗絲");
		 $("#com_desc").attr("value","搭配細緻蕾絲與緞帶蝴蝶結。");
		 $("#com_price").attr("value","52");
		 $("#com_note").val("校稿確認並收到款項後，製作約10~14個工作天（不含例假日。<br>*未滿100張須加收版費 。");
	  });
   });
</script>
<body bgcolor='white'>
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
        
    <div class="col-xs-12 col-sm-10">
     <div class="row"> 	
        <div id="formblock" style="width:700px;height:400px;">
        <c:if test="${not empty errorMsgs}">
			<font color='red'>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="list-style-type:none;">${message}</li>
				</c:forEach>
			</ul>
			</font>
		</c:if>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/com/com.do" name="form1"  enctype="multipart/form-data">
		<h4 style="margin-left:420px;font-weight:bold">新增商品</h4>  
		<br>			
			<div class="container" id="insertContent">
					<div class="row">
						<div class="col-xs-12 col-sm-4  col-sm-offset-1">
						   <div class="input-group">
							      <span class="input-group-addon">商品名稱<font color="red"><b>*</b></font></span>
							      <input id="com_name" type="text" class="form-control" name="com_name" style="width:80%" value="<%= (comVO==null)? "婚紗相片組" : comVO.getCom_name()%>">
		   					</div>
						</div>
						<div class="col-xs-12 col-sm-4">
						    <div class="input-group" style="margin-right:50px">
		   					    <span class="input-group-addon">商品分類<font color="red"><b>*</b></font></span>
							    <select name="pcm_no" class="form-control" style="width:98%">
							            <option value="1">喜帖</option>
							            <option value="2">婚紗相本</option>
							            <option value="3">婚禮小物</option> 
							            <option value="4">婚禮用品</option> 
								</select>
		   					</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-xs-12 col-sm-4  col-sm-offset-1">
						   <div class="input-group">
							      <span class="input-group-addon">商品描述<font color="red"><b>*</b></font>:</span>
							      <input id="com_desc" type="text" class="form-control" name="com_desc" style="width:80%" value="<%= (comVO==null)? "好用喜帖" : comVO.getCom_desc()%>">
		   					</div>
						</div>
						<div class="col-xs-12 col-sm-4">
		   					<div class="input-group">
							      <span class="input-group-addon">上架日期<font color="red"><b>*</b></font>:</span>
							      <input id="datepicker1" type="text" class="form-control" name="com_shelf_date" placeholder="請點選" style="width:80%;">
		   					</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-xs-12 col-sm-4  col-sm-offset-1">
					 		<div class="input-group">
							      <span class="input-group-addon">商品價格<font color="red"><b>*</b></font>:</span>
							      <input id="com_price" type="text" class="form-control" name="com_price" style="width:80%" value="<%= (comVO==null)? "30" : comVO.getCom_price()%>">
		   					</div>
						</div>
						<div class="col-xs-12 col-sm-4">
						    <div class="input-group">
							      <span class="input-group-addon">下架日期<font color="red"><b>*</b></font>:</span>
							      <input id="datepicker2" type="text" class="form-control" name="com_off_date" placeholder="請點選" style="width:80%;">
		   					</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-xs-12 col-sm-4  col-sm-offset-1">
						   <div class="input-group">
						        <div class="row" style="margin-left:13px">
								          <label class="col-sm-5 control-label" style="margin-left:-17px;">上傳圖片:</label>		
								 </div>	    
								  <div class="row" style="margin-left:3px">
									      <input type="file" id="upPic" name="com_pic"  onchange="loadFile()"/><br>	
								  </div>    
									      <img id="pic" width=100>
		   					</div> 
						</div>
						<div class="col-xs-12 col-sm-6">
						          <div class="form-group">
						                  <div class="row" style="margin-left:13px">
							                   <label class="col-sm-4 control-label" style="margin-left:-25px;">商品備註:</label>			    
							              </div>
						                  <div class="row" style="margin-left:3px">
								  	           <textarea class="form-control" id="com_note" name="com_note" rows="5" cols="10"  style="width:53%;"></textarea>
					   		          <!-- 	 <script> CKEDITOR.replace( 'com_note',{});</script>     -->
					   		              </div>
					   		      </div>
		   				</div>
					</div>
				</div>
              </div><!--container-->
	        <br>
	        <br>
			<input type="hidden" name="action" value="insert">
			<input type="hidden" name="sup_no" value="${supAccount.sup_no}">
			<input type="hidden" name="com_status"  value="1">
			<input type="submit" value="送出新增" style="margin-top:15px;margin-left:116px"/>
			<input type="button" id="addCom" value="magic" style="margin-top:15px;margin-left:116px"/>
		</FORM>
		
		</div><!-- formblock end -->
	</div><!-- row end -->
	</body>

		<script>
		  function loadFile(){
			  var filePic = document.getElementById('upPic').files[0];
			  var readFile = new FileReader();
			  readFile.readAsDataURL(filePic);
			  readFile.addEventListener('load',function(){
				  var image = document.getElementById('pic');
				  image.src = readFile.result;
			  },false);
		  }
		</script>
</html>



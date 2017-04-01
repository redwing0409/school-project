<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.place.model.*"%>
<%
PlaceVO placeVO = (PlaceVO) request.getAttribute("placeVO"); //PlaceServlet.java (Concroller), 存入req的placeVO物件 (包括幫忙取出的placeVO, 也包括輸入資料錯誤時的placeVO物件)
%>

<html>
<head>
<title>場地資料修改 - update_Place_input.jsp</title>
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
		 $("#place_name").val("陽明山納美花園");
		 $("#place_adds").val("台北市士林區仰德大道三段250巷11弄51號");
		 $("#place_note").val("納美以環保自然的工法，營造陽明山原生種豐富的奇花異草及原生態森林。");
	  });
   });
</script>
</head>

<body bgcolor='white'>
<nav class="navbar navbar-default" role="navigation">	
			<!-- 手機隱藏選單區 -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 左導覽 -->
				<div class="col-xs-12 col-sm-4 pull-left">
						<img src="<%=request.getContextPath()%>/supplier_end/images/MarryMe-logo.png" id="logo">
						<img src="<%=request.getContextPath()%>/supplier_end/images/MarryMe-Word.png" id="logo-word">
				</div>
				<!-- 左導覽結束 -->
			
				<!-- 右導覽 -->
				<div class="col-xs-12  col-sm-offset-4 col-sm-4">
					<ul class="nav navbar-nav navbar-right">
						<li class="login option_text"><a href="#"><font size="3" color="orange">${supAccount.sup_name}</font> ，您好</a></li>
						<li class="login option_text"><a href="<%=request.getContextPath()%>/sup/sup.do?action=logout">登出</a></li>
						<li class="login option_text"><a href="<%=request.getContextPath()%>/sup/sup.do?action=getOne_For_Update&sup_no=${supAccount.sup_no}&number=1">個人設定</a></li>		
					</ul>
				</div>
				<!-- 右導覽結束 -->
			</div>
			<!-- 手機隱藏選單區結束 -->
		</nav>
        <!--左邊功能選單-->
          <!--左邊會館功能選單-->
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
			        		<a data-toggle="collapse" href="#collapse1">喜宴場地管理</a>
			     		 </h4>
			    	</div>
			    	<div id="collapse1" class="panel-collapse collapse">
			     	 <ul class="list-group">
			        	<li class="list-group-item"><i class="glyphicon glyphicon-tasks"></i><a href="<%=request.getContextPath()%>/sup/sup.do?action=listPlace_BySup_no_A&sup_no=${supAccount.sup_no}">
			        	場地查詢</a></li>
			        	<li class="list-group-item"><i class="glyphicon glyphicon-plus"></i><a href="<%=request.getContextPath()%>/supplier_end/Place/addPlace.jsp">
			        	新增場地</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			  	<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-th-list"></i>
			        		<a data-toggle="collapse" href="#collapse2">菜單管理</a>
			     		 </h4>
			    	</div>
			    	<div id="collapse2" class="panel-collapse collapse">
			     	 <ul class="list-group">
			         	<li class="list-group-item"><i class="glyphicon glyphicon-tags"></i><a href="<%=request.getContextPath()%>/supplier_end/menu/listSupMenus.jsp">菜單</a></li>
			        	<li class="list-group-item"><i class="glyphicon glyphicon-plus"></i><a href="<%=request.getContextPath() %>/supplier_end/menu/addMenu.jsp">新增菜單</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			  	<div class="panel panel-default">
			    	<div class="panel-heading">
			      		<h4 class="panel-title"><i class="glyphicon glyphicon-shopping-cart"></i>
			        		<a data-toggle="collapse" href="#collapse3">客戶預約管理</a>
			     		</h4>
			    	</div>
			    	<div id="collapse3" class="panel-collapse collapse">
			     	 <ul class="list-group">
			        	<li class="list-group-item"><i class="glyphicon glyphicon-tags"></i><a href="<%=request.getContextPath()%>/supplier_end/Appointment/listSupAppointment.jsp">歷史清單</a></li>
			      	 </ul>
			    	</div>
			  	</div>
			</div>
           <!--左邊功能選單結束-->
           </div>
		
		<div class="divider"></div>

	        <div class="col-xs-12 col-sm-10">
		            <div class="row"> 	 
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
							
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Place/PlaceServlet.do" name="form1" enctype="multipart/form-data">
						<h4 style="margin-left:300px;font-weight:bold">修改場地資料</h4>  
						<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:auto">
							<tr>
								<td>場地編號:</td>
								<td><%=placeVO.getPlace_no()%></td>
							</tr>
							<%-- <jsp:useBean id="SupSvc" scope="page" class="com.sup.model.SupService" />
							<tr>
								<td>廠商名稱:</td>
								<td>
								        <c:forEach var="SupVO" items="${SupSvc.all}">
						                <c:if test="${placeVO.sup_no==SupVO.sup_no}">
						                 ${SupVO.sup_name}
						                </c:if>
						            </c:forEach></td>
							</tr> --%>
							<tr>
								<td>場地名稱<font color="red"><b>*</b></font>:</td>
								<td><input type="TEXT" id="place_name" class="form-control" name="place_name" size="45"	value="<%=placeVO.getPlace_name()%>" style="width:50%"/></td>
							</tr>
							<tr>
								<td>場地類別:</td>
								<td>
									<select size="1" name="place_type" class="selectpicker form-control" style="width:30%">
					          			<option value="1" ${(1==placeVO.place_type)?'selected':''}>酒店、飯店
					 					<option value="2" ${(2==placeVO.place_type)?'selected':''}>庭園、莊園
					 					<option value="3" ${(3==placeVO.place_type)?'selected':''}>婚宴餐廳
					       			</select>
								</td>
							</tr>
						
						
							<tr>
								<td>場地地區:</td>
								<td>
									<select size="1" name="place_area" class="selectpicker form-control" style="width:30%">
											<option value="N" ${("N"==placeVO.place_area)?'selected':''}>北部
						 					<option value="E" ${("E"==placeVO.place_area)?'selected':''}>東部
						 					<option value="S" ${("S"==placeVO.place_area)?'selected':''}>南部
											<option value="C" ${("C"==placeVO.place_area)?'selected':''}>中部		   
					       			</select>
					    		</td>
							</tr>
							<tr>
								<td>場地地址<font color="red"><b>*</b></font>:</td>
								<td><input type="TEXT" id="place_adds" name="place_adds" size="45" class="form-control" value="<%=placeVO.getPlace_adds()%>" /></td>
							</tr>
							<tr>
								<td>原場地圖片:</td>
								<td style="text-align:center;vertical-align:middle" ><img src="ShowPlace_Pic.do?place_no=<%=placeVO.getPlace_no()%>" width="200" height="150"/>
								要修改的場地圖片:
								<img id="image" width="200" >
								<input type="file" id="theFile" name="place_pic" size="45"	value="<%=placeVO.getPlace_pic()%>" />
								</td>
							</tr>
							<tr>
								<td>場地介紹<font color="red"><b>*</b></font>:</td>
								<td><input type="TEXT" id="place_note" name="place_note" size="45"	class="form-control" value="<%=placeVO.getPlace_note()%>" /></td>
							</tr>
							<tr>
								<td>場地狀態:</td>
								<td>
									<select name="place_status" class="selectpicker form-control" style="width:30%">
							            <option value="1">顯示</option>
							            <option value="0">不顯示</option>
									</select>
						       </td>
							</tr>
							
						</table>
						<br>
						<input type="hidden" name="action"	value="update">
						<input type="hidden" name="place_no" value="<%=placeVO.getPlace_no()%>">
						<input type="hidden" name="sup_no" value="<%=placeVO.getSup_no()%>">
						<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
					    <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--只用於:listAllPlace.jsp-->
						<input type="submit" value="送出修改" style="margin-top:-35px">
						<input type="button" id="upPlace" value="magic" style="margin-top:-35px">
					</FORM>
					</div>
			</div>
	
         <!--footer開始-->
		<div class="divider"></div>
	  	
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>

<%
	MenuVO MenuVO = (MenuVO) request.getAttribute("MenuVO"); //MenuServlet.java (Concroller), 存入req的MenuVO物件 (包括幫忙取出的MenuVO, 也包括輸入資料錯誤時的MenuVO物件)
%>
<html>
<head>
<title>菜色資料修改 - update_menu_input.jsp</title>
        <meta charset="big5">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/css/orderQuery.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/menu/css/table2.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<script src="https://code.jquery.com/jquery.js"></script>
	    <script src="js/jquery-ui.js"></script>
	    <script src="<%=request.getContextPath() %>/supplier_end/menu/js/image.js"></script>
	    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	    
		<script>
		   $(document).ready(function(){
			  $("#upMenu").click(function(){
				 $("#menu_name").attr("value","番茄海鮮細扁麵");
				 $("#menu_note").attr("value","微酸微甜的番茄醬汁，搭配蝦子、淡菜、透抽及蛤蠣一起拌炒，番茄酸甜加上滿滿海味，最後點上一些奧立岡葉，讓整道料理美味更加升級!");
				 $("#menu_price").val("580");
			  });
		   });
		</script>	
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
</head>
<body>
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
			        	<li class="list-group-item"><i class="glyphicon glyphicon-tasks"></i></i><a href="<%=request.getContextPath()%>/sup/sup.do?action=listPlace_BySup_no_A&sup_no=${supAccount.sup_no}">
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
					
					<FORM METHOD="post" ACTION="menu.do" name="form1" enctype="multipart/form-data">
					 <h4 style="margin-left:300px;font-weight:bold">菜單資料修改</h4>  
					<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:auto">
						<tr>
							<td>菜色編號:</td>
							<td><%=MenuVO.getMenu_no()%></td>
							
						</tr>
						<jsp:useBean id="PlaceSvc" scope="page" class="com.place.model.PlaceService" />
						<tr>
							<td>場地:</td>
							<td><c:forEach var="PlaceVO" items="${PlaceSvc.all}">
									<c:if test="${MenuVO.place_no==PlaceVO.place_no}">
						                    ${PlaceVO.place_name}
					                 </c:if>
							       </c:forEach> 
							 </td>
						</tr>
						<tr>
							<td>菜色名稱<font color=red><b>*</b></font>:</td>
							<td><input type="TEXT" id="menu_name" name="menu_name" class="form-control"
								value="<%= (MenuVO==null)? "" : MenuVO.getMenu_name()%>" style="width:40%"/></td>
						</tr>
						<tr>
							<td>菜色價格<font color=red><b>*</b></font>:</td>
							<td><input type="TEXT" id="menu_price" name="menu_price" class="form-control"
								value="<%= (MenuVO==null)? "" : MenuVO.getMenu_price()%>" style="width:40%"/></td>
						</tr>
						<tr>
							<td>菜色介紹<font color=red><b>*</b></font>:</td>
							<td><input type="TEXT" id="menu_note" name="menu_note" class="form-control"
								value="<%= (MenuVO==null)? "" : MenuVO.getMenu_note()%>"/></td>
						</tr>
						<tr>
							<td>菜色圖片:</td>
							<td style="text-align:center;vertical-align:middle" ><img src="ShowMenu_Pic.do?menu_no=<%=MenuVO.getMenu_no()%>" width="200"/>
							要修改的菜色圖片:
							<img id="image" width="200" >
							<input type="file" id="theFile" name="menu_pic" size="45" value="<%=MenuVO.getMenu_no()%>">
							</td>
						</tr>
						
					</table>
					<br>
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="menu_no" value="<%=MenuVO.getMenu_no()%>">
					<input type="hidden" name="place_no" value="<%=MenuVO.getPlace_no()%>">
					<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
					<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--只用於:istAllEmp.jsp-->
					<input type="submit" value="送出修改" style="margin-top:-35px">
					<input type="button" id="upMenu" value="magic" style="margin-top:-35px">
					</FORM>
					      
			        </div>
			   </div>
	   
		 <!--footer開始-->
				<div class="divider"></div>
				<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
				
</body>
</html>

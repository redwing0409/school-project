<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.place.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<jsp:useBean id="listPlace_BySup_no" scope="request" type="java.util.Set" />
<jsp:useBean id="SupSvc" scope="page" class="com.sup.model.SupService" />
<html>
<head>
		<meta charset="big5">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>查詢場地</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/css/orderQuery.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/sup/css/table2.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<script src="https://code.jquery.com/jquery.js"></script>
	    <script src="js/jquery-ui.js"></script>
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

        <!--場地內容-->
        <div class="divider"></div>
        <div class="col-xs-12 col-sm-10">
           <div class="row">
				<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue">
					<tr style="font-weight:bold">
						<th>場地編號</th>
						<th>廠商編號</th>
						<th>場地類別</th>
						<th>場地名稱</th>
						<th>場地地區</th>
						<th>場地地址</th>
						<th>場地圖片</th>
						<th>場地介紹</th>
						<th>場地狀態</th>
						<th>編輯</th>
					</tr>
					<%@ include file="pages/listPlace_BySup_no_page1.file" %>
					<c:forEach var="placeVO" items="${listPlace_BySup_no}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr align='center' valign='middle' ${(placeVO.place_no==param.place_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
							<td>${placeVO.place_no}</td>
							<td><c:forEach var="SupVO" items="${SupSvc.all}">
				                    <c:if test="${placeVO.sup_no==SupVO.sup_no}">
					                    ${SupVO.sup_no}【<font color=orange>${SupVO.sup_name}</font>】
				                    </c:if>
				                </c:forEach>
				                </td>
							<td>
								<c:if test="${placeVO.place_type == 1}">酒店、飯店</c:if>
					          	<c:if test="${placeVO.place_type == 2}">庭園、莊園</c:if>
					          	<c:if test="${placeVO.place_type == 3}">婚宴餐廳</c:if>
							</td>
							<td>${placeVO.place_name}</td>
							<td>
								<c:if test="${placeVO.place_area == 'N'}">北部</c:if>
					          	<c:if test="${placeVO.place_area == 'C'}">中部</c:if>
					          	<c:if test="${placeVO.place_area == 'S'}">南部</c:if>
					          	<c:if test="${placeVO.place_area == 'E'}">東部</c:if>
							</td>
							<td>${placeVO.place_adds}</td>
							<td>
								<img src="<%=request.getContextPath()%>/Place/ShowPlace_Pic.do?place_no=${placeVO.place_no}" width="100" height="80"/>
							</td>
							<td>${placeVO.place_note}</td>
							<td>
							   <c:if test="${placeVO.place_status=='1'}">顯示</c:if>
							   <c:if test="${placeVO.place_status=='0'}">不顯示</c:if>
							</td>
							<td>
							     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Place/PlaceServlet.do">
							     <input type="submit" value="修改">
							     <input type="hidden" name="place_no" value="${placeVO.place_no}">
							     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
							     <input type="hidden" name="whichPage"	value="<%=whichPage%>"> 
							     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="pages/listPlace_BySup_no_page2.file" %>
            </div>
         </div>

   
	  	<div class="divider"></div>
	  	
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>

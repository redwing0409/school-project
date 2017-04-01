<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sup.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	SupService SupSvc = new SupService();
	List<SupVO> list = SupSvc.getAllUnCheck();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有廠商資料 - listUnCheckSup.jsp</title>
<meta charset="big5">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>員工平台</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/css/orderQuery.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/sup/css/table1.css">

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
			changeMonth : true,
			changeYear : true
		});
	});
</script>

<style type="text/css">
body {
	margin: 0;
	padding: 0;
	height: 100%;
	width: 100%;
	 background:linear-gradient(top,#ffffff,rgba(80%,80%,80%,0)),url(<%=request.getContextPath()%>/back_end/images/p8.jpg) no-repeat;
	 background:-moz-linear-gradient(top,#ffffff,rgba(80%,80%,80%,0)),url(<%=request.getContextPath()%>/back_end/images/p8.jpg) no-repeat;
	 background:-webkit-linear-gradient(top,#ffffff,rgba(80%,80%,80%,0)),url(<%=request.getContextPath()%>/back_end/images/p8.jpg) no-repeat;
	background-size: cover;
}

#aa {
	margin-top: 50px;
}
</style>

</head>
<body bgcolor='white'>
	<nav class="navbar navbar-default" role="navigation">
		<!-- 手機隱藏選單區 -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<!-- 左導覽 -->
			<div class="col-xs-12 col-sm-4 pull-left">
				<img
					src="<%=request.getContextPath()%>/back_end/images/MarryMe-logo.png"
					id="logo"> <img
					src="<%=request.getContextPath()%>/back_end/images/MarryMe-Word.png"
					id="logo-word">
			</div>
			<!-- 左導覽結束 -->

			<!-- 右導覽 -->
			<div class="col-xs-12  col-sm-offset-4 col-sm-4">
				<ul class="nav navbar-nav navbar-right">
					<li class="login option_text"><a href="#"><font size="3"
							color="orange">${empAccount.emp_name}</font> ，您好</a></li>
					<li class="login option_text"><a
						href="<%=request.getContextPath()%>/employee/employee.do?action=logout">登出</a></li>
					<li class="login option_text"><a
						href="<%=request.getContextPath()%>/employee/employee.do?action=getOne_For_Update&emp_no=${empAccount.emp_no}">個人設定</a></li>
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
					<h4 class="panel-title">
						<i class=" glyphicon glyphicon-home"></i> <a
							href="<%=request.getContextPath()%>/back_end/employee/employee_firstpage.jsp">HOME</a>
					</h4>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<i class="glyphicon glyphicon-cog"></i> <a data-toggle="collapse"
							href="#collapse1">廠商管理</a>
					</h4>
				</div>
				<div id="collapse1" class="panel-collapse collapse">
					<ul class="list-group">
						<li class="list-group-item"><i
							class="glyphicon glyphicon-tasks"></i><a
							href="<%=request.getContextPath()%>/back_end/sup/listAllSup.jsp">
								廠商查詢</a></li>
						<li class="list-group-item"><i
							class="glyphicon glyphicon-plus"></i><a
							href="<%=request.getContextPath()%>/back_end/sup/listUnCheckSup.jsp">
								廠商認證</a></li>
					</ul>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<i class="glyphicon glyphicon-th-list"></i> <a
							data-toggle="collapse" href="#collapse2">員工管理</a>
					</h4>
				</div>
				<div id="collapse2" class="panel-collapse collapse">
					<ul class="list-group">
						<li class="list-group-item"><i
							class="glyphicon glyphicon-tags"></i><a
							href="<%=request.getContextPath()%>/back_end/employee/listAllEmployee.jsp">所有員工</a></li>
						<li class="list-group-item"><i
							class="glyphicon glyphicon-plus"></i><a
							href="<%=request.getContextPath()%>/back_end/employee/addEmployee.jsp">新增員工</a></li>
					</ul>
				</div>
			</div>

		</div>
		<!--左邊會館功能選單結束-->
	</div>


	<div class="divider"></div>
	<div class="col-xs-12 col-sm-10">
		<div class="row">
			<div class="modal-body">
				<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue" >
					<tr style="font-weight: bold">
						<th>廠商編號</th>
						<th>廠商名稱</th>
						<th>廠商帳號</th>
						<th>統一編號</th>
						<th>窗口</th>
						<th>電話區碼</th>
						<th>電話</th>
						<th>手機</th>
						<th>郵遞區號</th>
						<th>地址</th>
						<th>備註</th>
						<th>類別</th>
						<th>審核</th>
					</tr>

					<%@ include file="pages/page1.file"%>

					<c:forEach var="SupVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<c:if test="${SupVO.sup_note=='0'}">
							<tr align='center' valign='middle' height="100">
								<!--將修改的那一筆加入對比色而已-->
								<td>${SupVO.sup_no}</td>
								<td>${SupVO.sup_name}</td>
								<td>${SupVO.sup_acct}</td>
								<td>${SupVO.sup_id}</td>
								<td>${SupVO.sup_con}</td>
								<td>0${SupVO.sup_telcode}</td>
								<td>${SupVO.sup_tel}</td>
								<td>0${SupVO.sup_tax}</td>
								<td>${SupVO.sup_adcode}</td>
								<td>${SupVO.sup_addr}</td>
								<td>${SupVO.sup_note=="0"?"尚待驗證":""}</td>
								<td>${SupVO.sup_type=="H"?"會館":"周邊商品"}</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/sup/sup.do">
										<input type="submit" value="進入審核"> <input
											type="hidden" name="sup_no" value="${SupVO.sup_no}">
										<%-- 			     				<input type="hidden" name="chef_chk_cond" value="${chefVO.chef_chk_cond}"> --%>
										<input type="hidden" name="action" value="getOne_For_Check">
									</FORM> <%--                            <div class="btn btn-danger btn-xs" href="<%=request.getContextPath()%>/back-end/chef/ChefCheckPage.jsp">進入審核</div> --%>
								</td>

							</tr>
						</c:if>
					</c:forEach>

				</table>

				<%@ include file="pages/page2.file"%>
			</div>
		</div>
	</div>


	<script
		src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>

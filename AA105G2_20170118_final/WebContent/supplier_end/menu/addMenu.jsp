<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.menu.model.*"%>
<%@ page import="com.place.model.*"%>
<%@ page import="com.sup.model.*"%>
<%
MenuVO MenuVO = (MenuVO) request.getAttribute("MenuVO");
SupService SupSvc = new SupService();
pageContext.setAttribute("SupSvc", SupSvc);
%>

<html>
<head>
		<title>����Ʒs�W - addMenu.jsp</title>
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
	    <script src="<%=request.getContextPath()%>/supplier_end/Appointment/js/jquery-ui.js"></script>
	    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	    
<script>
   $(document).ready(function(){
	  $("#addMenu").click(function(){
		 $("#menu_name").attr("value","�f�X���A�ӫ���");
		 $("#menu_note").attr("value","�L�ķL�����f�X��ġA�f�t���l�B�H��B�z��ε����@�_�ժ��A�f�X�Ĳ��[�W���������A�̫��I�W�@�Ƕ��ߩ����A����D�Ʋz������[�ɯ�!");
		 $("#menu_price").val("580");
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
			        	<li class="list-group-item"><i class="glyphicon glyphicon-tasks"></i></i><a href="<%=request.getContextPath()%>/sup/sup.do?action=listPlace_BySup_no_A&sup_no=${supAccount.sup_no}">
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
					<%-- ���~���C --%>
					<c:if test="${not empty errorMsgs}">
						<font color='red'>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="list-style-type:none;">${message}</li>
							</c:forEach>
						</ul>
						</font>
					</c:if>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/menu.do" name="form1" enctype="multipart/form-data">
					<h4 style="margin-left:170px;font-weight:bold">�s�W�����</h4>  
					<table  class="table table-hover table-striped table-bordered table-condensed" color="lightblue" style="width:auto">
					    <jsp:useBean id="placeSvc" scope="page" class="com.place.model.PlaceService" />
						<tr>
							<td>���W��<font color="red"><b>*</b></font>:</td>
							<td><input type="TEXT" id="menu_name" name="menu_name" class="form-control"
								value="<%= (MenuVO==null)? "" : MenuVO.getMenu_name()%>" /></td>
						</tr>
						<tr>
							<td>����<font color="red"><b>*</b></font>:</td>
							<td><input type="TEXT" id="menu_price" name="menu_price" class="form-control"
								value="<%= (MenuVO==null)? "" : MenuVO.getMenu_price()%>" style="width:40%"/></td>
						</tr>
						<tr>
							<td>���a:</td><% String sup_no=((SupVO)session.getAttribute("supAccount")).getSup_no(); %>
							<td><select name="place_no"><c:forEach var="placeVO" items="<%= SupSvc.getPlaceBySup_no(sup_no) %>">
									<option  value="${placeVO.place_no}"  >${placeVO.place_name}</option></c:forEach></select>
							</td>
						</tr>
						<tr>
							<td>��⤶��<font color="red"><b>*</b></font>:</td>
							<td><input type="TEXT" id="menu_note" name="menu_note" class="form-control"
								value="<%= (MenuVO==null)? "" : MenuVO.getMenu_note()%>" /></td>
						</tr>
						<tr>
							<td>���Ϥ�:</td>
							<td><input type="file" id="upPic" name="menu_pic" size="45" onchange="loadFile()"/><br>
							<img id="pic" width=100></td>
						</tr>
						
					
					</table>
					<br>
					 
					<input type="hidden" name="action" value="insert">
					<input type="submit" value="�e�X�s�W" style="margin-top:-35px">
					<input type="button" id="addMenu" value="magic" style="margin-top:-15px">
					</FORM>
					</div>
			</div>

	        <!--footer�}�l-->
					<div class="divider"></div>
				  	
					<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

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
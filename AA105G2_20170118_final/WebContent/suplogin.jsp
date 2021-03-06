<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script
	src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<title>login.jsp</title>

<style type="text/css">
@import url(http://fonts.googleapis.com/css?family=Raleway:600);
body {
	margin: 0;
	padding: 0;
	height: 100%;
	width: 100%;
	background-image: url("<%=request.getContextPath()%>/supplier_end/images/p9.jpg");
	background-size: cover;
}

span {	
	color:#fa6198;
	font-family: 微軟正黑體;
	font-size:25px;
	font-weight:bold;
}

label{
   color:#fa6198;
   forn-family:
   font-family: 微軟正黑體;
   font-size:20px;
   font-weight:bold;
}

p {
    color:#000000;
	font-family: 微軟正黑體;
	font-size:16px;
}

.col-centered {
	float: none;
	margin: 0 auto;
	margin-top:150px;	
	background-color:lightgray;
}

.wrath-content-box {
	padding: 20px;	
	background-color:lightgray;
}

.text-right{
	margin-top:20px;
	margin-left:25px;
}
</style>
</head>
<body>
<center>	
<form action="<%= request.getContextPath() %>/login/suploginhandler" method="post">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-3 col-centered table-bordered ">
						<div class="wrath-content-box">
							<span>廠商登入</span>
						</div>
						<div class="wrath-content-box">
							<div>
								<label>帳號</label>
								<input type="text" class="form-control"  name="account" id="account" value="" />
							</div>
							<div>
								<label>密碼</label>
								<input type="password" class="form-control"	id="password" name="password" value="" />
							</div>
							<div class="col-sm-6">
								<span><img src="<%=request.getContextPath()%>/front_end/images/logo/logo2.png" style="width: 120% ; height: 80%"></span>
							</div>
							<div class="col-sm-6">
								<div class="text-right">
									<input type="submit" class="btn btn-success btn-sm form-control" value="登入" />
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
	</form>
	<c:if test="${not empty errorMsgs}">
	<font color='red'>
	${errorMsgs}
	</font>
</c:if>
</center>
</body>
</html>
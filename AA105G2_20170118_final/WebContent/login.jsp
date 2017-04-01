<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    background-image: url("<%=request.getContextPath()%>/front_end/images/pic/p1.jpg");    
    background-repeat:no-repeat;
    background-size: cover;
	margin:0;
	padding:0;
	height: 100%;
	width: 100%;	
}
span {	
	color:#fa6198;
	font-family: 微軟正黑體;
	font-size:25px;
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
	background-color:;	
}
.wrath-content-box {
	padding: 20px;	
}
.text-right{
	margin-top:20px;
	margin-left:25px;
}
/*  帳號密碼開始  */
.input-element {
  margin: 0 auto;
  padding-top: 30px;
  position: relative;
  overflow: visible;
}
.input-element:last-child {
  margin-bottom: 0;
}
 .input-element:after {   /*欄位點選前底線 */
  content: " ";
  display: block;
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 3px;
  background: none;
}
.input-element:before {   /*欄位點選後底線*/
  content: " ";
  display: block;
  position: absolute;
  bottom: 0;
  width: 0;
  height: 3px;
  background: #3B7FC4;
  -webkit-transition: width .3s ease-in-out;
  transition: width .3s ease-in-out;
  z-index: 20;
  left: 50%;
  -webkit-transform: translateX(-50%);
  transform: translateX(-50%);
}
/*帳號密碼結束*/
.input-element label {                    /*欄位文字*/
  -webkit-backface-visibility: hidden;
  -moz-backface-visibility: hidden;
  -ms-backface-visibility: hidden;
  -webkit-transform: translateZ(0);
  transform: translateZ(0);
  color: grey;
  font-size: 18px;
  top: 35px;
  left: 5px;
  text-align:left;
  position: absolute;
  -webkit-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
  width: 100%;
}
.input-element label:hover {              /*滑鼠移入的欄位文字*/
  cursor: pointer;
  color: #fa6198;
}
.input-element input {                    /*輸入欄文字*/
  width: 100%;
  padding: 3px 0;
  background: none;
  border: none;
  outline: none;
  color: black;
  font-size: 15px;
  -webkit-backface-visibility: hidden;
}
.input-element.active label {
  top: 15px;
  color: #3B7FC4;
  font-size: 13px;
}
.input-element.active:before {
  width: 100%;
}		
</style>
</head>

<body>
	<center>
		<form action="<%=request.getContextPath()%>/login/loginhandler"
			method="post">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-3 col-centered table-bordered ">
						<div class="wrath-content-box">
							<span>會 員 登 入</span>
						</div>
						<div class="wrath-content-box">
							<div class="input-element">
								<label for="account">帳號</label>
								<input type="text" class="form-control"  name="account" id="account" value="" />
							</div>
							<div class="input-element">
								<label for="password">密碼</label>
								<input type="password" class="form-control"	id="password" name="password" value="" />
							</div>
							<div class="col-sm-6">
								<span><img src="<%=request.getContextPath()%>/front_end/images/logo/logo2.png" style="width: 120% ; height: 80%"></span>
							</div>
							<div class="col-sm-6">
								<div class="form-group1 text-right">
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
			<font color='red'> ${errorMsgs} </font>
		</c:if>
	</center>
	<script src="http://libs.useso.com/js/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
	<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>			
	<script type="text/javascript">
		$('.input-element input').focusin(function(){
		  $(this).parent().addClass('active');
		});

		$('.input-element input').blur(function(){
		  if(!$(this).val().length > 0) {  
		    $(this).parent().removeClass('active');
		  }
		});
	</script>
</body>
</html>
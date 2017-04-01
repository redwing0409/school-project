<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.order_item.model.*"%>
<%@ page import="com.commodity.model.*"%>
<%@ page import="com.member.model.*"%>
<%@page import="com.member.model.*"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("/front_end/frontIndex.jsp");
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<jsp:useBean id="comSvc" scope="page" class="com.commodity.model.ComService"/>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"/>
<!--  two capture time methods -->
<jsp:useBean id="dateObject"  scope="page" class="java.util.Date" />
<c:set var="date" value="<%=new java.util.Date()%>" />
<!--  two capture time methods end -->

<%
  // ComService comSvc = new ComService();
  // List<ComVO> list = comSvc.getAll();
  // System.out.println(((com.commodity.model.ComVO)(list.get(0))).getCom_no());
  // System.out.println(((com.commodity.model.ComVO)(list.get(1))).getCom_no());
  // System.out.println();
  // pageContext.setAttribute("list",list);
%>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>填寫寄送資料</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/TransmitInfo.css">
       <style>
       /* form effect */
			#case_form form {
			  width: 70%;
			}
			#case_form form dl {
			  width: 100%;
			  margin: 0 0 1em;
			  overflow: hidden;
			}
			#case_form form dt {
			  float: left;
			  width: 20%;
			  padding-top: 2%;
			  line-height: 1;
			}
			#case_form form dd {
			  float: left;
			  width: 80%;
			}
			
			#case_form input,
			#case_form textarea {
			  -webkit-transition: background 0.4s linear;
			          transition: background 0.4s linear;
			}
			
			#case_form input {
			  margin-left:-35px;
			  padding: 2%;
			  width: 70%;
			  border: 1px solid #e8e8e8;
			  background: #eaeaea;
			  line-height: 1.2;
			}
			#case_form textarea {
			  margin-left:-35px;
			  width: 70%;
			  min-height: 3 em;
			  padding: 2%;
			  border: 1px solid #e8e8e8;
			  background: #eaeaea;
			  line-height: 1.6;
			}
			#case_form input:focus,
			#case_form textarea:focus {
			  background: #f8f8f8;
			}
#accountMemberName{
/* 	background-color:yellow; */
	position: fixed;
    margin-top: 1%;
    cursor: pointer;
    z-index: 3;
	float:right;
	z-index:3;
	margin-left: 370px;
}
#login{
/* 	background-color:red; */
	position: fixed;
    margin-top: 1%;
    cursor: pointer;
    z-index: 3;
	float:right;
	z-index:3;
	margin-left: 370px;
}
#headerMarginLeft{ 
 	margin-left:339px; 
}
#topHeaderArea,#topHeaderGroups,#topHeaderGroupsIcon{
	background-color:#e9eaeb;
}
		
       </style>
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
        <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/bootstrap.min.js"></script>

		 <!--  Nav toggle effect -->
		<script type="text/javascript">
			$(document).ready( function() {
				$(".slidename").mouseover(function() {
					$(".slideCharacter").slideDown(1000);
				});
				$(".slidename").mouseleave(function() {
					$(".slideCharacter").slideUp(1000);
				});
				
				$(".slidemain").mouseover(function() {
					$(".slidesub").slideDown(1000);
				});
				$(".slidesub").mouseleave(function() {
					$(".slidesub").slideUp(1000);
				});
				
				$("#login").mouseover(function() {
					$(".slideLogin").slideDown(1000);
				});
				$("#login").mouseleave(function() {
					$(".slideLogin").slideUp(1000);
				});
		});
		</script>  
		 <!--  Nav toggle effect end-->
		<!-- form effect -->
		<script type="text/javascript">
		 $(document).ready(function(){
		 	var rule1=/^.{1,10}$/;
            $("#name").blur(function(){
                if(rule1.test($(this).val())){
                    $('.error1').text('')
                    $(this).css("border-color","#e8e8e8")
                }else{
                    $('.error1').text('請輸入姓名')
                    $(this).css("border-color","red")
                }
            })

            var rule2=/^[09]{2}[0-9]{8}$/;
            $("#phone").blur(function(){
                if(rule2.test($(this).val())){
                    $('.error2').text('')
                    $(this).css("border-color","#e8e8e8")
                }else{
                    $('.error2').text('請輸入09xxxxxxxx')
                    $(this).css("border-color","red")
                }
           	 });	

           	 var rule3=/^.{1,50}$/;
             $("#address").blur(function(){
                if(rule3.test($(this).val())){
                    $('.error3').text('')
                    $(this).css("border-color","#e8e8e8")
                }else{
                    $('.error3').text('請輸入地址')
                    $(this).css("border-color","red")
                }
              })	
           });
		</script>  
		<!-- form effect end -->
	</head>
	<body>
	
        <!-- 滾動導覽列 -->
     	<div class="container">
			<div class="row">
			 	<div class="col-xs-12 col-sm-6">
					<div class="slidemenu" style="margin-left: 5px">
						<c:if test="${account.member_no !=null }">
							<div class="slidemain">
								<span><img
									style="width: 15px; height: 15px; margin-bottom: 3px"
									src="<%=request.getContextPath()%>/front_end/images/img/setting.png"></span>我的管理
							</div>
							<div class="slidesub" style="display: none">
								<ul>
									<li>
										<a href="<%= request.getContextPath() %>/front_end/updateMemberInfo.jsp" id="updateMemberSelect">修改基本資料</a>
									</li>
									<li><a href="<%= request.getContextPath() %>/front_end/friend/listFriendByMember.jsp">管理好友</a></li>
									<li><a href="<%= request.getContextPath() %>/front_end/Appointment/listOwnAppointment.jsp">瀏覽預約清單</a></li>
							<li><a href="<%= request.getContextPath() %>/front_end/shoppingcar/ShoppingRecord.jsp">瀏覽購物清單</a></li>
									<li>
									<a href="<%= request.getContextPath() %>/member/memberServlet.do?action=logOut&requestURL=<%=request.getServletPath()%>">登出</a>
<%-- 										<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/member/memberServlet.do" > --%>
<%-- 											<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<!-- 											<input type="hidden" name="action" value="logOut"> -->
<!-- 											<input type="submit" value="登出"> -->
<!-- 										</FORM> -->
									</li>
								</ul>
							</div>
						</c:if>
					</div>
				</div>
				<div class="col-xs-12 col-sm-6">
				<c:if test="${account.member_no ==null }">
					<div class="slidename" style="margin-left: 300px">
						<span><img
							style="width: 15px; height: 15px; margin-bottom: 3px"
							src="<%=request.getContextPath()%>/front_end/images/img/people.png"></span>註冊
						<div class="slideCharacter" style="display: none">
							<ul>
								<li><a href="#registered1" data-toggle="modal">一般會員/註冊</a></li>
								<li><a href="#registered2" data-toggle="modal">廠商會員/註冊</a></li>
							</ul>
						</div>
					</div>
					</c:if>
					<c:if test = "${account!=null}">
						<div id="accountMemberName">
						<img style="width: 15px; height: 15px; margin-bottom: 3px"
							src="<%=request.getContextPath()%>/front_end/images/img/online.png">${account.member_name}</div>
					</c:if>
					<c:if test="${account.member_no ==null }">
				<div id="login">
					<img style="width: 15px; height: 15px; margin-bottom: 3px" src="<%=request.getContextPath()%>/front_end/images/img/people.png">登入
					<div class="slideLogin" style="display: none">
						<ul>
							<li><a href="<%=request.getContextPath()%>/login.jsp">會員登入</a></li>
							<li><a href="<%=request.getContextPath()%>/supplier_end/suplogin.jsp">廠商登入</a></li>
						</ul>
					</div>
				</div>
			</c:if>
				</div>
			</div>
			
		</div>
		<!-- 滾動導覽列結束 -->
        <!-- 導覽列項目 -->
		<div id="nav2">
		    <div class="container" id="topHeaderArea">
		    	<div class="row">
		    		<div class="col-xs-12 col-sm-6 col-sm-offset-3">
		    			<div class="col-xs-12 col-sm-2 col-sm-offset-2">
		    				 <div class="container">
		    				 	<div class="row">
		    				 		<a href="<%=request.getContextPath()%>/front_end/frontIndex.jsp"><img src="<%=request.getContextPath()%>/front_end/images/components/icon1.png"/></a>
		    				 	</div>
		    				 </div>
		    				 <div class="container">
		    				 	<div class="row">
		    				 		<h5 class="line"><a href="<%=request.getContextPath()%>/front_end/frontIndex.jsp">回首頁</a></h5>
		    				 	</div>
		    				 </div>
		    			</div>
		    			<div class="col-xs-12 col-sm-2">
		    				<div class="container">
		    				 	<div class="row">
		    				 		<a href="<%=request.getContextPath()%>/front_end/article/listAllArticle.jsp"><img src="<%=request.getContextPath()%>/front_end/images/components/icon2.png"/></a>
		    				 	</div>
		    				 </div>
		    				 <div class="container">
		    				 	<div class="row">
		    				 		<h5 class="line"><a href="<%=request.getContextPath()%>/front_end/article/listAllArticle.jsp">討論區</a></h5>
		    				 	</div>
		    				 </div>
		    			</div>
		    			<div class="col-xs-12 col-sm-2">
		    				<div class="container">
		    					<div class="row">
		    						<a href="<%=request.getContextPath()%>/front_end/place/place.jsp" ><img src="<%=request.getContextPath()%>/front_end/images/components/icon3.png" alt="Link" /></a>
		    					</div>
		    				</div>
		    				<div class="container">
		    					<div class="row">
		    						<h5 class="line"><a href="<%=request.getContextPath()%>/front_end/place/place.jsp">婚宴場地</a></h5>
		    					</div>
		    				</div>
		    			</div>
		    			<div class="col-xs-12 col-sm-2">
		    				<div class="container">
		    					<div class="row">
		    						<a href="<%=request.getContextPath()%>/front_end/shoppingcar/ShoppingPage.jsp"><img src="<%=request.getContextPath()%>/front_end/images/components/icon9.png" alt="Link" /></a>
		    					</div>
		    				</div>
		    				<div class="container">
		    					<div class="row">
		    						<h5 class="line"><a href="<%=request.getContextPath()%>/front_end/shoppingcar/ShoppingPage.jsp">購物商城</a></h5>
		    					</div>
		    				</div>
		    			</div>
		    			<div class="col-xs-12 col-sm-2">
		    				<div class="container">
		    					<div class="row" id="topHeaderGroupsIcon">
		    						<a href="<%=request.getContextPath()%>/front_end/groups/groupsIndex.jsp" ><img src="<%=request.getContextPath()%>/front_end/images/components/icon5.png" alt="Link" /></a>
		    					</div>
		    				</div>
		    				<div class="container">
		    					<div class="row" id="topHeaderGroups">
		    						<h5 class="line"><a href="<%=request.getContextPath()%>/front_end/groups/groupsIndex.jsp">婚友社群</a></h5>
		    					</div>
		    				</div>
		    			</div>
		    		</div>
		    	</div>
		    </div>
		</div>
        <!-- 導覽列項目結束 -->
	<!-- 一般會員註冊跳出頁面 -->
	<div class="modal fade" id="registered1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<img
							src="<%=request.getContextPath()%>/front_end/images/X_mark.png">
					</button>
					<h2 class="modal-title">會員資料輸入</h2>
				</div>
				<div class="modal-body place">
					<c:if test="${not empty errorMsgsFromInsert}">
						<div id="errorMsgsArea1">
							<font color='red'>請修正以下錯誤:
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li>${message}</li>
									</c:forEach>
								</ul>
							</font>
						</div>
					</c:if>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/member/memberServlet.do"
						name="form1" enctype="multipart/form-data" id="regist">
						<div class="col-xs-12 col-sm-6">
							<div class="input-group">
								<div class="input-group-addon">帳號</div>
								<input type="text" name="member_acc" id="member_acc"
									class="form-control"
									value="<%=(memberVO == null) ? "" : memberVO.getMember_acc()%>">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">密碼</div>
								<input type="password" name="member_pw" id="member_pw"
									class="form-control"
									value="<%=(memberVO == null) ? "" : memberVO.getMember_pw()%>">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">姓名</div>
								<input type="text" name="member_name" id="member_name"
									class="form-control"
									value="<%=(memberVO == null) ? "" : memberVO.getMember_name()%>">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">地址</div>
								<input type="text" name="member_addr" id="member_addr"
									class="form-control"
									value="<%=(memberVO == null) ? "" : memberVO.getMember_addr()%>">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">電子信箱</div>
								<input type="text" name="member_email" id="member_email"
									class="form-control"
									value="<%=(memberVO == null) ? "" : memberVO.getMember_email()%>" />
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">行動電話</div>
								<input type="number" name="member_mobile" id="member_mobile"
									class="form-control" maxlength="10"
									value="<%=(memberVO == null) ? "" : memberVO.getMember_mobile()%>">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">出生日期</div>
								<input type="date" name="member_birthday" id="member_birthday"
									class="form-control">
							</div>
							<br>
							<%
								java.sql.Date date_SQL2 = new java.sql.Date(System.currentTimeMillis());
							%>
							<input type="hidden" name="enroll_time" id="enroll_time"
								class="form-control" value="<%=date_SQL2%>">

						</div>

						<div class="col-xs-12 col-sm-6">

							<div>
								<label for="member_sex">性別:</label><br> <label
									class="radio-inline"> <input type="radio"
									name="member_sex" value="1"
									<%=(memberVO == null || memberVO.getMember_sex().equals(1)) ? "checked" : ""%>>男性
								</label> <label class="radio-inline"> <input type="radio"
									name="member_sex" value="0"
									<%=(memberVO == null || memberVO.getMember_sex().equals(1)) ? "" : "checked"%>>女性
								</label>
							</div>
							<br>
							<div>
								<img id="image" style="width: 200px; height: 200px"> 
								<input
									type="file" id="theFile" name="member_pic" size="45" />
							</div>
						</div>
				</div>
				<div class="modal-footer">
					<input type="hidden" name="action" value="insert"> <input
						type="hidden" name="requestURL"
						value="<%=request.getServletPath()%>">
					<button type="button" class="btn btn-success" data-toggle="modal"
						onclick="submit();">送出</button>
					<script>
						function submit() {
							$("#regist").submit();
						}
					</script>
				</div>
				</FORM>
			</div>
		</div>
	</div>

	<!-- 廠商會員註冊跳出頁面 -->
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/sup/sup.do"
		name="form1" enctype="multipart/form-data" id="regist2">
		<div class="modal fade" id="registered2">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<img
								src="<%=request.getContextPath()%>/front_end/images/X_mark.png">
						</button>
						<h2 class="modal-title">廠商資料輸入</h2>
					</div>
					<div class="modal-body place">
						<div class="col-xs-12 col-sm-6">
							<div class="input-group">
								<div class="input-group-addon">廠商帳號</div>
								<input type="text" name="sup_acct" id="sup_acct"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">廠商密碼</div>
								<input type="password" name="sup_pwd" id="sup_pwd"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">廠商名稱</div>
								<input type="text" name="sup_name" id="sup_name"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">郵遞區號</div>
								<input type="number" name="sup_adcode" id="sup_adcode"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">廠商地址</div>
								<input type="text" name="sup_addr" id="sup_addr"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">接洽窗口</div>
								<input type="text" name="sup_con" id="sup_con"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">廠商統編</div>
								<input type="number" name="sup_id" id="sup_id"
									class="form-control">
							</div>
						</div>

						<div class="col-xs-12 col-sm-6">

							<div class="input-group">
								<div class="input-group-addon">電話區碼</div>
								<input type="number" name="sup_telcode" id="sup_telcode"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">公司電話</div>
								<input type="number" name="sup_tel" id="sup_tel"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">傳真電話</div>
								<input type="number" name="sup_tax" id="sup_tax"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">廠商類別</div>
								<select name="sup_tape" id="sup_tape" class="form-control">
									<option value="C">周邊物品</option>
									<option value="H">會館</option>
								</select>
							</div>
							<br> <input type="hidden" name="sup_note" id="sup_note"
								class="form-control" value="0">
						</div>
					</div>
					<div class="modal-footer">
						<input type="hidden" name="action" value="insert">
						<button type="button" class="btn btn-success" data-toggle="modal"
							onclick="submit();">送出</button>
						<script>
							function submit() {
								$("#regist2").submit();
							}
						</script>
					</div>
				</div>
			</div>
		</div>

	</FORM>

<!-- 修改會員資料開始 -->
<div class="modal fade" id="updateMember">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">
				<img src="<%=request.getContextPath()%>/front_end/images/X_mark.png">
			</button>
			<h2 class="modal-title">修改會員資料</h2>
			</div>
		<div class="modal-body place">
		<c:if test="${not empty errorMsgsFromUpdate}">
			<div id="errorMsgsArea3">
				<font color='red'>請修正以下錯誤:
					<ul>
						<c:forEach var="message" items="${errorMsgsFromUpdate}">
							<li>${message}</li>
						</c:forEach>
					</ul>
				</font>
			</div>
		</c:if>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/memberServlet.do" name="form1" enctype="multipart/form-data" id="updateMemberData">
			<div class="col-xs-12 col-sm-6">
				<div class="input-group">
					<div class="input-group-addon">
						密碼
					</div>
					<input type="password" name="member_pw" id="member_pw" class="form-control" value="${account.member_pw}">							
				</div>						
				<br>
				<div class="input-group">
					<div class="input-group-addon">
						地址
					</div>
					<input type="text" name="member_addr" id="member_addr" class="form-control" value="${account.member_addr}">							
				</div>	
				<br>
				<div class="input-group">
					<div class="input-group-addon">
						行動電話
					</div>
					<input type="number" name="member_mobile" id="member_mobile" class="form-control" maxlength="10" value="${account.member_mobile}" >							
				</div>	
				<br>
			</div>
		
			<div class="col-xs-12 col-sm-6">
				 <div>
				 <img src="<%= request.getContextPath() %>/member/ShowMember_Pic.do?member_no=${account.member_no}" width="20%"/>
				 <img id="image2" style="width: 200px; height: 200px">	
				 <input type="file" id="theFile2" name="member_pic" size="45"/>	
				 </div>				 
			</div>
		</div>
			<input type="hidden" name="member_no" value="${account.member_no}">
			<input type="hidden" name="member_acc" value="${account.member_acc}">
			<input type="hidden" name="member_name" value="${account.member_name}">
			<input type="hidden" name="member_email" value="${account.member_email}">
			<input type="hidden" name="member_birthday" value="${account.member_birthday}">
			<input type="hidden" name="enroll_time" value="${account.enroll_time}">
			<input type="hidden" name="member_sex" value="${account.member_sex}">
		    <input type="hidden" name="action" value="update">
		    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			<button type="button" class="btn btn-success"  data-toggle="modal" onclick="submit();">送出</button>
			<script> function submit(){$("#updateMemberData").submit();}</script>
		</FORM>
		</div>
	</div>
</div>
<!-- 修改會員資料結束 -->
	<div id="websocketArea">
		<%
			if (session.getAttribute("account") != null) {
		%>
		<jsp:include page="/front_end/groups/inviteView.jsp" />
		<%
			}
		%>
	</div>
	</FORM>
        
		<div id="intro"><!--intro -->
		<!-- 這邊是大家頁面內容 -->
 			<div id="bgpanel">
	 			<c:if test="${not empty purerrorMsgs}">
					<font color='red'>
						<ul>
							<c:forEach var="message" items="${purerrorMsgs}">
								<li style="list-style-type:none;">${message}</li>
							</c:forEach>
						</ul>
					</font>
				</c:if>
			<div id="case_form" class="caseSeparate">
 <%
   String amount = (String) session.getAttribute("amount");

   MemberVO memVO = (MemberVO)session.getAttribute("account");
   
   Vector<OrdVO> buylist = (Vector<OrdVO>)session.getAttribute("shoppingcart");
   pageContext.setAttribute("buylist",buylist); 
%>
						<div id="transmitinfo" style="padding:80px;margin-top:-60px;margin-left:150px">
							<h4 style="margin-left:100px;font-weight:bold">請輸入寄送資訊</h4>
							<form METHOD="post"  ACTION="<%=request.getContextPath()%>/purord/purord.do">
								<dl>
									<dt><label for="name">收件人<font color="red"><b>*</b></font>:</label></dt>
									<dd><input type="text"  id="name" name="pur_name" placeholder="請輸入收件人姓名"  value="<%=memVO.getMember_name()%>"><span class='error1'></span></dd>
								</dl>
								<dl>
									<dt><label for="phone">手機<font color="red"><b>*</b></font>:</label></dt>
									<dd><input type="text"  id="phone" name="pur_tel" placeholder="請輸入手機"  value="<%=memVO.getMember_mobile()%>"><span class='error2'></span></dd>
								</dl>
								
								<dl>
									<dt><label for="address">地址<font color="red"><b>*</b></font>:</label></dt>
									<dd><input type="text"  id="address" name="pur_add" placeholder="請輸入地址"  value="<%=memVO.getMember_addr()%>"><span class='error3'></span></dd>
								</dl>
								<dl>
									<dt><label for="meno">備註</label></dt>
									<dd><textarea id="pur_memo" name="pur_memo" placeholder=""  value="Be carefully"></textarea></dd>
								</dl>
									<button id="checkout" type="submit" style="margin-top:-10px;margin-left:60px">結帳</button>
									<input type="hidden"  name="member_no"  value="<%=memVO.getMember_no()%>">
									<input type="hidden"  name="member_email"  value="<%=memVO.getMember_email()%>">
									<input type="hidden"  name="pur_date"  value="<fmt:formatDate pattern="yyyy-MM-dd" value="${dateObject}" />">
									<input type="hidden"  name="pur_money"  value="<fmt:formatDate pattern="yyyy-MM-dd" value="${dateObject}" />">
									<input type="hidden"  name="pur_product"  value="<fmt:formatDate pattern="yyyy-MM-dd" value="${dateObject}" />">
									<input type="hidden"  name="pur_close"  value="<fmt:formatDate pattern="yyyy-MM-dd" value="${dateObject}" />">
							        <input type="hidden" name="pur_status"  value=1>
									<input type="hidden"  name="pur_sum"  value="<%=amount%>">
									<c:forEach var="buyitem" items="${buylist}">
										<input type="hidden"  name="com_no" value="${buyitem.com_no}">
										<input type="hidden"  name="ord_price" value="${buyitem.ord_price*listitem.ord_qty}">
										<input type="hidden"  name="ord_qty" value="${buyitem.ord_qty}">
										<input type="hidden"  name="return_qty" value="0">
										<input type="hidden"  name="ship_status" value="1">
									</c:forEach>
									<input type="hidden" name="action" value="insert2">
							</form>
						</div> <!-- transmitinfo end -->
					</div>	
 			</div><!--bgpanel end -->
			<!-- 這邊是大家頁面內容結束 -->
		</div> <!--#intro end-->
	</body>
<script>
$(document).ready(function() {
	$("#insertMemberButton").click(function(){
			$("#errorMsgsArea1").remove();
	});
	if($("#errorMsgsArea1").val() !=null ){
		$("#registered1").modal("show");
	};
	
	$("#updateMemberSelect").click(function(){
		$("#errorMsgsArea3").remove();
		$("#updateMember").modal("show");
	})
	if($("#errorMsgsArea3").val() !=null ){
		$("#updateMember").modal("show");
	};
});
</script>
	
</html>

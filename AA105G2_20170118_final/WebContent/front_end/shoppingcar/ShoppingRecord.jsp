<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.commodity.model.*"%>
<%@ page import="com.purchase_order.model.*"%>
<%@ page import="com.order_item.model.*"%>
<%@page import="com.member.model.*"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("/front_end/frontIndex.jsp");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%      
     PurService purSvc = new PurService();
     List<PurVO> list = purSvc.getAll();
     pageContext.setAttribute("list", list);
%>
<jsp:useBean id="ordSvc" scope="page" class="com.order_item.model.OrdService" />
<jsp:useBean id="comSvc" scope="page" class="com.commodity.model.ComService" />

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
		<title>訂單內容</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/ShoppingCart.css">

		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
        <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/jquery-3.1.1.min.js"></script>
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
	
	<style>
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
<%
  SimpleDateFormat sf = new SimpleDateFormat();
  pageContext.setAttribute("sf",sf);
%>
 			<div id="bgpanel" style="overflow:scroll;">
		 			  <div id="showpastlist" style="float:right;padding:50px;margin-right:50px;margin-top:-40px;">
		 			    <h4 style="margin-left:350px;font-weight:bold">歷史訂單</h4>  
					 			<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue">						
									<tr bgcolor="#999999">
										<th>訂單編號</th>
										<th>訂單日期</th>
										<th>收款日期</th>				
										<th>訂單金額</th>
										<th>收件人姓名</th>
										<th>收件人地址</th>	
										<th>訂單明細</th>					
									</tr>
									<c:forEach var="purVO" items="${list}" varStatus="s">
						                <c:if test="${purVO.member_no==account.member_no}">
							          		<tr> 
												<td width="100"><div align="center"><b>${purVO.pur_no}</b></div></td> 
												<td width="100"><div align="center"><b>${sf.format(purVO.pur_date)}</b></div></td>
												<td width="100"><div align="center"><b>${sf.format(purVO.pur_money)}</b></div></td>
												<td width="100"><div align="center"><b>$${purVO.pur_sum}</b></div></td>
												<td width="100"><div align="center"><b>${purVO.pur_name}</b></div></td>
												<td width="300"><div align="center"><b>${purVO.pur_add}</b></div></td>	
												<td width="100">
													<div align="center">
														<button class="showinfo">
															<a href="#${purVO.pur_no}" data-toggle="modal" class="btn btn-info">檢視</a>
														</button>
													</div>
												</td>
												<td>
												<!-- 顯示訂單明細開始 -->
													<div class="modal fade" id="${purVO.pur_no}">
														<div class="modal-dialog" id="">
															<div class="modal-content" id="">
																<table class="table table-hover table-striped table-bordered table-condensed" color="lightblue"  style="width:100%">
								                                    <tr>
																		<th>訂單編號 </th>
																		<th>商品名稱</th>
																		<th>金額</th>
																		<th>訂購數量(組)</th>
																		<th>退貨數量(組)</th>
																		<th>狀態</th>
																	 </tr>
								                                     <c:forEach var="ordlist" items="${ordSvc.all}">
								                                       <c:if test="${ordlist.pur_no==purVO.pur_no}">
																			<tr align='center' valign='middle'>
																		        <td>${ordlist.pur_no}</td>
																				<td>${comSvc.getOneCom(ordlist.com_no).com_name}</td>
																				<td>${ordlist.ord_price}</td>
																				<td>${ordlist.ord_qty}</td>
																				<td>${ordlist.return_qty}</td>
																				<td>
																				   <c:if test="${ordlist.ship_status==0}">已出貨</c:if>
																				   <c:if test="${ordlist.ship_status==1}">未出貨</c:if>
																				</td>
																			</tr>
																		</c:if>
																	</c:forEach>
																</table>
															</div>
														</div>
													</div><!-- 顯示訂單明細結束 -->	
												</td>												
											</tr>
										</c:if>
									</c:forEach> 							  
								</table> 
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

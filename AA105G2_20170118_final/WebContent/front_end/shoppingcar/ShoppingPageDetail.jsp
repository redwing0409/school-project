<%@page import="com.order_item.model.OrdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.commodity.model.*"%>
<%@page import="com.member.model.*"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("/front_end/frontIndex.jsp");
%>

<%
  ComVO comVO = (ComVO) request.getAttribute("comVO"); //ComServlet.java(Concroller), �s�Jreq��empVO����
  
%>
<%
  pageContext.setAttribute("location",request.getRequestURI());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>�ӫ~���e</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/ShoppingPageDetail.css">

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
        <!-- �u�ʾ����C -->
     	<div class="container">
			<div class="row">
			 	<div class="col-xs-12 col-sm-6">
					<div class="slidemenu" style="margin-left: 5px">
						<c:if test="${account.member_no !=null }">
							<div class="slidemain">
								<span><img
									style="width: 15px; height: 15px; margin-bottom: 3px"
									src="<%=request.getContextPath()%>/front_end/images/img/setting.png"></span>�ڪ��޲z
							</div>
							<div class="slidesub" style="display: none">
								<ul>
									<li>
										<a href="<%= request.getContextPath() %>/front_end/updateMemberInfo.jsp" id="updateMemberSelect">�ק�򥻸��</a>
									</li>
									<li><a href="<%= request.getContextPath() %>/front_end/friend/listFriendByMember.jsp">�޲z�n��</a></li>
									<li><a href="<%= request.getContextPath() %>/front_end/Appointment/listOwnAppointment.jsp">�s���w���M��</a></li>
							<li><a href="<%= request.getContextPath() %>/front_end/shoppingcar/ShoppingRecord.jsp">�s���ʪ��M��</a></li>
									<li>
									<a href="<%= request.getContextPath() %>/member/memberServlet.do?action=logOut&requestURL=<%=request.getServletPath()%>">�n�X</a>
<%-- 										<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/member/memberServlet.do" > --%>
<%-- 											<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<!-- 											<input type="hidden" name="action" value="logOut"> -->
<!-- 											<input type="submit" value="�n�X"> -->
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
							src="<%=request.getContextPath()%>/front_end/images/img/people.png"></span>���U
						<div class="slideCharacter" style="display: none">
							<ul>
								<li><a href="#registered1" data-toggle="modal">�@��|��/���U</a></li>
								<li><a href="#registered2" data-toggle="modal">�t�ӷ|��/���U</a></li>
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
					<img style="width: 15px; height: 15px; margin-bottom: 3px" src="<%=request.getContextPath()%>/front_end/images/img/people.png">�n�J
					<div class="slideLogin" style="display: none">
						<ul>
							<li><a href="<%=request.getContextPath()%>/login.jsp">�|���n�J</a></li>
							<li><a href="<%=request.getContextPath()%>/supplier_end/suplogin.jsp">�t�ӵn�J</a></li>
						</ul>
					</div>
				</div>
			</c:if>
				</div>
			</div>
			
		</div>
		<!-- �u�ʾ����C���� -->
        <!-- �����C���� -->
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
		    				 		<h5 class="line"><a href="<%=request.getContextPath()%>/front_end/frontIndex.jsp">�^����</a></h5>
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
		    				 		<h5 class="line"><a href="<%=request.getContextPath()%>/front_end/article/listAllArticle.jsp">�Q�װ�</a></h5>
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
		    						<h5 class="line"><a href="<%=request.getContextPath()%>/front_end/place/place.jsp">�B�b���a</a></h5>
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
		    						<h5 class="line"><a href="<%=request.getContextPath()%>/front_end/shoppingcar/ShoppingPage.jsp">�ʪ��ӫ�</a></h5>
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
		    						<h5 class="line"><a href="<%=request.getContextPath()%>/front_end/groups/groupsIndex.jsp">�B�ͪ��s</a></h5>
		    					</div>
		    				</div>
		    			</div>
		    		</div>
		    	</div>
		    </div>
		</div>
        <!-- �����C���ص��� -->
	<!-- �@��|�����U���X���� -->
	<div class="modal fade" id="registered1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<img
							src="<%=request.getContextPath()%>/front_end/images/X_mark.png">
					</button>
					<h2 class="modal-title">�|����ƿ�J</h2>
				</div>
				<div class="modal-body place">
					<c:if test="${not empty errorMsgsFromInsert}">
						<div id="errorMsgsArea1">
							<font color='red'>�Эץ��H�U���~:
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
								<div class="input-group-addon">�b��</div>
								<input type="text" name="member_acc" id="member_acc"
									class="form-control"
									value="<%=(memberVO == null) ? "" : memberVO.getMember_acc()%>">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">�K�X</div>
								<input type="password" name="member_pw" id="member_pw"
									class="form-control"
									value="<%=(memberVO == null) ? "" : memberVO.getMember_pw()%>">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">�m�W</div>
								<input type="text" name="member_name" id="member_name"
									class="form-control"
									value="<%=(memberVO == null) ? "" : memberVO.getMember_name()%>">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">�a�}</div>
								<input type="text" name="member_addr" id="member_addr"
									class="form-control"
									value="<%=(memberVO == null) ? "" : memberVO.getMember_addr()%>">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">�q�l�H�c</div>
								<input type="text" name="member_email" id="member_email"
									class="form-control"
									value="<%=(memberVO == null) ? "" : memberVO.getMember_email()%>" />
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">��ʹq��</div>
								<input type="number" name="member_mobile" id="member_mobile"
									class="form-control" maxlength="10"
									value="<%=(memberVO == null) ? "" : memberVO.getMember_mobile()%>">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">�X�ͤ��</div>
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
								<label for="member_sex">�ʧO:</label><br> <label
									class="radio-inline"> <input type="radio"
									name="member_sex" value="1"
									<%=(memberVO == null || memberVO.getMember_sex().equals(1)) ? "checked" : ""%>>�k��
								</label> <label class="radio-inline"> <input type="radio"
									name="member_sex" value="0"
									<%=(memberVO == null || memberVO.getMember_sex().equals(1)) ? "" : "checked"%>>�k��
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
						onclick="submit();">�e�X</button>
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

	<!-- �t�ӷ|�����U���X���� -->
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
						<h2 class="modal-title">�t�Ӹ�ƿ�J</h2>
					</div>
					<div class="modal-body place">
						<div class="col-xs-12 col-sm-6">
							<div class="input-group">
								<div class="input-group-addon">�t�ӱb��</div>
								<input type="text" name="sup_acct" id="sup_acct"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">�t�ӱK�X</div>
								<input type="password" name="sup_pwd" id="sup_pwd"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">�t�ӦW��</div>
								<input type="text" name="sup_name" id="sup_name"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">�l���ϸ�</div>
								<input type="number" name="sup_adcode" id="sup_adcode"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">�t�Ӧa�}</div>
								<input type="text" name="sup_addr" id="sup_addr"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">�������f</div>
								<input type="text" name="sup_con" id="sup_con"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">�t�Ӳνs</div>
								<input type="number" name="sup_id" id="sup_id"
									class="form-control">
							</div>
						</div>

						<div class="col-xs-12 col-sm-6">

							<div class="input-group">
								<div class="input-group-addon">�q�ܰϽX</div>
								<input type="number" name="sup_telcode" id="sup_telcode"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">���q�q��</div>
								<input type="number" name="sup_tel" id="sup_tel"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">�ǯu�q��</div>
								<input type="number" name="sup_tax" id="sup_tax"
									class="form-control">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-addon">�t�����O</div>
								<select name="sup_tape" id="sup_tape" class="form-control">
									<option value="C">�P�䪫�~</option>
									<option value="H">�|�]</option>
								</select>
							</div>
							<br> <input type="hidden" name="sup_note" id="sup_note"
								class="form-control" value="0">
						</div>
					</div>
					<div class="modal-footer">
						<input type="hidden" name="action" value="insert">
						<button type="button" class="btn btn-success" data-toggle="modal"
							onclick="submit();">�e�X</button>
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

<!-- �ק�|����ƶ}�l -->
<div class="modal fade" id="updateMember">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">
				<img src="<%=request.getContextPath()%>/front_end/images/X_mark.png">
			</button>
			<h2 class="modal-title">�ק�|�����</h2>
			</div>
		<div class="modal-body place">
		<c:if test="${not empty errorMsgsFromUpdate}">
			<div id="errorMsgsArea3">
				<font color='red'>�Эץ��H�U���~:
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
						�K�X
					</div>
					<input type="password" name="member_pw" id="member_pw" class="form-control" value="${account.member_pw}">							
				</div>						
				<br>
				<div class="input-group">
					<div class="input-group-addon">
						�a�}
					</div>
					<input type="text" name="member_addr" id="member_addr" class="form-control" value="${account.member_addr}">							
				</div>	
				<br>
				<div class="input-group">
					<div class="input-group-addon">
						��ʹq��
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
			<button type="button" class="btn btn-success"  data-toggle="modal" onclick="submit();">�e�X</button>
			<script> function submit(){$("#updateMemberData").submit();}</script>
		</FORM>
		</div>
	</div>
</div>
<!-- �ק�|����Ƶ��� -->
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
        
		<div id="intro"><!--#intro -->
		<!-- �o��O�j�a�������e -->
 			<div id="bgpanel">
						<div class="col-xs-12 col-sm-2">
			        		         <!--�ӫ~�j�M-->
			             <div class="row">
			                   <form class="navbar-form" role="search" ACTION="<%=request.getContextPath()%>/com/com.do" method="post">
					                   <div class="form-group">
					                         <input type="text" class="form-control pull-left" name="com_name" style="width:100%" placeholder="�j�M�ӫ~">
					                   </div>
					                   	 <button type="submit" class="btn btn-default pull-left "  style="margin-top:5%">�j�M</button>
		        					     <input type="hidden" name="action" value="listCom_ByCompositeQuery_front">
			                 </form>
			        <!-- �ӫ~�j�M���� -->
			        </div> <!-- row end-->
			         <div class="row">
			                     <div id="car_pic" style="margin-left:15%">
			                                      <a href="<%=request.getContextPath()%>/front_end/shoppingcar/ShoppingCart.jsp"><img src="<%=request.getContextPath()%>/front_end/images/img/buycar.jpg"   style="width:80%"></a><br>
  <%   
    int amount = 0;
    int totalQuantity = 0;
    Vector<OrdVO> buylist =new Vector<OrdVO>();
    if (session.getAttribute("shoppingcart")!=null) {   
		 buylist = (Vector<OrdVO>)session.getAttribute("shoppingcart");
     }   
         for(OrdVO buyitem : buylist){
    	   totalQuantity += buyitem.getOrd_qty();
    	   amount += (buyitem.getOrd_qty())*(buyitem.getOrd_price());
         }
        pageContext.setAttribute("amount",amount);
        pageContext.setAttribute("totalQuantity",totalQuantity);
   %>
			                            <span>�`���B:$<font color="red"><b>${amount} </b></font>��</span><br>
			                            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�@:<font color="red"><b>${totalQuantity} </b></font>��</span>
			                       </div>
			          </div><!-- row end-->
	     </div><!-- col-xs-12 col-sm-2 end-->
		        <!--�k��ӫ~�Ӹ`-->
		        <div class="col-xs-12 col-sm-9">
		        	<div class="divider"></div>
		                <div class="container-fluid">
		        	         <div class="row">
		        			    <div class="thumbnail"  style="width:70%;height:50%">
		        			        <div id="row">
		        			            <div>
					        			    <a class="showImage">
										       	<div class="col-xs-12 col-sm-9"><img src="<%=request.getContextPath()%>/DBGif.do?name=${comVO.com_no}" style="width:260px;height:240px">
										       </div>
										    </a>
									    </div>
									    <div class="caption">
								        	<p style="font-weight:bold">${comVO.com_name}</p>
							        		<div id="buyprice" ><strong>$${comVO.com_price}</strong></div>
							        			<form action="<%=request.getContextPath()%>/ord/shopord.do" method="post">
													<div class="row"> 
													      <div class="form-group">
													        <div class="col-xs-12 col-sm-4" style="margin-top:1%">
													            <label for="style" class="control-label"><h5>�ƶq</h5></label>
													        </div>
													        <div class="col-xs-12 col-sm-8" style="margin-top:1%">
														        <select name="ord_qty" class="selectpicker form-control" style="width:91%">
														            <option value="1">1</option>
														            <option value="2">2</option>
														            <option value="3">3</option> 
														        </select>
														     </div>
													</div>        
													<div class="row"> 
														     <div class="col-xs-12 col-sm-7" style="margin-top:2%" style="margin-left:10%">
									            	            <button type="submit" class="btn btn-default">�ڭn����</button>
									            	            <input type="hidden" name="com_no" value="${comVO.com_no}"> 
									            	            <input type="hidden" name="ord_price" value="${comVO.com_price}">
									            	            <input type="hidden" name="location" value="<%=pageContext.getAttribute("location")%>">
									            	            <input type="hidden" name="whichPage" value="1">
									            	            <input type="hidden" name="action" value="ADD">  
									                        </div>    
													 </div>
												   </div> 								              	                 
											</form>
							      		</div>
							        </div>
								</div>
		        			</div>
		        			
		        			<div class="row">
			        			<div class="col-xs-12 col-sm-9">
			        				<ul id="myTab" class="nav nav-tabs" style="width:95%;margin-top:-1%">
										<li class="active"><a href="#descript" data-toggle="tab">
											�ӫ~�y�z</a></li>
										<li><a href="#notes" data-toggle="tab">�`�N�ƶ�</a></li>
								    </ul>
								    <div id="myTabContent" class="tab-content">
										<div class="tab-pane fade in active" id="descript">
											<p>
												${comVO.com_desc}
											</p>
										</div>
										<div class="tab-pane fade" id="notes">
											<p>
											    ${comVO.com_note}
											</p>
										</div>
			        				</div>
			        			</div><!-- col-xs-12 col-sm-9 end -->
		        			</div>
		        		</div><!-- container-fluid end -->
		       		</div><!-- col-xs-12 col-sm-9 end -->
        			<!--�k��ӫ~�Ӹ`����-->
	      	
				<!-- �o��O�j�a�������e���� -->
			</div><!--bgpanel end-->
		</div> <!--intro end-->
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

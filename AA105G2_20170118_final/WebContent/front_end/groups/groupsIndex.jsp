<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.member.model.*"%>
<%@page import="com.groups.model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>婚友社群</title>
</head>
<style>
#gropusImage {
	table-layout: fixed;
	word-wrap: break-word;
 	height: 140px;  
 	width: 205px; 
	overflow: hidden;
}
.thumbnail{
	margin-top:10px;
	height:200px;
}
</style>
<body>
	
	<jsp:include page="/front_end/groups/groupsFile.jsp" />
	
	<jsp:useBean id="groupsSvc" scope="page" class="com.groups.model.GroupsService" />
	<jsp:useBean id="groups_listSvc" scope="page" class="com.groups_list.model.Groups_listService" />
	      
	<div id="intro"><!--#intro -->
	<!-- 這邊是大家頁面內容 -->
		<div id="bgpanel">
			
			<div class="groups_area">
			<div class="title">
			你管理的社團
			<div class="floatRight" >
				<a href='#createGroups' data-toggle="modal" id="createGroupsButton">+</a>
			</div>
			</div>
		         <c:forEach var="groupsVO" items="${groupsSvc.allFront}" > 
			        	 <c:if test="${account.member_no==groupsVO.groups_owner}">
				        	 <FORM class="select_form" METHOD="post" ACTION="<%= request.getContextPath() %>/bulletin/bulletinServlet.do" >
								<div class="col-xs-12 col-sm-3 groups_select" href='#' data-toggle="modal" 
								data-groupsno="${groupsVO.groups_no}" data-requesturl="<%=request.getServletPath()%>" data-action="getOne_For_Display">
									<div class="thumbnail">
										<div id="gropusImage">
											<img src="<%= request.getContextPath() %>/groups/ShowGroupsPhoto.do?groups_no=${groupsVO.groups_no}" class="img-responsive groupsImg"/>
										</div>
										<div class="groupsTitle" style="font-size:70%;">
											<h5 class="text-center"><strong>${groupsVO.groups_title}</strong></h5>
										</div>
									</div>
								</div>
								<input type="hidden" name="groups_no" value="${groupsVO.groups_no}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							    <input type="hidden" name="action" value="getOne_For_Display">
							 </FORM>
			         	 </c:if>
		         </c:forEach>
		       </div>
			<div class="groups_area">
			<div class="title">
			你參加的社團
			</div>
		         <c:forEach var="groups_listVO" items="${groups_listSvc.all}" > 
		        	 <c:if test="${account.member_no==groups_listVO.member_no}">
		        	 	 <c:forEach var="groupsVO" items="${groupsSvc.allFront}" >
			        	 	 <c:if test="${groups_listVO.groups_no==groupsVO.groups_no}">
					        	 <FORM class="select_form" METHOD="post" ACTION="<%= request.getContextPath() %>/bulletin/bulletinServlet.do" >
									<div class="col-xs-12 col-sm-3 groups_select">
										<div class="thumbnail">
											<div id="gropusImage">
												<img src="<%= request.getContextPath() %>/groups/ShowGroupsPhoto.do?groups_no=${groupsVO.groups_no}" class="img-responsive groupsImg"/>
											</div>
											<div class="caption" style="font-size:70%;">
												<h5 class="text-center"><strong>${groupsVO.groups_title}</strong></h5>
											</div>
										</div>
									</div>
									<input type="hidden" name="groups_no" value="${groupsVO.groups_no}">
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								    <input type="hidden" name="action" value="getOne_For_Display">
								 </FORM>
							 </c:if>
						 </c:forEach>
		         	 </c:if>
		         </c:forEach>
		    </div>
		</div>	<!--#bgpanel end-->
	<!-- 這邊是大家頁面內容結束 -->
	</div> <!--#intro end-->
	
	
<!-- 	<div id="websocketArea"> -->
<%-- 	<%if (session.getAttribute("account")!=null){%> --%>
<%-- 		    <jsp:include page="/front_end/groups/inviteView.jsp"/> --%>
<%-- 	<%} %> --%>
<!-- 	</div> -->
	<%
		GroupsVO groupsVO = (GroupsVO) request.getAttribute("addGroups");
	%>
	<div class="modal fade" id="createGroups">
		<div class="modal-dialog" id="showCreateGroupsAreaPosition">
			<div class="modal-content" id="test2">
				<div class="modal-header" id="createGroupsHeader">
					建立新社群
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body" id="createGroupsContent">
					<c:if test="${not empty errorMsgs}">
						<div id="errorMsgsArea">
							<font color='red'>請修正以下錯誤:
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li>${message}</li>
									</c:forEach>
								</ul>
							</font>
						</div>
					</c:if>
					<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/groups/groupsServlet.do" name="form1">
						<label for="groupsTitle">替你的社群取個名字</label>
						<input type="text" name="groups_title" id="groupsTitle" class="form-control"
						 size="45" value="<%= (groupsVO==null)? "" : groupsVO.getGroups_title()%>" />
							
			            <input type="hidden" name="groups_owner" value="${account.member_no}">
			            <%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
						<input type="hidden" name="groups_time" value="<%= date_SQL%>">
						<input type="hidden" name="action" value="insert">
						<input type="submit" value="建立" id="createButton">
					</FORM>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
$(document).ready(function() {
});
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/groups/js/groupsIndex.js"></script>
</html>
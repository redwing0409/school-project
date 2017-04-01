<%@page import="com.member.model.*"%>
<%@page import="com.groups.model.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  GroupsVO groupsVO = (GroupsVO) request.getAttribute("listOneGroups_Front"); //GroupsServlet.java(Concroller), 存入的GroupsVO物件
  request.setAttribute("groupsVO",groupsVO);
  MemberService memberSvc = new MemberService();
  MemberVO memberVO = memberSvc.getOneMember(groupsVO.getGroups_owner());
%>
<html>
<head>
<title>社群資料 - ListOneGroups_Front.jsp</title>
</head>

<body>

<jsp:include page="/front_end/groups/groupsFile.jsp" />

<div id="intro"><!--#intro -->
<!-- 這邊是大家頁面內容 -->
	<div id="bgpanel">
		<div role="tabpanel">
		    <!-- 標籤面板：標籤區 -->
		    <jsp:useBean id="groupsSvc" scope="page" class="com.groups.model.GroupsService" />
		    	<div id="groupsInfo" data-groupno="${groupsVO.groups_no}">
					<%= groupsVO.getGroups_title()%> - <%= memberVO.getMember_name()%>
					<c:if test="${account.member_no==groupsVO.groups_owner}">
						<div id="deleteGroupsButton" class="floatRight">
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groups/groupsServlet.do">
							    <input type="button" class="btn btn-info" value="刪除">
							    <input type="hidden" name="groups_no" value="<%= groupsVO.getGroups_no()%>">
							    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							    <input type="hidden" name="action"	value="delete">
						    </FORM>
					    </div>
					    <div id="modifyGroupsButton" class="floatRight">
 <a href="#modifyGroupsTitleArea" data-toggle="modal" class="btn btn-info" id="modifyGroupsTitleButton" style="font-size:10px;font-weight: bold">修改</a>
						</div>
					</c:if>
		    	</div>
		    	<div id="groupSelectArea">
			    	<div id="bulletinSelectButton">
			            <FORM id="bulletinForm" METHOD="post" ACTION="<%= request.getContextPath() %>/bulletin/bulletinServlet.do">
						    <input type="submit" class="btn btn-info floatLeft" value="塗鴉牆"> 
						    <input type="hidden" name="groups_no" value="${groupsVO.groups_no}">
						    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						    <input type="hidden" name="action" value="getOne_For_Display">
						</FORM>
					</div>
				    <div id="photoSelectButton">
			            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/photo/photoServlet.do">
						    <input type="submit" class="btn btn-info floatLeft" value="照片"> 
						    <input type="hidden" name="groups_no" value="${groupsVO.groups_no}">
						    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						    <input type="hidden" name="action" value="getOne_For_Display">
						</FORM>
				    </div>
				    <div id="memberSelectButton">
			            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groups/groupsServlet.do">
						    <input type="submit" class="btn btn-info floatLeft" value="成員"> 
						    <input type="hidden" name="groups_no" value="<%= groupsVO.getGroups_no()%>">
						    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						    <input type="hidden" name="action" value="listMembers_ByGroups_no">
						</FORM>
			        </div>
			        <c:if test="${account.member_no==groupsVO.groups_owner}">
			        	<div id="showInviteSelect" class="floatRight"></div>
			        </c:if>
			    </div>
			
		    <!-- 標籤面板：內容區 -->
			    <div id="groupContentArea">
					<%if (request.getAttribute("listBulletinByGroups")!=null){%>
					       <jsp:include page="/front_end/bulletin/listBulletinByGroups.jsp" />
					<%} %>
					
					<%if (request.getAttribute("listPhotoByGroups")!=null){%>
					       <jsp:include page="/front_end/photo/listPhotoByGroups.jsp" />
					<%} %>
					
					<%if (request.getAttribute("listOneByGroupsNo")!=null){%>
					       <jsp:include page="/front_end/groups_list/listOneByGroupsNo.jsp" />
					<%} %>
			    </div>
		</div>
	</div>	<!--#bgpanel end-->
			
	<!-- 這邊是大家頁面內容結束 -->
</div> <!--#intro end-->

<!-- <div id="websocketArea"> -->
<%-- 	<%if (session.getAttribute("account")!=null){%> --%>
<%-- 		    <jsp:include page="/front_end/groups/inviteView.jsp"/> --%>
<%-- 	<%} %> --%>
<!-- </div> -->
<!-- 修改社群開始 -->
<div class="modal fade" id="modifyGroupsTitleArea">
	<div class="modal-dialog" id="showModifyGroupsTitleAreaPosition">
		<div class="modal-content" id="">
			<div class="modal-header" id="modifyBulletinHeader">
				修改社群名稱   
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body" id="modifyBulletinContent">
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
					<label for="groupsTitle">修改社群名稱</label>
					<input type="TEXT" name="groups_title" id="groupsTitle" size="45" class="form-control"
					value="<%= (groupsVO==null)? "" : groupsVO.getGroups_title()%>" />
					<input type="hidden" name="groups_no" value="<%=groupsVO.getGroups_no()%>">
					<input type="hidden" name="groups_owner" value="${groupsVO.groups_owner}">
					<input type="hidden" name="groups_time" value="<%= groupsVO.getGroups_time() %>">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="hidden" name="action" value="update">
					<input type="submit" value="送出" id="updateGroupsButton" class="floatRight">
			    </FORM>
			</div>
		</div>
	</div>
</div>
<!-- 修改社群結束 -->
</body>
<script>

</script>
<script language="JavaScript" src="<%=request.getContextPath()%>/front_end/groups/js/Groups_Invite.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/front_end/groups/js/groupsContent.js"></script>
</html>
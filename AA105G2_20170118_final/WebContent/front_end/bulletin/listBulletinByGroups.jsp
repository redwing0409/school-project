<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@page import="com.bulletin.model.*"%>
<%@page import="com.member.model.*"%>
<%@page import="com.groups.model.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	List<BulletinVO> list = (List<BulletinVO>) request.getAttribute("listBulletinByGroups"); //BulletinServlet.java(Concroller)
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="groupsSvc" scope="page" class="com.groups.model.GroupsService" />
<html>
<head>
<style>
th, td {
	padding: 5px;
}
table {
	table-layout: fixed;
	word-break: break-all;
	border-collapse: separate;
    border-spacing: 5px;
}
img {
	max-width: 100%;
	max-height: 100%;
	overflow:hidden;
}
</style>
<title>社群塗鴉牆資料 - ListBulletinByGroups.jsp</title>
</head>
<script type="text/javascript" src="<%= request.getContextPath() %>/front_end/bulletin/ckeditor/ckeditor.js"></script>
<body>
<div class="col-xs-12 col-sm-2"></div>
<div class="col-xs-12 col-sm-8" >
	<c:forEach var="bulletinVO" items="${list}">
	<div id="eachBulletin">
		<table style="width:100%">
		<jsp:useBean id="bulletinVO" scope="page" class="com.bulletin.model.BulletinVO"/>
		<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");%>
			<c:forEach var="memberVO" items="${memberSvc.all}">
				<c:if test="${bulletinVO.member_no==memberVO.member_no}">
					<tr>
						<td rowspan="2" align="center" width="75px" height="75px">
							<img src="<%= request.getContextPath() %>/member/ShowMember_Pic.do?member_no=${memberVO.member_no}"/>
						</td>
						<td>
						      作者:   ${memberVO.member_name} 
					    </td>
				    </tr>
				    <tr>
					    <td>
					      	 發布時間: <%= sdf.format(bulletinVO.getBulletin_time()) %>
						</td>
					</tr>
					<tr>
						<td colspan="2" id="bulletinContent">
							${bulletinVO.bulletin_content}
						</td>
					</tr>
					<tr>
						<td colspan="2" style="height:20px">
							<c:if test="${bulletinVO.member_no==account.member_no}">
							    <div id="deleteBulletinButton" class="floatRight">
									<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/bulletin/bulletinServlet.do">
										<input type="button" class="btn btn-info" value="刪除">
									    <input type="hidden" name="bulletin_no" value="${bulletinVO.bulletin_no}">
									    <input type="hidden" name="groups_no" value="${bulletinVO.groups_no}">
									    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									    <input type="hidden" name="action"	value="delete">
								  	</FORM>
							    </div>
							    <div id="modifyBulletinButton" class="floatRight">
									<a href="#${bulletinVO.bulletin_no}" data-toggle="modal" class="btn btn-info" id="modifyBulletinButton" style="font-size:10px;font-weight: bold">修改</a>
							    </div>
						    </c:if>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
<!-- 修改的彈跳視窗 -->
	<div class="modal fade" id="${bulletinVO.bulletin_no}">
		<div class="modal-dialog" id="showModifyBulletinAreaPosition">
			<div class="modal-content" id="">
				<div class="modal-header" id="modifyBulletinHeader">
					修改塗鴉牆內容   
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
					<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/bulletin/bulletinServlet.do" name="form1">
						<textarea cols="80" rows="5" id="modifyContent${bulletinVO.bulletin_no}" name="bulletin_content">${bulletinVO.bulletin_content}</textarea>
						<script type="text/javascript">
							CKEDITOR.replace( 'modifyContent${bulletinVO.bulletin_no}', {skin:'moono-lisa'});
						</script>
					    <input type="hidden" name="action" value="update">
						<input type="hidden" name="bulletin_no" value="<%=bulletinVO.getBulletin_no()%>">
						<input type="hidden" name="groups_no" value="<%=bulletinVO.getGroups_no()%>">
						<input type="hidden" name="member_no" value="${bulletinVO.member_no}">
						<input type="hidden" name="bulletin_time" value="<%= bulletinVO.getBulletin_time() %>">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
						<input type="submit" value="送出修改">
				    </FORM>
				</div>
			</div>
		</div>
	</div>
	</div>
<!-- 修改的彈跳視窗 -->	
	</c:forEach>
</div>
<div class="col-xs-12 col-sm-2">
<a href='#insertBulletin' data-toggle="modal" id="insertBulletinButton" class="floatRight">+</a>
</div>
<div class="modal fade" id="insertBulletin">
	<div class="modal-dialog" id="showModifyBulletinAreaPosition">
		<div class="modal-content" id="">
			<div class="modal-header" id="modifyBulletinHeader">
				新增塗鴉牆內容   
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
				<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/bulletin/bulletinServlet.do" name="form1">
					<textarea cols="80" rows="5" id="insertContent" name="bulletin_content"></textarea>
					<script type="text/javascript">
						CKEDITOR.replace( 'insertContent', {skin:'moono-lisa'});
					</script>
					<input type="hidden" name="groups_no" value="${param.groups_no}">
					<input type="hidden" name="member_no" value="${account.member_no}">
					<%java.sql.Timestamp date_SQL = new java.sql.Timestamp(System.currentTimeMillis());%>
					<input type="hidden" name="bulletin_time" value="<%= date_SQL%>">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
					<input type="hidden" name="action" value="insert">
					<input type="submit" value="送出新增"></FORM>
			    </FORM>
			</div>
		</div>
	</div>
</div>
</body>
<script>
$(document).ready(function() {
	$("#deleteBulletinButton input[type=button]").click(function(){
		if(confirm("確定要刪除?")){
			$(this).parent().submit();
		};
	});
	$("#bgpanel").css('height','auto');
	$("#bgpanel").css('margin-bottom','20px');
});
</script>
</html>
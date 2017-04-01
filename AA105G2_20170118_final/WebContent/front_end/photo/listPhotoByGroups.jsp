<%@page import="com.photo.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@page import="com.member.model.*"%>
<%@page import="com.groups.model.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	PhotoVO photoVO = (PhotoVO) request.getAttribute("addPhoto");
	List<PhotoVO> list = (List<PhotoVO>) request.getAttribute("listPhotoByGroups"); 
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="groupsSvc" scope="page" class="com.groups.model.GroupsService" />
<html>
<head>
<title>社群照片 - ListPhotoByGroups.jsp</title>
</head>
<style>
table {
	table-layout: fixed;
	word-break: break-all;
	border-collapse: collapse;
    border-spacing: 5px;
}
img {
	max-width: 100%;
	max-height: 100%;
	overflow:hidden;
}
td{
	height:200px;
	width:25%;
	padding:3px;
}
#deletePhotoBtn{
	background-color: white; /* Green */
    border: none;
    color: black;
    padding: 0px 16px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    margin: 4px 2px;
    transition-duration: 0.4s;
    cursor: pointer;
}
#deletePhotoBtn:hover{
	background-color: black; /* Green */
    border: 1px solid white;
    color: white;
    transition-duration: 0.4s;
    cursor: pointer;
}

</style>
<body>
<%@ include file="pages/photoLine.file" %>
<div>
	共<%= rowNumber %>張
	<a href='#insertPhoto' data-toggle="modal" id="insertPhotoButton" class="floatRight">+</a>
</div>

<table id="listGroupsPhoto" border='2'  width='100%'>

<% for( whichLine = 1; whichLine <= lineNumber ; whichLine++) {%>
	<%  lineIndex=lineIndexArray[whichLine-1];%>
	<tr>
		<c:forEach var="photoVO" items="${list}" begin="<%=lineIndex%>" end="<%=lineIndex+photosPerLine-1%>">
			<td align="center" >
				<div class="eachPhotoFile" data-photono="${photoVO.photo_no}" data-phototitle="${photoVO.photo_title}" data-groupno="${photoVO.groups_no}">
					<img  src="<%= request.getContextPath() %>/photo/ShowPhoto.do?photo_no=${photoVO.photo_no}"/>
				</div>
			</td>
		</c:forEach>
	</tr>
<% } %>
</table>
<!-- 顯示大圖開始 -->
<div class="modal fade" id="showBigPhotoArea">
	<div class="modal-dialog" id="showBigPhotoAreaPosition">
		<div class="" id="showBigPhoto">
		</div>
		<div id="showBigPhotoFooter">
			<div id="showPhotoTitle" class="floatLeft" style="font-weight: bold;color: white">
			</div>
			<div id="deletePhotoButton" class="floatRight">
				<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/photo/photoServlet.do">
					<input id="deletePhotoBtn" type="submit" value="刪除">
					<input id="deletePhotoNo" type="hidden" name="photo_no" value="">
					<input id="deletePhotoGroupNO" type="hidden" name="groups_no" value="">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="hidden" name="action"	value="delete">
				</FORM>
			</div>
		</div>
	</div>
</div>
<!-- 顯示大圖結束 -->
<!-- 新增照片開始 -->
<div class="modal fade" id="insertPhoto">
	<div class="modal-dialog" id="showInsertPhotoAreaPosition">
		<div class="modal-content" id="">
			<div class="modal-header" id="insertPhotoHeader">
				新增照片
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body" id="insertPhotoContent">
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
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/photo/photoServlet.do" name="form1" enctype="multipart/form-data">
					<input type="hidden" name="member_no" value="${account.member_no}">
					<input type="hidden" name="groups_no" value="${param.groups_no}">
					<div class="input-group">
						<div class="input-group-addon">照片說明</div>
						<input type="TEXT" name="photo_title" size="45" id="photo_title" class="form-control"
								value="<%= (photoVO==null)? "" : photoVO.getPhoto_title()%>" />
					</div>
					<input type="file" id="theFile" name="photo_file" size="45"/>
					<%java.sql.Timestamp date_SQL = new java.sql.Timestamp(System.currentTimeMillis());%>
					<input type="hidden" name="photo_createdate" value="<%= date_SQL%>" >
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="hidden" name="action" value="insert">
					<input type="submit" value="送出新增">
				</FORM>
				<img id="image" width="20%">
			</div>
		</div>
	</div>
</div>
<!-- 新增照片結束 -->
<%-- <c:if test="${list.size() != 0}"> --%>
<%-- 	<c:if test="${not empty errorMsgs}"> --%>
<!-- 		<font color='red'>請修正以下錯誤: -->
<!-- 		<ul> -->
<%-- 			<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 				<li>${message}</li> --%>
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->
<!-- 		</font> -->
<%-- 	</c:if> --%>
	
<!-- 	<table border='1' bordercolor='#CCCCFF' width='100%'> -->
<!-- 		<tr> -->
<!-- 			<th>照片編號</th> -->
<!-- 			<th>會員編號</th> -->
<!-- 			<th>社群編號</th> -->
<!-- 			<th>照片說明</th> -->
<!-- 			<th>檔案</th> -->
<!-- 			<th>日期/時間</th> -->
<%-- 			<c:if test="${photoVO.member_no==account.member_no}"> --%>
<!-- 				<th>刪除</th> -->
<%-- 			</c:if> --%>
<!-- 		</tr> -->
<%-- 		<c:forEach var="photoVO" items="${list}"> --%>
<%-- 			<jsp:useBean id="photoVO" scope="page" class="com.photo.model.PhotoVO"/> --%>
<%-- 			<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");%> --%>
<%-- 			<tr align='center' valign='middle' ${(photoVO.photo_no==param.photo_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已--> --%>
<%-- 				<td>${photoVO.photo_no}</td> --%>
<%-- 				<td>${photoVO.member_no} -  --%>
<%-- 					<c:forEach var="memberVO" items="${memberSvc.all}"> --%>
<%-- 	                    <c:if test="${photoVO.member_no==memberVO.member_no}"> --%>
<%-- 		                    ${memberVO.member_name} --%>
<%-- 	                    </c:if> --%>
<%-- 	                </c:forEach> --%>
<!-- 				</td> -->
<%-- 				<td>${photoVO.groups_no} -  --%>
<%-- 					<c:forEach var="groupsVO" items="${groupsSvc.allFront}"> --%>
<%-- 		                    <c:if test="${photoVO.groups_no==groupsVO.groups_no}"> --%>
<%-- 			                    ${groupsVO.groups_title} --%>
<%-- 		                    </c:if> --%>
<%-- 	                </c:forEach> --%>
<!-- 				</td> -->
<%-- 				<td>${photoVO.photo_title}</td> --%>
<!-- 				<td> -->
<%-- 					<img src="<%= request.getContextPath() %>/photo/ShowPhoto.do?photo_no=${photoVO.photo_no}" width="50%"/> --%>
<!-- 				</td> -->
<%-- 				<td><%= sdf.format(photoVO.getPhoto_createdate()) %></td> --%>
<%-- 				<c:if test="${photoVO.member_no==account.member_no}"> --%>
<!-- 					<td> -->
<%-- 					  <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/photo/photoServlet.do"> --%>
<!-- 					     <input type="submit" value="刪除"> -->
<%-- 					     <input type="hidden" name="photo_no" value="${photoVO.photo_no}"> --%>
<%-- 					     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<!-- 					     <input type="hidden" name="action"	value="delete"> -->
<!-- 					  </FORM> -->
<!-- 					</td> -->
<%-- 				</c:if> --%>
<!-- 			</tr> -->
<%-- 		</c:forEach> --%>
<!-- 	</table> -->
<%-- </c:if> --%>
<%-- <c:if test="${list.size() == 0}"> --%>
<!-- 	<font color='red' size='5'><strong>此社團沒有照片</strong></font> -->
<!-- 	<br> -->
<%-- 	<img src="<%= request.getContextPath() %>/photo/ShowPhoto.do?photo_no=0" /> --%>
<%-- </c:if> --%>

</body>
<script>
$(document).ready(function() {
	$("#bgpanel").css('height','auto');
	$("#bgpanel").css('margin-bottom','20px');
	$(".eachPhotoFile").click(function(){
		var index = $(".eachPhotoFile").index(this);
		var photono = $(this).data('photono');
		var phototitle = $(this).data('phototitle');
		var groupno = $(this).data('groupno');
		var img = "<img  id=\"" + photono + "\" src=\"" + "<%= request.getContextPath() %>/photo/ShowPhoto.do?photo_no=" + photono + "\"/>";
		var id = "\#" + photono;
		
		$("#showBigPhoto").html(img);
		
		var image = new Image();
		image.src = $(id).attr("src");
		var imgHeight = image.naturalHeight;
		var imgWidth = image.naturalWidth;
		
		if(imgHeight>550){
			imgWidth = imgWidth*(550/imgHeight);
			imgHeight = 550;
		}
		
		if (imgWidth>600){
			imgHeight = imgHeight*(600/imgWidth);
			imgWidth = 600;
		}
		
		if(imgHeight<550){
			$("#showBigPhotoAreaPosition").css('margin-top','7%');
		}
		
		$(id).css({height:imgHeight})
		$("#deletePhotoNo").val(photono);
		$("#deletePhotoGroupNO").val(groupno);
		
		$("#showBigPhotoAreaPosition").css({height:imgHeight+34,width:imgWidth});
		$("#showPhotoTitle").html(phototitle);
		$("#showBigPhotoArea").modal("show");
		
	});
});
</script>
<script language="JavaScript" src="<%=request.getContextPath()%>/front_end/photo/js/image.js"></script>
</html>
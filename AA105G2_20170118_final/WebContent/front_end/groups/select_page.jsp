<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>AA150G2 Groups:Home</title>
</head>
<body onload="connect();" onunload="disconnect();">
<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>AA105G2 Groups: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>


	<h3>資料查詢:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
		</font>
	</c:if>
	
	<a href='listAllGroups_Front.jsp'>List</a> all Groups <br><br>
	<a href='addGroups.jsp'>Add</a> a new Member <br><br>
	
	<jsp:useBean id="groupsSvc" scope="page" class="com.groups.model.GroupsService" />
	
	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/groups/groupsServlet.do" >
       <b>選擇社群(建立):</b>
       <select size="1" name="groups_no">
         <c:forEach var="groupsVO" items="${groupsSvc.allFront}" > 
        	 <c:if test="${account.member_no==groupsVO.groups_owner}">
         		 <option value="${groupsVO.groups_no}">${groupsVO.groups_title}
         	 </c:if>
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
    
    <jsp:useBean id="groups_listSvc" scope="page" class="com.groups_list.model.Groups_listService" />
    
    <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/groups/groupsServlet.do" >
       <b>選擇社群(參加):</b>
       <select size="1" name="groups_no">
         <c:forEach var="groups_listVO" items="${groups_listSvc.all}" > 
	         <c:if test="${account.member_no==groups_listVO.member_no}">
	         	<c:forEach var="groupsVO" items="${groupsSvc.allFront}" > 
		         	<c:if test="${groups_listVO.groups_no==groupsVO.groups_no}">
		         		<option value="${groupsVO.groups_no}">${groupsVO.groups_title}
		         	</c:if>
	         	</c:forEach>
	         </c:if>
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
    
<div id="websocketArea">
	<%if (session.getAttribute("account")!=null){%>
		    <jsp:include page="/front_end/groups/inviteView.jsp"/>
	<%} %>
</div>


</body>

</html>
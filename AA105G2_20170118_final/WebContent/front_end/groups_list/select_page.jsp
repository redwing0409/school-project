<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>AA150G2 Groups:Home</title>
</head>
<body>
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
	
	<a href='listAllGroups_list_Back.jsp'>顯示所有社群(含狀態)</a><br><br>
	<a href='listAllGroups_list_Front.jsp'>顯示所有未被刪除的社群</a>  <br><br>
	<a href='addGroups_list.jsp'>Add</a> a new Member <br><br>
	
	
	<jsp:useBean id="groupsSvc" scope="page" class="com.groups.model.GroupsService" />
	
	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/groups_list/groups_listServlet.do" >
       <b>選擇社群:</b>
       <select size="1" name="groups_no">
         <c:forEach var="groupsVO" items="${groupsSvc.allFront}">
             <option value="${groupsVO.groups_no}">${groupsVO.groups_title}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
    
    <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
    
    <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/groups_list/groups_listServlet.do" >
       <b>選擇會員:</b>
       <select size="1" name="member_no">
         <c:forEach var="memberVO" items="${memberSvc.all}" > 
          <option value="${memberVO.member_no}">${memberVO.member_name}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>

</body>
</html>
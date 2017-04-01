<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>AA150G2 Photo:Home</title>
</head>
<body>
<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>AA105G2 Photo:Home</h3><font color=red>( MVC )</font></td>
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
	
	<a href='listAllPhoto_Front.jsp'>List</a> all Photo <br><br>
	<a href='addPhoto.jsp'>Add</a> a new Photo <br><br>
	
	<jsp:useBean id="groupsSvc" scope="page" class="com.groups.model.GroupsService" />
	
	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/photo/photoServlet.do" >
       <b>選擇社群:</b>
       <select size="1" name="groups_no">
         <c:forEach var="groupsVO" items="${groupsSvc.allFront}" > 
          <option value="${groupsVO.groups_no}">${groupsVO.groups_title}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>

</body>
</html>
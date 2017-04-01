<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>把我打婚吧-預約訂單管理</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='lightblue' align='center' valign='middle' height='20'>
    <td><h3>IBM APPOINTMENT: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM APPOINTMENT: Home</p>

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

<ul>
  <li><a href='listOwnAppointment.jsp'>所有預約訂單</a></li> <br><br>
  
<!--   <li> -->
<%--     <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/Appointment/AppointmentServlet.do" > --%>
<!--      <FORM METHOD="post" ACTION="AppointmentServlet.do" >  -->
<!--         <b><font color=pink>輸入訂單編號 (如10):</font></b> -->
<!--         <input type="text" name="app_no"> -->
<!--         <input type="submit" value="送出"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--     </FORM> -->
<!--   </li> -->

<%--   <jsp:useBean id="appointmentSvc" scope="page" class="com.appointment.model.AppointmentService" /> --%>
   
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/Appointment/AppointmentServlet.do" > --%>
<!--        <b><font color=pink>選擇訂單編號:</font></b> -->
<!--        <select size="1" name="app_no"> -->
<%--          <c:forEach var="appointmentVO" items="${appointmentSvc.all}" >  --%>
<%--           <option value="${appointmentVO.app_no}">${appointmentVO.app_no} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="送出"> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--     </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/Appointment/AppointmentServlet.do" > --%>
<!--        <b><font color=pink>選擇會員編號:</font></b> -->
<!--        <select size="1" name="app_no"> -->
<%--          <c:forEach var="appointmentVO" items="${appointmentSvc.all}" >  --%>
<%--           <option value="${appointmentVO.app_no}">${appointmentVO.member_no} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="送出"> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--      </FORM> -->
<!--   </li> -->


<%--   <jsp:useBean id="placeSvc" scope="page" class="com.place.model.PlaceService" /> --%>
  
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/Place/PlaceServlet.do" > --%>
<!--        <b><font color=orange>選擇場地:</font></b> -->
<!--        <select size="1" name="place_no"> -->
<%--          <c:forEach var="placeVO" items="${placeSvc.all}" >  --%>
<%--           <option value="${placeVO.place_no}">${placeVO.place_name} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="送出"> -->
<!--        <input type="hidden" name="action" value="listAppointment_ByPlaceno"> -->
<!--      </FORM> -->
<!--   </li> -->

</ul>

<!-- <h3>訂單管理</h3> -->

<!-- <ul> -->
<!--   <li><a href='addAppointment.jsp'>新增預約訂單</a></li> -->
<!-- </ul> -->

</body>

</html>

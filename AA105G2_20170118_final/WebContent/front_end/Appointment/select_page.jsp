<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>��ڥ��B�a-�w���q��޲z</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='lightblue' align='center' valign='middle' height='20'>
    <td><h3>IBM APPOINTMENT: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM APPOINTMENT: Home</p>

<h3>��Ƭd��:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<ul>
  <li><a href='listOwnAppointment.jsp'>�Ҧ��w���q��</a></li> <br><br>
  
<!--   <li> -->
<%--     <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/Appointment/AppointmentServlet.do" > --%>
<!--      <FORM METHOD="post" ACTION="AppointmentServlet.do" >  -->
<!--         <b><font color=pink>��J�q��s�� (�p10):</font></b> -->
<!--         <input type="text" name="app_no"> -->
<!--         <input type="submit" value="�e�X"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--     </FORM> -->
<!--   </li> -->

<%--   <jsp:useBean id="appointmentSvc" scope="page" class="com.appointment.model.AppointmentService" /> --%>
   
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/Appointment/AppointmentServlet.do" > --%>
<!--        <b><font color=pink>��ܭq��s��:</font></b> -->
<!--        <select size="1" name="app_no"> -->
<%--          <c:forEach var="appointmentVO" items="${appointmentSvc.all}" >  --%>
<%--           <option value="${appointmentVO.app_no}">${appointmentVO.app_no} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="�e�X"> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--     </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/Appointment/AppointmentServlet.do" > --%>
<!--        <b><font color=pink>��ܷ|���s��:</font></b> -->
<!--        <select size="1" name="app_no"> -->
<%--          <c:forEach var="appointmentVO" items="${appointmentSvc.all}" >  --%>
<%--           <option value="${appointmentVO.app_no}">${appointmentVO.member_no} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="�e�X"> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--      </FORM> -->
<!--   </li> -->


<%--   <jsp:useBean id="placeSvc" scope="page" class="com.place.model.PlaceService" /> --%>
  
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/Place/PlaceServlet.do" > --%>
<!--        <b><font color=orange>��ܳ��a:</font></b> -->
<!--        <select size="1" name="place_no"> -->
<%--          <c:forEach var="placeVO" items="${placeSvc.all}" >  --%>
<%--           <option value="${placeVO.place_no}">${placeVO.place_name} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="�e�X"> -->
<!--        <input type="hidden" name="action" value="listAppointment_ByPlaceno"> -->
<!--      </FORM> -->
<!--   </li> -->

</ul>

<!-- <h3>�q��޲z</h3> -->

<!-- <ul> -->
<!--   <li><a href='addAppointment.jsp'>�s�W�w���q��</a></li> -->
<!-- </ul> -->

</body>

</html>

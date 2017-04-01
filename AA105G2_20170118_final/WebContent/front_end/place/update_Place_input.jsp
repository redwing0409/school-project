<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.place.model.*"%>
<%
PlaceVO placeVO = (PlaceVO) request.getAttribute("placeVO"); //PlaceServlet.java (Concroller), �s�Jreq��placeVO���� (�]�A�������X��placeVO, �]�]�A��J��ƿ��~�ɪ�placeVO����)

%>
<html>
<head>
<title>���a��ƭק� - update_Place_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="<%=request.getContextPath() %>/front_end/Place/js/calendarcode.js"></script>
<script language="JavaScript" src="<%=request.getContextPath() %>/front_end/Place/js/image.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='450'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���a��ƭק� - update_Place_input.jsp</h3>
		<a href="<%=request.getContextPath() %>/front_end/Place/select_page.jsp"><img src="<%=request.getContextPath() %>/front_end/Place/images/back1.gif" width="100" height="32" border="0">�^����</a></td>
	</tr>
</table>

<h3>��ƭק�:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Place/PlaceServlet.do" name="form1" enctype="multipart/form-data">
	<table border="0">
		<tr>
			<td>���a�s��:<font color=red><b>*</b></font></td>
			<td><%=placeVO.getPlace_no()%></td>
		</tr>
		<tr>
			<td>�t�ӽs��:<font color=red><b>*</b></font></td>
			<td><%=placeVO.getSup_no()%></td>
		</tr>
		<tr>
			<td>���a���O:</td>
			<td>
				<select size="1" name="place_type">
          			<option value="1" ${(1==placeVO.place_type)?'selected':''}>�s���B����
 					<option value="2" ${(2==placeVO.place_type)?'selected':''}>�x��B����
 					<option value="3" ${(3==placeVO.place_type)?'selected':''}>�B�b�\�U
       			</select>
			</td>
		</tr>
		<tr>
			<td>���a�W��:</td>
			<td><input type="TEXT" name="place_name" size="45"	value="<%=placeVO.getPlace_name()%>" /></td>
		</tr>
	
		<tr>
			<td>���a�a��:</td>
			<td>
				<select size="1" name="place_area">
						<option value="N" ${("N"==placeVO.place_area)?'selected':''}>�_��
	 					<option value="E" ${("E"==placeVO.place_area)?'selected':''}>�F��
	 					<option value="S" ${("S"==placeVO.place_area)?'selected':''}>�n��
						<option value="C" ${("C"==placeVO.place_area)?'selected':''}>����		   
       			</select>
    		</td>
		</tr>
		<tr>
			<td>���a�a�}:</td>
			<td><input type="TEXT" name="place_adds" size="45"	value="<%=placeVO.getPlace_adds()%>" /></td>
		</tr>
		<tr>
			<td>����a�Ϥ�:</td>
			<td style="text-align:center;vertical-align:middle" ><img src="ShowPlace_Pic.do?place_no=<%=placeVO.getPlace_no()%>" width="200" height="150"/>
			�n�ק諸���a�Ϥ�:
			<img id="image" width="200" >
			<input type="file" id="theFile" name="place_pic" size="45"	value="<%=placeVO.getPlace_pic()%>" />
			</td>
		</tr>
		<tr>
			<td>���a����:</td>
			<td><input type="TEXT" name="place_note" size="45"	value="<%=placeVO.getPlace_note()%>" /></td>
		</tr>
		<tr>
			<td>���a���A:<font color=red><b>*</b></font></td>
			<td><%=placeVO.getPlace_status()%></td>
		</tr>
		
	</table>
	<br>
	<input type="hidden" name="action"	value="update">
	<input type="hidden" name="place_no" value="<%=placeVO.getPlace_no()%>">
	<input type="hidden" name="sup_no" value="<%=placeVO.getSup_no()%>">
	<input type="hidden" name="place_status" value="<%=placeVO.getPlace_status()%>">
	<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
    <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:listAllPlace.jsp-->
	<input type="submit" value="�e�X�ק�">
</FORM>
    
	

<br>�e�X�ק諸�ӷ��������|:<br><b>
   <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br>
   <font color=blue>request.getParameter("whichPage"):</font> <%= request.getParameter("whichPage")%> 
</body>
</html>

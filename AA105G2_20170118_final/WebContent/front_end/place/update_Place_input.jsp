<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.place.model.*"%>
<%
PlaceVO placeVO = (PlaceVO) request.getAttribute("placeVO"); //PlaceServlet.java (Concroller), 存入req的placeVO物件 (包括幫忙取出的placeVO, 也包括輸入資料錯誤時的placeVO物件)

%>
<html>
<head>
<title>場地資料修改 - update_Place_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="<%=request.getContextPath() %>/front_end/Place/js/calendarcode.js"></script>
<script language="JavaScript" src="<%=request.getContextPath() %>/front_end/Place/js/image.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='450'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>場地資料修改 - update_Place_input.jsp</h3>
		<a href="<%=request.getContextPath() %>/front_end/Place/select_page.jsp"><img src="<%=request.getContextPath() %>/front_end/Place/images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Place/PlaceServlet.do" name="form1" enctype="multipart/form-data">
	<table border="0">
		<tr>
			<td>場地編號:<font color=red><b>*</b></font></td>
			<td><%=placeVO.getPlace_no()%></td>
		</tr>
		<tr>
			<td>廠商編號:<font color=red><b>*</b></font></td>
			<td><%=placeVO.getSup_no()%></td>
		</tr>
		<tr>
			<td>場地類別:</td>
			<td>
				<select size="1" name="place_type">
          			<option value="1" ${(1==placeVO.place_type)?'selected':''}>酒店、飯店
 					<option value="2" ${(2==placeVO.place_type)?'selected':''}>庭園、莊園
 					<option value="3" ${(3==placeVO.place_type)?'selected':''}>婚宴餐廳
       			</select>
			</td>
		</tr>
		<tr>
			<td>場地名稱:</td>
			<td><input type="TEXT" name="place_name" size="45"	value="<%=placeVO.getPlace_name()%>" /></td>
		</tr>
	
		<tr>
			<td>場地地區:</td>
			<td>
				<select size="1" name="place_area">
						<option value="N" ${("N"==placeVO.place_area)?'selected':''}>北部
	 					<option value="E" ${("E"==placeVO.place_area)?'selected':''}>東部
	 					<option value="S" ${("S"==placeVO.place_area)?'selected':''}>南部
						<option value="C" ${("C"==placeVO.place_area)?'selected':''}>中部		   
       			</select>
    		</td>
		</tr>
		<tr>
			<td>場地地址:</td>
			<td><input type="TEXT" name="place_adds" size="45"	value="<%=placeVO.getPlace_adds()%>" /></td>
		</tr>
		<tr>
			<td>原場地圖片:</td>
			<td style="text-align:center;vertical-align:middle" ><img src="ShowPlace_Pic.do?place_no=<%=placeVO.getPlace_no()%>" width="200" height="150"/>
			要修改的場地圖片:
			<img id="image" width="200" >
			<input type="file" id="theFile" name="place_pic" size="45"	value="<%=placeVO.getPlace_pic()%>" />
			</td>
		</tr>
		<tr>
			<td>場地介紹:</td>
			<td><input type="TEXT" name="place_note" size="45"	value="<%=placeVO.getPlace_note()%>" /></td>
		</tr>
		<tr>
			<td>場地狀態:<font color=red><b>*</b></font></td>
			<td><%=placeVO.getPlace_status()%></td>
		</tr>
		
	</table>
	<br>
	<input type="hidden" name="action"	value="update">
	<input type="hidden" name="place_no" value="<%=placeVO.getPlace_no()%>">
	<input type="hidden" name="sup_no" value="<%=placeVO.getSup_no()%>">
	<input type="hidden" name="place_status" value="<%=placeVO.getPlace_status()%>">
	<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
    <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--只用於:listAllPlace.jsp-->
	<input type="submit" value="送出修改">
</FORM>
    
	

<br>送出修改的來源網頁路徑:<br><b>
   <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br>
   <font color=blue>request.getParameter("whichPage"):</font> <%= request.getParameter("whichPage")%> 
</body>
</html>

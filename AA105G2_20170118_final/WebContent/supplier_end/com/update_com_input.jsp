<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.commodity.model.*"%>
<%
    ComVO comVO = (ComVO) request.getAttribute("comVO"); //ComServlet.java (Concroller), �s�Jreq��comVO���� (�]�A�������X��comVO, �]�]�A��J��ƿ��~�ɪ�empVO����)

%>
<html>
<head>
<title>�ӫ~��ƭק� - update_com_input.jsp</title></head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/supplier_end/com/css/update_com_input.css">
<script language="JavaScript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>

<script src="https://code.jquery.com/jquery.js"></script>
<script src="<%=request.getContextPath()%>/supplier_end/com/js/jquery-ui.js"></script>

<script>
 $(function() {
	var $j = jQuery.noConflict();
	$j("#datepicker1,#datepicker2").datepicker({
		    dateFormat:"yy-mm-dd",
		    showOtherMonths: true,
		    selectOtherMonths: true,
			changeMonth: true,
			changeYear: true
		});
	});
</script>
<script>
   $(document).ready(function(){
	  $("#updateCom").click(function(){
		 $("#com_name").attr("value","���ֹ�M");
		 $("#com_desc").attr("value","�ؤo:��9cm*�����|8cm�C<br>����:�a�q�������J�M�C");
		 $("#com_price").attr("value","420");
		 $("#com_note").val("�s�@�Ѽ�:�q�h�s�@�ѼƥH�A�ȤH���i�����D�C");
	  });
   });
</script>

<body bgcolor='white'>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="list-style-type:none;">${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/com/com.do" name="form1" enctype="multipart/form-data">
<h4 style="margin-left:400px;font-weight:bold">�ק�ӫ~���</h4>  
<br>
	<div class="container" id="updateContent">
					<div class="row">
						<div class="col-xs-12 col-sm-4  col-sm-offset-1">
						    <div class="input-group">
							      <span class="">�ӫ~�s��:</span>
							      <%=comVO.getCom_no()%>
		   					</div>
						</div>
						<div class="col-xs-12 col-sm-4">
						     <div class="input-group" style="margin-right:50px">
		   					      <span class="">�ӫ~���A<font color="red"><b>*</b></font>:</span>
								  <%-- <label class="radio-inline"><input type="radio" name="com_status" value="1" <%if (comVO.getCom_status()==1){%>checked<%} %>>�W�[</label>
								  <label class="radio-inline"><input type="radio" name="com_status" value="2" <%if (comVO.getCom_status()==2){%>checked<%} %>>�U�[</label> --%>
								  <label class="radio-inline"><input type="radio" name="com_status" value="1" <%= (comVO==null || comVO.getCom_status().equals(1)) ? "checked" : "" %>>�W�[</label>
								  <label class="radio-inline"><input type="radio" name="com_status" value="2" <%= (comVO==null || comVO.getCom_status().equals(1)) ? "" : "checked" %>>�U�[</label>
		   					</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-xs-12 col-sm-4  col-sm-offset-1">
						     <div class="input-group">
							      <span class="input-group-addon">�ӫ~�W��<font color="red"><b>*</b></font>:</span>
		   					      <input id="com_name" type="text" class="form-control" name="com_name" style="width:80%" value="<%=comVO.getCom_name()%>" />
		   					</div>
						</div>
						<div class="col-xs-12 col-sm-4">
						    <div class="input-group" style="margin-right:50px">
		   					    <span class="input-group-addon">�ӫ~����<font color="red"><b>*</b></font></span>
							    <select name="pcm_no" class="form-control" style="width:98%">
							            <option value="1">�ߩ�</option>
							            <option value="2">�B���ۥ�</option>
							            <option value="3">�B§�p��</option> 
							            <option value="4">�B§�Ϋ~</option> 
								</select>
		   					</div>
						</div>
					</div>
					<br>
					<div class="row">
					   <div class="col-xs-12 col-sm-4  col-sm-offset-1">
						   <div class="input-group">
							      <span class="input-group-addon">�ӫ~�y�z<font color="red"><b>*</b></font>:</span>
							      <input id="com_desc" type="text" class="form-control" name="com_desc" style="width:80%" value="<%= (comVO==null)? "�n�γߩ�" : comVO.getCom_desc()%>">
		   				   </div>
						</div>
						<div class="col-xs-12 col-sm-4">
		   					<div class="input-group">
							      <span class="input-group-addon">�W�[���<font color="red"><b>*</b></font>:</span>
							      <input id="datepicker1" type="text" class="form-control" name="com_shelf_date" placeholder="���I��" style="width:80%;">
		   					</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-xs-12 col-sm-4  col-sm-offset-1">
					 		<div class="input-group">
							      <span class="input-group-addon">�ӫ~����<font color="red"><b>*</b></font>:</span>
							      <input id="com_price" type="text" class="form-control" name="com_price" style="width:80%" value="<%= (comVO==null)? "30" : comVO.getCom_price()%>">
		   					</div>
						</div>
						<div class="col-xs-12 col-sm-4">
						    <div class="input-group">
							      <span class="input-group-addon">�U�[���<font color="red"><b>*</b></font>:</span>
							      <input id="datepicker2" type="text" class="form-control" name="com_off_date" placeholder="���I��" style="width:80%;">
		   					</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-xs-12 col-sm-4  col-sm-offset-1">
						   <div class="input-group">
						        <div class="row" style="margin-left:13px">
								    <label class="col-sm-5 control-label" style="margin-left:-17px;">�W�ǹϤ�:</label>		
								 </div>	    
								 <div class="row" style="margin-left:3px">
									 <input type="file" id="upPic" name="com_pic"  onchange="loadFile()"/><br>	
								 </div>    
									 <img id="pic" width=100>
		   					</div> 
						</div>
						<div class="col-xs-12 col-sm-6">
					          <div class="form-group">
					                  <div class="row" style="margin-left:13px">
						                   <label class="col-sm-4 control-label" style="margin-left:-25px;">�ӫ~�Ƶ�:</label>			    
						              </div>
					                  <div class="row" style="margin-left:3px">
							  	           <textarea class="form-control" id="com_note" name="com_note" rows="5" cols="10"  style="width:53%;"></textarea>
				   		          <!-- 	 <script> CKEDITOR.replace( 'com_note',{});</script>     -->
				   		              </div>
				   	          </div>
		   				</div>
					</div>
				</div>
            </div><!--container-->


<input type="hidden" name="action" value="update">
<input type="hidden" name="com_no" value="<%=comVO.getCom_no()%>">
<input type="hidden" name="sup_no" value="${supAccount.sup_no}">
<input type="submit" value="�e�X�ק�" style="margin-top:15px;margin-left:102px"/>
<input type="button" id="updateCom" value="magic" style="margin-top:15px;margin-left:102px"/>
</FORM>
</body>

<script>
  function loadFile(){
	  var filePic = document.getElementById('upPic').files[0];
	  var readFile = new FileReader();
	  readFile.readAsDataURL(filePic);
	  readFile.addEventListener('load',function(){
		  var image = document.getElementById('pic');
		  image.src = readFile.result;
	  },false);
  }
</script>
</html>



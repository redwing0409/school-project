<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.place.model.*"%>
<%@ page import="com.appointment.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="java.sql.Date"%>


<%	
	PlaceService placeSvc = new PlaceService();
	AppointmentVO appointmentVO = (AppointmentVO) request.getAttribute("appointmentVO");
	List<PlaceVO> list = null;
	
	if (request.getAttribute("listArea") != null) {
		list = (List<PlaceVO>) request.getAttribute("listArea");
		if(session.getAttribute("listType")!=null){
			list.retainAll((List<PlaceVO>)(session.getAttribute("listType")));
			session.removeAttribute("listArea");
		}
		
	} else if (request.getAttribute("listType") != null) {
		list = (List<PlaceVO>) request.getAttribute("listType");
		if(session.getAttribute("listArea")!=null){
			list.retainAll((List<PlaceVO>)(session.getAttribute("listArea")));
			session.removeAttribute("listType");
		}		
		
	} else {
		list = placeSvc.getAll();
		session.removeAttribute("listType");
		session.removeAttribute("listArea");
	}
	pageContext.setAttribute("list", list);
%>

<html>
<head>


<meta name="viewport" content="width=device-width, initial-scale=1">
<title>�|�]�w��</title>
<%-- <link rel="stylesheet"	href="<%=request.getContextPath()%>/front_end/pages/css/bootstrap.min.css"> --%>
<%-- <link rel="stylesheet"  href="<%=request.getContextPath()%>/front_end/pages/css/PlacesPage.css"> --%>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>   <!-- ��䪺js -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" /> <!--��䪺css-->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/pages/js/bootstrap.min.js"></script> --%>


<script>	
		//modal
		var number;
		var arrayOri=[];
		function show(data){
			var note=data.getAttribute("data-note");
			number=data.getAttribute("data-no");
			var placeAdds=data.getAttribute("data-adds");
			var placeText=document.getElementById("placeText");
			var pic =document.getElementById("pic");
			var adds =document.getElementById("placeAdds");
			document.getElementById("hiddenNo").value=number;
			placeText.innerHTML=note;
			adds.innerHTML=placeAdds;
			pic.src="<%=request.getContextPath()%>/Place/ShowPlace_Pic.do?place_no="+number;

			//ajax for menu
			$.ajax({
				 type:"GET",
				 url:"<%=request.getContextPath()%>/GetMenuJason.do",
				 data:{"place_no":number},
				 dataType:"json",
				 success:function (array){
	console.log(array);
					 creat(array);
			     },
	             error:function(){alert("error")}
         	})
			function creat(array){
				$(".item").remove();
				for (i in array) {
					if(i==0){
						$(".carousel-inner").append("<div class='item active' id='menu-pic'><img src='"+"<%=request.getContextPath()%>"+"/ShowMenu_Pic.do?menu_no="+array[i].menu_no+"' alt=''><div class='container'><div class='carousel-caption' id='menu-text'><p>"+array[i].menu_name+"</p></div></div></div>");
						}else{
						$(".carousel-inner").append("<div class='item' id='menu-pic'><img src='"+"<%=request.getContextPath()%>"+"/ShowMenu_Pic.do?menu_no="+array[i].menu_no+"' alt=''><div class='container'><div class='carousel-caption' id='menu-text'><p>"+array[i].menu_name+"</p></div></div></div>");	
					}
				}	
			}

			//ajax for calendar
	       	var forbidddenDate;
        	var xhr = new XMLHttpRequest(); 
        	var url= "<%=request.getContextPath()%>/GetDateJason.do?place_no="+number;
		xhr.open("Get", url, true);
		xhr.send(null);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
//					console.log(xhr.responseText);
					forbidddenDate = JSON.parse(xhr.responseText);
					arrayOri = forbidddenDate.forbiddenDate;
					//console.log(arrayOri);

				} else
					alert(xhr.status);
			}
		};
		var sty = {
			dayNames : [ "�P����", "�P���@", "�P���G", "�P���T", "�P���|", "�P����", "�P����" ],
			dayNamesMin : [ "��", "�@", "�G", "�T", "�|", "��", "��" ],
			monthNames : [ "�@��", "�G��", "�T��", "�|��", "����", "����", "�C��", "�K��",
					"�E��", "�Q��", "�Q�@��", "�Q�G��" ],

			showMonthAfterYear : true,
			dateFormat : "yy-mm-dd",
			numberOfMonths : 2,
			showButtonPanel : true,
			minDate : -0,
			beforeShowDay : notcheck
		};

		function notcheck(date) {
			var arrayForbidden = [];
			arrayForbidden = arrayOri.concat(arrayFinal);
			//console.log(arrayForbidden);
			var string = jQuery.datepicker.formatDate('yy-mm-dd', date);
			return [ arrayForbidden.indexOf(string) == -1 ];
		}

		$("#datepicker").datepicker(sty);
		$("#datepicker").datepicker("refresh");

	}

	//ajx

	//�H�U��websocket
	var MyPoint2 = "/MyEchoServerCal";
	var host2 = window.location.host;
	var path2 = window.location.pathname;
	var webCtx2 = path2.substring(0, path2.indexOf('/', 1));
	var endPointURL2 = "ws://" + window.location.host + webCtx2 + MyPoint2;

	var webSocket;
	var arrayFinal = [];
	var indexOfArray = 0;
	var array ;
	function connectCal() {
		console.log("���websocket�s�u");
		// �إ� websocket ����
		console.log("���websocket:"+endPointURL2);
		//console.log("���websocket+"+endPointURL);
		webSocket = new WebSocket(endPointURL2);
		webSocket.onopen = function(event) {
		};

		//var indexOfArray = 0;
		var time = 0;
		var date = document.getElementById("datepicker");
		array = [];
		date.onchange = function() {
			if (time == 0) {
				indexOfArray = array.length;
				array.push(date.value);
				var jsonObj = {
					"place_no" : number,
					"forbiddenDate" : date.value,
					"array" : array,
					"indexOfArray" : -1
				};
				webSocket.send(JSON.stringify(jsonObj));
			} else {
				var jsonObj = {
					"place_no" : number,
					"forbiddenDate" : date.value,
					"array" : array,
					"indexOfArray" : indexOfArray
				};
				webSocket.send(JSON.stringify(jsonObj));
			}
			time++;
		}
		webSocket.onmessage = function(event) {		
			var jsonObj = JSON.parse(event.data);
			array = jsonObj.array;		
			if (jsonObj.place_no == number) {
				arrayFinal = array.concat(arrayOri);			
			}
			$("#datepicker").datepicker("refresh");
		};

	}

	function disconnectCal() {
		var jsonObj = {
				"place_no" : number,
				"forbiddenDate" : "",
				"array" : array,
				"indexOfArray" : indexOfArray
			};
			webSocket.send(JSON.stringify(jsonObj));		
		webSocket.close();
	}
</script>


<!--  Nav toggle effect -->
<script type="text/javascript">
	$(document).ready(function() {
		$('.slidemenu').mouseover(function() {
			$('.slidesub').stop(true, false).slideDown();
		});
		$('.slidemenu').mouseleave(function() {
			$('.slidesub').stop(true, false).slideUp();
		});
		$(".area").click(function() {
			$("#area-group").slideToggle("slow", function() {
				$("#type-group").hide("slow");
			});
		});

		$(".type").click(function() {
			$("#type-group").slideToggle("slow", function() {
				$("#area-group").hide("slow")
			});
		});
	});
</script>
<!--  Nav toggle effect end-->

<style type="text/css">
#menu-pic img{    
    width:100%;
    height:100%;
}
#menu-text p{
    font-size:35px;
    font-weight: bold;
}

</style>
</head>
<body onunload="disconnectCal();">
	<!-- �����C���ص��� -->
	<jsp:include page="/front_end/place/headerForPlace.jsp" />
	<div id="intro">
		<!--#intro -->
		<!-- �o��O�j�a�������e -->

		<div id="bgpanel">
			<div class="col-xs-12 col-sm-2 reservation">
				<h1>�|�]�w��</h1>
				<a href="#" class="list-group-item area">�a�Ͽ��</a>
				<ul id="area-group">
					<a
						href="<%=request.getContextPath()%>/Place/PlaceServlet.do?action=getArea_For_Display&place_area=N"
						class="list-group-item">�_��</a>
					<a
						href="<%=request.getContextPath()%>/Place/PlaceServlet.do?action=getArea_For_Display&place_area=C"
						class="list-group-item">����</a>
					<a
						href="<%=request.getContextPath()%>/Place/PlaceServlet.do?action=getArea_For_Display&place_area=S"
						class="list-group-item">�n��</a>
					<a
						href="<%=request.getContextPath()%>/Place/PlaceServlet.do?action=getArea_For_Display&place_area=E"
						class="list-group-item">�F��</a>
				</ul>
				<a href="#" class="list-group-item type">���a���A</a>
				<ul id="type-group">
					<a
						href="<%=request.getContextPath()%>/Place/PlaceServlet.do?action=getType_For_Display&place_type=1"
						class="list-group-item">�s���B����</a>
					<a
						href="<%=request.getContextPath()%>/Place/PlaceServlet.do?action=getType_For_Display&place_type=2"
						class="list-group-item">�x��B����</a>
					<a
						href="<%=request.getContextPath()%>/Place/PlaceServlet.do?action=getType_For_Display&place_type=3"
						class="list-group-item">�B�b�\�U</a>
				</ul>
			</div>


			<div class="col-xs-12 col-sm-10">



				<%@ include file="page1.file"%>
				<c:forEach var="PlaceVo" items="${list}" step="4" varStatus="s"
					begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<div class="row" id="place-row">

						<c:if test="${PlaceVo.place_status == 1}">
							<div class="col-xs-12 col-sm-3 img">
								<a href='#modal-id' data-toggle="modal"
									data-note="${list[s.index].place_note}"
									data-no="${list[s.index].place_no}" 
									data-adds="${list[s.index].place_adds}"
									onclick="show(this);"><img
									src="<%=request.getContextPath()%>/Place/ShowPlace_Pic.do?place_no=${list[s.index].place_no}"
									class="img-responsive"></a>
								<p>${list[s.index].place_name}</p>
							</div>

						</c:if>
						<c:if test="${list[s.index+1].place_status == 1}">
							<div class="col-xs-12 col-sm-3 img">
								<a href='#modal-id' data-toggle="modal"
									data-note="${list[s.index+1].place_note}"
									data-no="${list[s.index+1].place_no}" 
									data-adds="${list[s.index+1].place_adds}"
									onclick="show(this);"><img
									src="<%=request.getContextPath()%>/Place/ShowPlace_Pic.do?place_no=${list[s.index+1].place_no}"
									class="img-responsive"></a>
								<p>${list[s.index+1].place_name}</p>
							</div>

						</c:if>
						<c:if test="${list[s.index+2].place_status == 1}">
							<div class="col-xs-12 col-sm-3 img">
								<a href='#modal-id' data-toggle="modal"
									data-note="${list[s.index+2].place_note}"
									data-no="${list[s.index+2].place_no}" 
									data-adds="${list[s.index+2].place_adds}"
									onclick="show(this);"><img
									src="<%=request.getContextPath()%>/Place/ShowPlace_Pic.do?place_no=${list[s.index+2].place_no}"
									class="img-responsive"></a>
								<p>${list[s.index+2].place_name}</p>
							</div>

						</c:if>
						<c:if test="${list[s.index+3].place_status == 1}">
							<div class="col-xs-12 col-sm-3 img">
								<a href='#modal-id' data-toggle="modal"
									data-note="${list[s.index+3].place_note}"
									data-no="${list[s.index+3].place_no}" 
									data-adds="${list[s.index+3].place_adds}"
									onclick="show(this);"><img
									src="<%=request.getContextPath()%>/Place/ShowPlace_Pic.do?place_no=${list[s.index+3].place_no}"
									class="img-responsive"></a>
								<p>${list[s.index+3].place_name}</p>
							</div>

						</c:if>


					</div>
				</c:forEach>
				<%@ include file="page2.file"%>
			</div>
		</div>


		<!-- �I��Ϥ����X�����a���� -->
		<div class="modal fade" id="modal-id">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h2 class="modal-title">���a����</h2>
					</div>
					<div class="modal-body place">
						<div class="col-xs-12 col-sm-6">
							<img src="" class="img-responsive" id="pic">
						</div>
						<div class="col-xs-12 col-sm-6" id="placeText"></div>						
						<div class="col-xs-12 col-sm-6" style='color:red'>�a�}:<span id="placeAdds"></span></div>						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-warning" href='#menu'
							data-toggle="modal">���˦�</button>
						<button type="button" class="btn btn-warning" href='#reservation'
							data-toggle="modal" onclick="connectCal();">���a�w��</button>

					</div>
				</div>
			</div>
		</div>

		<!-- ���˦��e�� -->
		<div class="modal fade" id="menu">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h2 class="modal-title">���˦�</h2>
					</div>
					<div class="modal-body">
						<div id="carousel-id" class="carousel slide" data-ride="carousel">
							<!-- 							�ۿO���D�ϰ� -->
							<div class="carousel-inner">
<!-- 								<div class="item active"> -->
<!-- 									<img src="���Ӥ�/10.jpg" alt=""> -->
<!-- 									<div class="container"> -->
<!-- 										<div class="carousel-caption"> -->
<!-- 											<p>�A�ܹL�F�ܡH</p> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="item"> -->
<!-- 									<img src="���Ӥ�/20.jpg" alt=""> -->
<!-- 									<div class="container"> -->
<!-- 										<div class="carousel-caption"> -->
<!-- 											<p>�A�w�ˤF�ܡH</p> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="item"> -->
<!-- 									<img src="���Ӥ�/30.jpg" alt=""> -->
<!-- 									<div class="container"> -->
<!-- 										<div class="carousel-caption"> -->
<!-- 											<p>�ڬO�����A�A�i�H��r���b�o����</p> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
							</div>
						<!-- �W�U������� -->
						<a class="left carousel-control" href="#carousel-id"
							data-slide="prev"><span
							class="glyphicon glyphicon-chevron-left"></span></a> <a
							class="right carousel-control" href="#carousel-id"
							data-slide="next"><span
							class="glyphicon glyphicon-chevron-right"></span></a>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" data-dismiss="modal">����</button>
				</div>
			</div>
		</div>
	</div>


	<!-- ���a�w���e�� -->
	<div class="modal fade" id="reservation">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title">���a�w��</h2>
				</div>
				<div class="modal-body">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Appointment/AppointmentServlet.do">
						<div class="col-xs-12 col-sm-8">

							<table>
								<tr>
									<td>�w�����:</td>
									<td><input type="text" id="datepicker" name="app_place_date"></td>
								</tr>
							</table>
						</div>
						<div>

							<button class="btn btn-warning" text-align="right" id="dateSubmit" disabled="true">�w�q���a</button>
							<input type="hidden" name="action" value="check"> <input
								type="hidden" name="place_no" value="" id="hiddenNo">

						</div>
					</FORM>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" data-dismiss="modal">����</button>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- �o��O�j�a�������e���� -->
	</div>
	<!--#intro end-->

</body>
<script>
	$("#datepicker").change(function(){
		if($(datepicker).val()!=""){
			$("#dateSubmit").attr("disabled",false);	
		}else{
			$("#dateSubmit").attr("disabled",true);
		}
	});
</script>
</html>

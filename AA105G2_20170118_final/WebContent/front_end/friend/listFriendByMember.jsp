<%@page import="java.util.*"%>
<%@page import="com.friend.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.member.model.*"%>
<%@page import="com.groups.model.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	//從session取得登入會員帳號
	String member_no=((MemberVO)session.getAttribute("account")).getMember_no();
	FriendService friendSvc=new FriendService();
	List<FriendVO> list=friendSvc.getOneFriend(member_no);
	pageContext.setAttribute("list",list);

%>
<html lang="">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>首頁</title>
	<link rel="stylesheet"	href="<%=request.getContextPath()%>/front_end/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/top_bottom.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/smoothDivScroll.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/secondeffect.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/FriendPage.css">
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->

	
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/bootstrap.min.js"></script>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/jquery.min.js"></script> --%>
	
	
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/jquery.scrollTo-1.4.2-min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/jquery.parallax-1.1.3.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/jquery.localscroll-1.2.7-min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/jquery-ui-1.10.3.custom.min.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/jquery.mousewheel.min.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/jquery.kinetic.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/jquery.smoothdivscroll-1.3-min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/jquery.flipping_text.js"></script>
    <!--Right slide to pages-->
	<script type="text/javascript">
		$(document).ready(function(){
			$('#nav').localScroll(800);
			$('#intro').parallax("50%", 0.1);
			$('#second').parallax("50%", 0.1);
			$('.bg').parallax("50%", 0.5);
			$('#third').parallax("50%", 0.3);
		})
	</script> 
	<!--Right slide to pages end-->

       <!--Fisrt page's rotation of pictures-->
    <script type="text/javascript">
		$(document).ready(function () {
			$("div#makeMeScrollable").smoothDivScroll({
				autoScrollingMode: "onStart"
			});
		});
	</script>
	<!--Fisrt page's rotation of pictures end-->	

	<!--Fisrt page's transform of font-->	

	<script type="text/javascript">
		 $(document).ready(function(){
	  	  $(".fonteffect .demofont").flipping_text();
	  	   });
    </script>
    <!--Fisrt page's transform of font end-->

       <!--Second page's rotation of advertisement-->	
	<script type="text/javascript">
		$(document).ready(function() {
		    var num_li = $(".adnav li").length;
		    var img_width = 540;
		    var width_banner = num_li*img_width;
		    var timer = 2500; //  設定輪撥速度

		    $(".adbanner").css("width", width_banner);

		    //  當滑鼠移過時，圖片移動到特定位置
		    for (i = 0; i < num_li; i++) {
		        $(".adnav li:eq(" + i + ")").mouseenter({
		            id: i
		        }, function(e) {
		            n = e.data.id
		            change();
		        })
		    }

		    //  設定輪撥函數
		    clock = setInterval(auto, timer);
		    n = 0;

		    function auto() {
		        n++;
		        if (n >= num_li) {
		            n = 0;
		        }

		        position = -n * img_width;
		        $(".adbanner").animate({
		            "left": +position + "px"
		        }, 400)

		        $(".adnav li").css({
	                "color": "#000",
	                "font-weight": "normal",
	                "border-bottom": "none"
		        })

		         //  讓滑鼠移到的清單改變成 hover 字體
		        $(".adnav li:eq(" + n + ")").css({
		            "color": "#FF60AF",
		            "font-weight": "bold",
		            "border-bottom": "3px solid #FF60AF"
		        })
		    }

		    //  當滑鼠移到導覽列、圖案上時把輪撥暫停
		    $(".adnav li, .adbanner img").hover(function() {
		        clearInterval(clock)
		    }, function() {
		    //當滑鼠移開時，輪播重新開始
		        clock = setInterval(auto, timer)      
		    })

		    //  當滑鼠移到 addnav時，根據標題移動圖片
		    function change() {
		        clearInterval(clock);
		        clock = setInterval(auto, timer);
		        position2 = -n * img_width;
		        $(".adbanner").stop();
		        $(".adbanner").animate({
		                "left": +position2 + "px"
		            }, 400)
		        //  讓所有的清單變回原本的字體
		        $(".adnav li").css({
	                "color": "#000",
	                "font-weight": "normal",
	                "border-bottom": "none"
		         })
		        //  讓滑鼠移到的清單改變成 hover 字體
		        $(".adnav li:eq(" + n + ")").css({
		            "color": "#FF60AF",
		            "font-weight": "bold",
		            "border-bottom": "3px solid #FF60AF"
		        })
		    }
		})				
	</script>
	 <!--Second page's rotation of advertisement-->	

	 <!--  Nav toggle effect -->
	<script type="text/javascript">
		$(document).ready( function() {
			$(".slidename").mouseover(function() {
				$(".slideCharacter").slideDown(1000);
			});
			$(".slidename").mouseleave(function() {
				$(".slideCharacter").slideUp(1000);
			});
			
			$(".slidemain").mouseover(function() {
				$(".slidesub").slideDown(1000);
			});
			$(".slidesub").mouseleave(function() {
				$(".slidesub").slideUp(1000);
			});
		});
	</script>
	 <!--  Nav toggle effect end-->
<style>
	.table-striped > tbody > tr:nth-of-type(2n+1) {
	    background-color: white;
	}
	.table-striped > tbody > tr:nth-of-type(2n) {
	    background-color: #f9dbe5;
	}
	#deleteFriendBtn,#addFriendBtn {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 0px 16px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    margin: 4px 2px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    cursor: pointer;
	}
	
	#deleteFriendBtn {
	    background-color: white; 
	    color: black; 
	    border: 2px solid #555555;
	}
	
	#deleteFriendBtn:hover {
	    background-color: #555555;
	    color: white;
	}
	#addFriendBtn{
		background-color: white; 
	    color: black; 
	    border: 2px solid #4CAF50; 
	}
	#addFriendBtn:hover{
		background-color: #4CAF50;
	    color: white;
	}
#accountMemberName{
/* 	background-color:yellow; */
	float:right;
}
#login{
/* 	background-color:red; */
	float:right;
}
#headerMarginLeft{ 
 	margin-left:339px; 
} 
</style>
</head>
<body>
        <!-- 滾動導覽列 -->
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-6">
			<div class="slidemenu" style="margin-left: 5px">
				<c:if test="${account.member_no !=null }">
					<div class="slidemain">
						<span><img
							style="width: 15px; height: 15px; margin-bottom: 3px"
							src="<%=request.getContextPath()%>/front_end/images/img/setting.png"></span>我的管理
					</div>
					<div class="slidesub" style="display: none">
						<ul>
							<li>
								<a href="<%= request.getContextPath() %>/front_end/updateMemberInfo.jsp" id="updateMemberSelect">修改基本資料</a>
							</li>
							<li><a href="<%= request.getContextPath() %>/front_end/friend/listFriendByMember.jsp">管理好友</a></li>
							<li><a href="<%= request.getContextPath() %>/front_end/Appointment/listOwnAppointment.jsp">瀏覽預約清單</a></li>
							<li><a href="<%= request.getContextPath() %>/front_end/shoppingcar/ShoppingRecord.jsp">瀏覽購物清單</a></li>
							<li>
							<a href="<%= request.getContextPath() %>/member/memberServlet.do?action=logOut&requestURL=<%=request.getServletPath()%>">登出</a>
<%-- 								<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/member/memberServlet.do" > --%>
<%-- 									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<!-- 									<input type="hidden" name="action" value="logOut"> -->
<!-- 									<input type="submit" value="登出"> -->
<!-- 								</FORM> -->
							</li>
						</ul>
					</div>
				</c:if>
			</div>
		</div>
		<div class="col-xs-12 col-sm-6">
			<c:if test="${account.member_no ==null }">
				<div class="slidename" style="margin-left: 300px">
					<span><img	style="width: 15px; height: 15px; margin-bottom: 3px" src="<%=request.getContextPath()%>/front_end/images/img/people.png"></span>會員註冊
					<div class="slideCharacter" style="display: none">
						<ul>
							<li><a href="<%= request.getContextPath() %>/front_end/member_keyin.jsp" data-toggle="modal">一般會員/註冊</a></li>
							<li><a href="<%= request.getContextPath() %>/front_end/sup_keyin.jsp" data-toggle="modal">廠商會員/註冊</a></li>
						</ul>
					</div>
				</div>
			</c:if>
			<c:if test = "${account!=null}">
				<div id="accountMemberName">
				<img style="width: 15px; height: 15px; margin-bottom: 3px"
					src="<%=request.getContextPath()%>/front_end/images/img/online.png">${account.member_name}</div>
			</c:if>
			<c:if test="${account.member_no ==null }">
				<div id="login">
					<a href="<%=request.getContextPath()%>/login.jsp">登入</a>
				</div>
			</c:if>
		</div>
	</div>
</div>
	<!-- 滾動導覽列結束 -->
	<!-- 導覽列項目 -->
	<div id="nav2">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-sm-offset-3" id="headerMarginLeft"> 
					<div class="col-xs-12 col-sm-2 col-sm-offset-2">
						<div class="container">
							<div class="row">
								<a href="<%=request.getContextPath()%>/front_end/frontIndex.jsp"><img
									src="<%=request.getContextPath()%>/front_end/images/components/icon1.png" /></a>
									
							</div>
						</div>
						<div class="container">
							<div class="row">
								<h5 class="line">
									<a href="<%=request.getContextPath()%>/front_end/frontIndex.jsp">回首頁</a>
								</h5>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-2">
						<div class="container">
							<div class="row">
								<a
									href="<%=request.getContextPath()%>/front_end/article/listAllArticle.jsp"><img
									src="<%=request.getContextPath()%>/front_end/images/components/icon2.png" /></a>
							</div>
						</div>
						<div class="container">
							<div class="row">
								<h5 class="line">
									<a
										href="<%=request.getContextPath()%>/front_end/article/listAllArticle.jsp">討論區</a>
								</h5>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-2">
						<div class="container">
							<div class="row">
								<a
									href="<%=request.getContextPath()%>/front_end/place/place.jsp"><img
									src="<%=request.getContextPath()%>/front_end/images/components/icon3.png"
									alt="Link" /></a>
							</div>
						</div>
						<div class="container">
							<div class="row">
								<h5 class="line">
									<a
										href="<%=request.getContextPath()%>/front_end/place/place.jsp">婚宴場地</a>
								</h5>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-2">
						<div class="container">
							<div class="row">
								<a
									href="<%=request.getContextPath()%>/front_end/shoppingcar/ShoppingPage.jsp"><img
									src="<%=request.getContextPath()%>/front_end/images/components/icon9.png"
									alt="Link" /></a>
							</div>
						</div>
						<div class="container">
							<div class="row">
								<h5 class="line">
									<a
										href="<%=request.getContextPath()%>/front_end/shoppingcar/ShoppingPage.jsp">購物商城</a>
								</h5>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-2">
						<div class="container">
							<div class="row">
								<a
									href="<%=request.getContextPath()%>/front_end/groups/groupsIndex.jsp"><img
									src="<%=request.getContextPath()%>/front_end/images/components/icon5.png"
									alt="Link" /></a>
							</div>
						</div>
						<div class="container">
							<div class="row">
								<h5 class="line">
									<a
										href="<%=request.getContextPath()%>/front_end/groups/groupsIndex.jsp">婚友社群</a>
								</h5>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
        <!-- 導覽列項目結束 -->

	<div id="intro">
		<div id="bgpanel">
			<div class="col-xs-12 col-sm-2"></div>
			<div class="col-xs-12 col-sm-8" >
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
			<b>共<font color=red><%=list.size()%></font>人</b>
			<table width='100%' class="table table-hover table-striped table-bordered table-condensed">
				<tr align='center' valign='middle'>	
					<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/friend/friend.do" name="form1">
						<td>
							<input type="hidden" name="member_no" value="${account.member_no}"/>
							<input id="addFriendText" type="TEXT" name="member_acc" size="45" value="請輸入好友帳號" />
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="action" value="insert"/>
						</td>
						<td>
							<input type="submit" id="addFriendBtn" value="送出新增">
						</td>
					</FORM>
				</tr>
				<tr align='center' valign='middle'>
					<td><strong>好友名稱(帳號)</strong></td>
					<td><strong>移除好友</strong></td>
				</tr>
				<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
				<c:forEach var="friendVO" items="${list}">
					
					<tr align='center' valign='middle'>
						<td>
							<c:forEach var="memberVO" items="${memberSvc.all}">
								<c:if test="${memberVO.member_no == friendVO.friend_no}">
									${memberVO.member_name} (${memberVO.member_acc})
								</c:if>
							</c:forEach>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/friend/friend.do">
								<input type="submit" id="deleteFriendBtn" value="刪除"> <input type="hidden"
									name="member_no" value="${friendVO.member_no}"> <input
									type="hidden" name="friend_no" value="${friendVO.friend_no}">
								<input type="hidden" name="requestURL"
									value="<%=request.getServletPath()%>">
								<input type="hidden" name="action" value="delete">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			</div>
			<div class="col-xs-12 col-sm-2"></div>
		</div>
	</div>
	<!--#intro end-->
		
<!-- webSocket區塊 -->
<div id="websocketArea">
	<%if (session.getAttribute("account")!=null){%>
		    <jsp:include page="/front_end/groups/inviteView.jsp"/>
	<%} %>
</div>
</body>
<script>
$(document).ready(function() {
	$("#addFriendText").click(function(){
		console.log("click");
		$("#addFriendText").val("");
	});
	$("#bgpanel").css('height','auto');
	$("#bgpanel").css('margin-bottom','20px');
})
</script>
</html>

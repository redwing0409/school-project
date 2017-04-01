<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.member.model.*"%>
<%@page import="com.groups.model.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("/front_end/frontIndex.jsp");
%>
<html lang="">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>首頁</title>
	<link rel="stylesheet"	href="<%=request.getContextPath()%>/front_end/css/bootstrap.min.css">
<%-- 	<link rel="stylesheet"	href="<%=request.getContextPath()%>/front_end/article/css/Header.css"> --%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/top_bottom.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/smoothDivScroll.css">
       <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/secondeffect.css">
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->

	
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/jquery.min.js"></script>
	
	
	
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
			
			$("#login").mouseover(function() {
				$(".slideLogin").slideDown(1000);
			});
			$("#login").mouseleave(function() {
				$(".slideLogin").slideUp(1000);
			});
	});
	</script>
	 <!--  Nav toggle effect end-->
<style>
#accountMemberName{
/* 	background-color:yellow; */
	position: fixed;
    margin-top: 1%;
    cursor: pointer;
    z-index: 3;
	float:right;
	z-index:3;
	margin-left: 370px;
}
#login{
/* 	background-color:red; */
	position: fixed;
    margin-top: 1%;
    cursor: pointer;
    z-index: 3;
	float:right;
	z-index:3;
	margin-left: 370px;
}
#headerMarginLeft{ 
 	margin-left:339px; 
}
#topHeaderArea,#topHeaderGroups,#topHeaderGroupsIcon{
	background-color:#e9eaeb;
}
</style>
</head>
<body>
        <!-- 滾動導覽列 -->
<div class="container" >
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
					<span><img	style="width: 15px; height: 15px; margin-bottom: 3px" src="<%=request.getContextPath()%>/front_end/images/img/people.png"></span>註冊
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
					<img style="width: 15px; height: 15px; margin-bottom: 3px" src="<%=request.getContextPath()%>/front_end/images/img/people.png">登入
					<div class="slideLogin" style="display: none">
						<ul>
							<li><a href="<%=request.getContextPath()%>/login.jsp">會員登入</a></li>
							<li><a href="<%=request.getContextPath()%>/supplier_end/suplogin.jsp">廠商登入</a></li>
						</ul>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</div>
	<!-- 滾動導覽列結束 -->
	<!-- 導覽列項目 -->
	<div id="nav2">
		<div class="container" id="topHeaderArea">
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
						<div class="container" >
							<div class="row" id="topHeaderGroupsIcon">
								<a
									href="<%=request.getContextPath()%>/front_end/groups/groupsIndex.jsp"><img
									src="<%=request.getContextPath()%>/front_end/images/components/icon5.png"
									alt="Link" /></a>
							</div>
						</div>
						<div class="container">
							<div class="row" id="topHeaderGroups">
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
			<!--廣告輪播-->
			    <div id="makeMeScrollable">
					<a href="#"><img src="<%=request.getContextPath()%>/front_end/images/pic/p1.jpg"/></a>
					<a href="#"><img src="<%=request.getContextPath()%>/front_end/images/pic/p2.jpg"/></a>
					<a href="#"><img src="<%=request.getContextPath()%>/front_end/images/pic/p3.jpg"/></a>
					<a href="#"><img src="<%=request.getContextPath()%>/front_end/images/pic/p4.jpg"/></a>
					<a href="#"><img src="<%=request.getContextPath()%>/front_end/images/pic/p5.jpg"/></a>
					<a href="#"><img src="<%=request.getContextPath()%>/front_end/images/pic/p6.jpg"/></a>
					<a href="#"><img src="<%=request.getContextPath()%>/front_end/images/pic/p7.jpg"/></a>
					<a href="#"><img src="<%=request.getContextPath()%>/front_end/images/pic/p8.jpg"/></a>
				</div> 
			<!--廣告輪播end-->
			
				<div class="fonteffect">
				  <span><img id="imgfont" src="<%=request.getContextPath()%>/front_end/images/img/diamond.png"></span>
		     		 <h1 class="demofont">Welcome to our website!!</h1>
		     		 <h2 class="demofont">You will find happy marriage!!</h2>
				</div>
			</div>			
		</div> <!--#intro end-->

		<div id="second"><!--#second-->
		    <!--廣告結束-->
		    <div id="bgpanel2">
		      <div class="container">
		    	<div class="row">
		    		<div class="col-xs-12 col-sm-6">
	    				<div id="adall">
				    		<div class="adnav">
						        <ul>
						            <li class="active">廣告1</li>
						            <li>廣告2</li>
						        </ul>
				    		</div>
							<div class="adbanner">
				                   <a href="#"><img src="<%=request.getContextPath()%>/front_end/images/adv/Adv2.jpg"></a>
				                   <a href="#"><img src="<%=request.getContextPath()%>/front_end/images/adv/Adv4.jpg"></a>
							</div>		     
						</div>
		    		</div>
		    		<div class="col-xs-12 col-sm-6">
		    		    <div class="row">
							<div id="discuss">
								<div id="disnav">
									<div id="disblock">廣告1<span id="more"><a href="#">看更多</a></span></div>
								</div>
									 <ul id="listmenu">
										<li class="item"><a href="#">搶攻頂級送禮與自食商機、晶華酒店年菜外帶內容全面升級。</a></li>
										<li class="item"><a href="#">[餐飲]2017金雞啼 慶好年|除夕圍爐和現做外帶年菜。</a></li>
										<li class="item"><a href="#">尾牙春酒特惠專案看過來看過來～ 還沒定好春酒尾牙場地的福委們 快來參考台北彭園的尾牙春酒特惠專案喔!!!…</a></li>
									</ul>
							</div>
						</div>
						<div class="row">
							 <div id="board">
							 	<div id="boardnav">
							 		<div id="wallblock">廣告2<span id="more"><a href="#">看更多</a></span></div>
							 	</div>
	 								<ul id="listmenu">
										<li class="item"><a href="#">精采的生活也要搭配美味佳餚，兆品酒店 台中主廚團隊嚴選頂級食材頂尖廚藝，將當季食材變成一道道創意料理，美食新潮魅力必令人食指大動。無論是粵式料理、西點，大宴小酌為愛好美食的您，滿足著您的胃，也滿足您的心。</a></li>
										<li class="item"><a href="#">除了品味年菜，雅園引領全台首創富貴年夜飯 歡樂除夕總會自93年舉辦以來連續12年佳評如潮，欣賞五光十色精彩表演及享受精緻豪華富貴年夜飯。</a></li>
										<li class="item"><a href="#">2016富苑喜宴會館 | 新年好運到 除夕專案</a></li>
									</ul>
							 </div>
						</div>
		    		</div>
		    	</div>
		    </div> <!--container -->	
		   </div><!--bgpanel2-->
		  
		</div> <!--#second end-->
		<div id="third">
		  <div id="bgpanel3">
			 <div class="float-left">
		        	<div class="container">
						<div class="row">	
							<div class="col-xs-12 col-sm-12 col-sm-offset-1">
			        			<div class="view second-effect">
									<img src="<%=request.getContextPath()%>/DBGif.do?name=10001" />
							        <div class="mask">
										<a href="#" class="info">Read More</a>
									</div>
								</div>
								<div class="view second-effect">
									<img src="<%=request.getContextPath()%>/DBGif.do?name=10002" />
									<div class="mask">
										<a href="#" class="info">Read More</a>
									</div>
								</div>	
								<div class="view second-effect">
									<img src="<%=request.getContextPath()%>/DBGif.do?name=10003" />
									<div class="mask">
										<a href="#" class="info">Read More</a>
									</div>
								</div>
		        			</div>
			        	</div>
			        	<div class="row">	
							<div class="col-xs-12 col-sm-12 col-sm-offset-1">
			        			<div class="view second-effect">
									<img src="<%=request.getContextPath()%>/DBGif.do?name=10004" />
							        <div class="mask">
										<a href="#" class="info">Read More</a>
									</div>
								</div>
								<div class="view second-effect">
									<img src="<%=request.getContextPath()%>/DBGif.do?name=10005" />
									<div class="mask">
										<a href="#" class="info">Read More</a>
									</div>
								</div>	
								<div class="view second-effect">
									<img src="<%=request.getContextPath()%>/DBGif.do?name=10006" />
									<div class="mask">
										<a href="#" class="info">Read More</a>
									</div>
								</div>
		        			</div>
			        	</div>
		        	</div><!--container end-->
		        </div>
			 </div><!--bgpanel2 end-->	
		   </div><!--#third end-->
		
		

<!-- 廠商會員註冊跳出頁面 -->
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/sup/sup.do" name="form1" enctype="multipart/form-data" id="regist2">
	<div class="modal fade" id="registered2">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<img src="<%=request.getContextPath()%>/front_end/images/X_mark.png">
					</button>
					<h2 class="modal-title">廠商資料輸入</h2>
				</div>
				<div class="modal-body place">
					<div class="col-xs-12 col-sm-6">
					    <div class="input-group">
							<div class="input-group-addon">
								廠商帳號
							</div>
							<input type="text" name="sup_acct" id="sup_acct" class="form-control">							
						</div>
						<br>
						<div class="input-group">
							<div class="input-group-addon">
								廠商密碼
							</div>
							<input type="password" name="sup_pwd" id="sup_pwd" class="form-control">							
						</div>						
						<br>
						<div class="input-group">
							<div class="input-group-addon">
								廠商名稱
							</div>
							<input type="text" name="sup_name" id="sup_name" class="form-control">							
						</div>
						<br>
						<div class="input-group">
							<div class="input-group-addon">
								郵遞區號
							</div>
							<input type="number" name="sup_adcode" id="sup_adcode" class="form-control">							
						</div>				
						<br>
						<div class="input-group">
							<div class="input-group-addon">
								廠商地址
							</div>
							<input type="text" name="sup_addr" id="sup_addr" class="form-control">							
						</div>	
						<br>
						<div class="input-group">
							<div class="input-group-addon">
								接洽窗口
							</div>
							<input type="text" name="sup_con" id="sup_con" class="form-control">							
						</div>												
						<br>
						<div class="input-group">
							<div class="input-group-addon">
								廠商統編
							</div>
							<input type="number" name="sup_id" id="sup_id" class="form-control">							
						</div>													
					</div>
	
					<div class="col-xs-12 col-sm-6">
						
						<div class="input-group">
							<div class="input-group-addon">
								電話區碼
							</div>
							<input type="number" name="sup_telcode" id="sup_telcode" class="form-control">							
						</div>		
						<br>
						<div class="input-group">
							<div class="input-group-addon">
								公司電話
							</div>
							<input type="number" name="sup_tel" id="sup_tel" class="form-control">							
						</div>
						<br>
						<div class="input-group">
							<div class="input-group-addon">
								傳真電話
							</div>
							<input type="number" name="sup_tax" id="sup_tax" class="form-control">							
						</div>	
						<br>
						<div class="input-group">
							<div class="input-group-addon">
								廠商類別
							</div>
							<select name="sup_tape" id="sup_tape" class="form-control">								  
								  <option value="C">周邊物品</option>
								  <option value="H">會館</option>								  	
							</select> 						
						</div>
						<br>
							<input type="hidden" name="sup_note" id="sup_note" class="form-control" value="0">							
					</div>
				</div>
				<div class="modal-footer">
				    <input type="hidden" name="action" value="insert">
					<button type="button" class="btn btn-success"  data-toggle="modal" onclick="submit();">送出</button>
					<script> function submit(){$("#regist2").submit();}</script>
				</div>
			</div>
		</div>
	</div>
	
</FORM>	

<!-- 修改會員資料開始 -->
<div class="modal fade" id="updateMember">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">
				<img src="<%=request.getContextPath()%>/front_end/images/X_mark.png">
			</button>
			<h2 class="modal-title">修改會員資料</h2>
			</div>
		<div class="modal-body place">
		<c:if test="${not empty errorMsgsFromUpdate}">
			<div id="errorMsgsArea3">
				<font color='red'>請修正以下錯誤:
					<ul>
						<c:forEach var="message" items="${errorMsgsFromUpdate}">
							<li>${message}</li>
						</c:forEach>
					</ul>
				</font>
			</div>
		</c:if>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/memberServlet.do" name="form1" enctype="multipart/form-data" id="updateMemberData">
			<div class="col-xs-12 col-sm-6">
				<div class="input-group">
					<div class="input-group-addon">
						密碼
					</div>
					<input type="password" name="member_pw" id="member_pw" class="form-control" value="${account.member_pw}">							
				</div>						
				<br>
				<div class="input-group">
					<div class="input-group-addon">
						地址
					</div>
					<input type="text" name="member_addr" id="member_addr" class="form-control" value="${account.member_addr}">							
				</div>	
				<br>
				<div class="input-group">
					<div class="input-group-addon">
						行動電話
					</div>
					<input type="number" name="member_mobile" id="member_mobile" class="form-control" maxlength="10" value="${account.member_mobile}" >							
				</div>	
				<br>
			</div>
		
			<div class="col-xs-12 col-sm-6">
				 <div>
				 <img src="<%= request.getContextPath() %>/member/ShowMember_Pic.do?member_no=${account.member_no}" width="20%"/>
				 <img id="image2" style="width: 200px; height: 200px">	
				 <input type="file" id="theFile2" name="member_pic" size="45"/>	
				 </div>				 
			</div>
		</div>
			<input type="hidden" name="member_no" value="${account.member_no}">
			<input type="hidden" name="member_acc" value="${account.member_acc}">
			<input type="hidden" name="member_name" value="${account.member_name}">
			<input type="hidden" name="member_email" value="${account.member_email}">
			<input type="hidden" name="member_birthday" value="${account.member_birthday}">
			<input type="hidden" name="enroll_time" value="${account.enroll_time}">
			<input type="hidden" name="member_sex" value="${account.member_sex}">
		    <input type="hidden" name="action" value="update">
		    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			<button type="button" class="btn btn-success"  data-toggle="modal" onclick="submit();">送出</button>
			<script> function submit(){$("#updateMemberData").submit();}</script>
		</FORM>
		</div>
	</div>
</div>
<!-- 修改會員資料結束 -->
<!-- 聊天好友區塊 -->
<div id="messagesAndFriendArea">
	<%if (session.getAttribute("account")!=null){%>
		    <jsp:include page="/front_end/messages/messagesFriendList.jsp"/>
	<%} %>
</div>
<!-- webSocket區塊 -->
<div id="websocketArea">
	<%if (session.getAttribute("account")!=null){%>
		    <jsp:include page="/front_end/groups/inviteView.jsp"/>
	<%} %>
</div>
</body>
</html>

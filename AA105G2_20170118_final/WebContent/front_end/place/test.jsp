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

	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>首頁</title>
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/top_bottom.css">
		<link rel="stylesheet" href="css/main.css">
		<link rel="stylesheet" href="css/smoothDivScroll.css">
        <link rel="stylesheet" href="css/secondeffect.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.scrollTo-1.4.2-min.js"></script>
		<script type="text/javascript" src="js/jquery.parallax-1.1.3.js"></script>
		<script type="text/javascript" src="js/jquery.localscroll-1.2.7-min.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js" ></script>
		<script type="text/javascript" src="js/jquery.mousewheel.min.js" ></script>
		<script type="text/javascript" src="js/jquery.kinetic.min.js"></script>
	    <script type="text/javascript" src="js/jquery.smoothdivscroll-1.3-min.js"></script>
	    <script type="text/javascript" src="js/jquery.flipping_text.js"></script>
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
		    $('.slidemenu').mouseover( function(){
		    	$('.slidesub').stop(true,false).slideDown();
		    }); 
		    $('.slidemenu').mouseleave( function(){
		    	$('.slidesub').stop(true,false).slideUp();
		    }); 
		});
		</script>
		 <!--  Nav toggle effect end-->
	</head>
	<body>
        <!-- 滾動導覽列 -->
		<ul id="nav">
			<li><a href="#intro" title="Next Section"><img src="images/img/love.png" style="width:15px;height:15px"/></a></li>
		    <li><a href="#second" title="Next Section"><img src="images/img/love.png" style="width:15px;height:15px"/></a></li>
		    <li><a href="#third" title="Next Section"><img src="images/img/love.png" style="width:15px;height:15px"/></a></li>
		</ul>
		<div class="container">
			<div class="row">
			 	<div class="col-xs-12 col-sm-6">	
					 <div class="slidemenu" style="margin-left:5px">	
					        <div class="slidemain"><span><img style="width:15px;height:15px;margin-bottom:3px" src="images/img/setting.png"></span>我的管理</div>
					        <div class="slidesub" style="display:none">
					            <ul>
					                <li><a href="#">修改基本資料</a></li>
					                <li><a href="#">管理好友</a></li>
					                <li><a href="#">瀏覽歷史清單</a></li>
					                <li><a href="#">即時訊息</a></li>
					                <li><a href="#">登出</a></li>
					            </ul> 
				   			</div>
			    	 </div> 
    			</div>
				<div class="col-xs-12 col-sm-6">
					<div class="slidename" style="margin-left:300px"><span><img style="width:15px;height:15px;margin-bottom:3px" src="images/img/people.png"></span><a href="#">登入/註冊</a></div>
			     		<div class="slidename" style="margin-left:400px"><span><img style="width:15px;height:15px;margin-bottom:3px" src="images/img/online.png"></span>AA105您好</div>
				</div>	 
			</div>
		</div>
		<!-- 滾動導覽列結束 -->
        <!-- 導覽列項目 -->
		<div id="nav2">
		    <div class="container">
		    	<div class="row">
		    		<div class="col-xs-12 col-sm-6 col-sm-offset-3">
		    	<!-- 		<div class="col-xs-12 col-sm-2 col-sm-offset-2">
		    				 <div class="container">
		    				 	<div class="row">
		    				 		<a href="#"><img src="images/components/icon1.png"/></a>
		    				 	</div>
		    				 </div>
		    				 <div class="container">
		    				 	<div class="row">
		    				 		<h5 class="line"><a href="#">最新消息</a></h5>
		    				 	</div>
		    				 </div>
		    			</div> -->
		    			<div class="col-xs-12 col-sm-2 col-sm-offset-4">
		    				<div class="container">
		    				 	<div class="row">
		    				 		<a href="Discuss.html"><img src="images/components/icon2.png"/></a>
		    				 	</div>
		    				 </div>
		    				 <div class="container">
		    				 	<div class="row">
		    				 		<h5 class="line"><a href="Discuss.html">討論區</a></h5>
		    				 	</div>
		    				 </div>
		    			</div>
		    			<div class="col-xs-12 col-sm-2">
		    				<div class="container">
		    					<div class="row">
		    						<a href="#" ><img src="images/components/icon3.png" alt="Link" /></a>
		    					</div>
		    				</div>
		    				<div class="container">
		    					<div class="row">
		    						<h5 class="line"><a href="Places.html">婚宴場地</a></h5>
		    					</div>
		    				</div>
		    			</div>
		    			<div class="col-xs-12 col-sm-2">
		    				<div class="container">
		    					<div class="row">
		    						<a href="ShoppingPage.html" ><img src="images/components/icon9.png" alt="Link"/></a>
		    					</div>
		    				</div>
		    				<div class="container">
		    					<div class="row">
		    						<h5 class="line"><a href="ShoppingPage.html">購物商城</a></h5>
		    					</div>
		    				</div>
		    			</div>
		    			<div class="col-xs-12 col-sm-2">
		    				<div class="container">
		    					<div class="row">
		    						<a href="#" ><img src="images/components/icon5.png" alt="Link" /></a>
		    					</div>
		    				</div>
		    				<div class="container">
		    					<div class="row">
		    						<h5 class="line"><a href="Groups.html">婚友社群</a></h5>
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
					<a href="#"><img src="images/pic/p1.jpg"/></a>
					<a href="#"><img src="images/pic/p2.jpg"/></a>
					<a href="#"><img src="images/pic/p3.jpg"/></a>
					<a href="#"><img src="images/pic/p4.jpg"/></a>
					<a href="#"><img src="images/pic/p5.jpg"/></a>
					<a href="#"><img src="images/pic/p6.jpg"/></a>
					<a href="#"><img src="images/pic/p7.jpg"/></a>
					<a href="#"><img src="images/pic/p8.jpg"/></a>
				</div> 
			<!--廣告輪播end-->
			
				<div class="fonteffect">
				  <span><img id="imgfont" src="images/img/diamond.png"></span>
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
				                   <a href="#"><img src="images/adv/p2.jpg"></a>
				                   <a href="#"><img src="images/adv/p3.jpg"></a>
							</div>		     
						</div>
		    		</div>
		    		<div class="col-xs-12 col-sm-6">
		    		    <div class="row">
							<div id="discuss">
								<div id="disnav">
									<div id="disblock">討論區<span id="more"><a href="#">看更多</a></span></div>
								</div>
									 <ul id="listmenu">
										<li class="item"><a href="#">[DIY]夢幻婚鞋不求人。詢問度爆表的自製手</a></li><div class="username"><p>aqua901sdfwsf2qwer23efewqfqergfergfqerf</p></div>
										<li class="item"><a href="#">[籌備][Wedding ♥Love] 無敵詳細的夢幻2222222222222222222222222222222222222222222222222</a></li><div class="username"><p>teppi0318</p></div>
										<li class="item"><a href="#">[籌備]設計人辦婚禮超專業222ssssssssssssssssssssss222222222222222222222222222222222222222222222222！五步驟。</a></li><div class="username"><p>dfabg62asdvfdasf213rfrqewgfasdfqadeasdfqe</p></div>
									</ul>
							</div>
						</div>
						<div class="row">
							 <div id="board">
							 	<div id="boardnav">
							 		<div id="wallblock">塗鴉牆<span id="more"><a href="#">看更多</a></span></div>
							 	</div>
	 								<ul id="listmenu">
										<li class="item"><a href="#">[DIY]夢幻婚鞋不求人。詢問度爆表的自製手</a></li><div class="username"><p>aqua901sdfwsf2qwer23efewqfqergfergfqerf</p></div>
										<li class="item"><a href="#">[籌備][Wedding ♥Love] 無敵詳細的夢幻2222222222222222222222222222222222222222222222222</a></li><div class="username"><p>teppi0318</p></div>
										<li class="item"><a href="#">[籌備]設計人辦婚禮超專業222ssssssssssssssssssssss222222222222222222222222222222222222222222222222！五步驟。</a></li><div class="username"><p>dfabg62asdvfdasf213rfrqewgfasdfqadeasdfqe</p></div>
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
									<img src="images/homegoods/p1.jpg" />
							        <div class="mask">
										<a href="#" class="info">Read More</a>
									</div>
								</div>
								<div class="view second-effect">
									<img src="images/homegoods/p2.jpg" />
									<div class="mask">
										<a href="#" class="info">Read More</a>
									</div>
								</div>	
								<div class="view second-effect">
									<img src="images/homegoods/p3.jpg" />
									<div class="mask">
										<a href="#" class="info">Read More</a>
									</div>
								</div>
		        			</div>
			        	</div>
			        	<div class="row">	
							<div class="col-xs-12 col-sm-12 col-sm-offset-1">
			        			<div class="view second-effect">
									<img src="images/homegoods/p4.jpg" />
							        <div class="mask">
										<a href="#" class="info">Read More</a>
									</div>
								</div>
								<div class="view second-effect">
									<img src="images/homegoods/p5.jpg" />
									<div class="mask">
										<a href="#" class="info">Read More</a>
									</div>
								</div>	
								<div class="view second-effect">
									<img src="images/homegoods/p6.jpg" />
									<div class="mask">
										<a href="#" class="info">Read More</a>
									</div>
								</div>
		        			</div>
			        	</div>
		        	</div><!--container end-->
		        </div>
			 </div><!--bgpanel2 end-->	
		   </div>
		</div> <!--#third end-->
	</body>
</html>

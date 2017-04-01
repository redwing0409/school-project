<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.response.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>


<%
	ArticleService articleSvc = new ArticleService();
	MemberService memSvc = new MemberService();
	ArticleVO articleVO = articleSvc.getOneArticle(request.getParameter("article_no"));
	List<ResponseVO> list = articleSvc.getAllResponse(request.getParameter("article_no"));
	pageContext.setAttribute("articleVO", articleVO);
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("memSvc", memSvc);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/css/DiscussPage.css">
<style>
h1{
	background-color:#ff60af;
	text-align: center;
	font-size:30px !important;
	color:white;
}
.buttonDel {
	display: none
}
#showName{
	background-color: #DDDDDD;
}
.articleTitle{
	float:left;
}
#float{
	clear:both;
}
#title{
	margin-top: 10px;
	margin-left: 10px;
	font-style:tohoma;
	font-weight:bolder;
}
#addView{
	text-align:right;
	vertical-align: bottom;
}
.articleContent,#responseContent{
	margin-top: 10px;
	margin-left: 10px;
	font-size:30px;
}
hr.style-four {
    height: 12px;
    border: 0;
    box-shadow: inset 0 12px 12px -12px rgba(0, 0, 0, 0.5);
}
.response{
	float:left;
}
#responsefloat{
	clear:both;
}
.deleteArticleBtn{
	text-align:right;
}
hr.style-seven {
    height: 30px;
    border-style: solid;
    border-color: black;
    border-width: 1px 0 0 0;
    border-radius: 20px;
}
hr.style-seven:before { /* Not really supposed to work, but does */
    display: block;
    content: "";
    height: 30px;
    margin-top: -31px;
    border-style: solid;
    border-color: black;
    border-width: 0 0 1px 0;
    border-radius: 20px;
}
#deleteDiv{
	width: 800px;
	border-color: black;
}
#viewTime{
	color:red;
}



</style>
<script
	src="<%=request.getContextPath()%>/front_end/article/js/ckeditor/ckeditor.js"></script>
<script>
	function ckedit(count){
		document.getElementById("submit"+count).style.display = "block";
		document.getElementById("submit"+count).disabled = "";
		document.getElementById("edit"+count).style.display = "none";
		CKEDITOR.replace( "editText"+count, {width:650});
		//document.getElementById("modify"+count).style.display = "none";
	}
	function ckeditArticle(){

		document.getElementById("submit").style.display = "block";
		document.getElementById("submit").disabled = "";
		document.getElementById("edit").style.display = "none";
		CKEDITOR.replace( "editText", {width:750});
		document.getElementById("modify").style.display = "none";
	}	
	
</script>

<title>文章- listArticleContent.jsp</title>

</head>
<body>
	<jsp:include page="/front_end/article/articleFile.jsp" />
	<div id="intro">
		<!--#intro -->
		<!-- 這邊是大家頁面內容 -->
		<div id="bgpanel">
			<div class="col-sm-2" id="select">
				<jsp:include page="/front_end/article/select_page.jsp" />
			</div>
			<div class="col-sm-10" id="content">
				

				<form METHOD="post"
					ACTION="<%=request.getContextPath()%>/article/article.do">
					<h1>討論區文章</h1>
					<table border='0'  width='800' >
						
						
						<tr>
								<td>
									<div id="showImage" class="articleTitle">
										<img id="memberImage" src="<%= request.getContextPath() %>/member/ShowMember_Pic.do?member_no=${articleVO.member_no}" height="100" width="100">
									</div>
									<div class="articleTitle">
										<div id="showName" style="width:680px;">
											<c:forEach var="memberVO" items="${memSvc.all}">
	                    						<c:if test="${articleVO.member_no==memberVO.member_no}">
		                   						 ${memberVO.member_acc}-${memberVO.member_name}
	                    						</c:if>
	                						</c:forEach>
	                						<br>
	                						<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((ArticleVO)pageContext.getAttribute("articleVO")).getArticle_time()) %>
										</div>
										<div id="title">
											${articleVO.article_title}
										</div>
									<div>
									<div id="float"></div>
								</td>
						</tr>
						<tr>
							<td>
							
								<hr id="divide" class="style-four">
									<c:if test="${account.member_no == articleVO.member_no}" var="condition" scope="page">	
										<button type="button" id="edit" class="button btn btn-info" onclick="ckeditArticle()">編輯</button>
										<input type="submit" value="送出修改" id="submit" class="buttonDel" disabled=true>
										<input type="hidden" name="article_no" value="${articleVO.article_no}"> 
										<input type="hidden" name="member_no" value="${articleVO.member_no}">
										<input type="hidden" name="article_title" value="${articleVO.article_title}">
										<input type="hidden" name="article_views" value="${articleVO.article_views}">
										<input type="hidden" name="article_time" value="${articleVO.article_time}">
										<input type="hidden" name="article_status" value="${articleVO.article_status}">
										<input type="hidden" name="action" value="update">
									</c:if>
								<div id="modify" class="articleContent">
									${articleVO.article_content}
								</div>
								<div>
									<TEXTAREA id="editText" name="modify_content"
										style="display: none">${articleVO.article_content}</TEXTAREA>
								</div>
								<div id="addView">會員瀏覽次數:<span id="viewTime">${articleVO.article_views}</span></div>
								<hr id="divide" class="style-four">
							</td>
						</tr>

					</table>
				</form>
				
				
				
				<h1>文章回應</h1>

				<c:forEach var="responseVO" varStatus="status" items="${list}">
					<hr class="style-seven">
					<c:if test="${account.member_no == responseVO.member_no}" var="condition" scope="page">
						<div id="deleteDiv">
							<form METHOD="post" ACTION="<%=request.getContextPath()%>/response/response.do">
								<input type="button" class="btn btn-important deleteArticleBtn" value="刪除"> 
								<input type="hidden" name="response_no" value="${responseVO.response_no}"> 
								<input type="hidden" name="article_no" value="${articleVO.article_no}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="action" value="delete">
							</form>
						<div>
					</c:if>
					<form METHOD="post" ACTION="<%=request.getContextPath()%>/response/response.do">
						<table>
							<tr>
								<td>
									<div id="responseMember" class="response">
										<img id="memberImage" src="<%= request.getContextPath() %>/member/ShowMember_Pic.do?member_no=${responseVO.member_no}" height="100" width="100">
										<div id="memberName">
											<c:forEach var="memberVO" items="${memSvc.all}">
	                    						<c:if test="${responseVO.member_no==memberVO.member_no}">
		                   						 ${memberVO.member_acc}-${memberVO.member_name}
	                    						</c:if>
	                						</c:forEach>									
										</div>
										<div id="responseTime">
											<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((ResponseVO)pageContext.getAttribute("responseVO")).getResponse_time()) %>
										</div>
									</div>
									<div id="responseContent" class="response">
										<div id="modify${status.count}">
											${responseVO.response_content}
											<c:if test="${account.member_no == responseVO.member_no}" var="condition" scope="page">
													<button type="button" id="edit${status.count}" class="btn btn-info" onclick="ckedit(${status.count})">編輯</button> 
													<input type="submit" value="送出修改" id="submit${status.count}" class="buttonDel" disabled=true> 
													<input type="hidden" name="response_no" value="${responseVO.response_no}">
													<input type="hidden" name="article_no" value="${articleVO.article_no}"> 
													<input type="hidden" name="member_no" value="${account.member_no}"> 
													<input type="hidden" name="response_time" value="<%=new Timestamp(System.currentTimeMillis())%>">
													<input type="hidden" name="action" value="update">
											</c:if>
										</div>
										<div>
											<form METHOD="post" ACTION="<%=request.getContextPath()%>/response/response.do" name="form1">
												<TEXTAREA id="editText${status.count}" name="modify_content" style="display: none">${responseVO.response_content}</TEXTAREA>
											</form>
										</div>	
									</div>
									<div id="responsefloat">
									</div>
								</td>
							</tr>
						</table>
					</form>
				</c:forEach>

<c:if test="${account!=null}">

				<%	ResponseVO responseVO = (ResponseVO) request.getAttribute("ResponseVO");%>
				<h1>回復文章</h1>
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
				<Form METHOD="post"
					ACTION="<%=request.getContextPath()%>/response/response.do"
					name="form1">
					<TEXTAREA rows="4" cols="50" name="response_content" id="response_content"><%=(responseVO == null) ? "輸入你想回復的內容" : responseVO.getResponse_content()%></TEXTAREA>
					<script>
 						CKEDITOR.replace( 'response_content', {width:800});
 					</script> 

					<input type="hidden" name="article_no" value="${articleVO.article_no}"> 
					<input type="hidden" name="member_no" value="${account.member_no}"> 
					<input type="hidden" name="response_time" value="<%=new Timestamp(System.currentTimeMillis())%>">
					<input type="hidden" name="action" value="insert"> 
					<button type="button" class="btn btn-info" id="addArticle">送出</button>
				</Form>
</c:if>
				<%	
					Boolean bol=false;
					List<String> articleList=null;
					articleList=(List<String>)session.getAttribute("addViews");
					System.out.println("articleList="+articleList);
					System.out.println("文章編號"+articleVO.getArticle_no());
					if(articleList!=null&&!articleList.contains(articleVO.getArticle_no())){
						bol=true;
						articleList.add(articleVO.getArticle_no());
					}
					if (bol) {
						Integer views = articleVO.getArticle_views();
						articleSvc.updateArticle(articleVO.getArticle_no(), articleVO.getMember_no(),
								articleVO.getArticle_title(), articleVO.getArticle_content(), ++views,
								articleVO.getArticle_time(), articleVO.getArticle_status());
						session.setAttribute("addViews", articleList);
					}
				%>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$("#bgpanel").css('height','auto');
	$("#bgpanel").css('margin-bottom','20px');
});
$(".deleteArticleBtn").click(function(){
	if(confirm("確定要刪除?")){
		$(this).parent().submit();
	};
});
$("#addArticle").click(function(){
		$(this).parent().submit();
});

</script>
</html>

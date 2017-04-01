<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.member.model.*"%>
<%
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/css/DiscussPage.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/article/css/addArticle.css">	
<script src="<%=request.getContextPath()%>/front_end/article/js/ckeditor/ckeditor.js"></script>


<title>討論區文章新增 - addArticle.jsp</title>
<style>
table{
	margin-top:10px;
}


</style>
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
				<div id="addArticle">新增文章</div> 
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

				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/article/article.do"
					name="form1">
					<table border="0">
						<tr>
							<td colspan="2">
								<div class="input-group">
									<div class="input-group-addon">
										文章標題
									</div>
									<input type="TEXT" name="article_title" id="article_title" class="form-control" value="<%=(articleVO == null) ? "文章標題" : articleVO.getArticle_title()%>">	
								</div>
							</td>
<!-- 							<td><input type="TEXT" name="article_title" size="45" -->
<%-- 								value="<%=(articleVO == null) ? "文章標題" : articleVO.getArticle_title()%>" /></td> --%>
						</tr>
						<tr>
							<td>文章內容:</td>
							<td>
								<TEXTAREA id="article_content" name="article_content"><%=(articleVO == null) ? "文章內容" : articleVO.getArticle_content()%></TEXTAREA>
								<script>
									CKEDITOR.replace( "article_content", {width:500});
								</script>							
							</td>
						</tr>

						<input type="hidden" name="article_time"
							value="<%=new Timestamp(System.currentTimeMillis())%>">
						<input type="hidden" name="article_views" value="0">
						<input type="hidden" name="article_status" value="1">
						<input type="hidden" name="member_no" value="${account.member_no}">
						<!-- 從登入帳號取得 -->
						<input type="hidden" name="whichPage" value="1">




					</table>
					<br> <input type="hidden" name="action" value="insert">
					<input type="submit" value="發表文章" class="">
				</FORM>
			</div>
		</div>
	</div>
</body>
<script>
$("#article_title").click(function(){
	$("#article_title").val("");
});
</script>
</html>

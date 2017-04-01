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


<title>�Q�װϤ峹�s�W - addArticle.jsp</title>
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
		<!-- �o��O�j�a�������e -->
		<div id="bgpanel">
			<div class="col-sm-2" id="select">
				<jsp:include page="/front_end/article/select_page.jsp" />
			</div>
			<div class="col-sm-10" id="content">
				<div id="addArticle">�s�W�峹</div> 
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

				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/article/article.do"
					name="form1">
					<table border="0">
						<tr>
							<td colspan="2">
								<div class="input-group">
									<div class="input-group-addon">
										�峹���D
									</div>
									<input type="TEXT" name="article_title" id="article_title" class="form-control" value="<%=(articleVO == null) ? "�峹���D" : articleVO.getArticle_title()%>">	
								</div>
							</td>
<!-- 							<td><input type="TEXT" name="article_title" size="45" -->
<%-- 								value="<%=(articleVO == null) ? "�峹���D" : articleVO.getArticle_title()%>" /></td> --%>
						</tr>
						<tr>
							<td>�峹���e:</td>
							<td>
								<TEXTAREA id="article_content" name="article_content"><%=(articleVO == null) ? "�峹���e" : articleVO.getArticle_content()%></TEXTAREA>
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
						<!-- �q�n�J�b�����o -->
						<input type="hidden" name="whichPage" value="1">




					</table>
					<br> <input type="hidden" name="action" value="insert">
					<input type="submit" value="�o��峹" class="">
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

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.response.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MemberVO memberVO = (MemberVO) session.getAttribute("account");
	ResponseService responseSvc = new ResponseService();
	Set<String> set = responseSvc.getOwnResponse(memberVO.getMember_no());
	List<ArticleVO> list = new ArrayList<ArticleVO>();
	for (ArticleVO articleVO : new ArticleService().getAllFront()) {
		for (String article_no : set) {
			if (articleVO.getArticle_no().equals(article_no)) {
				list.add(articleVO);
			}
		}
	}
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/article/css/listAllArticle.css">
<title>曾回覆過文章列表 - listOwnResponse.jsp</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/css/DiscussPage.css">

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

				<div id="allArticle">討論區文章列表</div> 

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

				<table border='1'  class="table table-hover table-striped table-bordered table-condensed" color="#ff60af">
					<thead>
					<tr>
						<th style="text-align:center">文章編號</th>
						<th style="text-align:center">發表會員</th>
						<th width="300px" style="text-align:center">文章標題</th>
						<!-- 		<th>文章內容</th> -->
						<th style="text-align:center">會員瀏覽次數</th>
						<th style="text-align:center">發表時間</th>


					</tr>
					</thead>
					<%@ include file="page1.file"%>
					<c:forEach var="articleVO" items="${list}" varStatus="s"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tbody>
							<tr align='center' valign='middle' ${(articleVO.article_no==param.article_no) ? 'bgcolor=#CCCCFF':''} style="font-weight:bold">
								<td>
									<c:if test="${articleVO.member_no==account.member_no}">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do">
											<input type="submit" value="刪除"> <input type="hidden" name="article_no" value="${articleVO.article_no}">
											 <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											<input type="hidden" name="whichPage" value="<%=whichPage%>">
											<input type="hidden" name="action" value="delete">
										</FORM>
									</c:if>
									${articleVO.article_no}
								</td>
								<td>
								<c:forEach var="memberVO" items="${memberSvc.all}">
	                    			<c:if test="${articleVO.member_no==memberVO.member_no}">
		                    			${memberVO.member_name}
		                    			<br>
	<%-- 	                    			<img src="<%= request.getContextPath() %>/member/ShowMember_Pic.do?member_no=${memberVO.member_no}" height="80" width="80"/> --%>
	                    			</c:if>
	                			</c:forEach>
								</td>
								<td><a
									href="<%=request.getContextPath()%>/front_end/article/listArticleContent.jsp?article_no=${articleVO.article_no}&whichPage=${param.whichPage}" style="color:red">${articleVO.article_title}</href></td>
								<td>${articleVO.article_views}</td>
								<td><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((ArticleVO)pageContext.getAttribute("articleVO")).getArticle_time()) %></td>
	
	
	<!-- 							<td> -->
	<!-- 								<FORM METHOD="post" -->
	<%-- 									ACTION="<%=request.getContextPath()%>/article/article.do"> --%>
	<!-- 									<input type="submit" value="修改"> <input type="hidden" -->
	<%-- 										name="article_no" value="${articleVO.article_no}"> <input --%>
	<!-- 										type="hidden" name="requestURL" -->
	<%-- 										value="<%=request.getServletPath()%>"> --%>
	<!-- 									送出本網頁的路徑給Controller -->
	<%-- 									<input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
	<!-- 									送出當前是第幾頁給Controller -->
	<!-- 									<input type="hidden" name="action" value="getOne_For_Update"> -->
	<!-- 								</FORM> -->
	
	<!-- 							</td> -->
	<!-- 							<td> -->
<!-- 									<FORM METHOD="post" -->
<%-- 										ACTION="<%=request.getContextPath()%>/article/article.do"> --%>
<!-- 										<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 											name="article_no" value="${articleVO.article_no}"> <input --%>
<!-- 											type="hidden" name="requestURL" -->
<%-- 											value="<%=request.getServletPath()%>"> --%>
<!-- 										送出本網頁的路徑給Controller -->
<%-- 										<input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 										送出當前是第幾頁給Controller -->
<!-- 										<input type="hidden" name="action" value="delete"> -->
<!-- 									</FORM> -->
	<!-- 							</td> -->
							</tr>
						</tbody>
					</c:forEach>
				</table>
				<%@ include file="page2.file"%>
			</div>
		</div>
	</div>
	<div id="websocketArea">
	</div>
</body>
</html>

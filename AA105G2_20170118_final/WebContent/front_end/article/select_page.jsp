<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>IBM Article: Home</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/article/css/select_page.css">
<script language="JavaScript" src="<%=request.getContextPath()%>/front_end/article/js/select_page.js"></script>
</head>
<body>




	<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
	<div class="panel panel-default">
		<div class="panel-heading" role="tab" id="panel1">
			<h4 class="panel-title">
				<a href="<%=request.getContextPath()%>/front_end/article/listAllArticle.jsp" role="button" class="collapsed" aria-expanded="false"
					aria-controls="aaa">所有文章</a>
			</h4>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading" role="tab" id="panel1">
			<h4 class="panel-title">
				<a href="#aaa" data-parent="#accordion1" data-toggle="collapse"
					role="button" class="collapsed" aria-expanded="false"
					aria-controls="aaa">找文章</a>
			</h4>
<%-- 			<%-- 錯誤表列 --%> 
<%-- 			<c:if test="${not empty errorMsgs}"> --%>
<!-- 				<font color='red'>請修正以下錯誤: -->
<!-- 					<ul> -->
<%-- 						<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 							<li>${message}</li> --%>
<%-- 						</c:forEach> --%>
<!-- 					</ul> -->
<!-- 				</font> -->
<%-- 			</c:if> --%>
		</div>
		<div id="aaa" class="panel-collapse collapse" role="tabpanel"
			aria-labelledby="panel2">
			<div class="panel-body">
				<ul>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/article/article.do"
						name="form1" id="form1">

						<li><b class="b">標題關鍵字:</b></li> <input type="text"
							name="article_title" value="" size="7" class="form-control" style="height:30px;"><br>

						<li><b class="b">發表人帳號:</b></li> <input type="text"
							name="member_acc" value="" size="7" class="form-control" style="height:30px;"><br>

						<li><b class="b">內容關鍵字:</b></li> <input type="text"
							name="article_content" value="" size="7" class="form-control" style="height:30px;"><br>

						<li><b class="b">點閱率大於多少:</b></li> 
							<input type="number" name="article_views"  value="" min="0" class="form-control" style="height:30px;">
							<br>
						<div class="btn btn-primary" onclick="submit();">送出</div>
						<input type="hidden" name="action"
							value="listArticle_ByCompositeQuery">
					</FORM>
				</ul>

			</div>
		</div>
	</div>



	<div class="panel panel-default">
		<div class="panel-heading" role="tab" id="panel2">
			<h4 class="panel-title">
				<a href="#bbb" data-parent="#accordion2" data-toggle="collapse"
					role="button" class="collapsed" aria-expanded="false"
					aria-controls="bbb">文章管理</a>
			</h4>
		</div>
		<div id="bbb" class="panel-collapse collapse" role="tabpanel"
			aria-labelledby="panel2">
			<div class="panel-body">
				<ul>
					<li><a href='<%=request.getContextPath()%>/front_end/article/addArticle.jsp' style="color:black"><b class="b">發表文章</b></a></li>

					<li><a
						href='<%=request.getContextPath()%>/front_end/article/listOwnArticle.jsp' style="color:black"><b
							class="b">查詢自己發表過的文章:</b></a></li>

					<li><a
						href='<%=request.getContextPath()%>/front_end/article/listOwnResponse.jsp' style="color:black"><b
							class="b">查詢自己曾回覆過的文章:</b></a></li>
				</ul>
			</div>
		</div>
	</div>
		
</body>

</html>

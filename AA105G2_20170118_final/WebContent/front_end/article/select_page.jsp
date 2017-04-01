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




	<%-- �U�νƦX�d��-�H�U���-�i�H�N�W�� --%>
	<div class="panel panel-default">
		<div class="panel-heading" role="tab" id="panel1">
			<h4 class="panel-title">
				<a href="<%=request.getContextPath()%>/front_end/article/listAllArticle.jsp" role="button" class="collapsed" aria-expanded="false"
					aria-controls="aaa">�Ҧ��峹</a>
			</h4>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading" role="tab" id="panel1">
			<h4 class="panel-title">
				<a href="#aaa" data-parent="#accordion1" data-toggle="collapse"
					role="button" class="collapsed" aria-expanded="false"
					aria-controls="aaa">��峹</a>
			</h4>
<%-- 			<%-- ���~��C --%> 
<%-- 			<c:if test="${not empty errorMsgs}"> --%>
<!-- 				<font color='red'>�Эץ��H�U���~: -->
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

						<li><b class="b">���D����r:</b></li> <input type="text"
							name="article_title" value="" size="7" class="form-control" style="height:30px;"><br>

						<li><b class="b">�o��H�b��:</b></li> <input type="text"
							name="member_acc" value="" size="7" class="form-control" style="height:30px;"><br>

						<li><b class="b">���e����r:</b></li> <input type="text"
							name="article_content" value="" size="7" class="form-control" style="height:30px;"><br>

						<li><b class="b">�I�\�v�j��h��:</b></li> 
							<input type="number" name="article_views"  value="" min="0" class="form-control" style="height:30px;">
							<br>
						<div class="btn btn-primary" onclick="submit();">�e�X</div>
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
					aria-controls="bbb">�峹�޲z</a>
			</h4>
		</div>
		<div id="bbb" class="panel-collapse collapse" role="tabpanel"
			aria-labelledby="panel2">
			<div class="panel-body">
				<ul>
					<li><a href='<%=request.getContextPath()%>/front_end/article/addArticle.jsp' style="color:black"><b class="b">�o��峹</b></a></li>

					<li><a
						href='<%=request.getContextPath()%>/front_end/article/listOwnArticle.jsp' style="color:black"><b
							class="b">�d�ߦۤv�o��L���峹:</b></a></li>

					<li><a
						href='<%=request.getContextPath()%>/front_end/article/listOwnResponse.jsp' style="color:black"><b
							class="b">�d�ߦۤv���^�йL���峹:</b></a></li>
				</ul>
			</div>
		</div>
	</div>
		
</body>

</html>

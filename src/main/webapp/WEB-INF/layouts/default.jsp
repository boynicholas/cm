<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>智能消费管理平台</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1.0">
<link rel="stylesheet" href="${ctx}/static/bootstrap/3.0.3/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/static/styles/default.css">

<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/3.0.3/js/bootstrap.min.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<sitemesh:head/>
</head>

<body>
<%@ include file="/WEB-INF/layouts/header.jsp"%>
	<sitemesh:body/>
<%@ include file="/WEB-INF/layouts/footer.jsp"%>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- header start -->
	<nav class="navbar navbar-fixed-top navbar-default" role="navigation">
    	<div class="navbar-header">
        	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">工具导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="javascript:;">智能消费管理平台</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        	<ul class="nav navbar-nav">
            	<li><a href="javascript:;">个人记账</a></li>
                <li class="active"><a href="javascript:;">团队记账</a></li>
                <li><a href="javascript:;">帮助</a></li>
                <li><a href="javascript:;">关于</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
            	<li><a href="javascript:;">设置</a></li>
               	<li class="dropdown">
                	<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">倪大杰 <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                    	<li><a href="javascript:;">个人资料</a></li>
                        <li><a href="javascript:;">修改密码</a></li>
                        <li class="divider"></li>
                        <li><a href="javascript:;">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <!-- header end -->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/2.3.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/2.3.2/css/bootstrap-responsive.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/styles/loginStyle.css">

<title>智能消费管理平台</title>

<script type="text/javascript" src="static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="static/bootstrap/2.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/CryptoJS/sha1.js"></script>
</head>
<body>
<div id="loginbox">
	<div class="loginform">
        <div class="control-group normal_text">
            <h3>
                <img src="${ctx}/static/images/login/logo.png" alt="智能消费管理平台">
            </h3>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_lg">
                        <i class="icon-user"></i>
                    </span><input type="text" id="u_name" name="u_name" placeholder="用户名">
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_ly">
                        <i class="icon-lock"></i>
                    </span><input type="password" id="u_pass" name="u_pass" placeholder="密码">
                </div>
            </div>
        </div>
        <p class="errmsg"></p>
        <div class="form-actions">
            <span class="pull-left">
                <a href="javascript:;" class="flip-link btn btn-info" id="forword_btn">忘记密码?</a>
            </span>
            <span class="pull-right">
                <a href="javascript:;" class="btn btn-success" id="login_btn">登陆</a>
            </span>
        </div>
  	</div>
</div>
<div class="footer">
	Copyright &copy; 2014 智航软件开发团队 All Rights reserved.
</div>
<script type="text/javascript">
$(function(){
	$("#login_btn").click(function(){
		var login = new loginFun();
		login.setUserName($("#u_name").val());
		login.setUserPass($("#u_pass").val());
		login.setCaptcha($("#captcha").val());
		if(!login.loginCheck()){
			return false;
		}
		login.login();
	})
})

function loginFun(){
	var _user_name;
	var _user_pass;
	var _captcha;
	
	this.setUserName = function(user_name){
		_user_name = $.trim(user_name);
	}
	
	this.getUserName = function(){
		return _user_name;
	}
	
	this.setUserPass = function(user_pass){
		_user_pass = $.trim(user_pass);
	}
	
	this.getUserPass = function(){
		return _user_pass;
	}
	
	this.setCaptcha = function(captcha){
		_captcha = $.trim(captcha);
	}
	
	this.getCaptcha = function(){
		return _captcha;
	}
	
	this.loginCheck = function(){
		if(_user_name == null || _user_name.length == 0 || _user_name.length < 4 || _user_name.length > 16){
			errMsg("用户名不正确");
			return false;
		}else if(_user_pass == null || _user_pass.length == 0 || _user_pass.length < 6 || _user_pass.length > 32){
			errMsg("密码不正确");
			return false;
		}
		return true;
	}
	
	this.login = function(){
		$.ajax({
			url:'login',
			type:'post',
			dataType:"json",
			data:"user_name=" + _user_name + "&user_pass=" + CryptoJS.SHA1(_user_pass) + "&captcha=" + _captcha + "&rememberMe=false",
			success: function(data){
				alert(data);
			}
		})
	}
}

function errMsg(msg){
	$(".errmsg").text(msg);
	flash(".errmsg", 8, 30, 100);
}

function flash(obj,time,wh,fx)
{ 
	$(function(){
	var $panel = $(obj);
	var offset = $panel.offset()-$panel.width();
	var x= offset.left;
	var y= offset.top;
	for(var i=1; i<=time; i++){
		if(i%2==0)
		{
			$panel.animate({left:'+'+wh+'px'},fx);
		}else
		{
			$panel.animate({left:'-'+wh+'px'},fx);
		}
			
	}
	$panel.animate({left:0},fx);
	$panel.offset({ top: y, left: x });
		
	})
}
</script>
</body>
</html>
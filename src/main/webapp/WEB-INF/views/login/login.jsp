<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<style type="text/css">
span.error{
	margin-top: 5px;
	font-weight: bold;
	color: #EA5200;
	display: inline-block;
}

#login_error,#register_error{
	font-size:16px;
	color:#ea5200;
}
</style>
<script type="text/javascript" src="${ctx}/static/jquery-form/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/static/CryptoJS/sha1.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-6">
        	<div class="panel panel-default" id="login_panel">
            	<div class="panel-heading">
                	<h3 class="panel-title">登录智能消费管理平台</h3>
                </div>
                <div class="panel-body">
                	<form class="form-horizontal" role="form" id="login_form">
                    	<div class="form-group">
                        	<label for="login_user_name" class="col-sm-2 control-label">用户名:</label>
                            <div class="col-sm-6">
                            	<input type="text" class="form-control" id="login_user_name" name="login_user_name" maxlength="16">
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="login_user_pass" class="col-sm-2 control-label">密码:</label>
                            <div class="col-sm-6">
                            	<input type="password" class="form-control" id="login_user_pass" name="login_user_pass" maxlength="32">
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="login_verify" class="col-sm-2 control-label">验证码:</label>
                            <div class="col-sm-3">
                            	<input type="text" class="form-control" id="login_verify" name="login_verify" maxlength="4">
                            </div>
                            <div class="col-sm-3">
                            	<img src="kaptcha.jpg" onclick="changeCaptcha(this)" id="img_verify" style="width:100px;height:35px;">
                            </div>

                        </div>
                        <hr>
                        <div class="form-group">
                        	<div class="col-sm-4 pull-right">
                            	<button type="submit" class="btn btn-info col-sm-4" id="login_btn">登录</button>
                            </div>
                            <div class="col-sm-8 pull-right">
                            	<span id="login_error"></span>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    	<div class="col-md-6">
        	<div class="panel panel-default" id="register_panel">
            	<div class="panel-heading">
                	<h3 class="panel-title">
                    	注册智能消费管理平台
                    </h3>
                </div>
                <div class="panel-body">
                	<form class="form-horizontal" role="form" id="register_form">
                    	<div class="form-group">
                        	<label for="userName" class="col-sm-2 control-label">用户名:</label>
                            <div class="col-sm-6">
                            	<input type="text" class="form-control" id="userName" name="userName" maxlength="16">
                                <span class="help-block">请输入4-16位字符，英文、数字、下划线组合</span>
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="userNick" class="col-sm-2 control-label">昵称:</label>
                            <div class="col-sm-6">
                            	<input type="text" class="form-control" id="userNick" name="userNick" maxlength="10">
                                <span class="help-block">请输入4-10位字符、英文、数字、汉字、下划线组合</span>
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="userPass" class="col-sm-2 control-label">密码:</label>
                            <div class="col-sm-6">
                            	<input type="password" class="form-control" id="userPass" name="userPass" maxlength="32">
                                <span class="help-block">请输入6-32位字符，不允许空格</span>
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="re_user_pass" class="col-sm-2 control-label">确认密码:</label>
                            <div class="col-sm-6">
                            	<input type="password" class="form-control" id="re_user_pass" name="re_user_pass" maxlength="32">
                                <span class="help-block">请确认输入的密码</span>
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="userEmail" class="col-sm-2 control-label">邮箱:</label>
                            <div class="col-sm-6">
                            	<input type="text" class="form-control" id="userEmail" name="userEmail">
                                <span class="help-block">请输入你的邮箱，请务必填写真实</span>
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="userqq" class="col-sm-2 control-label">QQ号码:</label>
                            <div class="col-sm-6">
                            	<input type="text" class="form-control" id="userqq" name="userqq">
                                <span class="help-block">请输入你的QQ号，请务必填写真实</span>
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                       <!--  <div class="form-group">
                        	<label for="userPhone" class="col-sm-2 control-label">手机号码:</label>
                            <div class="col-sm-4">
                            	<input type="text" class="form-control" id="userPhone" name="userPhone">
                                <span class="help-block">请输入正确的手机号码，并将手机收到的效验码输入下面的文本框中</span>
                            </div>
                            <div class="col-sm-6" style="padding-left:0;">
                            	<button class="btn btn-primary" type="button">获取效验码</button>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="phone_verify" class="col-sm-2 control-label">效验码:</label>
                            <div class="col-sm-4">
                            	<input type="text" class="form-control" id="phone_verify" name="phone_verify" maxlength="6">
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div> -->
                         <div class="form-group">
                         	<div class="col-sm-4 pull-right">
                            	<button type="submit" class="btn btn-info col-sm-4" id="register_btn">注册</button>
                            </div>
                            <div class="col-sm-8 pull-right">
                            	<span id="register_error"></span>
                            </div>
                         </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
var login_ajax = {
	url:'login',
	type:'post',
	dataType:"json",
	beforeSend:function(){
		$("#login_btn").html("登陆中").attr("disabled","disabled");
	},
	complete:function(){
		$("#login_btn").html("登录").removeAttr("disabled");
		$("#login_user_pass").val("");
	},
	error:function(){
		$("#login_form span#login_error").html("系统故障");
		flash("#login_panel",30,10,2);
	},
	success:function(data){
		if(!data.success){
			$("#login_form span#login_error").html(data.message);
			changeCaptcha('#img_verify');
			flash("#login_panel",30,10,2);
		}else{
			window.location.href="index";
		}
	}
}

var register_ajax = {
		url:'register',
		type:'post',
		dataType:"json",
		beforeSend:function(){
			$("#register_btn").html("注册中").attr("disabled","disabled");
		},
		complete:function(){
			$("#register_btn").html("注册").removeAttr("disabled");
			$("#userPass").val("");
			$("#re_user_pass").val("");
		},
		error:function(){
			$("#register_form span#register_error").html("系统故障");
		},
		success:function(data){
			if(!data.success){
				$("#register_form span#register_error").html(data.message);
			}else{
				window.location.href="verifyEmail";
			}
		}
	}
$(function(){
	$("#login_form").validate({
		errorPlacement: function(error, element) {  
		    error.appendTo(element.parent().next(".col-sm-4").find("span"));
		},
		rules:{
			login_user_name:{
				required:true,
				rangelength:[4,16]
			},
			login_user_pass:{
				required:true,
				rangelength:[6,32]
			},
			login_verify:{
				required:true,
			}
		},
		submitHandler: function(form){
			  $("#login_user_pass").val(CryptoJS.SHA1($("#login_user_pass").val()));
		      $(form).ajaxSubmit(login_ajax);     
		}  
	});
	
	$("#register_form").validate({
		errorElement:"span",
		errorPlacement: function(error, element) {  
		    error.appendTo(element.parent().next(".col-sm-4"));
		},
		rules:{
			userName:{
				required:true,
				checkuser:true,
				rangelength:[4,16],
				remote:{
					url:'register/checkUser',
					type:'post',
					datatype:'json',
					data:{
						userName:function(){return $("#userName").val();}
					}
				}
			},
			userNick:{
				required:true,
				rangelength:[2,10]
			},
			userPass:{
				required:true,
				rangelength:[6,32]
			},
			re_user_pass:{
				required:true,
				equalTo:'#userPass'
			},
			userEmail:{
				required:true,
				email:true
			},
			userqq:{
				required:true,
				qq:true
			},
		},
		messages:{
			userName:{
				remote:"用户名已存在"
			}
		},
		onkeyup:false,
		onfocusout: function(element) { 
		      $(element).valid(); 
		},
		submitHandler: function(form){
		  $("#userPass").val(CryptoJS.SHA1($("#userPass").val()));
	      $(form).ajaxSubmit(register_ajax);     
		} 
	});
})

function changeCaptcha(a){
	$(a).attr("src",$(a).attr("src"));
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
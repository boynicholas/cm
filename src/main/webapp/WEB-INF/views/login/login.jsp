<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-6">
        	<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title">登录智能消费管理平台</h3>
                </div>
                <div class="panel-body">
                	<form class="form-horizontal" role="form" id="login_form">
                    	<div class="form-group">
                        	<label for="login_user_name" class="col-sm-2 control-label">用户名:</label>
                            <div class="col-sm-6">
                            	<input type="text" class="form-control" class="{required:true,rangelength[4,16]}" id="login_user_name" name="login_user_name" maxlength="16">
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="login_user_pass" class="col-sm-2 control-label">密码:</label>
                            <div class="col-sm-6">
                            	<input type="password" class="form-control" id="login_user_pass" name="login_user_pass" class="{required:true,rangelength[6,32]}" maxlength="32">
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="login_verify" class="col-sm-2 control-label">验证码:</label>
                            <div class="col-sm-3">
                            	<input type="text" class="form-control" class="required" id="login_verify" name="login_verify" maxlength="4">
                            </div>
                            <div class="col-sm-3">
                            	<img src="kaptcha.jpg" onclick="this." style="border:1px;width:100px;height:35px;">
                            </div>
                        </div>
                        <hr>
                        <div class="form-group">
                        	<div class="col-sm-10 pull-right">
                            	<button type="submit" class="btn btn-info col-sm-4">登录</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    	<div class="col-md-6">
        	<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title">
                    	注册智能消费管理平台
                    </h3>
                </div>
                <div class="panel-body">
                	<form class="form-horizontal" role="form" id="register_form">
                    	<div class="form-group">
                        	<label for="user_name" class="col-sm-2 control-label">用户名:</label>
                            <div class="col-sm-6">
                            	<input type="text" class="form-control" id="user_name" name="user_name" class="{required:true,rangelength[4,16]}" maxlength="16">
                                <span class="help-block">请输入4-16位字符，英文、数字、下划线组合</span>
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="user_nick" class="col-sm-2 control-label">昵称:</label>
                            <div class="col-sm-6">
                            	<input type="text" class="form-control" class="{required:true,rangelength[4,10]}" id="user_nick" name="user_nick" maxlength="10">
                                <span class="help-block">请输入4-10位字符、英文、数字、汉字、下划线组合</span>
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="user_pass" class="col-sm-2 control-label">密码:</label>
                            <div class="col-sm-6">
                            	<input type="password" class="form-control" class="{required:true,rangelength[6,32]}" id="user_pass" name="user_pass" maxlength="32">
                                <span class="help-block">请输入6-32位字符，不允许空格</span>
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="re_user_pass" class="col-sm-2 control-label">确认密码:</label>
                            <div class="col-sm-6">
                            	<input type="password" class="form-control" class="{required:true,equalTo:'#userpass'}" id="re_user_pass" name="re_user_pass" maxlength="32">
                                <span class="help-block">请确认输入的密码</span>
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="user_email" class="col-sm-2 control-label">邮箱:</label>
                            <div class="col-sm-6">
                            	<input type="text" class="form-control" class="{required:true,email:true}" id="user_email" name="user_email">
                                <span class="help-block">请输入你的邮箱，请务必填写真实</span>
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="user_qq" class="col-sm-2 control-label">QQ号码:</label>
                            <div class="col-sm-6">
                            	<input type="text" class="form-control" class="{required:true,digits:true}" id="user_qq" name="user_qq">
                                <span class="help-block">请输入你的QQ号，请务必填写真实</span>
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="user_phone" class="col-sm-2 control-label">手机号码:</label>
                            <div class="col-sm-4">
                            	<input type="text" class="form-control" class="{required:true,digits:true}" id="user_phone" name="user_phone">
                                <span class="help-block">请输入正确的手机号码，并将手机收到的效验码输入下面的文本框中</span>
                            </div>
                            <div class="col-sm-6" style="padding-left:0;">
                            	<button class="btn btn-primary" type="button">获取效验码</button>
                            </div>
                        </div>
                        <div class="form-group">
                        	<label for="phone_verify" class="col-sm-2 control-label">效验码:</label>
                            <div class="col-sm-4">
                            	<input type="text" class="form-control" class="required" id="phone_verify" name="phone_verify" maxlength="6">
                            </div>
                            <div class="col-sm-4">
                            	<span class="error"></span>
                            </div>
                        </div>
                         <div class="form-group">
                         	<div class="col-sm-10 pull-right">
                            	<button type="submit" class="btn btn-info col-sm-4">注册</button>
                            </div>
                         </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
$(function(){
	$("#login_form").validate({
		debug:true,
		errorPlacement:function(error,element){
			alert(elment.parent().next().html());
			error.appendTo(element.parent().next().find(".error"));
		}
	});
	$("#register_form").validate({
		debug:true,
		errorPlacement:function(error,element){
			error.appendTo(element.parent().next().find(".error"));
		}
	});
})
</script>
</body>
</html>
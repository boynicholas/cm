/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: CN
 */
jQuery.extend(jQuery.validator.messages, {
        required: "必填项",
		remote: "请修正该字段",
		email: "请输入正确格式的电子邮件",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO).",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符串",
		maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
		minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
		rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
		range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
		min: jQuery.validator.format("请输入一个最小为 {0} 的值"),
		checkGranterThan:"请确定两个数据的大小",
		checkDate:"请确定两个日期的大小"
		/*checkEmail:"邮箱格式错误，请输入正确的邮箱格式"*/
});

jQuery.validator.addMethod("mobile", function(value, element) { 
	var length = value.length; 
	var mobile = /^1\d{10}$/ 
	return this.optional(element) || (length == 11 && mobile.test(value)); 
}, "手机号码格式错误");

jQuery.validator.addMethod("phone", function(value, element) { 
	var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/; 
	return this.optional(element) || (tel.test(value)); 
}, "电话号码格式错误");

jQuery.validator.addMethod("zipCode", function(value, element) { 
	var tel = /^[0-9]{6}$/; 
	return this.optional(element) || (tel.test(value)); 
}, "邮政编码格式错误");

jQuery.validator.addMethod("qq", function(value, element) { 
	var tel = /^[1-9]\d{4,14}$/; 
	return this.optional(element) || (tel.test(value)); 
}, "qq号码格式错误");

jQuery.validator.addMethod("ip", function(value, element) { 
	var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/; 
	return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)); 
}, "Ip地址格式错误");

jQuery.validator.addMethod("chrnum", function(value, element) {
	var chrnum = /^([a-zA-Z0-9]+)$/;
	return this.optional(element) || (chrnum.test(value));
}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");

jQuery.validator.addMethod("checkuser",function(value, element){
	var checkuser = /^[a-zA-z]([a-zA-z0-9_]+)$/;
	return this.optional(element) || (checkuser.test(value));
}, "只能输入数字和字母和下划线");

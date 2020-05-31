<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>
<base href="<%=basePath %>>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="html/scripts/jquery/jquery-1.7.1.js"></script>
<link href="html/style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="html/style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="html/scripts/authority/commonAll.js"></script>
<script type="text/javascript" src="html/scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="html/scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="html/style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
<script type="text/javascript" src="html/scripts/artDialog/artDialog.js?skin=default"></script>
<title>添加敏感词</title>
<script type="text/javascript">
	$(function(){
		$("#addBtn").click(function(){
			var add_content = $("#sensitive_word").val();
			
			if (add_content == "" || add_content == null || add_content == undefined) {
				info_prompt("添加内容不能为空！！！", 1500);
			} else {
				$.ajax({
					type: "POST",
					url: "key_words/add_key_word",
					data: "content=" + add_content,
					dataType: "json",
					success: function(msg){
						if (msg.state == "success") {
							success_prompt('添加成功！！！', 1500);							
						} else {
							fail_prompt('添加失败，请稍后再试！！！', 1500);
						}
					},
					error:function (XMLHttpRequest, textStatus, errorThrown) {
						if (textStatus == 'error') {
							fail_prompt('呀，服务器出错了，请稍后再试！！！', 1500);
						}
					}
				});
			}
        })
		
	})
	/**
	 * 弹出式提示框，默认1.2秒自动消失
	 * @param message 提示信息
	 * @param style 提示样式，有alert-success、alert-danger、alert-warning、alert-info
	 * @param time 消失时间
	 */
	var prompt = function (message, style, time)
	{
	    style = (style === undefined) ? 'alert-success' : style;
	    time = (time === undefined) ? 1200 : time;
	    $('<div>')
	        .appendTo('body')
	        .addClass('alert ' + style)
	        .html(message)
	        .show()
	        .delay(time)
	        .fadeOut();
	};
	
	// 成功提示
	var success_prompt = function(message, time)
	{
	    prompt(message, 'alert-success', time);
	};
	
	// 失败提示
	var fail_prompt = function(message, time)
	{
	    prompt(message, 'alert-danger', time);
	};
	
	// 提醒
	var warning_prompt = function(message, time)
	{
	    prompt(message, 'alert-warning', time);
	};
	
	// 信息提示
	var info_prompt = function(message, time)
	{
	    prompt(message, 'alert-info', time);
	};
</script>
<style type="text/css">
	.alert {
	    display: none;
	    position: fixed;
	    top: 20%;
	    left: 50%;
	    min-width: 200px;
	    margin-left: -100px;
	    z-index: 99999;
	    padding: 15px;
	    border: 1px solid transparent;
	    border-radius: 4px;
	}
	
	.alert-success {
	    color: #3c763d;
	    background-color: #dff0d8;
	    border-color: #d6e9c6;
	}
	
	.alert-info {
	    color: #31708f;
	    background-color: #d9edf7;
	    border-color: #bce8f1;
	}
	
	.alert-warning {
	    color: #8a6d3b;
	    background-color: #fcf8e3;
	    border-color: #faebcc;
	}
	
	.alert-danger {
	    color: #a94442;
	    background-color: #f2dede;
	    border-color: #ebccd1;
	}
</style>
</head>
<body>
	<div class="ui_content">
		<div class="ui_text_indent">
			<div id="box_border">
				<div id="box_top">搜索</div>
				<div id="box_center">
					<span style="margin-left: 20px;">输入敏感词</span>&nbsp;&nbsp;<input type="text" style="width: 500px" id="sensitive_word" name="sensitive_word" class="ui_input_txt02" />
					<input type="button" value="添加" style="margin-left: 400px" class="ui_input_btn01" id="addBtn" /> 
				</div>
			</div>
		</div>
	</div>
</body>
</html>
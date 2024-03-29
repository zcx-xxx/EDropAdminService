﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>EDrop-后台系统</title>
<link href="html/style/authority/login_css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="html/scripts/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript">
	$(function(){
		// 登录
		$("#login_sub").click(function(){
			var pwd = $("#pwd").val();
			var name = $("#name").val();
			$.ajax({
				type: "POST",
				url: "admin/login",
				data: "userName=" + name + "&password=" + pwd,
				dataType: "json",
				success: function(msg){
					if (msg.state == "success") {
						var storage=window.localStorage;
						storage["user_name"] = name;
						$("#submitForm").attr("action", "html/index.jsp").submit();
					} else if (msg.state == "fail"){
						$("#login_err").text("用户名或者密码错误");
					}
				}
			});
        })
	})
	
	/*回车事件*/
	function EnterPress(e){ //传入 event 
		var e = e || window.event; 
		if(e.keyCode == 13){ 
			var pwd = $("#pwd").val();
			var name = $("#name").val();

			$.ajax({
				type: "POST",
				url: "admin/login",
				data: "userName=" + name + "&password=" + pwd,
				dataType: "json",
				success: function(msg){
					if (msg.state == "success") {
						var storage=window.localStorage;
						storage["user_name"] = name;
						$("#submitForm").attr("action", "html/index.jsp").submit();
					} else if (msg.state == "fail"){
						$("#login_err").text("用户名或者密码错误");
					}
				}
			});
		} 
	} 
</script>
</head>
<body>
	<div id="login_center">
		<div id="login_area">
			<div id="login_box">
				<div id="login_form">
					<form id="submitForm" action="" method="post">
						<div id="login_tip">
							<span id="login_err" class="sty_txt2" color="red"></span>
						</div>
						<div>
							 用户名：<input type="text" name="userEntity.userCode" class="username" id="name">
						</div>
						<div>
							密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="userEntity.password" 
							class="pwd" id="pwd" onkeypress="EnterPress(event)" />
						</div>
						<div id="btn_area">
							<input type="button" class="login_btn" id="login_sub"  value="登  录">
							<input type="reset" class="login_btn" id="login_ret" value="重 置">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
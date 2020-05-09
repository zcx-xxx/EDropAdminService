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
<title>信息管理系统</title>
<base href="<%=basePath %>>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="html/scripts/jquery/jquery-1.7.1.js"></script>
<link href="html/style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="html/style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="html/scripts/authority/commonAll.js"></script>
<script type="text/javascript" src="html/scripts/jquery/jquery-1.4.4.min.js"></script>
<script src="html/scripts/My97DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script>
<script type="text/javascript" src="html/scripts/artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript">
	$(document).ready(function() {
		/*
		 * 提交
		 */
	
		$("#submitbutton").click(function(){
			
			$.ajax({
				type: "POST",
				url: "employee/update",
				data: $('#submitForm').serialize(),
				dataType : "text",
				cache:false,
				success : function(dates){
					if(dates =='success'){
						/**  关闭弹出iframe  **/
						window.parent.$.fancybox.close();
					}
					
				},
				 error:function(error){alert(error);}
			});
	    })
		
		/*
		 * 取消
		 */
		$("#cancelbutton").click(function() {
			/**  关闭弹出iframe  **/
			window.parent.$.fancybox.close();
		});
	});
	

	
	/** 表单验证  **/
	function validateForm(){
		if($("#username").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'用户名不能为空', ok:true,});
			return false;
		}
		if($("#phone").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'请填写手机号码', ok:true,});
			return false;
		}
		if($("#imgpath").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填写头像路径', ok:true,});
			return false;
		}
		if($("#qq").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填写qq', ok:true,});
			return false;
		}
		if($("#gender").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'性别不能为空', ok:true,});
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<form id="submitForm" name="submitForm" action="user/update" method="post">
	<input type="hidden" name="fyID" value="14458625716623" id="fyID"/>
	<div id="container">
		<div id="nav_links">
			当前位置：基础数据&nbsp;>&nbsp;<span style="color: #1A5CC6;">工作人员信息编辑</span>
			<div id="page_close">
				<a href="javascript:parent.$.fancybox.close();">
					<img src="html/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<input type="hidden" id="userid" name="userid" value="${employee.id }" class="ui_input_txt01"/>
					
				<tr>
					<td class="ui_text_rt" width="80">用户名</td>
					<td class="ui_text_lt">
						<input type="text" id="username" name="username" value="${employee.username }" class="ui_input_txt01"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">电话</td>
					<td class="ui_text_lt">
						<input type="text" id="phone" name="phone" value="${employee.phone }" class="ui_input_txt01"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">头像路径</td>
					<td class="ui_text_lt">
						<input type="text" id="imgpath" name="imgpath" value="${employee.imgpath }" class="ui_input_txt01" onkeyup="checkFyFh();"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">QQ</td>
					<td class="ui_text_lt">
						<input type="text" id="qq" name="qq" value="${employee.qq }" class="ui_input_txt01" onkeyup="checkFyFh();"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">性别</td>
					<td class="ui_text_lt">
						<select name="gender" id="gender" class="ui_select01">
							<option value="">--请选择--</option>
                            <option value="男" selected="selected">男</option>
                            <option value="女" >女</option>
                        </select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input id="submitbutton" type="button" value="提交" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" value="取消" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>

</body>
</html>
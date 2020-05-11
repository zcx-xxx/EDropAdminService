<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>
<title>信息管理系统</title>
<base href="http://localhost:8080/EDropAdminService/" />
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
				url: "rubbish/update",
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
		if($("#name").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'用户名不能为空', ok:true,});
			return false;
		}
		if($("#type").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'请选择垃圾分类', ok:true,});
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<form id="submitForm" name="submitForm" action="user/update" method="post">
	<div id="container">
		<div id="nav_links">
			当前位置：基础数据&nbsp;>&nbsp;<span style="color: #1A5CC6;">垃圾数据编辑</span>
			<div id="page_close">
				<a href="javascript:parent.$.fancybox.close();">
					<img src="html/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<input type="hidden" id="id" name="id" value="${rubbish.id }" class="ui_input_txt01"/>
					
				<tr>
					<td class="ui_text_rt" width="80">垃圾名称</td>
					<td class="ui_text_lt">
						<input type="text" id="name" name="name" value="${rubbish.name }" class="ui_input_txt01"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">性别</td>
					<td class="ui_text_lt">
						<select name="type" id="type" class="ui_select02">
							<option value="">--请选择--</option>
							<c:if test="${rubbish.typeId == 1 }">
                            	<option value="可回收物" selected="selected">可回收物</option>
                                <option value="有害垃圾">有害垃圾</option>
                                <option value="湿垃圾">湿垃圾</option>
                                <option value="干垃圾">干垃圾</option>
                            </c:if>
                            <c:if test="${rubbish.typeId == 2 }">
                            	<option value="可回收物" >可回收物</option>
                                <option value="有害垃圾" selected="selected">有害垃圾</option>
                                <option value="湿垃圾">湿垃圾</option>
                                <option value="干垃圾">干垃圾</option>
                            </c:if>
                            <c:if test="${rubbish.typeId == 3 }">
                            	<option value="可回收物" >可回收物</option>
                                <option value="有害垃圾">有害垃圾</option>
                                <option value="湿垃圾" selected="selected">湿垃圾</option>
                                <option value="干垃圾">干垃圾</option>
                            </c:if>
                            <c:if test="${rubbish.typeId == 4 }">
                            	<option value="可回收物" >可回收物</option>
                                <option value="有害垃圾">有害垃圾</option>
                                <option value="湿垃圾">湿垃圾</option>
                                <option value="干垃圾" selected="selected">干垃圾</option>
                            </c:if>
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
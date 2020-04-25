<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>
<base href="http://localhost:8080/EDropAdminService/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="html/scripts/jquery/jquery-1.7.1.js"></script>
<link href="html/style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="html/style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="html/scripts/authority/commonAll.js"></script>
<script type="text/javascript" src="html/scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="html/scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="html/style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
<script type="text/javascript" src="html/scripts/artDialog/artDialog.js?skin=default"></script>
<title>信息管理系统</title>
<script type="text/javascript">
	$(function(){
		// 登录
		$("#find_user").click(function(){
			var username = $("#username").val();
			var phone = $("#phone").val();
			$.ajax({
				type: "POST",
				url: "user/find",
				data: "username=" + username + "&phone=" + phone,
				dataType : "html",
				cache:false,
				success : function(dates){
					$("#updatediv").html(dates);//要刷新的div
					
				},
				 error:function(error){alert(error);}
			});
	    })
	})
	$(document).ready(function(){
		/** 新增   **/
	    $("#addBtn").fancybox({
	    	'href'  : 'house_edit.html',
	    	'width' : 733,
	        'height' : 530,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : false,
	        'onClosed' : function() { 
	        	window.location.href = 'house_list.html';
	        }
	    });
		
	    /** 导入  **/
	    $("#importBtn").fancybox({
	    	'href'  : '/xngzf/archives/importFangyuan.action',
	    	'width' : 633,
	        'height' : 260,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : false,
	        'onClosed' : function() { 
	        	window.location.href = 'house_list.html';
	        }
	    });
		
	    /**编辑   **/
	    $("a.edit").fancybox({
	    	'width' : 733,
	        'height' : 530,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : false,
	        'onClosed' : function() { 
	        	window.location.href = 'html/house_list.jsp';
	        }
	    });
	});
	/** 用户角色   **/
	var userRole = '';

	/** 新增   **/
	function add(){
		$("#submitForm").attr("action", "/xngzf/archives/luruFangyuan.action").submit();	
	}
	 
	/** Excel导出  **/
	function exportExcel(){
		if( confirm('您确定要导出吗？') ){
			var fyXqCode = $("#fyXq").val();
			var fyXqName = $('#fyXq option:selected').text();
//	 		alert(fyXqCode);
			if(fyXqCode=="" || fyXqCode==null){
				$("#fyXqName").val("");
			}else{
//	 			alert(fyXqCode);
				$("#fyXqName").val(fyXqName);
			}
			$("#submitForm").attr("action", "/xngzf/archives/exportExcelFangyuan.action").submit();	
		}
	}
	
	/** 删除 **/
	function del(fyID){
		// 非空判断
		if(fyID == '') return;
		if(confirm("您确定要删除吗？")){
			$("#submitForm").attr("action", "/xngzf/archives/delFangyuan.action?fyID=" + fyID).submit();			
		}
	}
	
	/** 批量删除 **/
	function batchDel(){
		if($("input[name='IDCheck']:checked").size()<=0){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'至少选择一条', ok:true,});
			return;
		}
		// 1）取出用户选中的checkbox放入字符串传给后台,form提交
		var allIDCheck = "";
		$("input[name='IDCheck']:checked").each(function(index, domEle){
			bjText = $(domEle).parent("td").parent("tr").last().children("td").last().prev().text();
// 			alert(bjText);
			// 用户选择的checkbox, 过滤掉“已审核”的，记住哦
			if($.trim(bjText)=="已审核"){
// 				$(domEle).removeAttr("checked");
				$(domEle).parent("td").parent("tr").css({color:"red"});
				$("#resultInfo").html("已审核的是不允许您删除的，请联系管理员删除！！！");
// 				return;
			}else{
				allIDCheck += $(domEle).val() + ",";
			}
		});
		// 截掉最后一个","
		if(allIDCheck.length>0) {
			allIDCheck = allIDCheck.substring(0, allIDCheck.length-1);
			// 赋给隐藏域
			$("#allIDCheck").val(allIDCheck);
			if(confirm("您确定要批量删除这些记录吗？")){
				// 提交form
				$("#submitForm").attr("action", "/xngzf/archives/batchDelFangyuan.action").submit();
			}
		}
	}

	/** 普通跳转 **/
	function jumpNormalPage(page){
		$("#submitForm").attr("action", "user/list?page=" + page).submit();
	}
	
	/** 输入页跳转 **/
	function jumpInputPage(totalPage){
		// 如果“跳转页数”不为空
		if($("#jumpNumTxt").val() != ''){
			var pageNum = parseInt($("#jumpNumTxt").val());
			// 如果跳转页数在不合理范围内，则置为1
			if(pageNum<1 | pageNum>totalPage){
				art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'请输入合适的页数，\n自动为您跳到首页', ok:true,});
				pageNum = 1;
			}
			$("#submitForm").attr("action", "user/list?page=" + pageNum).submit();
		}else{
			// “跳转页数”为空
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'请输入合适的页数，\n自动为您跳到首页', ok:true,});
			$("#submitForm").attr("action", "user/list?page=" + 1).submit();
		}
	}
</script>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="submitForm" name="submitForm" action="" method="post"  id="finddiv">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							用户名&nbsp;&nbsp;<input type="text" id="username" name="username" class="ui_input_txt02" />
							电话&nbsp;&nbsp;<input type="text" id="phone" name="phone" class="ui_input_txt02" />
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01" id="find_user"/> 
							<input type="button" value="新增" class="ui_input_btn01" id="addBtn" /> 
							<input type="button" value="删除" class="ui_input_btn01" onclick="batchDel();" /> 
							<input type="button" value="导入" class="ui_input_btn01" id="importBtn" />
							<input type="button" value="导出" class="ui_input_btn01" onclick="exportExcel();" />
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content" id="updatediv">
				<div class="ui_tb" >
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" onclick="selectOrClearAllCheckbox(this);" />
							</th>
							<th>用户名</th>
							<th>电话</th>
							<th>地址</th>
							<th>详细地址</th>
							<th>性别</th>
							<th>注册时间</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${pageInfo.list }" var="p">
							<tr>
								<td><input type="checkbox" name="IDCheck"
									value="14458579642011" class="acb" /></td>
								<td>${p.username }</td>
								<td>${p.phone }</td>
								<td>${p.address }</td>
								<td>${p.detailAddress }</td>
								<td>${p.gender }</td>
								<td>${p.registerTime }</td>
								<td><a href="house_edit.html?fyID=14458579642011"
									class="edit">编辑</a> <a href="javascript:del('14458579642011');">删除</a>
								</td>
							</tr>
						</c:forEach>

					</table>
				</div>
				<div class="ui_tb_h30">
					<div class="ui_flt" style="height: 30px; line-height: 30px;">
						共有
						<span class="ui_txt_bold04">${pageInfo.total }</span>
						条记录，当前第
						<span class="ui_txt_bold04">${pageInfo.pageNum }
						/
						${pageInfo.pages }</span>
						页
					</div>
					<div class="ui_frt">
						<!--    如果是第一页，则只显示下一页、尾页 -->
							<input type="button" value="首页" class="ui_input_btn01" onclick="jumpNormalPage(1);"/>
							<c:if test="${pageInfo.pageNum > 1 }">
								<input type="button" value="上一页" class="ui_input_btn01" onclick="jumpNormalPage(${pageInfo.pageNum-1 });" />
							</c:if>
							<c:if test="${pageInfo.pageNum < pageInfo.pages }">
								<input type="button" value="下一页" class="ui_input_btn01" onclick="jumpNormalPage(${pageInfo.pageNum+1 });" />
							</c:if>
							<input type="button" value="尾页" class="ui_input_btn01"
								onclick="jumpNormalPage(${pageInfo.pages });" />
					</div>
				</div>
			</div>
		</div>
	</form>

</body>
</html>
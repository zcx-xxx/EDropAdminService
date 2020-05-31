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
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="html/scripts/jquery/jquery-1.7.1.js"></script>
<link href="html/style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="html/style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="html/scripts/authority/commonAll.js"></script>
<script type="text/javascript" src="html/scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="html/scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="html/style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
<script type="text/javascript" src="html/scripts/artDialog/artDialog.js?skin=default"></script>
<title>EDrop-后台管理系统</title>
<script type="text/javascript">
	$(function(){
		$("#find_rubbish").click(function(){
			var name = $("#name").val();
			var type = $("#type").val();
			$.ajax({
				type: "POST",
				url: "rubbish/find",
				data: "name=" + name + "&type=" + type,
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
	    	'href'  : 'html/rubbish_add.jsp',
	    	'width' : 733,
	        'height' : 530,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : false,
	        'onClosed' : function() { 
	        	window.location.href = 'rubbish/list';
	        }
	    });
		
	    /** 导入  **/
	    $("#importBtn").fancybox({
	    	'href'  : 'html/index.jsp',
	    	'width' : 633,
	        'height' : 260,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : false,
	        'onClosed' : function() { 
	        	window.location.href = 'html/index.jsp';
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
	        	window.location.href = 'rubbish/list';
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
	
	/** 删除
	function del(fyID){
		// 非空判断
		if(fyID == '') return;
		if(confirm("您确定要删除吗？")){
			$("#submitForm").attr("action", "/xngzf/archives/delFangyuan.action?fyID=" + fyID).submit();			
		}
	} **/
	/**删除 **/
	function del(rid){
		$.ajax({
			type: "POST",
			url: "rubbish/delete",
			data: "rid=" + rid ,
			dataType : "html",
			cache:false,
			success : function(dates){
				$("#updatediv").html(dates);//要刷新的div
				
			},
			 error:function(error){alert(error);}
		});
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
		$("#submitForm").attr("action", "rubbish/list?page=" + page).submit();
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
			$("#submitForm").attr("action", "rubbish/list?page=" + pageNum).submit();
		}else{
			// “跳转页数”为空
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'请输入合适的页数，\n自动为您跳到首页', ok:true,});
			$("#submitForm").attr("action", "rubbish/list?page=" + 1).submit();
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
							垃圾名称&nbsp;&nbsp;<input type="text" id="name" name="name" class="ui_input_txt02" />
							垃圾分类&nbsp;&nbsp;
							<select name="type" id="type" class="ui_select02" onchange="getFyDhListByFyXqCode();">
                                <option value="">--请选择--</option>
                                <option value="可回收物">可回收物</option>
                                <option value="有害垃圾">有害垃圾</option>
                                <option value="湿垃圾">湿垃圾</option>
                                <option value="干垃圾">干垃圾</option>
                            </select>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01" id="find_rubbish"/> 
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
							<th>垃圾名称</th>
							<th>垃圾分类</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${pageInfo.list }" var="p">
							<tr>
								<td><input type="checkbox" name="IDCheck"
									value="14458579642011" class="acb" /></td>
								<td>${p.name }</td>
								<c:if test="${p.typeId == 1 }">
									<td>可回收物</td>
								</c:if>
								<c:if test="${p.typeId == 2 }">
									<td>有害垃圾</td>
								</c:if>
								<c:if test="${p.typeId == 3 }">
									<td>湿垃圾</td>
								</c:if>
								<c:if test="${p.typeId == 4 }">
									<td>干垃圾</td>
								</c:if>
								<td><a href="rubbish/edit?id=${p.id}"
									class="edit">编辑</a> &nbsp;&nbsp;<a href="javascript:del(${p.id })">删除</a>
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
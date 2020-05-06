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
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="html/scripts/jquery/jquery-1.7.1.js"></script>
<link href="html/style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="html/style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="html/scripts/authority/commonAll.js"></script>
<script type="text/javascript" src="html/scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="html/scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="html/style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
<script type="text/javascript" src="html/scripts/artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="html/js/date_formate.js"></script>
<title>反馈信息列表</title>
<script type="text/javascript">
	$(function(){
	})
	
	function delete_article(obj) {
		// 获得对应文章的 id
		var id = $(obj).children("span").first().attr("data-id");
		console.log(id);
		// 异步请求数据
		$.ajax({
			type: "POST",
			url: "article/delete_article",
			data: "articleId=" + id,
			dataType: "html",
			success: function(msg){
				$("#updatediv").html(msg);//要刷新的div
			}
		});
	}
	
	function edit_article(obj) {
		// 获得对应文章的 id
		var id = $(obj).children("span").first().attr("data-id");
		console.log(id);
	}
	
	function jumpNormalPage(page) {
		$.ajax({
			type: "POST",
			url: "article/load_article_list",
			data: "page=" + page,
			dataType: "html",
			success: function(msg){
				$("#updatediv").html(msg);//要刷新的div
			}
		});
	}
	function date_formate(date_item) {
		var array = date_item.split(/[, :]/);
		var longMonths = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
		var year = array[3];
		var day = array[1];
		var month = 0;
		for (var m in longMonths) {
			month += 1;
			console.log(m.substring(0, 3) + " " + array[0].substring(0, 3));
			if (longMonths[m].substring(0, 3) == array[0].substring(0, 3)) {
				break;
			}
		}
		var hour = array[4];
		var minute = array[5];
		var seconds = array[6];
		if (array[7] == 'PM') {
			hour = parseInt(hour) + 12;
		}
		if (hour < 10) hour = "0" + hour;
		
		return "" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + seconds;
	}
	
</script>
</head>
<body>
	<div class="ui_content" id="updatediv">
		<div class="ui_text_indent">
			<div id="box_border" style="margin-left: 20px; margin-right: 20px; margin-top: 40px; background: #ffffff;">
				<div style="padding-left:30px; padding-right: 20px; background: #ffffff;">
					<table style="width: 100%;">
						<tbody id="message_tbody" style="vertical-align: middle; display: table-row-group; border-color: #ddd; background: #fff">
							<tr id="" style="vertical-align: inherit; display: table_row; border-color: inherit; height: 50px;">
								<th rowspan="1" colspan="1" class="el-table_2_column_4" style="border-bottom: 1px solid #ebeef5;">
									<div class="cell">
									<span id="" class="message-title">标题</span>
									</div>
								</th>
								<th rowspan="1" colspan="1" class="el-table_2_column_5  ">
									<div class="cell" id="">评论数</div>
								</th>
								<th rowspan="1" colspan="1" class="el-table_2_column_5  ">
									<div class="cell" id="">点赞数</div>
								</th>
								<th rowspan="1" colspan="1" class="el-table_2_column_5  ">
									<div class="cell" id="">阅读数</div>
								</th>
								</th>
								<th rowspan="1" colspan="1" class="el-table_2_column_5  ">
									<div class="cell" id="">操作</div>
								</th>
								</th>
								<th rowspan="1" colspan="1" class="el-table_2_column_5  ">
									<div class="cell" id="">操作</div>
								</th>				
							</tr>
							<c:forEach items="${pageInfo.list }" var="p">
								<tr id="message_modle_tr" style="vertical-align: inherit; display: table_row; border-color: inherit; height: 50px;">
									<td rowspan="1" colspan="1" class="el-table_2_column_4" style="border-bottom: 1px solid #ebeef5; width: 50%;">
										<div class="cell">
											<span id="message_content_span" class="message-title">${p.articleTitle }</span>
										</div>
									</td>
									<td rowspan="1" colspan="1" class="el-table_2_column_5 " align="center" valign="middle">
										<div class="cell" id="message_date_div">${p.articleCommentCount }</div>
									</td>
									<td rowspan="1" colspan="1" class="el-table_2_column_5  " align="center" valign="middle">
										<div class="cell" id="message_date_div">${p.articleLikeCount }</div>
									</td>
									<td rowspan="1" colspan="1" class="el-table_2_column_5  " align="center" valign="middle">
										<div class="cell" id="message_date_div">${p.articleViews }</div>
									</td>
									<td rowspan="1" colspan="1" class="el-table_2_column_6" align="center" valign="middle">
										<div class="cell" style="cursor: pointer;">
											<button id="mark_read_button" type="button" class="el-button el-button--defaultel-button--small"
											style="border-radius: 3px; cursor: pointer; display: inline-block; line-height: 1;
											background: #fff; border: 1px solid #dcdfe6; padding: 9px 15px;" onclick="edit_article(this)">
												<span id="edit_article_span" data-id="${p.id }" style="cursor: pointer;">编辑</span>
											</button>
										</div>
									</td>
									<td rowspan="1" colspan="1" class="el-table_2_column_6" align="center" valign="middle">
										<div class="cell" style="cursor: pointer;">
											<button id="mark_read_button" type="button" class="el-button el-button--default el-button--small"
											style="border-radius: 3px; cursor: pointer; display: inline-block; line-height: 1;
											background: #fff; border: 1px solid #dcdfe6; padding: 9px 15px;" onclick="delete_article(this)">
												<span id="delete_article_span" data-id="${p.id }" style="cursor: pointer;">删除</span>
											</button>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
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
	</div>
</body>
</html>
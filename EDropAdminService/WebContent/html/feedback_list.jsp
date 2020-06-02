<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
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
		default_button_state();
	})

	function mark_readed(obj) {
		// 获得标记的 id
		var id = $(obj).children("span").first().attr("data-id");
		// 更新后台数据库
		$.ajax({
			type: "POST",
			url: "feedback/mark_readed",
			data: "feedbackId=" + id,
			dataType: "json",
			success: function(msg){
				// 将自己所在行删除
				$(obj).parents("tr").remove();
				// 更改消息显示数量
				sync_feedback_counts();
			}
		});
	}
	function deleted_readed(obj) {
		// 获得标记的 id
		var id = $(obj).children("span").first().attr("data-id");
		// 更新后台数据库
		$.ajax({
			type: "POST",
			url: "feedback/delete_readed",
			data: "feedbackId=" + id,
			dataType: "json",
			success: function(msg){
				// 将自己所在行删除
				$(obj).parents("tr").remove();
				// 更改消息显示数量
				sync_feedback_counts();
			}
		});
	}
	function restore_deleted(obj) {
		// 获得标记的 id
		var id = $(obj).children("span").first().attr("data-id");
		// 更新后台数据库
		$.ajax({
			type: "POST",
			url: "feedback/restore_deleted_feedback",
			data: "feedbackId=" + id,
			dataType: "json",
			success: function(msg){
				// 将自己所在行删除
				$(obj).parents("tr").remove();
				// 更改消息显示数量
				sync_feedback_counts();
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
	
	function default_button_state() {
		$("#no_read_button").css("background-color", "#4CAF50");
		$("#readed_button").css("background-color", "#fff");
		$("#deleted_button").css("background-color", "#fff");
		$("#no_read_button").css("color", "white");
		$("#readed_button").css("color", "black");
		$("#deleted_button").css("color", "black");
		sync_feedback_counts();
		
		// 获取反馈列表
		var message_tbody = $("#message_tbody");
		// 获取反馈条目
		var message_item = $('<tr id="message_modle_tr" style="vertical-align: inherit; display: table_row; border-color: inherit;'
				+ 'height: 50px;">'
		+ '<td rowspan="1" colspan="1" class="el-table_2_column_4" style="border-bottom: 1px solid #ebeef5;">'
		+ '	<div class="cell">'
		+ '		<span id="message_content_span" class="message-title">【系统通知】该系统将于今晚凌晨2点到5点进行升级维护</span>'
		+ '	</div>'
		+ '</td>'
		+ '<td rowspan="1" colspan="1" class="el-table_2_column_5  ">'
		+ '	<div class="cell" id="message_date_div">2018-04-19 20:00:00</div>'
		+ '</td>'
		+ '<td rowspan="1" colspan="1" class="el-table_2_column_6">'
		+ '	<div class="cell" style="cursor: pointer;">'
		+ '		<button id="mark_read_button" type="button" class="el-button el-button--default el-button--small"' 
		+ '		style="border-radius: 3px; cursor: pointer; display: inline-block; line-height: 1;'
		+ '		background: #fff; border: 1px solid #dcdfe6; padding: 9px 15px;" onclick="mark_readed(this)">'
		+ '			<span id="message_state_span" data-id="0" style="cursor: pointer;">标为已读</span>'
		+ '		</button>'
		+ '	</div>'
		+ '</td>'
		+ '</tr> ');
		// 将反馈列表清空
		message_tbody.empty();
		// 请求数据
		$.ajax({
			type: "POST",
			url: "feedback/get_feedback",
			data: "isReaded=" + "0",
			dataType: "json",
			success: function(msg){
				for (var i = 0; i < msg.length; ++i) {
					var feedback_item = message_item.clone();
					var t = date_formate(msg[i]['publishTime']);
					feedback_item.children("td").first().children("div").first().children("span").first().html(msg[i]['content']);
					feedback_item.children("td").first().next().children("div").first().html(t);
					feedback_item.children("td").last().children("div").first().children("button").first().
					children("span").first().attr("data-id", msg[i]['id']);
					message_tbody.append(feedback_item);
				}
			}
		});
	}
	function readed_button_state() {
		$("#no_read_button").css("background-color", "#fff");
		$("#readed_button").css("background-color", "#4CAF50");
		$("#deleted_button").css("background-color", "#fff");
		$("#no_read_button").css("color", "black");
		$("#readed_button").css("color", "white");
		$("#deleted_button").css("color", "black");
		sync_feedback_counts();
		
		// 获取反馈列表
		var message_tbody = $("#message_tbody");
		// 获取反馈条目
		var message_item = $('<tr id="message_modle_tr" style="vertical-align: inherit; display: table_row; border-color: inherit;'
				+ 'height: 50px;">'
		+ '<td rowspan="1" colspan="1" class="el-table_2_column_4" style="border-bottom: 1px solid #ebeef5;">'
		+ '	<div class="cell">'
		+ '		<span id="message_content_span" class="message-title">【系统通知】该系统将于今晚凌晨2点到5点进行升级维护</span>'
		+ '	</div>'
		+ '</td>'
		+ '<td rowspan="1" colspan="1" class="el-table_2_column_5  ">'
		+ '	<div class="cell" id="message_date_div">2018-04-19 20:00:00</div>'
		+ '</td>'
		+ '<td rowspan="1" colspan="1" class="el-table_2_column_6">'
		+ '	<div class="cell" style="cursor: pointer;">'
		+ '		<button id="mark_read_button" type="button" class="el-button el-button--default el-button--small"' 
		+ '		style="border-radius: 3px; cursor: pointer; display: inline-block; line-height: 1;'
		+ '		background: #fff; border: 1px solid #dcdfe6; padding: 9px 15px;" onclick="deleted_readed(this)">'
		+ '			<span id="message_state_span" data-id="0" style="cursor: pointer;">删除</span>'
		+ '		</button>'
		+ '	</div>'
		+ '</td>'
		+ '</tr> ');
		// 改变显示状态
		message_item.css("display", "table_row");
		// 将反馈列表清空
		message_tbody.empty();
		// 请求数据
		$.ajax({
			type: "POST",
			url: "feedback/get_feedback",
			data: "isReaded=" + "1",
			dataType: "json",
			success: function(msg){
				for (var i = 0; i < msg.length; ++i) {
					console.log("haha" + msg[i]['id']);
					var feedback_item = message_item.clone();
					var t = date_formate(msg[i]['publishTime']);
					feedback_item.children("td").first().children("div").first().children("span").first().html(msg[i]['content']);
					feedback_item.children("td").first().next().children("div").first().html(t);
					feedback_item.children("td").last().children("div").first().children("button").first().
						children("span").first().html("删除");
					feedback_item.children("td").last().children("div").first().children("button").first().
						children("span").first().attr("data-id", msg[i]['id']);
					message_tbody.append(feedback_item);
				}
			}
		});
	}
	function deleted_button_state() {
		$("#no_read_button").css("background-color", "#fff");
		$("#readed_button").css("background-color", "#fff");
		$("#deleted_button").css("background-color", "#4CAF50");
		$("#no_read_button").css("color", "black");
		$("#readed_button").css("color", "black");
		$("#deleted_button").css("color", "white");
		sync_feedback_counts();
		
		// 获取反馈列表
		var message_tbody = $("#message_tbody");
		// 获取反馈条目
		var message_item = $('<tr id="message_modle_tr" style="vertical-align: inherit; display: table_row; border-color: inherit;'
				+ 'height: 50px;">'
		+ '<td rowspan="1" colspan="1" class="el-table_2_column_4" style="border-bottom: 1px solid #ebeef5;">'
		+ '	<div class="cell">'
		+ '		<span id="message_content_span" class="message-title">【系统通知】该系统将于今晚凌晨2点到5点进行升级维护</span>'
		+ '	</div>'
		+ '</td>'
		+ '<td rowspan="1" colspan="1" class="el-table_2_column_5  ">'
		+ '	<div class="cell" id="message_date_div">2018-04-19 20:00:00</div>'
		+ '</td>'
		+ '<td rowspan="1" colspan="1" class="el-table_2_column_6">'
		+ '	<div class="cell" style="cursor: pointer;">'
		+ '		<button id="mark_read_button" type="button" class="el-button el-button--default el-button--small"' 
		+ '		style="border-radius: 3px; cursor: pointer; display: inline-block; line-height: 1;'
		+ '		background: #fff; border: 1px solid #dcdfe6; padding: 9px 15px;" onclick="restore_deleted(this)">'
		+ '			<span id="message_state_span" data-id="0" style="cursor: pointer;">还原</span>'
		+ '		</button>'
		+ '	</div>'
		+ '</td>'
		+ '</tr> ');
		// 改变显示状态
		message_item.css("display", "table_row");
		// 将反馈列表清空
		message_tbody.empty();
		// 请求数据
		$.ajax({
			type: "POST",
			url: "feedback/get_deleted_feedback",
			dataType: "json",
			success: function(msg){
				for (var i = 0; i < msg.length; ++i) {
					console.log("haha" + msg[i]['id']);
					var feedback_item = message_item.clone();
					var t = date_formate(msg[i]['publishTime']);
					feedback_item.children("td").first().children("div").first().children("span").first().html(msg[i]['content']);
					feedback_item.children("td").first().next().children("div").first().html(t);
					feedback_item.children("td").last().children("div").first().children("button").first().
						children("span").first().html("还原");
					feedback_item.children("td").last().children("div").first().children("button").first().
						children("span").first().attr("data-id", msg[i]['id']);
					message_tbody.append(feedback_item);
				}
			}
		});
	}
	
	function sync_feedback_counts() {
		$.ajax({
			type: "POST",
			url: "feedback/get_diff_feedback_counts",
			dataType: "json",
			success: function(msg){
				var no_read = msg['no_read'];
				var readed = msg['readed'];
				var deleted = msg['deleted'];
				
				$("#no_read_span").html("(" + no_read + ")");
				$("#readed_span").html("(" + readed + ")");
				$("#deleted_span").html("(" + deleted + ")");
			}
		});
	}
	
</script>
<style type="text/css">
	body{
		background: #f0f0f0;
	}
	.button_state {
		float: left;
	    background-color: #4CAF50;
	    border: none;
	    color: white;
	    padding: 10px 32px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 16px;
	    margin: 4px 2px;
	    cursor: pointer;
	}

</style>
</head>
<body>
	<div class="ui_content">
		<div class="ui_text_indent">
			<div id="box_border" style="margin-left: 20px; margin-right: 20px; margin-top: 40px; background: #ffffff;">
				<div id="box_top" style="height: 50px; background: #ffffff; margin-top: 30px; padding-left: 30px;">
					<button class="button_state" id="no_read_button" onclick="default_button_state()">未读消息<span id="no_read_span">(0)</span></button>
					<button class="button_state" id="readed_button" onclick="readed_button_state()">已读消息<span id="readed_span">(0)</span></button>
					<button class="button_state" id="deleted_button" onclick="deleted_button_state()">回收站<span id="deleted_span">(0)</span></button>
				</div>
				<div style="padding-left:30px; padding-right: 20px; background: #ffffff;">
					<table style="width: 100%;">
						<tbody id="message_tbody" style="vertical-align: middle; display: table-row-group; border-color: #ddd; background: #fff">
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
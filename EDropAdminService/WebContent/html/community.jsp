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
<title>发表社区文章</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>

    <link rel="stylesheet" href="html/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="html/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" href="html/css/community.css"/>
    <link rel="stylesheet" href="html/css/editormd.min.css"/>
    <script src="html/js/community.js" type="application/javascript"></script>
    <script src="html/js/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="html/js/bootstrap.min.js" type="application/javascript"></script>
    <script src="html/js/editormd.min.js" type="application/javascript"></script>
    <script type="text/javascript">
		$(document).ready(function() {
			/*
			 * 提交
			 */
			$("#submitbutton").click(
				function(){
					$.ajax({
						type: "POST",
						url: "community/add",
						data: $('#submitForm').serialize(),
						dataType : "text",
						cache:false,
						success : function(dates){
							if(dates =='success'){
								/**  发表成功**/
								//window.parent.$.fancybox.close();
								alert("发表成功");
							}
							
						},
						 error:function(error){alert(error);}
					});
			});
		});
</script>
</head>
<body>
	<div class="container-fluid main">
        <div class="row">
            <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
            <h2><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>发表文章</h2>
            <hr/>
            <form action="" method="post" id="submitForm" name="submitForm">
                <input type="hidden" name="id"/>
                <div class="form-group">
                    <label for="title">文章标题(简单扼要):</label>
                    <input type="text" name="title"  autocomplete="off" class="form-control"
                           id="title" placeholder="文章标题..."/>
                    <label for="title">文章描述(描述信息):</label>
                    <input type="text" name="description" id="description" autocomplete="off" class="form-control"
                           id="title" placeholder="文章描述..."/>
                </div>
                <div class="form-group" id="question-editor">
                        <label for="title">文章内容(必填，请参照右侧提示):</label>
                        <textarea style="display: none;" name="content" id="content"
                                  class="form-control"
                                  cols="30" rows="10"></textarea>
                </div>
                <script type="text/javascript">
                    $(function () {
                        var editor = editormd("question-editor", {
                            width: "100%",
                            height: 400,
                            path: "html/js/lib/",
                            delay: 0,
                            watch: false,
                            placeholder: "请输入文章内容",
                            emoji:true,
                            imageUpload: true,
                            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                            imageUploadURL: "<%=basePath %>community/upload",
                        });
                    });
                    
                </script>

                <div class="form-group">
                    
                    
                </div>
                <div class="container-fluid main ">
                    <div class="row">
                        <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
                            <button type="submit" id="submitbutton" class="btn btn-success btn-publish ">
                           		     发布
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
            <h3>文章发表指南</h3>
            • 文章标题: 请用精简的语言描述您发布的文章标题，不超过25字 <br/>
            • 文章内容: 详细补充您的文章内容，并对您的文字负责。<br/>
            <br/>
        </div>
    </div>
</div>

</body>
</html>
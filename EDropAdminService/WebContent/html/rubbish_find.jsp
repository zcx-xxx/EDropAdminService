<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
								<td><a href="rubbish/edit?name=${p.name}"
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
</body>
</html>
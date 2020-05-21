<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<div class="ui_content" id="updatediv">
				<div class="ui_tb" >
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" onclick="selectOrClearAllCheckbox(this);" />
							</th>
							<th>订单编号</th>
							<th>联系人</th>
							<th>联系电话</th>
							<th>用户id</th>
							<th>接单id</th>
							<th>详细地址</th>
							<th>下单时间</th>
							<th>接单时间</th>
							<th>订单状态</th>
						</tr>
						<c:forEach items="${pageInfo.list }" var="p">
							<tr>
								<td><input type="checkbox" name="IDCheck"
									value="14458579642011" class="acb" /></td>
								<td>${p.number }</td>
								<td>${p.ouname }</td>
								<td>${p.outelephone }</td>
								<td>${p.userId }</td>
								<td>${p.employeeId }</td>
								<td>${p.orderAddress }</td>
								<td>${p.reserveTime }</td>
								<td>${p.createTime }</td>
								<td>
									<c:if test="${p.state==0 }">未完成</c:if>
									<c:if test="${p.state==1 }">已完成</c:if>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Txw Pet Shop</title>
<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/icon.ico" media="screen" />

</head>
<body>
	${ sessionScope.adminUser.getUserName()} 您好!<br>
	<br>
	<c:if test="${orders == null || pageSize == 0 || pageNo < 1 || pageNo > pageCount }">
		没有任何记录。
	</c:if>
	<c:if test="${orders != null && pageSize > 0 && pageNo ge 1 && pageNo <= pageCount }">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>ID</th>
				<th>用户</th>
				<th>电话</th>
				<th>地址</th>
				<th>付款情况</th>
				
				<th>订单状态</th>
				
				<th>详细</th>
				<th>加入购物车</th>
			</tr>
			
			<c:forEach items="${orders }" var="order">
				<tr>
					<td>${order.id }</td>
					<td>${order.getUser().getUserName() }</td>
					<td>${order.getUser().getTel() }</td>
					
					<td>${order.getUser().getAddress() }</td>
					<td>
						<c:if test="${order.status == 1}">
							已付款
						</c:if>
						<c:if test="${order.status == 0}">
							未付款
						</c:if>
					</td>
					
					<td>
						<c:if test="${order.complete == 1}">
							已完成
						</c:if>
						<c:if test="${order.complete == 0}">
							未完成
						</c:if>
					</td>
					
					<td><a href="${pageContext.request.contextPath }/orderInfo?id=${order.id }&&userName=${ sessionScope.adminUser.getUserName()}">订单详细</a></td>
					<td>
						<c:if test="${order.complete == 1}">
							订单已完成
						</c:if>
						<c:if test="${order.complete == 0}">
							<a href="${pageContext.request.contextPath }/doOrder?userName=${sessionScope.adminUser.getUserName()}&id=${order.id}">订单完成</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="8">
					共  ${pageSize } 条记录
					共 ${pageCount }  页
					当前第 ${pageNo } 页
					<c:if test="${pageNo > 1 && pageNo <= pageCount }">
						<a href="?pageNo=${pageNo - 1}&userName=${sessionScope.adminUser.getUserName()}">上一页</a>
					</c:if>
					<c:if test="${pageNo ge 1 && pageNo < pageCount }">
						<a href="?pageNo=${pageNo + 1}&userName=${sessionScope.adminUser.getUserName()}">下一页</a>
					</c:if>
					
				</td>
			</tr>
			
		</table>
	</c:if>
</body>
</html>
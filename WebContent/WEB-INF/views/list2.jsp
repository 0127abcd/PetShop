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
	${ sessionScope.user.getUserName()} 您好! <a href="/logOff">注销</a><br>
	<a href="login?petCategory=cat&userName=${sessionScope.user.getUserName()}">cat</a>
	<a href="login?petCategory=dog&userName=${sessionScope.user.getUserName()}">dog</a>
	<br>
	<c:if test="${pets == null || pageSize == 0 || pageNo < 1 || pageNo > pageCount }">
		没有任何记录。
	</c:if>
	<c:if test="${pets != null && pageSize > 0 && pageNo ge 1 && pageNo <= pageCount }">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>ID</th>
				<th>PetName</th>
				<th>PetCategory</th>
				
				<th>PetCount</th>
				<th>PetPrice</th>
				
				<th>PetImg</th>
				
				<th>详细</th>
				<th>加入购物车</th>
			</tr>
			
			<c:forEach items="${pets }" var="pet">
				<tr>
					<td>${pet.id }</td>
					<td>${pet.petName }</td>
					<td>${pet.petCategory }</td>
					
					<td>${pet.petCount }</td>
					<td>${pet.petPrice }</td>
					
					<td>${pet.petImg }</td>
					
					<td><a href="${pageContext.request.contextPath }/petInfo?id=${pet.id}&userName=${sessionScope.user.getUserName()}">详细</a></td>
					<td>
						<a href="${pageContext.request.contextPath }/petBuy?id=${pet.id}&userName=${sessionScope.user.getUserName()}&&petCategory=${petCategory}">加入购物车</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="8">
					共  ${pageSize } 条记录
					共 ${pageCount }  页
					当前第 ${pageNo } 页
					<c:if test="${pageNo > 1 && pageNo <= pageCount }">
						<a href="?pageNo=${pageNo - 1}&userName=${sessionScope.user.getUserName()}&&petCategory=${sessionScope.petCategory}">上一页</a>
					</c:if>
					<c:if test="${pageNo ge 1 && pageNo < pageCount }">
						<a href="?pageNo=${pageNo + 1}&userName=${sessionScope.user.getUserName()}&&petCategory=${sessionScope.petCategory}">下一页</a>
					</c:if>
					
				</td>
			</tr>
			
		</table>
	</c:if>
	<br>
	<c:if test="${sessionScope.shopCar != null }">
		<c:forEach items="${sessionScope.shopCar}" var="sPets">
			名称：${sPets.petName }<br>
			价格：${sPets.petPrice } 元<br>
			<a href="${pageContext.request.contextPath }/petDel?id=${sPets.id}&userName=${sessionScope.user.getUserName()}&&petCategory=${sessionScope.petCategory}&&pageNo=${pageNo}">删除</a>
			<br>
		</c:forEach>
		总价：${sum } 元
		<c:if test="${sum != 0 }">
			<a href="${pageContext.request.contextPath }/pay?id=${sessionScope.car_id}&userName=${sessionScope.user.getUserName()}&sum=${sum}">购买</a>
		</c:if>
	</c:if>
</body>
</html>
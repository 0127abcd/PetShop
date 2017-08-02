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
	<a href="${pageContext.request.contextPath }/adminLogin?userName=${sessionScope.adminUser.getUserName()}">返回首页</a><br>
	<c:if test="${pets == null || pageSize == 0 || pageNo < 1 || pageNo > pageCount }">
		没有任何记录。<br>
	</c:if>
	客户：${user.getUserName() }<br>
	地址：${user.getAddress() }<br>
	邮箱：${user.getEmail() }<br>
	电话：${user.getTel() }<br>
	<c:if test="${pets != null && pageSize > 0 && pageNo ge 1 && pageNo <= pageCount }">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>ID</th>
				<th>PetName</th>
				<th>PetCategory</th>
				
				<th>PetCount</th>
				<th>PetPrice</th>
				
				<th>PetImg</th>
				
			</tr>
			
			<c:forEach items="${pets }" var="pet">
				<tr>
					<td>${pet.id }</td>
					<td>${pet.petName }</td>
					<td>${pet.petCategory }</td>
					
					<td>${pet.petCount }</td>
					<td>${pet.petPrice }</td>
					
					<td>${pet.petImg }</td>
					
				</tr>
			</c:forEach>
			<tr>
				<td colspan="8">
					共  ${pageSize } 条记录
					共 ${pageCount }  页
					当前第 ${pageNo } 页
					<c:if test="${pageNo > 1 && pageNo <= pageCount }">
						<a href="?pageNo=${pageNo - 1}&userName=${sessionScope.adminUser.getUserName()}&id=${carId}">上一页</a>
					</c:if>
					<c:if test="${pageNo ge 1 && pageNo < pageCount }">
						<a href="?pageNo=${pageNo + 1}&userName=${sessionScope.adminUser.getUserName()}&id=${carId}">下一页</a>
					</c:if>
					
				</td>
			</tr>
			
		</table>
		总价：${sum } 元<br>
		<c:if test="${isComplete == 1}">
			订单已完成
		</c:if>
		<c:if test="${isComplete == 0}">
			<a href="doOrder?userName=${sessionScope.adminUser.getUserName()}&id=${carId}">订单完成</a>
		</c:if>
	</c:if>

</body>
</html>
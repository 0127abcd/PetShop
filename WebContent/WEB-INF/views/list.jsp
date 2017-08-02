<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet" type="text/css" media="all" />	
<title>Txw Pet Shop</title>
<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/icon.ico" media="screen" />

</head>
<body>
	${ sessionScope.user.getUserName()} 您好! <a href="logOff">注销</a><br>
	<br>
	<c:if test="${pets == null || pageSize == 0 || pageNo < 1 || pageNo > pageCount }">
		没有任何记录。
	</c:if>
	<c:if test="${pets != null && pageSize > 0 && pageNo ge 1 && pageNo <= pageCount }">
			
		<div class="left">
			<ul>
				<li><a href="login?petCategory=cat&userName=${sessionScope.user.getUserName()}">cat</a></li>
				<li><a href="login?petCategory=dog&userName=${sessionScope.user.getUserName()}">dog</a></li>
			</ul>
		</div>
	
		<div class="pets"> 
				<div class="product-one">
					<c:forEach items="${pets }" var="pet">
						<div class="product-left"> 
							<div class="p-one">							
									<a href="${pageContext.request.contextPath }/petInfo?id=${pet.id}&userName=${sessionScope.user.getUserName()}">
										<img src="${pageContext.request.contextPath }/${pet.petImg }" alt="" />
										<div class="mask">
											<span>Quick View</span>
										</div>
									</a>
								<h4>${pet.petName }</h4>
								<p>
									<a class="item_add" href="${pageContext.request.contextPath }/petBuy?id=${pet.id}&userName=${sessionScope.user.getUserName()}&&petCategory=${petCategory}">
									<i></i> 
									<span class=" item_price">${pet.petPrice }</span>
									</a>
								</p>
							
							</div>
						</div>
					</c:forEach>
				</div>
		</div>
		<div class="cart">
			<br>
			<c:if test="${sessionScope.shopCar != null }">
				<c:forEach items="${sessionScope.shopCar}" var="sPets">
					<p>
						名称：${sPets.petName }<br>
						价格：${sPets.petPrice } 元<br>
						<a href="${pageContext.request.contextPath }/petDel?id=${sPets.id}&userName=${sessionScope.user.getUserName()}&&petCategory=${sessionScope.petCategory}&&pageNo=${pageNo}">删除</a>
						<br>
					</p>
				</c:forEach>
				<p>
					总价：${sum } 元
					<c:if test="${sum != 0 }">
						<a href="${pageContext.request.contextPath }/pay?id=${sessionScope.car_id}&userName=${sessionScope.user.getUserName()}&sum=${sum}">购买</a>
					</c:if>
				</p>
			</c:if>
		</div>
		<div class="page">
			<font>
				共  ${pageSize } 条记录
				共 ${pageCount }  页
				当前第 ${pageNo } 页
			</font>
			<p>
				<c:if test="${pageNo > 1 && pageNo <= pageCount }">
					<span><a href="?pageNo=${pageNo - 1}&userName=${sessionScope.user.getUserName()}&&petCategory=${sessionScope.petCategory}">上一页</a></span>
				</c:if>
				<c:if test="${pageNo ge 1 && pageNo < pageCount }">
					<span><a href="?pageNo=${pageNo + 1}&userName=${sessionScope.user.getUserName()}&&petCategory=${sessionScope.petCategory}">下一页</a></span>
				</c:if>
			</p>
		</div>
	</c:if>
	<div class="copyright">
	<p>Copyright &copy; 2017 Txw All rights reserved.</p>
</div>
</body>
</html>
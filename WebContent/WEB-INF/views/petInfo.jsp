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
	宠物名字：${pet.petName }<br>
	库存数量：${pet.petCount }<br>
	价　　格：${pet.petPrice}<br>
	图　　片：<img src="${pageContext.request.contextPath }/${pet.petImg }" height="200px" width="200px"/><br>
	描　　述：${pet.petDisc }<br>
	种　　类：${pet.petCategory }<br>
	<a href="${pageContext.request.contextPath }/petBuy?id=${pet.id}&userName=${sessionScope.user.getUserName()}">加入购物车</a>
	<a href="${pageContext.request.contextPath }/login?userName=${sessionScope.user.getUserName()}">返回首页</a><br>
	评论：<br>
	<c:if test="${comments eq null or comments.size() eq 0}">
		暂无评论
	</c:if>
	<c:if test="${comments ne null and comments.size() ne 0}">
		<c:forEach items="${comments }" var="comment">
			内容：${comment.content} <br>
			用户：<c:if test="${comment.anonymous == 1}">匿名用户</c:if>
				<c:if test="${comment.anonymous == 0}">${comment.getUser().getUserName()}</c:if>
			&nbsp;&nbsp;&nbsp;&nbsp;时间：${comment.date}
			<br><br>
		</c:forEach>
	</c:if>
	<c:if test="${canComment == 1}">
	<form action="doComment?id=${pet.id}&userName=${sessionScope.user.getUserName()}" method="POST">
		<textarea rows="10" cols="30" name="content" 
			style="width: 250px; 
			height: 50px;	
			padding: 15px 30px;
			color: #999;
			font-size: 13px;
			outline: none;
			display: block;
			background:none;
			border: 1px solid #D1D1D1;
			border-radius:10px;
			-webkit-border-radius: 10px;
			-o-border-radius: 10px;
			-moz-border-radius: 10px;
			-ms-border-radius: 10px;
			resize:none;">
		</textarea>
		<br>
		<input type="checkbox" name="anonymous" value="1">匿名
		<input type="submit" value="评论">		
	</form>
	</c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Txw Pet Shop</title>
<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/icon.ico" media="screen" />

</head>
<body>

	<form action="doForgetPassword" method="POST">
		用户：<input type="text" name="userName"/><br>
		邮箱：<input type="text" name="email"/><br>
		电话：<input type="text" name="tel" /><br>
		<input type="submit" value="OK">
	</form>
</body>
</html>
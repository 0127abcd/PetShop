<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" media="all"/>
<title>Txw PetShop 后台管理系统</title>
<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/icon.ico" media="screen" />

</head>
<body>
	
	<div class="login">
	<div class="login-top">
		<h1>后台登录</h1>
		<form action="adminLogin" method="POST">
			<input type="text" name="userName">
			<input type="password" name="password">
	    <div class="forgot">
	    	<input type="submit" value="确认" >
	    </div>
	    </form>
	</div>
	<div class="login-bottom">
		<h3><a href="#">友情链接</a></h3>
	</div>
</div>	
<div class="copyright">
	<p>Copyright &copy; 2017 Txw All rights reserved.</p>
</div>
	
</body>
</html>
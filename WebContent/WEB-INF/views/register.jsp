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

<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#ok").attr("disabled", true);
		$("#userName").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			$(this).val(val);
			
			
			var url = "${pageContext.request.contextPath }/ajaxValidateUserName";
			var args = {"userName":val};
			
			$.post(url, args, function(data){
				if(data == "0"){
					$("font").remove();
					$("#userName").after("<font color='green'>  用户名可用</font>");
					$("#ok").attr("disabled", false);
				} else if(data == "1"){
					$("font").remove();
					$("#userName").after("<font color='red'>  用户名不可用</font>");
					$("#userName").val("");
					$("#userName").focus();
					$("#ok").attr("disabled", true);
				} else{
					alert("网络程序出错！");
				}
			});
		});
	});
	
</script>

</head>
<body>

	<form action="doRegister" method="POST">
		用户：<input type="text" name="userName" id="userName"/><br>
		密码：<input type="password" name="password" id="password"/><br>
		邮箱：<input type="text" name="email" id="email"/><br>
		地址：<input type="text" name="address" id="address"><br>
		电话：<input type="text" name="tel" id="tel"><br>
	<input type="submit" value="OK" id="ok">
	</form>
</body>
</html>
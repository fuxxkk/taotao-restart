<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="/js/jquery-easyui-1.4.5/jquery.min.js"></script>
<title>test</title>
</head>
<body>
	<script type="text/javascript">
		$.ajax({
			url:"http://manage.taotao.com/jsonp.json",
			dataType:"json",
			type:"get",
			success:function(msg){
				console.log(msg.test);
			}
		})
	</script>
</body>
</html>
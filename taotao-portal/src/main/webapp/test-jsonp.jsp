<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
<title>test</title>
</head>
<body>
	<script type="text/javascript">
		/* function aaa(msg){
			console.log(msg.test);
		} */
		$.ajax({
			url:"http://manage.taotao.com/jsonp.jsp",
			dataType:"jsonp",
			success:function(msg){
				console.log(msg.test);
			}
		})
	</script>
	<!-- <script type="text/javascript" src="http://manage.taotao.com/jsonp.jsp?callback=aaa">
	</script> -->
	
</body>
</html>
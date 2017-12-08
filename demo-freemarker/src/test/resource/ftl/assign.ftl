<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<#assign str="hello world">
    字符串:${str}
<hr>
<#assign num =123456>
    数字:${num}
<hr>
<#assign bool=true>
    布尔值:${bool?string}
        ${bool?string("yes","no")}
<hr>
<#assign data=.now>
    日期${data?string("yyyy-MM-dd HH:mm:ss")}
<hr>
<#assign dd="2017-12-08"?date("yyyy-MM-dd")>
    字符串转日期:${dd?string("yyyy年MM月dd日")}
<hr>
获取范围字符:${"1234567890"[0..6]}
<#--  -->



</body>
</html>
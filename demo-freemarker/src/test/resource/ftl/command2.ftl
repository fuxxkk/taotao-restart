<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


循环语句:<br>
<#list empList as emp>
    ${emp} ----名字:${emp.name} ,年龄${emp.age} , 生日:${emp.birthday?string("yyyy年MM月dd日")} .<br>
    <#if emp.workAge gte 7>
        ${emp.name} 假期为7<br>
    <#elseif emp.workAge gte 5>
        ${emp.name} 假期为5<br>
    <#elseif emp.workAge gte 3>
        ${emp.name} 假期为3<br>
    <#else >
         没有假期<br>
    </#if>
</#list>
</body>
</html>
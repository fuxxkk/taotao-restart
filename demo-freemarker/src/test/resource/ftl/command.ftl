<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${emp} ----名字:${emp.name} ,年龄${emp.age} , 生日:${emp.birthday?string("yyyy年MM月dd日")} .

条件控制语句:<br>
<#if emp.workAge gte 7>
    ${emp.name} 假期为7
    <#elseif emp.workAge gte 5>
        ${emp.name} 假期为5
    <#elseif emp.workAge gte 3>
        ${emp.name} 假期为3
    <#else >
         没有假期
</#if>


</body>
</html>
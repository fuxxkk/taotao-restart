<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>testNull</title>
</head>
<body>
名字为空时不显示:${emp.name!}
名字为空时显示: ${emp.name!("名字为空!")}

<#assign bool=true>
bool:${bool???string}


<#assign str="">
<#if str??>
    bool???str存在: ${str}
    <#else >
    bool???str:字符串为空
</#if>

</body>
</html>
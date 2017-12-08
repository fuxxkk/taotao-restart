<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>testNull</title>
</head>
<body>
<#macro sayhello name>
    hello ${name}<br>
</#macro>
<@sayhello name="ljm"/>

<#macro printNum name num=3>
    <#list 1..num as no>
        ${name} --- ${no}
    </#list>
</#macro>
<@printNum name="lisi"/>

</body>
</html>
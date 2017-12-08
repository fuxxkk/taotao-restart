<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>testNull</title>
</head>
<body>
list:<br>
<#assign nums=[1,2,3,4,5,6,7,8]>

循环nums:<br>
<#list nums as num>
    ${num}<br>
</#list>
<hr>
循环lists 5-10:
<#list nums[0..2] as num>
    ${num}<br>
</#list>
<hr>
Map:<br>
<#assign map ={"name":"lisi","age":11}>
循环map:<br>
<#list map?keys as key>
    ${key}----${map["${key}"]}<br>
</#list>

</body>
</html>
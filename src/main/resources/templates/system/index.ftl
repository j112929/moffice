<html>
<head>
    <style>
        body {
            text-align: center;
        }
    </style>
</head>
<body>
<form method="post" action="publish">
    name:<input type="text" name="name"/> description:<input type="text" name="description"/>
    <button type="submit">提交</button>
</form>
<hr/>
<ul>
<#list systems![] as system>
    <li>name:${system.name!}&nbsp;&nbsp;description:${system.description!}</li>
</#list>
</ul>
</body>
</html>
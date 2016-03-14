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
    username:<input type="text" name="username"/> password:<input type="text" name="password"/>
    <button type="submit">提交</button>
</form>
<hr/>
<ul>
<#list users![] as user>
    <li>username:${user.username!}</li>
</#list>
</ul>
</body>
</html>
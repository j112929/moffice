<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Sign in Stock Trading Portfolio Sign in</title>
    <link href="/static/css/bootstrap/3.3.4/bootstrap.css" rel="stylesheet">
    <link href="/static/css/login.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div data-bind="visible: showErrorMessage" class="alert alert-danger">
        Invalid username/password.
    </div>
    <form class="form-signin" method="post" action="login">
        <h3 class="muted form-signin-heading">Please sign in</h3>
        <input type="text" class="input-block-level" placeholder="User name" name="username">
        <input type="password" class="input-block-level" placeholder="Password" name="password">
        <button class="btn btn-primary" type="submit">Sign in</button>
        <hr>
        <p class="text-info">
            <small>
                Log in as <strong>xiaojiang/xiaojiang</strong> or <strong>zhanggj/xiaojiang</strong><br>
                See <code>WebSecurityConfig.java</code></small>
        </p>
    </form>
</div><!-- /container -->

<script src="//cdn.bootcss.com/knockout/2.3.0/knockout-debug.js"></script>
<script type="text/javascript">
    ko.applyBindings({
        showErrorMessage : ko.computed(function() {
            var query = window.location.search;
            return query ? (query.indexOf('error') != -1) : false;
        })
    });
</script>
</body>
</html>
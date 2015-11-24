<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>文艺复管理系统</title>

    <!-- Bootstrap core CSS -->
    <link href="<@spring.url '/css/bootstrap.min.css'/>" rel="stylesheet">
    
    <!-- Custom styles for this template -->
    <link href="<@spring.url '/css/signin.css'/>" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<@spring.url '/js/ie10-viewport-bug-workaround.js'/>"></script>
  </head>

  <body>

    <div class="container">
      <#if spring.status?? && spring.status.errorMessages??>
        <ul>
          <#list spring.status.errorMessages as error>
            <li>${error}</li>
          </#list>
        </ul>
      </#if>

      <form class="form-signin" action="<@spring.url '/login'/>" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        
        <h2 class="form-signin-heading">文艺复管理系统</h2>
        <label for="input帐号" class="sr-only">Username</label>
        <input type="text" id="inputUsername" name="username" class="form-control" placeholder="帐号" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码" required>
        
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  </body>
</html>

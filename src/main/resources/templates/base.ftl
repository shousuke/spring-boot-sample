<#import "/spring.ftl" as spring />

<#macro default_page_title>Welcome!</#macro>

<#macro page_title><@default_page_title /></#macro>

<#macro default_stylesheets>
  <link href="<@spring.url '/css/common.css'/>" rel="stylesheet">
</#macro>

<#macro stylesheets><@default_stylesheets /></#macro>

<#macro default_javascripts>
  <script src="<@spring.url '/js/common.js'/>"></script>
</#macro>

<#macro javascripts></#macro>

<#macro page_sidebar></#macro>

<#macro page_body></#macro>

<#macro display_page>
    <!DOCTYPE html>
    <html lang="en">
      <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
            
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">
            
        <title><@page_title /> - 文艺复管理系统</title>
            
        <!-- Bootstrap core CSS -->
        <link href="<@spring.url '/css/bootstrap.min.css'/>" rel="stylesheet">
        
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="<@spring.url '/js/ie10-viewport-bug-workaround.js'/>"></script>
        
        <@stylesheets />
      </head>
        
      <body>
        <nav class="navbar navbar-dark bg-inverse navbar-fixed-top">
          <div class="container-fluid">
            <span class="navbar-brand">文艺复</span>
            <ul class="nav navbar-nav">
              <li class="nav-item active">
                <a class="nav-link" href="<@spring.url '/dashboard'/>">Dashboard</a>
              </li>
            </ul>
            
            <ul class="nav navbar-nav pull-right">
              <li class="nav-item active">
                <a class="nav-link" href="<@spring.url '/logout'/>">登出</a>
              </li>
            </ul>
          </div>
        </nav>
    
        <div class="container-fluid">
          <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
              <@page_sidebar />
            </div>
            
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
              <@page_body />
            </div>
          </div>
        </div>
    
        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="<@spring.url '/js/jquery-2.1.4.min.js'/>"></script>
        <script src="<@spring.url '/js/bootstrap.min.js'/>"></script>
        <@javascripts />
      </body>
    </html>
</#macro>
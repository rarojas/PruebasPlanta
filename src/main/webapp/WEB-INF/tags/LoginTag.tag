<%@tag description="Master Page" pageEncoding="UTF-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@attribute name="header" fragment="true" %> 
<%@attribute name="titlePage" fragment="true" %> 
<%@attribute name="app" fragment="true" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">       
        <meta http-equiv="X-UA-Compatible" content="IE=9" />
        <meta http-equiv="X-UA-Compatible" content="IE=10" />
        <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
        <title><jsp:invoke fragment="titlePage"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link href="<c:url value="/js/libs/twitter-bootstrap/css/bootstrap-theme.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/js/libs/twitter-bootstrap/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/js/libs/angular-loading-bar/loading-bar.min.css"/>" rel="stylesheet" type="text/css"/>   
        <script src="<c:url value="/js/libs/jquery/jquery.js"/>" type="text/javascript"></script>      
        <jsp:invoke fragment="app"/>
        <style>
             body {
                padding-top: 30px; /* Required padding for .navbar-fixed-top. Change if height of navigation changes. */
            }

            .navbar-fixed-top .nav {
                padding: 25px 0;
            }

            .navbar-fixed-top .navbar-brand {
                padding: 0 15px;
            }

            @media(min-width:768px) {
                body {
                    padding-top: 70px; /* Required padding for .navbar-fixed-top. Change if height of navigation changes. */
                }

                .navbar-fixed-top .navbar-brand {
                    padding: 0;
                }
            }
        </style>
    </head> 
    <body>                       
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">
                        <img src="<c:url value="/img/Logos/Logo ok fondos de color RGB-03.png"/>" class="img-responsive" width="150" alt=""/>
                    </a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#" style="padding:0px">
                                <img style="width:150px" src="<c:url value="/img/Selmec.png"/>" class="img-responsive" width="150" alt=""/>
                            </a>
                        </li>                        

                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <div class="container-fluid"> 
            <div class='col-md-12'>
                <jsp:doBody/>
            </div>
        </div>     
    </body>  
</html>

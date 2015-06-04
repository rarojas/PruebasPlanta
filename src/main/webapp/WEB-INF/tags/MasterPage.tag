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
        <script src="<c:url value="/js/libs/jquery/jquery.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/js/libs/d3/d3.v3.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/js/libs/nvd3/nv.d3.js"/>" type="text/javascript"></script>                
        <script src="<c:url value="/js/libs/angular.js/angular.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/angular.js/i18n/angular-locale_es-mx.js"/>"></script> 
        <script src="<c:url value="/js/libs/angular-directives-d3/angularjs-nvd3-directives.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/n3-linecharts/line-chart.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/angular.js/angular-resource.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/angular.js/angular-cookies.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/angular.js/angular-animate.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/angular.js/angular-route.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/angular.js/angular-touch.min.js"/>"></script>            
        <script src="<c:url value="/js/libs/angular.js/angular-messages.min.js"/>"></script>    
        <script src="<c:url value="/js/app/app.js"/>"></script>            
        <script src="<c:url value="/js/app/services.js"/>"></script>  
        <script src="<c:url value="/js/app/routes.js"/>"></script>  
        <script src="<c:url value="/js/app/directives.js"/>"></script>     

        <script src="<c:url value="/js/libs/moment.js/moment.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/moment.js/langs.min.js"/>"></script> 
        <script src="<c:url value="/js/angular-moment/angular-moment.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-loading-bar/loading-bar.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-slider/angular-slider.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/twitter-bootstrap/js/bootstrap.min.js"/>"></script>     
<!--        <script src="<c:url value="/js/libs/angular-strap/angular-strap.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-strap/angular-strap.tpl.min.js"/>"></script> -->

        
        <script src="<c:url value="/js/libs/angular-ui-bootstrap/angular-ui-bootstrap.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-ui-bootstrap/angular-ui-bootstrap-tpls.min.js"/>"></script>     

        <script src="<c:url value="/js/libs/jquery-noty/jquery.noty.packaged.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-gantt/angular-gantt.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-gantt/angular-gantt-plugins.js"/>"></script>     
        <script src="<c:url value="/js/libs/lodash/lodash.min.js"/>"></script>  
        <script src="<c:url value="/js/libs/angular-multiselect/angularjs-dropdown-multiselect.min.js"/>"></script>  

        <script src="<c:url value="/js/angular-gm/angular-gm.min.js"/>"></script>  
        <script src="//maps.googleapis.com/maps/api/js?sensor=false"></script>
        <script src="http://code.highcharts.com/highcharts.js"></script>

        <link href="<c:url value="/js/libs/twitter-bootstrap/css/bootstrap-theme.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/js/libs/twitter-bootstrap/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/js/libs/angular-loading-bar/loading-bar.css"/>" rel="stylesheet" type="text/css"/>   
        <link href="<c:url value="/js/libs/angular-gantt/angular-gantt.css"/>" rel="stylesheet" type="text/css"/>   
        <link href="<c:url value="/js/libs/angular-gantt/angular-gantt-plugins.css"/>" rel="stylesheet" type="text/css"/>   
        <link href="<c:url value="/js/css/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>   
        <link href="<c:url value="/js/css/sb-admin.css"/>" rel="stylesheet" type="text/css"/>   
        <link href="<c:url value="/js/css/typehead.css"/>" rel="stylesheet" type="text/css"/>   
        <style>
            .gauge {
                width:250px;
                height:125px;
            }
            .square{
                width: 15px;
                height: 15px;
                display: block;
                float: left;
                margin-right: 10px;                
            }
        </style>
        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
            $(function () {
                $("#menu-toggle").click(function (e) {
                    e.preventDefault();
                    $("#wrapper").toggleClass("toggled");
                    $(".side-nav").toggleClass("toggled");
                });
            });
        </script>
        <jsp:invoke fragment="app"/>        
    </head> 

    <body ng-app="PlantaAPP">               
        <div id="wrapper" class="toggled">        
            <asidemenu></asidemenu>
            <div id="page-wrapper">
                <div class="container-fluid"> 
                    <div class='col-md-12'>
                        <jsp:doBody/>
                    </div>
                </div>   
            </div>
        </div>       
    </body>  
</html>
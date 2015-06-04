<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-default  navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button"  class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"  ng-show="user !== undefined">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#/Inicio" id="menu-toggle" >
            <img style="width:150px" src="/img/EV.png" class="img-responsive hidden-xs" alt="" />
        </a>                    
    </div>
    <!-- Top Menu Items -->
    <ul class="nav navbar-right top-nav">
        <li class="dropdown" >
            <a href class="dropdown-toggle" data-toggle="dropdown">                            
                <i class="fa fa-user" ></i>                            
                Bienvenid@ {{user.nombre}} 
                <b class="caret"></b>                            
            </a>
            <ul class="dropdown-menu" ng-show="user !== undefined">
                <li>
                    <a href="#/MiCuenta"><i class="fa fa-fw fa-user"></i> Cuenta</a>
                </li>                
                <li class="divider"></li>
                <li>                    
                    <a href="/j_spring_security_logout"><i class="fa fa-fw fa-power-off"></i> Cerrar Sesión</a>
                </li>
            </ul>
        </li>
        <li class="hidden-xs">
            <a href style="padding:0px" >
                <img src="/img/LogoSelmec.png" class="img-responsive" style="height: 50px" alt=""/>
            </a>
        </li>
    </ul>    
    <div bs-navbar class="collapse navbar-collapse navbar-ex1-collapse" >
        <ul class="nav navbar-nav side-nav toggled" ng-show="user !== undefined">
            <sec:authorize access="hasRole('Administrador') || hasRole('Tecnico Pruebas Ensamble') || hasRole('Supervisor Pruebas Ensamble')">
                <li data-match-route="/NuevaPrueba" title="Registra nueva planta para pruebas de ensamble" tooltip>
                    <a href="#/NuevaPrueba">                                
                        Nueva
                    </a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('Tecnico Pruebas Ensamble') || hasRole('Administrador') || hasRole('Supervisor Pruebas Ensamble')" >
                <li data-match-route="/Pruebas" title="Inventario de pruebas de ensamble" tooltip>
                    <a href="#/Pruebas">Pruebas</a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('Administrador') || hasRole('Supervisor Pruebas Ensamble')">
                <li data-match-route="/ProgramacionPruebasEnsamble" >
                    <a href="#/ProgramacionPruebasEnsamble">Programación de Pruebas de Ensamble </a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('Administrador')">
                <li data-match-route="/ProgramacionPruebasArranque" >
                    <a href="#/ProgramacionPruebasArranque">Programación de Pruebas de Arranques</a>                            
                </li>
            </sec:authorize>    
            <sec:authorize access="hasRole('Administrador')">
                <li data-match-route="/NuevoArranque" >
                    <a href="#/NuevoArranque">Nuevo Arranque</a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('Administrador')">
                <li data-match-route="/PruebasArranque" >
                    <a href="#/PruebasArranque">Pruebas Arranque</a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('Administrador') || hasRole('Supervisor Pruebas Ensamble')">
                <li data-match-route="/Clientes" ">
                    <a href="#/Clientes">Clientes</a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('Administrador') || hasRole('Supervisor Pruebas Ensamble')">
                <li data-match-route="/Motores" >
                    <a href="#/Motores" class="dropdown-toggle">Motores</a>                                        
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('Administrador') || hasRole('Supervisor Pruebas Ensamble')">
                <li data-match-route="/Incidencias">
                    <a href="#/Incidencias" class="dropdown-toggle">Incidencias</a>                                        
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('Administrador') || hasRole('Supervisor Pruebas Ensamble')">
                <li data-match-route="/Usuarios" >
                    <a href="#/Usuarios" class="dropdown-toggle">Usuarios</a>                                        
                </li> 
            </sec:authorize>
            <sec:authorize access="hasRole('Administrador')">
                <li data-match-route="/Ubicaciones" >
                    <a href="#/Ubicaciones" class="dropdown-toggle">Ubicaciones</a>                                        
                </li> 
            </sec:authorize>
            <sec:authorize access="hasRole('Administrador') || hasRole('Supervisor Pruebas Ensamble')">
                <li data-match-route="/Carriles" >
                    <a href="#/Carriles" class="dropdown-toggle">Carriles</a>                                        
                </li> 
            </sec:authorize>
            <sec:authorize access="hasRole('Administrador')">
                <li data-match-route="/Kits" >
                    <a href="#/Kits" class="dropdown-toggle">Kits</a>                                        
                </li>                                
            </sec:authorize>
            <sec:authorize access="hasRole('Administrador') || hasRole('Supervisor Pruebas Ensamble')">
                <li data-match-route="/Generador">
                    <a href="#/Generador" class="dropdown-toggle">Generadores</a>                                        
                </li>                                
            </sec:authorize>
        </ul>
    </div>    
</nav>

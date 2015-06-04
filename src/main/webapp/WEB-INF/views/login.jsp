<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:LoginTag>
    <jsp:attribute name="titlePage">        
        Pruebas de Ensamble De Plantas
    </jsp:attribute> 
    <jsp:attribute name="app">
        <style>
            .error {
                padding: 15px;
                margin-bottom: 20px;
                border: 1px solid transparent;
                border-radius: 4px;
                color: #a94442;
                background-color: #f2dede;
                border-color: #ebccd1;
            }

            .msg {
                padding: 15px;
                margin-bottom: 20px;
                border: 1px solid transparent;
                border-radius: 4px;
                color: #31708f;
                background-color: #d9edf7;
                border-color: #bce8f1;
            }

            #login-box {
                width: 300px;
                padding: 20px;
                margin: 100px auto;
                background: #fff;
                -webkit-border-radius: 2px;
                -moz-border-radius: 2px;
                border: 1px solid #000;
            }
        </style>    
    </jsp:attribute>
    <jsp:body>  
        <div class="row text-center">
            <h1>Pruebas de Ensamble De Plantas</h1>
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-4 ">
                <form name='loginForm' action="<c:url value='j_spring_security_check' />" method='POST' class="form-horizontal">
                    <fieldset>
                        <legend>Ingreso al sistema</legend>
                        <c:if test="${not empty error}">
                            <div class="error">${error}</div>
                        </c:if>
                        <c:if test="${not empty msg}">
                            <div class="msg">${msg}</div>
                        </c:if>
                        <div class="form-group">
                            <label class="control-label col-md-4">  
                                Usuario
                            </label>
                            <div class="col-md-8">
                                <input type="text" name="username" class="form-control"/>
                            </div>                       
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4">
                                Password
                            </label>
                            <div class="col-md-8">
                                <input type="password" name="password" class="form-control"/>
                            </div>                       
                        </div>
                        <div class="form-group">                    
                            <div class="col-md-8 col-md-offset-4">
                                <button type="submit"  class="btn btn-success">
                                    Ingresar
                                </button>
                            </div>                       
                        </div>              
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </fieldset>
                </form>
            </div>
        </div>
    </jsp:body>
</t:LoginTag>
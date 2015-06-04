<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<t:LoginTag>
    <jsp:attribute name="titlePage">        
        Establecer Contraseña
    </jsp:attribute>    
    <jsp:body>  
        <div class="row text-center">
            <h1>Establecer Contraseña</h1>
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-4 ">
                <form:form name='loginPassword' action="<c:url value='/SetPassword' />" method='POST' class="form-horizontal">
                    <div class="form-group">
                        <form:label path="password" class="control-label col-md-4">Contraseña</form:label>
                            <div class="col-md-8">
                            <form:password path="password" class="form-control" />
                        </div>                        
                    </div>
                    <div class="form-group">
                        <form:label path="confirm" class="control-label col-md-4">Confirma Contraseña</form:label>
                            <div class="col-md-8">
                            <form:password path="confirm" class="form-control" />
                        </div>                        
                    </div>
                    <div class="form-group">                        
                        <div class="col-md-4-offset col-md-4">
                            <button type="submit" class="btn bnt-success">Enviar</button>
                        </div>                                                
                    </div>
                </form:form>                
            </div>
        </div>
    </jsp:body>
</t:LoginTag>
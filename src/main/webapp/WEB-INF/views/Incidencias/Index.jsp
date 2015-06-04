<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:MasterPage>   
    <jsp:attribute name="titlePage">
        Incidencias
    </jsp:attribute> 
    <jsp:attribute name="app">
        <script src="<c:url value="/js/app/Incidencias/controllers.js"/>"></script> 
        <script src="<c:url value="/js/app/Incidencias/routes.js"/>"></script> 
    </jsp:attribute> 
    <jsp:body>        
        <ng-view>
        </ng-view>
    </jsp:body>
</t:MasterPage>
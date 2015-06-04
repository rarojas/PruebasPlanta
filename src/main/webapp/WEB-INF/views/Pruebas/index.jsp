<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:MasterPage>
    <jsp:attribute name="titlePage">
      Pruebas de Ensamble
    </jsp:attribute> 
    <jsp:body>
        <div ng-view>
        </div>
    </jsp:body>
</t:MasterPage>
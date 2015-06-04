<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:MasterPage>
    <jsp:attribute name="titlePage">
        <h1>Motores</h1>
    </jsp:attribute>    
    <jsp:body>        
        <div ng-controller="MotoresController">
            <div class="row">
                <div class="col-md-12">
                    <table class="table">
                        <tbody>
                            <tr ng-repeat="motor in motores">
                                <td>{{$index}}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</t:MasterPage>
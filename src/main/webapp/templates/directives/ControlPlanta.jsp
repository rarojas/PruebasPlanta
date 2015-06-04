<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="row">
    <div class="col-md-12">
        <sec:authorize access="hasRole('Tecnico Pruebas Ensamble')">
            <fieldset>
                <legend>Prueba</legend>
                <div class="btn-group">                                 
                    <button type="button" class="btn btn-success" ng-click="Enable()" ng-show="prueba === undefined && OptionsControl.Locked">
                        <span class='glyphicon glyphicon-ok'></span><br>
                        Iniciar Prueba
                    </button>                
                    <a class="btn btn-danger" href="#/Pruebas/{{ensamble.id}}" ng-show="prueba !== undefined && Estado !== Estatus.Running">
                        <span class='glyphicon glyphicon-stop'></span><br>
                        Salir Prueba                        
                    </a>
                    <button type="button" class="btn btn-success" ng-click="Start()" ng-show="!OptionsControl.Locked && Estado !== Estatus.Running && prueba === undefined">
                        <span class='glyphicon glyphicon-play'></span><br>
                        START                           
                    </button>
                    <button type="button" class="btn btn-danger" ng-click="StopButton()" ng-show="!OptionsControl.Locked && Estado === Estatus.Running">
                        <span class='glyphicon glyphicon-stop'></span><br>
                        STOP                                           
                    </button>                
                </div>
            </fieldset>
        </sec:authorize>
    </div>
</div>
<div class="col-md-12" >
    <sec:authorize access="hasRole('Tecnico Pruebas Ensamble')">
        <fieldset>
            <legend>Control Planta : <label ng-style="{ color: Estado.color}" class="label">{{Estado.text}}</label>  </legend>                
            <div class="btn-group">
                <button type="button" class="btn " ng-click="ArranquePlanta()" >
                    <img src="../../img/Icons/Arranque.png" alt="" width="50"/>
                </button>
                <button type="button" class="btn" ng-click="ParoPlanta()"    >
                    <img src="../../img/Icons/PARO.png" alt="" width="50"/>                          
                </button>            
            </div>
        </fieldset>    
    </sec:authorize>                
</div>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<fieldset>
    <legend>Datos Prueba</legend>
    <div class="row">                        
        <div class="col-md-6">
            <i class="fa fa-clock-o"></i> 
            <label>Fecha y Hora de Inicio</label>
        </div>                            
        <div class="col-md-6">
            <label class="label label-success">{{prueba.dtInicio| date : 'dd-MM-yyyy hh:mm:ss'}}</label>
        </div>
    </div>                                            
    <div class="row">                        
        <div class="col-md-6">
            <i class="fa fa-clock-o"></i> <label>Fecha y Hora de Fin</label>
        </div>                            
        <div class="col-md-6">
            <label class="label label-success">{{prueba.dtFin| date : 'dd-MM-yyyy hh:mm:ss'}}</label>
        </div>
    </div>                                                                                                                                                
    <div class="row">                        
        <div class="col-md-6">
            <i class="fa fa-info-circle"></i>    <label>Folio</label>
        </div>                            
        <div class="col-md-6">
            <label class="label label-success">{{ensamble.folio}}</label>
        </div>
    </div> 
    <div class="row">                        
        <div class="col-md-6">
            <i class="fa fa-bars"></i>  <label>Carril de prueba</label>
        </div>                            
        <div class="col-md-6">
            <label class="label label-success">{{ensamble.carriles.noCarril}} - De {{ensamble.carriles.minCap}} Kw a {{ensamble.carriles.maxCap}}</label>
        </div>
    </div>
    <div class="row" ng-show="AccumulateTime !== null">                        
        <div class="col-md-6">
            <i class="fa fa-clock-o"></i> <label>Tiempo Prueba</label>
        </div>                            
        <div class="col-md-6">
            <label  class="label label-primary">{{AccumulateTime| minutos}}</label> 
        </div>
    </div>   

    <div class="row">                        
        <div class="col-md-6">
            <i class="fa fa-remove"></i> <label>Valores fuera de rango</label>
        </div>                            
        <div class="col-md-6">
            <label  class="label label-primary">{{log.length}}</label> 
        </div>
    </div> 
    <div class="row">                        
        <div class="col-md-6">
            <i class="fa fa-exclamation"></i> <label>Estatus</label>
        </div>                            
        <div class="col-md-6">
            <label  class="label label-primary">{{prueba.estatus}}</label> 
        </div>
    </div>
    <div class="row">                        
        <div class="col-md-6">
            <label></label>
        </div>
        <div class="col-md-6">
            <label  class="label label-primary"></label> 
        </div>
    </div>
    <fieldset ng-show="((prueba.estatus === 'Finalizada'))">
        <legend>Resultados de la Prueba Ejecutor</legend>
        <div class="form-group">                        
            <div class="col-md-12">
                <button ng-click="ApruebaPruebaTecnico()" type="button"  class="btn btn-success" >
                    Autorizar
                </button>
                <button ng-click="RechazaPruebaTecnico()" class="btn btn-danger"type="button" >
                    Rechazar
                </button>
            </div>
        </div>
    </fieldset>
    <div class="form-group">                        
        <div class="col-md-12">
            <a href="#/PruebasArranque/{{ensamble.id}}" class="btn btn-primary">
                <i class="fa fa-reply"></i>
                Regresar a pruebas</a>
        </div>
    </div>    
</fieldset>
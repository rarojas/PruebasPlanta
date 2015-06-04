<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:MasterPage>
    <jsp:attribute name="titlePage">
        Pruebas de Ensamble
    </jsp:attribute> 
    <jsp:body>
        <div ng-controller="EnsambleController" class="container">    
            <form class="form-horizontal" role="form" name="formPlanta" ng-submit="submit()">                
                <div class="row">
                    <div class="col-md-12">
                        <fieldset>
                            <legend class="h3">
                                Datos Generales de la Planta de Emergencia                                                        
                            </legend>
                            <div class="row">
                                <div class="col-md-4">
                                    <h3>Planta de Emergencia</h3>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Modelo:                                       
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" required ng-model="planta.modelo" name="modelo">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            No. Serie:                                       
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" required ng-model="planta.noSerie" name="serie">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            No. Op                                       
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" required ng-model="planta.noOp" name="produccion">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <h3>Motor</h3>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Motores:
                                        </label>
                                        <div class="col-md-8">
                                            <select name="motor" ng-options="m.modelo as  m.modelo group by m.marca for m in motores" ng-model="planta.motores.modelo" required class="form-control" ></select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">
                                            Marca:                                        
                                        </label>                                        
                                        <label class="control-label col-md-8">{{ SelectedMotor().marca}}</label>                                        
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">
                                            Modelo:                                        
                                        </label>                                        
                                        <label class="control-label col-md-8">{{ SelectedMotor().modelo}}</label>                                        
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            No. Serie                                        
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" required ng-model="planta.motorSerie" name="serieMotor">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <h3>Generador</h3>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Marca:                                        
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" required name="generador_marca">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Modelo:                                        
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" required name="generador_modelo">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            No. Serie                                        
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" required name="generador_serie">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </fieldset>                    
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">                        
                        <fieldset>
                            <legend class="h3">
                                Datos Especificos de la Planta De Emergencia                                              
                            </legend>
                            <div class="row">
                                <div class="col-md-4">                                    
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Tipo de Servicio                                
                                        </label>
                                        <div class="col-md-8">
                                            <select class="form-control" ng-options="c.id as c.text for c in tipoServicio" ng-model="planta.tipoOperacion" name="tipoOperacion"></select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            KW
                                        </label>
                                        <div class="col-md-8">
                                            <input type="number" class="form-control" required min="0" name="kw"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            KVA
                                        </label>
                                        <div class="col-md-8">
                                            <input type="number" class="form-control" required min="0" name="kva">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Corriente de operación
                                        </label>
                                        <div class="col-md-8">
                                            <input type="number" class="form-control" required min="0" name="capInt">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Tipo Control                                        
                                        </label>
                                        <div class="col-md-8">
                                            <select class="form-control" ng-options="c.id as c.text for c in tipoControl" ng-model="planta.controlId" name="tipoControl"></select>  
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Tipo Combustible                                        
                                        </label>
                                        <div class="col-md-8">
                                            <select class="form-control" ng-options="c.id as c.text for c in combustible" ng-model="tcombustible" name="tipoCombustible"></select>  
                                        </div>
                                    </div>                                  
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Carril                                     
                                        </label>
                                        <div class="col-md-8">
                                            <select class="form-control" ng-options="c.id as c.text for c in carriles" ng-model="planta.carrilId" name="tipoCombustible"></select>  
                                        </div>
                                    </div>     
                                      <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Carril                                     
                                        </label>
                                        <div class="col-md-8">
                                            <select class="form-control" ng-options="c.id as c.text for c in carriles" ng-model="planta.carrilId" name="tipoCombustible"></select>  
                                        </div>
                                    </div>     
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Altitud                                     
                                        </label>
                                        <div class="col-md-8">
                                            <input type="number"  class="form-control" required ng-model="planta.altitud" name="altitud">
                                        </div>
                                    </div>  
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Patin                                     
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text"   class="form-control" required ng-model="planta.patin" name="patin">
                                        </div>
                                    </div>  
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Guardas                                     
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text"   class="form-control" required ng-model="planta.guardas" name="guardas">
                                        </div>
                                    </div>  
                                </div>
                                <div class="col-md-4">                                    
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Cap. Interruptor                                        
                                        </label>
                                        <div class="col-md-8">
                                            <input type="number"  min="0" class="form-control" required ng-model="plata.capInterruptor" name="capInt">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Fases:                                        
                                        </label>
                                        <div class="col-md-8">
                                            <input type="number" min="1" max="3" class="form-control" required ng-model="planta.noFases" name="nofases">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4" >
                                            Voltaje:                                        
                                        </label>
                                        <div class="col-md-8">
                                            <input type="number" min="0" class="form-control" required ng-model="planta.voltajeOperacion" name="voltaje">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">
                                            Frecuencia:                                       
                                        </label>
                                        <div class="col-md-8">
                                            <input type="number" min="0" class="form-control" required ng-model="planta.frecuenciaOperacion" name="frecuencia">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 text-right">
                        <button class="btn btn-primary" type="submit" ng-disabled="formPlanta.$invalid">
                            Guardar y Comenzar las Pruebas
                        </button>
                    </div>
                </div>
            </form>

        </div>
    </jsp:body>
</t:MasterPage>
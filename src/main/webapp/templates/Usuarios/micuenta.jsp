<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="col-md-12">
    <div class="row">
        <div class="col-md-12">
            <h1>Mi Cuenta</h1>
        </div>        
    </div>
    <div class="row">
        <div class="col-md-12">
            <form class="form-horizontal" role="form" name="formMiCuenta" ng-submit="ChangeDataUser()" novalidate>
                <fieldset>
                    <legend>Mis Datos</legend>
                    <div class="form-group">
                        <label class="control-label col-md-4">Nombre</label>
                        <div class="col-md-8">
                            <input class="form-control" ng-model="usuario.nombre" required type="text" name="nombre">
                            <div ng-if="interacted(formMiCuenta.nombre)" ng-messages="formMiCuenta.nombre.$error"  ng-messages-include="/templates/directives/formsErrors.html">
                            </div>
                        </div>
                    </div>                    
                    <div class="form-group">
                        <label class="control-label col-md-4">Apellidos</label>
                        <div class="col-md-8">
                            <input class="form-control" ng-model="usuario.apellidos" required type="text" name="apellidos">
                            <div ng-if="interacted(formMiCuenta.apellidos)" ng-messages="formMiCuenta.apellidos.$error"  ng-messages-include="/templates/directives/formsErrors.html">
                            </div>
                        </div>
                    </div>                    
                    <div class="form-group">
                        <label class="control-label col-md-4">Email (usuario)</label>
                        <div class="col-md-8">
                            <input class="form-control" ng-model="usuario.email" required type="email" name="email" >
                            <div ng-if="interacted(formMiCuenta.email)" ng-messages="formMiCuenta.email.$error"  ng-messages-include="/templates/directives/formsErrors.html">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">Roles</label>
                        <div class="col-md-8">
                            <ul ng-repeat="rol in usuario.roles" class="list-group">
                                <li class="list-group-item">
                                    {{rol}}
                                </li>
                            </ul>
                        </div>
                    </div>                   
                    <div class="form-group">                    
                        <div class="col-md-8 col-md-offset-4">
                            <button class="btn btn-primary" type="submit" ng-disabled="formMiCuenta.$invalid">
                                <i class="fa fa-save"></i>
                                Guardar Cambios</button>
                        </div>                
                    </div>     
                </fieldset>
            </form>
            <form  class="form-horizontal" role="form" name="formPass" ng-submit="ChangePass()" novalidate>
                <fieldset>
                    <legend>Cambio de Contraseña</legend>
                    <div class="form-group">
                        <label class="control-label col-md-4">Contraseña Actual</label>
                        <div class="col-md-8">
                            <input class="form-control" ng-model="usuario.oldPassword" name="actual" required type="password" autocomplete="no">
                            <div ng-if="interactedPass(formPass.actual)" ng-messages="formPass.oldPassword.$error"  ng-messages-include="/templates/directives/formsErrors.html">
                            </div>
                        </div>
                    </div>  
                    <div class="form-group">
                        <label class="control-label col-md-4">Nueva Contraseña</label>
                        <div class="col-md-8">
                            <input class="form-control" ng-model="usuario.newPassword" name="nueva" required type="password" autocomplete="no">
                            <div ng-if="interactedPass(formPass.nueva)" ng-messages="formPass.newPassword.$error"  ng-messages-include="/templates/directives/formsErrors.html">
                            </div>
                        </div>
                    </div>     
                    <div class="form-group">
                        <label class="control-label col-md-4">Confirma Nueva  Contraseña</label>
                        <div class="col-md-8">
                            <input class="form-control" ng-model="usuario.confirm" name="confirm"  required type="password" autocomplete="no">
                            <div ng-if="interactedPass(formPass.confirm)" ng-messages="formPass.confirm.$error"  ng-messages-include="/templates/directives/formsErrors.html">
                            </div>
                        </div>
                    </div>     
                    <div class="form-group">               
                        <div class="col-md-8 col-md-offset-4">
                            <button class="btn btn-primary" type="submit"  ng-disabled="formPass.$invalid"><i class="fa fa-save"></i>
                                Guardar Cambios</button>
                        </div>
                    </div>     
                </fieldset>                 
            </form>
        </div>
    </div>
</div>
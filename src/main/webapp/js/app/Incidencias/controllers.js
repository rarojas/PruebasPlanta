app.controller("IncidenciasCtrl",
        ["$scope", "PlantaServices", "$routeParams", "$filter"
                    , function ($scope, PlantaServices, $filter) {
                        BaseTableController.call(this, $scope, $filter);
                        $scope.items = PlantaServices.Incidencias.query($scope.Init);
                        $scope.Delete = function (incidencia) {
                            PlantaServices.Incidencias.delete({id: incidencia.id}, function () {
                                $scope.incidencias.pop(incidencia);
                            }, function () {
                            });
                        };
                    }
        ]);

app.controller("IncidenciasSaveCtrl",
        ["$scope", "PlantaServices", "$routeParams", "$location"
                    , function ($scope, PlantaServices, $routeParams, $location) {
                        $scope.submitted = false;
                        $scope.interacted = function (field) {
                            return $scope.submitted || field.$dirty;
                        };
                        $scope.incidencia = new PlantaServices.Incidencias();
                        if ($routeParams.IncidenciaID !== undefined)
                            $scope.incidencia.$get({id: $routeParams.IncidenciaID});
                        $scope.Save = function () {
                            $scope.submitted = true;
                            $scope.incidencia.$save(function () {
                                noty({text: "Registro Creado Exitosamente¡¡", type: "success"});
                                if (!$routeParams.IncidenciaID)
                                    $location.path("/Incidencias/Edit/" + $scope.incidencia.id);
                            }, function () {
                                noty({text: "Ocurrio un error al realizar la acción¡¡", type: "error"});
                            });
                        };
                        $scope.Update = function () {
                            $scope.submitted = true;
                            $scope.incidencia.$update(function () {
                                noty({text: "Registro Actualizado Exitosamente¡¡", type: "success"});
                            }, function () {
                                noty({text: "Ocurrio un error al realizar la acción¡¡", type: "error"});
                            });
                        };
                        $scope.Delete = function () {
                            $scope.incidencia.$delete(function () {
                                $location.path("/Incidencias");
                                noty({text: "Registro Borrado Exitosamente¡¡", type: "success"});
                            }, function () {
                            });
                        };
                    }
        ]);
app.controller("MotoresCtrl",
        ["$scope", "PlantaServices", "$filter"
                    , function ($scope, PlantaServices, $filter) {
                        BaseTableController.call(this, $scope, $filter);
                        $scope.items = PlantaServices.Motores.query($scope.Init);
                        $scope.Delete = function (motor) {
                            PlantaServices.Motores.delete({modelo: motor.modelo}, function () {
                                $scope.incidencias.pop(motor);
                            }, function () {
                            });
                        };
                    }
        ]);

app.controller("MotoresSaveCtrl",
        ["$scope", "PlantaServices", "$routeParams", "$location"
                    , function ($scope, PlantaServices, $routeParams, $location) {
                        $scope.motor = new PlantaServices.Motores();
                        if ($routeParams.modelo !== undefined)
                            $scope.motor.$get({modelo: $routeParams.modelo});
                        $scope.Save = function () {
                            $scope.motor.$save(function () {
                                if (!$routeParams.modelo)
                                    $location.path("/Motores/Edit/" + $scope.motor.id);
                            }, function () {

                            });
                        };
                        $scope.Update = function () {
                            $scope.motor.$update(function () {
                            }, function () {
                            });
                        };
                        $scope.Delete = function () {
                            $scope.motor.$delete(function () {
                                $location.path("/Motores");
                            }, function () {

                            });
                        };
                    }
        ]);
app.controller("UsuariosCtrl",
        ["$scope", "PlantaServices", "$filter"
                    , function ($scope, PlantaServices, $filter) {
                        BaseTableController.call(this, $scope, $filter);
                        $scope.items = PlantaServices.Usuarios.query($scope.Init);
                        $scope.Delete = function (usuario) {
                            PlantaServices.Usuarios.delete({id: usuario.id}, function () {
                                $scope.usuario.pop(usuario);
                            }, function () {
                            });
                        };
                    }
        ]);

app.controller("UsuariosSaveCtrl", ["$scope", "PlantaServices", "$routeParams", "$location"
            , function ($scope, PlantaServices, $routeParams, $location) {
                $scope.usuario = new PlantaServices.Usuarios();
                $scope.usuario.roles = [];
                if ($routeParams.id !== undefined)
                    $scope.usuario.$get({id: $routeParams.id});
                $scope.roles = PlantaServices.Roles.query();
                $scope.settings = {displayProp: 'nbRol', idProp: 'id', externalIdProp: '',
                    showCheckAll: false, showUncheckAll: false};
                $scope.customTexts = {buttonDefaultText: 'Roles', selectionCount: 'seleccionadas'};
                $scope.Save = function () {
                    $scope.usuario.$save(function () {
                        if (!$routeParams.id)
                            $location.path("/Usuarios/Edit/" + $scope.usuario.id);
                    }, function () {

                    });
                };
                $scope.Update = function () {
                    $scope.usuario.$update(function () {
                    }, function () {
                    });
                };
                $scope.Delete = function () {
                    $scope.usuario.$delete(function () {
                        $location.path("/Usuarios");
                    }, function () {

                    });
                };
            }
]);
app.controller("CarrilesCtrl",
        ["$scope", "PlantaServices", "$routeParams"
                    , function ($scope, PlantaServices) {
                        $scope.carriles = PlantaServices.Carriles.query();
                        $scope.Delete = function (carril) {
                            PlantaServices.Carriles.delete({id: carril.id}, function () {
                                $scope.carriles.pop(carril);
                            }, function () {
                            });
                        };
                    }
        ]);

app.controller("CarrilesSaveCtrl",
        ["$scope", "PlantaServices", "$routeParams", "$location"
                    , function ($scope, PlantaServices, $routeParams, $location) {
                        $scope.carril = new PlantaServices.Carriles();
                        if ($routeParams.id !== undefined)
                            $scope.carril.$get({id: $routeParams.id});
                        $scope.Save = function () {
                            $scope.carril.$save(function () {
                                if (!$routeParams.id)
                                    $location.path("/Carriles/Edit/" + $scope.carril.id);
                            }, function () {

                            });
                        };
                        $scope.Update = function () {
                            $scope.carril.$update(function () {
                            }, function () {
                            });
                        };
                        $scope.Delete = function () {
                            $scope.carril.$delete(function () {
                                $location.path("/Carriles");
                            }, function () {

                            });
                        };
                    }
        ]);

app.controller("UbicacionesCtrl",
        ["$scope", "PlantaServices", "$filter"
                    , function ($scope, PlantaServices, $filter) {
                        BaseTableController.call(this, $scope, $filter);
                        $scope.clientes = PlantaServices.Clientes.query();
                        $scope.items = PlantaServices.Ubicaciones.query($scope.Init);
                        $scope.Delete = function (ubicacion) {
                            PlantaServices.Ubicaciones.delete({id: ubicacion.id}, function () {
                                $scope.ubicacion.pop(ubicacion);
                            }, function () {
                            });
                        };
                    }
        ]);

app.controller("UbicacionesSaveCtrl",
        ["$scope", "PlantaServices", "$routeParams", "$location"
                    , function ($scope, PlantaServices, $routeParams, $location) {
                        $scope.clientes = PlantaServices.Clientes.query();
                        $scope.ubicacion = new PlantaServices.Ubicaciones();
                        if ($routeParams.id !== undefined)
                            $scope.ubicacion.$get({id: $routeParams.id});
                        $scope.Save = function () {
                            $scope.ubicacion.$save(function () {
                                if (!$routeParams.id)
                                    $location.path("/Ubicaciones/Edit/" + $scope.ubicacion.id);
                            }, function () {

                            });
                        };
                        $scope.Update = function () {
                            $scope.ubicacion.$update(function () {
                            }, function () {
                            });
                        };
                        $scope.Delete = function () {
                            $scope.ubicacion.$delete(function () {
                                $location.path("/Ubicaciones");
                            }, function () {

                            });
                        };
                    }
        ]);


app.controller("ClientesCtrl",
        ["$scope", "PlantaServices", "$filter"
                    , function ($scope, PlantaServices, $filter) {
                        BaseTableController.call(this, $scope, $filter);
                        $scope.items = PlantaServices.Clientes.query($scope.Init);
                        $scope.Delete = function (cliente) {
                            PlantaServices.Clientes.delete({id: cliente.id}, function () {
                                $scope.cliente.pop(cliente);
                            }, function () {
                            });
                        };
                    }
        ]);

app.controller("ClientesSaveCtrl",
        ["$scope", "PlantaServices", "$routeParams", "$location"
                    , function ($scope, PlantaServices, $routeParams, $location) {
                        $scope.cliente = new PlantaServices.Clientes();
                        if ($routeParams.id !== undefined)
                            $scope.cliente.$get({id: $routeParams.id});
                        $scope.Save = function () {
                            $scope.cliente.$save(function () {
                                if (!$routeParams.id)
                                    $location.path("/Clientes/Edit/" + $scope.cliente.id);
                            }, function () {

                            });
                        };
                        $scope.Update = function () {
                            $scope.cliente.$update(function () {
                            }, function () {
                            });
                        };
                        $scope.Delete = function () {
                            $scope.cliente.$delete(function () {
                                $location.path("/Clientes");
                            }, function () {

                            });
                        };
                    }
        ]);

app.controller("KitsCtrl",
        ["$scope", "PlantaServices", "$filter"
                    , function ($scope, PlantaServices, $filter) {
                        BaseTableController.call(this, $scope, $filter);
                        $scope.items = PlantaServices.Kits.query($scope.Init);
                        $scope.Delete = function (kit) {
                            PlantaServices.Kits.delete({id: kit.id}, function () {
                                $scope.kites.pop(kit);
                            }, function () {
                            });
                        };
                    }
        ]);

app.controller("KitsSaveCtrl",
        ["$scope", "PlantaServices", "$routeParams", "$location"
                    , function ($scope, PlantaServices, $routeParams, $location) {
                        $scope.kit = new PlantaServices.Kits();
                        if ($routeParams.id !== undefined)
                            $scope.kit.$get({id: $routeParams.id});
                        $scope.Save = function () {
                            $scope.kit.$save(function () {
                                if (!$routeParams.id)
                                    $location.path("/Kits/Edit/" + $scope.kit.id);
                            }, function () {

                            });
                        };
                        $scope.Update = function () {
                            $scope.kit.$update(function () {
                            }, function () {
                            });
                        };
                        $scope.Delete = function () {
                            $scope.kit.$delete(function () {
                                $location.path("/Kits");
                            }, function () {

                            });
                        };
                    }
        ]);
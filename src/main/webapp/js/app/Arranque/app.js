app.controller("NuevoArranqueCtrl",
        ["$scope", "PlantaServices", "$filter", "$location", "$modal",
            function ($scope, PlantaServices, $filter, $location, $modal) {
                $scope.motores = PlantaServices.Plantas.query();
                $scope.ubicaciones = PlantaServices.Ubicaciones.query();
                $scope.kits = PlantaServices.Kits.query();
                $scope.clientes = PlantaServices.Clientes.query();
                $scope.geocoder = new google.maps.Geocoder();
                $scope.openMap = function () {
                    var modalInstance = $modal.open({
                        templateUrl: 'templates/EnsambleArranque/Location.html',
                        size: 'lg',
                        resolve: {options: function () {
                                return {center: $scope.center, ubicacion: $scope.prueba.ubicaciones};
                            }},
                        controller: function ($scope, options, $timeout) {
                            $scope.geocoder = new google.maps.Geocoder();
                            $scope.sites = [];
                            $scope.search = function () {
                                $scope.geocoder.geocode({'address': $scope.buscar}, function (results, status) {
                                    if (status === 'OK') {
                                        $scope.busqueda = results;
                                    } else {
                                        alert("Geocoding no tuvo éxito debido a: " + status);
                                    }
                                });
                            };
                            $scope.select = function (item) {
                                $scope.site().lat = item.geometry.location.lat();
                                $scope.site().lng = item.geometry.location.lng();
                                $scope.site().direccion = item.formatted_address;
                                var result = $filter('filter')(item.address_components, function (item) {
                                    return item.types[0] === "postal_code";
                                });
                                if (result.length > 0)
                                    $scope.site().cp = result[0].short_name;
                                $scope.$broadcast('gmMarkersUpdate', 'sites');
                                $scope.$broadcast('gmMapResize', 'myMap');
                            };
                            $scope.options = {
                                map: {
                                    center: options.center,
                                    zoom: 6,
                                    mapTypeId: google.maps.MapTypeId.ROADMAP
                                },
                                marker: {
                                    clickable: false,
                                    draggable: true
                                }
                            };
                            $timeout(function () {
                                if (options.ubicacion)
                                    $scope.sites = [{id: 0, name: 'Locación de nueva planta', lat: options.ubicacion.latitude, lng: options.ubicacion.longitude}];
                                else
                                    $scope.sites = [{id: 0, name: 'Locación de nueva planta', lat: options.center.lat(), lng: options.center.lng()}];
                                $scope.$broadcast('gmMarkersUpdate', 'sites');
                                $scope.$broadcast('gmMapResize', 'myMap');
                            }, 100);
                            $scope.site = function () {
                                return $scope.sites[0];
                            };
                            $scope.myClickFn = function (map, event) {
                                $scope.site().lat = event.latLng.k;
                                $scope.site().lng = event.latLng.D;
                                $scope.center = new google.maps.LatLng($scope.site().lat, $scope.site().lng);
                                $scope.geocoder.geocode({'latLng': $scope.center}, $scope.geocodeResult);
                                $scope.$broadcast('gmMarkersUpdate', 'sites');
                            };
                            $scope.geocodeResult = function (results, status) {
                                if (status === 'OK') {
                                    if (results.length > 0) {
                                        $scope.select(results[0]);
                                        $scope.$apply();
                                    }
                                } else {
                                    alert("Geocoding no tuvo éxito debido a: " + status);
                                }
                            };
                            $scope.getMarkerOptions = function (house) {
                                return {
                                    clickable: false,
                                    draggable: true,
                                    title: house.name
                                };
                            };
                            $scope.setMarkerLocation = function (site, marker) {
                                var position = marker.getPosition();
                                site.lat = position.lat();
                                site.lng = position.lng();
                            };
                        }
                    });
                    modalInstance.result.then(function (selectedItem) {
                        $scope.selected = selectedItem;
                    }, function () {
                    });
                };

                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function (position) {
                        $scope.center = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
                    }, function () {
                    });
                } else {
                }

                $scope.searchPlanta = function (term) {
                    $scope.plantas = PlantaServices.Plantas.ByOP({noOP: term});
                };
                $scope.searchPlantaBySerie = function (term) {
                    $scope.plantas = PlantaServices.Plantas.ByNoSerie({Serie: term});
                };
                $scope.prueba = new PlantaServices.EnsambleArranque({
                    folio: "14PR000001", dtCreacion: new Date()
                });
                $scope.selectPlanta = function (planta) {
                    $scope.prueba.planta = planta;
                };
                $scope.submit = function () {
                    $scope.prueba.$save(function (prueba) {
                        noty({text: "Prueba de arranque registrada con el folio : " + prueba.folio + "¡¡¡", type: "success", modal: true});
                        $location.path("/Pruebas/" + prueba.id);
                    }, function () {
                        noty({text: "Ocurrio un error", type: "error"});
                    });
                };
            }]);
app.controller("PruebasArranqueCtrl", ["$scope", "PlantaServices", "$filter",
    function ($scope, PlantaServices, $filter) {
        BaseTableController.call(this, $scope, $filter);
        $scope.items = PlantaServices.EnsambleArranque.query($scope.Init);
    }]);
app.controller("EnsambleArranqueCtrl", ["$scope", "PlantaServices", "$routeParams", "$filter", "$modal",
    function ($scope, PlantaServices, $routeParams, $filter, $modal) {
        if (!$routeParams.PruebaID) {
        }
        else
            $scope.prueba = PlantaServices.EnsambleArranque.get({id: $routeParams.PruebaID});
        $scope.canClose = function () {
            if (!$scope.prueba.pruebaarranques)
                return false;
            var total = $filter("filter")($scope.prueba.pruebaarranques, {estatus: "AutorizadaSupervisor"}, true);
            if (total === 4) {
                return true;
            } else {
                return false;
            }

        };

        $scope.ViewLocation = function () {
            var modalInstance = $modal.open({
                templateUrl: 'templates/directives/Location.html',
                size: 'lg',
                resolve: {
                    options: function () {
                        return {ubicacion: $scope.prueba.ubicacion};
                    }
                },
                controller: function ($scope, $timeout, options) {
                    $scope.ubicaciones = [options.ubicacion];
                    $scope.options = {
                        map: {
                            center: new google.maps.LatLng(options.ubicacion.latitude, options.ubicacion.longitude),
                            zoom: 6
                        }
                    };
                    $timeout(function () {
                        $scope.$broadcast('gmMapResize', 'myMap');
                    }, 100);
                }
            });
            modalInstance.result.then(function () {
            }, function () {
            });
        };
        $scope.Aprobar = function () {
            PlantaServices.EnsambleArranque.Aprobar(function () {
            }, function () {
            });
        };
        $scope.Rechazar = function () {
            PlantaServices.EnsambleArranque.Rechazar(function () {
            }, function () {
            });
        };
    }]);
app.controller("PruebaArranqueCtrl", ["$routeParams", "$scope", "PlantaServices", "$timeout",
    function ($routeParams, $scope, PlantaServices, $timeout) {
        $scope.ensamble = PlantaServices.EnsambleArranque.get({id: $routeParams.EnsambleID}, function () {
            $scope.prueba = new PlantaServices.Arranque({
                dtInicio: new Date(), dtFin: new Date(), tipo: 0, estatus: 0,
                ensamblearranque: {id: $scope.ensamble.id}
            });
        });
    }]);
app.controller("PruebaInstalacionCtrl", ["$routeParams", "$scope", "PlantaServices", "$timeout",
    function ($routeParams, $scope, PlantaServices, $timeout) {
        $scope.ensamble = PlantaServices.EnsambleArranque.get({id: $routeParams.EnsambleID}, function () {
            $scope.prueba = new PlantaServices.Instalaciones({
                dtInicio: new Date(), dtFin: new Date(), tipo: 0, estatus: 0,
                ensamblearranque: {id: $scope.ensamble.id}
            });
        });
        $scope.ApruebaPruebaTecnico = function () {
            PlantaServices.Pruebasarranque.ApruebaT({id: $scope.prueba.id}, {}, function () {
            }, function () {
            });
        };
        $scope.RechazaPruebaTecnico = function () {
            PlantaServices.Pruebasarranque.RechazaT({id: $scope.prueba.id}, {}, function () {
            }, function () {
            });
        };
        $scope.GuardarPrueba = function () {
            $scope.prueba.estatus = 'Finalizada';
            $scope.prueba.$save(function () {
            }, function () {
            });
        };
    }]);
app.controller("PruebaVacioCtrl", ["$routeParams", "$scope", "PlantaServices", "$timeout",
    function ($routeParams, $scope, PlantaServices, $timeout) {

    }]);
app.controller("PruebaInstalacionViewCtrl", ["$routeParams", "$scope", "PlantaServices", "$timeout",
    function ($routeParams, $scope, PlantaServices, $timeout) {
        $scope.ensamble = PlantaServices.EnsambleArranque.get({id: $routeParams.EnsambleID}, function () {
        });
        $scope.prueba = new PlantaServices.Instalaciones.get({id: $routeParams.PruebaID});
        $scope.ApruebaPruebaTecnico = function () {
            PlantaServices.Pruebasarranque.ApruebaT({id: $scope.prueba.id}, function () {
            }, function () {
            });
        };
        $scope.RechazaPruebaTecnico = function () {
            PlantaServices.Pruebasarranque.RechazaT({id: $scope.prueba.id}, function () {
            }, function () {
            });
        };
        $scope.GuardarPrueba = function () {
            $scope.prueba.$save(function () {
                $scope.prueba.estatus = 2;
            }, function () {
            });
        };
    }]);
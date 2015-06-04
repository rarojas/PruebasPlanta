var app = angular.module("PlantaAPP", ['nvd3ChartDirectives', 'n3-line-chart', "ngResource", "ngRoute", 'AngularGM',
    'angular-loading-bar', 'vr.directives.slider', 'gantt', 'ui.bootstrap',
    'gantt.sortable', 'gantt.movable', 'gantt.tooltips', 'gantt.bounds', 'gantt.table' //,'mgcrea.ngStrap'
            , "angularMoment", 'ngMessages', 'angularjs-dropdown-multiselect']);
var BaseTableController = function ($scope, $filter) {
    $scope.current = 0;
    $scope.total = 0;
    $scope.rowsPage = 10;
    $scope.totalRows = 0;
    $scope.pages = [];
    $scope.next = function () {
        if ($scope.current !== 0 && $scope.total < $scope.current)
            $scope.current++;
        else
            $scope.current = 0;
    };
    $scope.previous = function () {
        if (($scope.current - 1) > 0)
            $scope.current--;
        else
            $scope.current = $scope.total - 1;
    };
    $scope.setPage = function (page) {
        $scope.current = page;
    };
    $scope.$watch("search", function () {
        $scope.current = 0;
        $scope.filtered = $filter("filter")($scope.items, $scope.search);
        $scope.total = Math.ceil($scope.filtered.length / $scope.rowsPage);
        $scope.pages = [];
        for (var page = 0; page > $scope.total; page++)
            $scope.pages.push(page);
    }, true);
    $scope.result = function () {
        return $scope.filtered.slice($scope.current * $scope.rowsPage, ($scope.current + 1) * $scope.rowsPage);
    };
    $scope.Init = function () {
        $scope.total = Math.ceil($scope.items.length / $scope.rowsPage);
        $scope.totalRows = $scope.items.length;
        $scope.filtered = $scope.items;
        console.log($scope.total);
        for (var page = 0; page < $scope.total; page++)
            $scope.pages.push(page);
    };
};
app.controller("PruebasCtrl", ["$scope", "PlantaServices", "$filter",
    function ($scope, PlantaServices, $filter) {
        BaseTableController.call(this, $scope, $filter);
        $scope.items = PlantaServices.Ensambles.query($scope.Init);
        $scope.Estatus = ['Terminada', 'Aprobada', 'EnEjecucion', 'Rechazada', 'Programada'];
    }]);
app.controller("GeneradorController", ["$scope", "PlantaServices", "$filter",
    function ($scope, PlantaServices, $filter) {
        BaseTableController.call(this, $scope, $filter);
        $scope.items = PlantaServices.Generador.query($scope.Init);
        $scope.Delete = function (Generador) {
            PlantaServices.Generador.delete({id: Generador.id}, function () {
                $scope.incidencias.pop(Generador);
            }, function () {
            });
        };
    }]);
app.controller("GeneradorCreateController", ["$scope", "PlantaServices", "$routeParams", "$location",
    function ($scope, PlantaServices, $routeParams, $location) {
        $scope.generador = new PlantaServices.Generador();
        if ($routeParams.GeneradorID !== undefined)
            $scope.generador.$get({id: $routeParams.GeneradorID});
        $scope.Save = function () {
            $scope.generador.$save(function () {
                if (!$routeParams.GeneradorID)
                    $location.path("/Generador/Edit/" + $scope.generador.id);
            }, function () {

            });
        };
        $scope.Update = function () {
            $scope.generador.$save(function () {
            }, function () {
            });
        };
        $scope.Delete = function () {
            $scope.generador.$delete(function () {
                $location.path("/Generador");
            }, function () {

            });
        };
    }]);
app.controller("PruebaCtrl", ["$scope", "PlantaServices", "$routeParams", "$filter", "$location",
    function ($scope, PlantaServices, $routeParams, $filter, $location) {
        if (!$routeParams.PruebaID) {
        }
        else
            $scope.prueba = PlantaServices.Ensambles.get({id: $routeParams.PruebaID});
        $scope.filterByTipo = function (tipo) {
            if ($scope.prueba) {
                var result = $filter('filter')($scope.prueba.pruebas, function (item) {
                    if (item.tipo !== tipo)
                        return false;
                    if (item.estatus === 'AutorizadoEjecutor' || item.estatus === 'AutorizadaSupervisor')
                        return true;
                    return false;
                });
                if (result !== undefined)
                    return result.length;
            }
            return 0;
        };
        $scope.filterByTipoSup = function (tipo) {
            if ($scope.prueba) {
                var result = $filter('filter')($scope.prueba.pruebas, function (item) {
                    if (item.tipo !== tipo)
                        return false;
                    if (item.estatus === 'AutorizadaSupervisor')
                        return true;
                    return false;
                });
                if (result !== undefined)
                    return result.length;
            }
            return 0;
        };
        $scope.canClose = function () {
            if (!$scope.prueba)
                return false;
            var total = $filter("filter")($scope.prueba.pruebas, {estatus: "AutorizadaSupervisor"}, true);
            if (total === 4) {
                return true;
            } else {
                return false;
            }
        };
        $scope.Aprobar = function () {
            PlantaServices.Ensambles.Aprobar({id: $scope.prueba.id}, function () {
                $scope.prueba = PlantaServices.Ensambles.get({id: $routeParams.PruebaID});
                noty({text: "Aprobación de las pruebas con exito¡¡¡", type: "success"});
            }, function () {
                noty({text: "Ocurrio  un error¡¡", type: "error"});
            });
        };
        $scope.Rechazar = function () {
            PlantaServices.Ensambles.Rechazar({id: $scope.prueba.id}, function () {
                noty({text: "Rechazo de las pruebas con exito¡¡¡", type: "success"});
            }, function () {
                noty({text: "Ocurrio  un error¡¡", type: "error"});
            });
        };
    }]);
var BaseController = function ($scope, $filter, $interval, $routeParams, PlantaServices, $timeout, $location) {
    $scope.OptionsControl = {
        Locked: true
    };
    $scope.log = [];
    $scope.Iteracion = [];
    $scope.destroy = function () {
        $interval.cancel($scope.isOnlinePoller);
        $interval.cancel($scope.isOnPoller);
    };
    $scope.$on("$destroy", $scope.destroy);
    $scope.isOnlineCheck = function () {
        PlantaServices.Plantas.isOnline({id: $scope.ensamble.carriles.planta},
        function (response) {
            $scope.isOnline = response.isOnline;
        });
    };
    $timeout(function () {
        $scope.isOnlineCheck();
        $scope.isOnlinePoller = $interval($scope.isOnlineCheck, 5000);
    }, 2000);
    $scope.isOnCheck = function () {
        PlantaServices.Plantas.isOn({id: $scope.ensamble.carriles.planta},
        function (response) {
            $scope.isOn = response.isON;
        });
    };
    $timeout(function () {
        $scope.isOnCheck();
        $scope.isOnPoller = $interval($scope.isOnCheck, 5000);
    }, 2000);
    $scope.$on("$locationChangeStart", function (event, newUrl) {
        if ($scope.Poller.$$intervalId) {
            noty({
                text: "Desea cancelar la prueba?", modal: true,
                closeWith: [], buttons: [
                    {addClass: 'btn btn-primary', text: 'Ok', onClick: function ($noty) {
                            $noty.close();
                            $scope.Stop();
                            $scope.destroy();
                            $location.path(newUrl);
                        }
                    },
                    {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
                            $noty.close();
                        }
                    }
                ]
            });
            event.preventDefault();
        }
        $scope.destroy();
    });
    $scope.GetColor = function (max, min, read) {
        var esperado = d3.mean([min, max]);
        var result = Math.abs(esperado - read);
        result /= (esperado - min);
        result > 1 ? 1 : result;
        var color = d3.scale.linear().domain([0, 0.5, 1]).range(["green", "yellow", "red"]);
        return color(result);
    };
    $scope.Init = function () {
        $scope.incidencias = PlantaServices.Incidencias.query();
        $scope.ensamble = PlantaServices.Ensambles.get({id: $routeParams.EnsambleID}, function () {
            $scope.pruebas = PlantaServices.Ensambles.get({id: $routeParams.EnsambleID}, function () {

                $scope.Redondear2Dec = function (numero) {
                    var result = parseFloat(numero).toFixed(2);
                    return result;
                };
                $scope.ValMax_L1N_SC = $scope.ValMax_L2N_SC = $scope.ValMax_L3N_SC = $scope.Redondear2Dec(1.01 * $scope.pruebas.planta.voltaje);
                $scope.ValMax_Frec_SC = $scope.Redondear2Dec(1.01 * $scope.pruebas.planta.motores.frecuenciaOperacion);
                $scope.ValMax_Temp_SC = $scope.Redondear2Dec(100);
                $scope.ValMax_Presion_SC = $scope.Redondear2Dec($scope.pruebas.planta.motores.presionMax);
                $scope.ValMax_Carga_Alter_SC = "N/A";
                $scope.ValMin_L1N_SC = $scope.ValMin_L2N_SC = $scope.ValMin_L3N_SC = $scope.Redondear2Dec(0.99 * $scope.pruebas.planta.voltaje);
                $scope.ValMin_Frec_SC = $scope.Redondear2Dec(0.99 * $scope.pruebas.planta.motores.frecuenciaOperacion);
                $scope.ValMin_Temp_SC = "N/A";
                $scope.ValMin_Presion_SC = $scope.Redondear2Dec(1.5 * 14.5038);
                $scope.ValMin_Carga_Alter_SC = "N/A";
            });
            $scope.valores = PlantaServices.Pruebas.Valores({id: $scope.ensamble.planta.noSerie}, function () {
                $scope.PruebaCarga = [{}, {}, {}, {}];
                var i = 0;
                for (i = 0; i < 4; i++) {

                    angular.copy($scope.valores, $scope.PruebaCarga[i]);
                    var max = ($scope.valores.Max.I1 * 0.25 * 1.10 * i);
                    var min = ($scope.valores.Min.I1 * 0.25 * 0.90 * i);
                    $scope.PruebaCarga[i].Max.I1 = max;
                    $scope.PruebaCarga[i].Max.I2 = max;
                    $scope.PruebaCarga[i].Max.I3 = max;
                    $scope.PruebaCarga[i].Min.I1 = min;
                    $scope.PruebaCarga[i].Min.I2 = min;
                    $scope.PruebaCarga[i].Min.I3 = min;
                }

                $scope.PruebaCargaIndex = function () {
                    return $scope.Iteraciones.current < 2 ? $scope.Iteraciones.current + 1: 3;
                };
                $scope.valores.Max.I1 = $scope.valores.Max.I2 = $scope.valores.Max.I3 = 0;
                $scope.valores.Min.I1 = $scope.valores.Min.I2 = $scope.valores.Min.I3 = 0;
            }
            );
        });
    };
    $scope.Init();
    $scope.Enable = function () {
        $scope.OptionsControl.Locked = false;
    };
    $scope.Disable = function () {
        $scope.OptionsControl.Locked = true;
    };
    $scope.Added = [];
    $scope.now = [];
    $scope.Poller = {};
    $scope.RefreshTime = 1;
    $scope.RefreshTimes = [1, 5, 30, 60];
    $scope.Estatus = {
        Waiting: {text: "En espera", value: 0, color: 'green'},
        Running: {text: "Corriendo...", value: 1, color: 'blue'},
        Error: {text: "Error", value: 2, color: 'red'}
    };
    $scope.Save = function () {
        $scope.prueba.ensamble = $scope.ensamble;
        $scope.prueba.$update();
    };
    $scope.Estado = $scope.Estatus.Waiting;
    $scope.AccumulateTime = 0;
    $scope.intervalTime = 1;
    $scope.lastMinute = [];
    $scope.Accumulate = [];
    $scope.optionsArray = [
    ];
    $scope.VariablesInit = [
        {legend: "Grafica de Voltaje L1N", unit: "Volts", visible: true, variables: [{y: "L1N", label: "L1-N", color: "#67B331"}, {y: "MaxV", label: "Max V", color: "#c90a0a"}, {y: "MinV", label: "Min V", color: "#c90a0a"}]},
        {legend: "Grafica de Voltaje L2N", unit: "Volts", visible: true, variables: [{y: "L2N", label: "L2-N", color: "#67B331"}, {y: "MaxV", label: "Max V", color: "#c90a0a"}, {y: "MinV", label: "Min V", color: "#c90a0a"}]},
        {legend: "Grafica de Voltaje L3N", unit: "Volts", visible: true, variables: [{y: "L3N", label: "L3-N", color: "#67B331"}, {y: "MaxV", label: "Max V", color: "#c90a0a"}, {y: "MinV", label: "Min V", color: "#c90a0a"}]},
        {legend: "Grafica de Corriente I1", unit: "Amperes", visible: true, variables: [{y: "I1", label: "I1", color: "#67B331"}, {y: "MaxI", label: "Max I", color: "#c90a0a"}, {y: "MinI", label: "Min I", color: "#c90a0a"}]},
        {legend: "Grafica de Corriente I2", unit: "Amperes", visible: true, variables: [{y: "I2", label: "I2", color: "#67B331"}, {y: "MaxI", label: "Max I", color: "#c90a0a"}, {y: "MinI", label: "Min I", color: "#c90a0a"}]},
        {legend: "Grafica de Corriente I3", unit: "Amperes", visible: true, variables: [{y: "I3", label: "I3", color: "#67B331"}, {y: "MaxI", label: "Max I", color: "#c90a0a"}, {y: "MinI", label: "Min I", color: "#c90a0a"}]},
        {legend: "Grafica de Temperatura", unit: "° C", visible: true, variables: [{y: "Temp", label: "Temp", color: "#67B331"}, {y: "MaxT", label: "Max T", color: "#c90a0a"}, {y: "MinT", label: "Min T", color: "#c90a0a"}]},
        {legend: "Grafica de Frecuencia", unit: "Hertz", visible: true, variables: [{y: "HZ", label: "HZ", color: "#67B331"}, {y: "MaxF", label: "Max F", color: "#c90a0a"}, {y: "MinF", label: "Min F", color: "#c90a0a"}]}
    ];
    $scope.options = {
        series: [
            {y: "L1N", label: "L1-N", color: "#ff0000", type: "line", thickness: "1px"},
            {y: "MaxV", label: "Max V", color: "#c90a0a", type: "line", thickness: "1px"},
            {y: "MinV", label: "Min V", color: "#c90a0a", type: "line", thickness: "1px"}
        ],
        axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
        lineMode: "bundle",
        tension: 0.7,
        tooltip: {mode: "scrubber"},
        drawLegend: true,
        drawDots: true,
        columnsHGap: 5
    };
    angular.forEach($scope.VariablesInit, function (item, index) {
        $scope.optionsArray.push({
            legend: item.legend,
            visible: item.visible,
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear", unit: item.unit}},
            lineMode: "linear",
            tension: 0.7,
            tooltip: {mode: "scrubber"},
            drawLegend: true,
            drawDots: true,
            columnsHGap: 5});
        $scope.optionsArray[index].series = [];
        $scope.optionsArray[index].id = index;
        angular.forEach(item.variables, function (variable, indexVariable) {
            $scope.optionsArray[index].series.push({
                y: variable.y, label: variable.label, color: variable.color, type: "line", thickness: "1px"
            });
        });
    });
    $scope.optionsCorriente = {
        series: [
            {
                y: "I1", label: "I1", axis: "y", color: "#ff0000", type: "line", thickness: "1px"
            }, {
                y: "I2", label: "I2", axis: "y", color: "#64c900", type: "line", thickness: "1px"
            }, {
                y: "I3", label: "I3", axis: "y", color: "#428bca", type: "line", thickness: "1px"
            }],
        axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
        lineMode: "linear",
        tension: 0.7,
        tooltip: {mode: "scrubber"},
        drawLegend: true,
        drawDots: true,
        columnsHGap: 5
    };
    $scope.optionsPresion = {
        series: [{y: "Presion", label: "Presión", axis: "y", color: "#4C2D73", type: "line", thickness: "1px"}],
        axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
        lineMode: "linear",
        tension: 0.7,
        tooltip: {mode: "scrubber"},
        drawLegend: true,
        drawDots: true,
        columnsHGap: 5
    };
    $scope.optionsTemp = {};
    angular.copy($scope.options, $scope.optionsTemp);
    $scope.optionsTemp.series = [
        {y: "Temp", label: "Temp", color: "#ff0000", type: "line", thickness: "1px"},
        {y: "HZ", label: "HZ", axis: "y2", color: "#64c900", type: "line", thickness: "1px"},
        {y: "MaxF", label: "Max HZ", axis: "y2", color: "#c90a0a", type: "line", thickness: "1px"},
        {y: "MinF", label: "Min HZ", axis: "y2", color: "#c90a0a", type: "line", thickness: "1px"}
    ];
    $scope.CanAprove = true;
    $scope.Stop = function () {

        $scope.Estado = $scope.Estatus.Waiting;
        $interval.cancel($scope.Poller);
        $interval.cancel($scope.timer);
        $scope.Poller = {};
        $scope.timer = {};
        if ($scope.prueba) {
            $scope.prueba.dtFin = new Date();
            $scope.prueba.estatus = "Finalizada";
            $scope.prueba.ensamble = {id: $scope.ensamble.id},
            $scope.prueba.$update();
        }
    };
    $scope.StopButton = function () {
        $scope.ParoPlanta();
        $scope.Stop();
    };

    $scope.ParoPlanta = function () {
        PlantaServices.Plantas.Off({id: $scope.ensamble.carriles.planta}, {id: $scope.ensamble.id}, function () {
            noty({text: "Apagado de planta exitoso¡¡¡", type: "success"});
        }, function () {
            noty({text: "Error al apagar la planta la planta¡¡¡", type: "error"});
        });
    };
    $scope.ArranquePlanta = function () {
        PlantaServices.Plantas.On({id: $scope.ensamble.carriles.planta}, {id: $scope.ensamble.id}, function () {
            noty({text: "Encendido de planta exitoso¡¡¡", type: "success"});
        }, function () {
            noty({text: "Error al encender la planta¡¡¡", type: "error"});
        });
    };
    $scope.Autoriza = function () {
        PlantaServices.Pruebas.AutorizaE({id: $scope.prueba.id}, {},
                function () {
                    $scope.prueba.estatus = 4;
                    noty({
                        text: "Aprobación con exito¡¡¡¡¡¡", type: "success"
                    });
                    $location.path("/Pruebas/" + $scope.ensamble.id);
                }, function () {
            noty({
                text: "Ocurrio un error al realizar la operacion¡¡¡", type: "error"
            });
        });
    };
    $scope.Rechaza = function () {
        PlantaServices.Pruebas.RechazaE({id: $scope.prueba.id}, {},
                function () {
                    $scope.prueba.estatus = 3;
                    noty({
                        text: "Rechazo con exito¡¡¡¡¡¡", type: "success"
                    });
                    $location.path("/Pruebas/" + $scope.ensamble.id);
                }, function () {
            noty({
                text: "Ocurrio un error al realizar la operacion¡¡¡", type: "error"
            });
        });
    };
    $scope.AutorizaS = function () {
        PlantaServices.Pruebas.AutorizaS({id: $scope.prueba.id}, {},
                function () {
                    $scope.prueba.estatus = 6;
                    noty({
                        text: "Aprobación con exito¡¡¡¡¡¡", type: "success"
                    });
                    $location.path("/Pruebas/" + $scope.ensamble.id);
                }, function () {
            noty({
                text: "Ocurrio un error al realizar la operacion¡¡¡", type: "error"
            });
        });
    };
    $scope.RechazaS = function () {
        PlantaServices.Pruebas.RechazaS({id: $scope.prueba.id}, {},
                function () {
                    $scope.prueba.estatus = 5;
                    noty({
                        text: "Rechazo con exito¡¡¡¡¡¡", type: "success"
                    });
                    $location.path("/Pruebas/" + $scope.ensamble.id);
                }, function () {
            noty({
                text: "Ocurrio un error al realizar la operacion¡¡¡", type: "error"
            });
        });
    };
    $scope.alerts = [];
    $scope.typeAlerts =
            {
                voltaje: {type: "danger", msg: "Voltaje Fuera de rango", id: 1},
                frecuencia: {type: "danger", msg: "Voltaje Fuera de rango", id: 2},
                temp: {type: "danger", msg: "Alta temperatura", id: 3}
            };
    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
    $scope.NowToLastMinute = function () {

        if ($scope.valores.Max.L1N < $scope.now.L1N || $scope.valores.Min.L1N > $scope.now.L1N) {
            if ($filter("filter")($scope.alerts, {id: 1})) {
                $scope.alerts.push($scope.typeAlerts.voltaje);
            }
            $scope.log.push($scope.now.L1N);
        }
        if ($scope.valores.Max.L2N < $scope.now.L2N || $scope.valores.Min.L2N > $scope.now.L2N) {
            $scope.log.push($scope.now.L2N);
        }
        if ($scope.valores.Max.L3N < $scope.now.L3N || $scope.valores.Min.L3N > $scope.now.L3N) {
            $scope.log.push($scope.now.L3N);
        }
        if ($scope.valores.Max.HZ < $scope.now.HZ || $scope.valores.Min.HZ > $scope.now.HZ) {
            if ($filter("filter")($scope.alerts, {id: 2})) {
                $scope.alerts.push($scope.typeAlerts.frecuencia);
            }
            $scope.log.push($scope.now.HZ);
        }

        if ($scope.valores.Max.Temp < $scope.now.Temp) {
            if ($filter("filter")($scope.alerts, {id: 3})) {
                $scope.alerts.push($scope.typeAlerts.temp);
            }
            $scope.log.push($scope.now.Temp);
        }

        return {
            x: $scope.AccumulateTime,
            L1N: $scope.now.L1N,
            L2N: $scope.now.L2N,
            L3N: $scope.now.L3N,
            I1: $scope.now.I1,
            I2: $scope.now.I2,
            I3: $scope.now.I3, HZ: $scope.now.HZ,
            Temp: $scope.now.Temp,
            RPM: $scope.now.RPM,
            MaxV: $scope.valores.Max.L1N,
            MinV: $scope.valores.Min.L1N,
            MaxI: $scope.valores.Max.I1,
            MinI: $scope.valores.Min.I1,
            MaxF: $scope.valores.Max.HZ,
            MinF: $scope.valores.Min.HZ,
            MaxT: $scope.valores.Max.Temp,
            MinT: $scope.valores.Min.Temp,
            Presion: $scope.now.Presion
        };
    };
    $scope.Start = function () {
        noty({
            text: "¿Confirma el comienzo de la prueba", modal: true, closeWith: [],
            buttons: [
                {addClass: 'btn btn-primary', text: 'Ok',
                    onClick: function ($noty) {
                        $noty.close();
                        $scope.notyInit = noty({text: 'Comenzando prueba en  ...', type: 'success', modal: true});
                        $scope.notyInit.count = 5;
                        $scope.intervalNoty = $interval(function () {
                            $scope.notyInit.setText("Comenzando prueba en ... " + $scope.notyInit.count);
                            $scope.notyInit.count--;
                        }, 1000);
                        $timeout(function () {
                            $scope.notyInit.close();
                            $interval.cancel($scope.intervalNoty);
                            $scope.Process();
                        }, 5000);
                    }
                },
                {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
                        $noty.close();
                        noty({text: 'Cancelado', type: 'error'});
                    }
                }]
        });
        $scope.CanAprove = true;
    };
};
app.controller("PruebaSinCargaCtrl", [
    "$scope", "$filter", "$interval", "$routeParams", "PlantaServices", "$timeout", "$interval",
    function ($scope, $filter, $interval, $routeParams, PlantaServices, $timeout, $interval) {
        BaseController.call(this, $scope, $filter, $interval, $routeParams, PlantaServices, $timeout);
        $interval.cancel($scope.isOnlinePoller);
        $interval.cancel($scope.isOnPoller);
        $scope.Redondear2Dec = function (numero) {
            var result = parseFloat(numero).toFixed(2);
            return result;
        };
        $scope.ValMax_I1_SC = $scope.ValMax_I2_SC = $scope.ValMax_I3_SC = $scope.Redondear2Dec(0);
        $scope.ValMax_L1N_SC = $scope.ValMax_L2N_SC = $scope.ValMax_L3N_SC = $scope.Redondear2Dec(0);
        $scope.ValMax_Frec_SC = $scope.Redondear2Dec(0);
        $scope.ValMax_Temp_SC = $scope.Redondear2Dec(0);
        $scope.ValMax_Presion_SC = $scope.Redondear2Dec(0);
        $scope.ValMax_Carga_Alter_SC = $scope.Redondear2Dec(0);
        $scope.ValMin_I1_SC = $scope.ValMin_I2_SC = $scope.ValMin_I3_SC = $scope.Redondear2Dec(0);
        $scope.ValMin_L1N_SC = $scope.ValMin_L2N_SC = $scope.ValMin_L3N_SC = $scope.Redondear2Dec(0);
        $scope.ValMin_Frec_SC = $scope.Redondear2Dec(0);
        $scope.ValMin_Temp_SC = $scope.Redondear2Dec(0);
        $scope.ValMin_Presion_SC = $scope.Redondear2Dec(0);
        $scope.ValMin_Carga_Alter_SC = $scope.Redondear2Dec(0);
        $scope.prueba = PlantaServices.Pruebas.get({id: $routeParams.PruebaID}, function () {
        });
        $scope.optionsVoltaje = {
            series: [
                {
                    y: "L1N",
                    label: "L1-N",
                    color: "#ff0000",
                    type: "line",
                    thickness: "1px"
                }, {
                    y: "L2N",
                    label: "L2-N",
                    color: "#64c900",
                    type: "line",
                    thickness: "1px"
                }, {
                    y: "L3N",
                    label: "L3-N",
                    color: "#428bca",
                    type: "line",
                    thickness: "1px"
                }
            ],
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
            lineMode: "bundle",
            tension: 0.7,
            tooltip: {mode: "scrubber"},
            drawLegend: true
        };
        $scope.optionsFrecuencia = {
            series: [
                {
                    y: "HZ",
                    label: "HZ",
                    color: "#428bca",
                    type: "line",
                    thickness: "1px"
                }
            ],
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
            lineMode: "bundle",
            tension: 0.7,
            tooltip: {mode: "scrubber"},
            drawLegend: true
        };
        $scope.optionsTemperatura = {
            series: [
                {
                    y: "Temp",
                    label: "Temp",
                    color: "#428bca",
                    type: "line",
                    thickness: "1px"
                }
            ],
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
            lineMode: "bundle",
            tension: 0.7,
            tooltip: {mode: "scrubber"},
            drawLegend: true
        };
        $scope.optionsPresionAceite = {
            series: [
                {
                    y: "Presion",
                    label: "PSI",
                    color: "#428bca",
                    type: "line",
                    thickness: "1px"
                }
            ],
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
            lineMode: "bundle",
            tension: 0.7,
            tooltip: {mode: "scrubber"},
            drawLegend: true
        };
        $scope.optionsCorriente = {
            series: [
                {
                    y: "I1",
                    label: "I1",
                    color: "#ff0000",
                    type: "line",
                    thickness: "1px"
                }, {
                    y: "I2",
                    label: "I2",
                    color: "#64c900",
                    type: "line",
                    thickness: "1px"
                }, {
                    y: "I3",
                    label: "I3",
                    color: "#428bca",
                    type: "line",
                    thickness: "1px"
                }
            ],
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
            lineMode: "bundle",
            tension: 0.7,
            tooltip: {mode: "scrubber"},
            drawLegend: true
        };
        $scope.data = PlantaServices.Pruebas.Lecturas({id: $routeParams.PruebaID}, function () {
            $scope.max = Math.floor($scope.data.length / 60);
            $scope.min = 0;
        });
        $scope.minute = 1;
        $scope.$watch("minute", function () {
            var index = $scope.minute * 60;
            var i = 0;
            $scope.lastMinute = [];
            if ($scope.data.length >= index + 60)
                for (i = 0; i < 60; i++) {
                    $scope.lastMinute.push($scope.data[i + index]);
                }
        });
    }]);
app.controller("PruebaSinCargaController", [
    "$scope", "$filter", "$interval", "$routeParams", "PlantaServices", "$timeout", "$location",
    function ($scope, $filter, $interval, $routeParams, PlantaServices, $timeout, $location) {
        BaseController.call(this, $scope, $filter, $interval, $routeParams, PlantaServices, $timeout, $location);
        $scope.Iteraciones = {current: 0, Iteracciones: [{No: 1, Time: 5 * 60, current: 0}]};
        $timeout(function () {
        }, 1000);
        $scope.Process = function () {
            $scope.Estado = $scope.Estatus.Running;
            $scope.data = [];
            $scope.Iteraciones.current = 0;
            $scope.Iteraciones.Iteracciones[0].current = 0;
            $scope.prueba = new PlantaServices.Pruebas({
                ensamble: {id: $scope.ensamble.id},
                tipo: 0,
                estatus: "Ejecutando",
                dtInicio: new Date(), dtFin: new Date(),
                comentario: null,
                incidencias: null
            });
            $scope.prueba.$save(function () {
                noty({text: "Comenzando Prueba de carga "});
                $scope.timer = $interval(function () {
                    $scope.prueba.dtFin = new Date();
                    if ($scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current >= $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].Time) {
                        $scope.Iteraciones.current++;
                        var current = $scope.now;
                        current.index = $scope.Iteraciones.current;
                        $scope.Accumulate.push(current);
                        if ($scope.Iteraciones.Iteracciones.length === $scope.Iteraciones.current) {
                            noty({text: "Terminando prueba¡¡¡", type: 'success'});
                            return $scope.Stop();
                        }
                        else {
                            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current = 0;
                            noty({text: "Comenzando Iteracion " + $scope.Iteraciones.current + "¡¡¡", type: 'success'});
                        }
                    }
                    if ($scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].alert)
                        if ($scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].alert.time
                                === $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current) {
                            noty({text: $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].alert.msg,
                                type: "error"});
                        }
                    $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current++;
                    $scope.AccumulateTime++;
                }, 1000);
                $scope.Poller = $interval(function () {
                    PlantaServices.Pruebas.GetValues({id: $scope.prueba.id, seg: $scope.AccumulateTime, ite: $scope.Iteraciones.current, equipo: $scope.ensamble.carriles.equipo}
                    , function (response) {
                        $scope.now = response;
                        $scope.now.time = $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current;
                        $scope.lastMinute.push($scope.NowToLastMinute());
                        if ($scope.lastMinute.length > (30 / $scope.RefreshTime)) {
                            $scope.lastMinute.shift();
                        }

                        $scope.data.push({x: $scope.AccumulateTime, L1N: $scope.now.L1N, L2N: $scope.now.L2N, L3N: $scope.now.L3N});
                    }, function () {

                    });
                }, $scope.RefreshTime * 1000);
            }, function () {
                $scope.Estado = $scope.Estatus.Waiting;
            });
        };
    }]);
app.controller("PruebaConCargaController", [
    "$scope", "$filter", "$interval", "$routeParams", "PlantaServices", "$timeout", "$location",
    function ($scope, $filter, $interval, $routeParams, PlantaServices, $timeout, $location) {
        BaseController.call(this, $scope, $filter, $interval, $routeParams, PlantaServices, $timeout, $location);
        $scope.Accumulate = [];
        $scope.Iteraciones = {current: 0,
            Iteracciones: [
                {No: 1, Time: 5 * 60, current: 0, alert: {msg: 'Aumentar la carga a 50%¡¡¡', time: 240}},
                {No: 2, Time: 5 * 60, current: 0, alert: {msg: 'Aumentar la carga a 75%¡¡¡', time: 240}},
                {No: 3, Time: 5 * 60, current: 0, alert: {msg: 'Aumentar la carga a 100%¡¡¡', time: 240}},
                {No: 4, Time: 10 * 60, current: 0 },
                {No: 5, Time: 10 * 60, current: 0},
                {No: 6, Time: 10 * 60, current: 0},
                {No: 7, Time: 10 * 60, current: 0},
                {No: 8, Time: 10 * 60, current: 0},
                {No: 9, Time: 10 * 60, current: 0}
            ]};
        $scope.Process = function () {
            $scope.Estado = $scope.Estatus.Running;
            $scope.data = [];
            $scope.Iteraciones.current = 0;
            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current = 0;
            $scope.prueba = new PlantaServices.Pruebas({
                ensamble: {id: $scope.ensamble.id},
                tipo: 1,
                estatus: "Ejecutando",
                dtInicio: new Date(), dtFin: new Date()

            });
            $scope.prueba.$save(function () {
                noty({text: "Comenzando Prueba de carga "});
                $scope.timer = $interval(function () {
                    if ($scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current >= $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].Time) {
                        $scope.Iteraciones.current++;
                        var current = $scope.now;
                        current.index = $scope.Iteraciones.current;
                        $scope.Accumulate.push(current);
                        if ($scope.Iteraciones.Iteracciones.length === $scope.Iteraciones.current) {
                            noty({text: "Terminando prueba¡¡¡", type: 'success'});
                            return $scope.Stop();
                        }
                        else {
                            $scope.Iteracion.push(new PlantaServices.Iteraciones({id: $scope.now.id}));
                            $scope.Iteracion[$scope.Iteraciones.current - 1 ].$save();
                            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current = 0;
                            noty({text: "Comenzando Iteracion " + $scope.Iteraciones.current + "¡¡¡", type: 'success'});
                        }
                    }
                    if ($scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].alert)
                        if ($scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].alert.time
                                === $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current) {
                            noty({text: $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].alert.msg,
                                type: "error"});
                        }
                    $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current++;
                    $scope.AccumulateTime++;
                }, 1000);
                $scope.Poller = $interval(function () {
                    PlantaServices.Pruebas.GetValues({id: $scope.prueba.id, seg: $scope.AccumulateTime, ite: $scope.Iteraciones.current, equipo: $scope.ensamble.carriles.equipo}
                    , function (response) {
                        $scope.now = response;
                        $scope.now.time = $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current;
                        $scope.lastMinute.push($scope.NowToLastMinute());
                        if ($scope.lastMinute.length > (30 / $scope.RefreshTime)) {
                            $scope.lastMinute.shift();
                        }
                        $scope.data.push({x: $scope.AccumulateTime, L1N: $scope.now.L1N, L2N: $scope.now.L2N, L3N: $scope.now.L3N});
                    }, function () {

                    });
                }, $scope.RefreshTime * 1000);
            }, function () {
                $scope.Estado = $scope.Estatus.Waiting;
            });
        };
    }]);
app.controller("PruebaConCargaSubitaCtrl", [
    "$scope", "$filter", "$interval", "$routeParams", "PlantaServices", "$timeout", "$location", "$interval",
    function ($scope, $filter, $interval, $routeParams, PlantaServices, $timeout, $location, $interval) {
        BaseController.call(this, $scope, $filter, $interval, $routeParams, PlantaServices, $timeout, $location);
        $interval.cancel($scope.isOnlinePoller);
        $interval.cancel($scope.isOnPoller);
        $scope.prueba = PlantaServices.Pruebas.get({id: $routeParams.PruebaID});
        $('#container').highcharts({
            chart: {type: 'spline', zoomType: 'x', title: 'Voltaje de fases'},
            xAxis: {
                events: {
                    afterSetExtremes: function () {
                        var extrems = this.getExtremes();
                        $scope.chart1.xAxis[0].setExtremes(extrems.min, extrems.max, true, false);
                    }
                }
            },
            series: [{name: 'L1', data: []}, {name: 'L2', data: []}, {name: 'L3', data: []}]
        });
        $('#chartCorriente').highcharts({
            chart: {type: 'spline', zoomType: 'x', title: 'Corriente de fases'},
            series: [{name: 'I1', data: []}, {name: 'I2', data: []}, {name: 'I3', data: []}]
        });
        $scope.optionsVoltaje = {
            series: [
                {
                    y: "L1N",
                    label: "L1-N",
                    color: "#ff0000",
                    type: "line",
                    thickness: "1px"
                }, {
                    y: "L2N",
                    label: "L2-N",
                    color: "#64c900",
                    type: "line",
                    thickness: "1px"
                }, {
                    y: "L3N",
                    label: "L3-N",
                    color: "#428bca",
                    type: "line",
                    thickness: "1px"
                }
            ],
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
            tension: 0.7,
            lineMode: "linear",
            tooltip: {mode: "scrubber"},
            drawLegend: true
        };
        $scope.optionsFrecuencia = {
            series: [
                {
                    y: "HZ",
                    label: "HZ",
                    color: "#428bca",
                    type: "line",
                    thickness: "1px"
                }
            ],
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
            lineMode: "linear",
            tension: 0.7,
            tooltip: {mode: "scrubber"},
            drawLegend: true
        };
        $scope.optionsTemperatura = {
            series: [
                {
                    y: "Temp",
                    label: "Temp",
                    color: "#428bca",
                    type: "line",
                    thickness: "1px"
                }
            ],
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
            lineMode: "linear",
            tension: 0.7,
            tooltip: {mode: "scrubber"},
            drawLegend: true
        };
        $scope.optionsPresionAceite = {
            series: [
                {
                    y: "Presion",
                    label: "PSI",
                    color: "#428bca",
                    type: "line",
                    thickness: "1px"
                }
            ],
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
            lineMode: "linear",
            tension: 0.7,
            tooltip: {mode: "scrubber"},
            drawLegend: true
        };
        $scope.chart = $('#container').highcharts();
        $scope.chart1 = $('#chartCorriente').highcharts();
        $scope.datos = PlantaServices.Pruebas.LecturasCS({id: $routeParams.PruebaID}, function () {
            var count = 0;
            for (count = 0; count < $scope.datos.listaCargasubitaDTO.length; count++) {
                $scope.datos.listaCargasubitaDTO[count].iteracion = count + 1;
            }

            angular.forEach($scope.datos.listaLecturaDTO, function (item) {
                $scope.chart.series[0].addPoint([item.x, parseFloat(item.L1N)], false);
                $scope.chart.series[1].addPoint([item.x, parseFloat(item.L2N)], false);
                $scope.chart.series[2].addPoint([item.x, parseFloat(item.L3N)], false);
                $scope.chart1.series[0].addPoint([item.x, parseFloat(item.I1)], false);
                $scope.chart1.series[1].addPoint([item.x, parseFloat(item.I2)], false);
                $scope.chart1.series[2].addPoint([item.x, parseFloat(item.I3)], false);
            });
            $scope.chart.redraw();
            $scope.chart1.redraw();
        });
    }]);
app.controller("PruebaConCargaSubitaController", [
    "$scope", "$filter", "$interval", "$routeParams", "PlantaServices", "$timeout", "$location", "$interval",
    function ($scope, $filter, $interval, $routeParams, PlantaServices, $timeout, $location, $interval) {
        BaseController.call(this, $scope, $filter, $interval, $routeParams, PlantaServices, $timeout, $location);
        $scope.Accumulate = [];
        $scope.Iteraciones = {current: 0,
            Iteracciones: [
                {No: 1, Time: 1 * 60, current: 0},
                {No: 2, Time: 1 * 60, current: 0},
                {No: 3, Time: 1 * 60, current: 0}
            ]};
        $scope.data = {voltaje: [], hz: []};
        $scope.Process = function () {
            $scope.Estado = $scope.Estatus.Running;
            $scope.data = {voltaje: [], hz: []};
            $scope.Iteraciones.current = 0;
            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current = 0;
            $scope.prueba = new PlantaServices.Pruebas({
                ensamble: {id: $scope.ensamble.id},
                tipo: 2,
                estatus: "Ejecutando",
                dtInicio: new Date(),
                dtFin: new Date()
            });
            $scope.prueba.$save(function () {
                noty({text: "Comenzando Prueba de Carga Subita"});
                $scope.timer = $interval(function () {

                    if ($scope.now.L1N >= $scope.valores.Min.L1N && $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].active) {
                        var current = $scope.now;
                        current.index = $scope.Iteraciones.current;
                        $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current;
                        current.voltaje = {max: 0, min: 0};
                        current.hz = {max: 0, min: 0};
                        current.voltaje.max = _.max($scope.data.voltaje);
                        current.voltaje.min = _.min($scope.data.voltaje);
                        current.hz.max = _.max($scope.data.hz);
                        current.hz.min = _.min($scope.data.hz);
                        var cargsubita = {
                            hinicio: current.hz.max
                            , hfinal: current.hz.min
                            , vfinal: current.voltaje.max
                            , vinicio: current.voltaje.min
                            , icarga: current.index
                            , seg: current.time
                            , prueba: $scope.prueba
                        };
                        PlantaServices.CargaSubita.save(cargsubita, function () {
                        });
                        $scope.Accumulate.push(current);
                        $scope.Iteraciones.current++;
                        if ($scope.Iteraciones.Iteracciones.length === $scope.Iteraciones.current) {
                            noty({text: "Terminando prueba¡¡¡", type: 'success'});
                            return $scope.Stop();
                        }
                        else {
                            $scope.data = {voltaje: [], hz: []};
                            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current = 0;
                            noty({text: "Comenzando Iteracion " + $scope.Iteraciones.current + "¡¡¡", type: 'success'});
                        }
                    }
                    if (!$scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].active)
                        if ($scope.now.L1N < $scope.valores.Min.L1N)
                            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].active = true;
                    if ($scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].active) {
                        $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current++;
                        $scope.data.voltaje.push($scope.now.L1N);
                        $scope.data.hz.push($scope.now.HZ);
                    }
                    $scope.AccumulateTime++;
                }, 1000);
                $scope.Poller = $interval(function () {
                    PlantaServices.Pruebas.GetValues({id: $scope.prueba.id, seg: $scope.AccumulateTime, ite: $scope.Iteraciones.current, equipo: $scope.ensamble.carriles.equipo}
                    , function (response) {
                        $scope.now = response;
                        $scope.now.time = $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current;
                        $scope.lastMinute.push($scope.NowToLastMinute());
                        if ($scope.lastMinute.length > (30 / $scope.RefreshTime)) {
                            $scope.lastMinute.shift();
                        }

                    }, function () {
                    });
                }, 1000);
            }, function () {
                $scope.Estado = $scope.Estatus.Waiting;
            }
            );
        };
    }]);
app.controller("PruebaControlCtrl", [
    "$scope", "$filter", "$interval", "$routeParams", "PlantaServices", "$timeout", "$location",
    function ($scope, $filter, $interval, $routeParams, PlantaServices, $timeout, $location) {
        BaseController.call(this, $scope, $filter, $interval, $routeParams, PlantaServices, $timeout, $location);
        $scope.prueba = new PlantaServices.Pruebascontrol({
            tipo: 3,
            estatus: "Creada",
            dtInicio: new Date(),
            dtFin: new Date(),
            contactos: [],
            comentario: null,
            incidencias: null
        });
        $scope.GetData = function () {
            PlantaServices.Plantas.GetProteccion({id: $scope.ensamble.carriles.planta}, function (response) {
                $scope.prueba.termometro = response.config.TempSd;
                $scope.prueba.presion = response.config.PressSD;
                $scope.prueba.sobrevelocidad = response.config.Overspeed;
            });
        };
        $scope.AddContacto = function () {
            $scope.prueba.contactos.push({});
        };
        $scope.RemoveContacto = function (contacto) {
            $scope.prueba.contactos.pop(contacto);
        };
        $scope.contactos = PlantaServices.Contactos.query();
        $scope.GuardarPrueba = function () {
            $scope.prueba.ensamble = {id: $scope.ensamble.id};
            $scope.prueba.estatus = "Finalizada";
            if ($scope.prueba.id)
                $scope.prueba.$update(function () {
                }, function () {
                });
            else
                $scope.prueba.$save(function () {
                }, function () {
                });
        };
    }]);
app.controller("PruebaControlViewCtrl", [
    "$scope", "$filter", "$interval", "$routeParams", "PlantaServices", "$timeout", "$location",
    function ($scope, $filter, $interval, $routeParams, PlantaServices, $timeout, $location) {
        BaseController.call(this, $scope, $filter, $interval, $routeParams, PlantaServices, $timeout, $location);
        $interval.cancel($scope.isOnlinePoller);
        $interval.cancel($scope.isOnPoller);
        $scope.prueba = PlantaServices.Pruebascontrol.get({id: $routeParams.PruebaID});
    }]);
app.controller("EnsambleController", ["$scope", "PlantaServices", "$filter", "$location", "$interval",
    function ($scope, PlantaServices, $filter, $location, $interval) {
        $scope.motores = PlantaServices.Motores.query();
        $scope.submitted = false;
        $scope.interracted = function (field) {
            return $scope.submitted || field.$dirty;
        };
        $scope.tipoServicio = [{id: 0, text: "Emergencia"}, {id: 1, text: "Continuo"}];
        $scope.tipoControl = [{id: 0, text: "Intellite"}];
        $scope.combustible = [{id: 0, text: "Diesel"}, {id: 1, text: "Gasolina"}];
        $scope.carriles = PlantaServices.Carriles.query();
        $scope.tiposConexion = PlantaServices.Pruebas.TipoConexion();
        $scope.Generadores = PlantaServices.Generador.query();
        $scope.$on("$destroy", function () {
            $interval.cancel($scope.OnlinePoller);
        });
        $scope.checkOnline = function () {
            $scope.OnlinePoller = $interval(function () {
                if ($scope.ensamble.cariles)
                    PlantaServices.Plantas.isOnline({id: $scope.ensamble.cariles.planta}
                    , function (response) {
                        $scope.isOnline = response.isOnline;
                    });
                else {
                    $scope.isOnline = false;
                }
            }, 5000);
        };
        $scope.autoconfig = function () {
            if ($scope.ensamble.cariles) {
                PlantaServices.Pruebas.AutoConfig({equipo: $scope.ensamble.cariles.planta}
                , function (response) {
                    $scope.AutoConfigPlanta = response;
                    angular.extend($scope.planta, $scope.AutoConfigPlanta.config);
                });
            }
        };
        $scope.searchOP = function (term) {
            $scope.OPS = PlantaServices.Produccion.GetByOP({noOP: term});
        };
        $scope.selectOp = function (op) {
            $scope.planta.op = op;
            $scope.planta.noOp = op.op;
        };
        $scope.SelectedMotor = function () {
            if (!$scope.planta.motores)
                return;
            var motor = $filter("filter")($scope.motores, function (item) {
                return item.modelo === $scope.planta.motores.modelo;
            });
            if (motor.length !== 0)
                return motor[0];
        };
        $scope.onSelectMotor = function () {
            $scope.planta.modelo = $scope.SelectedMotor().modeloPlanta;
        };
        $scope.planta = {};        
        $scope.submit = function () {
            var planta = new PlantaServices.Plantas($scope.planta);
            planta.$save(
                    function () {
                        var prueba = new PlantaServices.Ensambles({
                            folio: "14PR000001",
                            dtCreacion: new Date(),
                            planta: planta,
                            dtProgramada: $scope.ensamble.dtProgramada,
                            cariles: $scope.ensamble.cariles,
                            altitud: $scope.ensamble.altitud,
                            patin: $scope.ensamble.patin,
                            guardas: $scope.ensamble.guardas,
                            rediador: $scope.ensamble.rediador,
                            estatus: "Programada"
                        });
                        prueba.$save(function () {
                            noty({text: "Planta registrada con el folio : " + prueba.folio + "¡¡¡", type: "success", modal: true});
                            prueba.$QR({id: prueba.id}, function () {
                                $location.path("/Pruebas/" + prueba.id);
                            }, function () {
                                $location.path("/Pruebas/" + prueba.id);                               
                            });
                        }, function () {
                            planta.$delete(function () {
                            }, function () {
                                alert("Ocurrio un error");
                            });
                            alert("Ocurrio un error");
                        });
                    },
                    function () {
                        alert("Ocurrio un error");
                    }
            );
        };
    }]);
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
app.controller("PruebaVacioCtrl", ["$routeParams", "$scope", "PlantaServices", "$timeout",
    function ($routeParams, $scope, PlantaServices, $timeout) {
        $scope.ensamble = PlantaServices.EnsambleArranque.get({id: $routeParams.EnsambleID}, function () {
            $scope.prueba = new PlantaServices.Vacio({
                dtInicio: new Date(), dtFin: new Date(), tipo: 2, estatus: 0,
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
                $scope.prueba.estatus = 'Creada';
            });
        };
    }]);
app.controller("PruebaArranqueCtrl", ["$routeParams", "$scope", "PlantaServices", "$timeout",
    function ($routeParams, $scope, PlantaServices, $timeout) {
        $scope.ensamble = PlantaServices.EnsambleArranque.get({id: $routeParams.EnsambleID}, function () {
            $scope.prueba = new PlantaServices.Arranque({
                dtInicio: new Date(), dtFin: new Date(), tipo: 1, estatus: 0,
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
                $scope.prueba.estatus = 'Creada';
            });
        };
    }]);
app.controller("PruebaArranqueViewCtrl", ["$routeParams", "$scope", "PlantaServices", "$timeout",
    function ($routeParams, $scope, PlantaServices, $timeout) {
        $scope.ensamble = PlantaServices.EnsambleArranque.get({id: $routeParams.EnsambleID},
        function () {
        });
        $scope.prueba = new PlantaServices.Arranque.get({id: $routeParams.PruebaID});
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
                $scope.prueba.estatus = 'Creada';
            });
        };
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
app.run(["$rootScope", "PlantaServices", "amMoment", "$filter", "$window",
    function ($rootScope, PlantaServices, amMoment, $filter, $window) {
        amMoment.changeLocale('es');
        $rootScope.user = PlantaServices.Usuarios.current();
        $rootScope.GoBack = function () {
            $window.history.back();
        };
        $rootScope.hasRole = function (role) {
            if ($rootScope.user === undefined) {
                return false;
            }
            if ($rootScope.user.roles === undefined) {
                return false;
            }
            return $filter("filter")($rootScope.user.roles, function (item) {
                return item === role;
            }).length !== 0;
        };
    }]);
app.controller('MainCtrl', ['$scope', '$timeout', '$log', 'Uuid', 'Sample', 'ganttMouseOffset', 'moment', "PlantaServices",
    function ($scope, $timeout, $log, Uuid, Sample, mouseOffset, moment, PlantaServices) {
        $scope.options = {
            mode: 'custom',
            scale: 'day',
            maxHeight: false,
            width: false,
            autoExpand: 'none',
            taskOutOfRange: 'truncate',
            fromDate: undefined,
            toDate: undefined,
            'show-side': true,
            currentDate: 'line',
            currentDateValue: new Date(),
            draw: false,
            readOnly: true,
            filterTask: undefined,
            filterRow: undefined,
            dateFrames: {
                'weekend': {
                    evaluator: function (date) {
                        return date.isoWeekday() === 6 || date.isoWeekday() === 7;
                    },
                    targets: ['weekend']
                }
            },
            timeFramesNonWorkingMode: 'visible',
            columnMagnet: '5 minutes',
            api: function (api) {
                $scope.api = api;
            }
        };
        Sample.getSample().success(function (response) {
            $scope.data = response;
        });
    }]);
app.controller('ProgramacionPruebasArranqueCtrl', ['$scope', '$timeout', '$log', 'Uuid', 'Sample', 'ganttMouseOffset', 'moment', "PlantaServices",
    function ($scope, $timeout, $log, Uuid, Sample, mouseOffset, moment, PlantaServices) {
        $scope.options = {
            mode: 'custom',
            scale: 'day',
            maxHeight: false,
            width: false,
            autoExpand: 'none',
            taskOutOfRange: 'truncate',
            fromDate: undefined,
            toDate: undefined,
            showLabelsColumn: true,
            currentDate: 'line',
            currentDateValue: new Date(),
            draw: false,
            readOnly: false,
            filterTask: undefined,
            filterRow: undefined,
            dateFrames: {
                'weekend': {
                    evaluator: function (date) {
                        return date.isoWeekday() === 6 || date.isoWeekday() === 7;
                    },
                    targets: ['weekend']
                }
            },
            timeFramesNonWorkingMode: 'visible',
            columnMagnet: '5 minutes',
            api: function (api) {
                $scope.api = api;
                api.core.on.ready($scope, function () {
                    $scope.loadData([{name: "Carril 3", tasks: [{
                                    from: "2014-11-04", to: 1415167200000, name: "Planta OP017019", color: "green"}]},
                        {name: "Carril 5", tasks: []}]);
//                    $scope.api.data.clear();
//                    $scope.addSamples();
                    api.directives.on.destroy($scope, function (directiveName, directiveScope, element) {
                        if (directiveName === 'ganttRow') {
                            element.off('mousedown', directiveScope.drawHandler);
                            delete directiveScope.drawHandler;
                        }
                    });
                });
            }
        };
        $scope.addSamples = function () {
            $scope.api.timespans.load(Sample.getSampleTimespans().timespan1);
            PlantaServices.Planeacion.Arranques(function (data) {
                $scope.loadData(data);
            });
        };
        $scope.loadData = function (data) {
            $scope.api.data.load(data);
        };
    }]);
app.controller("PruebaConCargaCtrl", [
    "$scope", "$filter", "$interval", "$routeParams", "PlantaServices", "$timeout", "$interval",
    function ($scope, $filter, $interval, $routeParams, PlantaServices, $timeout, $interval) {
        BaseController.call(this, $scope, $filter, $interval, $routeParams, PlantaServices, $timeout);
        $interval.cancel($scope.isOnlinePoller);
        $interval.cancel($scope.isOnPoller);
        $scope.prueba = PlantaServices.Pruebas.get({id: $routeParams.PruebaID});
        $scope.optionsVoltaje = {
            series: [
                {
                    y: "L1N",
                    label: "L1-N",
                    color: "#ff0000",
                    type: "line",
                    thickness: "1px"
                }, {
                    y: "L2N",
                    label: "L2-N",
                    color: "#64c900",
                    type: "line",
                    thickness: "1px"
                }, {
                    y: "L3N",
                    label: "L3-N",
                    color: "#428bca",
                    type: "line",
                    thickness: "1px"
                }
            ],
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
            lineMode: "linear",
            tension: 0.7,
            tooltip: {mode: "scrubber"},
            drawLegend: true
        };
        $scope.optionsFrecuencia = {
            series: [
                {
                    y: "HZ",
                    label: "HZ",
                    color: "#428bca",
                    type: "line",
                    thickness: "1px"
                }
            ],
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
            lineMode: "linear",
            tension: 0.7,
            tooltip: {mode: "scrubber"},
            drawLegend: true
        };
        $scope.optionsTemperatura = {
            series: [
                {
                    y: "Temp",
                    label: "Temp",
                    color: "#428bca",
                    type: "line",
                    thickness: "1px"
                }
            ],
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
            lineMode: "linear",
            tension: 0.7,
            tooltip: {mode: "scrubber"},
            drawLegend: true
        };
        $scope.optionsPresionAceite = {
            series: [
                {
                    y: "Presion",
                    label: "PSI",
                    color: "#428bca",
                    type: "line",
                    thickness: "1px"
                }
            ],
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
            lineMode: "linear",
            tension: 0.7,
            tooltip: {mode: "scrubber"},
            drawLegend: true
        };
        $scope.optionsCorriente = {
            series: [
                {
                    y: "I1",
                    label: "I1",
                    color: "#ff0000",
                    type: "line",
                    thickness: "1px"
                }, {
                    y: "I2",
                    label: "I2",
                    color: "#64c900",
                    type: "line",
                    thickness: "1px"
                }, {
                    y: "I3",
                    label: "I3",
                    color: "#428bca",
                    type: "line",
                    thickness: "1px"
                }
            ],
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}},
            lineMode: "linear",
            tension: 0.7,
            tooltip: {mode: "scrubber"},
            drawLegend: true
        };
        $scope.numIteracion = "";
        $scope.data = PlantaServices.Pruebas.Lecturas({id: $routeParams.PruebaID}, function () {
        });
        $scope.datosCC = PlantaServices.Pruebas.LecturasCC({id: $routeParams.PruebaID}, function () {
            $scope.max = Math.floor($scope.datosCC.length / 60);
            $scope.min = 0;
            var count = 0;
            for (count = 0; count < $scope.datosCC.length; count++) {
                $scope.datosCC[count].minutos = 0;
                $scope.datosCC[count].porcentajeCarga = 0;
                var minutosAndSegundos = (Math.floor(($scope.datosCC[count].x) / 60)) + " min y " + (($scope.datosCC[count].x) % 60) + " seg.";
                $scope.datosCC[count].minutos = (minutosAndSegundos);
                if ($scope.datosCC[count].Iteracion === 0) {
                    $scope.datosCC[count].porcentajeCarga = "25%";
                } else if ($scope.datosCC[count].Iteracion === 1) {
                    $scope.datosCC[count].porcentajeCarga = "50%";
                } else if ($scope.datosCC[count].Iteracion === 2) {
                    $scope.datosCC[count].porcentajeCarga = "75%";
                } else if ($scope.datosCC[count].Iteracion > 2 && $scope.datosCC[count].Iteracion < 10) {
                    $scope.datosCC[count].porcentajeCarga = "100%";
                } else {
                    $scope.datosCC[count].porcentajeCarga = "-";
                }

            }

        });
        $scope.minute = 1;
        $scope.$watch("minute", function () {
            var index = $scope.minute * 60;
            var i = 0;
            $scope.lastMinute = [];
            if ($scope.data.length >= index + 60)
                for (i = 0; i < 60; i++) {
                    $scope.lastMinute.push($scope.data[i + index]);
                }
        });
        $scope.Redondear2Dec = function (numero) {
            var result = parseFloat(numero).toFixed(2);
            return result;
        };
        $scope.ValMax_I1_CC = $scope.ValMax_I2_CC = $scope.ValMax_I3_CC = $scope.Redondear2Dec(0);
        $scope.ValMax_L1N_CC = $scope.ValMax_L2N_CC = $scope.ValMax_L3N_CC = $scope.Redondear2Dec(0);
        $scope.ValMax_Frec_CC = $scope.Redondear2Dec(0);
        $scope.ValMax_Temp_CC = $scope.Redondear2Dec(0);
        $scope.ValMax_Presion_CC = $scope.Redondear2Dec(0);
        $scope.ValMax_Carga_Alter_CC = $scope.Redondear2Dec(0);
        $scope.ValMin_I1_CC = $scope.ValMin_I2_CC = $scope.ValMin_I3_CC = $scope.Redondear2Dec(0);
        $scope.ValMin_L1N_CC = $scope.ValMin_L2N_CC = $scope.ValMin_L3N_CC = $scope.Redondear2Dec(0);
        $scope.ValMin_Frec_CC = $scope.Redondear2Dec(0);
        $scope.ValMin_Temp_CC = $scope.Redondear2Dec(0);
        $scope.ValMin_Presion_CC = $scope.Redondear2Dec(0);
        $scope.ValMin_Carga_Alter_CC = $scope.Redondear2Dec(0);
        $scope.calcularValoresEsperados = function (iteracion) {
            $scope.numIteracion = iteracion;
            if ($scope.numIteracion >= 0 && $scope.numIteracion <= 9) {

                $scope.ValMax_I1_CC = $scope.ValMax_I2_CC = $scope.ValMax_I3_CC = $scope.Redondear2Dec((($scope.pruebas.planta.motores.kw * 1000) / $scope.pruebas.planta.voltaje) * (100 - $scope.pruebas.planta.motores.derrateo) * 0.0025 * 1.01 * $scope.numIteracion);
                $scope.ValMax_L1N_CC = $scope.ValMax_L2N_CC = $scope.ValMax_L3N_CC = $scope.Redondear2Dec($scope.pruebas.planta.voltaje * 1.01);
                $scope.ValMax_Frec_CC = $scope.Redondear2Dec($scope.pruebas.planta.motores.frecuenciaOperacion * 1.01);
                $scope.ValMax_Temp_CC = $scope.Redondear2Dec(100);
                $scope.ValMax_Presion_CC = $scope.Redondear2Dec($scope.pruebas.planta.motores.presionMax);
                $scope.ValMax_Carga_Alter_CC = "N/A";
                $scope.ValMin_I1_CC = $scope.ValMin_I2_CC = $scope.ValMin_I3_CC = $scope.Redondear2Dec((($scope.pruebas.planta.motores.kw * 1000) / $scope.pruebas.planta.voltaje) * (100 - $scope.pruebas.planta.motores.derrateo) * 0.0025 * 0.99 * $scope.numIteracion);
                $scope.ValMin_L1N_CC = $scope.ValMin_L2N_CC = $scope.ValMin_L3N_CC = $scope.Redondear2Dec($scope.pruebas.planta.voltaje * 0.99);
                $scope.ValMin_Frec_CC = $scope.Redondear2Dec($scope.pruebas.planta.motores.frecuenciaOperacion * 0.99);
                $scope.ValMin_Temp_CC = "N/A";
                $scope.ValMin_Presion_CC = $scope.Redondear2Dec(1.5 * 14.5038);
                $scope.ValMin_Carga_Alter_CC = "N/A";
            }

        };
    }]);
app.controller("PlantasEditCtrl", function ($scope, PlantaServices, $routeParams) {
    $scope.planta = PlantaServices.Plantas.get({noSerie: $routeParams.id});
    $scope.motores = PlantaServices.Motores.query();
    $scope.tiposConexion = PlantaServices.Pruebas.TipoConexion();
    $scope.Save = function () {
        $scope.planta.$update(function () {

        });
    };
});
app.controller("MiCuentaController", function ($scope, $rootScope, PlantaServices) {
    $scope.usuario = $rootScope.user;
    $scope.submitted = false;
    $scope.submittedPass = false;
    $scope.interacted = function (field) {
        return $scope.submitted || field.$dirty;
    };
    $scope.interactedPass = function (field) {
        return $scope.submittedPass || field.$dirty;
    };
    $scope.ChangeDataUser = function () {
        $scope.submitted = true;
    };
    $scope.ChangePass = function () {
        $scope.submittedPass = true;
        PlantaServices.Usuarios.ChangePassword($scope.usuario, function (response) {
            console.log(response);
            if (response.result === true) {
                noty({text: "Contraseña actulizada correctamente¡¡¡", type: "success"});
            }
            else {
                noty({text: "Datos incorrectos¡¡¡", type: "error"});
            }
        }, function () {
        });
    };
});

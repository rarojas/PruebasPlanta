var ControlPlantaCtrl = function ($scope, PlantaService) {
    $scope.CarrilId = {};
};
app.directive('controlplanta', function () {
    return {
        templateUrl: '/templates/directives/ControlPlanta.jsp'
    };
});
app.directive('incidenciasctrl', function () {
    return {
        templateUrl: '/templates/directives/Incidencias.html'
    };
});
app.directive('tableCtrl', function () {
    return {
        replace: true,
        restrict: 'EA',
        templateUrl: '/templates/directives/table.jsp'
    };
});
app.directive('datosprueba', function () {
    return {
        replace: true,
        restrict: 'EA',
        templateUrl: '/templates/directives/DatosPrueba.jsp'
    };
});
app.directive('datospruebaarranque', function () {
    return {
        replace: true,
        restrict: 'EA',
        templateUrl: '/templates/directives/DatosPruebaArranque.jsp'
    };
});
app.directive('tableCtrlCarga', function () {
    return {
        replace: true,
        restrict: 'EA',
        templateUrl: '/templates/directives/tablecarga.jsp'
    };
});
app.directive('tableCtrlSubita', function () {
    return {
        replace: true,
        restrict: 'EA',
        templateUrl: '/templates/directives/tablesubita.jsp'
    };
});
app.directive('tooltip', function () {
    return {
        replace: true,
        restrict: 'CA',
        link: function (scope, element, attrs, controller) {
            element.tooltip();
        }
    };
});
app.filter('minutos', function () {
    return function (input) {
        input = input || 0;
        var seg = input % 60;
        var min = Math.floor(input / 60);
        var out = min.toString() + ":" + (seg < 10 ? "0" : "") + seg.toString();
        return out;
    };
});
app.directive('check', function () {
    return {
        replace: true,
        restrict: 'EA',
        scope: {
            value: "=value"
        },
        template: '<div> <i class="fa fa-2x" ng-class=" { \'fa-check success\': value,\'fa-close danger\': !value }" ></i></div>'
    };
});
app.directive('estatusPrueba', function () {
    return {
        replace: true,
        restrict: 'EA',
        scope: {
            value: "=value"
        },
        controller: ["$scope", function ($scope) {
                $scope.msg = "Desconocido";
                $scope.icon = "fa-question";
                switch ($scope.value) {
                    case "Creada":
                        $scope.msg = "Creada";
                        $scope.icon = "fa-plus";
                        break;
                    case "Ejecutando":
                        $scope.msg = "En curso";
                        $scope.icon = "fa-play";
                        break;
                    case "Finalizada":
                        $scope.msg = "Finalizada";
                        $scope.icon = "fa-stop";
                        break;
                    case "RechazadaEjecutor":
                        $scope.msg = "Rechazada Ejecutor";
                        $scope.icon = "fa-times";
                        break;
                    case "AutorizadoEjecutor":
                        $scope.msg = "Autorizada Ejecutor";
                        $scope.icon = "fa-check";
                        break;
                    case "RechazadaSupervisor":
                        $scope.msg = "Rechazada Supervisor";
                        $scope.icon = "fa-times-circle";
                        break;
                    case "AutorizadaSupervisor":
                        $scope.msg = "Autorizada Supervisor";
                        $scope.icon = "fa-check-circle";
                        break;
                }
            }],
        template: '<div><label> {{msg}}</label>  <i class="fa {{icon}}"></i></div>'
    };
});
app.directive('listPruebas', function () {
    return {
        replace: true,
        restrict: 'EA',
        scope: {
            prueba: "=prueba",
            url: "@url",
            urlview: "@urlview",
            tipo: "@tipo"
        },
        templateUrl: '/templates/directives/listPruebas.html'
    };
});
app.directive('listPruebasArranque', function () {
    return {
        replace: true,
        restrict: 'EA',
        scope: {
            prueba: "=prueba",
            url: "@url",
            urlview: "@urlview",
            tipo: "@tipo"
        },
        templateUrl: '/templates/directives/listPruebasArranque.html'
    };
});
app.directive('circle', function () {
    return {
        replace: true,
        restrict: 'EA',
        scope: {
            color: "=color"
        },
        template: "<div class='full-circle' ng-style='{\"background-color\": color}'><div>"
    };
});

app.factory("EstatusPlanta", ["$rootScope", "PlantaServices",
    function ($rootScope, PlantaServices) {
        var planta = {};
        planta.ensamble = null;
        planta.on = null;
        planta.lastRead = {};
        planta.Paro = function () {
            PlantaServices.Plantas.Off({id: $scope.ensamble.id}, {id: $scope.ensamble.id}, function () {
                noty({text: "Apagado de planta exitoso¡¡¡", type: "success"});
            }, function () {
                noty({text: "Error al apagar la planta la planta¡¡¡", type: "error"});
            });
        };
        planta.Arranque = function () {
            PlantaServices.Plantas.On({id: $scope.ensamble.id}, {id: $scope.ensamble.id}, function () {
                noty({text: "Encendido de planta exitoso¡¡¡", type: "success"});
            }, function () {
                noty({text: "Error al encender la planta¡¡¡", type: "error"});
            });
        };


        return planta;
    }]);


app.directive('autocomplete', ["$timeout", function ($timeout) {
        return {
            restrict: 'E',
            transclude: true,
            replace: true,
            template: '<div><form><input class="form-control" ng-model="term" ng-change="query()" type="text" autocomplete="off" /></form><div ng-transclude></div></div>',
            scope: {
                search: "&",
                select: "&",
                items: "=",
                term: "="
            },
            controller: ["$scope", function ($scope) {
                    $scope.items = [];
                    $scope.hide = false;
                    this.activate = function (item) {
                        $scope.active = item;
                    };
                    this.activateNextItem = function () {
                        var index = $scope.items.indexOf($scope.active);
                        this.activate($scope.items[(index + 1) % $scope.items.length]);
                    };
                    this.activatePreviousItem = function () {
                        var index = $scope.items.indexOf($scope.active);
                        this.activate($scope.items[index === 0 ? $scope.items.length - 1 : index - 1]);
                    };
                    this.isActive = function (item) {
                        return $scope.active === item;
                    };
                    this.selectActive = function () {
                        this.select($scope.active);
                    };

                    this.select = function (item) {
                        $scope.hide = true;
                        $scope.focused = true;
                        $scope.select({item: item});
                    };

                    $scope.isVisible = function () {
                        return !$scope.hide && ($scope.focused || $scope.mousedOver);
                    };
                    var filterTextTimeout;
                    $scope.query = function () {
                        if (($scope.term.length | 0) > 2) {
                            if (filterTextTimeout)
                                $timeout.cancel(filterTextTimeout);
                            filterTextTimeout = $timeout(function () {
                                $scope.hide = false;
                                $scope.search({term: $scope.term});
                            }, 250);

                        }
                    };
                }],
            link: function (scope, element, attrs, controller) {

                var $input = element.find('form > input');
                var $list = element.find('> div');

                $input.bind('focus', function () {
                    scope.$apply(function () {
                        scope.focused = true;
                    });
                });

                $input.bind('blur', function () {
                    scope.$apply(function () {
                        scope.focused = false;
                    });
                });

                $list.bind('mouseover', function () {
                    scope.$apply(function () {
                        scope.mousedOver = true;
                    });
                });

                $list.bind('mouseleave', function () {
                    scope.$apply(function () {
                        scope.mousedOver = false;
                    });
                });

                $input.bind('keyup', function (e) {
                    if (e.keyCode === 9 || e.keyCode === 13) {
                        scope.$apply(function () {
                            controller.selectActive();
                        });
                    }

                    if (e.keyCode === 27) {
                        scope.$apply(function () {
                            scope.hide = true;
                        });
                    }
                });

                $input.bind('keydown', function (e) {
                    if (e.keyCode === 9 || e.keyCode === 13 || e.keyCode === 27) {
                        e.preventDefault();
                    }
                    ;

                    if (e.keyCode === 40) {
                        e.preventDefault();
                        scope.$apply(function () {
                            controller.activateNextItem();
                        });
                    }

                    if (e.keyCode === 38) {
                        e.preventDefault();
                        scope.$apply(function () {
                            controller.activatePreviousItem();
                        });
                    }
                });

                scope.$watch('items', function (items) {
                    controller.activate(items.length ? items[0] : null);
                });

                scope.$watch('focused', function (focused) {
                    if (focused) {
                        $timeout(function () {
                            $input.focus();
                        }, 0, false);
                    }
                });

                scope.$watch('isVisible()', function (visible) {
                    if (visible) {
                        var pos = $input.position();
                        var height = $input[0].offsetHeight;

                        $list.css({
                            top: pos.top + height,
                            left: pos.left,
                            position: 'absolute',
                            display: 'block'
                        });
                    } else {
                        $list.css('display', 'none');
                    }
                });
            }
        };
    }]);

app.directive('itemautocomplete', function () {
    return {
        require: '^autocomplete',
        link: function (scope, element, attrs, controller) {
            var item = scope.$eval(attrs.itemautocomplete);
            scope.$watch(function () {
                return controller.isActive(item);
            }, function (active) {
                if (active) {
                    element.addClass('active');
                } else {
                    element.removeClass('active');
                }
            });

            element.bind('mouseenter', function (e) {
                scope.$apply(function () {
                    controller.activate(item);
                });
            });

            element.bind('click', function (e) {
                scope.$apply(function () {
                    controller.select(item);
                });
            });
        }
    };
});

app.directive('gauge', function () {
    return {
        restrict: "AEC",
        scope: {
            canvasid: "@",
            value: "=",
            max: "=",
            sdvalue: "=",
            min: "=",
            unit: "@",
            title: "@",
            reverse: "@"
        },
        link: function (scope, element, attrs, controller) {
            scope.canvas = document.getElementById(attrs.canvasid);
            scope.context = scope.canvas.getContext('2d');
            scope.x = 100;
            scope.y = 150; //scope.canvas.height;
            scope.radius = 75;
            scope.startAngle = 1 * Math.PI;
            scope.endAngle = 2 * Math.PI;
            scope.counterClockwise = false;
            scope.rendervalue = 0;
            scope.lastValue = 0;
        },
        controller: function ($scope) {
            var pointCircle = function (point, distance, angle) {
                return {
                    x: point.x + (Math.sin(angle) * distance), y: point.y + (Math.cos(angle) * distance)
                };
            };
            $scope.draw = function () {
                $scope.canvas.width = $scope.canvas.width;
                $scope.context.font = "20px GloberBook";
                $scope.context.textBaseline = 'center';
                $scope.context.fillText($scope.title, $scope.x - (4.5 * $scope.title.length), 30);
                $scope.context.beginPath();
                $scope.context.arc($scope.x, $scope.y, $scope.radius, $scope.startAngle, $scope.sdAngle, $scope.counterClockwise);
                $scope.context.lineWidth = 45;
                var grd = $scope.context.createLinearGradient(30, 0, 150, 0);
                if (!$scope.reverse) {
                    grd.addColorStop(0, '#40AC00');
                    grd.addColorStop(1, '#40AC00');
                }
                else {
                    grd.addColorStop(0, '#C40000');
                    grd.addColorStop(1, '#C40000');
                }
                $scope.context.strokeStyle = grd;
                $scope.context.stroke();
                $scope.context.beginPath();
                $scope.context.arc($scope.x, $scope.y, $scope.radius, $scope.sdAngle, $scope.endAngle, $scope.counterClockwise);
                $scope.context.lineWidth = 45;
                var grd = $scope.context.createLinearGradient(30, 0, 150, 0);
                if (!$scope.reverse) {
                    grd.addColorStop(0, '#C40000');
                    grd.addColorStop(1, '#C40000');
                }
                else {
                    grd.addColorStop(0, '#40AC00');
                    grd.addColorStop(1, '#40AC00');
                }
                $scope.context.strokeStyle = grd;
                $scope.context.stroke();
                $scope.context.beginPath();
                $scope.context.arc($scope.x, $scope.y, 23, $scope.startAngle, $scope.endAngle, $scope.counterClockwise);
                $scope.context.lineWidth = 46;
                $scope.context.strokeStyle = "#344251";
                $scope.context.stroke();
                $scope.context.fillStyle = '#fff';
                $scope.context.font = "15px GloberBook";
                $scope.context.textBaseline = 'bottom';
                $scope.context.fillText($scope.rendervalue.toFixed(2) + ' ' + $scope.unit, $scope.x - 33, $scope.y);
                var range = $scope.max * 1.1;
                var centerT = pointCircle({x: $scope.x, y: $scope.y}, 47, Math.PI * (-0.5 - ($scope.rendervalue / range)));
                $scope.context.save();
                $scope.context.translate(centerT.x, centerT.y);
                $scope.context.fillStyle = "#fff";
                $scope.context.rotate(Math.PI * (+0.5 + ($scope.rendervalue / range)));
                $scope.context.beginPath();
                $scope.context.moveTo(0, 15);
                $scope.context.lineTo(-10, 0);
                $scope.context.lineTo(10, 0);
                $scope.context.closePath();
                $scope.context.fill();
                $scope.context.restore();
                for (var j = 0; j < 9; j++) {
                    var center = pointCircle({x: $scope.x, y: $scope.y}, 85, Math.PI * (-0.5 - (j / 8)));
                    $scope.context.save();
                    $scope.context.translate(center.x, center.y);
                    $scope.context.rotate(Math.PI * (-0.5 + (j / 8)));
                    $scope.context.beginPath();
                    $scope.context.fillStyle = "#fff";
                    $scope.context.font = "15px GloberBook";
                    $scope.context.textBaseline = 'middle';
                    if (j === 0)
                        $scope.context.fillText(j === 0 ? 0 : ((range * j) / 8).toFixed(0), 2, -5);
                    else
                    if (j === 8)
                        $scope.context.fillText(j === 0 ? 0 : ((range * j) / 8).toFixed(0), -12, -5);
                    else
                        $scope.context.fillText(j === 0 ? 0 : ((range * j) / 8).toFixed(0), -10, -5);
                    $scope.context.restore();
                }
            };
            $scope.$watch("max", function (val) {
                var rangeScope = $scope.max;                
                rangeScope *= 1.1;
                if ($scope.sdvalue === undefined)
                    $scope.sdvalue = $scope.max / 2;
                $scope.sdAngle = (($scope.sdvalue / rangeScope) + 1) * Math.PI;
            });
            $scope.$watch("value", function (val) {

                $scope.lastValue = $scope.value;
                $scope.value = val;
                var delay = 2000;
                var diff = val - $scope.lastValue;
                var steps = 50;
                var step = diff / steps;
                var stepDelay = delay / steps;
                value = $scope.lastValue;
                var timer = setInterval(function () {
                    $scope.rendervalue += step;
                    $scope.draw();
                    steps--;
                    if (steps === 0) {
                        clearInterval(timer);
                        setTimeout(function () {
                            $scope.rendervalue = parseFloat(val);
                            $scope.draw();
                        }, 0);
                    }
                }, stepDelay);
                $scope.draw();
            });
        }
    };
});
app.directive('asidemenu', function () {
    return {
        replace: true,
        templateUrl: '/templates/directives/asidemenu.jsp',
        link: function (scope, element, attrs, controller) {
            var $aside = element.find(".side-nav")
            $aside.bind('mouseenter', function (e) {
                $aside.removeClass("toggled");
            });
            $aside.bind('mouseleave', function (e) {
                $aside.addClass("toggled");
            });
            $aside.bind('click', function (e) {
                $aside.toggleClass("toggled");
            });
        }
    };
});





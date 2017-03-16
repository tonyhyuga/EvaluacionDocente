(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('ReporteEvaluativoOOService', ReporteEvaluativoOOService);

    ReporteEvaluativoOOService.$inject = ['$resource'];

    function ReporteEvaluativoOOService ($resource) {
        var resourceUrl =  'apo/reporteEvaluativo/reporteOO:idA,:idP,:idAnio,:idIndice,:tipoEvaluacion,:idDependencia';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' },
            'save': {
                method: 'POST',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            }
        });
    }
})();

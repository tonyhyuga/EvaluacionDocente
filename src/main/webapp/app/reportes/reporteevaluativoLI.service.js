(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('ReporteEvaluativoLIService', ReporteEvaluativoLIService);

    ReporteEvaluativoLIService.$inject = ['$resource'];

    function ReporteEvaluativoLIService ($resource) {
        var resourceUrl =  'apo/reporteEvaluativo/reporteLI:idA,:idP,:idAnio,:idIndice,:tipoEvaluacion,:idDependencia';

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
            'save': { method:'POST' }
        });
    }
})();

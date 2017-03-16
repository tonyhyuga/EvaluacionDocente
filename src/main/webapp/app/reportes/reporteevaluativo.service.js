(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('ReporteEvaluativoService', ReporteEvaluativoService);

    ReporteEvaluativoService.$inject = ['$resource'];

    function ReporteEvaluativoService ($resource) {
        var resourceUrl =  'apo/reporteEvaluativo:search,:type,:indice,:anio,:centro,:ep';

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
            'update': { method:'PUT' }
        });
    }
})();

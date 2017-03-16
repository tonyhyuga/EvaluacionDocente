(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('ReporteEvaluativoLibreService', ReporteEvaluativoLibreService);

    ReporteEvaluativoLibreService.$inject = ['$resource'];

    function ReporteEvaluativoLibreService ($resource) {
        var resourceUrl =  'apo/guardarreporteli:id';

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

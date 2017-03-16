(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('MiReporteEvaluativoLibre', MiReporteEvaluativoLibre);

    MiReporteEvaluativoLibre.$inject = ['$resource'];

    function MiReporteEvaluativoLibre ($resource) {
        var resourceUrl =  'apo/verreporteli:id';

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

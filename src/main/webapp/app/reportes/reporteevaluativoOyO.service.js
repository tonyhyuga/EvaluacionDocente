(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('ReporteEvaluativoOyOService', ReporteEvaluativoOyOService);

    ReporteEvaluativoOyOService.$inject = ['$resource'];

    function ReporteEvaluativoOyOService ($resource) {
        var resourceUrl =  'apo/guardarreporteoo:id';

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

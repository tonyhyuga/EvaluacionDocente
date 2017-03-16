(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('MiReporteEvaluativoOO', MiReporteEvaluativoOO);

    MiReporteEvaluativoOO.$inject = ['$resource'];

    function MiReporteEvaluativoOO ($resource) {
        var resourceUrl =  'apo/verreporteoo:id';

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

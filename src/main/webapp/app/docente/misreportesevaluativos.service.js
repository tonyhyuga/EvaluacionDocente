(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('MisReportesEvaluativos', MisReportesEvaluativos);

    MisReportesEvaluativos.$inject = ['$resource'];

    function MisReportesEvaluativos ($resource) {
        var resourceUrl =  'apo/misreportes:search,:type,:indice,:anio,:centro,:ep';

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

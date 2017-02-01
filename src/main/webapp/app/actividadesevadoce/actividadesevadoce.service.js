(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('ActividadesEvaluacion', ActividadesEvaluacion);

    ActividadesEvaluacion.$inject = ['$resource','DateUtils'];

    function ActividadesEvaluacion ($resource,DateUtils) {
        var resourceUrl =  'apo/actividades/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.fin=DateUtils.convertLocalDateFromServer(data.fin);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();

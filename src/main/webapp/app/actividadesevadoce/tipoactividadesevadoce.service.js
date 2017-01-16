(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('TipoActividadesEvaluacion', TipoActividadesEvaluacion);

    TipoActividadesEvaluacion.$inject = ['$resource'];

    function TipoActividadesEvaluacion ($resource) {
        var resourceUrl =  'apo/tiposActividades/:id';

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

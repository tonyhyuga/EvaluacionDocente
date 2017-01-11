(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('BuscadorEmpleado', BuscadorEmpleado);

    BuscadorEmpleado.$inject = ['$resource'];

    function BuscadorEmpleado ($resource) {
        var resourceUrl =  'apo/buscarpersona/:id:criterio';

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

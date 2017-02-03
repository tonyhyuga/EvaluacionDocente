(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('GestorClasesFiltroService', GestorClasesFiltroService);

    GestorClasesFiltroService.$inject = ['$resource'];

    function GestorClasesFiltroService ($resource) {
        var resourceUrl =  'apo/profesores/:criterio';

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

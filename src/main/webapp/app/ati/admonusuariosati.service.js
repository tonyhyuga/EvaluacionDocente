(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('UsuariosATI', UsuariosATI);

    UsuariosATI.$inject = ['$resource'];

    function UsuariosATI ($resource) {
        var resourceUrl =  'apo/admonusuarios/:id/:tipo';

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
            'save': {
                method: 'POST',
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

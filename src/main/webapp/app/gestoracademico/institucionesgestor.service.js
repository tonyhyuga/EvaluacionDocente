(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('InstitucionesGestor', InstitucionesGestor);

    InstitucionesGestor.$inject = ['$resource'];

    function InstitucionesGestor ($resource) {
        var resourceUrl =  'apo/institucionesgestor';

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

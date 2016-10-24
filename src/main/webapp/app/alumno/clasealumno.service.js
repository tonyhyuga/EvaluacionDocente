(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('Alumno', Alumno);

    Alumno.$inject = ['$resource'];

    function Alumno ($resource) {
        var resourceUrl =  'apo/clasesalumno/:id';

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

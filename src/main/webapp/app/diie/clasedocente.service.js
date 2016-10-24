(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('Docente', Docente);

    Docente.$inject = ['$resource'];

    function Docente ($resource) {
        var resourceUrl =  'apo/clasesdocente/:id';

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

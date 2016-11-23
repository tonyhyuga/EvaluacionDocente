(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('Diie', Diie);

    Diie.$inject = ['$resource'];

    function Diie ($resource) {
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

(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('Instituciones', Instituciones);

    Instituciones.$inject = ['$resource'];

    function Instituciones ($resource) {
        var resourceUrl =  'apo/instituciones/:id';

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

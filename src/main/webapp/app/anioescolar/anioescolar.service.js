(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('AniosEscolares', AniosEscolares);

    AniosEscolares.$inject = ['$resource'];

    function AniosEscolares ($resource) {
        var resourceUrl =  'apo/aniosEscolares/:id';

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

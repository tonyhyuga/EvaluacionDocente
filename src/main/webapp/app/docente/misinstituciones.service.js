(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('MisInstituciones', MisInstituciones);

    MisInstituciones.$inject = ['$resource'];

    function MisInstituciones ($resource) {
        var resourceUrl =  'apo/misinstituciones';

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

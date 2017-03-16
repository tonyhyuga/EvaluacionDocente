(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('RvisionReporteLibreService', RvisionReporteLibreService);

    RvisionReporteLibreService.$inject = ['$resource'];

    function RvisionReporteLibreService ($resource) {
        var resourceUrl =  'apo/revisionreporteli:id';

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
            'update': { method:'PUT' },
            'save': { method:'POST' }
        });
    }
})();

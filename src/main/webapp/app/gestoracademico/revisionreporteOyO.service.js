(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('RevisionReporteOyOService', RevisionReporteOyOService);

    RevisionReporteOyOService.$inject = ['$resource'];

    function RevisionReporteOyOService ($resource) {
        var resourceUrl =  'apo/revisionreporteoo:id';

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
            'save': {
                method: 'POST',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            }
        });
    }
})();

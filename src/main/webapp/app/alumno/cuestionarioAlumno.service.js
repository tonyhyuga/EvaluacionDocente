(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('CuestionarioAlumnoService', CuestionarioAlumnoService);

    CuestionarioAlumnoService.$inject = ['$resource'];
    
    function CuestionarioAlumnoService ($resource) {
    	var resourceUrl = 'apo/evaluacion/:ambito/';
    	
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
            'obtener': {
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

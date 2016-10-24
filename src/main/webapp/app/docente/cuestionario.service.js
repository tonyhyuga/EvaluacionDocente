(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('CuestionarioService', CuestionarioService);

    CuestionarioService.$inject = ['$resource'];
    
    function CuestionarioService ($resource) {
    	var resourceUrl = 'apo/autoevaluacion/:ambito/:profesor';
    	
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
                	//alert(data);
                    if (data) {
                        data = angular.fromJson(data);
                    }
                   // alert(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();

(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('GestorAcademico', GestorAcademico);

    GestorAcademico.$inject = ['$resource'];

    function GestorAcademico ($resource) {
    	var resourceUrl =  'apo/profesores:search,:type,:indice,:anio,:ep/:id/:idp';

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

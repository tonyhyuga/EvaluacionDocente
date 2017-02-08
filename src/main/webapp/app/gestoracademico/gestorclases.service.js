(function() {
    'use strict';

    angular
        .module('campoApp')
        .factory('GestorAmbitoService', GestorAmbitoService);

    GestorAmbitoService.$inject = ['$resource'];

    function GestorAmbitoService ($resource) {
    	   var resourceUrl =  'apo/profesores/:id/:idp';

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

(function() {
    'use strict';
    angular
        .module('campoApp')
        .factory('showdownloadpage', showdownloadpage);

    showdownloadpage.$inject = ['$resource'];

    function showdownloadpage ($resource) {
        var resourceUrl =  'apo/alumosquecontestaron/:ambito';

        return $resource(resourceUrl, {}, {
           // 'query': { method: 'GET', isArray: true},
            'get': {  method: 'GET',isArray: true
//                transformResponse: function (data) {
//                    if (data) {
//                        data = angular.fromJson(data);
//                    }
//                    return data;
//                }
            },
            //'update': { method:'PUT' }
        });
        
        
        
//      function showDownloadPage () {//cargar alumnos
//          var resourceUrl =  'api/alumosdownload/';
//
//          return $resource(resourceUrl, {}, {
//              'query': { method: 'GET', isArray: true},
//              'get': {
//                  method: 'GET',
//                  transformResponse: function (data) {
//                      if (data) {
//                          data = angular.fromJson(data);
//                      }
//                      return data;
//                  }
//              },
//              'update': { method:'PUT' }
//          });
//      }
    }
})();
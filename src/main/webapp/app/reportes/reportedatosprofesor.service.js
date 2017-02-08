
(function() {
    'use strict';
    
    angular
    	.module('campoApp')
    	.factory('ReporteProfesorService',ReporteProfesorService);
    

    ReporteProfesorService.$inject = ['$q', '$timeout', '$window','$http','$state','$resource'];
    //var downloadModule = angular.module('components.donwload', []);


        function ReporteProfesorService($q, $timeout, $window,$http,$state,$resource) {
            var down ={
                	donwl:download,
                //	showDownloadPage:showDownloadPage
                };
        	
        	
            return  down;
            
            
            function download(name) {//descargar archivo

                    var defer = $q.defer();

                    $timeout(function() {
                            $window.location = 'apo/reporteclaseprofesor/'+name;
                        }, 1000)
                        .then(function() {                        	
                            defer.resolve('success');
//                            $state.go('gestor', null,{reload:false});
                        }, function() {
                            defer.reject('error');
                        });
                    return defer.promise;
                }
        }
        
//        function showDownloadPage () {//cargar alumnos
//            var resourceUrl =  'api/alumosdownload/';
//
//            return $resource(resourceUrl, {}, {
//                'query': { method: 'GET', isArray: true},
//                'get': {
//                    method: 'GET',
//                    transformResponse: function (data) {
//                        if (data) {
//                            data = angular.fromJson(data);
//                        }
//                        return data;
//                    }
//                },
//                'update': { method:'PUT' }
//            });
//        }
        
        
        
})();
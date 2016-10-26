//(function() {
//    'use strict';
//
//    var downloadModule = angular.module('components.donwload', []).controller('downloadService', ['$q', '$timeout', '$window',
//        function($q, $timeout, $window) {
//            return {
//                download: function(name) {
//
//                    var defer = $q.defer();
//
//                    $timeout(function() {
//                            $window.location = 'download?name=' + name;
//
//                        }, 1000)
//                        .then(function() {
//                            defer.resolve('success');
//                        }, function() {
//                            defer.reject('error');
//                        });
//                    return defer.promise;
//                }
//            };
//        }
//    ]);
//})();



(function() {
    'use strict';
    
    angular
    	.module('campoApp')
    	.factory('downloadService',downloadService);
    

    downloadService.$inject = ['$q', '$timeout', '$window','$http','$state'];
    //var downloadModule = angular.module('components.donwload', []);


        function downloadService($q, $timeout, $window,$http,$state) {
            var down ={
                	donwl:download
                };
        	
        	
            return  down;
            
            
            function download(name) {

                    var defer = $q.defer();

                    $timeout(function() {
                    	//var data =$http.get('apo/download/189', {responseType: 'arraybuffer'});
                            $window.location = 'apo/download/'+name;
                    	
                    	//return data;
                        }, 1000)
                        .then(function() {
//                        	alert(data.toString());
//                        	var file = new Blob([data], {type: 'application/txt'});
//                            var fileURL = URL.createObjectURL(file);
//                            window.open(fileURL);
                            
                            
                        	//saveAs(file,'FILE_8552225.TXT');
                        	
                            defer.resolve('success');
//                            $state.go('gestor', null,{reload:false});
                        }, function() {
                            defer.reject('error');
                        });
                    return defer.promise;
                }
            
        }
   
})();
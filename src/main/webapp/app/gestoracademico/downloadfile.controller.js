(function() {
    'use strict';

    angular
        .module('campoApp')
        .controller('downloadfilecontroller', downloadfilecontroller);

    downloadfilecontroller.$inject = ['$scope', '$state','$stateParams', 'GestorAcademico', 'ParseLinks', 'AlertService', 'downloadService','showdownloadpage'];

    function downloadfilecontroller ($scope, $state, $stateParams,GestorAcademico, ParseLinks, AlertService,downloadService,showdownloadpage) {
        var vm = this;
        var idambito;

        vm.download=download;
        vm.showDownloadPage=showDownloadPage;
        vm.alumosObtenidos=null;
        showDownloadPage();
        
        function download(idAmbito){
            downloadService.donwl(idAmbito)
            .then(function(success) {
                console.log('success : ' + success);
            }, function(error) {
                console.log('error : ' + error);
            });
        }
        
        function showDownloadPage(){
        	idambito=$stateParams.ambito;
        	 vm.alumosObtenidos=showdownloadpage.get ({ambito : $stateParams.ambito});
        }
        
        
        function download(){
            downloadService.donwl(idambito)
            .then(function(success) {
                console.log('success : ' + success);
            }, function(error) {
                console.log('error : ' + error);
            });
        }
    }
})();
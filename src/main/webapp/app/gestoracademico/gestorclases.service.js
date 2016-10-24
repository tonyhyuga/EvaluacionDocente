(function() {
    'use strict';

    angular
        .module('campoApp')
        .factory('GestorAmbitoService', GestorAmbitoService);

    GestorAmbitoService.$inject = ['$uibModal'];

    function GestorAmbitoService ($uibModal) {
        var service = {
            open: open
        };

        var modalInstance = null;
        var resetModal = function () {
            modalInstance = null;
        };

        return service;

        function open (entity) {
        	//alert(modalInstance!==null);
        
            if (modalInstance !== null) return;
            modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/gestoracademico/crearambito.html',
                controller: 'AmbitoController',
                controllerAs: 'vm'
            });
//            modalInstance.result.then(
//                resetModal,
//                resetModal
//            );
        }
    }
})();

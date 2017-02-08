(function() {
    'use strict';

    angular
        .module('campoApp')
        .controller('AmbitoController', AmbitoController);

    AmbitoController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance','entity','GestorAmbitoService' ];

    function AmbitoController ($timeout, $scope, $stateParams, $uibModalInstance,entity,GestorAmbitoService) {
        var vm = this;

        vm.ambito = entity;
        vm.clear = clear;
        vm.save = save;
        vm.cancel = cancel;
        vm.name=null;
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.ambito.id !== null) {
            	GestorAmbitoService.update(vm.ambito, onSaveSuccess, onSaveError);
            } else {
            	GestorAmbitoService.save(vm.ambito, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('campoApp:usuarioUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
        
        function cancel () {
            $uibModalInstance.dismiss('cancel');
        }

    }
})();

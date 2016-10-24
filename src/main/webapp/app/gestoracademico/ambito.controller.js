(function() {
    'use strict';

    angular
        .module('campoApp')
        .controller('AmbitoController', AmbitoController);

    AmbitoController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance','entity','GestorAcademico' ];

    function AmbitoController ($timeout, $scope, $stateParams, $uibModalInstance,entity,GestorAcademico) {
        var vm = this;

        vm.ambito = entity;
      //  vm.isNew = isNew;
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
        	//alert("salvado!!"+vm.ambito.formaDeEvaluar);
            vm.isSaving = true;
            if (vm.ambito.id !== null) {
            	GestorAcademico.update(vm.ambito, onSaveSuccess, onSaveError);
            } else {
            	GestorAcademico.save(vm.ambito, onSaveSuccess, onSaveError);
            }
        	$uibModalInstance.dismiss('cancel');///esto debe cambiar
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
//            vm.credentials = {
//                username: null,
//                password: null,
//                rememberMe: true
//            };
//            vm.authenticationError = false;
            $uibModalInstance.dismiss('cancel');
        }
        
//        function isNew(){
//        	//alert(vm.ambito.id ==null)
//        	if( vm.ambito == null ||vm.ambito.id ==null )
//        		return true;
//        	else
//        		return false;
//        }


    }
})();

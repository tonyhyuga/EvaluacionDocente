(function() {
    'use strict';

    angular
        .module('campoApp')
        .controller('PerfilDeleteController',PerfilDeleteController);

    PerfilDeleteController.$inject = ['$uibModalInstance', 'entity', 'UsuariosATI'];

    function PerfilDeleteController($uibModalInstance, entity, UsuariosATI) {
        var vm = this;

        vm.usuario = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
        	UsuariosATI.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
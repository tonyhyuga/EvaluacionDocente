(function() {
    'use strict';

    angular
        .module('campoApp')
        .controller('NuevoUsuarioController', NuevoUsuarioController);

    NuevoUsuarioController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Instituciones','BuscadorEmpleado','UsuariosATI'];

    function NuevoUsuarioController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Instituciones,BuscadorEmpleado,UsuariosATI) {
        var vm = this;
        vm.entity=entity;
        vm.criterio="";
        vm.clear = clear;
        vm.save = save;
        vm.institucionSelected=entity.institucion;
        vm.buscar=buscar;
        vm.setEmpleado=setEmpleado;
        vm.empleados=null;
        vm.idpersona=null;
        vm.valid=valid;
        cargarDropDown();
        
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
           vm.entity.institucion=vm.institucionSelected;
          //  if (vm.usuario.id !== null) {
           //     Usuario.update(vm.usuario, onSaveSuccess, onSaveError);
           // } else {
            UsuariosATI.save(vm.entity, onSaveSuccess, onSaveError);
         //   }
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
        
        function cargarDropDown(){
        	vm.instituciones=Instituciones.query();
        }
        
        function buscar(){
        	if(vm.criterio!=null && vm.criterio.trim()!=""){
        	vm.empleados=BuscadorEmpleado.query({criterio : vm.criterio});
        	}
        }
        function setEmpleado(idPersonaEmpleado){
        	 vm.idpersona=idPersonaEmpleado;
        	 vm.entity.persona=idPersonaEmpleado;
        }
        function valid() {
        	if(vm.institucionSelected==null || vm.idpersona==null)
        		return false;
            return true;
        }
        

    }
})();

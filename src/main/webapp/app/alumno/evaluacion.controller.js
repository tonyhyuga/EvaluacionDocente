(function() {
    'use strict';
    
    angular
        .module('campoApp')
        .controller('EvaluacionController', EvaluacionController);

    EvaluacionController.$inject = ['$scope', '$state', 'CuestionarioAlumnoService', 'ParseLinks', 'AlertService'];

    function EvaluacionController ($scope, $state, CuestionarioAlumnoService, ParseLinks, AlertService) {
    	var vm = this;
    	vm.show = 0;
    	vm.save=save;
    	vm.resuelto=null;
        CuestionarioAlumnoService.obtener({
    		ambito : $state.params.ambito
        }, onSuccess, onError);        

        function onSuccess(data, headers) {
        	
            vm.resuelto = data;
        }
        
        function onError(error) {
            AlertService.error(error.data.message);
        }
        
        function save() {
        	var grupo=vm.resuelto.grupoPreguntasWrapper;
        	var newRespuestas =[];
        	$state.go('alumno');
        }
        
        function onSaveSuccess (result) {
            $scope.$emit('campoApp:usuarioUpdate', result);
            $state.go('docente');
        }
        
        function onSaveError(error) {
            AlertService.error(error.data.message);
        }
    }
})();

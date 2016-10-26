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
        	var cuestionario=vm.resuelto.cuestionarioResuelto;

        	var newRespuestas =[];
        
        	for(var i=0;i<grupo.length;i++){
        		for(var j=0;j<grupo[i].preguntasWrapperForUI.length;j++){
        			for(var h=0;h<cuestionario.respuestasPregunta.length;h++){
        				if(cuestionario.respuestasPregunta[h].pregunta.id==grupo[i].preguntasWrapperForUI[j].pregunta.id){
        					
        					if(grupo[i].preguntasWrapperForUI[j].pregunta.tipoPregunta.tipoPregunta=='RadioButton'){
        						cuestionario.respuestasPregunta[h].respuestaSeleccionada=grupo[i].preguntasWrapperForUI[j].respuesta.respuestaSeleccionada;
        					
        					}
        					if(grupo[i].preguntasWrapperForUI[j].pregunta.tipoPregunta.tipoPregunta=='TextArea'){
        						cuestionario.respuestasPregunta[h].respuestaSeleccionada=grupo[i].preguntasWrapperForUI[j].respuesta.respuestaSeleccionada;
        					
        					}
        					if(grupo[i].preguntasWrapperForUI[j].pregunta.tipoPregunta.tipoPregunta=='Escala'){
        						cuestionario.respuestasPregunta[h].respuestaSeleccionada=grupo[i].preguntasWrapperForUI[j].respuesta.respuestaSeleccionada;
        				
        					}
        				}
        			}
        				
        		}
        
        	}
            CuestionarioAlumnoService.save(cuestionario, onSaveSuccess, onSaveError);
        	$state.go('alumno');
        }
        
        function onSaveSuccess (result) {
            $scope.$emit('campoApp:usuarioUpdate', result);
            $state.go('alumno');
        }
        
        function onSaveError(error) {
            AlertService.error(error.data.message);
        }
    }
})();

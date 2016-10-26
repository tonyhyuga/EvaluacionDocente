(function() {
    'use strict';
    
    angular
        .module('campoApp')
        .controller('AutoevaluacionController', AutoevaluacionController);

    AutoevaluacionController.$inject = ['$scope', '$state', 'CuestionarioService', 'ParseLinks', 'AlertService'];

    function AutoevaluacionController ($scope, $state, CuestionarioService, ParseLinks, AlertService) {
    	var vm = this;
    	vm.show = 0;
    	vm.save = save;
    	vm.resuelto = null;
        CuestionarioService.obtener({
    		ambito : $state.params.ambito, 
    		profesor : $state.params.profesor
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
        	var k=0;
        	for(var i=0;i<grupo.length;i++){
        		for(var j=0;j<grupo[i].preguntasWrapperForUI.length;j++){
        			for(var h=0;h<cuestionario.respuestasPregunta.length;h++){
        				if(cuestionario.respuestasPregunta[h].pregunta.id==grupo[i].preguntasWrapperForUI[j].pregunta.id){
        					if(grupo[i].preguntasWrapperUI[j].pregunta.tipoPregunta.tipoPregunta=='RadioButton'){
        						cuestionario.respuestasPregunta[h].respuestaSeleccionada =grupo[i].preguntasWrapperForUI[j].respuesta.respuestaSeleccionada;
            				}
        					if(grupo[i].preguntasWrapperForUI[j].pregunta.tipoPregunta.tipoPregunta=='TextArea'){
        						cuestionario.respuestasPregunta[h].respuestaSeleccionada=grupo[i].preguntasWrapperForUI[j].respuesta.respuestaSeleccionada;
            				}
        					if(grupo[i].preguntasWrapperForUI[j].pregunta.tipoPregunta.tipoPregunta=='Escala'){
        						cuestionario.respuestasPregunta[h].respuestaSeleccionada=grupo[i].preguntasWrapperForUI[j].respuesta.respuestaSeleccionada;
            				}
        				}
        				k++;
        			}
        				
        		}
        
        	}
            CuestionarioService.save(cuestionario, onSaveSuccess, onSaveError);
        	$state.go('docente');
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

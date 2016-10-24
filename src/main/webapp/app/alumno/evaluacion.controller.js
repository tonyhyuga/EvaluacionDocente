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
        	 // vm.isSaving = true;
//              if (vm.show == 4 ) {
//            	  CuestionarioService.update(vm.resuelto, onSaveSuccess, onSaveError);
//              } else {
        	var grupo=vm.resuelto.grupoPreguntasWrapper;
        	//alert(cuestionario);
        	//alert(cuestionario.respuestasPregunta.length);
//        	for(var i=0;i<cuestionario.respuestasPregunta.length;i++){
//        	alert(cuestionario.respuestasPregunta[i].opcion.toString());
//        	}
        	var newRespuestas =[];
        	//var newRespuestas =[resuelto.cuestionarioResuelto.respuestasPregunta.length];
        	//var k=0;
//        	for(var i=0;i<grupo.length;i++){
//        		for(var j=0;j<grupo[i].preguntasWrapper.length;j++){
//        			//alert(grupo[i].preguntasWrapper[j].respuestaPregunta.opcion.toString());
//        			for(var h=0;vm.resuelto.cuestionarioResuelto.respuestasPregunta.length;h++){
//        				if(vm.resuelto.cuestionarioResuelto.respuestasPregunta[h].pregunta.id==grupo[i].preguntasWrapper[j].pregunta.id){
//        					if(grupo[i].preguntasWrapper[j].pregunta.tipoPregunta.tipoPregunta=='RadioButton'){
//        						vm.resuelto.cuestionarioResuelto.respuestasPregunta[h].opcion=grupo[i].preguntasWrapper[j].respuestaPregunta.opcion;
//            				}
//        					if(grupo[i].preguntasWrapper[j].pregunta.tipoPregunta.tipoPregunta=='TextArea'){
//        						vm.resuelto.cuestionarioResuelto.respuestasPregunta[h].respuestaSeleccionada=grupo[i].preguntasWrapper[j].respuestaPregunta.respuestaString;
//            				}
//        					if(grupo[i].preguntasWrapper[j].pregunta.tipoPregunta.tipoPregunta=='Escala'){
//        						vm.resuelto.cuestionarioResuelto.respuestasPregunta[h].opcion=grupo[i].preguntasWrapper[j].respuestaPregunta.opcion;
//            				}
//        				}
//        				
//        				
//        				
//        			}
//        				
//        			}
//        
//        		}
            	
            	
//        	vm.resuelto.cuestionarioResuelto.respuestasPregunta=newRespuestas;
//CuestionarioService.update(vm.resuelto, onSaveSuccess, onSaveError);
//            	  CuestionarioService.save(vm.resuelto, onSaveSuccess, onSaveError);
              //}
        	$state.go('alumno');
        }
        
        function onSaveSuccess (result) {
            $scope.$emit('campoApp:usuarioUpdate', result);
          //  $uibModalInstance.close(result);
            $state.go('docente');
        }
        function onSaveError(error) {
            AlertService.error(error.data.message);
        }
    }
})();

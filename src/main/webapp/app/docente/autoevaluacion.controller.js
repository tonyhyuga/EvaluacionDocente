(function() {
    'use strict';
    
    angular
        .module('campoApp')
        .controller('AutoevaluacionController', AutoevaluacionController);

    AutoevaluacionController.$inject = ['$scope', '$state', 'CuestionarioService', 'ParseLinks', 'AlertService'];

    function AutoevaluacionController ($scope, $state, CuestionarioService, ParseLinks, AlertService) {
    	var vm = this;
    	vm.show = 0;
    	vm.save=save;
    	vm.resuelto=null;
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
        	 // vm.isSaving = true;
//              if (vm.show == 4 ) {
//            	  CuestionarioService.update(vm.resuelto, onSaveSuccess, onSaveError);
//              } else {
        	var grupo=vm.resuelto.grupoPreguntasWrapper;
        	var cuestionario=vm.resuelto.cuestionarioResuelto;
        	//alert(cuestionario.respuestasPregunta[0].pregunta.id);
//        	alert(cuestionario.respuestasPregunta[0].pregunta.id);
        	//alert(grupo[0].preguntasWrapper.length);
        	//alert(grupo[0].preguntasWrapperForUI[0].respuesta.opcion);
        	//alert(cuestionario.respuestasPregunta.length);
//        	for(var i=0;i<cuestionario.respuestasPregunta.length;i++){
//        	alert('id: '+cuestionario.respuestasPregunta[i].pregunta.id);
//        	}
        	var newRespuestas =[];
        	//var newRespuestas =[resuelto.cuestionarioResuelto.respuestasPregunta.length];
        	var k=0;
        	for(var i=0;i<grupo.length;i++){
        		for(var j=0;j<grupo[i].preguntasWrapperForUI.length;j++){
        			//alert(grupo[i].preguntasWrapper[j].respuestaPregunta.opcion.toString());
        			for(var h=0;h<cuestionario.respuestasPregunta.length;h++){
        				if(cuestionario.respuestasPregunta[h].pregunta.id==grupo[i].preguntasWrapperForUI[j].pregunta.id){
        					if(grupo[i].preguntasWrapper[j].pregunta.tipoPregunta.tipoPregunta=='RadioButton'){
        						cuestionario.respuestasPregunta[h].opcion =grupo[i].preguntasWrapperForUI[j].respuesta.opcion;
        					//	alert(grupo[i].preguntasWrapperForUI[j].respuesta.opcion+' R ');
            				}
        					if(grupo[i].preguntasWrapperForUI[j].pregunta.tipoPregunta.tipoPregunta=='TextArea'){
        						cuestionario.respuestasPregunta[h].respuestaSeleccionada=grupo[i].preguntasWrapperForUI[j].respuesta.respuestaSeleccionada;
        					//	alert(grupo[i].preguntasWrapperForUI[j].respuesta.respuestaSeleccionada+' T ');
            				}
        					if(grupo[i].preguntasWrapperForUI[j].pregunta.tipoPregunta.tipoPregunta=='Escala'){
        						cuestionario.respuestasPregunta[h].opcion=grupo[i].preguntasWrapperForUI[j].respuesta.opcion;
        					//	alert(grupo[i].preguntasWrapperForUI[j].respuesta.opcion+' E ');
            				}
        				}
        				k++;
        				
        				
        			}
        				
        			}
        
        		}
//        	alert(newRespuestas.toString)
//        	vm.resuelto.cuestionarioResuelto.respuestasPregunta=newRespuestas;
            	
//        	vm.resuelto.cuestionarioResuelto.respuestasPregunta=newRespuestas;
//CuestionarioService.update(vm.resuelto, onSaveSuccess, onSaveError);
            	  CuestionarioService.save(cuestionario, onSaveSuccess, onSaveError);
              //}
        	$state.go('docente');
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

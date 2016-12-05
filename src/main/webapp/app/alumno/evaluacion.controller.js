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
    	vm.anterior=anterior;
    	vm.siguiente=siguiente;
    	vm.validarseccion=validarseccion;
    	vm.valido=false;
    	vm.mensaje=null;
    	vm.preguntainvalida=[];
    	vm.contain=contain;
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
        	var grupo=vm.resuelto.grupoPreguntasForUI;
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
        	
        }
        
        function onSaveSuccess (result) {
            $scope.$emit('campoApp:usuarioUpdate', result);
            $state.go('alumno',{}, {reload: true});
        }
        
        function onSaveError(error) {
            AlertService.error(error.data.message);
            $state.go('alumno',{}, {reload: true});
        }
        
        function anterior(indice){
        	vm.validarseccion(indice);
        	if(vm.valido)
        	vm.show=indice;
        	
        }
        function siguiente(indice){
        	vm.validarseccion(indice);
        	if(vm.valido)
        	vm.show=indice+2;
        	
        }
        
        function validarseccion(seccion){
        	var grupo=vm.resuelto.grupoPreguntasForUI;
        	var cuestionario=vm.resuelto.cuestionarioResuelto;
        	//var newRespuestas =[];
        	var valido=true;
        	var mensaje=null;
        	var k=0;
        	vm.preguntainvalida=[];
        	//for(var i=0;i<grupo.length;i++){
        	//alert(seccion);
        	var index=0;
        		for(var j=0;j<grupo[seccion].preguntasWrapperForUI.length;j++){
        			for(var h=0;h<cuestionario.respuestasPregunta.length;h++){
        				if(grupo[seccion].preguntasWrapperForUI[j].pregunta.obligatoria && cuestionario.respuestasPregunta[h].pregunta.id==grupo[seccion].preguntasWrapperForUI[j].pregunta.id){

        					if(grupo[seccion].preguntasWrapperForUI[j].respuesta.respuestaSeleccionada==null){
        						valido=false;
        						vm.preguntainvalida[index]=grupo[seccion].preguntasWrapperForUI[j].pregunta.id;
        						
        						//break;
        						index++;
        					}
        					
        				}
        				
        			}
        				
        		}
        		if(!valido){
        			mensaje="No ha contestado todas las preguntas de esta sección";
					//break;
				}
        	//}
        		vm.valido=valido;
        		vm.mensaje=mensaje;
        }
        
        function contain(idpregunta){
        	var invalido=false;
        	//alert(vm.preguntainvalida.length);
        	for(var index=0;index<vm.preguntainvalida.length;index++){
        		if(vm.preguntainvalida[index]==idpregunta){
        			invalido=true;
        			break;
        		}
        			
        	}
        	return invalido;
        }
    }
})();

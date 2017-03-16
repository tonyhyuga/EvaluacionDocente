(function() {
    'use strict';

    angular
        .module('campoApp')
        .controller('ReporteEvaluativoLIController', ReporteEvaluativoLIController);

    ReporteEvaluativoLIController.$inject = ['$scope', '$state', 'ReporteEvaluativoLibreService', 'ParseLinks', 'AlertService', 'pagingParams', 'paginationConstants', 'LoginService','entity'
    	];

    function ReporteEvaluativoLIController ($scope, $state, ReporteEvaluativoLibreService, ParseLinks, AlertService, pagingParams, paginationConstants,LoginService,entity
    		) {
        var vm = this;
        vm.reporte=entity.reporte;
        vm.wrapper=entity;
        vm.tipo=entity.tipo;
        vm.round=round;
        vm.save=save;
        function round(valor){
        	return Math.round(valor);
        }
        vm.print=print;
        
        function print(){
        	window.print();
        }
        
        function save(){
       	 vm.isSaving = true;
       	 alert(vm.wrapper.comentario1);
            if (vm.wrapper.reporte.id !== null) {
            	ReporteEvaluativoLibreService.update(vm.wrapper, onSaveSuccess, onSaveError).
              	 $promise.then(function(result){
            		 vm.wrapper.reporte=result;
            	 });
            } else {
            	ReporteEvaluativoLibreService.save(vm.wrapper, onSaveSuccess, onSaveError).
           	 $promise.then(function(result){
        		 vm.wrapper.reporte=result;
        	 });
            }
       }
       function onSaveSuccess (result) {
           $scope.$emit('campoApp:reporte', result);
          // $uibModalInstance.close(result);
           vm.isSaving = false;
           if(vm.wrapper.tipoUser==1)
           $state.go('generarReporteLI', null, { reload: true });
           if(vm.wrapper.tipoUser==2)
        	   $state.go('revisionReporteLI', null, { reload: true });
       }

       function onSaveError () {
           vm.isSaving = false;
       }
        
        
        Highcharts.setOptions({
            chart: {
                backgroundColor: {
                    linearGradient: [0, 0, 500, 500],
                    stops: [
                        [0, 'rgb(255, 255, 255)'],
                        [1, 'rgb(255, 255, 255)']
                        ]
                },
                borderWidth: 2,
                plotBackgroundColor: 'rgba(255, 255, 255, .9)',
                plotShadow: true,
                plotBorderWidth: 1
            }
        });
        
        var chart1 = new Highcharts.Chart({
            chart: {
                renderTo: 'iteraccion',
    			type: 'bar'
            },
            title: {
                text: 'Relación Profesor - Estudiante'
            },
            xAxis: {
            	categories: ['Muestra respeto al dirigirse hacia los estudiantes', 
            		'Muestra con el ejemplo congruencia entre lo que dice y hace', 
            		'Promueve el intercambio de conocimientos, experiencias y opiniones entre los estudiantes',
            		'Optimiza el tiempo de las sesiones presenciales para el logro de los aprendizajes.'],
                type: 'integer'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            series: [{
                name: 'Estudiantes',
                data: [Math.round(vm.reporte.muestraRespetoEstudiantes),
                	Math.round(vm.reporte.congruenciaEstudiantes),
                	Math.round(vm.reporte.intercambioConocimientoE),
                	Math.round(vm.reporte.optimizaTiempoEstudiante)],
               	color: '#002e5f'
            }, {
                name: 'Autoevaluación',
                data: [
                	Math.round(vm.reporte.muestraRespetoProfesor),
                	Math.round(vm.reporte.congruenciaProfesor),
                	Math.round(vm.reporte.intercambioConocimientoP),
                	Math.round(vm.reporte.optimizaTiempoProfesor)],
                color: '#ffb60f'
            }]
        });

        var chart2 = new Highcharts.Chart({
            chart: {
                renderTo: 'dimensionii',
    			type: 'bar'
            },
            title: {
                text: 'Relación Profesor - Estudiante'
            },
            xAxis: {
            	categories: ['Utilizo actividades que promuevan el interés del estudiante hacia el aprendizaje de la asignatura.',
            		'Establezco el puntaje de cada una de las actividades de evaluación utilizadas durante la asignatura.', 
            		'Informo la puntuación obtenida en cada una de las estrategias de evaluación.',
            		'Procuro que las actividades de evaluación se relacione con lo visto en la asignatura.', 
            		'Retroalimento el progreso academico para que el estudiante identifique sus fortalezas y debilidades.'],
                type: 'integer'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            series: [{
                name: 'Estudiantes',
                data: [Math.round(vm.reporte.interesAprendizajeEstudiante),
                	Math.round(vm.reporte.establecePuntajeEstudiante),
                	Math.round(vm.reporte.informaPuntuacionEstudiante),
                	Math.round(vm.reporte.actividadesRelacionadoVistoE),
                	Math.round(vm.reporte.retroalimentoEstudiante)],
               	color: '#002e5f'
            }, {
                name: 'Autoevaluación',
                data: [Math.round(vm.reporte.interesAprendizajeProfesor),
                	Math.round(vm.reporte.establecePuntajeProfesor), 
                	Math.round(vm.reporte.informaPuntuacionProfesor),
                	Math.round(vm.reporte.actividadesRelacionadoVistoP), 
                	Math.round(vm.reporte.retroalimentoProfesor)],
                color: '#ffb60f'
            }]
        });
        
       
      

        


    }
})();

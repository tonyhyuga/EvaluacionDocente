(function() {
    'use strict';

    angular
        .module('campoApp')
        .controller('ReporteEvaluativoOOController', ReporteEvaluativoOOController);

    ReporteEvaluativoOOController.$inject = ['$scope', '$state', 'ReporteEvaluativoOOService', 'ParseLinks', 'AlertService', 'pagingParams', 'paginationConstants', 'LoginService','entity','ReporteEvaluativoOyOService'
    	];

    function ReporteEvaluativoOOController ($scope, $state, ReporteEvaluativoOOService, ParseLinks, AlertService, pagingParams, paginationConstants,LoginService,entity,ReporteEvaluativoOyOService
    		) {
        var vm = this;
        
       // vm.loadPage = loadPage;
        vm.reporte=entity.reporte;
        vm.wrapper=entity;
        vm.tipo=entity.tipo;
        //loadAll();
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
             if (vm.wrapper.reporte.id !== null) {
            	 ReporteEvaluativoOyOService.update(vm.wrapper, onSaveSuccess, onSaveError).
               	 $promise.then(function(result){
            		 vm.wrapper.reporte=result;
            	 });
             } else {
            	 //alert("save");
            	 ReporteEvaluativoOyOService.save(vm.wrapper, onSaveSuccess, onSaveError).
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
            $state.go('generarReporteOO', null, { reload: true });
            if(vm.wrapper.tipoUser==2)
            $state.go('revisionReporteOO', null, { reload: true });
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
            		'Guía a los estudiantes a identificar sus necesidades o recursos para aprender de manera independiente', 
            		'Muestra con el ejemplo congruencia entre lo que dice y hace', 
            		'Promueve la ética de la profesión en la asignatura que imparte', 
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
                	Math.round(vm.reporte.guiaAprenderIndependienteE), 
                	Math.round(vm.reporte.congruenciaEstudiantes),
                	Math.round(vm.reporte.eticaEstudiantes),
                	Math.round(vm.reporte.intercambioConocimientoE),
                	Math.round(vm.reporte.optimizaTiempoEstudiante)],
               	color: '#002e5f'
            }, {
                name: 'Autoevaluación',
                data: [
                	Math.round(vm.reporte.muestraRespetoProfesor), 
                	Math.round(vm.reporte.guiaAprenderIndependienteP), 
                	Math.round(vm.reporte.congruenciaProfesor),
                	Math.round(vm.reporte.eticaProfesor),
                	Math.round(vm.reporte.intercambioConocimientoP),
                	Math.round(vm.reporte.optimizaTiempoProfesor)],
                color: '#ffb60f'
            }]
        });

        var chart2 = new Highcharts.Chart({
            chart: {
                renderTo: 'tic',
    			type: 'bar'
            },
            title: {
                text: 'Relación Profesor - Estudiante'
            },
            xAxis: {
            	categories: ['Promuevo el uso de la plataforma UADY Virtual como herramienta de apoyo al proceso de enseñanza y aprendizaje'],
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
                data: [Math.round(vm.reporte.usoPlataformasEstudiantes)],
               	color: '#002e5f'
            }, {
                name: 'Autoevaluación',
                data: [Math.round(vm.reporte.usoPlataformasProfesor)],
                color: '#ffb60f'
            }]
        });
        var chart3 = new Highcharts.Chart({
            chart: {
                renderTo: 'estrategias',
    			type: 'bar'
            },
            title: {
                text: 'Estrategias'
            },
            xAxis: {
            	categories: ['Sugiero actividades complementarias de aprendizaje para reforzar el desarrollo de las competencias.', 
            		'Utilizo actividades de aprendizaje dentro y/o fuera del aula, que contribuyan al desarrollo de las competencias de la asignatura.', 
            		'Utilizo actividades de aprendizaje que promuevan el análisis de los temas tratados.', 
            		'Utilizo actividades donde el estudiante integre los conocimientos previos y los nuevos.', 
            		'Utilizo actividades que promuevan el interés del estudiante hacia el aprendizaje de la asignatura.'],
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
                data: [Math.round(vm.reporte.actividadesComplementariasE),
                		Math.round(vm.reporte.desarrolloCompetenciasE), 
                		Math.round(vm.reporte.promueveAnalisisEstudiantes),
                		Math.round(vm.reporte.integraConocimientoEstudiante),
                		Math.round(vm.reporte.interesAprendizajeEstudiante)],
               	color: '#002e5f'
            }, {
                name: 'Autoevaluación',
                data: [Math.round(vm.reporte.actividadesComplementariasP), 
                	Math.round(vm.reporte.desarrolloCompetenciasP), 
                	Math.round(vm.reporte.promueveAnalisisProfesor),
                	Math.round(vm.reporte.integraConocimientoProfesor),
                	Math.round(vm.reporte.interesAprendizajeProfesor)],
                color: '#ffb60f'
            }]
        });
        var chart4 = new Highcharts.Chart({
            chart: {
                renderTo: 'tecnicas',
    			type: 'bar'
            },
            title: {
                text: 'Evaluación - técnicas'
            },
            xAxis: {
            	categories: ['Establezco el puntaje de cada una de las actividades de evaluación utilizadas durante la asignatura.', 
            		'Informo la puntuación obtenida en cada una de las estrategias de evaluación.'],
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
                data: [Math.round(vm.reporte.establecePuntajeEstudiante),
                	Math.round(vm.reporte.informaPuntuacionEstudiante)],
               	color: '#002e5f'
            }, {
                name: 'Autoevaluación',
                data: [Math.round(vm.reporte.establecePuntajeProfesor), 
                	Math.round(vm.reporte.informaPuntuacionProfesor)],
                color: '#ffb60f'
            }]
        });
        var chart5 = new Highcharts.Chart({
            chart: {
                renderTo: 'evaluacion',
    			type: 'bar'
            },
            title: {
                text: 'Evaluación'
            },
            xAxis: {
            	categories: ['Procuro que las actividades de evaluación se relacione con lo visto en la asignatura.', 
            		'Retroalimento el progreso academico para que el estudiante identifique sus fortalezas y debilidades.', 
            		'Utilizo diferentes formas de evaluación.'],
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
                data: [Math.round(vm.reporte.actividadesRelacionadoVistoE),
                	Math.round(vm.reporte.retroalimentoEstudiante), 
                	Math.round(vm.reporte.diferentesFormasEvaluacionE)],
               	color: '#002e5f'
            }, {
                name: 'Autoevaluación',
                data: [Math.round(vm.reporte.actividadesRelacionadoVistoP), 
                	Math.round(vm.reporte.retroalimentoProfesor), 
                	Math.round(vm.reporte.diferentesFormasEvaluacionP)],
                color: '#ffb60f'
            }]
        });
        var chart6 = new Highcharts.Chart({
            chart: {
                renderTo: 'valores',
    			type: 'bar'
            },
            title: {
                text: 'Valores Universitarios'
            },
            xAxis: [{
                categories: ['No ético', 
            		'Deshonesto', 
            		'Irresponsable social', 
            		'Intolerante', 
            		'Ilegal',
            		'Irrespetuoso',
            		'No humilde',
            		'Inequitativo',
            		'Sin rigor académico'],
                reversed: false,
                labels: {
                    step: 1
                }
            }, { // mirror axis on right side
                opposite: true,
                reversed: false,
                categories: ['Ético', 
            		'Honesto', 
            		'Responsable social', 
            		'Tolerante', 
            		'Legal',
            		'Respetuoso',
            		'Humilde',
            		'Equitativo',
            		'Rigor académico'],
                linkedTo: 0,
                labels: {
                    step: 1
                }
            }],
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            series: [{
                name: 'Estudiantes',
                data: [
                	Math.round(vm.reporte.eticoEstudiantes),
                	Math.round(vm.reporte.honestoEstudiantes), 
                	Math.round(vm.reporte.responsableSocialEstudiantes),
                	Math.round(vm.reporte.toleranteEstudiantes),
                	Math.round(vm.reporte.legalEstudiantes),
                	Math.round(vm.reporte.respetuosoEstudiantes),
                	Math.round(vm.reporte.humildeEstudiantes),
                	Math.round(vm.reporte.equitativoEstudiantes),
                	Math.round(vm.reporte.rigoerAcademicoEstudiantes)],
               	color: '#002e5f'
            }, {
                name: 'Autoevaluación',
                data: [Math.round(vm.reporte.eticoProfesor), 
                	Math.round(vm.reporte.honestoProfesor), 
                	Math.round(vm.reporte.responsableSocialProfesor),
                	Math.round(vm.reporte.toleranteProfesor),
                	Math.round(vm.reporte.legalProfesor),
                	Math.round(vm.reporte.respetuosoProfesor),
                	Math.round(vm.reporte.humildeProfesor),
                	Math.round(vm.reporte.equitativoProfesor),
                	Math.round(vm.reporte.rigorAcademicoProfesor)],
                color: '#ffb60f'
            }]
        });
        
        
     
            
         
        
 

    }
})();

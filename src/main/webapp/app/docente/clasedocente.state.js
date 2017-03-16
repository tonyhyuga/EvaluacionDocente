(function() {
    'use strict';

    angular
        .module('campoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('docente', {
            parent: 'app',
            url: '/docente?page&sort&search',
            data: {
            	authorities: ['PROFESOR'],
                pageTitle: 'Mis Asignaturas - Evaluación Docente'
            },
            views: {
                'content@': {
                    templateUrl: 'app/docente/clasesdocente.html',
                    controller: 'DocenteController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null,
                anio: '',
                indice: '1'
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search,
                        anio: $stateParams.anio,
                        indice: $stateParams.indice,
                    };
                }]
            }
        })
        .state('autoevaluacion', {
            parent: 'docente',
            url: '/autoevaluacion/{ambito}/{profesor}',
            data: {
            	authorities: ['PROFESOR'],
                pageTitle: 'Autoevaluación - Evaluación Docente'
            },
            views: {
                'content@': {
                    templateUrl: 'app/docente/autoevaluacion.html',
                    controller: 'AutoevaluacionController',
                    controllerAs: 'vm'
                }
            },
            params: {
            	ambito: '$stateParams.ambito',
            	profesor: '$stateParams.profesor'
            }
        })
        .state('reporteprofesor', {
            parent: 'app',
            url: '/misevaluaciones?page&sort&search&type',
            data: {
            	authorities: ['PROFESOR'],
                pageTitle: 'Mis Reportes Evaluativos'
            },
            views: {
                'content@': {
                    templateUrl: 'app/docente/misreportesevaluativos.html',
                    controller: 'MisReportesEvaluativosController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null,
                type: '1',
                indice : '1',
                anio: '0',
                aniostr: '',
                centro: '0',
                ep: false
                
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search,
                        type: $stateParams.type,
                        indice: $stateParams.indice,
                        anio: $stateParams.anio,
                        aniostr: $stateParams.aniostr,
                        centro: $stateParams.centro,
                        ep: $stateParams.ep
                    };
                }]
            }
        })
         .state('verReporteOO', {
            parent: 'reporteprofesor',
            url: '/reporteoo/{id}',
            data: {
            	authorities: ['PROFESOR'],
                pageTitle: 'Reporte Evaluativo'
            },
            views: {
                'content@': {
                    templateUrl: 'app/reportes/reporteevaluativoOO.html',
                    controller: 'ReporteEvaluativoOOController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'MiReporteEvaluativoOO','$state', function($stateParams, MiReporteEvaluativoOO,$state) {
              	  return MiReporteEvaluativoOO.get({id: $stateParams.id}).$promise;
                }]
            },
        })
          .state('verReporteLI', {
            parent: 'reporteprofesor',
            url: '/reporteli/{id}',
            data: {
            	authorities: ['PROFESOR'],
                pageTitle: 'Reporte Evaluativo'
            },
            views: {
                'content@': {
                    templateUrl: 'app/reportes/reporteevaluativoLI.html',
                    controller: 'ReporteEvaluativoLIController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'MiReporteEvaluativoLibre','$state', function($stateParams, MiReporteEvaluativoLibre,$state) {
              	  return MiReporteEvaluativoLibre.get({id: $stateParams.id}).$promise;
                }]
            },

        })
    }

})();

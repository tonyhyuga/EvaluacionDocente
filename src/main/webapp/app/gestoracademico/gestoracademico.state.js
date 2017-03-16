(function() {
    'use strict';

    angular
        .module('campoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('gestor', {
            parent: 'app',
            url: '/gestoracademico?page&sort&search&type',
            data: {
            	authorities: ['GESTOR_ACADEMICO'],
                pageTitle: 'Portal Gestor Academico'
            },
            views: {
                'content@': {
                    templateUrl: 'app/gestoracademico/docentesgestor.html',
                    controller: 'GestorController',
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
                        ep: $stateParams.ep
                    };
                }]
            }
        })
        .state('gestor.ambito', {
            parent: 'gestor',
            url: '/gestoracademico/{id}/{idp}',
            data: {
            	authorities: ['GESTOR_ACADEMICO']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/gestoracademico/crearambito.html',
                    controller: 'AmbitoController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['GestorAmbitoService', function(GestorAmbitoService) {
                        	return GestorAmbitoService.obtener({id : $stateParams.id , idp : $stateParams.idp}).$promise;
                
                        }]
                    }
                }).result.then(function() {
                    $state.go('gestor', null, { reload: 'gestor' });
                }, function() {
                    $state.go('gestor');
                });
            }]
        })
        .state('downloadPage', {
            parent: 'app',
            url: '/downloadPage/{ambito}',
            data: {
            	authorities: ['GESTOR_ACADEMICO'],
                pageTitle: 'Descargar reporte de alumnos evaluados'
            },
            views: {
                'content@': {
                    templateUrl: 'app/gestoracademico/downloadpage.html',
                    controller: 'downloadfilecontroller',
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
                idambito: '$stateParams.ambito'
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }]
            }
        })
          .state('revision', {
            parent: 'app',
            url: '/revsionreporte?page&sort&search&type',
            data: {
            	authorities: ['GESTOR_ACADEMICO'],
                pageTitle: 'Revision reporte Evaluativo'
            },
            views: {
                'content@': {
                    templateUrl: 'app/gestoracademico/misrevisionesevaluativos.html',
                    controller: 'RevisionEvaluativosController',
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
           .state('revisionReporteOO', {
            parent: 'revision',
            url: '/revisionreporteoo/{id}',
            data: {
            	authorities: ['GESTOR_ACADEMICO'],
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
                entity: ['$stateParams', 'RevisionReporteOyOService','$state', function($stateParams, RevisionReporteOyOService,$state) {
              	  return RevisionReporteOyOService.get({id: $stateParams.id}).$promise;
                }]
            },
        })
          .state('revisionReporteLI', {
            parent: 'revision',
            url: '/revisionreporteli/{id}',
            data: {
            	authorities: ['GESTOR_ACADEMICO'],
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
                entity: ['$stateParams', 'RevisionReporteLibreService','$state', function($stateParams, RevisionReporteLibreService,$state) {
              	  return RevisionReporteLibreService.get({id: $stateParams.id}).$promise;
                }]
            },

        })
     
    }

})();

(function() {
    'use strict';

    angular
        .module('campoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('diie', {
            parent: 'app',
            url: '/diie?page&sort&search',
            data: {
            	authorities: ['ADMTVO_EVALUACION_DOCENTE'],
                pageTitle: 'Portal Docente'
            },
            views: {
                'content@': {
                    templateUrl: 'app/diie/diieportal.html',
                    controller: 'DIIEController',
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
        .state('diiereporteevaluativo', {
            parent: 'app',
            url: '/reporteEvaluativo?page&sort&search',
            data: {
            	authorities: ['ADMTVO_EVALUACION_DOCENTE'],
                pageTitle: 'Reporte Evaluativo'
            },
            views: {
                'content@': {
                    templateUrl: 'app/reportes/reporteevaluativo.html',
                    controller: 'ReporteEvaluativoController',
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
         .state('generarReporteOO', {
            parent: 'diiereporteevaluativo',
            url: '/reporte/{idA},{idP},{idAnio},{idIndice},{tipoEvaluacion},{idDependencia}',
            data: {
            	authorities: ['ADMTVO_EVALUACION_DOCENTE'],
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
          entity: ['$stateParams', 'ReporteEvaluativoOOService','$state', function($stateParams, ReporteEvaluativoOOService,$state) {
        	  return ReporteEvaluativoOOService.get({idA: $stateParams.idA,
                  idP: $stateParams.idP,
                  idAnio: $stateParams.idAnio,
                  idIndice: $stateParams.idIndice,
                  tipoEvaluacion: $stateParams.tipoEvaluacion,
                  idDependencia: $stateParams.idDependencia}).$promise;
          }]
      },
//            params: {
//            	idA: '$stateParams.idA',
//            	idP: '$stateParams.idP',
//            	idAnio: '$stateParams.idAnio' ,
//            	idIndice: '$stateParams.idIndice' ,
//            	tipoEvaluacion: '$stateParams.tipoEvaluacion',
//            	idDependencia: '$stateParams.idDependencia' 
//            }
        })
        .state('generarReporteLI', {
            parent: 'diiereporteevaluativo',
            url: '/reporteli/{idA},{idP},{idAnio},{idIndice},{tipoEvaluacion},{idDependencia}',
            data: {
            	authorities: ['ADMTVO_EVALUACION_DOCENTE'],
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
                entity: ['$stateParams', 'ReporteEvaluativoLIService','$state', function($stateParams, ReporteEvaluativoLIService,$state) {
              	  return ReporteEvaluativoLIService.get({idA: $stateParams.idA,
                        idP: $stateParams.idP,
                        idAnio: $stateParams.idAnio,
                        idIndice: $stateParams.idIndice,
                        tipoEvaluacion: $stateParams.tipoEvaluacion,
                        idDependencia: $stateParams.idDependencia}).$promise;
                }]
            },
//            params: {
//            	idA: '$stateParams.idA',
//            	idP: '$stateParams.idP',
//            	idAnio: '$stateParams.idAnio' ,
//            	idIndice: '$stateParams.idIndice' ,
//            	tipoEvaluacion: '$stateParams.tipoEvaluacion' ,
//            	idDependencia: '$stateParams.idDependencia' 
//            }
        })
//        .state('usuario-detail', {
//            parent: 'entity',
//            url: '/usuario/{id}',
//            data: {
//                pageTitle: 'Usuario'
//            },
//            views: {
//                'content@': {
//                    templateUrl: 'app/entities/usuario/usuario-detail.html',
//                    controller: 'UsuarioDetailController',
//                    controllerAs: 'vm'
//                }
//            },
//            resolve: {
//                entity: ['$stateParams', 'Usuario', function($stateParams, Usuario) {
//                    return Usuario.get({id : $stateParams.id}).$promise;
//                }],
//                previousState: ["$state", function ($state) {
//                    var currentStateData = {
//                        name: $state.current.name || 'usuario',
//                        params: $state.params,
//                        url: $state.href($state.current.name, $state.params)
//                    };
//                    return currentStateData;
//                }]
//            }
//        })
//        .state('usuario-detail.edit', {
//            parent: 'usuario-detail',
//            url: '/detail/edit',
//            data: {
//            },
//            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
//                $uibModal.open({
//                    templateUrl: 'app/entities/usuario/usuario-dialog.html',
//                    controller: 'UsuarioDialogController',
//                    controllerAs: 'vm',
//                    backdrop: 'static',
//                    size: 'lg',
//                    resolve: {
//                        entity: ['Usuario', function(Usuario) {
//                            return Usuario.get({id : $stateParams.id}).$promise;
//                        }]
//                    }
//                }).result.then(function() {
//                    $state.go('^', {}, { reload: false });
//                }, function() {
//                    $state.go('^');
//                });
//            }]
//        })
//        .state('usuario.new', {
//            parent: 'usuario',
//            url: '/new',
//            data: {
//            },
//            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
//                $uibModal.open({
//                    templateUrl: 'app/entities/usuario/usuario-dialog.html',
//                    controller: 'UsuarioDialogController',
//                    controllerAs: 'vm',
//                    backdrop: 'static',
//                    size: 'lg',
//                    resolve: {
//                        entity: function () {
//                            return {
//                                login: null,
//                                password: null,
//                                activo: null,
//                                id: null
//                            };
//                        }
//                    }
//                }).result.then(function() {
//                    $state.go('usuario', null, { reload: 'usuario' });
//                }, function() {
//                    $state.go('usuario');
//                });
//            }]
//        })
//        .state('usuario.edit', {
//            parent: 'usuario',
//            url: '/{id}/edit',
//            data: {
//            },
//            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
//                $uibModal.open({
//                    templateUrl: 'app/entities/usuario/usuario-dialog.html',
//                    controller: 'UsuarioDialogController',
//                    controllerAs: 'vm',
//                    backdrop: 'static',
//                    size: 'lg',
//                    resolve: {
//                        entity: ['Usuario', function(Usuario) {
//                            return Usuario.get({id : $stateParams.id}).$promise;
//                        }]
//                    }
//                }).result.then(function() {
//                    $state.go('usuario', null, { reload: 'usuario' });
//                }, function() {
//                    $state.go('^');
//                });
//            }]
//        })
//        .state('usuario.delete', {
//            parent: 'usuario',
//            url: '/{id}/delete',
//            data: {
//            },
//            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
//                $uibModal.open({
//                    templateUrl: 'app/entities/usuario/usuario-delete-dialog.html',
//                    controller: 'UsuarioDeleteController',
//                    controllerAs: 'vm',
//                    size: 'md',
//                    resolve: {
//                        entity: ['Usuario', function(Usuario) {
//                            return Usuario.get({id : $stateParams.id}).$promise;
//                        }]
//                    }
//                }).result.then(function() {
//                    $state.go('usuario', null, { reload: 'usuario' });
//                }, function() {
//                    $state.go('^');
//                });
//            }]
//        });
    }

})();

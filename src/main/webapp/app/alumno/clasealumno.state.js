(function() {
    'use strict';

    angular
        .module('campoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('alumno', {
            parent: 'app',
            url: '/alumno?page&sort&search',
            data: {
            	authorities: ['ALUMNO'],
                pageTitle: 'Mis Asignaturas - Evaluación Docente'
            },
            views: {
                'content@': {
                    templateUrl: 'app/alumno/clasesalumno.html',
                    controller: 'AlumnoController',
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
                search: null
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
        .state('evaluacion', {
            parent: 'alumno',
            url: '/evaluacion/{ambito}',
            data: {
            	authorities: ['ALUMNO'],
                pageTitle: 'Evaluación - Evaluación Docente'
            },
            views: {
                'content@': {
                    templateUrl: 'app/alumno/evaluacion.html',
                    controller: 'EvaluacionController',
                    controllerAs: 'vm'
                }
            },
            params: {
            	ambito: '$stateParams.ambito'
            }
        })
       .state('alumno-oo2', {
            parent: 'alumno',
            url: '/alumno-oo2?page&sort&search',
            data: {
            	authorities: ['ALUMNO'],
                pageTitle: 'Portal Alumno'
            },
            views: {
                'content@': {
                    templateUrl: 'app/alumno/estudiante-oo-2.html',
                    controller: 'AlumnoController',
                    controllerAs: 'vm'
                }
            }
       })
       .state('alumno-oo3', {
            parent: 'alumno',
            url: '/alumno-oo3?page&sort&search',
            data: {
            	authorities: ['ALUMNO'],
                pageTitle: 'Portal Alumno'
            },
            views: {
                'content@': {
                    templateUrl: 'app/alumno/estudiante-oo-3.html',
                    controller: 'AlumnoController',
                    controllerAs: 'vm'
                }
            }
       })
       .state('alumno-oo4', {
            parent: 'alumno',
            url: '/alumno-oo4?page&sort&search',
            data: {
            	authorities: ['ALUMNO'],
                pageTitle: 'Portal Alumno'
            },
            views: {
                'content@': {
                    templateUrl: 'app/alumno/estudiante-oo-4.html',
                    controller: 'AlumnoController',
                    controllerAs: 'vm'
                }
            }
       })
       .state('alumno-oo5', {
            parent: 'alumno',
            url: '/alumno-oo5?page&sort&search',
            data: {
            	authorities: ['ALUMNO'],
                pageTitle: 'Portal Alumno'
            },
            views: {
                'content@': {
                    templateUrl: 'app/alumno/estudiante-oo-5.html',
                    controller: 'AlumnoController',
                    controllerAs: 'vm'
                }
            }
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

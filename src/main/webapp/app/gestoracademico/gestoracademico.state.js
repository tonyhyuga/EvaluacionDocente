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
            url: '/gestoracademico?page&sort&search',
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
//        .state('gestor.ambito', {
//            parent: 'app',
//            url: '/ambito',
//            data: {
//                pageTitle: 'Portal Alumno'
//            },
//            views: {
//                'content@': {
//                    templateUrl: 'app/gestoracademico/crearambito.html',
//                    controller: 'AmbitoController',
//                    controllerAs: 'vm',
//                    backdrop: 'static',
//                }
//            }
//       })
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
                        entity: ['GestorAcademico', function(GestorAcademico) {
                        	return GestorAcademico.obtener({id : $stateParams.id , idp : $stateParams.idp}).$promise;
                
                        }]
                    }
                }).result.then(function() {
                    $state.go('gestor', null, { reload: 'gestor' });
                }, function() {
                    $state.go('gestor');
                });
            }]
        })
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

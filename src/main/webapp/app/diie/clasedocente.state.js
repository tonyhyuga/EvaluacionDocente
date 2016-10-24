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
            url: '/?page&sort&search',
            data: {
            	authorities: [],
                pageTitle: 'Portal Docente'
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

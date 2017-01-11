(function() {
    'use strict';

    angular
        .module('campoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('admeval', {
            parent: 'app',
            url: '/admonusuariosadmin?page&sort&search',
            data: {
            	authorities: ['ATI_EVALUACION_DOCENTE'],
                pageTitle: 'Administrar Usuarios ADMIN'
            },
            views: {
                'content@': {
                    templateUrl: 'app/ati/admonusuariosadmin.html',
                    controller: 'UsuariosAdminEvaluacionController',
                    controllerAs: 'vm'
                }
            },
            params: {
                tipo: 70
                	,
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
        .state('admonevalua', {
            parent: 'admeval',
            url: '/new',
            data: {
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/ati/nuevousuario.html',
                    controller: 'NuevoUsuarioController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                institucion: null,
                                persona: null,
                                rol: 70,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('admeval', null, { reload: 'admeval' });
                }, function() {
                    $state.go('admeval');
                });
            }]
        })
        .state('admonevalua.delete', {
            parent: 'admeval',
            url: '/{id}/delete',
            data: {
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/ati/deleteperfilconfirm.html',
                    controller: 'PerfilDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UsuariosATI', function(UsuariosATI) {
                            return UsuariosATI.get({id : $stateParams.id,tipo : 70}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('admeval', null, { reload: 'admeval' });
                }, function() {
                    $state.go('admeval');
                });
            }]
        });
    }

})();

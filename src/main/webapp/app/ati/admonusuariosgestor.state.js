(function() {
    'use strict';

    angular
        .module('campoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('admgestor', {
            parent: 'app',
            url: '/admonusuariosgestor?page&sort&search',
            data: {
            	authorities: ['ATI_EVALUACION_DOCENTE'],
                pageTitle: 'Administrar Usuarios ATI'
            },
            views: {
                'content@': {
                    templateUrl: 'app/ati/admonusuariosgestor.html',
                    controller: 'UsuariosGestorController',
                    controllerAs: 'vm'
                }
            },
            params: {
                tipo: 71
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
        .state('admperfilgestor', {
            parent: 'admgestor',
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
                                rol: 71,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('admgestor', null, { reload: 'admgestor' });
                }, function() {
                    $state.go('admgestor');
                });
            }]
        })
        .state('admgestor.delete', {
            parent: 'admgestor',
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
                            return UsuariosATI.get({id : $stateParams.id,tipo : 71}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('admgestor', null, { reload: 'admgestor' });
                }, function() {
                    $state.go('admgestor');
                });
            }]
        });
    }

})();

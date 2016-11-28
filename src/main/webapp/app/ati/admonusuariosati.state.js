(function() {
    'use strict';

    angular
        .module('campoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('ati', {
            parent: 'app',
            url: '/admonusuariosati?page&sort&search',
            data: {
            	authorities: ['ATI_EVALUACION_DOCENTE'],
                pageTitle: 'Administrar Usuarios ATI'
            },
            views: {
                'content@': {
                    templateUrl: 'app/ati/admonusuariosati.html',
                    controller: 'UsuariosATIController',
                    controllerAs: 'vm'
                }
            },
            params: {
                tipo: 69
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
        .state('admonati', {
            parent: 'ati',
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
                                rol: 69,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('ati', null, { reload: 'ati' });
                }, function() {
                    $state.go('ati');
                });
            }]
        })
        .state('admonati.delete', {
            parent: 'ati',
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
                            return UsuariosATI.get({id : $stateParams.id,tipo : 69}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ati', null, { reload: 'ati' });
                }, function() {
                    $state.go('ati');
                });
            }]
        });
    }

})();

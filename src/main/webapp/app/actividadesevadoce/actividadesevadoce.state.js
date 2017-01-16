(function() {
    'use strict';

    angular
        .module('campoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('adminactividades', {
            parent: 'app',
            url: '/actividades?page&sort&search',
            data: {
            	authorities: ['ATI_EVALUACION_DOCENTE','ADMTVO_EVALUACION_DOCENTE'],
                pageTitle: 'Administrar Actividades'
            },
            views: {
                'content@': {
                    templateUrl: 'app/actividadesevadoce/actividadesevaluaciondocente.html',
                    controller: 'ActividadesEvaDoceController',
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
        .state('adminactividades.new', {
            parent: 'adminactividades',
            url: '/new',
            data: {
            	authorities: ['ATI_EVALUACION_DOCENTE','ADMTVO_EVALUACION_DOCENTE']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/actividadesevadoce/actividades-dialog.html',
                    controller: 'ActividadEvaDoceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                tipoActividad: null,
                                institucion: null,
                                indicePeriodo: null,
                                anioEscolar: null,
                                inicio: null,
                                fin: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('adminactividades', null, { reload: 'adminactividades' });
                }, function() {
                    $state.go('adminactividades');
                });
            }]
        })
        .state('adminactividades.edit', {
            parent: 'adminactividades',
            url: '/{id}/edit',
            data: {
            	authorities: ['ATI_EVALUACION_DOCENTE','ADMTVO_EVALUACION_DOCENTE']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/actividadesevadoce/actividades-dialog.html',
                    controller: 'ActividadEvaDoceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ActividadesEvaluacion', function(ActividadesEvaluacion) {
                            return ActividadesEvaluacion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('adminactividades', null, { reload: 'adminactividades' });
                }, function() {
                    $state.go('^');
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

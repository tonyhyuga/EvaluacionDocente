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
        .state('gestor.ambito', {
            parent: 'app',
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
                        	var a=GestorAcademico.obtener({id : $stateParams.id , idp : $stateParams.idp}).$promise
                           if(a==null)
                        	   a={tipoAmbito: null
                           			}
                        	return a;
                        }]
                    }
                }).result.then(function() {
                    $state.go('gestor', null, { reload: 'gestor' });
                }, function() {
                    $state.go('gestor');
                });
            }]
        })
    }

})();

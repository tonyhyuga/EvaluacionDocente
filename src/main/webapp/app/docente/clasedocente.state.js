(function() {
    'use strict';

    angular
        .module('campoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('docente', {
            parent: 'app',
            url: '/docente?page&sort&search',
            data: {
            	authorities: ['PROFESOR'],
                pageTitle: 'Mis Asignaturas - Evaluación Docente'
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
        .state('autoevaluacion', {
            parent: 'docente',
            url: '/autoevaluacion/{ambito}/{profesor}',
            data: {
            	authorities: ['PROFESOR'],
                pageTitle: 'Autoevaluación - Evaluación Docente'
            },
            views: {
                'content@': {
                    templateUrl: 'app/docente/autoevaluacion.html',
                    controller: 'AutoevaluacionController',
                    controllerAs: 'vm'
                }
            },
            params: {
            	ambito: '$stateParams.ambito',
            	profesor: '$stateParams.profesor'
            }
        })
    }

})();

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
        .state('docente-oo2', {
            parent: 'docente',
            url: '/docente-oo2?page&sort&search',
            data: {
            	authorities: ['PROFESOR'],
                pageTitle: 'Portal Gestor Academico'
            },
            views: {
                'content@': {
                    templateUrl: 'app/docente/profesor-oo-2.html',
                    controller: 'DocenteController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('docente-oo3', {
            parent: 'docente',
            url: '/docente-oo3?page&sort&search',
            data: {
            	authorities: ['PROFESOR'],
                pageTitle: 'Portal Gestor Academico'
            },
            views: {
                'content@': {
                    templateUrl: 'app/docente/profesor-oo-3.html',
                    controller: 'DocenteController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('docente-oo4', {
            parent: 'docente',
            url: '/docente-oo4?page&sort&search',
            data: {
            	authorities: ['PROFESOR'],
                pageTitle: 'Portal Gestor Academico'
            },
            views: {
                'content@': {
                    templateUrl: 'app/docente/profesor-oo-4.html',
                    controller: 'DocenteController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('docente-oo5', {
            parent: 'docente',
            url: '/docente-oo5?page&sort&search',
            data: {
            	authorities: ['PROFESOR'],
                pageTitle: 'Portal Gestor Academico'
            },
            views: {
                'content@': {
                    templateUrl: 'app/docente/profesor-oo-5.html',
                    controller: 'DocenteController',
                    controllerAs: 'vm'
                }
            }
        })

    }

})();

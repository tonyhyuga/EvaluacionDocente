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
    }

})();

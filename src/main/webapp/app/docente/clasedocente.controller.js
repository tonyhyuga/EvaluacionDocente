(function() {
    'use strict';

    angular
        .module('campoApp')
        .controller('DocenteController', DocenteController);

    DocenteController.$inject = ['$scope', '$state', 'Docente', 'ParseLinks', 'AlertService', 'pagingParams', 'paginationConstants','AniosEscolares'];

    function DocenteController ($scope, $state, Docente, ParseLinks, AlertService, pagingParams, paginationConstants,AniosEscolares) {
        var vm = this;
        
        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.anioSeleccionado=pagingParams.anio;
        vm.indicePeriodo=pagingParams.indice;
        vm.search=search;

        cargarDropdrown();

        loadAll();

        function loadAll () {
        	Docente.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort(),
                anio: vm.anioSeleccionado,
                indice: vm.indicePeriodo
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                //alert(data);
                vm.clases = data;
                vm.page = pagingParams.page;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function loadPage (page) {
            vm.page = page;
            vm.transition();
        }

        function transition () {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch,
                anio: vm.anioSeleccionado,
                indice: vm.indicePeriodo
            });
        }
        
        function cargarDropdrown(){
        	vm.aniosEscolares=AniosEscolares.query();
        }
        

        
        function search(){
        	
        	if(vm.anioSeleccionado==null )
        		return ;
        	$state.transitionTo($state.$current, {
                page: 0,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch,
//                type: vm.type,
                anio: vm.anioSeleccionado,
                indice: vm.indicePeriodo
            },onSuccess);
        	 function onSuccess(data, headers) {
                 vm.links = ParseLinks.parse(headers('link'));
                 vm.totalItems = headers('X-Total-Count');
                 vm.queryCount = vm.totalItems;
                 vm.usuarios = data;
                 vm.page = pagingParams.page;
             }
        	
        }
        
    }
})();

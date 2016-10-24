(function() {
    'use strict';

    angular
        .module('campoApp')
        .controller('GestorController', GestorController);

    GestorController.$inject = ['$scope', '$state', 'GestorAcademico', 'ParseLinks', 'AlertService', 'pagingParams', 'paginationConstants', 'GestorAmbitoService','LoginService'];

    function GestorController ($scope, $state, GestorAcademico, ParseLinks, AlertService, pagingParams, paginationConstants,GestorAmbitoService,LoginService) {
        var vm = this;
        
        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.openDialog=openDialog;
       
        loadAll();
        vm.isNew = isNew;
        function loadAll () {
        	GestorAcademico.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort()
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
                vm.usuarios = data;
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
        
        function openDialog () {
        	var entity={tipo:null
        	}
        	GestorAmbitoService.open(entity);
        	//LoginService.open();
        }

        function transition () {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch
            });
        }
        
        function isNew(){
        	//alert(vm.usuarios.evaluacion ==null)
        	if( vm.usuarios.evaluacion.id ==null )
        		return true;
        	else
        		return false;
        }

    }
})();

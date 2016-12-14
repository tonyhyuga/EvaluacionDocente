(function() {
    'use strict';

    angular
        .module('campoApp')
        .controller('GestorController', GestorController)
//        .filter('makeUppercase', function () {
//			  return function (item) {
////			    return item.toUpperCase();
////			    return item.toLowerCase();
////				  return "Sustituyendo..";
//			  };
//			});
//         .filter('startsWithA', function () {
//        	  return function (items) {
//        		    var filtered = [];
//        		    for (var i = 0; i < items.length; i++) {
//        		      var item = items[i];
//        		      if (/b/i.test(item.clase.asignaturaBase.nombre.substring(0, 1))) {
//        		        filtered.push(item);
//        		      }
//        		    }
//        		    return filtered;
//
//			  };
//			});
//              .filter('startsWithA', function () {
//        	  return function (items) {
//        		    var filtered = [];
//        		    var input = "Biogeografía";
//        		    for (var i = 0; i < items.length; i++) {
//        		      var item = items[i];
//        		      var buscador = new RegExp(input);
//        		      
//        		      if(buscador.test(item.clase.asignaturaBase.nombre,input)){
////        		      if (/b/i.test(item.clase.asignaturaBase.nombre.substring(0, 1))) {
//        		        filtered.push(item);
//        		      }
//        		    }
//        		    return filtered;
//
//			  };
//			});
         .filter('filtrogestor', function () {
        	 return function(usuarios, start) {
        		    return usuarios.slice(start);

			  };
			});

    GestorController.$inject = ['$scope', '$state', 'GestorAcademico', 'ParseLinks', 'AlertService', 'pagingParams', 'paginationConstants', 'GestorAmbitoService','LoginService','downloadService'];

    function GestorController ($scope, $state, GestorAcademico, ParseLinks, AlertService, pagingParams, paginationConstants,GestorAmbitoService,LoginService,downloadService) {
        var vm = this;
        
        vm.ejemplo = 'ejemplo texto';
        vm.filtrogestores = filtrogestoresdemo();
        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.openDialog=openDialog;
        vm.download=download;
       
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
        
        function download(idAmbito){
            downloadService.donwl(idAmbito)
            .then(function(success) {
                console.log('success : ' + success);
            }, function(error) {
                console.log('error : ' + error);
            });
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
        
        function filtrogestoresdemo(){
        	return 'sdfsdf ';
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

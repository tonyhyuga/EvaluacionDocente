(function() {
    'use strict';

    angular
        .module('campoApp')
        .controller('ReporteEvaluativoController', ReporteEvaluativoController);

    ReporteEvaluativoController.$inject = ['$scope', '$state', 'ReporteEvaluativoService', 'ParseLinks', 'AlertService', 'pagingParams', 'paginationConstants', 'GestorAmbitoService','LoginService','downloadService',
    	'AniosEscolares','ReporteProfesorService','ReporteAlumnoService','Instituciones'];

    function ReporteEvaluativoController ($scope, $state, ReporteEvaluativoService, ParseLinks, AlertService, pagingParams, paginationConstants,GestorAmbitoService,LoginService,downloadService,
    		AniosEscolares,ReporteProfesorService,ReporteAlumnoService,Instituciones) {
        var vm = this;
        
       // vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        //vm.openDialog=openDialog;
     
        vm.indicePeriodo=pagingParams.indice;
        vm.search=search;
        vm.type=pagingParams.type;
        vm.currentSearch=pagingParams.search;
        vm.anioSeleccionado=pagingParams.anio;
        vm.institucionSelected=pagingParams.centro;
        vm.evaluacionesPendientes=pagingParams.ep;
        cargarDropdrown();
        loadAll();
        vm.isNew = isNew;
        function loadAll () {
        	ReporteEvaluativoService.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort(),
                search:  vm.currentSearch,
                type: vm.type,
                indice: vm.indicePeriodo,
                anio: vm.anioSeleccionado,
                centro:vm.institucionSelected,
                ep: vm.evaluacionesPendientes,
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
                vm.wrappers = data;
                vm.page = pagingParams.page;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
       

//        function loadPage (page) {
//            vm.page = page;
//            vm.transition();
//        }
        
//        function openDialog () {
//        	var entity={tipo:null
//        	}
//        	GestorAmbitoService.open(entity);
//        }

        function transition () {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch,
                type: vm.type,
                indice: vm.indicePeriodo,
                anio: vm.anioSeleccionado,
                centro: vm.institucionSelected,
                ep: vm.evaluacionesPendientes,
            },onSuccess,{reload:false});
       	 function onSuccess(data, headers) {
             vm.links = ParseLinks.parse(headers('link'));
             vm.totalItems = headers('X-Total-Count');
             vm.queryCount = vm.totalItems;
             vm.wrappers = data;
             vm.page = pagingParams.page;
         }
        }
        
        function isNew(){
        	if( vm.usuarios.evaluacion.id ==null )
        		return true;
        	else
        		return false;
        }
        
        function cargarDropdrown(){
        	vm.aniosEscolares=AniosEscolares.query();
        	vm.dependencias=Instituciones.query();
        }
        
        function search(){
        	
        	if(vm.anioSeleccionado==null )
        		return ;
        	$state.transitionTo($state.$current, {
                page: 0,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch,
                type: vm.type,
                indice: vm.indicePeriodo,
                anio: vm.anioSeleccionado,
                aniostr: vm.anioSeleccionado,
                centro: vm.institucionSelected,
                ep: vm.evaluacionesPendientes
            },onSuccess);
        	 function onSuccess(data, headers) {
                 vm.links = ParseLinks.parse(headers('link'));
                 vm.totalItems = headers('X-Total-Count');
                 vm.queryCount = vm.totalItems;
                 vm.wrappers = data;
                 vm.page = pagingParams.page;
             }
        	
        }

    }
})();

(function() {
    'use strict';

    angular
        .module('campoApp')
        .controller('GestorController', GestorController);

    GestorController.$inject = ['$scope', '$state', 'GestorAcademico', 'ParseLinks', 'AlertService', 'pagingParams', 'paginationConstants', 'GestorAmbitoService','LoginService','downloadService','AniosEscolares','ReporteProfesorService','ReporteAlumnoService'];

    function GestorController ($scope, $state, GestorAcademico, ParseLinks, AlertService, pagingParams, paginationConstants,GestorAmbitoService,LoginService,downloadService,AniosEscolares,ReporteProfesorService,ReporteAlumnoService) {
        var vm = this;
        
       // vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        //vm.openDialog=openDialog;
        vm.download=download;
//        vm.descargarReporteAlumnos=descargarReporteAlumnos;
//        vm.descargarReporteProfesor=descargarReporteProfesor;
        vm.indicePeriodo=pagingParams.indice;
        vm.search=search;
        vm.type=pagingParams.type;
        vm.currentSearch=pagingParams.search;
        vm.anioSeleccionado=pagingParams.anio;
        vm.evaluacionesPendientes=pagingParams.ep;
        cargarDropdrown();
        loadAll();
        vm.isNew = isNew;
        function loadAll () {
        	GestorAcademico.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort(),
                search:  vm.currentSearch,
                type: vm.type,
                indice: vm.indicePeriodo,
                anio: vm.anioSeleccionado,
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
        
//        function descargarReporteAlumnos(idAmbito){
//        	ReporteAlumnoService.donwl(idAmbito)
//            .then(function(success) {
//                console.log('success : ' + success);
//            }, function(error) {
//                console.log('error : ' + error);
//            });
//        }
//        
//        function descargarReporteProfesor(idAmbito){
//        	ReporteProfesorService.donwl(idAmbito)
//            .then(function(success) {
//                console.log('success : ' + success);
//            }, function(error) {
//                console.log('error : ' + error);
//            });
//        }

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
                ep: vm.evaluacionesPendientes,
            },onSuccess,{reload:false});
       	 function onSuccess(data, headers) {
             vm.links = ParseLinks.parse(headers('link'));
             vm.totalItems = headers('X-Total-Count');
             vm.queryCount = vm.totalItems;
             vm.usuarios = data;
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
                ep: vm.evaluacionesPendientes,
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

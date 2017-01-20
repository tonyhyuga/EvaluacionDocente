(function() {
    'use strict';

    angular
        .module('campoApp')
        .controller('ActividadEvaDoceDialogController', ActividadEvaDoceDialogController);

    ActividadEvaDoceDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ActividadesEvaluacion', 'Instituciones','TipoActividadesEvaluacion','AniosEscolares'];

    function ActividadEvaDoceDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ActividadesEvaluacion, Instituciones,TipoActividadesEvaluacion,AniosEscolares) {
        var vm = this;

        vm.actividad = entity;
        vm.clear = clear;
        vm.save = save;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.selectedTipo;
        vm.institucionSelected;
        vm.anioSelected;
        vm.indicePeriodo;

        cargarDropDowns();
        
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.actividad.id !== null) {
            	ActividadesEvaluacion.update(vm.actividad, onSaveSuccess, onSaveError);
            } else {
            	ActividadesEvaluacion.save(vm.actividad, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('campoApp:actividadUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
        
        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
        
        function cargarDropDowns(){
        	vm.dependencias=Instituciones.query();
        	vm.tiposActividades=TipoActividadesEvaluacion.query();
        	vm.aniosEscolares=AniosEscolares.query();
        }

    }
})();

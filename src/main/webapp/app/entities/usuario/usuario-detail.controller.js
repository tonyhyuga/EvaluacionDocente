(function() {
    'use strict';

    angular
        .module('campoApp')
        .controller('UsuarioDetailController', UsuarioDetailController);

    UsuarioDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Usuario'];

    function UsuarioDetailController($scope, $rootScope, $stateParams, previousState, entity, Usuario) {
        var vm = this;

        vm.usuario = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('campoApp:usuarioUpdate', function(event, result) {
            vm.usuario = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

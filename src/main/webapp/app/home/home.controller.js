(function() {
    'use strict';

    angular
        .module('campoApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function HomeController ($scope, Principal, LoginService, $state) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.hasRol=hasRol;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }
        function hasRol () {
        	var has=false
        	
        	if(vm.account!=null && vm.account.authorities!=null){
        		 for(var i=0;i<vm.account.authorities.length;i++){
        			 if(vm.account.authorities[i]=='PROFESOR'){
        				 has=true; 
        			 }
        			 if(vm.account.authorities[i]=='GESTOR_ACADEMICO'){
        				 has=true;}
        			 if(vm.account.authorities[i]=='ALUMNO'){
        				 has=true;}
        		 }
        	}

            return has;
        }
    }
})();

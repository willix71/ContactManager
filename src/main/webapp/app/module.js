(function() {
	var app = angular.module('contactManager', ['ngRoute','ngSanitize','mgcrea.ngStrap','ngResource']);

	app.config(['$routeProvider', '$locationProvider',function($routeProvider, $locationProvider){
		$routeProvider
		.when('/', {
			controller: 'indexCtl',
			templateUrl: 'assets/partials/index.html'
			})
		.when('/contact/:id', {
			controller: 'contactCtl',
			templateUrl: 'assets/partials/contact.html'
			})
		.when('/add', {
			controller: 'addCtl',
			templateUrl: 'assets/partials/add.html'
			})
		.when('/test', {
			controller: 'testCtrl',
			templateUrl: 'assets/partials/test.html'
			})				
		.otherwise({
			redirectTo: '/'
			});

		// $locationProvider.hashPrefix('!');
		// $locationProvider.html5Mode(true);

	}]);
	
	app.controller('appCtl', ['$scope', '$location',function($scope, $location){
		$scope.startSearch = function(){
			$location.path('#/');
		};

		$scope.pageClass = function(path){
			return (path == $location.path()) ? 'active' : '';
		};
	}]);

}) ();
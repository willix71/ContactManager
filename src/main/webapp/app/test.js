(function(){

	var app = angular.module('contactManager');

	// ========= test ==================================================================================

	app.controller('testCtrl', ['$scope','$http','$filter','filterFilter', 'demoService','demoServices', 
		function($scope, $http, $filter,filterFilter,demoService,demoServices){
		$scope.ds=demoService;
		$scope.clickHandler = function(){
			$scope.isHidden = !$scope.isHidden;
		};

		$scope.styleDemo = function(){
			if(!$scope.styler){
				return;
			}

			return {
				background: 'green',
				fontWeight: 'bold'
			};
		};

		// var store = $scope;
		// store.contacts = [];
		// $http.get('assets/data/contacts.json').success(function(data){ 
		// 	//store.contacts = $filter('filter')(data,{'name': 'P'}); 
		// 	//store.contacts = filterFilter(data,{'name': 'P'}); 
		// 	store.contacts = data;
		// });
	}]);

	app.filter('truncate', function(){
		return function(input, limit){
			return (input.length > limit) ? input.substr(0, limit)+'...' : input;
		};
	});

	app.value('demoService', 'abc123');

	app.factory('demoServices', function demoServiceFactory(){
		return 'abc123';
	});
})()

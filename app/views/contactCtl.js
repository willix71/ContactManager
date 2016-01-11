angular.module('contactManager').controller('contactCtl', ['$scope','$routeParams','contactManager', function($scope,$routeParams,contactManager){
	$scope.contact = contactManager.find($routeParams.id);
}]);
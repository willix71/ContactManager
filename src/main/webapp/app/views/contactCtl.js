angular.module('contactManager').controller('contactCtl', ['$scope','$routeParams','contactManager', function($scope,$routeParams,contactManager){

	$scope.contact = contactManager.find($routeParams.id);
		
	// set the first (direct) avatar
	$scope.gravatarUrl = 'http://gravatar.com/avatar/'+toMd5($scope.contact.email);
	
}]);
angular.module('contactManager').controller('contactCtl', ['$scope','$routeParams','$http', 'contactManager', function($scope,$routeParams,$http,contactManager){
	//$scope.contact = contactManager.find($routeParams.id);
	console.log("Fetching contact " + $routeParams.id);
	$http.get("http://localhost:8080/ContactManager/rest/contact/" + $routeParams.id).success(function(data) {
		console.log("Fetched contact " + data.name);
		
		$scope.contact = data;
		
		// set the first (direct) avatar
		$scope.gravatarUrl = 'http://gravatar.com/avatar/'+toMd5(data.email);
	});
}]);
angular.module('contactManager').controller('indexCtl', ['$scope', '$http', 'contactManager', function($scope, $http, contactManager){
	//$scope.contacts = contactManager.get();
	$http.get("http://localhost:8080/ContactManager/rest/contact").success(function(data) {
		$scope.contacts = data;
	});
	
	$scope.remove = function(contact){
		console.log("Removing contact " + contact.name);
		$http({
			method: 'DELETE',
			url: "http://localhost:8080/ContactManager/rest/contact/"+contact.id
			
		}).success(function() {
			console.log("Removed contact " + contact.name);
			
			$http.get("http://localhost:8080/ContactManager/rest/contact").success(function(data) {
				$scope.contacts = data;
			});
		});
	};
}]);
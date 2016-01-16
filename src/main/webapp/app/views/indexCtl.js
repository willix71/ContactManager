angular.module('contactManager').controller('indexCtl', ['$scope', 'contactManager', function($scope, contactManager){
	$scope.contacts = contactManager.get();
	
	$scope.remove = function(contact){
		console.log("Removing contact " + contact.name);
		contactManager.remove(contact.id);
		$scope.contacts = contactManager.get();		
	};
}]);
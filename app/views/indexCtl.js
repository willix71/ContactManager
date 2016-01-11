angular.module('contactManager').controller('indexCtl', ['$scope', 'contactManager', function($scope, contactManager){
	$scope.contacts = contactManager.get();

	$scope.remove = function(contact){
		var t = 'zxyzxyzxyzxy123456789';
		console.log(t);
		contactManager.remove(contact.id);
	};
}]);
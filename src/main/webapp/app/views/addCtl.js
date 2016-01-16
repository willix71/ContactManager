angular.module('contactManager').controller('addCtl', ['$scope','$alert','contactManager',function($scope,$alert,contactManager){
	var alert = $alert({
		title: 'Success!',
		content: 'The contact was added successfully to the list.',
		type: 'success',
		container: '#alertContainer',
		show: false,
	});

	$scope.submitContact = function(){
		contactManager.create($scope.contact);
		
		$scope.comfirmedContact = $scope.contact;
		$scope.contact = null;
		$scope.added = true;

		alert.show();
	};
}]);
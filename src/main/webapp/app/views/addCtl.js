angular.module('contactManager').controller('addCtl', ['$scope','$alert','$http', 'contactManager',function($scope,$alert,$http,contactManager){
	var alert = $alert({
		title: 'Success!',
		content: 'The contact was added successfully to the list.',
		type: 'success',
		container: '#alertContainer',
		show: false,
	});

	$scope.submitContact = function(){
		$scope.contact.id = new Date().getTime();

		//contactManager.create($scope.contact);
		$http.post('http://localhost:8080/ContactManager/rest/contact/', $scope.contact);
		
		$scope.comfirmedContact = $scope.contact;
		$scope.contact = null;
		$scope.added = true;

		alert.show();
	};
}]);
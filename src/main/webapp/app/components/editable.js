angular.module('contactManager').directive('editable', ['$http', function($http){
	return {
		restrict: 'AE',
		templateUrl: 'assets/partials/editable.html',
		scope: {
			value: '=editable',
			field: '@fieldType',
			fieldName: '@fieldName',
			contactId: '=contactId'
				
		},
		controller: ['$scope', function($scope){
			$scope.field = ($scope.field) ? $scope.field : 'text';

			$scope.editor = {
				showing: false,
				value: $scope.value
			};

			$scope.toggleEditor = function(){
				$scope.editor.showing = !$scope.editor.showing;
				$scope.editor.value = $scope.value;
			};

			$scope.save = function(){
				var rollbackValue = $scope.value;				
				$scope.value = $scope.editor.value;
				$scope.toggleEditor();
				
				// build the json to send
				var j = JSON.parse('{"' + $scope.fieldName+ '":"'+$scope.value + '"}');

				$http.patch('http://localhost:8080/ContactManager/rest/contact/'+$scope.contactId, j).error(function() {
					$scope.value = rollbackValue;
				});
			};
		}]
	};
}])

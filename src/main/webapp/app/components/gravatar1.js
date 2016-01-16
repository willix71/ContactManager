angular.module('contactManager').directive('gravatar1', function(){
	function computeUrl(email, size) {
		var email5 = toMd5(email);
		return 'http://gravatar.com/avatar/'+email5 + "?s=" + size;
	}
	
	return {
		restrict: 'AE',
		template: '<img ng-src="{{gravatarUrl1}}" class="{{class}}">',
		replace: true,
		scope: {
			contactEmail: '=email',
			size: '@',
			class: '@'
		},
		
		controller: ['$scope', function($scope){
			// keep an eye on the email and change the avatar url as soon as it's done 
			$scope.$watch('contactEmail', function(email) {		
				$scope.gravatarUrl1 = computeUrl(email, $scope.size);
			});
		}]
	};
})
angular.module('contactManager').directive('gravatar2', function(){
	function computeUrl(email, size) {
		var email5 = toMd5(email);
		return 'http://gravatar.com/avatar/'+email5 + "?s=" + size;
	}
	
	return {
		restrict: 'AE',
		template: '<img ng-src="{{gravatarUrl2}}" class="{{class}}">',
		replace: true,
		link: function(scope, elem, attrs){
			var imgsize = attrs.size; // remember the size for the watch
			scope.class = attrs.class; // pass the class to the template
			scope.$watch(attrs.email, function(email) {		
				scope.gravatarUrl2 = computeUrl(email, imgsize);
			});
		}
	};
})
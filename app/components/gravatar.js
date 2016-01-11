angular.module('contactManager').directive('gravatar', function(){
	return {
		restrict: 'AE',
		template: '<img src="{{img}}" class="{{class}}">',
		replace: true,
		link: function(scope, elem, attrs){
			var md5=toMd5;
			var size = (attrs.size) ? attrs.size : 64;
			scope.img = 'http://gravatar.com/avatar/'+md5(attrs.email)+'?s='+size;
			scope.class = attrs.class;
		}
	}
})
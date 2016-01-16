angular.module('contactManager').factory('contactManager', ['$resource',function($resource) {
	var Resource = $resource('http://localhost:10080/ContactManager/rest/contact/:id', {id:'@id'},{
		update: {method: 'PATCH'}
	});
	return {
		get: function(){
			return Resource.query();
		},
		find: function(id){
			return Resource.get({'id':id});			
		},
		create: function(contact){
			Resource.save(null,contact);
		},
		update: function(id, modification,onError){
			Resource.update({'id':id},modification,null,onError);
		},
		remove: function(id){
			Resource.remove({'id':id});	
		}
	};
}]);
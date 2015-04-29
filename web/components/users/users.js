'use strict';

var API_URL = 'http://localhost:8080';

angular.module('paasport.users', ['ui.router', 'ngResource'])

.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider

	.state('users', {
		url: '/users',
		views: {
			'header@': {
				templateUrl: 'components/shared/header.html'
			},
			'nav@': {
				templateUrl: 'components/users/nav.html'
			},
			'main@': {
				templateUrl: 'components/users/main.html'
			}
		}
	})

	.state('users.list', {
		url: '/list',
		views: {
			'header@': {
				templateUrl: 'components/shared/header.html'
			},
			'nav@': {
				templateUrl: 'components/users/nav.html'
			},
			'main@': {
				templateUrl: 'components/users/list.html',
				controller: 'UserListController'
			}
		}
	})

	.state('users.edit', {
		url: '/:id',
		views: {
			'header@': {
				templateUrl: 'components/shared/header.html'
			},
			'nav@': {
				templateUrl: 'components/users/nav.html'
			},
			'main@': {
				templateUrl: 'components/users/edit.html',
				controller: 'UserEditController'
			}
		}
	})

	.state('users.add', {
		url: '/add',
		views: {
			'header@': {
				templateUrl: 'components/shared/header.html'
			},
			'nav@': {
				templateUrl: 'components/users/nav.html'
			},
			'main@': {
				templateUrl: 'components/users/add.html',
				controller: 'UserAddController'
			}
		}
	})
})

/* SERVICES */

.factory('UsersFactory', function($resource) {
	return $resource(API_URL + '/getallusers', {}, {
		query: {
			method: 'GET',
			isArray: true
		},
		create: {
			method: 'POST'
		}
	})
})

.factory('AddFactory', function($resource) {
	return $resource(API_URL + '/adduser', {}, {
		save: {
			method: 'POST',
			params: {
				firstname: '@firstname',
				lastname: '@lastname',
				username: '@username',
				email: '@email',
				password: '@password'
			}
		}
	})
})

.factory('DeleteFactory', function($resource) {
	return $resource(API_URL + '/deleteuser', {}, {
		delete: {
			method: 'DELETE',
			params: {
				userid: '@id'
			}
		}
	})
})

/* CONTROLLERS */

.controller('UserListController', ['$scope', 'UsersFactory', 'DeleteFactory',
	function($scope, UsersFactory, DeleteFactory) {
		
		$scope.users = UsersFactory.query();

		$scope.deleteUser = function deleteUser(user){
			DeleteFactory.delete({ userid : user.id })
			var index = $scope.users.indexOf(user);
			$scope.users.splice(index, 1);
		}		
	}
])

.controller('UserAddController', ['$scope', 'UsersFactory', 'AddFactory', '$location',
	function($scope, UsersFactory, AddFactory, $location) {
		$scope.createNewUser = function() {
			AddFactory.save($scope.user);
			$scope.users = UsersFactory.query();
			$location.path('/users/list');
		}
	}
])

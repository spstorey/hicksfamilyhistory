'use strict';

var app = angular.module('app', ['ngResource','ngRoute','ui.bootstrap']);

app.config(['$routeProvider', function($routeProvider) {
	$routeProvider.
		when('/', {templateUrl: 'spalsh.html', controller: SplashCtrl}).
		otherwise({redirectTo: '/'});
}]);


var SplashCtrl = function ($scope) {

    $scope.slides = [
        {
            image: 'https://copy.com/KcXFL4TvROdF',
            text: 'Dan, Lauren, Sharon and Jessie'
        },
        {
            image: 'https://copy.com/w9rE9k7RQnsi',
            text: 'Me !'
        },
        {
            image: 'https://copy.com/iZ8WblnUDQsX',
            text: 'My beautiful wife and me'
        }
    ];
};
'use strict';

var app = angular.module('app', ['ngResource','ngRoute','ui.bootstrap']);

app.config(['$routeProvider', function($routeProvider) {
	$routeProvider.
		when('/', {templateUrl: 'splash.html', controller: SplashCtrl}).
        when('/news', {templateUrl: 'news.html', controller: NewsCtrl}).
        when('/people', {templateUrl: 'people.html', controller: PeopleCtrl}).
        when('/photos', {templateUrl: 'photos.html', controller: PhotosCtrl}).
        when('/tree', {templateUrl: 'tree.html', controller: TreeCtrl}).
        when('/contact', {templateUrl: 'contact.html', controller: ContactCtrl}).
		otherwise({redirectTo: '/'});
}]);

var NewsCtrl = function ($scope) {
};

var PeopleCtrl = function ($scope) {
};

var PhotosCtrl = function ($scope, $http) {

    $http.get('/media/albums').success(function (data) {

        $scope.albums = data._embedded.albums;

        for (var cursor in $scope.albums) {

            $http.get($scope.albums[cursor]._links.self.href).success(function (album) {
                if (album._links.coverPhoto) {
                    for (var finder in $scope.albums) {
                        if ($scope.albums[finder].name === album.name) {
                            $scope.albums[finder].coverPhoto = album._links.coverPhoto.href;
                            console.log($scope.albums[finder]);
                        }
                    }
                }
            });
        }
    });
};

var TreeCtrl = function ($scope) {
};

var ContactCtrl = function ($scope) {
};

var SplashCtrl = function ($scope) {

    $scope.slides = [
        { image: 'https://copy.com/CZfPLD6RjZqC', text: '' },
        { image: 'https://copy.com/mSqgSyvPprLh', text: '' },
        { image: 'https://copy.com/2Zv76oHQMiTi', text: '' },
        { image: 'https://copy.com/1NhJSIqbTvkQ', text: '' },
        { image: 'https://copy.com/acMODAhA9V6n', text: '' },
        { image: 'https://copy.com/n0SdKZBSAQdZ', text: '' },
        { image: 'https://copy.com/7rsP7h14gF6L', text: '' },
        { image: 'https://copy.com/yYU0eS9VlTFT', text: '' },
        { image: 'https://copy.com/t9dJoyxL6wFm', text: '' },
        { image: 'https://copy.com/yRHn0H1n6XkG', text: '' },
        { image: 'https://copy.com/MjYAUcWKlIRP', text: '' },
        { image: 'https://copy.com/tGu0eS66yb2h', text: '' },
        { image: 'https://copy.com/RWPnwdDC8S7U', text: '' },
        { image: 'https://copy.com/rGvQpYYeEwVG', text: '' },
        { image: 'https://copy.com/r3XqRJz4QQmS', text: '' },
        { image: 'https://copy.com/f8TUBKMQsEvB', text: '' },
        { image: 'https://copy.com/aNGVwEg82Lil', text: '' },
        { image: 'https://copy.com/X43jEtqie9fi', text: '' },
        { image: 'https://copy.com/eVaszEDsDV89', text: '' },
        { image: 'https://copy.com/R9WODnZHqu69', text: '' },
        { image: 'https://copy.com/L28GX3Xs1Me3', text: '' },
        { image: 'https://copy.com/MtEUcck1l3Jr', text: '' },
        { image: 'https://copy.com/XSqf9jlQpG86', text: '' },
        { image: 'https://copy.com/wWZfsF9mo9aI', text: '' }
    ];
};
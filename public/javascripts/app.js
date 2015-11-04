(function() {
    'use strict';

    angular
        .module('2GIG', [
            'ngRoute'
        ])
        .config(function($routeProvider) {
            $routeProvider
                .when('/', {
                    templateUrl: '../index.html',
                    controller: 'MainCtrl'
                })
                .when('/new', {
                    tempalteUrl: '../new.html',
                    controller: 'NewAdCtrl'
                })
                .when('/view/:adId', {
                    templateUrl: '../view.html',
                    controller: 'ViewAdCtrl'
                })
                .otherwise({
                    redirectTo: '/'
                });
        })
})();
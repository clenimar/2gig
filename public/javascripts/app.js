(function() {
    'use strict';

    angular
        .module('2GIG', [
            'ngRoute'
        ])
        .config(function($routeProvider) {
            $routeProvider
                .when('/', {
                    templateUrl: 'views/index.html',
                    controller: 'MainCtrl'
                })
                .when('/new', {
                    tempalteUrl: 'views/new.html',
                    controller: 'NewAdCtrl'
                })
                .when('/view/:adId', {
                    templateUrl: 'views/view.html',
                    controller: 'ViewAdCtrl'
                })
                .otherwise({
                    redirectTo: '/'
                });
        })
})();
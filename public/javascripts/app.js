(function() {
    'use strict';

    angular
        .module('2GIG', [
            'ui.router'
        ])
        .config(function($stateProvider, $urlRouterProvider) {
            $stateProvider
                .state('home', {
                    url: '/',
                    templateUrl: '/assets/partials/home.html',
                    controller: 'MainCtrl',
                    controllerAs: 'main'
                })
                .state('new', {
                    url: '/new',
                    templateUrl: '/assets/partials/new.html',
                    controller: 'NewAdCtrl'
                })
                .state('view', {
                    url: '/view/:id',
                    templateUrl: '/assets/partials/view.html',
                    controller: 'ViewAdCtrl'
                });

            $urlRouterProvider
                .otherwise('/');
        })
})();
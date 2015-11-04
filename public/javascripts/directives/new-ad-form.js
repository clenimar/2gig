(function() {
    'use strict';

    angular.module('2GIG')
        .directive('newAdForm', function() {
            return {
                restrict: 'E',
                templateUrl: '/assets/javascripts/directives/new-ad-form.html'
            };
        });
})();
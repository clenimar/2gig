(function() {
    'use strict';

    angular.module('2GIG')
        .directive('interestIcon', function() {
            return {
                restrict: 'E',
                templateUrl: '/assets/javascripts/directives/interest-icon.html'
            };
        });
})();